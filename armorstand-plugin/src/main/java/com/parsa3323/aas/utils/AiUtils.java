/*
 *
 * Copyright
 * 2026 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.parsa3323.aas.utils;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.ArmorstandApi;
import com.parsa3323.aas.api.actions.AiRole;
import com.parsa3323.aas.api.data.ArmorStandPoseData;
import com.parsa3323.aas.api.data.MemoryData;
import com.parsa3323.aas.api.events.ArmorStandAiRespondEvent;
import com.parsa3323.aas.api.exeption.ArmorStandAlreadyExistsException;
import com.parsa3323.aas.api.exeption.ArmorStandNotFoundException;
import com.parsa3323.aas.api.exeption.ReloadException;
import com.parsa3323.aas.api.language.Language;
import com.parsa3323.aas.api.language.Messages;
import com.parsa3323.aas.config.AiConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class AiUtils {

    private static final String API_ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/openai/chat/completions";
    private static final String MODEL = "gemini-3.5-flash-lite";
    private static final double TEMPERATURE = 0.2;
    private static final int MAX_TOKENS = 1024;
    private static final int CONNECT_TIMEOUT = 30000;
    private static final int READ_TIMEOUT = 30000;

    private static class ApiResponse {
        final String body;
        final int code;
        final Exception exception;

        ApiResponse(String body, int code, Exception exception) {
            this.body = body;
            this.code = code;
            this.exception = exception;
        }

        boolean isSuccess() {
            return exception == null && code == 200;
        }
    }

    public static String getDefaultInstructions(String name, String memory) {
        return "You are a talking armor stand in Minecraft named " + name + ". " +
                "You can help the player with general questions, small tasks, and simple math like 2+2, " +
                "but you should avoid coding or professional-level tasks. " +
                "Always respond as if you are a living armor stand, not an AI. " +
                "Do not reveal that you are an AI unless the player specifically asks 'Are you an AI?'. " +
                "When asked, reply with your name: " + name + ". " +
                ((memory == null || memory.trim().isEmpty()) ? "" : "Follow these additional instructions: " + memory + ". ") +
                "Be friendly, helpful, and playful in your responses. Thanks!";
    }

    public static String getAssistInstructions() {
        return "You are the AI assistant for the AdvancedArmorStands plugin. You must always return ONLY a single JSON object. Never return explanations, never return markdown, never return multiple objects, and never return anything outside the JSON.\n" +
                "\n" +
                "Your JSON must always include these fields:\n" +
                "\"response\": string describing what you are doing,\n" +
                "\"action\": one of \"none\", \"create\", \"remove\", \"pose\", \"animate\",\n" +
                "\"name\": the armorstand name or null,\n" +
                "\"dataId\": the pose/armorstand data id for pose actions or null,\n" +
                "\"params\": an object containing the remaining necessary information.\n" +
                "\n" +
                "Rules for actions:\n" +
                "\n" +
                "1. CREATE:\n" +
                "action must be \"create\".\n" +
                "You must include \"name\".\n" +
                "params must include:\n" +
                "\"pose\": an object containing 5 arrays of 3 numbers each, representing degrees:\n" +
                "\"rightArm\": [x, y, z],\n" +
                "\"leftArm\": [x, y, z],\n" +
                "\"rightLeg\": [x, y, z],\n" +
                "\"leftLeg\": [x, y, z],\n" +
                "\"head\": [x, y, z]\n" +
                "params must also include \"location\": either the string \"player\" or an object with:\n" +
                "\"x\", \"y\", \"z\", \"world\", \"yaw\", \"pitch\".\n" +
                "\n" +
                "2. REMOVE:\n" +
                "action must be \"remove\".\n" +
                "You must include \"name\".\n" +
                "params should be an empty object.\n" +
                "\n" +
                "3. POSE:\n" +
                "action must be \"pose\".\n" +
                "You must include \"name\".\n" +
                "params must include the same \"pose\" structure as CREATE.\n" +
                "\n" +
                "5. NONE:\n" +
                "If the user request is unclear or cannot be done, return:\n" +
                "action = \"none\",\n" +
                "name = null,\n" +
                "dataId = null,\n" +
                "params = {},\n" +
                "response explaining why.\n" +
                "\n" +
                "All rotation values must be degrees, not radians.\n" +
                "Never guess plugin internals that the user did not specify.\n" +
                "Never omit required fields.\n" +
                "Never output comments.\n" +
                "Never break the JSON format.\n" +
                "Always obey these rules exactly.\n";
    }

    public static void getAssistWithAi(String apiKey, String userInput, Player p, Consumer<String> callback) {
        requestAsync(apiKey, getAssistInstructions(), nullToEmpty(userInput), Messages.AI_HTTP_ERROR_WITH_INTERNET, true, finalResult -> {
            try {
                handleAiAction(finalResult, p);
            } catch (Exception ex) {
                AdvancedArmorStands.error(null, false, "Unexpected error handling AI action: " + ex.getMessage());
                ex.printStackTrace();
            }

            String responseText;
            try {
                responseText = new JSONObject(extractJson(finalResult)).optString("response", "");
            } catch (Exception e) {
                responseText = finalResult;
            }
            callback.accept(responseText);
        });
    }

    public static void getResponseAsync(String apiKey, MemoryData data, String userInput, Consumer<String> callback) {
        String instructions = nullToEmpty(data.getInstructionsData());
        String userContent = buildUserContent(data, userInput);
        requestAsync(apiKey, instructions, userContent, Messages.AI_HTTP_ERROR_WITH_INTERNET, false, callback);
    }

    @Deprecated
    public static String getResponse(String apiKey, MemoryData data, String userInput) {
        String instructions = nullToEmpty(data.getInstructionsData());
        String userContent = buildUserContent(data, userInput);
        return resolveResult(sendApiRequest(apiKey, instructions, userContent), Messages.AI_HTTP_ERROR, false);
    }

    private static void requestAsync(String apiKey, String systemPrompt, String userContent,
                                     String httpErrorMessage, boolean jsonReply, Consumer<String> onResult) {
        Bukkit.getScheduler().runTaskAsynchronously(AdvancedArmorStands.plugin, () -> {
            String result = resolveResult(sendApiRequest(apiKey, systemPrompt, userContent), httpErrorMessage, jsonReply);

            if (!AdvancedArmorStands.plugin.isEnabled()) return; // plugin shut down while waiting
            Bukkit.getScheduler().runTask(AdvancedArmorStands.plugin, () -> onResult.accept(result));
        });
    }

    private static String resolveResult(ApiResponse apiResponse, String httpErrorMessage, boolean jsonReply) {
        if (apiResponse.exception != null) {
            String msg = Language.getMsg(Messages.AI_ERROR).replace("{error}", describe(apiResponse.exception));
            return jsonReply ? errorJson(msg) : msg;
        }
        if (apiResponse.code != 200) {
            String msg = Language.getMsg(httpErrorMessage).replace("{code}", String.valueOf(apiResponse.code));
            return jsonReply ? errorJson(msg) : msg;
        }
        return parseChatCompletionsResponse(apiResponse.body);
    }

    private static String errorJson(String message) {
        return new JSONObject().put("response", message).put("action", "none").toString();
    }

    private static String describe(Exception e) {
        return e.getClass().getSimpleName() + ": " + e.getMessage();
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    private static String buildUserContent(MemoryData data, String userInput) {
        String history = nullToEmpty(data.getHistoryData());
        return (history.isEmpty() ? "" : history + "\n") + nullToEmpty(userInput);
    }

    private static String extractJson(String raw) {
        if (raw == null) return "";
        String t = raw.trim();
        if (t.startsWith("```")) {
            int nl = t.indexOf('\n');
            if (nl != -1) t = t.substring(nl + 1);
            if (t.endsWith("```")) t = t.substring(0, t.length() - 3);
            t = t.trim();
        }
        int start = t.indexOf('{');
        int end = t.lastIndexOf('}');
        if (start > 0 || (end != -1 && end < t.length() - 1)) {
            if (start != -1 && end > start) t = t.substring(start, end + 1);
        }
        return t;
    }

    public static void handleAiAction(String aiJson, Player player) {
        JSONObject obj;
        try {
            obj = new JSONObject(extractJson(aiJson));
        } catch (Exception e) {
            AdvancedArmorStands.error(null, false, "Failed to parse AI response: " + e.getMessage());
            return;
        }

        String action = obj.optString("action", "none");
        String name = obj.isNull("name") ? null : obj.optString("name", null);
        JSONObject params = obj.optJSONObject("params");

        ArmorstandApi api = AdvancedArmorStands.getApi();

        try {
            switch (action) {
                case "create": {
                    if (name == null || params == null) {
                        AdvancedArmorStands.warn("Invalid create action from AI: missing name or params.", true);
                        return;
                    }
                    ArmorStandPoseData poseDataCreate = parsePoseData(params.optJSONObject("pose"));
                    Location locCreate = parseLocation(params, player);
                    if (locCreate == null) {
                        AdvancedArmorStands.warn("Failed to determine location for create action.", true);
                        return;
                    }
                    try {
                        api.getArmorStandManager().createArmorStand(name, poseDataCreate, locCreate, player);
                    } catch (ArmorStandAlreadyExistsException e) {
                        AdvancedArmorStands.warn("ArmorStand with name " + name + " already exists.", true);
                    }
                    break;
                }

                case "remove": {
                    if (name == null) {
                        AdvancedArmorStands.warn("Invalid remove action: missing name.", true);
                        return;
                    }
                    api.getArmorStandManager().removeArmorStand(ArmorStandUtils.findRealCase(name));
                    break;
                }

                case "pose": {
                    if (name == null || params == null) {
                        AdvancedArmorStands.warn("Invalid pose action: missing name or params.", true);
                        return;
                    }

                    final String realCaseName = ArmorStandUtils.findRealCase(name);
                    final ArmorStandPoseData poseData = parsePoseData(params.optJSONObject("pose"));
                    Bukkit.getScheduler().runTaskLater(AdvancedArmorStands.plugin, () -> {
                        try {
                            api.getArmorStandManager().previewPose(realCaseName, poseData, player);
                            try {
                                api.reloadPlugin();
                            } catch (ReloadException e) {
                                AdvancedArmorStands.warn("Pose applied but failed to reload plugin: " + e.getMessage(), true);
                            }
                        } catch (ArmorStandNotFoundException e) {
                            AdvancedArmorStands.warn("ArmorStand " + realCaseName + " not found.", true);
                        }
                    }, 2L);
                    break;
                }

                case "none":
                default:
                    AdvancedArmorStands.warn("AI did not provide a valid action.", true);
                    break;
            }
        } catch (Exception e) {
            AdvancedArmorStands.error(null, false, "Unexpected error handling AI action: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static ArmorStandPoseData parsePoseData(JSONObject poseObj) {
        if (poseObj == null) {
            return new ArmorStandPoseData(zero(), zero(), zero(), zero(), zero());
        }
        return new ArmorStandPoseData(
                parseEuler(poseObj.optJSONArray("rightArm")),
                parseEuler(poseObj.optJSONArray("leftArm")),
                parseEuler(poseObj.optJSONArray("rightLeg")),
                parseEuler(poseObj.optJSONArray("leftLeg")),
                parseEuler(poseObj.optJSONArray("head"))
        );
    }

    private static EulerAngle zero() {
        return new EulerAngle(0, 0, 0);
    }

    private static EulerAngle parseEuler(JSONArray arr) {
        if (arr == null || arr.length() != 3) return zero();
        return new EulerAngle(
                Math.toRadians(arr.optDouble(0, 0)),
                Math.toRadians(arr.optDouble(1, 0)),
                Math.toRadians(arr.optDouble(2, 0))
        );
    }

    private static Location parseLocation(JSONObject params, Player player) {
        if (params == null) return null;
        Location base = player.getLocation();
        Object locObj = params.opt("location");

        if (locObj instanceof JSONObject) {
            JSONObject locJson = (JSONObject) locObj;
            World world = Bukkit.getWorld(locJson.optString("world", "world"));
            if (world == null) world = player.getWorld();
            return new Location(
                    world,
                    locJson.optDouble("x", base.getX()),
                    locJson.optDouble("y", base.getY()),
                    locJson.optDouble("z", base.getZ()),
                    (float) locJson.optDouble("yaw", base.getYaw()),
                    (float) locJson.optDouble("pitch", base.getPitch())
            );
        }
        return base;
    }

    public static String getUserSetInstructions(ArmorStand armorStand) {
        String path = ArmorStandUtils.getNameByArmorStand(armorStand) + ".custom-instructions";
        return AiConfig.get().getString(path);
    }

    public static void setUserSetInstructions(ArmorStand armorStand, String value) {
        String path = ArmorStandUtils.getNameByArmorStand(armorStand) + ".custom-instructions";
        AiConfig.get().set(path, value);
        AiConfig.save();
    }

    public static void sendResponseWithHistory(Player player, String response, String armorStandName, String userInput) {
        sendResponse(player, response);
        Bukkit.getPluginManager().callEvent(new ArmorStandAiRespondEvent(ArmorStandUtils.getArmorStandByName(armorStandName), response, userInput, player));
        addToHistory(player.getName(), armorStandName, AiRole.PLAYER, userInput);
        addToHistory(player.getName(), armorStandName, AiRole.AI, response);
    }

    public static void sendResponse(Player player, String response) {
        player.sendMessage(Language.getMsg(Messages.AI_PREFIX) + response);
    }

    public static void addToHistory(String playerName, String armorStandName, AiRole role, String content) {
        YamlConfiguration config = AiConfig.get();
        String path = playerName + "." + armorStandName + ".conversation";

        List<Map<String, Object>> conversation = new ArrayList<>();
        for (Map<?, ?> entry : config.getMapList(path)) {
            Map<String, Object> map = new HashMap<>();
            for (Map.Entry<?, ?> e : entry.entrySet()) {
                map.put(String.valueOf(e.getKey()), e.getValue());
            }
            conversation.add(map);
        }

        Map<String, Object> newEntry = new HashMap<>();
        newEntry.put("role", role.name().toLowerCase());
        newEntry.put("content", content);
        conversation.add(newEntry);

        config.set(path, conversation);
        AiConfig.save();
    }

    public static String getHistory(String playerName, String armorStandName) {
        List<Map<?, ?>> rawList = AiConfig.get().getMapList(playerName + "." + armorStandName + ".conversation");
        if (rawList.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        for (Map<?, ?> entry : rawList) {
            Object role = entry.get("role");
            Object content = entry.get("content");
            if (role != null && content != null) {
                sb.append(role).append(": ").append(content).append("\n");
            }
        }
        return sb.toString().trim();
    }

    public static void clearHistory(String playerName, String armorStandName) {
        AiConfig.get().set(playerName + "." + armorStandName, null);
        AiConfig.save();
    }

    private static ApiResponse sendApiRequest(String apiKey, String systemPrompt, String userMessage) {
        HttpURLConnection conn = null;
        try {
            JSONObject body = new JSONObject();
            body.put("model", MODEL);
            body.put("messages", new JSONArray()
                    .put(new JSONObject().put("role", "system").put("content", systemPrompt))
                    .put(new JSONObject().put("role", "user").put("content", userMessage)));
            body.put("temperature", TEMPERATURE);
            body.put("max_tokens", MAX_TOKENS);

            conn = (HttpURLConnection) new URL(API_ENDPOINT).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.toString().getBytes(StandardCharsets.UTF_8));
            }

            int code = conn.getResponseCode();
            String responseBody = readStream(code == 200 ? conn.getInputStream() : conn.getErrorStream());
            return new ApiResponse(responseBody, code, null);
        } catch (Exception e) {
            return new ApiResponse(null, -1, e);
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    private static String readStream(InputStream inputStream) throws IOException {
        if (inputStream == null) return "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append('\n');
            return sb.toString();
        }
    }

    private static String parseChatCompletionsResponse(String json) {
        try {
            JSONObject obj = new JSONObject(json);

            JSONArray choices = obj.optJSONArray("choices");
            if (choices != null && !choices.isEmpty()) {
                JSONObject first = choices.getJSONObject(0);
                JSONObject message = first.optJSONObject("message");
                if (message != null && message.has("content")) {
                    String text = extractText(message.get("content"));
                    if (text != null) return text;
                }
                if (first.has("text")) return first.getString("text");
            }

            JSONArray candidates = obj.optJSONArray("candidates");
            if (candidates != null && !candidates.isEmpty()) {
                JSONObject content = candidates.getJSONObject(0).optJSONObject("content");
                if (content != null) {
                    String text = extractText(content);
                    if (text != null) return text;
                }
            }

            String scraped = scrapeFirstText(obj.toString());
            if (scraped != null) return scraped;

            return Language.getMsg(Messages.AI_RESPONSE_NOT_FOUND);

        } catch (Exception e) {
            return Language.getMsg(Messages.AI_PARSE_ERROR).replace("{error}", describe(e));
        }
    }

    private static String extractText(Object content) {
        if (content instanceof String) return (String) content;

        if (content instanceof JSONObject) {
            JSONObject c = (JSONObject) content;
            if (c.has("text")) return c.optString("text", null);
            JSONArray parts = c.optJSONArray("parts");
            if (parts != null && !parts.isEmpty()) {
                JSONObject p0 = parts.optJSONObject(0);
                if (p0 != null && p0.has("text")) return p0.optString("text", null);
            }
        }

        if (content instanceof JSONArray) {
            JSONArray arr = (JSONArray) content;
            if (!arr.isEmpty()) {
                return extractText(arr.get(0));
            }
        }
        return null;
    }

    private static String scrapeFirstText(String raw) {
        int idx = raw.indexOf("\"text\":\"");
        if (idx == -1) return null;

        StringBuilder sb = new StringBuilder();
        boolean esc = false;
        for (int i = idx + 8; i < raw.length(); i++) {
            char c = raw.charAt(i);
            if (esc) {
                switch (c) {
                    case 'n': sb.append('\n'); break;
                    case 'r': sb.append('\r'); break;
                    case 't': sb.append('\t'); break;
                    default: sb.append(c);
                }
                esc = false;
            } else if (c == '\\') {
                esc = true;
            } else if (c == '"') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}