/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
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

package me.parsa.aas.Commands.Manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabComp implements TabCompleter {

    private CommandManager mainCommand;

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        mainCommand = new CommandManager();
        if (strings.length > 1) {
            Player player = (Player) commandSender;

            for (int i = 0; i < mainCommand.getSubCommands().size(); i++) {
                if (strings[0].equalsIgnoreCase(mainCommand.getSubCommands().get(i).getName())) {
                    List<String> list = mainCommand.getSubCommands().get(i).getTabComplete(player, strings);
                    return list;
                }
            }
        } else if (strings.length == 1) {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < mainCommand.getSubCommands().size(); i++) {
                if (mainCommand.getSubCommands().get(i).isForOps()) {
                    if (commandSender.hasPermission("advanced-armorstands.admin")) {
                        list.add(mainCommand.getSubCommands().get(i).getName());
                    }
                } else {
                    list.add(mainCommand.getSubCommands().get(i).getName());
                }

            }

            return list;
        }


        return null;
    }
}

