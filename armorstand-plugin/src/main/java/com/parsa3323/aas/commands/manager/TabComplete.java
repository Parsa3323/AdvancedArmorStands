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

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {

    private CommandManager mainCommand;

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        mainCommand = new CommandManager();

        if (strings.length > 1) {
            Player player = (Player) commandSender;

            for (int i = 0; i < mainCommand.getSubCommands().size(); i++) {
                if (strings[0].equalsIgnoreCase(mainCommand.getSubCommands().get(i).getName()) || strings[0].equalsIgnoreCase(mainCommand.getSubCommands().get(i).getAlias())) {
                    List<String> subTab = mainCommand.getSubCommands().get(i).getTabComplete(player, strings);

                    if (subTab == null || subTab.isEmpty()) return new ArrayList<>();

                    String currentArg = strings[strings.length - 1].toLowerCase();

                    if (currentArg.isEmpty()) return subTab;

                    List<String> filtered = new ArrayList<>();

                    for (String suggestion : subTab) {
                        if (suggestion.toLowerCase().startsWith(currentArg)) {
                            filtered.add(suggestion);
                        }
                    }

                    if (filtered.size() == 1) return filtered;

                    return new ArrayList<>();
                }
            }
        } else if (strings.length == 1) {
            ArrayList<String> list = new ArrayList<>();
            String currentInput = strings[0].toLowerCase();


            for (int i = 0; i < mainCommand.getSubCommands().size(); i++) {
                String subName = mainCommand.getSubCommands().get(i).getName().toLowerCase();

                if (subName.startsWith(currentInput)) {
                    if (mainCommand.getSubCommands().get(i).isForOps()) {
                        if (commandSender.hasPermission("advanced-armorstands.admin")) {
                            list.add(mainCommand.getSubCommands().get(i).getName());
                        }
                    } else {
                        list.add(mainCommand.getSubCommands().get(i).getName());
                    }
                }
            }


            if ("help".startsWith(currentInput)) {
                list.add("help");
            }


            if (currentInput.isEmpty()) {
                for (int i = 0; i < mainCommand.getSubCommands().size(); i++) {
                    if (mainCommand.getSubCommands().get(i).isForOps()) {
                        if (commandSender.hasPermission("advanced-armorstands.admin")) {
                            list.add(mainCommand.getSubCommands().get(i).getName());
                        }
                    } else {
                        list.add(mainCommand.getSubCommands().get(i).getName());
                    }
                }
                list.add("help");
                return list;
            }

            if (list.size() == 1) return list;
            return list;
        }

        return new ArrayList<>();
    }

}
