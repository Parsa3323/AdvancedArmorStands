/*
 *
 * Copyright
 * 2026 AdvancedArmorStands, Inc.
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
    private static final List<Integer> animationTaskIDs = new ArrayList<>();

    public static void init() {
        AdvancedArmorStands.debug("Initializing animations...");
        FileConfiguration animations = AnimationConfig.get();
        cancelAnimationTasks();

        for (String key : ArmorStandUtils.getArmorStandList()) {
            AdvancedArmorStands.debug("Checking ArmorStand key: " + key);

            if (!ArmorStandsConfig.get().contains("armorstands." + key + ".animation")) {
                AdvancedArmorStands.debug("No animation defined for key: " + key);
                continue;
            }

            String animationName = ArmorStandsConfig.get().getString("armorstands." + key + ".animation");
            AdvancedArmorStands.debug("Found animation '" + animationName + "' for key: " + key);

            ConfigurationSection cs = animations.getConfigurationSection("animations." + animationName);
            if (cs == null) {
                AdvancedArmorStands.debug("Missing animation config section for: " + animationName);
                continue;
            }

            int interval = Math.max(1, cs.getInt("interval", 1));
            boolean loop = cs.getBoolean("loop");
            boolean realistic = cs.getBoolean("realistic-animations.enabled", false);
            int frames = Math.max(1, cs.getInt("realistic-animations.frames", 1));
            List<Map<?, ?>> steps = cs.getMapList("steps");

            if (steps == null || steps.isEmpty()) {
                AdvancedArmorStands.warn("Animation '" + animationName + "' has no steps defined!", true);
                continue;
            }

            if (!realistic) frames = 1;

            AdvancedArmorStands.debug("Scheduling animation '" + animationName + "' with " + steps.size() + " steps, interval: " + interval + ", loop: " + loop + ", realistic: " + realistic + ", frames: " + frames);

            final int interpolationFrames = frames;

            int taskID = Bukkit.getScheduler().runTaskTimer(AdvancedArmorStands.plugin, new Runnable() {
                private int currentStep = 0;
                private int frame = interpolationFrames;
                private Map<String, Object> previousStep = null;
                private Map<String, Object> targetStep = null;
                private boolean initialized = false;
                private boolean finished = false;

                @Override
                public void run() {
                    ArmorStand stand = ArmorStandUtils.getArmorStandByName(key);
                    if (stand == null) {
                        AdvancedArmorStands.debug("ArmorStand not found for key: " + key);
                        return;
                    }

                    if (!initialized) {
                        previousStep = castMap(steps.get(0));
                        targetStep = previousStep;
                        applyStep(stand, targetStep);
                        playEffects(stand, targetStep);
                        initialized = true;

                        if (steps.size() == 1 && !loop) finished = true;
                        if (steps.size() == 1 && loop) {
                            currentStep = 0;
                            frame = interpolationFrames;
                        }
                        return;
                    }

                    if (finished) return;

                    if (frame >= interpolationFrames) {
                        currentStep++;

                        if (currentStep >= steps.size()) {
                            if (!loop) {
                                finished = true;
                                return;
                            }
                            currentStep = 0;
                        }

                        previousStep = targetStep;
                        targetStep = castMap(steps.get(currentStep));
                        frame = 0;

                        if (interpolationFrames == 1) {
                            applyStep(stand, targetStep);
                            playEffects(stand, targetStep);
                            frame = interpolationFrames;
                        }
                    }

                    if (interpolationFrames > 1 && frame < interpolationFrames) {
                        double progress = (double) frame / interpolationFrames;
                        interpolateStep(stand, previousStep, targetStep, progress);
                        frame++;

                        if (frame >= interpolationFrames) {
                            applyStep(stand, targetStep);
                            playEffects(stand, targetStep);
                        }
                    }
                }
            }, 0L, interval).getTaskId();

            animationTaskIDs.add(taskID);
            animationTaskID = taskID;
        }

        if (animationTaskIDs.isEmpty()) animationTaskID = -1;
    }

    private static void interpolateStep(ArmorStand stand, Map<String, Object> previousStep, Map<String, Object> targetStep, double progress) {
        applyInterpolatedPose(stand, previousStep, targetStep, "head", stand.getHeadPose(), progress);
        applyInterpolatedPose(stand, previousStep, targetStep, "right_arm", stand.getRightArmPose(), progress);
        applyInterpolatedPose(stand, previousStep, targetStep, "left_arm", stand.getLeftArmPose(), progress);
        applyInterpolatedPose(stand, previousStep, targetStep, "right_leg", stand.getRightLegPose(), progress);
        applyInterpolatedPose(stand, previousStep, targetStep, "left_leg", stand.getLeftLegPose(), progress);
    }

    private static void applyInterpolatedPose(ArmorStand stand, Map<String, Object> previousStep, Map<String, Object> targetStep, String part, EulerAngle currentPose, double progress) {
        Map<String, Object> previousPose = previousStep == null ? null : castMap(previousStep.get(part));
        Map<String, Object> targetPose = targetStep == null ? null : castMap(targetStep.get(part));

        if (previousPose == null && targetPose == null) return;

        double startX = previousPose != null ? getDoubleSafe(previousPose, "x") : Math.toDegrees(currentPose.getX());
        double startY = previousPose != null ? getDoubleSafe(previousPose, "y") : Math.toDegrees(currentPose.getY());
        double startZ = previousPose != null ? getDoubleSafe(previousPose, "z") : Math.toDegrees(currentPose.getZ());

        double endX = targetPose != null ? getDoubleSafe(targetPose, "x") : startX;
        double endY = targetPose != null ? getDoubleSafe(targetPose, "y") : startY;
        double endZ = targetPose != null ? getDoubleSafe(targetPose, "z") : startZ;

        double x = startX + (endX - startX) * progress;
        double y = startY + (endY - startY) * progress;
        double z = startZ + (endZ - startZ) * progress;

        EulerAngle pose = new EulerAngle(Math.toRadians(x), Math.toRadians(y), Math.toRadians(z));

        switch (part) {
            case "head":
                stand.setHeadPose(pose);
                break;
            case "right_arm":
                stand.setRightArmPose(pose);
                break;
            case "left_arm":
                stand.setLeftArmPose(pose);
                break;
            case "right_leg":
                stand.setRightLegPose(pose);
                break;
            case "left_leg":
                stand.setLeftLegPose(pose);
                break;
        }
    }

    private static void applyStep(ArmorStand stand, Map<String, Object> step) {
        if (step == null) return;

        Map<String, Object> head = castMap(step.get("head"));
        Map<String, Object> rightArm = castMap(step.get("right_arm"));
        Map<String, Object> leftArm = castMap(step.get("left_arm"));
        Map<String, Object> rightLeg = castMap(step.get("right_leg"));
        Map<String, Object> leftLeg = castMap(step.get("left_leg"));

        if (head != null) {
            stand.setHeadPose(new EulerAngle(Math.toRadians(getDoubleSafe(head, "x")), Math.toRadians(getDoubleSafe(head, "y")), Math.toRadians(getDoubleSafe(head, "z"))));
        }

        if (rightArm != null) {
            stand.setRightArmPose(new EulerAngle(Math.toRadians(getDoubleSafe(rightArm, "x")), Math.toRadians(getDoubleSafe(rightArm, "y")), Math.toRadians(getDoubleSafe(rightArm, "z"))));
        }

        if (leftArm != null) {
            stand.setLeftArmPose(new EulerAngle(Math.toRadians(getDoubleSafe(leftArm, "x")), Math.toRadians(getDoubleSafe(leftArm, "y")), Math.toRadians(getDoubleSafe(leftArm, "z"))));
        }

        if (rightLeg != null) {
            stand.setRightLegPose(new EulerAngle(Math.toRadians(getDoubleSafe(rightLeg, "x")), Math.toRadians(getDoubleSafe(rightLeg, "y")), Math.toRadians(getDoubleSafe(rightLeg, "z"))));
        }

        if (leftLeg != null) {
            stand.setLeftLegPose(new EulerAngle(Math.toRadians(getDoubleSafe(leftLeg, "x")), Math.toRadians(getDoubleSafe(leftLeg, "y")), Math.toRadians(getDoubleSafe(leftLeg, "z"))));
        }
    }

    private static void playEffects(ArmorStand stand, Map<String, Object> step) {
        if (step == null) return;

        Map<String, Object> effects = castMap(step.get("effects"));
        if (effects == null || !effects.containsKey("sound")) return;

        try {
            Sound sound = XSound.valueOf(String.valueOf(effects.get("sound"))).parseSound();
            if (sound != null) stand.getWorld().playSound(stand.getLocation(), sound, 1F, 1F);
        } catch (IllegalArgumentException ex) {
            AdvancedArmorStands.debug("Invalid sound: " + effects.get("sound"));
        }
    }

    public static ArrayList<String> getAnimationsList() {
        ConfigurationSection cs = AnimationConfig.get().getConfigurationSection("animations");
        if (cs == null) return new ArrayList<>();
        return new ArrayList<>(cs.getKeys(false));
    }

    private static double getDoubleSafe(Map<String, Object> map, String key) {
        Object val = map.get(key);

        if (val instanceof Number) return ((Number) val).doubleValue();

        if (val instanceof String) {
            try {
                return Double.parseDouble((String) val);
            } catch (NumberFormatException ignored) {
            }
        }

        return 0D;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> castMap(Object value) {
        if (value instanceof Map) return (Map<String, Object>) value;
        return null;
    }

    private static void cancelAnimationTasks() {
        if (animationTaskIDs.isEmpty()) {
            animationTaskID = -1;
            return;
        }

        for (Integer taskID : animationTaskIDs) {
            Bukkit.getScheduler().cancelTask(taskID);
        }

        AdvancedArmorStands.debug("Canceled " + animationTaskIDs.size() + " animation task(s).");
        animationTaskIDs.clear();
        animationTaskID = -1;
    }

    public static void reloadAnimations() {
        cancelAnimationTasks();
        AnimationConfig.reload();
        init();
    }
}