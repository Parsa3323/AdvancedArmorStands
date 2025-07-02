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
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.utils.ArmorStandUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AnimAddCommand extends SubCommand {
    @Override
    public String getName() {
        return "addanimation";
    }

    @Override
    public String getDescription() {
        return "Add an animation to as";
    }

    @Override
    public String getSyntax() {
        return "/as addanimation <animation> <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 3) {
            sendUsage(player);
            return;
        }

        ConfigurationSection configurationSection = ArmorStandsConfig.get().getConfigurationSection("armorstands");

        if (configurationSection == null || !configurationSection.contains(args[2])) {
            player.sendMessage(ChatColor.RED + "Invalid armor stand");
            return;
        }

        if (!AnimationConfig.get().contains("animations." + args[1])) {
            player.sendMessage(ChatColor.RED + "Invalid animation");
            return;
        }

        configurationSection.set(args[2] + ".animation", args[1]);
        ArmorStandsConfig.save();
        player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1, 1);
        player.sendMessage(ChatColor.GREEN + "Successfully set the animation " + args[2] + " to armor stand " + args[1]);
    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        if (args.length == 2) {
            return new ArrayList<>(AnimationConfig.get().getConfigurationSection("animations").getKeys(false));

        }

        if (args.length == 3) {
            return ArmorStandUtils.getArmorStandList();
        }
        return null;
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
