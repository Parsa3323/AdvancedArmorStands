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
import com.parsa3323.aas.utils.ArmorStandUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeleportCommand extends SubCommand {
    @Override
    public String getName() {
        return "teleport";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Teleport to an ArmorStand";
    }

    @Override
    public String getSyntax() {
        return "/as teleport <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) {
            sendUsage(player);
            return;
        }

        ArmorStand stand = checkArmorStandAndNotify(player, args[1]);
        if (stand == null) return;

        if (!(stand != null && stand.isOnGround())) {
            if (args.length >= 3 && args[2].equalsIgnoreCase("--force")) {
                ArmorStandUtils.teleportToArmorStand(player, args[1]);
                player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.2f);
                return;
            }
            if (player.getGameMode() != GameMode.CREATIVE) {
                player.sendMessage(ChatColor.RED + "This armor stand is not on the ground. Are you sure you want to teleport to it?");
                player.sendMessage(ChatColor.RED + "Use '/as teleport " + args[1] + " --force' to force teleport");
                return;
            }

        }


        ArmorStandUtils.teleportToArmorStand(player, args[1]);
        player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.2f);

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
