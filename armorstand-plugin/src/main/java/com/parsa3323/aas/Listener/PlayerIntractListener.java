/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.Q
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.parsa3323.aas.Listener;


import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.Commands.CreateCommand;
import com.parsa3323.aas.Menus.ArmorStandMenu;
import com.parsa3323.aas.Player.IPlayer;
import com.parsa3323.aas.Player.PlayerManager;
import com.parsa3323.aas.Utils.ArmorStandSelectionCache;
import com.parsa3323.aas.Utils.ArmorStandUtils;
import com.parsa3323.aas.Utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class PlayerIntractListener implements Listener {

    private final Map<UUID, Integer> interactionCount  = new HashMap<>();
    private final Map<UUID, ArmorStand> selectCount = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerInteractAtEntityEvent(PlayerInteractAtEntityEvent e) {
        IPlayer player = PlayerManager.getCustomPlayerByBukkit(e.getPlayer());
        if (e.getRightClicked() instanceof ArmorStand) {
            if (player.isAdmin()) {
                if (ArmorStandUtils.isConfiguredArmorStand(e.getRightClicked())) {
                    if (e.getPlayer().isSneaking()) {
                        ArmorStandMenu armorStandMenu = new ArmorStandMenu(new PlayerMenuUtility(player.getBukkitPlayer()), "", (ArmorStand) e.getRightClicked());
                        ArmorStandSelectionCache.setSelectedArmorStand(player.getBukkitPlayer().getUniqueId(), (ArmorStand) e.getRightClicked());
                        armorStandMenu.open();
                    }
                    e.setCancelled(true);
                } else {
                    if (AdvancedArmorStands.plugin.getConfig().getBoolean("shift-right-click-to-add")) {


                        UUID playerId = player.getBukkitPlayer().getUniqueId();

                        if (!selectCount.containsKey(playerId) || selectCount.get(playerId) != (ArmorStand) e.getRightClicked()) {
                            interactionCount.put(playerId, 0);
                        }

                        int count = interactionCount.getOrDefault(playerId, 0) + 1;

                        if (count < 3) {
                            selectCount.put(playerId, (ArmorStand) e.getRightClicked());
                            interactionCount.put(playerId, count);
                            player.getBukkitPlayer().sendMessage(ChatColor.GREEN + "Do this " + (3 - count) + " more time(s) to save this advanced armor stands.");
                        } else if (count == 3) {
                            int randomSuffix = new Random().nextInt(900) + 100;
                            String name = "SavedStand" + randomSuffix;

                            ArmorStand stand = (ArmorStand) e.getRightClicked();
                            CreateCommand.saveArmorStand(name, stand);
                            player.getBukkitPlayer().sendMessage(ChatColor.GOLD + "Armor stand saved as " + name + "!");

                            interactionCount.remove(playerId);
                        }

                    }
                }

            }

        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof ArmorStand) {
            if (ArmorStandUtils.isConfiguredArmorStand(e.getEntity())) {
                e.setCancelled(true);
            }
        }

    }
}
