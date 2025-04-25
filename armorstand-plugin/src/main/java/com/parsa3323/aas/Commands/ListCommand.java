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

package com.parsa3323.aas.Commands;

import com.parsa3323.aas.Commands.Manager.SubCommand;
import com.parsa3323.aas.Player.IPlayer;
import com.parsa3323.aas.Player.PlayerManager;
import com.parsa3323.aas.Utils.ArmorStandUtils;
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

        IPlayer player1 = PlayerManager.getCustomPlayerByBukkit(player);
        player1.playSound("NOTE_BASS");

        if (armorStandList.isEmpty()) {
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "✖ No ArmorStands found! ✖");
            player1.playSound("VILLAGER_NO");
        } else {
            player.sendMessage(ChatColor.DARK_GRAY + "§m----------------------------------");
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  ✦ Saved ArmorStands ✦");
            player.sendMessage(ChatColor.DARK_GRAY + "§m----------------------------------");

            for (String name : armorStandList) {

                TextComponent textComponent = new TextComponent(ChatColor.DARK_GREEN + "» " + ChatColor.GREEN + name);
                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + name).create()));
                TextComponent deleteButton = new TextComponent(ChatColor.RED + "" + ChatColor.BOLD + " [DELETE] ");
                deleteButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Delete armorstand").create()));
                deleteButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as delete " + name));
                TextComponent teleportButton = new TextComponent(ChatColor.AQUA + "" + ChatColor.BOLD + "[TELEPORT]");
                teleportButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + "Teleport to armorstand").create()));
                teleportButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as teleport " + name));

                player.spigot().sendMessage(textComponent, deleteButton, teleportButton);
            }

            player.sendMessage(ChatColor.DARK_GRAY + "§m----------------------------------");
            player1.playSound("ORB_PICKUP");
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
