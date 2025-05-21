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

package com.parsa3323.aas.menus.manager;

import com.parsa3323.aas.AdvancedArmorStands;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

//        if (e.getCurrentItem() == null) {
//            return;
//        }


        InventoryHolder holder = e.getClickedInventory().getHolder();

        if (holder instanceof Menu) {
            Menu menu = (Menu) holder;

            if (menu.cancelClicks()) {
                e.setCancelled(true);
            }

            menu.handleMenu(e);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMenuClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();

        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Menu) {

            Menu menu = (Menu) holder;

            menu.close(e);
        }
    }
}