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
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.config.TypesConfig;
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
import java.util.Random;
import java.util.Set;

public class SaveMenu extends PaginatedMenu {

    private final ArmorStand armorStand;

    public SaveMenu(PlayerMenuUtility playerMenuUtility, ArmorStand armorStand) {
        super(playerMenuUtility);
        this.armorStand = armorStand;
    }

    @Override
    public String getMenuName() {
        return "SaveMenu";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        if (e.getCurrentItem() == null) return;

        Set<String> list = TypesConfig.get().getKeys(false);
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
                if (!((index + 1) >= list.size())) {
                    page++;

                    super.open();
                }
            }
            return;
        }

        if (clickedItem.getType() == Material.BARRIER) player.closeInventory();

        if (clickedItem.getType() == XMaterial.REDSTONE_BLOCK.parseMaterial()) {

            itemName = "SavedType-" + new Random().nextInt(900) + 100;

            TypesConfig.get().set( itemName + ".Arms", armorStand.hasArms());
            TypesConfig.get().set(itemName + ".Gravity", armorStand.hasGravity());
            TypesConfig.get().set(itemName + ".BasePlate", armorStand.hasBasePlate());
            TypesConfig.get().set(itemName + ".CustomName", armorStand.getCustomName());
            TypesConfig.get().set(itemName + ".isCustomNameVisible", armorStand.isCustomNameVisible());
            TypesConfig.get().set(itemName +  ".itemInHandMaterial", armorStand.getItemInHand().getType().name());
            TypesConfig.get().set(itemName +  ".HeadPos.x", Math.toDegrees(armorStand.getHeadPose().getX()));
            TypesConfig.get().set(itemName + ".HeadPos.y", Math.toDegrees(armorStand.getHeadPose().getY()));
            TypesConfig.get().set(itemName + ".HeadPos.z", Math.toDegrees(armorStand.getHeadPose().getZ()));
            TypesConfig.get().set(itemName + ".rightArmPose.x", Math.toDegrees(armorStand.getRightArmPose().getX()));
            TypesConfig.get().set(itemName + ".rightArmPose.y", Math.toDegrees(armorStand.getRightArmPose().getY()));
            TypesConfig.get().set(itemName + ".rightArmPose.z", Math.toDegrees(armorStand.getRightArmPose().getZ()));
            TypesConfig.get().set(itemName + ".leftArmPose.x", Math.toDegrees(armorStand.getLeftArmPose().getX()));
            TypesConfig.get().set(itemName + ".leftArmPose.y", Math.toDegrees(armorStand.getLeftArmPose().getY()));
            TypesConfig.get().set(itemName + ".leftArmPose.z", Math.toDegrees(armorStand.getLeftArmPose().getZ()));
            TypesConfig.get().set(itemName + ".rightLegPose.x", Math.toDegrees(armorStand.getRightLegPose().getX()));
            TypesConfig.get().set(itemName + ".rightLegPose.y", Math.toDegrees(armorStand.getRightLegPose().getY()));
            TypesConfig.get().set(itemName + ".rightLegPose.z", Math.toDegrees(armorStand.getRightLegPose().getZ()));
            TypesConfig.get().set(itemName + ".leftLegPose.x", Math.toDegrees(armorStand.getLeftLegPose().getX()));
            TypesConfig.get().set(itemName + ".leftLegPose.y", Math.toDegrees(armorStand.getLeftLegPose().getY()));
            TypesConfig.get().set(itemName + ".leftLegPose.z", Math.toDegrees(armorStand.getLeftLegPose().getZ()));

            TypesConfig.save();
            TypesConfig.reload();

            player.sendMessage(ChatColor.GREEN + "Created type '" + itemName + "' with this armor stand's properties");
            player.closeInventory();
        }

        if (clickedItem.getType() == Material.ARMOR_STAND) {

            if (TypesConfig.get().get(itemName) == null) return;

            TypesConfig.get().set(itemName + ".Arms", armorStand.hasArms());
            TypesConfig.get().set(itemName + ".Gravity", armorStand.hasGravity());
            TypesConfig.get().set(itemName + ".BasePlate", armorStand.hasBasePlate());
            TypesConfig.get().set(itemName + ".CustomName", armorStand.getCustomName());
            TypesConfig.get().set(itemName + ".isCustomNameVisible", armorStand.isCustomNameVisible());
            TypesConfig.get().set(itemName +  ".itemInHandMaterial", armorStand.getItemInHand().getType().name());
            TypesConfig.get().set(itemName +  ".HeadPos.x", Math.toDegrees(armorStand.getHeadPose().getX()));
            TypesConfig.get().set(itemName + ".HeadPos.y", Math.toDegrees(armorStand.getHeadPose().getY()));
            TypesConfig.get().set(itemName + ".HeadPos.z", Math.toDegrees(armorStand.getHeadPose().getZ()));
            TypesConfig.get().set(itemName + ".rightArmPose.x", Math.toDegrees(armorStand.getRightArmPose().getX()));
            TypesConfig.get().set(itemName + ".rightArmPose.y", Math.toDegrees(armorStand.getRightArmPose().getY()));
            TypesConfig.get().set(itemName + ".rightArmPose.z", Math.toDegrees(armorStand.getRightArmPose().getZ()));
            TypesConfig.get().set(itemName + ".leftArmPose.x", Math.toDegrees(armorStand.getLeftArmPose().getX()));
            TypesConfig.get().set(itemName + ".leftArmPose.y", Math.toDegrees(armorStand.getLeftArmPose().getY()));
            TypesConfig.get().set(itemName + ".leftArmPose.z", Math.toDegrees(armorStand.getLeftArmPose().getZ()));
            TypesConfig.get().set(itemName + ".rightLegPose.x", Math.toDegrees(armorStand.getRightLegPose().getX()));
            TypesConfig.get().set(itemName + ".rightLegPose.y", Math.toDegrees(armorStand.getRightLegPose().getY()));
            TypesConfig.get().set(itemName + ".rightLegPose.z", Math.toDegrees(armorStand.getRightLegPose().getZ()));
            TypesConfig.get().set(itemName + ".leftLegPose.x", Math.toDegrees(armorStand.getLeftLegPose().getX()));
            TypesConfig.get().set(itemName + ".leftLegPose.y", Math.toDegrees(armorStand.getLeftLegPose().getY()));
            TypesConfig.get().set(itemName + ".leftLegPose.z", Math.toDegrees(armorStand.getLeftLegPose().getZ()));

            TypesConfig.save();
            TypesConfig.reload();

            player.sendMessage(ChatColor.GREEN + "Saved armor stand's properties to '" + itemName + "'");
            player.closeInventory();

        }


    }

    @Override
    public void setMenuItems() {
        Set<String> keys = TypesConfig.get().getKeys(false);
        addMenuBorder(keys.size());


        if (keys.isEmpty()) return;

        ArrayList<String> list = new ArrayList<>(keys);

        index = (page * maxItemsPerPage);

        for (int i = 0; i < maxItemsPerPage; i++) {
            if (index >= list.size()) break;

            String key = list.get(index);

            ItemStack itemStack = new ItemStack(Material.ARMOR_STAND);
            ItemMeta itemMeta = itemStack.getItemMeta();

            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Selecting this will override");
            lore.add(ChatColor.GRAY + "your old " + key + " config to");
            lore.add(ChatColor.GRAY + "the current config you made");
            lore.add("");
            lore.add(ChatColor.YELLOW + "Click to save");

            itemMeta.setLore(lore);
            itemMeta.setDisplayName(ChatColor.YELLOW + key);
            itemStack.setItemMeta(itemMeta);

            inventory.addItem(itemStack);
            index++;
        }

        ItemStack createTypeItem = new ItemStack(XMaterial.REDSTONE_BLOCK.parseMaterial());
        ItemMeta createTypeMeta = createTypeItem.getItemMeta();
        createTypeMeta.setDisplayName(ChatColor.YELLOW + "Create a type");

        ArrayList<String> createLore = new ArrayList<>();
        createLore.add(ChatColor.GRAY + "Select this to create");
        createLore.add(ChatColor.GRAY + "a type with this armor");
        createLore.add(ChatColor.GRAY + "stand's properties");
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
        AdvancedArmorStands.debug("Closed menu");
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
