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
import com.parsa3323.aas.api.actions.AiRole;
import com.parsa3323.aas.api.data.MemoryData;
import com.parsa3323.aas.api.events.ArmorStandAiRespondEvent;
import com.parsa3323.aas.config.ActionConfig;
import com.parsa3323.aas.config.TypesConfig;
import com.parsa3323.aas.menus.ActionMenu;
import com.parsa3323.aas.menus.SaveMenu;
import com.parsa3323.aas.options.CustomNameOption;
import com.parsa3323.aas.options.manager.SettingsManager;
import com.parsa3323.aas.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Map;
import java.util.UUID;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerChat(AsyncPlayerChatEvent e) {

        String message = e.getMessage();

        if (CustomNameOption.players.containsKey(e.getPlayer().getUniqueId())) {

            if (e.getMessage().equalsIgnoreCase("exit")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Successfully quit the name set session");
                if (!VersionSupportUtil.isHigherThan("1.8")) {
                    SettingsManager settingsManager = new SettingsManager(new PlayerMenuUtility(e.getPlayer()), CustomNameOption.players.get(e.getPlayer().getUniqueId()), CustomNameOption.IS_FROM_SETTINGS);
                    settingsManager.open();
                }

                CustomNameOption.players.remove(e.getPlayer().getUniqueId());
                return;
            }

            e.setCancelled(true);

            CustomNameOption.players.get(e.getPlayer().getUniqueId()).setCustomName((AdvancedArmorStands.isIsPapiAvailable()) ?
                    PlaceholderAPI.setPlaceholders(e.getPlayer(), ChatColor.translateAlternateColorCodes('&', e.getMessage())) :
                    ChatColor.translateAlternateColorCodes('&', e.getMessage()));

            e.getPlayer().sendMessage(ChatColor.GREEN + "Successfully set armor stand's name to '" + e.getMessage() + "' ");

            if (!VersionSupportUtil.isHigherThan("1.8")) {
                SettingsManager settingsManager = new SettingsManager(new PlayerMenuUtility(e.getPlayer()), CustomNameOption.players.get(e.getPlayer().getUniqueId()), CustomNameOption.IS_FROM_SETTINGS);
                settingsManager.open();
            }

            CustomNameOption.players.remove(e.getPlayer().getUniqueId());


            return;
        }

        Map<UUID, ArmorStand> map = SaveMenu.playerList;
        Player p = e.getPlayer();

        if (map.containsKey(p.getUniqueId())) {

            if (e.getMessage().equalsIgnoreCase("exit")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Successfully quit the type create session");
                map.remove(p.getUniqueId());
                return;
            }

            e.setCancelled(true);

            ArmorStand armorStand = map.get(p.getUniqueId());
            String itemName = e.getMessage();

            if (TypesConfig.get().contains(itemName)) {
                p.sendMessage(ChatColor.RED + "This type already exists, Either chose another name or select the type in the save menu");
                return;
            }

            TypesConfig.get().set( itemName + ".Arms", armorStand.hasArms());
            TypesConfig.get().set(itemName + ".Gravity", armorStand.hasGravity());
            TypesConfig.get().set(itemName + ".BasePlate", armorStand.hasBasePlate());
            TypesConfig.get().set(itemName + ".CustomName", armorStand.getCustomName());
            TypesConfig.get().set(itemName + ".isCustomNameVisible", armorStand.isCustomNameVisible());
            TypesConfig.get().set(itemName +  ".itemInHandMaterial", armorStand.getItemInHand().getType().name());
            TypesConfig.get().set(itemName +  ".HeadPos.x", Math.toDegrees(armorStand.getHeadPose().getX()));
            TypesConfig.get().set(itemName + ".HeadPos.y", Math.toDegrees(armorStand.getHeadPose().getY()));
            TypesConfig.get().set(itemName + ".HeadPos.z", Math.toDegrees(armorStand.getHeadPose().getZ()));
            TypesConfig.get().set(itemName + ".rightArmPose.x", Math.toDegrees(armorStand.getRightArmPose().getX()));
            TypesConfig.get().set(itemName + ".rightArmPose.y", Math.toDegrees(armorStand.getRightArmPose().getY()));
            TypesConfig.get().set(itemName + ".rightArmPose.z", Math.toDegrees(armorStand.getRightArmPose().getZ()));
            TypesConfig.get().set(itemName + ".leftArmPose.x", Math.toDegrees(armorStand.getLeftArmPose().getX()));
            TypesConfig.get().set(itemName + ".leftArmPose.y", Math.toDegrees(armorStand.getLeftArmPose().getY()));
            TypesConfig.get().set(itemName + ".leftArmPose.z", Math.toDegrees(armorStand.getLeftArmPose().getZ()));
            TypesConfig.get().set(itemName + ".rightLegPose.x", Math.toDegrees(armorStand.getRightLegPose().getX()));
            TypesConfig.get().set(itemName + ".rightLegPose.y", Math.toDegrees(armorStand.getRightLegPose().getY()));
            TypesConfig.get().set(itemName + ".rightLegPose.z", Math.toDegrees(armorStand.getRightLegPose().getZ()));
            TypesConfig.get().set(itemName + ".leftLegPose.x", Math.toDegrees(armorStand.getLeftLegPose().getX()));
            TypesConfig.get().set(itemName + ".leftLegPose.y", Math.toDegrees(armorStand.getLeftLegPose().getY()));
            TypesConfig.get().set(itemName + ".leftLegPose.z", Math.toDegrees(armorStand.getLeftLegPose().getZ()));

            TypesConfig.save();
            TypesConfig.reload();

            p.sendMessage(ChatColor.GREEN + "Created type '" + itemName + "' with this armor stand's properties");
            map.remove(p.getUniqueId());

            return;
        }

        Map<UUID, String> actions = ActionMenu.getMap();

        if (actions.containsKey(p.getUniqueId())) {

            if (e.getMessage().equalsIgnoreCase("exit")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Successfully quit the action set session");

                if (!VersionSupportUtil.isHigherThan("1.8")) {
                    ActionMenu actionMenu = new ActionMenu(new PlayerMenuUtility(p), ArmorStandUtils.getArmorStandByName(actions.get(p.getUniqueId())));
                    actionMenu.open();
                }

                actions.remove(e.getPlayer().getUniqueId());
                return;
            }

            e.setCancelled(true);

            ActionConfig.get().set("armorstand." + actions.get(p.getUniqueId()) + "." + e.getMessage().replaceAll(" ", "-") + ".type", "player");
            ActionConfig.get().set("armorstand." + actions.get(p.getUniqueId()) + "." + e.getMessage().replaceAll(" ", "-") + ".trigger", "all");
            ActionConfig.save();
            ActionConfig.reload();

            p.sendMessage(ChatColor.GREEN + "Successfully created action '" + e.getMessage().replaceAll(" ", "-") + "'" + ", Change its settings in actions.yml.");

            if (!VersionSupportUtil.isHigherThan("1.8")) {
                ActionMenu actionMenu = new ActionMenu(new PlayerMenuUtility(p), ArmorStandUtils.getArmorStandByName(actions.get(p.getUniqueId())));
                actionMenu.open();
            }


            actions.remove(e.getPlayer().getUniqueId());


            return;
        }

        if (AdvancedArmorStands.isIsAiEnabled()) {
            for (String armorstand : ArmorStandUtils.getArmorStandList()) {
                String mentionPrefix = "@" + armorstand;

                if (message.startsWith(mentionPrefix)) {
                    if (!ArmorStandUtils.hasAi(armorstand)) {
                        p.sendMessage(ChatColor.RED + "This armorstand doesn't have AI enabled");
                        return;
                    }

                    String afterMention = message.substring(mentionPrefix.length()).trim();

                    String coloredMessage = ColorUtils.boldAndColor(ChatColor.YELLOW) + mentionPrefix + ChatColor.RESET + " " + afterMention;

                    e.setFormat("%1$s: " + coloredMessage);

                    MemoryData memoryData = new MemoryData(AiUtils.getHistory(p.getName(), armorstand), AiUtils.getDefaultInstructions(armorstand, AiUtils.getUserSetInstructions(ArmorStandUtils.getArmorStandByName(armorstand))));

                    Bukkit.getScheduler().runTaskLater(AdvancedArmorStands.plugin, () -> {
                        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "»" + ChatColor.GRAY + "] " + ChatColor.GRAY + "Thinking");

                        AiUtils.getResponseAsync(AdvancedArmorStands.getAiApiKey(), memoryData, afterMention, response -> {
                            Bukkit.getScheduler().runTask(AdvancedArmorStands.plugin, () -> {
                                p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "»" + ChatColor.GRAY + "] " + ChatColor.GRAY + response);

                                Bukkit.getPluginManager().callEvent(new ArmorStandAiRespondEvent(ArmorStandUtils.getArmorStandByName(armorstand), response, afterMention, p));

                                AiUtils.addToHistory(p.getName(), armorstand, AiRole.PLAYER, afterMention);
                                AiUtils.addToHistory(p.getName(), armorstand, AiRole.AI, response);


                            });
                        });

                    }, 2L);

                    break;
                }
            }
        }

    }

}
