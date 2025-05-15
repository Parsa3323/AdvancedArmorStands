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
import com.parsa3323.aas.configs.ArmorStands;
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
            player.sendMessage(ChatColor.RED + "This armor stand is not available");
            return;
        }
        new SettingsManager(new PlayerMenuUtility(player), armorStand, false).open();

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        return new ArrayList<>(ArmorStands.get().getConfigurationSection("armorstands").getKeys(false));
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
