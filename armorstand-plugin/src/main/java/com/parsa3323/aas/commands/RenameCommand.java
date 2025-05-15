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

import com.parsa3323.aas.api.events.ArmorStandRenameEvent;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.configs.ArmorStands;
import com.parsa3323.aas.player.PlayerManager;
import com.parsa3323.aas.utils.ArmorStandUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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

        String oldname = args[1];
        String newname = args[2];

        if (!ArmorStands.get().contains("armorstands." + oldname)) {
            player.sendMessage(ChatColor.RED + "Armor stand with name '" + oldname + "' not found.");
            return;
        }

        String path = "armorstands." + oldname;
        String uuid = ArmorStands.get().getString(path + ".UUID");
        String world = ArmorStands.get().getString(path + ".World");
        double x = ArmorStands.get().getDouble(path + ".X");
        double y = ArmorStands.get().getDouble(path + ".Y");
        double z = ArmorStands.get().getDouble(path + ".Z");

        ArmorStands.get().set("armorstands." + newname + ".UUID", uuid);
        ArmorStands.get().set("armorstands." + newname + ".World", world);
        ArmorStands.get().set("armorstands." + newname + ".X", x);
        ArmorStands.get().set("armorstands." + newname + ".Y", y);
        ArmorStands.get().set("armorstands." + newname + ".Z", z);

        ArmorStands.get().set("armorstands." + oldname, null);
        ArmorStands.save();

        ArmorStandRenameEvent armorStandRenameEvent = new ArmorStandRenameEvent(player, ArmorStandUtils.getArmorStandByName(newname));
        Bukkit.getPluginManager().callEvent(armorStandRenameEvent);
        if (!armorStandRenameEvent.isCancelled()) {
            player.sendMessage(ChatColor.GREEN + "Renamed armor stand '" + oldname + "' to '" + newname + "'");
            PlayerManager.getCustomPlayerByBukkit(player).playSound("ORB_PICKUP");
        }

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        if (args.length == 2) {
            return new ArrayList<>(ArmorStands.get().getConfigurationSection("armorstands").getKeys(false));
        } else {
            return null;
        }
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
