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

package com.parsa3323.aas.ai.manager;

import com.cryptomorin.xseries.XMaterial;
import com.parsa3323.aas.ai.AiToggleOption;
import com.parsa3323.aas.ai.MemoryOption;
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.menus.manager.PaginatedMenu;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AiSettingsManager extends PaginatedMenu {

    public ArrayList<AiSettingsOption> settings = new ArrayList<>();

    private final ArmorStand armorStand;

    public AiSettingsManager(PlayerMenuUtility playerMenuUtility, ArmorStand armorStand) {
        super(playerMenuUtility);
        this.armorStand = armorStand;

        settings.add(new MemoryOption());
        settings.add(new AiToggleOption());
    }


    @Override
    public String getMenuName() {
        return ChatColor.DARK_GRAY + "As " + ChatColor.GRAY + "» " + ChatColor.DARK_GRAY + "Ai Settings";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null) return;

        ItemMeta meta = clickedItem.getItemMeta();
        if (meta == null) return;

        String displayName = meta.getDisplayName();
        if (displayName == null) return;

        String itemName = ChatColor.stripColor(displayName);

        if (clickedItem.getType() == Material.ARROW) {
            if (itemName.equalsIgnoreCase("Left")) {
                if (page > 0) {
                    page--;
                    super.open();
                }
            } else if (itemName.equalsIgnoreCase("Right")) {
                if (!((index + 1) >= settings.size())) {
                    page++;

                    super.open();
                }
            }
            return;
        }

        if (clickedItem.getType() == XMaterial.BARRIER.parseMaterial()) {
            ArmorStandMenu armorStandMenu = new ArmorStandMenu(new PlayerMenuUtility(player), armorStand);
            armorStandMenu.open();
        }

        for (int i = 0; i < settings.size(); i++) {

            if (e.getCurrentItem().equals(settings.get(i).getItemStack(armorStand, playerMenuUtility.getOwner()))) {

                settings.get(i).execute(armorStand, playerMenuUtility.getOwner());
                if (settings.get(i).updateInventory()) super.open();

                return;
            }

        }
    }

    @Override
    public void setMenuItems() {

        addMenuBorder(settings.size());

        index = page * maxItemsPerPage;

        for (int i = 0; i < settings.size(); i++) {
            if (index >= settings.size()) break;

            ItemStack itemStack = settings.get(i).getItemStack(armorStand, playerMenuUtility.getOwner());
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
        return false;
    }

    @Override
    public void close(InventoryCloseEvent e) {

    }
}
