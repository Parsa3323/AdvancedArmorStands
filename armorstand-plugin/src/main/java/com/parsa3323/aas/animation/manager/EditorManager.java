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

package com.parsa3323.aas.animation.manager;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.animation.KeyFrameOption;
import com.parsa3323.aas.api.data.ArmorStandPoseData;
import com.parsa3323.aas.commands.AnimCreateCommand;
import com.parsa3323.aas.inventory.*;
import com.parsa3323.aas.inventory.manager.InventoryItem;
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.InventoryUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class EditorManager implements Listener {

    public ArrayList<InventoryItem> inventoryItems = new ArrayList<>();

    public EditorManager() {

        inventoryItems.add(new HeadItem());
        inventoryItems.add(new RightHandItem());
        inventoryItems.add(new LeftHandItem());
        inventoryItems.add(new RightLegItem());
        inventoryItems.add(new LeftLegItem());
        inventoryItems.add(new KeyFrameOption());
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getItem().hasItemMeta()) {
            if (ArmorStandSelectionCache.isInKeyFrameList(player)) {
                String name = ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName());
                if ("» EXIT (Right Click)".equalsIgnoreCase(name)) {
                    event.setCancelled(true);
                    if (InventoryUtils.hasGameMode(player)) {
                        player.setGameMode(InventoryUtils.getAndClearGameMode(player));
                    }
                    InventoryUtils.restore(player);
                    ArmorStandSelectionCache.removeFromKeyFrameList(player);
                    ArmorStand armorStand = ArmorStandSelectionCache.getKeyFrameSelectedArmorStand(player.getUniqueId());
                    AdvancedArmorStands.debug(armorStand.getName());

                    ArmorStandPoseData savedPose = ArmorStandUtils.getPose(armorStand.getUniqueId());

                    armorStand.setRightArmPose(savedPose.getRightArm());
                    armorStand.setLeftArmPose(savedPose.getLeftArm());
                    armorStand.setRightLegPose(savedPose.getRightLeg());
                    armorStand.setLeftLegPose(savedPose.getLeftLeg());
                    armorStand.setHeadPose(savedPose.getHead());

                    new ArmorStandMenu(new PlayerMenuUtility(player), armorStand).open();
                    ArmorStandSelectionCache.removeKeyFrameSelectedArmorStand(player.getUniqueId());

                    TextComponent textComponent = new TextComponent(ChatColor.GREEN + "Animation saved successfully, click to add to this armorstand");
                    textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + "Click to set this animation to " + ArmorStandUtils.getNameByArmorStand(armorStand)).create()));
                    textComponent.setClickEvent(new ClickEvent(
                            ClickEvent.Action.RUN_COMMAND,
                            "/as animation add "
                                    + AnimCreateCommand.animationNames.get(player.getUniqueId())
                                    + " "
                                    + ArmorStandUtils.getNameByArmorStand(armorStand)
                    ));

                    player.spigot().sendMessage(textComponent);

                    return;
                }
            }
        }

        if (player.hasPermission("advanced-armorstands.admin") && event.getItem() != null) {
            if (ArmorStandSelectionCache.isInKeyFrameList(player)) {
                for (int i = 0; i < getInventoryItems().size(); i++) {

                    if (event.getItem().equals(getInventoryItems().get(i).getItemStack())) {
                        event.setCancelled(true);

                        getInventoryItems().get(i).execute(player, ArmorStandSelectionCache.getKeyFrameSelectedArmorStand(player.getUniqueId()), action);

                    }
                }
            }
        }
    }


    public ArrayList<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }
}
