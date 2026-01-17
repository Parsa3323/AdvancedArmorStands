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

package com.parsa3323.aas.commands.manager;

import com.parsa3323.aas.utils.ArmorStandUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {
    public abstract String getName();

    public abstract ArrayList<String> getExampleLore();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void perform(Player player, String args[]);

    public abstract List<String> getTabComplete(Player player, String args[]);

    public abstract boolean isForOps();

    public void sendUsage(Player player) {
        TextComponent textComponent = new TextComponent(ChatColor.RED + "Usage: ");
        TextComponent main = new TextComponent(ChatColor.RED + getSyntax());
        main.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, getSyntax()));
        main.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + "Click to suggest").create()));
        player.spigot().sendMessage(textComponent, main);
    }

    public String getClosest(String input, ArrayList<String> options) {
        int minDistance = Integer.MAX_VALUE;
        String closest = null;

        for (String option : options) {
            int distance = levenshteinDistance(input.toLowerCase(), option.toLowerCase());
            if (distance < minDistance) {
                minDistance = distance;
                closest = option;
            }
        }

        return (minDistance <= 3) ? closest : null;
    }

    private static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1])
                    );
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    public ArmorStand checkArmorStandAndNotify(Player player, String input) {
        ArmorStand as = ArmorStandUtils.getArmorStandByName(input);
        if (as == null) {
            if (ArmorStandUtils.getArmorStandList().contains(input)) {
                player.sendMessage(ChatColor.RED + "This armor stand is not loaded");
                return null;
            }
            String suggestion = getClosest(input, ArmorStandUtils.getArmorStandList());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "Invalid armor stand '" + input + "'. Did you mean '" + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "Invalid armor stand");
            }
            return null;
        }
        return as;
    }

}
