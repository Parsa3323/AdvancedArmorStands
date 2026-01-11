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

import com.cryptomorin.xseries.XMaterial;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.data.ArmorStandPoseData;
import com.parsa3323.aas.api.events.ArmorStandDeleteEvent;
import com.parsa3323.aas.api.exeption.ArmorStandLoadException;
import com.parsa3323.aas.api.exeption.ArmorStandNotFoundException;
import com.parsa3323.aas.config.ArmorStandsConfig;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ArmorStandUtils {

    private static final Map<UUID, ArmorStandPoseData> poseMap = new ConcurrentHashMap<>();

    private static boolean isFirstTimeCreatingArmorStand = false;

    public static void setLoadedArmorStands(int loadedArmorStands) {
        ArmorStandUtils.loadedArmorStands = loadedArmorStands;
    }

    public static int getLoadedArmorStands() {
        return loadedArmorStands;
    }

    private static int loadedArmorStands = 0;

    public static boolean isLoaded(ArmorStand armorStand) {
        return armorStand != null;
    }

    public static boolean hasAi(String name) {
        return ArmorStandsConfig.get().getBoolean("armorstands." + name + ".ai");
    }

    public static void setAi(String name, boolean value) {
        ArmorStandsConfig.get().set("armorstands." + name + ".ai", value);

    }

    public static void resetArmorStandPosition(ArmorStand armorStand) {
        String name = getNameByArmorStand(armorStand);
        ConfigurationSection cs = ArmorStandsConfig.get().getConfigurationSection("armorstands." + name);
        if (cs == null) {
            AdvancedArmorStands.debug("No config section found for ArmorStand: " + name);
            return;
        }

        World world = Bukkit.getWorld(cs.getString("World"));
        if (world == null) {
            AdvancedArmorStands.debug("World not found for ArmorStand: " + name);
            return;
        }

        double x = cs.getDouble("X");
        double y = cs.getDouble("Y");
        double z = cs.getDouble("Z");
        float yaw = (float) cs.getDouble("yaw");
        float pitch = (float) cs.getDouble("pitch");

        Location newLoc = new Location(world, x, y, z, yaw, pitch);
        armorStand.teleport(newLoc);

        armorStand.setHeadPose(loadEulerAngle(ArmorStandsConfig.get().getConfigurationSection("armorstands"), name + ".pose.head"));
        armorStand.setBodyPose(loadEulerAngle(ArmorStandsConfig.get().getConfigurationSection("armorstands"), name + ".pose.body"));
        armorStand.setLeftArmPose(loadEulerAngle(ArmorStandsConfig.get().getConfigurationSection("armorstands"), name + ".pose.leftArm"));
        armorStand.setRightArmPose(loadEulerAngle(ArmorStandsConfig.get().getConfigurationSection("armorstands"), name + ".pose.rightArm"));
        armorStand.setLeftLegPose(loadEulerAngle(ArmorStandsConfig.get().getConfigurationSection("armorstands"), name + ".pose.leftLeg"));
        armorStand.setRightLegPose(loadEulerAngle(ArmorStandsConfig.get().getConfigurationSection("armorstands"), name + ".pose.rightLeg"));

        AdvancedArmorStands.debug("ArmorStand " + name + " position reset to " + newLoc);
    }


    public static String getNameByArmorStand(ArmorStand armorStand) {
        FileConfiguration config = ArmorStandsConfig.get();
        if (!config.contains("armorstands")) {
            return null;
        }

        Set<String> keys = config.getConfigurationSection("armorstands").getKeys(false);
        for (String key : keys) {
            String path = "armorstands." + key;
            UUID uuid = UUID.fromString(config.getString(path + ".UUID"));

            if (armorStand.getUniqueId().equals(uuid)) {
                return key;
            }

            World world = Bukkit.getWorld(config.getString(path + ".World"));
            if (world == null) continue;

            double x = config.getDouble(path + ".X");
            double y = config.getDouble(path + ".Y");
            double z = config.getDouble(path + ".Z");
            Location configLocation = new Location(world, x, y, z);

            if (armorStand.getLocation().distanceSquared(configLocation) < 0.01) {
                return key;
            }
        }

        return null;
    }

    public static boolean hasAnimation(ArmorStand armorStand) {
        ConfigurationSection configurationSection = ArmorStandsConfig.get().getConfigurationSection("armorstands");

        if (configurationSection == null) return false;

        return configurationSection.contains(getNameByArmorStand(armorStand) + ".animation");
    }

    public static void savePose(ArmorStand armorStand) {
        ArmorStandPoseData pose = new ArmorStandPoseData(
                armorStand.getRightArmPose(),
                armorStand.getLeftArmPose(),
                armorStand.getRightLegPose(),
                armorStand.getLeftLegPose(),
                armorStand.getHeadPose()
        );
        poseMap.put(armorStand.getUniqueId(), pose);
    }

    public static void removePose(Player player) {
        poseMap.remove(player.getUniqueId());
    }

    public static ArmorStandPoseData getPose(UUID armorStandId) {
        return poseMap.get(armorStandId);
    }

    public static void teleportToArmorStand(Player player, String name) {
        FileConfiguration config = ArmorStandsConfig.get();
        String path = "armorstands." + name;

        if (!config.contains(path)) {
            player.sendMessage(ChatColor.RED + "ArmorStand not found!");
            return;
        }

        World world = Bukkit.getWorld(config.getString(path + ".World"));
        double x = config.getDouble(path + ".X");
        double y = config.getDouble(path + ".Y");
        double z = config.getDouble(path + ".Z");

        if (world == null) {
            player.sendMessage(ChatColor.RED + "World not found!");
            return;
        }

        player.teleport(new Location(world, x, y, z));
        player.sendMessage(ChatColor.GREEN + "Teleported to ArmorStand: " + name);
    }

    public static boolean exists(String name) {
        return ArmorStandsConfig.get().contains("armorstands." + name);
    }

    public static boolean exists(ArmorStand armorStand) {
        return getNameByArmorStand(armorStand) != null;
    }

    public static ArrayList<String> getArmorStandList() {
        FileConfiguration config = ArmorStandsConfig.get();

        ConfigurationSection section = config.getConfigurationSection("armorstands");
        if (section == null) {
            AdvancedArmorStands.debug("'ArmorStands' section is missing in ArmorStands.yml!");
            return new ArrayList<>();
        }
        return new ArrayList<>(section.getKeys(false));
    }

    public static void deleteArmorStand(String name, Player player) {
        FileConfiguration config = ArmorStandsConfig.get();
        String path = "armorstands." + name;

        if (!config.contains(path)) {
            player.sendMessage(ChatColor.RED + "ArmorStand not found!");
            return;
        }

        UUID uuid = UUID.fromString(config.getString(path + ".UUID"));
        World world = Bukkit.getWorld(config.getString(path + ".World"));

        if (world == null) {
            player.sendMessage(ChatColor.RED + "World not found!");
            return;
        }



        for (Entity entity : world.getEntities()) {
            if (entity instanceof ArmorStand && entity.getUniqueId().equals(uuid)) {
                ArmorStandDeleteEvent armorStandDeleteEvent = new ArmorStandDeleteEvent(player,(ArmorStand) entity);
                Bukkit.getPluginManager().callEvent(armorStandDeleteEvent);
                if (armorStandDeleteEvent.isCancelled()) {
                    return;
                }
                entity.remove();
                break;
            }
        }


        config.set(path, null);
        ArmorStandsConfig.save();

        player.sendMessage(ChatColor.GREEN + "Deleted ArmorStand: " + name);
    }

    public static void deleteArmorStandNoLog(String name) throws ArmorStandNotFoundException {
        FileConfiguration config = ArmorStandsConfig.get();
        String path = "armorstands." + name;

        if (!config.contains(path)) {
            throw new ArmorStandNotFoundException("ArmorStand not found!");
        }

        UUID uuid = UUID.fromString(config.getString(path + ".UUID"));
        World world = Bukkit.getWorld(config.getString(path + ".World"));

        if (world == null) {
            throw new ArmorStandNotFoundException("World not found!");
        }



        for (Entity entity : world.getEntities()) {
            if (entity instanceof ArmorStand && entity.getUniqueId().equals(uuid)) {
                entity.remove();
                break;
            }
        }


        config.set(path, null);
        ArmorStandsConfig.save();
    }

    public static boolean isIsFirstTimeCreatingArmorStand() {
        return isFirstTimeCreatingArmorStand;
    }

    public static void setIsFirstTimeCreatingArmorStand(boolean isFirstTimeCreatingArmorStand) {
        ArmorStandUtils.isFirstTimeCreatingArmorStand = isFirstTimeCreatingArmorStand;
    }

    public static boolean isConfiguredArmorStand(Entity entity) {
        if (!(entity instanceof ArmorStand)) {
            AdvancedArmorStands.debug("Entity is not an ArmorStand: " + entity.getType());
            return false;
        }

        FileConfiguration config = ArmorStandsConfig.get();
        if (!config.contains("armorstands")) {
            AdvancedArmorStands.debug("Config does not contain 'ArmorStands' section.");
            return false;
        }

        Set<String> keys = config.getConfigurationSection("armorstands").getKeys(false);
        AdvancedArmorStands.debug("Found " + keys.size() + " ArmorStands in config.");

        for (String key : keys) {
            String path = "armorstands." + key;
            double x = config.getDouble(path + ".X");
            double y = config.getDouble(path + ".Y");
            double z = config.getDouble(path + ".Z");
            String worldName = config.getString(path + ".World");

            AdvancedArmorStands.debug("Checking ArmorStand: " + key + " at (" + x + ", " + y + ", " + z + ") in world " + worldName);

            if (worldName == null) {
                AdvancedArmorStands.debug("[ERROR] Missing world name for " + key + " in config!");
                continue;
            }

            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                AdvancedArmorStands.debug("[ERROR] Invalid world name: " + worldName);
                continue;
            }

            Location configLocation = new Location(world, x, y, z);
            Location entityLocation = entity.getLocation();
            double distance = entityLocation.distanceSquared(configLocation);

            AdvancedArmorStands.debug("Entity location: (" + entityLocation.getX() + ", " + entityLocation.getY() + ", " + entityLocation.getZ() + ")");
            AdvancedArmorStands.debug("Distance squared: " + distance);

            if (distance < 0.01) {
                AdvancedArmorStands.debug("MATCH FOUND! Entity is a configured ArmorStand.");
                return true;
            }
        }

        AdvancedArmorStands.debug("No match found. Entity is NOT a configured ArmorStand.");
        return false;
    }

    public static void checkArmorStandsFirstTime() {
        if (getArmorStandList().isEmpty()) {
            setIsFirstTimeCreatingArmorStand(true);
        }
    }

    public static ArmorStand getArmorStandByName(String s) {

        FileConfiguration config = ArmorStandsConfig.get();
        String path = "armorstands." + s;

        if (!config.contains(path)) {
            AdvancedArmorStands.error("ArmorStand not found!", true);
            return null;
        }

        UUID uuid = UUID.fromString(config.getString(path + ".UUID"));
        World world = Bukkit.getWorld(config.getString(path + ".World"));

        if (world == null) {
            AdvancedArmorStands.error("World not found!", true);
            return null;
        }


        for (Entity entity : world.getEntities()) {
            if (entity instanceof ArmorStand && entity.getUniqueId().equals(uuid)) {
                return (ArmorStand) entity;
            }
        }

        return null;
    }

    public static String findRealCase(String name) {
        FileConfiguration config = ArmorStandsConfig.get();
        ConfigurationSection section = config.getConfigurationSection("armorstands");

        if (section == null) return null;

        for (String key : section.getKeys(false)) {
            if (key.equalsIgnoreCase(name)) {
                return key;
            }
        }

        return null;
    }

    public static int getTotalArmorStands() {
        FileConfiguration config = ArmorStandsConfig.get();

        if (null == config.getConfigurationSection("armorstands")) return 0;

        Set<String> keys = config.getConfigurationSection("armorstands").getKeys(false);


        return keys.size();
    }


    public static boolean isValidEquipmentForSlot(ItemStack item, int slot) {
        Material mat = item.getType();
        switch (slot) {
            case 4: return mat == Material.AIR || canBeHelmet(mat);
            case 13: return mat == Material.AIR || canBeChestplate(mat);
            case 22: return mat == Material.AIR || canBeLeggings(mat);
            case 31: return mat == Material.AIR || canBeBoots(mat);
            case 39: return true;
            case 40: return true;
            default: return false;
        }
    }

    public static boolean canBeHelmet(Material mat) {
        return mat.name().endsWith("_HELMET") ||
                mat == XMaterial.PUMPKIN.parseMaterial() ||
                mat == XMaterial.PLAYER_HEAD.parseMaterial();
    }

    public static void saveArmorStand(String name, ArmorStand armorStand) {
        ConfigurationSection cs = ArmorStandsConfig.get().getConfigurationSection("armorstands");
        ConfigurationSection qs = ArmorStandsConfig.get().getConfigurationSection("armorstands." + name);

        qs.set("equipment.helmet", (armorStand.getHelmet() != null) ? armorStand.getHelmet() : XMaterial.AIR.parseMaterial());
        qs.set("equipment.chestplate", (armorStand.getChestplate() != null) ? armorStand.getChestplate() : XMaterial.AIR.parseMaterial());
        qs.set("equipment.leggings", (armorStand.getLeggings() != null) ? armorStand.getLeggings() : XMaterial.AIR.parseMaterial());
        qs.set("equipment.boots", (armorStand.getBoots() != null) ? armorStand.getBoots() : XMaterial.AIR.parseMaterial());
        qs.set("equipment.hand", (armorStand.getItemInHand() != null) ? armorStand.getItemInHand() : XMaterial.AIR.parseMaterial());

        Location loc = armorStand.getLocation();
//            cs.set(name + ".yaw", loc.getYaw());
//            cs.set(name + ".pitch", loc.getPitch());

        if (VersionSupportUtil.getVersionSupport().canGlow()) {

            cs.set(name + ".glowing", VersionSupportUtil.getVersionSupport().isGlowing(armorStand));

        }

        cs.set(name + ".small", armorStand.isSmall());
        cs.set(name + ".gravity", armorStand.hasGravity());
        cs.set(name + ".visible", armorStand.isVisible());
        cs.set(name + ".baseplate", armorStand.hasBasePlate());
        cs.set(name + ".marker", armorStand.isMarker());
        cs.set(name + ".arms", armorStand.hasArms());
        cs.set(name + ".customName", armorStand.getCustomName());
        cs.set(name + ".customNameVisible", armorStand.isCustomNameVisible());

        saveEulerAngle(cs, name + ".pose.head", armorStand.getHeadPose());
        saveEulerAngle(cs, name + ".pose.body", armorStand.getBodyPose());
        saveEulerAngle(cs, name + ".pose.leftArm", armorStand.getLeftArmPose());
        saveEulerAngle(cs, name + ".pose.rightArm", armorStand.getRightArmPose());
        saveEulerAngle(cs, name + ".pose.leftLeg", armorStand.getLeftLegPose());
        saveEulerAngle(cs, name + ".pose.rightLeg", armorStand.getRightLegPose());

        ArmorStandsConfig.save();
    }

    public static void checkForArmorStands() {

        ConfigurationSection cs = ArmorStandsConfig.get().getConfigurationSection("armorstands");

        int foundArmorStands = 0;

        int totalArmorStands = getTotalArmorStands();

        AdvancedArmorStands.debug("Starting checkForArmorStands: totalArmorStands = " + totalArmorStands);

        for (String key : getArmorStandList()) {

            AdvancedArmorStands.debug("Checking ArmorStand key: " + key);

            double x = cs.getDouble(key + ".X");

            double y = cs.getDouble(key + ".Y");

            double z = cs.getDouble(key + ".Z");

            float yaw = (float) cs.getDouble(key + ".yaw");
            float pitch = (float) cs.getDouble(key + ".pitch");

            World world = Bukkit.getWorld(cs.getString(key + ".World"));

            AdvancedArmorStands.debug("Location read from config: World=" + cs.getString(key + ".World") + ", X=" + x + ", Y=" + y + ", Z=" + z);

            if (world == null) {
                AdvancedArmorStands.debug("World " + cs.getString(key + ".World") + " is not loaded or null!");
                continue;
            }

            Location loc = new Location(world, x, y, z, yaw, pitch);

            if (!world.isChunkLoaded(loc.getChunk())) {
                AdvancedArmorStands.debug("Chunk at location " + loc + " is not loaded!");
            } else {
                AdvancedArmorStands.debug("Chunk at location " + loc + " is loaded.");
            }


            double radius = 0.1;

            boolean foundHere = false;


            for (Entity entity : world.getNearbyEntities(loc, radius, radius, radius)) {
                if (entity instanceof ArmorStand) {
                    Location entityLoc = entity.getLocation();

                    double tolerance = 0.2;
                    if (Math.abs(entityLoc.getX() - x) < tolerance &&
                            Math.abs(entityLoc.getY() - y) < tolerance &&
                            Math.abs(entityLoc.getZ() - z) < tolerance) {
                        foundArmorStands++;
                        AdvancedArmorStands.debug("ArmorStand exists at location: " + loc);
                        foundHere = true;
                        break;
                    }
                }
            }

            if (!foundHere) {
                AdvancedArmorStands.debug("No ArmorStands found near location: " + loc);
            }


        }

        AdvancedArmorStands.debug("Total ArmorStands expected: " + totalArmorStands + ", Found ArmorStands: " + foundArmorStands);
        setLoadedArmorStands(foundArmorStands);

        if (totalArmorStands > foundArmorStands) {

            AdvancedArmorStands.warn("It looks like some ArmorStands haven't been loaded by the world generator. To fix this, " +
                    "enable 'auto-load-armor-stands' in the config to automatically load all ArmorStands.");


        }

    }

    public static void loadArmorStand(String name) throws ArmorStandLoadException {
        try {
            ConfigurationSection cs = ArmorStandsConfig.get().getConfigurationSection("armorstands");
            ConfigurationSection qs = ArmorStandsConfig.get().getConfigurationSection("armorstands." + name);

            World world = Bukkit.getWorld(cs.getString(name + ".World"));
            if (world == null) {
                return;
            }

            double x = cs.getDouble(name + ".X");
            double y = cs.getDouble(name + ".Y");
            double z = cs.getDouble(name + ".Z");
            float yaw = (float) cs.getDouble(name + ".yaw");
            float pitch = (float) cs.getDouble(name + ".pitch");

            Location loc = new Location(world, x, y, z, yaw, pitch);

            double radius = 0.1;

            for (Entity entity : world.getNearbyEntities(loc, radius, radius, radius)) {
                if (entity instanceof ArmorStand) {
                    Location entityLoc = entity.getLocation();

                    double tolerance = 0.2;
                    if (Math.abs(entityLoc.getX() - x) < tolerance &&
                            Math.abs(entityLoc.getY() - y) < tolerance &&
                            Math.abs(entityLoc.getZ() - z) < tolerance) {
                        AdvancedArmorStands.debug("ArmorStand exists at location: " + loc);
                        return;
                    }
                }
            }


            ArmorStand armorStand = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);

            cs.set(name + ".UUID", armorStand.getUniqueId().toString());

            armorStand.setItemInHand(qs.getItemStack("equipment.hand"));
            armorStand.setBoots(qs.getItemStack("equipment.boots"));
            armorStand.setHelmet(qs.getItemStack("equipment.helmet"));
            armorStand.setChestplate(qs.getItemStack("equipment.chestplate"));
            armorStand.setLeggings(qs.getItemStack("equipment.leggings"));

            if (VersionSupportUtil.getVersionSupport().canGlow()) {

                VersionSupportUtil.getVersionSupport().setGlowing(armorStand, cs.getBoolean(name + ".glowing"));

            }

            armorStand.setSmall(cs.getBoolean(name + ".small"));
            armorStand.setGravity(cs.getBoolean(name + ".gravity"));
            armorStand.setVisible(cs.getBoolean(name + ".visible"));
            armorStand.setBasePlate(cs.getBoolean(name + ".baseplate"));
            armorStand.setMarker(cs.getBoolean(name + ".marker"));
            armorStand.setArms(cs.getBoolean(name + ".arms"));
            AdvancedArmorStands.debug(String.valueOf(cs.getBoolean(name + ".arms")));
            armorStand.setCustomName(cs.getString(name + ".customName"));
            armorStand.setCustomNameVisible(cs.getBoolean(name + ".customNameVisible"));

            armorStand.setHeadPose(loadEulerAngle(cs, name + ".pose.head"));
            armorStand.setBodyPose(loadEulerAngle(cs, name + ".pose.body"));
            armorStand.setLeftArmPose(loadEulerAngle(cs, name + ".pose.leftArm"));
            armorStand.setRightArmPose(loadEulerAngle(cs, name + ".pose.rightArm"));
            armorStand.setLeftLegPose(loadEulerAngle(cs, name + ".pose.leftLeg"));
            armorStand.setRightLegPose(loadEulerAngle(cs, name + ".pose.rightLeg"));
        } catch (Exception e) {
            throw new ArmorStandLoadException(e);
        }


    }

    public static boolean shouldTeleport(Player player, ArmorStand stand, double minDistance) {
        double distance = player.getLocation().distance(stand.getLocation());

        return distance > minDistance;
    }


    public static void teleportPlayerInFrontOfStand(Player player, ArmorStand stand, double distanceInFront) {
        Location standLoc = stand.getLocation().clone();

        Vector direction = standLoc.getDirection().normalize();

        Location target = standLoc.add(direction.multiply(distanceInFront));

        Vector lookAt = stand.getLocation().toVector().subtract(target.toVector());
        lookAt.setY(0);

        target.setDirection(lookAt);

        player.teleport(target);
    }



    public static void autoLoadArmorStand(String name) {
        if (AdvancedArmorStands.plugin.getConfig().getBoolean("auto-load-armor-stands")) {
            ConfigurationSection cs = ArmorStandsConfig.get().getConfigurationSection("armorstands");
            ConfigurationSection qs = ArmorStandsConfig.get().getConfigurationSection("armorstands." + name);

            World world = Bukkit.getWorld(cs.getString(name + ".World"));
            if (world == null) {
                return;
            }

            double x = cs.getDouble(name + ".X");
            double y = cs.getDouble(name + ".Y");
            double z = cs.getDouble(name + ".Z");
            float yaw = (float) cs.getDouble(name + ".yaw");
            float pitch = (float) cs.getDouble(name + ".pitch");

            Location loc = new Location(world, x, y, z, yaw, pitch);

            double radius = 0.1;

            for (Entity entity : world.getNearbyEntities(loc, radius, radius, radius)) {
                if (entity instanceof ArmorStand) {
                    Location entityLoc = entity.getLocation();

                    double tolerance = 0.2;
                    if (Math.abs(entityLoc.getX() - x) < tolerance &&
                            Math.abs(entityLoc.getY() - y) < tolerance &&
                            Math.abs(entityLoc.getZ() - z) < tolerance) {
                        AdvancedArmorStands.debug("ArmorStand exists at location: " + loc);
                        return;
                    }
                }
            }


            ArmorStand armorStand = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);

            cs.set(name + ".UUID", armorStand.getUniqueId().toString());

            armorStand.setItemInHand(qs.getItemStack("equipment.hand"));
            armorStand.setBoots(qs.getItemStack("equipment.boots"));
            armorStand.setHelmet(qs.getItemStack("equipment.helmet"));
            armorStand.setChestplate(qs.getItemStack("equipment.chestplate"));
            armorStand.setLeggings(qs.getItemStack("equipment.leggings"));

            if (VersionSupportUtil.getVersionSupport().canGlow()) {

                VersionSupportUtil.getVersionSupport().setGlowing(armorStand, cs.getBoolean(name + ".glowing"));

            }

            armorStand.setSmall(cs.getBoolean(name + ".small"));
            armorStand.setGravity(cs.getBoolean(name + ".gravity"));
            armorStand.setVisible(cs.getBoolean(name + ".visible"));
            armorStand.setBasePlate(cs.getBoolean(name + ".baseplate"));
            armorStand.setMarker(cs.getBoolean(name + ".marker"));
            armorStand.setArms(cs.getBoolean(name + ".arms"));
            AdvancedArmorStands.debug(String.valueOf(cs.getBoolean(name + ".arms")));
            armorStand.setCustomName(cs.getString(name + ".customName"));
            armorStand.setCustomNameVisible(cs.getBoolean(name + ".customNameVisible"));

            armorStand.setHeadPose(loadEulerAngle(cs, name + ".pose.head"));
            armorStand.setBodyPose(loadEulerAngle(cs, name + ".pose.body"));
            armorStand.setLeftArmPose(loadEulerAngle(cs, name + ".pose.leftArm"));
            armorStand.setRightArmPose(loadEulerAngle(cs, name + ".pose.rightArm"));
            armorStand.setLeftLegPose(loadEulerAngle(cs, name + ".pose.leftLeg"));
            armorStand.setRightLegPose(loadEulerAngle(cs, name + ".pose.rightLeg"));
        }

    }


    public static ArrayList<String> getArmorStandsWithAnimation() {

        ArrayList<String> list = new ArrayList<>();

        getArmorStandList().forEach(s -> {
            if (ArmorStandsConfig.get().contains("armorstands." + s + ".animation")) {
                list.add(s);
            }
        });

        return list;

    }

    private static void saveEulerAngle(ConfigurationSection config, String path, EulerAngle angle) {
        config.set(path + ".x", Math.toDegrees(angle.getX()));
        config.set(path + ".y", Math.toDegrees(angle.getY()));
        config.set(path + ".z", Math.toDegrees(angle.getZ()));
    }

    private static EulerAngle loadEulerAngle(ConfigurationSection config, String path) {
        double x = Math.toRadians(config.getDouble(path + ".x"));
        double y = Math.toRadians(config.getDouble(path + ".y"));
        double z = Math.toRadians(config.getDouble(path + ".z"));
        return new EulerAngle(x, y, z);
    }

    public static boolean canBeChestplate(Material mat) {
        return mat.name().endsWith("_CHESTPLATE");
    }

    public static boolean canBeLeggings(Material mat) {
        return mat.name().endsWith("_LEGGINGS");
    }

    public static boolean canBeBoots(Material mat) {
        return mat.name().endsWith("_BOOTS");
    }

}
