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

package me.parsa.aas.Menus;

import me.parsa.aas.AdvancedArmorStands;
import me.parsa.aas.Menus.Manager.Menu;
import me.parsa.aas.Utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

//TEST
public class ArmorStandMenu extends Menu {

    private final HashMap<UUID, Long> map = new HashMap<>();

    private final String name;

    private final ArmorStand armorStand;

    public ArmorStandMenu(PlayerMenuUtility playerMenuUtility, String name1, ArmorStand armorStand) {
        super(playerMenuUtility);
        this.name = name1;
        this.armorStand = armorStand;
    }

    @Override
    public String getMenuName() {
        return ChatColor.GOLD + "AAS" + ChatColor.YELLOW + "»" + ChatColor.GOLD + " Settings";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (p == null || e.getInventory() == null || e.getInventory().getHolder() == null) return;

        InventoryAction action = e.getAction();
        ClickType click = e.getClick();

        if (action == InventoryAction.DROP_ALL_CURSOR || action == InventoryAction.DROP_ONE_CURSOR
                || action == InventoryAction.DROP_ONE_SLOT || action == InventoryAction.DROP_ALL_SLOT) {
            e.setCancelled(true);
            return;
        }

        e.setCancelled(true);

        int slot = e.getSlot();
        ItemStack cursorItem = e.getCursor();

        if (cursorItem == null) {
            cursorItem = new ItemStack(Material.AIR);
        }

        if (slot != 4 && slot != 13 && slot != 22 && slot != 31 && slot != 40) return;

        UUID uuid = p.getUniqueId();
        long now = System.currentTimeMillis();
        if (map.containsKey(uuid) && (now - map.get(uuid)) < 5000) {
            int rem = (int) Math.ceil((5000 - (now - map.get(uuid))) / 1000.0);
            p.sendMessage(ChatColor.RED + "You must wait " + rem + " seconds before placing an item");
            return;
        }

        map.put(uuid, now);

        ItemStack placed = cursorItem.clone();
        placed.setAmount(1);

        ItemStack itemTaken = e.getCurrentItem();
        if (itemTaken == null) itemTaken = new ItemStack(Material.AIR);

        e.getInventory().setItem(slot, placed);
        p.setItemOnCursor(new ItemStack(Material.AIR));

        if (itemTaken.getType() != Material.AIR) {
            p.getInventory().addItem(itemTaken);
        }

        switch (slot) {
            case 4:
                armorStand.setHelmet(placed);
                break;
            case 13:
                armorStand.setChestplate(placed);
                break;
            case 22:
                armorStand.setLeggings(placed);
                break;
            case 31:
                armorStand.setBoots(placed);
                break;
            case 40:
                armorStand.setItemInHand(placed);
                break;
        }
    }



    @Override
    public void setMenuItems() {
        if (armorStand == null || !armorStand.isValid()) {
            playerMenuUtility.getOwner().sendMessage(ChatColor.RED + "The armor stand is no longer available!");
            playerMenuUtility.getOwner().closeInventory();
            return;
        }

        Inventory inv = inventory;
        setSlots(inv, Material.STAINED_GLASS_PANE, (byte) 7, new int[]{
                0, 1, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 43, 44
        });

        setSlots(inv, Material.STAINED_GLASS_PANE, (byte) 0, new int[]{
                2, 3, 5, 6, 38, 39, 41, 42
        });

        setSlots(inv, Material.STAINED_GLASS_PANE, (byte) 10, new int[]{
                10, 11, 12, 19, 20, 21, 28, 29, 30
        });

        setSlots(inv, Material.STAINED_GLASS_PANE, (byte) 2, new int[]{
                14, 15, 16, 23, 24, 25, 32, 33, 34
        });

        ItemStack head = (armorStand.getHelmet() != null) ? new ItemStack(armorStand.getHelmet().getType()) : new ItemStack(Material.AIR);
        ItemMeta headMeta = head.getItemMeta();
    //    headMeta.setDisplayName(ChatColor.GREEN + "Helmet");
        head.setItemMeta(headMeta);
        inventory.setItem(4, head);

        ItemStack chest = (armorStand.getChestplate() != null) ? new ItemStack(armorStand.getChestplate().getType()) : new ItemStack(Material.AIR);
        ItemMeta chestMeta = chest.getItemMeta();
     //   chestMeta.setDisplayName(ChatColor.GREEN + "ChestPlate");
        chest.setItemMeta(chestMeta);
        inventory.setItem(13, chest);

        ItemStack leg = (armorStand.getLeggings() != null) ? new ItemStack(armorStand.getLeggings().getType()) : new ItemStack(Material.AIR);
        ItemMeta legMeta = leg.getItemMeta();
     //   legMeta.setDisplayName(ChatColor.GREEN + "Leggings");
        leg.setItemMeta(legMeta);
        inventory.setItem(22, leg);

        ItemStack boots = (armorStand.getBoots() != null) ? new ItemStack(armorStand.getBoots().getType()) : new ItemStack(Material.AIR);
        ItemMeta bootMeta = boots.getItemMeta();
     //   bootMeta.setDisplayName(ChatColor.GREEN + "Boots");
        boots.setItemMeta(bootMeta);
        inventory.setItem(31, boots);

        ItemStack itemInHand = (armorStand.getItemInHand() != null) ? new ItemStack(armorStand.getItemInHand().getType()) : new ItemStack(Material.AIR);
        ItemMeta iihMeta = boots.getItemMeta();
        //   iihMeta.setDisplayName(ChatColor.GREEN + "itemInHand");
        boots.setItemMeta(iihMeta);
        inventory.setItem(40, itemInHand);

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

    private static void setSlots(Inventory inv, Material material, byte data, int[] slots) {
        ItemStack item = new ItemStack(material, 1, data);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
        }

        for (int slot : slots) {
            inv.setItem(slot, item);
        }
    }
}
