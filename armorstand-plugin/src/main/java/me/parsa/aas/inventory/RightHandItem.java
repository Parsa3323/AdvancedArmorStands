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

package me.parsa.aas.inventory;

import me.parsa.aas.inventory.manager.InventoryItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;

public class RightHandItem extends InventoryItem {
    @Override
    public String getName() {
        return "RightHandItem";
    }

    @Override
    public ItemStack getItemStack() {
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GOLD + "RIGHT CLICK " + ChatColor.GRAY + "To rotate right hand to right");
        lore.add(ChatColor.GOLD + "LEFT CLICK " + ChatColor.GRAY + "To rotate right hand to left");
        lore.add(ChatColor.GOLD + "SHIFT RIGHT CLICK " + ChatColor.GRAY + "To move right arm up");
        lore.add(ChatColor.GOLD + "SHIFT RIGHT CLICK " + ChatColor.GRAY + "To move left arm down");

        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "RightHand");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public int getSlot() {
        return 1;
    }

    @Override
    public void execute(Player p, ArmorStand armorStand, Action action) {
        armorStand.setArms(true);

        EulerAngle currentPose = armorStand.getRightArmPose();

        double step = Math.toRadians(5);

        if (p.isSneaking()) {
            if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
                EulerAngle newPose = new EulerAngle(currentPose.getX() - step, currentPose.getY(), currentPose.getZ());
                armorStand.setRightArmPose(newPose);
            } else if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {
                EulerAngle newPose = new EulerAngle(currentPose.getX() + step, currentPose.getY(), currentPose.getZ());
                armorStand.setRightArmPose(newPose);
            }
        }
        else {
            if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
                EulerAngle newPose = new EulerAngle(currentPose.getX(), currentPose.getY() + step, currentPose.getZ());
                armorStand.setRightArmPose(newPose);
            } else if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {
                EulerAngle newPose = new EulerAngle(currentPose.getX(), currentPose.getY() - step, currentPose.getZ());
                armorStand.setRightArmPose(newPose);
            }
        }
    }

}
