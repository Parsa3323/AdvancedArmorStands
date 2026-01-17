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

package com.parsa3323.aas.commands;

import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.ColorUtils;
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
    public ArrayList<String> getExampleLore() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Shows a list of ArmorStands";
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
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "     No Saved ArmorStands Found ");
            player.sendMessage(ChatColor.GRAY + "Use " + ChatColor.YELLOW + "/as create <type> <name>" + ChatColor.GRAY + " to create your first ArmorStand!");
            player.sendMessage("");
            player.playSound(player.getLocation(), XSound.BLOCK_NOTE_BLOCK_BASS.parseSound(), 0.8f, 0.5f);
        } else {
            player.sendMessage("");
            player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "         * Saved ArmorStands *");
            player.sendMessage(ChatColor.YELLOW + "           Found " + ColorUtils.boldAndColor(ChatColor.YELLOW) + armorStandList.size() + ChatColor.RESET + ChatColor.YELLOW + " saved ArmorStand" + (armorStandList.size() == 1 ? "" : "s"));

            TextComponent deleteAll = new TextComponent(ChatColor.DARK_RED + " [" + ColorUtils.boldAndColor(ChatColor.RED) + "DL All" + ChatColor.DARK_RED + "]");
            TextComponent loadAll = new TextComponent(ChatColor.DARK_GREEN + " [" + ColorUtils.boldAndColor(ChatColor.GREEN) + "LD All" + ChatColor.DARK_GREEN + "]");

            deleteAll.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(ChatColor.RED + "" + ChatColor.BOLD + "Delete All ArmorStands")
                            .append("\n" + ChatColor.GRAY + "This action cannot be undone!")
                            .append("\n" + " ")
                            .append("\n" + ChatColor.YELLOW + "Click to delete all ArmorStands ")
                            .create()));
            deleteAll.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as delete --all"));

            loadAll.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(ChatColor.GREEN + "" + ChatColor.BOLD + "Load AmorStands")
                            .append("\n" + ChatColor.GRAY + "Load all ArmorStands")
                            .append("\n" + " ")
                            .append("\n" + ChatColor.YELLOW + "Click to load all ArmorStands")
                            .create()));
            loadAll.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as load --all"));

            if (!ArmorStandUtils.getArmorStandList().isEmpty()){
                if (ArmorStandUtils.getLoadedArmorStands() < ArmorStandUtils.getTotalArmorStands()) {
                    player.spigot().sendMessage(new TextComponent("              "), deleteAll, loadAll);
                } else {
                    player.spigot().sendMessage(new TextComponent("                     "), deleteAll);
                }

            }

            player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            player.sendMessage("");





            int index = 1;
            for (String name : armorStandList) {
                TextComponent indexComponent = new TextComponent(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + index + ChatColor.DARK_GRAY + "] ");
                TextComponent nameComponent = new TextComponent((ArmorStandUtils.isLoaded(ArmorStandUtils.getArmorStandByName(name)) ? ChatColor.GREEN : ChatColor.RED) + "" + ChatColor.BOLD + name);
                nameComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder(ChatColor.YELLOW  + "ArmorStand: " + name)
                                .append("\n" + ChatColor.GRAY + "Click buttons to interact")
                                .append(!ArmorStandUtils.isLoaded(ArmorStandUtils.getArmorStandByName(name))
                                        ? "\n" + ChatColor.RED + "This ArmorStand is not loaded, enable auto load in config"
                                        : "")
                                .create()));

                TextComponent deleteButton = new TextComponent(ChatColor.DARK_RED + " [" + ChatColor.RED + ChatColor.BOLD + "DL" + ChatColor.DARK_RED + "]");
                deleteButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder(ChatColor.RED + "" + ChatColor.BOLD + "Delete ArmorStand")
                                .append("\n" + ChatColor.GRAY + "This action cannot be undone!")
                                .append("\n" + " ")
                                .append("\n" + ChatColor.YELLOW + "Click to delete: " + name)
                                .create()));
                deleteButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as delete " + name));

                TextComponent teleportButton = new TextComponent(ChatColor.DARK_AQUA + " [" + ChatColor.AQUA + ChatColor.BOLD + "TP" + ChatColor.DARK_AQUA + "]");
                teleportButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder(ChatColor.AQUA + "" + ChatColor.BOLD + "Teleport to ArmorStand")
                                .append("\n" + ChatColor.GRAY + "Instantly travel to this location")
                                .append("\n" + " ")
                                .append("\n" + ChatColor.YELLOW + "Destination: " + name)
                                .create()));
                teleportButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as teleport " + name));

                TextComponent loadButton = new TextComponent(ChatColor.DARK_GREEN + " [" + ChatColor.GREEN + ChatColor.BOLD + "LD" + ChatColor.DARK_GREEN + "]");
                loadButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ComponentBuilder(ChatColor.GREEN + "" + ChatColor.BOLD + "Load AmorStand")
                                .append("\n" + ChatColor.GRAY + "Load this ArmorStand")
                                .append("\n" + " ")
                                .append("\n" + ChatColor.YELLOW + "Click to load: " + name)
                                .create()));
                loadButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as load " + name));

                if (ArmorStandUtils.isLoaded(ArmorStandUtils.getArmorStandByName(name))) {
                    player.spigot().sendMessage(indexComponent, nameComponent, teleportButton, deleteButton);
                } else {
                    player.spigot().sendMessage(indexComponent, nameComponent, deleteButton, loadButton);
                }
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
