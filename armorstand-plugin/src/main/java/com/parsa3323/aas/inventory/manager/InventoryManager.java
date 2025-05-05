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

package com.parsa3323.aas.inventory.manager;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.inventory.*;
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.InventoryUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
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
            String name = ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName());
            if ("EXIT (Right Click)".equalsIgnoreCase(name)) {
                event.setCancelled(true);
                InventoryUtils.restore(player);
                ArmorStand armorStand = ArmorStandSelectionCache.getSelectedArmorStand(player.getUniqueId());
                AdvancedArmorStands.debug(armorStand.getName());
                new ArmorStandMenu(new PlayerMenuUtility(player), armorStand).open();
                ArmorStandSelectionCache.removeSelectedArmorStand(player.getUniqueId());

                return;
            }

        }


        if (player.hasPermission("advanced-armorstands.admin") && event.getItem() != null) {
            for (int i = 0; i < getInventoryItems().size(); i++) {

                if (event.getItem().equals(getInventoryItems().get(i).getItemStack())) {
                    event.setCancelled(true);
                    getInventoryItems().get(i).execute(player, ArmorStandSelectionCache.getSelectedArmorStand(player.getUniqueId()), action);

                }

            }
        }



    }



    public ArrayList<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }


}
