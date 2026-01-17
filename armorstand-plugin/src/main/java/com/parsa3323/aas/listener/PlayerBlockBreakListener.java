/*
 *
 * Copyright
 * 2026 AdvancedArmorStands, Inc.
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

package com.parsa3323.aas.listener;

import com.parsa3323.aas.ai.MemoryOption;
import com.parsa3323.aas.api.events.PlayerMoveArmorStandEvent;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.tools.MoveTool;
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.ArmorStandUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBlockBreakListener implements Listener {

    private boolean firstTimeBreaking = true;

    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent e) {

        if (MoveTool.moveList.containsKey(e.getPlayer().getUniqueId())) {

            e.setCancelled(true);

            Player player = e.getPlayer();

            Location blockLoc = e.getBlock().getLocation().add(0.5, 1, 0.5);


            ArmorStand as = MoveTool.moveList.get(player.getUniqueId());

            PlayerMoveArmorStandEvent armorStandMoveEvent = new PlayerMoveArmorStandEvent(blockLoc, as, player);
            Bukkit.getPluginManager().callEvent(armorStandMoveEvent);
            if (armorStandMoveEvent.isCancelled()) {
                MoveTool.moveList.remove(player.getUniqueId());
                return;
            }

            String path = "armorstands." + ArmorStandUtils.getNameByArmorStand(as);

            ArmorStandsConfig.get().set(path + ".World", blockLoc.getWorld().getName());
            ArmorStandsConfig.get().set(path + ".X", blockLoc.getX());
            ArmorStandsConfig.get().set(path + ".Y", blockLoc.getY());
            ArmorStandsConfig.get().set(path + ".Z", blockLoc.getZ());
            ArmorStandsConfig.save();

            as.teleport(blockLoc);

            MoveTool.moveList.remove(e.getPlayer().getUniqueId());

            player.sendMessage(ChatColor.GREEN + "Successfully moved the ArmorStand.");
        }

        if (ArmorStandSelectionCache.isIsInEditSession(e.getPlayer())) {
            if (firstTimeBreaking) {
                e.getPlayer().sendMessage(ChatColor.RED + "You cannot break blocks in edit session.");
                firstTimeBreaking = false;
            }

            e.setCancelled(true);

        }

        if (MemoryOption.waiting.containsKey(e.getPlayer().getUniqueId())) {
            if (firstTimeBreaking) {
                e.getPlayer().sendMessage(ChatColor.RED + "You cannot break blocks here.");
                firstTimeBreaking = false;
            }

            e.setCancelled(true);
        }

    }
}
