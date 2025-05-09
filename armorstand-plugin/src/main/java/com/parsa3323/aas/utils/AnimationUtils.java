package com.parsa3323.aas.utils;

import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.configs.AnimationConfig;
import com.parsa3323.aas.configs.ArmorStands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

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

            if (ArmorStands.get().contains("armorstands." + key + ".animation")) {
                AdvancedArmorStands.debug("armorstands." + key + ".animation");
                String animationName = ArmorStands.get().getString("armorstands." + key + ".animation");
                AdvancedArmorStands.debug("Found animation '" + animationName + "' for key: " + key);

                ConfigurationSection wave = animations.getConfigurationSection("animations." + animationName);
                if (wave == null) {
                    AdvancedArmorStands.debug("Missing animation config section for: " + animationName);
                    continue;
                }

                int interval = wave.getInt("interval");
                boolean loop = wave.getBoolean("loop");
                List<Map<?, ?>> steps = wave.getMapList("steps");

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
