/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.Q
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.parsa.aas.Utils;


import me.parsa.aas.AdvancedArmorStands;
import me.parsa.aas.Player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateChecker {
    private final JavaPlugin plugin;
    private final String repoOwner;
    private final String repoName;

    public UpdateChecker(JavaPlugin plugin, String repoOwner, String repoName) {
        this.plugin = plugin;
        this.repoOwner = repoOwner;
        this.repoName = repoName;
    }

    public void checkForUpdates() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                URL url = new URL("https://api.github.com/repos/" + repoOwner + "/" + repoName + "/tags");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "application/vnd.github.v3+json");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder json = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                reader.close();

                Pattern tagPattern = Pattern.compile("\"name\"\\s*:\\s*\"(v[^\"]+)\"");
                Matcher matcher = tagPattern.matcher(json.toString());

                String currentVersion = plugin.getDescription().getVersion().replace("v", "");
                String latestVersion = currentVersion;

                while (matcher.find()) {
                    String tag = matcher.group(1).replace("v", "");
                    if (compareVersions(tag, latestVersion) > 0) {
                        latestVersion = tag;
                    }
                }

                if (!latestVersion.equalsIgnoreCase(currentVersion)) {
                    String finalLatest = latestVersion;
                    Bukkit.getScheduler().runTask(plugin, () -> {
                        AdvancedArmorStands.warn("New version available: " + finalLatest + " (You are on " + currentVersion + ")");
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (PlayerManager.getCustomPlayerByBukkit(player).isAdmin()) {
                                player.sendMessage(ChatColor.YELLOW + "[AdvancedArmorStands] Update available: " + finalLatest);
                            }
                        }
                    });
                }
            } catch (Exception e) {
                AdvancedArmorStands.warn("Failed to check for updates: " + e.getMessage());
            }
        });
    }


    private int compareVersions(String v1, String v2) {
        String[] a = v1.split("[-+]");
        String[] b = v2.split("[-+]");
        String[] partsA = a[0].split("\\.");
        String[] partsB = b[0].split("\\.");

        for (int i = 0; i < Math.max(partsA.length, partsB.length); i++) {
            int n1 = i < partsA.length ? Integer.parseInt(partsA[i]) : 0;
            int n2 = i < partsB.length ? Integer.parseInt(partsB[i]) : 0;
            if (n1 != n2) return Integer.compare(n1, n2);
        }

        if (a.length == 2 && b.length == 1) return -1;
        if (a.length == 1 && b.length == 2) return 1;
        if (a.length == 2 && b.length == 2) {
            return a[1].compareTo(b[1]);
        }

        return 0;
    }



}
