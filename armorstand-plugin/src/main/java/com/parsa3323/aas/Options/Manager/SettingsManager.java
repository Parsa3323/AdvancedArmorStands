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

package com.parsa3323.aas.Options.Manager;

import com.parsa3323.aas.Menus.ArmorStandMenu;
import com.parsa3323.aas.Menus.Manager.PaginatedMenu;
import com.parsa3323.aas.Options.*;
import com.parsa3323.aas.Utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SettingsManager extends PaginatedMenu {

    public ArrayList<SettingsOption> settingsOptions = new ArrayList<>();

    private final boolean isFromSettings;

    private final ArmorStand armorStand;

    public SettingsManager(PlayerMenuUtility playerMenuUtility, ArmorStand armorStand, boolean isFromSettings) {
        super(playerMenuUtility);
        this.isFromSettings = isFromSettings;
        this.armorStand = armorStand;
        settingsOptions.add(new ArmsOptions());
        settingsOptions.add(new GravityOption());
        settingsOptions.add(new CustomNameOption());
        settingsOptions.add(new BasePlateOption());
        settingsOptions.add(new CustomNameVisibleOption());
    }

    @Override
    public String getMenuName() {
        return "Settings";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null) return;

        String itemName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

        if (clickedItem.getType() == Material.BARRIER) {

            if (isFromSettings) {
                ArmorStandMenu armorStandMenu = new ArmorStandMenu(new PlayerMenuUtility(playerMenuUtility.getOwner()), "NONE", armorStand);
                armorStandMenu.open();
            } else {
                playerMenuUtility.getOwner().closeInventory();

            }

        }

        if (clickedItem.getType() == Material.ARROW) {
            if (itemName.equalsIgnoreCase("Left")) {
                if (page > 0) {
                    page--;
                    super.open();
                }
            } else if (itemName.equalsIgnoreCase("Right")) {
                if (!((index + 1) >= getSettingsOptions().size())) {
                    page++;

                    super.open();
                }
            }
            return;
        }

        for (int i = 0; i < getSettingsOptions().size(); i++) {
            if (e.getCurrentItem().equals(getSettingsOptions().get(i).getItemStack(armorStand))) {
                getSettingsOptions().get(i).click(e, armorStand);
                if (getSettingsOptions().get(i).updateInventory()) {
                    super.open();
                }
                return;
            }
        }
    }

    @Override
    public void setMenuItems() {
        addMenuBorder(getSettingsOptions().size());

        index = page * maxItemsPerPage;

        for (int i = 0; i < getSettingsOptions().size(); i++) {
            if (index >= getSettingsOptions().size()) break;

            ItemStack itemStack = getSettingsOptions().get(i).getItemStack(armorStand);
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

    public ArrayList<SettingsOption> getSettingsOptions() {
        return settingsOptions;
    }
}
