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

package com.parsa3323.aas.inventory.manager;

import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.events.ArmorStandStateChangeEvent;
import com.parsa3323.aas.inventory.*;
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.InventoryUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class InventoryManager implements Listener {

    public ArrayList<InventoryItem> inventoryItems = new ArrayList<>();

    public InventoryManager() {

        inventoryItems.add(new RotateItem());
        inventoryItems.add(new HeadItem());
        inventoryItems.add(new RightHandItem());
        inventoryItems.add(new SaveItem());
        inventoryItems.add(new LeftHandItem());
        inventoryItems.add(new RightLegItem());
        inventoryItems.add(new LeftLegItem());

    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getItem().hasItemMeta()) {
            if (ArmorStandSelectionCache.isIsInEditSession(player)) {
                String name = ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName());
                if ("» EXIT (Right Click)".equalsIgnoreCase(name)) {
                    event.setCancelled(true);
                    InventoryUtils.restore(player);
                    ArmorStandSelectionCache.removeFromEditSession(player);
                    ArmorStand armorStand = ArmorStandSelectionCache.getSelectedArmorStand(player.getUniqueId());
                    AdvancedArmorStands.debug(armorStand.getName());
                    new ArmorStandMenu(new PlayerMenuUtility(player), armorStand).open();
                    ArmorStandSelectionCache.removeSelectedArmorStand(player.getUniqueId());

                    if (InventoryUtils.hasGameMode(player)) {
                        player.setGameMode(InventoryUtils.getAndClearGameMode(player));
                    }

                    return;
                }
            }
        }


        if (player.hasPermission("advanced-armorstands.admin") && event.getItem() != null) {
            if (ArmorStandSelectionCache.isIsInEditSession(player)) {
                for (int i = 0; i < getInventoryItems().size(); i++) {

                    if (event.getItem().equals(getInventoryItems().get(i).getItemStack())) {
                        event.setCancelled(true);


                        getInventoryItems().get(i).execute(player, ArmorStandSelectionCache.getSelectedArmorStand(player.getUniqueId()), action);

                        Bukkit.getPluginManager().callEvent(new ArmorStandStateChangeEvent(player, ArmorStandSelectionCache.getSelectedArmorStand(player.getUniqueId()), ArmorStandUtils.getNameByArmorStand(ArmorStandSelectionCache.getSelectedArmorStand(player.getUniqueId()))));

                        player.playSound(player.getLocation(), XSound.UI_BUTTON_CLICK.parseSound(), 1.0f, 1.0f);

                        InventoryUtils.sendStackingActionBar(player, ChatColor.GREEN + "Updated ArmorStand!", 60);
                    }

                }
            }
        }



    }



    public ArrayList<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }


}
