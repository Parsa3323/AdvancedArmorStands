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

package com.parsa3323.aas.tools.manager;

import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.menus.manager.PaginatedMenu;
import com.parsa3323.aas.tools.Test;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ToolsManager extends PaginatedMenu {

    public ArrayList<ToolsOption> toolsOptions = new ArrayList<>();

    private final ArmorStand armorStand;

    public ToolsManager(PlayerMenuUtility playerMenuUtility, ArmorStand armorStand) {
        super(playerMenuUtility);
        this.armorStand = armorStand;

        toolsOptions.add(new Test());
    }

    @Override
    public String getMenuName() {
        return ChatColor.DARK_GRAY + "As " + ChatColor.GRAY + "» " + ChatColor.DARK_GRAY + "Tools";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || !clickedItem.hasItemMeta() || !clickedItem.getItemMeta().hasDisplayName()) return;

        String itemName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

        if (clickedItem.getType() == Material.BARRIER) {

            ArmorStandMenu armorStandMenu = new ArmorStandMenu(new PlayerMenuUtility(playerMenuUtility.getOwner()), armorStand);
            armorStandMenu.open();


        }

        if (clickedItem.getType() == Material.ARROW) {
            if (itemName.equalsIgnoreCase("Left")) {
                if (page > 0) {
                    page--;
                    super.open();
                }
            } else if (itemName.equalsIgnoreCase("Right")) {
                if (!((index + 1) >= toolsOptions.size())) {
                    page++;

                    super.open();
                }
            }
            return;
        }

        for (ToolsOption toolsOption : toolsOptions) {
            if (e.getCurrentItem().equals(toolsOption.getItemStack(armorStand))) {
                toolsOption.execute(e, armorStand);
            }
        }

    }

    @Override
    public void setMenuItems() {
        addMenuBorder(toolsOptions.size());

        index = page * maxItemsPerPage;

        for (int i = 0; i < toolsOptions.size(); i++) {
            if (index >= toolsOptions.size()) break;

            ItemStack itemStack = toolsOptions.get(i).getItemStack(armorStand);
            inventory.addItem(itemStack);
            index++;

        }
    }


    @Override
    public ItemStack getFillEmpty() {
        return null;
    }

    @Override
    public Sound getOpenSound() {
        return null;
    }

    @Override
    public boolean cancelClicks() {
        return true;
    }

    @Override
    public void close(InventoryCloseEvent e) {

    }
}
