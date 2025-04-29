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

package com.parsa3323.aas.menus.manager;

import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;

    protected PlayerMenuUtility playerMenuUtility;

    protected ItemStack FILLER_GLASS = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);

    public Menu(PlayerMenuUtility playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
    }

    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent e);

    public abstract void setMenuItems();

    public abstract ItemStack getFillEmpty();

    public abstract Sound getOpenSound();

    public abstract boolean cancelClicks();

    public abstract void close(InventoryCloseEvent e);

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        this.setMenuItems();

        if (getFillEmpty() != null) {
            ItemStack glassPane = getFillEmpty();
            for (int i = 0; i < inventory.getSize(); i++) {
                if (inventory.getItem(i) == null) {
                    inventory.setItem(i, glassPane);
                }
            }
        }

        playerMenuUtility.getOwner().playSound(playerMenuUtility.getOwner().getLocation(), (getOpenSound() != null) ? getOpenSound() : Sound.NOTE_PLING, 1 , 1);
        playerMenuUtility.getOwner().openInventory(inventory);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }


    public ArrayList<String> createLoreAndDescription(String toJoinText, boolean pressable, String ... description) {
        ArrayList<String> lore = new ArrayList<>();
        for (int i = 0; i < description.length; i++) {
            lore.add(ChatColor.GRAY + description[i]);
        }
        lore.add(" ");
        if (pressable) {
            lore.add(ChatColor.YELLOW + toJoinText);
        }
        return lore;
    }

    public ArrayList<String> createLoreNormal(String ... lines) {
        ArrayList<String> lore = new ArrayList<>();
        for (String line : lines) {
            lore.add(ChatColor.GRAY + line);
        }
        return lore;
    }

    public ItemStack makeItem(String name, ArrayList<String> lore, Material item) {
        ItemStack itemStack = new ItemStack(item, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


}
