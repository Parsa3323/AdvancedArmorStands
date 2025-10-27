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

package com.parsa3323.aas.utils;

import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.config.ArmorStandsConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimationUtils {

    public static int animationTaskID = -1;

    public static void init() {
        AdvancedArmorStands.debug("Initializing animations...");
        FileConfiguration animations = AnimationConfig.get();

        if (animationTaskID != -1) {
            Bukkit.getScheduler().cancelTask(animationTaskID);
            AdvancedArmorStands.debug("Canceled previous animation task.");
        }

        for (String key : ArmorStandUtils.getArmorStandList()) {
            AdvancedArmorStands.debug("Checking armor stand key: " + key);

            if (ArmorStandsConfig.get().contains("armorstands." + key + ".animation")) {
                AdvancedArmorStands.debug("armorstands." + key + ".animation");
                String animationName = ArmorStandsConfig.get().getString("armorstands." + key + ".animation");
                AdvancedArmorStands.debug("Found animation '" + animationName + "' for key: " + key);

                ConfigurationSection cs = animations.getConfigurationSection("animations." + animationName);
                if (cs == null) {
                    AdvancedArmorStands.debug("Missing animation config section for: " + animationName);
                    continue;
                }

                int interval = cs.getInt("interval");
                boolean loop = cs.getBoolean("loop");
                List<Map<?, ?>> steps = cs.getMapList("steps");

                if (steps == null || steps.isEmpty()) {
                    AdvancedArmorStands.warn("Animation '" + animationName + "' has no steps defined!");
                    continue;
                }

                AdvancedArmorStands.debug("Scheduling animation '" + animationName + "' with " + steps.size() + " steps, interval: " + interval + ", loop: " + loop);

                animationTaskID = Bukkit.getScheduler().runTaskTimer(AdvancedArmorStands.plugin, new Runnable() {
                    int index = 0;

                    @Override
                    public void run() {
                        if (index >= steps.size()) {
                            if (loop) index = 0;
                            else return;
                        }

                        Map<String, Object> step = (Map<String, Object>) steps.get(index++);
                        Map<String, Object> head = (Map<String, Object>) step.get("head");
                        Map<String, Object> rightArm = (Map<String, Object>) step.get("right_arm");
                        Map<String, Object> leftArm = (Map<String, Object>) step.get("left_arm");
                        Map<String, Object> rightLeg = (Map<String, Object>) step.get("right_leg");
                        Map<String, Object> leftLeg = (Map<String, Object>) step.get("left_leg");
                        Map<String, Object> effects = (Map<String, Object>) step.get("effects");

                        ArmorStand stand = ArmorStandUtils.getArmorStandByName(key);
                        if (stand == null) {
                            AdvancedArmorStands.debug("ArmorStand not found for key: " + key);
                            return;
                        }

                        if (head != null) {
                            stand.setHeadPose(new EulerAngle(
                                    Math.toRadians(getIntSafe(head, "x")),
                                    Math.toRadians(getIntSafe(head, "y")),
                                    Math.toRadians(getIntSafe(head, "z"))
                            ));
                        }

                        if (rightArm != null) {
                            stand.setRightArmPose(new EulerAngle(
                                    Math.toRadians(getIntSafe(rightArm, "x")),
                                    Math.toRadians(getIntSafe(rightArm, "y")),
                                    Math.toRadians(getIntSafe(rightArm, "z"))
                            ));
                        }

                        if (leftArm != null) {
                            stand.setLeftArmPose(new EulerAngle(
                                    Math.toRadians(getIntSafe(leftArm, "x")),
                                    Math.toRadians(getIntSafe(leftArm, "y")),
                                    Math.toRadians(getIntSafe(leftArm, "z"))
                            ));
                        }

                        if (rightLeg != null) {
                            stand.setRightLegPose(new EulerAngle(
                                    Math.toRadians(getIntSafe(rightLeg, "x")),
                                    Math.toRadians(getIntSafe(rightLeg, "y")),
                                    Math.toRadians(getIntSafe(rightLeg, "z"))
                            ));
                        }

                        if (leftLeg != null) {
                            stand.setLeftLegPose(new EulerAngle(
                                    Math.toRadians(getIntSafe(leftLeg, "x")),
                                    Math.toRadians(getIntSafe(leftLeg, "y")),
                                    Math.toRadians(getIntSafe(leftLeg, "z"))
                            ));
                        }

                        if (effects != null && effects.containsKey("sound")) {
                            try {
                                Sound sound = XSound.valueOf((String) effects.get("sound")).parseSound();
                                stand.getWorld().playSound(stand.getLocation(), sound, 1F, 1F);
                            } catch (IllegalArgumentException ex) {
                                AdvancedArmorStands.debug("Invalid sound: " + effects.get("sound"));
                            }
                        }
                    }
                }, 0L, interval).getTaskId();
            } else {
                AdvancedArmorStands.debug("No animation defined for key: " + key);
            }
        }
    }

    public static ArrayList<String> getAnimationsList() {
        ConfigurationSection cs = AnimationConfig.get().getConfigurationSection("animations");

        if (cs == null) return new ArrayList<>();

        return new ArrayList<>(cs.getKeys(false));
    }

    private static int getIntSafe(Map<String, Object> map, String key) {
        Object val = map.get(key);
        if (val instanceof Number) return ((Number) val).intValue();
        if (val instanceof String) {
            try {
                return Integer.parseInt((String) val);
            } catch (NumberFormatException ignored) {}
        }
        return 0;
    }

    public static void reloadAnimations() {
        if (animationTaskID != -1) {
            Bukkit.getScheduler().cancelTask(animationTaskID);
            AdvancedArmorStands.debug("Canceled previous animation task.");
        }

        AnimationConfig.reload();
        init();
    }


}
