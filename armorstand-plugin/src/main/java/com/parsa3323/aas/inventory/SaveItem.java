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

package com.parsa3323.aas.inventory;

import com.parsa3323.aas.inventory.manager.InventoryItem;
import com.parsa3323.aas.menus.SaveMenu;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SaveItem extends InventoryItem {
    @Override
    public String getName() {
        return "SaveItem";
    }

    @Override
    public ItemStack getItemStack() {
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Click to save the ArmorStand setting ");
        lore.add(ChatColor.GRAY + "as a type, that you can use it later");
        lore.add("");
        lore.add(ColorUtils.italicAndColor(ChatColor.DARK_GRAY) + "AdvancedArmorStands Editor Item");

        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GRAY + "» " + ChatColor.GREEN + "Save" + ChatColor.GRAY + " (Right Click)");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public int getSlot() {
        return 7;
    }

    @Override
    public void execute(Player p, ArmorStand armorStand, Action action) {
        SaveMenu saveMenu = new SaveMenu(new PlayerMenuUtility(p.getPlayer()), armorStand);
        saveMenu.open();
    }
}
