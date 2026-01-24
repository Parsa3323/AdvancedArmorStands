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
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.CommandUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AnimationCommand extends SubCommand {

    public ArrayList<SubCommand> animationSubCommands = new ArrayList<>();

    public AnimationCommand() {
        animationSubCommands.add(new AnimAddCommand());
        animationSubCommands.add(new AnimCreateCommand());
        animationSubCommands.add(new AnimClearCommand());
        animationSubCommands.add(new AnimRemoveCommand());
    }

    @Override
    public ArrayList<String> getExampleLore() {
        return null;
    }

    @Override
    public String getName() {
        return "animation";
    }

    @Override
    public String getDescription() {
        return "Show animation commands";
    }

    @Override
    public String getSyntax() {
        return "/as animation";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("");
            player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            player.sendMessage("     " + ColorUtils.boldAndColor(ChatColor.GOLD) + "Advanced " + ColorUtils.boldAndColor(ChatColor.YELLOW) + "ArmorStands " + ColorUtils.boldAndColor(ChatColor.GRAY) + "animation commands");
            player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            player.sendMessage("");
            for (SubCommand cmd : animationSubCommands) {
                String command = cmd.getSyntax();
                String description = cmd.getDescription();
                String prefix = ChatColor.GOLD + " » ";
                String commandColor = ChatColor.YELLOW + "" + ChatColor.BOLD;
                String sep = ChatColor.DARK_GRAY + " - ";
                String descColor = ChatColor.GRAY.toString();

                String fullMessage = prefix + commandColor + command + sep + descColor + description;

                int lineCharLimit = 50;
                List<String> wrappedLines = new ArrayList<>();

                int index = 0;
                while (index < fullMessage.length()) {
                    int end = Math.min(index + lineCharLimit, fullMessage.length());

                    if (end < fullMessage.length() && fullMessage.charAt(end) != ' ') {
                        int spaceBack = fullMessage.lastIndexOf(' ', end);
                        if (spaceBack > index) end = spaceBack;
                    }

                    String line = fullMessage.substring(index, end).trim();
                    wrappedLines.add(line);
                    index = end;
                }

                for (String line : wrappedLines) {
                    TextComponent component = new TextComponent(line);
                    component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));

                    ArrayList<String> exampleLore = cmd.getExampleLore();

                    ComponentBuilder hoverBuilder = new ComponentBuilder("");

                    if (exampleLore != null && !exampleLore.isEmpty()) {
                        hoverBuilder.append(ChatColor.GRAY + "Examples:")
                                .append("\n");
                        for (String example : exampleLore) {
                            hoverBuilder.append(ChatColor.YELLOW + "  " + example)
                                    .append("\n");
                        }
                        hoverBuilder.append("\n");
                    }

                    hoverBuilder
                            .append(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Click to use this command")
                            .append("\n" + ChatColor.GRAY + "Command: " + ChatColor.YELLOW + command)
                            .append("\n" + ChatColor.GRAY + "Description: " + ChatColor.WHITE + description);

                    component.setHoverEvent(
                            new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverBuilder.create())
                    );

                    player.spigot().sendMessage(component);
                }



            }
            player.sendMessage(" ");
            player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.2f);
        } else {

            int count = 0;
            for (SubCommand cmd : animationSubCommands) {
                if (args[1].equalsIgnoreCase(cmd.getName())) {
                    count++;
                    if (cmd.isForOps()) {
                        if (player.hasPermission("advanced-armorstands.admin")) {
                            cmd.perform(player, args);
                        } else {
                            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Access Denied: " + ChatColor.RED + "You don't have permission to use this command!");
                        }
                    } else {
                        cmd.perform(player, args);
                    }
                }

            }

            if (count == 0) {
                String suggestion = CommandUtils.getClosestCommand(args[1], animationSubCommands);

                if (suggestion != null) {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Unknown Command: " + ChatColor.RED + "' is not a valid subcommand" + args[0] + "'. Did you mean '" + suggestion + "'?");
                } else {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Unknown Command: " + ChatColor.RED + "'" + args[0] + "' is not a valid subcommand.");
                }

            }

        }

    }


    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        if (args.length > 2) {

            for (SubCommand cmd : animationSubCommands) {

                if (args[1].equalsIgnoreCase(cmd.getName())) {

                    List<String> subTab = cmd.getTabComplete(player, args);

                    if (subTab == null || subTab.isEmpty()) return new ArrayList<>();

                    return subTab;

                }

            }

        } else {
            ArrayList<String> list = new ArrayList<>();
            String currentInput = args[1].toLowerCase();

            for (SubCommand cmd : animationSubCommands) {
                String subName = cmd.getName();

                if (cmd.isForOps()) {
                    if (player.hasPermission("advanced-armorstands.admin")) {
                        list.add(cmd.getName());
                    }
                } else {
                    list.add(cmd.getName());
                }
            }

            return list;

        }
        return new ArrayList<>();
    }

    @Override
    public boolean isForOps() {
        return true;
    }

    @Override
    public String getAlias() {
        return "anim";
    }
}