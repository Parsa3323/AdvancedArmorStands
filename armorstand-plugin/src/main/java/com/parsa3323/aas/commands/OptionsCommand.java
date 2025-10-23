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
import com.parsa3323.aas.options.manager.SettingsManager;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class OptionsCommand extends SubCommand {
    @Override
    public String getName() {
        return "options";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Opens the options menu";
    }

    @Override
    public String getSyntax() {
        return "/as options <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) {
            sendUsage(player);
            return;
        }

        ArmorStand armorStand = ArmorStandUtils.getArmorStandByName(args[1]);

        if (null == armorStand) {
            String suggestion = getClosest(args[1], ArmorStandUtils.getArmorStandList());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "Invalid armor stand '" + args[1] + "'. Did you mean '" + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "Invalid armor stand");
            }
            return;
        }
        new SettingsManager(new PlayerMenuUtility(player), armorStand, false).open();

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
