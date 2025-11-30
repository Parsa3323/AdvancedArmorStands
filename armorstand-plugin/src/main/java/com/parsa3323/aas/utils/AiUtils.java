package com.parsa3323.aas.utils;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.actions.AiRole;
import com.parsa3323.aas.api.data.MemoryData;
import com.parsa3323.aas.api.events.ArmorStandAiRespondEvent;
import com.parsa3323.aas.config.AiConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
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

public class AiUtils {


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

    public static void getResponseAsync(String apiKey, MemoryData data, String userInput, java.util.function.Consumer<String> callback) {
        new BukkitRunnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                String result;
                try {
                    String instructions = data.getInstructionsData() == null ? "" : data.getInstructionsData();
                    String history = data.getHistoryData() == null ? "" : data.getHistoryData();
                    String user = userInput == null ? "" : userInput;

                    String userContent = (history.isEmpty() ? "" : (history + "\n")) + user;

                    JSONObject body = new JSONObject();
                    body.put("model", "gemini-2.5-flash");

                    JSONArray messages = new JSONArray();
                    messages.put(new JSONObject().put("role", "system").put("content", instructions));
                    messages.put(new JSONObject().put("role", "user").put("content", userContent));
                    body.put("messages", messages);

                    body.put("temperature", 0.2);
                    body.put("max_tokens", 1024);

                    String endpointUrl = "https://generativelanguage.googleapis.com/v1beta/chat/completions";

                    URL url = new URL(endpointUrl);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Authorization", "Bearer " + apiKey);
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(30000);
                    conn.setReadTimeout(30000);

                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(body.toString().getBytes(StandardCharsets.UTF_8));
                    }

                    int responseCode = conn.getResponseCode();
                    String response = responseCode == 200 ? readStream(conn.getInputStream())
                            : readStream(conn.getErrorStream());

                    if (responseCode != 200) {
                        result = "AI error: HTTP " + responseCode + " - " + response;
                    } else {
                        result = parseChatCompletionsResponse(response);
                    }

                } catch (Exception e) {
                    result = "AI error: " + e.getClass().getSimpleName() + ": " + e.getMessage();
                } finally {
                    if (conn != null) conn.disconnect();
                }

                String finalResult = result;
                Bukkit.getScheduler().runTask(AdvancedArmorStands.plugin, () -> callback.accept(finalResult));
            }
        }.runTaskAsynchronously(AdvancedArmorStands.plugin);
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
        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "»" + ChatColor.GRAY + "] " + ChatColor.GOLD + response);

        Bukkit.getPluginManager().callEvent(new ArmorStandAiRespondEvent(ArmorStandUtils.getArmorStandByName(armorStandName), response, userInput, player));

        AiUtils.addToHistory(player.getName(), armorStandName, AiRole.PLAYER, userInput);
        AiUtils.addToHistory(player.getName(), armorStandName, AiRole.AI, response);
    }

    @Deprecated
    public static String getResponse(String apiKey, MemoryData data, String userInput) {
        HttpURLConnection conn = null;
        try {
            String instructions = data.getInstructionsData() == null ? "" : data.getInstructionsData();
            String history = data.getHistoryData() == null ? "" : data.getHistoryData();
            String user = userInput == null ? "" : userInput;

            String userContent = (history.isEmpty() ? "" : (history + "\n")) + user;

            JSONObject body = new JSONObject();
            body.put("model", "gemini-2.5-flash");

            JSONArray messages = new JSONArray();
            messages.put(new JSONObject()
                    .put("role", "system")
                    .put("content", instructions));
            messages.put(new JSONObject()
                    .put("role", "user")
                    .put("content", userContent));
            body.put("messages", messages);

            body.put("temperature", 0.2);
            body.put("max_tokens", 1024);

            String endpointUrl = "https://generativelanguage.googleapis.com/v1beta/chat/completions";

            URL url = new URL(endpointUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setDoOutput(true);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.toString().getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            String response = responseCode == 200 ? readStream(conn.getInputStream())
                    : readStream(conn.getErrorStream());

            if (responseCode != 200) {
                return "AI error: HTTP " + responseCode + " - " + response;
            }

            return parseChatCompletionsResponse(response);

        } catch (Exception e) {
            return "AI error: " + e.getClass().getSimpleName() + ": " + e.getMessage();
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    private static String readStream(InputStream inputStream) throws IOException {
        if (inputStream == null) return "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();
        return sb.toString();
    }

    public static void addToHistory(String playerName, String armorStandName, AiRole role, String content) {
        YamlConfiguration config = AiConfig.get();
        String path = playerName + "." + armorStandName + ".conversation";

        List<Map<?, ?>> rawList = config.getMapList(path);
        List<Map<String, Object>> conversation = new ArrayList<>();

        if (rawList != null) {
            for (Map<?, ?> entry : rawList) {
                Map<String, Object> map = new HashMap<>();
                for (Map.Entry<?, ?> e : entry.entrySet()) {
                    map.put(e.getKey().toString(), e.getValue());
                }
                conversation.add(map);
            }
        }

        Map<String, Object> newEntry = new HashMap<>();
        newEntry.put("role", role.name().toLowerCase());
        newEntry.put("content", content);

        conversation.add(newEntry);
        config.set(path, conversation);
        AiConfig.save();
    }

    public static String getHistory(String playerName, String armorStandName) {
        YamlConfiguration config = AiConfig.get();
        String path = playerName + "." + armorStandName + ".conversation";

        List<Map<?, ?>> rawList = config.getMapList(path);
        if (rawList == null || rawList.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        for (Map<?, ?> entry : rawList) {
            Object roleObj = entry.get("role");
            Object contentObj = entry.get("content");
            if (roleObj != null && contentObj != null) {
                sb.append(roleObj.toString()).append(": ").append(contentObj.toString()).append("\n");
            }
        }

        return sb.toString().trim();
    }



    public static void clearHistory(String playerName, String armorStandName) {
        YamlConfiguration config = AiConfig.get();
        String path = playerName + "." + armorStandName;
        config.set(path, null);
        AiConfig.save();
    }

    private static String parseChatCompletionsResponse(String json) {
        try {
            JSONObject obj = new JSONObject(json);

            if (obj.has("choices")) {
                JSONArray choices = obj.getJSONArray("choices");
                if (choices.length() > 0) {
                    JSONObject first = choices.getJSONObject(0);
                    if (first.has("message")) {
                        JSONObject message = first.getJSONObject("message");
                        if (message.has("content")) {
                            Object contentObj = message.get("content");
                            if (contentObj instanceof String) {
                                return (String) contentObj;
                            }
                            if (contentObj instanceof JSONObject) {
                                JSONObject c = (JSONObject) contentObj;
                                if (c.has("text")) return c.getString("text");
                                if (c.has("parts")) {
                                    JSONArray parts = c.getJSONArray("parts");
                                    if (parts.length() > 0) {
                                        JSONObject p0 = parts.getJSONObject(0);
                                        if (p0.has("text")) return p0.getString("text");
                                    }
                                }
                            }
                            if (contentObj instanceof JSONArray) {
                                JSONArray carr = (JSONArray) contentObj;
                                if (carr.length() > 0) {
                                    Object firstContent = carr.get(0);
                                    if (firstContent instanceof String) return (String) firstContent;
                                    if (firstContent instanceof JSONObject) {
                                        JSONObject fc = (JSONObject) firstContent;
                                        if (fc.has("text")) return fc.getString("text");
                                    }
                                }
                            }
                        }
                    }
                    if (first.has("text")) return first.getString("text");
                }
            }

            if (obj.has("candidates")) {
                JSONArray candidates = obj.getJSONArray("candidates");
                if (candidates.length() > 0) {
                    JSONObject content = candidates.getJSONObject(0).optJSONObject("content");
                    if (content != null && content.has("parts")) {
                        JSONArray parts = content.getJSONArray("parts");
                        if (parts.length() > 0) return parts.getJSONObject(0).getString("text");
                    }
                }
            }

            String asString = obj.toString();
            int idx = asString.indexOf("\"text\":\"");
            if (idx != -1) {
                idx += 8;
                StringBuilder sb = new StringBuilder();
                boolean esc = false;
                for (int i = idx; i < asString.length(); i++) {
                    char c = asString.charAt(i);
                    if (esc) {
                        if (c == 'n') sb.append('\n');
                        else if (c == 'r') sb.append('\r');
                        else if (c == 't') sb.append('\t');
                        else sb.append(c);
                        esc = false;
                    } else if (c == '\\') {
                        esc = true;
                    } else if (c == '"') {
                        break;
                    } else sb.append(c);
                }
                return sb.toString();
            }

            return "AI error: couldn't find assistant text in response";

        } catch (Exception e) {
            return "AI parse error: " + e.getClass().getSimpleName() + ": " + e.getMessage();
        }
    }
}
