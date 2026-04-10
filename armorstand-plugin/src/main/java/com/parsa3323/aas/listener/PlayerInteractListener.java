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


import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.actions.SenderType;
import com.parsa3323.aas.api.actions.TriggerType;
import com.parsa3323.aas.api.events.ActionTriggerEvent;
import com.parsa3323.aas.api.events.ArmorStandCreateEvent;
import com.parsa3323.aas.api.events.ArmorStandDeleteEvent;
import com.parsa3323.aas.api.player.IPlayer;
import com.parsa3323.aas.config.ActionConfig;
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.player.PlayerManager;
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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

public class PlayerInteractListener implements Listener {

    private final Map<UUID, Integer> interactionCount  = new HashMap<>();
    private final Map<UUID, Integer> deleteIntractionCount = new HashMap<>();
    private final Map<UUID, ArmorStand> selectCount = new HashMap<>();
    private final Map<UUID, ArmorStand> deletionCount = new HashMap<>();

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
                                TextComponent textComponent = new TextComponent(ChatColor.GREEN + "Do this " + (3 - count) + " more time" + ((3 - count) > 1 ? "s" : "") +  " to save this ArmorStands.");
                                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + "You can disable this in the config.yml").create()));

                                player.getBukkitPlayer().spigot().sendMessage(textComponent);
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

                                player.getBukkitPlayer().sendMessage(ChatColor.YELLOW + "ArmorStand saved as " + name + "!");
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
        if (standSection == null) {
            if (AdvancedArmorStands.plugin.getConfig().getBoolean("shift-click-to-delete")) {
                if (player.isSneaking()) {
                    UUID playerId = player.getUniqueId();

                    if (!deleteIntractionCount.containsKey(playerId) || deletionCount.get(playerId) != armorStand) {
                        deleteIntractionCount.put(playerId, 0);
                    }

                    int count = deleteIntractionCount.getOrDefault(playerId, 0) + 1;
                    System.out.println(count);

                    switch (count) {
                        case 1:
                            player.playSound(player.getLocation(), XSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1f, 0.8f);
                            break;
                        case 2:
                            player.playSound(player.getLocation(), XSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1f, 1.0f);
                            break;
                        case 3:
                            player.playSound(player.getLocation(), XSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1f, 1.2f);
                            break;

                    }

                    if (count < 3) {
                        deletionCount.put(playerId, armorStand);
                        deleteIntractionCount.put(playerId, count);


                        TextComponent textComponent = new TextComponent(ChatColor.RED + "Do this " + (3 - count) + " more time" + ((3 - count) > 1 ? "s" : "") + " to delete this ArmorStand.");
                        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + "You can disable this in the config.yml").create()));

                        player.spigot().sendMessage(textComponent);
                    } else if (count == 3) {

                        ArmorStandDeleteEvent armorStandDeleteEvent = new ArmorStandDeleteEvent(player, armorStand);
                        Bukkit.getPluginManager().callEvent(armorStandDeleteEvent);

                        if (armorStandDeleteEvent.isCancelled()) return;

                        ArmorStandUtils.deleteArmorStand(standName, player);

                        deleteIntractionCount.remove(playerId);
                    }
                }

            }

            return;
        };

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

}