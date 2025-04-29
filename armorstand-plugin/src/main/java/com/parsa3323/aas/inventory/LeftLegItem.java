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

package com.parsa3323.aas.inventory;

import com.parsa3323.aas.utils.PlayerUtils;
import com.parsa3323.aas.inventory.manager.InventoryItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;

public class LeftLegItem extends InventoryItem {
    @Override
    public String getName() {
        return "LeftLegItem";
    }

    @Override
    public ItemStack getItemStack() {
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.YELLOW + "RIGHT CLICK " + ChatColor.GRAY + "To rotate left leg to right");
        lore.add(ChatColor.YELLOW + "LEFT CLICK " + ChatColor.GRAY + "To rotate left leg to left");
        lore.add(ChatColor.YELLOW + "SHIFT RIGHT CLICK " + ChatColor.GRAY + "To move left leg up");
        lore.add(ChatColor.YELLOW + "SHIFT LEFT CLICK " + ChatColor.GRAY + "To move left leg down");
        lore.add(ChatColor.DARK_GRAY + "AdvancedArmorStands Editor Item");


        ItemStack itemStack = PlayerUtils.getSkullFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDA4NWE2NzgwMmMwZWE0NjJmYmJhNGExZGJiNjgxNzQ4ZjVjZDAxYTMwMzQ3NjdiMWVjZjgzYWUxMjdlODUwZiJ9fX0=");
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN  +"LeftLeg" + ChatColor.GRAY + " (Hover)");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public int getSlot() {
        return 5;
    }

    @Override
    public void execute(Player p, ArmorStand armorStand, Action action) {

        EulerAngle currentPose = armorStand.getLeftLegPose();
        double step = Math.toRadians(5);

        if (p.isSneaking()) {
            if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
                EulerAngle newPose = new EulerAngle(currentPose.getX() - step, currentPose.getY(), currentPose.getZ());
                armorStand.setLeftLegPose(newPose);
            } else if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {
                EulerAngle newPose = new EulerAngle(currentPose.getX() + step, currentPose.getY(), currentPose.getZ());
                armorStand.setLeftLegPose(newPose);
            }
        }
        else {
            if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
                EulerAngle newPose = new EulerAngle(currentPose.getX(), currentPose.getY() + step, currentPose.getZ());
                armorStand.setLeftLegPose(newPose);
            } else if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {
                EulerAngle newPose = new EulerAngle(currentPose.getX(), currentPose.getY() - step, currentPose.getZ());
                armorStand.setLeftLegPose(newPose);
            }
        }
    }
}
