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

import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.utils.ArmorStandUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class DeleteCommand extends SubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Delete an armor stand";
    }


    @Override
    public String getSyntax() {
        return "/as delete <name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length > 2) {
            return;
        }

        if (!ArmorStandUtils.exists(args[1])) {
            String suggestion = getClosest(args[1], ArmorStandUtils.getArmorStandList());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "Invalid armor stand '" + args[1] + "'. Did you mean '" + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "Invalid armor stand");
            }
            return;
        }

        ArmorStandUtils.deleteArmorStand(args[1], player);

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        return ArmorStandUtils.getArmorStandList();

    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
