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
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class SettingsCommand extends SubCommand {
    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public String getDescription() {
        return "Opens the settings menu";
    }

    @Override
    public String getSyntax() {
        return "/as settings <name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length < 2) {
            sendUsage(player);
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

        ArmorStandMenu armorStandMenu = new ArmorStandMenu(new PlayerMenuUtility(player), ArmorStandUtils.getArmorStandByName(args[1]));
        ArmorStandSelectionCache.setSelectedArmorStand(player.getUniqueId(), ArmorStandUtils.getArmorStandByName(args[1]));
        armorStandMenu.open();;
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
