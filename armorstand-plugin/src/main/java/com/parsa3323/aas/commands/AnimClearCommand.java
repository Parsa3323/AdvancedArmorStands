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
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.utils.ArmorStandUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AnimClearCommand extends SubCommand {
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Clear animations of an as";
    }

    @Override
    public String getSyntax() {
        return "/as animation clear <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 3) {
            sendUsage(player);
            return;
        }

        ConfigurationSection configurationSection = ArmorStandsConfig.get().getConfigurationSection("armorstands");

        ArmorStand as = checkArmorStandAndNotify(player, args[2]);

        if (as == null) {
            return;
        }

        if (configurationSection == null) {
            String suggestion = getClosest(args[2], ArmorStandUtils.getArmorStandList());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "Invalid armor stand '" + args[2] + "'. Did you mean '" + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "Invalid armor stand");
            }
            return;
        }

        configurationSection.set(args[2] + ".animation", null);
        ArmorStandsConfig.save();
        player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1, 1);
        player.sendMessage(ChatColor.GREEN + "Successfully cleared " + args[2] + "'s animations");
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
