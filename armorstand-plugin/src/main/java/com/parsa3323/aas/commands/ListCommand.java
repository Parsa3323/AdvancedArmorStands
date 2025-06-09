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
import com.parsa3323.aas.api.player.IPlayer;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.player.PlayerManager;
import com.parsa3323.aas.utils.ArmorStandUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

public class ListCommand extends SubCommand {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Shows a list of armor stands";
    }

    @Override
    public String getSyntax() {
        return "/as list";
    }

    @Override
    public void perform(Player player, String[] args) {
        ArrayList<String> armorStandList = ArmorStandUtils.getArmorStandList();

        if (armorStandList.isEmpty()) {
            player.sendMessage("");
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "    ⚠ No Saved ArmorStands Found ⚠");
            player.sendMessage(ChatColor.GRAY + "Use " + ChatColor.YELLOW + "/as save <name>" + ChatColor.GRAY + " to save your first ArmorStand!");
            player.sendMessage("");
            player.playSound(player.getLocation(), XSound.BLOCK_NOTE_BLOCK_BASS.parseSound(), 0.8f, 0.5f);
        } else {
            player.sendMessage("");
            player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "      * Saved ArmorStands *");
            player.sendMessage(ChatColor.YELLOW + "           Found " + ChatColor.WHITE + armorStandList.size() + ChatColor.YELLOW + " saved ArmorStand" + (armorStandList.size() == 1 ? "" : "s"));
            player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            player.sendMessage("");

            int index = 1;
            for (String name : armorStandList) {
                TextComponent indexComponent = new TextComponent(ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + index + ChatColor.DARK_GRAY + "] ");
                TextComponent nameComponent = new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + name);
                nameComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder(ChatColor.YELLOW + "" + ChatColor.BOLD + "ArmorStand: " + ChatColor.WHITE + name)
                                .append("\n" + ChatColor.GRAY + "Click buttons to interact")
                                .create()));

                TextComponent deleteButton = new TextComponent(ChatColor.DARK_RED + " [" + ChatColor.RED + "" + ChatColor.BOLD + "DL" + ChatColor.DARK_RED + "]");
                deleteButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder(ChatColor.RED + "" + ChatColor.BOLD + "* Delete ArmorStand")
                                .append("\n" + ChatColor.GRAY + "This action cannot be undone!")
                                .append("\n" + ChatColor.YELLOW + "Click to delete: " + ChatColor.WHITE + name)
                                .create()));
                deleteButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as delete " + name));

                TextComponent teleportButton = new TextComponent(ChatColor.DARK_AQUA + " [" + ChatColor.AQUA + "" + ChatColor.BOLD + "TP" + ChatColor.DARK_AQUA + "]");
                teleportButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder(ChatColor.AQUA + "" + ChatColor.BOLD + ">> Teleport to ArmorStand")
                                .append("\n" + ChatColor.GRAY + "Instantly travel to this location")
                                .append("\n" + ChatColor.YELLOW + "Destination: " + ChatColor.WHITE + name)
                                .create()));
                teleportButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as teleport " + name));

                player.spigot().sendMessage(indexComponent, nameComponent, teleportButton, deleteButton);
                index++;
            }

            player.sendMessage("");
            player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Tip: Hover over buttons for more information!");
            player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            player.sendMessage("");

            player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.2f);
        }

    }

    @Override
    public java.util.List<String> getTabComplete(Player player, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean isForOps() {
        return true;
    }




}
