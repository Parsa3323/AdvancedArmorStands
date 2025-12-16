/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
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

package com.parsa3323.aas.commands;

import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.actions.IssueLevel;
import com.parsa3323.aas.api.data.IssueData;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.IssueUtils;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DebugCommand extends SubCommand {
    @Override
    public String getName() {
        return "debug";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.DARK_GRAY + "This command is only for debugging");
        return lore;    }

    @Override
    public String getDescription() {
        return "Shows debug information";
    }

    @Override
    public String getSyntax() {
        return "/as debug";
    }

    @Override
    public void perform(Player player, String[] args) {
        String serverVersion = Bukkit.getVersion();
        String bukkitVersion = Bukkit.getBukkitVersion();
        String pluginVersion = AdvancedArmorStands.plugin.getDescription().getVersion();
        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();
        long usedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        player.sendMessage("");
        player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        player.sendMessage("     " + ColorUtils.boldAndColor(ChatColor.GOLD) + "Advanced " + ColorUtils.boldAndColor(ChatColor.YELLOW) + "ArmorStands " + ChatColor.DARK_GRAY + "- " + ChatColor.RED + "Debug Info");
        player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        player.sendMessage("");

        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + " Server Information");
        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Server Version: " + ChatColor.WHITE + serverVersion);
        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Bukkit Version: " + ChatColor.WHITE + bukkitVersion);
        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Players Online: " + ChatColor.WHITE + onlinePlayers + ChatColor.GRAY + "/" + ChatColor.WHITE + maxPlayers);
        player.sendMessage("");

        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + " Plugin Information");
        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Version Support: " + ChatColor.WHITE + VersionSupportUtil.getVersionSupport().getClass().getSimpleName());
        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Plugin Version: " + ChatColor.WHITE + pluginVersion);
        player.sendMessage(
                ChatColor.GOLD + " » " +
                        ChatColor.GRAY + "Issues: " +
                        ChatColor.RED + IssueUtils.getTotalIssues() + " issue(s) " +
                        ChatColor.GRAY + "and " +
                        ChatColor.YELLOW + IssueUtils.getTotalWarnings() + " warning(s)"
        );

        if (IssueUtils.hasIssues()) {
            int shown = 0;
            int limit = 3;

            for (IssueData issue : IssueUtils.topIssues(limit)) {
                if (issue.level != IssueLevel.ERROR) continue;

                player.sendMessage(
                        ChatColor.GOLD + " » " +
                                ChatColor.RED + issue.message +
                                ChatColor.DARK_GRAY + " (x" + issue.occurrences + ")"
                );
                shown++;
                if (shown >= limit) break;
            }

            int remaining = IssueUtils.getTotalIssues() - shown;
            if (remaining > 0) {
                player.sendMessage(
                        ChatColor.GOLD + " » " +
                                ChatColor.GRAY + "... and " + remaining + " more error(s)"
                );
            }
        }

        if (IssueUtils.hasWarnings()) {
            int shown = 0;
            int limit = 3;

            for (IssueData issue : IssueUtils.topIssues(Integer.MAX_VALUE)) {
                if (issue.level != IssueLevel.WARNING) continue;

                player.sendMessage(
                        ChatColor.GOLD + " » " +
                                ChatColor.YELLOW + issue.message +
                                ChatColor.DARK_GRAY + " (x" + issue.occurrences + ")"
                );
                shown++;
                if (shown >= limit) break;
            }

            int remaining = IssueUtils.getTotalWarnings() - shown;
            if (remaining > 0) {
                player.sendMessage(
                        ChatColor.GOLD + " » " +
                                ChatColor.GRAY + "... and " + remaining + " more warning(s)"
                );
            }
        }



        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Java Version: " + ChatColor.WHITE + System.getProperty("java.version"));
        player.sendMessage("");

        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + " Memory Information");
        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Used Memory: " + ChatColor.WHITE + usedMemory + "MB");
        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Max Memory: " + ChatColor.WHITE + maxMemory + "MB");
        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Free Memory: " + ChatColor.WHITE + (maxMemory - usedMemory) + "MB");
        player.sendMessage("");

        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + " ArmorStands Debug");
        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Total ArmorStands: " + ChatColor.WHITE + ArmorStandUtils.getTotalArmorStands());
        player.sendMessage(ChatColor.GOLD + " » " + ChatColor.GRAY + "Loaded ArmorStands: " + ChatColor.WHITE + ArmorStandUtils.getLoadedArmorStands());
        player.sendMessage("");

        player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "This information can help with troubleshooting issues.");
        player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        player.sendMessage("");

        player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.2f);
    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
