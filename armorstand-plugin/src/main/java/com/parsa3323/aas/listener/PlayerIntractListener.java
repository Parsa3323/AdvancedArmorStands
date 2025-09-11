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
import com.parsa3323.aas.api.actions.SenderType;
import com.parsa3323.aas.api.actions.TriggerType;
import com.parsa3323.aas.api.events.ActionTriggerEvent;
import com.parsa3323.aas.api.events.ArmorStandCreateEvent;
import com.parsa3323.aas.api.player.IPlayer;
import com.parsa3323.aas.config.ActionConfig;
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.player.PlayerManager;
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
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

                                ArmorStandCreateEvent armorStandCreateEvent = new ArmorStandCreateEvent(player.getBukkitPlayer(), stand, name);
                                Bukkit.getPluginManager().callEvent(armorStandCreateEvent);

                                if (armorStandCreateEvent.isCancelled()) {
                                    stand.remove();
                                    return;
                                }

                                //CreateCommand.saveArmorStand(name, stand);
                                player.getBukkitPlayer().sendMessage(ChatColor.YELLOW + "Armor stand saved as " + name + "!");
                                ArmorStandUtils.saveArmorStand(name, stand);

                                interactionCount.remove(playerId);
                            }

                        }

                    }
                }

            }

            if (ArmorStandUtils.isConfiguredArmorStand(e.getRightClicked())) {

                ConfigurationSection cs = ActionConfig.get().getConfigurationSection("armorstand." + ArmorStandUtils.getNameByArmorStand((ArmorStand) e.getRightClicked()));

                if (cs == null) return;

                ArrayList<String> list = new ArrayList<>(cs.getKeys(false));

                for (int i = 0; i < list.size(); i++) {
                    Player p = e.getPlayer();
                    String key = list.get(i);
                    String path = "armorstand." + ArmorStandUtils.getNameByArmorStand((ArmorStand) e.getRightClicked()) + "." + key;
                    String trigger = ActionConfig.get().getString(path + ".trigger", "all").toLowerCase();
                    boolean isSneaking = p.isSneaking();
                    boolean allow = trigger.equals("right-click")
                            || (isSneaking && trigger.equals("shift-right-click"))
                            || trigger.equals("all");

                    if (trigger.equals("right-click") && isSneaking) allow = false;

                    if (!allow) continue;

                    TriggerType triggerType = (isSneaking ? TriggerType.SHIFT_RIGHT_CLICK : TriggerType.RIGHT_CLICK);

                    switch (ActionConfig.get().getString(path + ".type")) {
                        case "server":
                            ActionTriggerEvent actionTriggerEvent = new ActionTriggerEvent(SenderType.CONSOLE, player.getBukkitPlayer(), triggerType, (ArmorStand) e.getRightClicked());
                            Bukkit.getPluginManager().callEvent(actionTriggerEvent);
                            if (!actionTriggerEvent.isCancelled()) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), key.replaceAll("-", " "));
                            }
                            break;
                        case "player":
                            ActionTriggerEvent actionTriggerEvent1 = new ActionTriggerEvent(SenderType.PLAYER, player.getBukkitPlayer(), triggerType, (ArmorStand) e.getRightClicked());
                            Bukkit.getPluginManager().callEvent(actionTriggerEvent1);
                            if (!actionTriggerEvent1.isCancelled()) {
                                p.performCommand(key.replaceAll("-", " "));
                            }
                    }
                }
                e.setCancelled(true);

            }

        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof ArmorStand)) return;
        if (!(e.getDamager() instanceof Player)) return;

        ArmorStand armorStand = (ArmorStand) e.getEntity();

        Player player = (Player) e.getDamager();

        if (!ArmorStandUtils.isConfiguredArmorStand(armorStand)) return;

        e.setCancelled(true);

        String standName = ArmorStandUtils.getNameByArmorStand(armorStand);
        ConfigurationSection standSection = ActionConfig.get().getConfigurationSection("armorstand." + standName);
        if (standSection == null) return;

        for (String key : standSection.getKeys(false)) {
            ConfigurationSection commandSection = standSection.getConfigurationSection(key);
            if (commandSection == null) continue;

            String trigger = commandSection.getString("trigger", "all");

            boolean isSneaking = player.isSneaking();
            boolean allow = trigger.equalsIgnoreCase("left-click")
                    || (isSneaking && trigger.equalsIgnoreCase("shift-left-click"))
                    || trigger.equalsIgnoreCase("all");

            if (trigger.equals("left-click") && isSneaking) allow = false;


            if (!allow) continue;

            TriggerType triggerType = (isSneaking ? TriggerType.SHIFT_LEFT_CLICK : TriggerType.LEFT_CLICK);

            String type = commandSection.getString("type");
            String command = key.replaceAll("-", " ");

            handleAction(type, player, triggerType, armorStand, command);
        }


    }

    private static void handleAction(String type, Player player, TriggerType triggerType, ArmorStand armorStand, String command) {
        if ("server".equalsIgnoreCase(type)) {
            ActionTriggerEvent actionTriggerEvent = new ActionTriggerEvent(SenderType.CONSOLE, player, triggerType, armorStand);
            Bukkit.getPluginManager().callEvent(actionTriggerEvent);
            if (!actionTriggerEvent.isCancelled()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        } else if ("player".equalsIgnoreCase(type)) {
            ActionTriggerEvent actionTriggerEvent = new ActionTriggerEvent(SenderType.PLAYER, player, triggerType, armorStand);
            Bukkit.getPluginManager().callEvent(actionTriggerEvent);
            if (!actionTriggerEvent.isCancelled()) {
                player.performCommand(command);
            }
        }
    }

}
