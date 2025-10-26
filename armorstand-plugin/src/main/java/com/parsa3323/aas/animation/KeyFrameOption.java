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

package com.parsa3323.aas.animation;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.commands.AnimCreateCommand;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.inventory.manager.InventoryItem;
import com.parsa3323.aas.menus.KeyFrameMenu;
import com.parsa3323.aas.utils.InventoryUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class KeyFrameOption extends InventoryItem {

    @Override
    public String getName() {
        return "KeyFrameOption";
    }
    
    @Override
    public ItemStack getItemStack() {
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.YELLOW + "RIGHT CLICK " + ChatColor.GRAY + "Add a keyframe");
        lore.add(ChatColor.YELLOW + "SHIFT RIGHT CLICK " + ChatColor.GRAY + "Open keyframe menu");
        lore.add(ChatColor.DARK_GRAY + "AdvancedArmorStands Editor Item");

        ItemStack itemStack = new ItemStack(XMaterial.NETHER_STAR.parseMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.GREEN + "Key frame" + ChatColor.GRAY + " (Hover)");

        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public int getSlot() {
        return 8;
    }

    @Override
    public void execute(Player p, ArmorStand armorStand, Action action) {

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (p.isSneaking()) {
                KeyFrameMenu keyFrameMenu = new KeyFrameMenu(new PlayerMenuUtility(p.getPlayer()));
                keyFrameMenu.open();
            } else {
                FileConfiguration config = AnimationConfig.get();

                String animName = AnimCreateCommand.animationNames.get(p.getUniqueId());
                String path = "animations." + animName + ".steps";

                List<Map<String, Object>> steps = new ArrayList<>();
                List<?> rawSteps = config.getList(path);
                if (rawSteps != null) {
                    for (Object obj : rawSteps) {
                        if (obj instanceof Map) {
                            Map<?, ?> rawMap = (Map<?, ?>) obj;
                            Map<String, Object> castedMap = new LinkedHashMap<>();
                            for (Map.Entry<?, ?> entry : rawMap.entrySet()) {
                                castedMap.put(entry.getKey().toString(), entry.getValue());
                            }
                            steps.add(castedMap);
                        }
                    }
                }

                Map<String, Object> newStep = new LinkedHashMap<>();

                Map<String, Object> head = new LinkedHashMap<>();
                head.put("x", Math.toDegrees(armorStand.getHeadPose().getX()));
                head.put("y", Math.toDegrees(armorStand.getHeadPose().getY()));
                head.put("z", Math.toDegrees(armorStand.getHeadPose().getZ()));
                newStep.put("head", head);

                Map<String, Object> leftArm = new LinkedHashMap<>();
                leftArm.put("x", Math.toDegrees(armorStand.getLeftArmPose().getX()));
                leftArm.put("y", Math.toDegrees(armorStand.getLeftArmPose().getY()));
                leftArm.put("z", Math.toDegrees(armorStand.getLeftArmPose().getZ()));
                newStep.put("left_arm", leftArm);

                Map<String, Object> rightArm = new LinkedHashMap<>();
                rightArm.put("x", Math.toDegrees(armorStand.getRightArmPose().getX()));
                rightArm.put("y", Math.toDegrees(armorStand.getRightArmPose().getY()));
                rightArm.put("z", Math.toDegrees(armorStand.getRightArmPose().getZ()));
                newStep.put("right_arm", rightArm);

                Map<String, Object> leftLeg = new LinkedHashMap<>();
                leftLeg.put("x", Math.toDegrees(armorStand.getLeftLegPose().getX()));
                leftLeg.put("y", Math.toDegrees(armorStand.getLeftLegPose().getY()));
                leftLeg.put("z", Math.toDegrees(armorStand.getLeftLegPose().getZ()));
                newStep.put("left_leg", leftLeg);

                Map<String, Object> rightLeg = new LinkedHashMap<>();
                rightLeg.put("x", Math.toDegrees(armorStand.getRightLegPose().getX()));
                rightLeg.put("y", Math.toDegrees(armorStand.getRightLegPose().getY()));
                rightLeg.put("z", Math.toDegrees(armorStand.getRightLegPose().getZ()));
                newStep.put("right_leg", rightLeg);

                steps.add(newStep);

                config.set(path, steps);
                AnimationConfig.save();

                InventoryUtils.sendStackingActionBar(p, ChatColor.GREEN + "Added keyframe!", 60);
                p.playSound(p.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.2f);

            }
        }

    }
}
