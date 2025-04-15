/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
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

package me.parsa.aas.Utils;

import me.parsa.aas.Menus.ArmorStandMenu;
import me.parsa.aas.inventory.manager.InventoryItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryUtils {

    private static final Map<UUID, ItemStack[]> contentBackups = new HashMap<>();
    private static final Map<UUID, ItemStack[]> armorBackups = new HashMap<>();

    private static ArrayList<InventoryItem> inventoryItems = ArmorStandMenu.inventoryManager.getInventoryItems();

    public static void setItems(Player p) {

        Inventory inventory = p.getInventory();
        inventory.clear();
        for (int i = 0; i < ArmorStandMenu.inventoryManager.getInventoryItems().size(); i++) {



            inventory.setItem(inventoryItems.get(i).getSlot(), inventoryItems.get(i).getItemStack());

            ItemStack itemStack = new ItemStack(Material.BARRIER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.RED + "EXIT");
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(8, itemStack);

        }



    }

    public static void save(Player player) {
        contentBackups.put(player.getUniqueId(), player.getInventory().getContents().clone());
        armorBackups.put(player.getUniqueId(), player.getInventory().getArmorContents().clone());
    }

    public static void restore(Player player) {
        UUID uuid = player.getUniqueId();
        if (contentBackups.containsKey(uuid) && armorBackups.containsKey(uuid)) {
            player.getInventory().setContents(contentBackups.get(uuid).clone());
            player.getInventory().setArmorContents(armorBackups.get(uuid).clone());
        }
    }

    public static void clear(Player player) {
        contentBackups.remove(player.getUniqueId());
        armorBackups.remove(player.getUniqueId());
    }

    public static boolean hasBackup(Player player) {
        return contentBackups.containsKey(player.getUniqueId());
    }

}
