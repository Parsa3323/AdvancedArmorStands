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

package com.parsa3323.aas.listener;


import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.player.IPlayer;
import com.parsa3323.aas.commands.CreateCommand;
import com.parsa3323.aas.config.ActionConfig;
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.player.PlayerManager;
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.*;

public class PlayerIntractListener implements Listener {

    private final Map<UUID, Integer> interactionCount  = new HashMap<>();
    private final Map<UUID, ArmorStand> selectCount = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerInteractAtEntityEvent(PlayerInteractAtEntityEvent e) {
        IPlayer player = PlayerManager.getByBukkit(e.getPlayer());
        if (e.getRightClicked() instanceof ArmorStand) {
            if (player.isAdmin()) {
                if (ArmorStandUtils.isConfiguredArmorStand(e.getRightClicked())) {
                    if (e.getPlayer().isSneaking()) {
                        ArmorStandMenu armorStandMenu = new ArmorStandMenu(new PlayerMenuUtility(player.getBukkitPlayer()), (ArmorStand) e.getRightClicked());
                        ArmorStandSelectionCache.setSelectedArmorStand(player.getBukkitPlayer().getUniqueId(), (ArmorStand) e.getRightClicked());
                        armorStandMenu.open();
                    } else {
                        ArrayList<String> list = new ArrayList<>(ActionConfig.get().getConfigurationSection("armorstand." + ArmorStandUtils.getNameByArmorStand((ArmorStand) e.getRightClicked())).getKeys(false));

                        if (list == null) {
                            list = new ArrayList<>();
                        }

                        for (int i = 0; i < list.size(); i++) {
                            Player p = e.getPlayer();

                            switch (ActionConfig.get().getString("armorstand." + ArmorStandUtils.getNameByArmorStand((ArmorStand) e.getRightClicked()) + "." + list.get(i) + ".type")) {
                                case "server":
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), list.get(i).replaceAll("-", " "));
                                    break;
                                case "player":
                                    p.performCommand(list.get(i).replaceAll("-", " "));

                            }
                        }
                        e.setCancelled(true);
                    }
                    e.setCancelled(true);
                } else {
                    if (AdvancedArmorStands.plugin.getConfig().getBoolean("shift-right-click-to-add")) {

                        if (player.getBukkitPlayer().isSneaking()) {

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
                                player.getBukkitPlayer().sendMessage(ChatColor.YELLOW + "Armor stand saved as " + name + "!");
                                ArmorStandUtils.saveArmorStand(name, stand);

                                interactionCount.remove(playerId);
                            }

                        }

                    }
                }

            } else {

                if (ArmorStandUtils.isConfiguredArmorStand(e.getRightClicked())) {
                    ArrayList<String> list = new ArrayList<>(ActionConfig.get().getConfigurationSection("armorstand." + ArmorStandUtils.getNameByArmorStand((ArmorStand) e.getRightClicked())).getKeys(false));
                    for (int i = 0; i < list.size(); i++) {
                        player.getBukkitPlayer().performCommand(list.get(i).replaceAll("-", " "));
                    }
                    e.setCancelled(true);

                }

            }

        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof ArmorStand) {
            if (ArmorStandUtils.isConfiguredArmorStand(e.getEntity())) {
                ArrayList<String> list = new ArrayList<>(ActionConfig.get().getConfigurationSection("armorstand." + ArmorStandUtils.getNameByArmorStand((ArmorStand) e.getEntity())).getKeys(false));

                if (list == null) {
                    list = new ArrayList<>();
                }

                for (int i = 0; i < list.size(); i++) {
                    if (!(e.getDamager() instanceof Player)) return;

                    Player p = (Player) e.getDamager();

                    switch (ActionConfig.get().getString("armorstand." + ArmorStandUtils.getNameByArmorStand((ArmorStand) e.getEntity()) + "." + list.get(i) + ".type")) {
                        case "server":
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), list.get(i).replaceAll("-", " "));
                            break;
                        case "player":
                            p.performCommand(list.get(i).replaceAll("-", " "));

                    }
                }
                e.setCancelled(true);
            }
        }

    }
}
