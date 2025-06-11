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

package com.parsa3323.aas.menus;

import com.cryptomorin.xseries.XMaterial;
import com.parsa3323.aas.config.ActionConfig;
import com.parsa3323.aas.menus.manager.PaginatedMenu;
import com.parsa3323.aas.utils.ActionUtils;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ActionMenu extends PaginatedMenu {

    private final ArmorStand armorStand;

    private static final Map<UUID, String> map = new HashMap<>();

    public ActionMenu(PlayerMenuUtility playerMenuUtility, ArmorStand armorStand) {
        super(playerMenuUtility);
        this.armorStand = armorStand;
    }

    @Override
    public String getMenuName() {
        return "Action Menu";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        ArrayList<String> totalAction = ActionUtils.getTotalActionsForArmorStand(ArmorStandUtils.getNameByArmorStand(armorStand));

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
                if (!((index + 1) >= totalAction.size())) {
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

        if (e.getSlot() == 50) {
            map.put(player.getUniqueId(), ArmorStandUtils.getNameByArmorStand(armorStand));
            player.closeInventory();
            player.sendMessage(ChatColor.GREEN + "Type the command to set (no '/'), Type 'exit' to cancel.");
        }

        if (clickedItem.getType() == XMaterial.PLAYER_HEAD.parseMaterial()) {

            if (ActionConfig.get().get("armorstand." + ArmorStandUtils.getNameByArmorStand(armorStand) + "." + itemName) == null) return;

            ActionConfig.get().set("armorstand." + ArmorStandUtils.getNameByArmorStand(armorStand) + "." + itemName, null);
            ActionConfig.save();
            ActionConfig.reload();

            super.open();
        }

    }

    public static Map<UUID, String> getMap() {
        return map;
    }

    @Override
    public void setMenuItems() {

        ArrayList<String> totalAction = ActionUtils.getTotalActionsForArmorStand(ArmorStandUtils.getNameByArmorStand(armorStand));

        addMenuBorder(totalAction.size());

        index = (page * maxItemsPerPage);

        for (int i = 0; i < maxItemsPerPage; i++) {
            if (index >= totalAction.size()) break;

            String key = totalAction.get(index);

            ItemStack itemStack = new ItemStack(VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODg5ODg1MjNmMjYzMWRlNWNiMDFmZGVhMzg3MDViNjRlYjkwNjY2N2Q4ZDk5YmNiODU5YTBhMTZkYjU5MWE3OCJ9fX0="));
            ItemMeta itemMeta = itemStack.getItemMeta();

            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "This means if you");
            lore.add(ChatColor.GRAY + "click this armor");
            lore.add(ChatColor.GRAY + "stand, command will");
            lore.add(ChatColor.GRAY + "be run: " + key.replaceAll("-", " "));
            lore.add("");
            lore.add(ChatColor.YELLOW + "Click to remove");

            itemMeta.setLore(lore);
            itemMeta.setDisplayName(ChatColor.YELLOW + key);
            itemStack.setItemMeta(itemMeta);

            inventory.addItem(itemStack);
            index++;

        }

        ItemStack createTypeItem = new ItemStack(XMaterial.REDSTONE_BLOCK.parseMaterial());
        ItemMeta createTypeMeta = createTypeItem.getItemMeta();
        createTypeMeta.setDisplayName(ChatColor.YELLOW + "Create an action");

        ArrayList<String> createLore = new ArrayList<>();
        createLore.add(ChatColor.GRAY + "Select this to create");
        createLore.add(ChatColor.GRAY + "an action that runs");
        createLore.add(ChatColor.GRAY + "commands on clicks");
        createLore.add("");
        createLore.add(ChatColor.YELLOW + "Click to create");



        createTypeMeta.setLore(createLore);
        createTypeItem.setItemMeta(createTypeMeta);

        inventory.setItem(50, createTypeItem);


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

    @Override
    public void addMenuBorder(int total) {
        if (page > 0) {
            inventory.setItem(48, makeItem(Material.ARROW, ChatColor.GREEN + "Left"));
        } else {
            inventory.setItem(48, super.FILLER_GLASS);
        }

        inventory.setItem(49, makeItem(Material.BARRIER, ChatColor.RED + "Close"));

        if ((page + 1) * maxItemsPerPage < total) {
            inventory.setItem(50, makeItem(Material.ARROW, ChatColor.GREEN + "Right"));
        } else {
            inventory.setItem(50, super.FILLER_GLASS);
        }

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
        inventory.setItem(17, super.FILLER_GLASS);
        inventory.setItem(18, super.FILLER_GLASS);
        inventory.setItem(26, super.FILLER_GLASS);
        inventory.setItem(27, super.FILLER_GLASS);
        inventory.setItem(35, super.FILLER_GLASS);
        inventory.setItem(36, super.FILLER_GLASS);

        for (int i = 44; i < 54; i++) {
            if (i == 50) continue;
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
    }
}
