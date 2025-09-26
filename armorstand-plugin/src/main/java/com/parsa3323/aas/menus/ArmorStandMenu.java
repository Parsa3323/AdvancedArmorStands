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
import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.events.ArmorStandStateChangeEvent;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.menus.manager.Menu;
import com.parsa3323.aas.options.manager.SettingsManager;
import com.parsa3323.aas.tools.manager.ToolsManager;
import com.parsa3323.aas.utils.*;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ArmorStandMenu extends Menu {

    private final HashMap<UUID, Long> cooldownMap = new HashMap<>();

    private final ArrayList<UUID> coolDownList = new ArrayList<>();

    private final ItemStack EQUIPMENT_ITEM = XMaterial.YELLOW_STAINED_GLASS_PANE.parseItem();

    private final Material EQUIPMENT_MATERIAL = EQUIPMENT_ITEM.getType();

    private final ArmorStand armorStand;

    public ArmorStandMenu(PlayerMenuUtility playerMenuUtility, ArmorStand armorStand) {
        super(playerMenuUtility);
        this.armorStand = armorStand;
    }

    @Override
    public String getMenuName() {
        return ChatColor.DARK_GRAY + "As " + ChatColor.GRAY + "» " + ChatColor.DARK_GRAY + "Settings";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        if (armorStand == null) {
            e.setCancelled(true);
            return;
        }

        Player p = (Player) e.getWhoClicked();

        switch (e.getSlot()) {
            case 33:
                InventoryUtils.save(p);
                if (playerMenuUtility.getOwner().getGameMode() == GameMode.ADVENTURE) {
                    InventoryUtils.setGameMode(playerMenuUtility.getOwner(), playerMenuUtility.getOwner().getGameMode());
                    playerMenuUtility.getOwner().setGameMode(GameMode.CREATIVE);
                    playerMenuUtility.getOwner().sendMessage(
                            ChatColor.GREEN + "Your gamemode has been temporarily switched to CREATIVE, " +
                                    "because edit sessions do not support ADVENTURE mode. " +
                                    "It will be restored automatically when you exit."
                    );
                }
                ArmorStandSelectionCache.setSelectedArmorStand(playerMenuUtility.getOwner().getUniqueId(), armorStand);
                ArmorStandSelectionCache.addToEditSession(p);
                InventoryUtils.setOptionItems(p);
                p.closeInventory();

                if (ArmorStandUtils.shouldTeleport(playerMenuUtility.getOwner(), armorStand, 12.0)) {
                    ArmorStandUtils.teleportPlayerInFrontOfStand(playerMenuUtility.getOwner(), armorStand, 2.0);
                }

                break;
            case 11:
                if (e.getClick() == ClickType.SHIFT_RIGHT || e.getClick() == ClickType.SHIFT_LEFT) {
                    ToolsManager toolsManager = new ToolsManager(new PlayerMenuUtility(playerMenuUtility.getOwner()), armorStand);
                    toolsManager.open();

                    return;
                }

                p.closeInventory();
                break;
            case 15:
                ActionMenu actionMenu = new ActionMenu(new PlayerMenuUtility(p), armorStand);
                actionMenu.open();
                break;
            case 29:
                SettingsManager settingsManager = new SettingsManager(new PlayerMenuUtility(p), armorStand, true);
                settingsManager.open();
        }

        if (p == null || e.getInventory() == null || e.getInventory().getHolder() == null) return;

        if (e.getAction() != InventoryAction.PICKUP_ALL
                && e.getAction() != InventoryAction.PICKUP_ONE
                && e.getAction() != InventoryAction.PLACE_ALL
                && e.getAction() != InventoryAction.PLACE_ONE
                && e.getAction() != InventoryAction.SWAP_WITH_CURSOR) {
            e.setCancelled(true);
            return;
        }

        ItemStack itemTaken = e.getCurrentItem();
        ItemStack cursorItem = e.getCursor();

        e.setCancelled(true);

        if (itemTaken != null && itemTaken.getType() == EQUIPMENT_MATERIAL && cursorItem.getType() == Material.AIR) {
            return;
        }


        int slot = e.getSlot();

        if (cursorItem == null) {
            cursorItem = new ItemStack(Material.AIR);
        }


        boolean doesSupportOffHand = VersionSupportUtil.getVersionSupport().canSetItemOffHand();

        if (slot != 4 && slot != 13 && slot != 22 && slot != 31 && slot != 40 && (!doesSupportOffHand || slot != 39)) {
            AdvancedArmorStands.debug("Returned");
            return;
        }

        UUID uuid = p.getUniqueId();
        long now = System.currentTimeMillis();
        if (cooldownMap.containsKey(uuid) && (now - cooldownMap.get(uuid)) < 1000) {
            int rem = (int) Math.ceil((1000 - (now - cooldownMap.get(uuid))) / 1000.0);
            p.sendMessage(ChatColor.RED + "You must wait " + rem + " seconds before placing an item");
            return;
        }

        ItemStack placed = cursorItem.clone();
        placed.setAmount(1);

        if (!ArmorStandUtils.isValidEquipmentForSlot(placed, slot)) {
            p.sendMessage(ChatColor.RED + "This item cannot be placed in this slot!");
            return;
        }

        if (cursorItem.getType() == Material.AIR) {
            AdvancedArmorStands.debug("Removing");
            e.getInventory().setItem(slot, createNull(getSlotName(slot)));
        } else {
            AdvancedArmorStands.debug("Adding");
            e.getInventory().setItem(slot, placed);
        }

        p.setItemOnCursor(new ItemStack(Material.AIR));

        if (itemTaken != null && itemTaken.getType() != Material.AIR && itemTaken.getType() != EQUIPMENT_MATERIAL) {
            p.getInventory().addItem(itemTaken);
        }

        try {
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
                case 39:
                    if (VersionSupportUtil.getVersionSupport().canSetItemOffHand()) {
                        VersionSupportUtil.getVersionSupport().setItemInOffHand(armorStand, placed);
                    } else {
                        return;
                    }
                    break;
                case 40:
                    armorStand.setItemInHand(placed);
                    break;

            }
            Bukkit.getPluginManager().callEvent(new ArmorStandStateChangeEvent(p, armorStand, ArmorStandUtils.getNameByArmorStand(armorStand)));

            p.playSound(p.getLocation(), VersionSupportUtil.getVersionSupport().getEquipSound(), 1,  1);
            p.sendMessage(ChatColor.GREEN + "Armor stand updated successfully!");
        } catch (Exception ex) {
            p.sendMessage(ChatColor.RED + "Failed to update armor stand!");
            ex.printStackTrace();
        }
        if (!coolDownList.contains(p.getUniqueId())) {
            cooldownMap.put(uuid, now);
        }
    }

    @Override
    public void setMenuItems() {
        if (armorStand == null || !armorStand.isValid()) {
            playerMenuUtility.getOwner().sendMessage(ChatColor.RED + "The armor stand is no longer available!");
            playerMenuUtility.getOwner().closeInventory();
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack grayPane = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial(), 1, (short) 7);
                ItemMeta meta = grayPane.getItemMeta();

                if (meta != null) {
                    meta.setDisplayName(ChatColor.RED + "Armor stand is not available");
                    grayPane.setItemMeta(meta);
                }

                inventory.setItem(i, grayPane);
            }
            return;
        }

        Inventory inv = inventory;

        setSlots(inv, XMaterial.GRAY_STAINED_GLASS_PANE, new int[]{
                0,8, 1, 7, 9, 17, 18, 27 , 26, 35, 36, 37, 43
        });

        setSlots(inv, XMaterial.WHITE_STAINED_GLASS_PANE, new int[]{
                2, 3, 5, 6, 38, 41, 42
        });

        setSlots(inv, XMaterial.WHITE_STAINED_GLASS_PANE, new int[]{
                10, 11, 12, 19, 20, 21, 28, 30
        });

        setSlots(inv, XMaterial.WHITE_STAINED_GLASS_PANE, new int[]{
                14, 24, 16, 23, 25, 32, 34
        });

        ItemStack info = XMaterial.GRAY_STAINED_GLASS_PANE.parseItem();
        ItemMeta iMeta = info.getItemMeta();

        String path = "armorstands." + ArmorStandUtils.getNameByArmorStand(armorStand);

        String dateCreated = ArmorStandsConfig.get().getString(path + ".info.date_created", "Unknown");
        String createdBy = ArmorStandsConfig.get().getString(path + ".info.created_by", "Unknown");
        String world = ArmorStandsConfig.get().getString(path + ".info.world", "Unknown");
        int x = ArmorStandsConfig.get().getInt(path + ".info.X", 0);
        int y = ArmorStandsConfig.get().getInt(path + ".info.Y", 0);
        int z = ArmorStandsConfig.get().getInt(path + ".info.Z", 0);

        ArrayList<String> iLore = new ArrayList<>();

        iLore.add(ChatColor.GOLD + " » " + ChatColor.GRAY + "Date Created: " + ChatColor.WHITE + dateCreated);
        iLore.add(ChatColor.GOLD + " » " + ChatColor.GRAY + "Created By: " + ChatColor.WHITE + createdBy);
        iLore.add(ChatColor.GOLD + " » " + ChatColor.GRAY + "World: " + ChatColor.WHITE + world);
        iLore.add(ChatColor.GOLD + " » " + ChatColor.GRAY + "Location: "
                + ChatColor.WHITE + x
                + ChatColor.GRAY + ", "
                + ChatColor.WHITE + y
                + ChatColor.GRAY + ", "
                + ChatColor.WHITE + z);

        iMeta.setLore(iLore);
        iMeta.setDisplayName(ColorUtils.boldAndColor(ChatColor.YELLOW) + "ArmorStand Information");

        info.setItemMeta(iMeta);

        inventory.setItem(44, info);

        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta cMeta = close.getItemMeta();

        cMeta.setDisplayName(ChatColor.RED + "Close");

        ArrayList<String> cLore = new ArrayList<>();

        cLore.add(ChatColor.GRAY + "Click to close this menu");
        cLore.add(ChatColor.GRAY + "Shift-click to open the ");
        cLore.add(ChatColor.GRAY + "Tools Menu for editing");
        cLore.add(ChatColor.GRAY + "the armor stand");

        cMeta.setLore(cLore);
        close.setItemMeta(cMeta);

        inventory.setItem(11, close);


        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Opens an inventory to");
        lore.add(ChatColor.GRAY + "create and delete on-click");
        lore.add(ChatColor.GRAY + "custom command actions for");
        lore.add(ChatColor.GRAY + "your armor stand");




        ItemStack options = new ItemStack(Material.NETHER_STAR);
        ItemMeta oMeta = options.getItemMeta();

        ArrayList<String> oLore = new ArrayList<>();

        oLore.add(ChatColor.GRAY + "Opens an inventory to");
        oLore.add(ChatColor.GRAY + "enable & disable armor");
        oLore.add(ChatColor.GRAY + "stand's options");

        oMeta.setLore(oLore);
        oMeta.setDisplayName(ChatColor.YELLOW + "Options!");
        options.setItemMeta(oMeta);

        inventory.setItem(29, options);

        ItemStack edit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta editMeta= edit.getItemMeta();
        editMeta.setDisplayName(ChatColor.YELLOW + "Edit!");

        ArrayList<String> editLore = new ArrayList<>();
        editLore.add(ChatColor.GRAY + "gives you some item");
        editLore.add(ChatColor.GRAY + "that you can edit");
        editLore.add(ChatColor.GRAY + "as positions with it");
        editMeta.setLore(editLore);
        edit.setItemMeta(editMeta);
        inventory.setItem(33, edit);

        ItemStack actions = new ItemStack(XMaterial.TRIPWIRE_HOOK.parseMaterial()) ;
        ItemMeta tMeta = actions.getItemMeta();
        tMeta.setDisplayName(ChatColor.YELLOW + "Actions");
        tMeta.setLore(lore);
        actions.setItemMeta(tMeta);

        inventory.setItem(15, actions);
        ItemStack head = (armorStand.getHelmet() != null && armorStand.getHelmet().getType() != Material.AIR) ? armorStand.getHelmet().clone() : createNull("HEAD");
        inventory.setItem(4, head);

        ItemStack chest = (armorStand.getChestplate() != null && armorStand.getChestplate().getType() != Material.AIR) ? armorStand.getChestplate().clone() : createNull("CHEST");
        inventory.setItem(13, chest);

        ItemStack leg = (armorStand.getLeggings() != null && armorStand.getLeggings().getType() != Material.AIR) ? armorStand.getLeggings().clone() : createNull("LEG");
        inventory.setItem(22, leg);

        ItemStack boots = (armorStand.getBoots() != null && armorStand.getBoots().getType() != Material.AIR)  ? armorStand.getBoots().clone() : createNull("BOOT");
        inventory.setItem(31, boots);

        ItemStack itemInHand = (armorStand.getItemInHand() != null && armorStand.getItemInHand().getType() != Material.AIR) ? armorStand.getItemInHand().clone() : createNull("NONE");
        inventory.setItem(40, itemInHand);

        ItemStack itemInOffHand;

        if (VersionSupportUtil.getVersionSupport().canSetItemOffHand()) {
            itemInOffHand = (VersionSupportUtil.getVersionSupport().getItemInOffHand(armorStand) != null && VersionSupportUtil.getVersionSupport().getItemInOffHand(armorStand).getType() != Material.AIR) ? VersionSupportUtil.getVersionSupport().getItemInOffHand(armorStand).clone() : createNull("NONE");


        } else {
            itemInOffHand = new ItemStack(XMaterial.WHITE_STAINED_GLASS_PANE.parseMaterial());

            ItemMeta itemMeta = itemInOffHand.getItemMeta();

            itemMeta.setDisplayName(" ");
            itemInOffHand.setItemMeta(itemMeta);
        }

        inventory.setItem(39, itemInOffHand);

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
        AdvancedArmorStands.debug("Closed menu");
    }

    private static void setSlots(Inventory inv, XMaterial material, int[] slots) {
        ItemStack item = material.parseItem();
        if (item == null) return;

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
        }

        for (int slot : slots) {
            inv.setItem(slot, item);
        }
    }

    private ItemStack createNull(String pos) {
        ItemStack head = EQUIPMENT_ITEM;
        ItemMeta itemMeta = head.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Place an item here");
        lore.add(ChatColor.GRAY + "To set the head");
        lore.add(ChatColor.GRAY + "Drag and drop item");
        lore.add(ChatColor.GRAY + "Only armor pieces allowed");

        switch (pos) {
            case "HEAD":
                ArrayList<String> headLore = new ArrayList<>();
                headLore.add(ChatColor.GRAY + "Place an item here");
                headLore.add(ChatColor.GRAY + "To set the head");
                headLore.add(ChatColor.GRAY + "Drag and drop item");
                headLore.add(ChatColor.GRAY + "Only armor pieces allowed");
                itemMeta.setDisplayName(ChatColor.YELLOW + "Head");
                itemMeta.setLore(headLore);
                break;
            case "CHEST":
                ArrayList<String> cLore = new ArrayList<>();
                cLore.add(ChatColor.GRAY + "Place an item here");
                cLore.add(ChatColor.GRAY + "To set the chest");
                cLore.add(ChatColor.GRAY + "Drag and drop item");
                cLore.add(ChatColor.GRAY + "Only armor pieces allowed");
                itemMeta.setDisplayName(ChatColor.YELLOW + "Chestplate");
                itemMeta.setLore(cLore);
                break;
            case "LEG":
                ArrayList<String> legLore = new ArrayList<>();
                legLore.add(ChatColor.GRAY + "Place an item here");
                legLore.add(ChatColor.GRAY + "To set the legs");
                legLore.add(ChatColor.GRAY + "Drag and drop item");
                legLore.add(ChatColor.GRAY + "Only armor pieces allowed");
                itemMeta.setDisplayName(ChatColor.YELLOW + "Leggings");
                itemMeta.setLore(legLore);
                break;
            case "BOOT":
                ArrayList<String> bootLore = new ArrayList<>();
                bootLore.add(ChatColor.GRAY + "Place an item here");
                bootLore.add(ChatColor.GRAY + "To set the boots");
                bootLore.add(ChatColor.GRAY + "Drag and drop item");
                bootLore.add(ChatColor.GRAY + "Only armor pieces allowed");
                itemMeta.setDisplayName(ChatColor.YELLOW + "Boots");
                itemMeta.setLore(bootLore);
                break;
            default:
                ArrayList<String> handLore = new ArrayList<>();
                handLore.add(ChatColor.GRAY + "Place an item here");
                handLore.add(ChatColor.GRAY + "To set the hand");
                handLore.add(ChatColor.GRAY + "Drag and drop item");
                handLore.add(ChatColor.GRAY + "All items are allowed");
                itemMeta.setDisplayName(ChatColor.YELLOW + "Hand");
                itemMeta.setLore(handLore);
                break;
        }


        head.setItemMeta(itemMeta);

        return head;

    }

    @Override
    public void open() {
        if (ArmorStandSelectionCache.isIsInEditSession(playerMenuUtility.getOwner())) {
            return;
        }
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

        playerMenuUtility.getOwner().playSound(playerMenuUtility.getOwner().getLocation(), (getOpenSound() != null) ? getOpenSound() : XSound.BLOCK_NOTE_BLOCK_PLING.parseSound(), 1 , 1);
        playerMenuUtility.getOwner().openInventory(inventory);
    }

    private String getSlotName(int slot) {
        switch (slot) {
            case 4:
                return "HEAD";
            case 13:
                return "CHEST";
            case 22:
                return "LEG";
            case 31:
                return "BOOT";
            case 40:
                return "HAND";
            case 39:
                return "OFFHAND";
            default:
                return "UNKNOWN";
        }
    }


}