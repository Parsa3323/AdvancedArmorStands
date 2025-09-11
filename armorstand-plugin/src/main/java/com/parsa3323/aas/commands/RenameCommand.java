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
import com.parsa3323.aas.api.events.ArmorStandRenameEvent;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.utils.ArmorStandUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class RenameCommand extends SubCommand {
    @Override
    public String getName() {
        return "rename";
    }

    @Override
    public String getDescription() {
        return "Rename an as's name";
    }

    @Override
    public String getSyntax() {
        return "/as rename <name> <new name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 3) {
            sendUsage(player);
            return;
        }

        String oldName = args[1];
        String newName = String.join("_", java.util.Arrays.copyOfRange(args, 2, args.length));

        if (!ArmorStandsConfig.get().contains("armorstands." + oldName)) {
            String suggestion = getClosest(args[1], ArmorStandUtils.getArmorStandList());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "Invalid armor stand '" + args[1] + "'. Did you mean '" + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "Invalid armor stand");
            }
            return;
        }

        ArmorStandRenameEvent armorStandRenameEvent = new ArmorStandRenameEvent(player, ArmorStandUtils.getArmorStandByName(newName));
        Bukkit.getPluginManager().callEvent(armorStandRenameEvent);
        if (!armorStandRenameEvent.isCancelled()) {
            String path = "armorstands." + oldName;
            String uuid = ArmorStandsConfig.get().getString(path + ".UUID");
            String world = ArmorStandsConfig.get().getString(path + ".World");
            double x = ArmorStandsConfig.get().getDouble(path + ".X");
            double y = ArmorStandsConfig.get().getDouble(path + ".Y");
            double z = ArmorStandsConfig.get().getDouble(path + ".Z");

            ArmorStandsConfig.get().set("armorstands." + newName + ".UUID", uuid);
            ArmorStandsConfig.get().set("armorstands." + newName + ".World", world);
            ArmorStandsConfig.get().set("armorstands." + newName + ".X", x);
            ArmorStandsConfig.get().set("armorstands." + newName + ".Y", y);
            ArmorStandsConfig.get().set("armorstands." + newName + ".Z", z);

            ArmorStandsConfig.get().set("armorstands." + oldName, null);
            ArmorStandsConfig.save();

            player.sendMessage(ChatColor.GREEN + "Renamed armor stand from '" + oldName + "' to '" + newName + "'");
            player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1,  1);
        }

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        if (args.length == 2) {
            return ArmorStandUtils.getArmorStandList();

        } else {
            return null;
        }
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
