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
import com.parsa3323.aas.api.events.ArmorStandDeleteEvent;
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

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class ArmorStandUtils {

    public static void setLoadedArmorStands(int loadedArmorStands) {
        ArmorStandUtils.loadedArmorStands = loadedArmorStands;
    }

    public static int getLoadedArmorStands() {
        return loadedArmorStands;
    }

    private static int loadedArmorStands = 0;

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

    public static ArrayList<String> getArmorStandList() {
        FileConfiguration config = ArmorStandsConfig.get();

        ConfigurationSection section = config.getConfigurationSection("armorstands");
        if (section == null) {
            AdvancedArmorStands.debug("'armorstands' section is missing in ArmorStands.yml!");
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
    public static boolean isConfiguredArmorStand(Entity entity) {
        if (!(entity instanceof ArmorStand)) {
            AdvancedArmorStands.debug("Entity is not an ArmorStand: " + entity.getType());
            return false;
        }

        FileConfiguration config = ArmorStandsConfig.get();
        if (!config.contains("armorstands")) {
            AdvancedArmorStands.debug("Config does not contain 'armorstands' section.");
            return false;
        }

        Set<String> keys = config.getConfigurationSection("armorstands").getKeys(false);
        AdvancedArmorStands.debug("Found " + keys.size() + " armor stands in config.");

        for (String key : keys) {
            String path = "armorstands." + key;
            double x = config.getDouble(path + ".X");
            double y = config.getDouble(path + ".Y");
            double z = config.getDouble(path + ".Z");
            String worldName = config.getString(path + ".World");

            AdvancedArmorStands.debug("Checking armor stand: " + key + " at (" + x + ", " + y + ", " + z + ") in world " + worldName);

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
                AdvancedArmorStands.debug("MATCH FOUND! Entity is a configured armor stand.");
                return true;
            }
        }

        AdvancedArmorStands.debug("No match found. Entity is NOT a configured armor stand.");
        return false;
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

    public static void saveArmorStand(String name, ArmorStand armorStand, FileConfiguration c) {
        if (AdvancedArmorStands.plugin.getConfig().getBoolean("auto-load-armor-stands")) {
            ConfigurationSection cs = c.getConfigurationSection("armorstands");

            Location loc = armorStand.getLocation();
//            cs.set(name + ".yaw", loc.getYaw());
//            cs.set(name + ".pitch", loc.getPitch());
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
    }

    public static void checkForArmorStands() {

        ConfigurationSection cs = ArmorStandsConfig.get().getConfigurationSection("armorstands");

        int foundArmorStands = 0;

        int totalArmorStands = getTotalArmorStands();

        AdvancedArmorStands.debug("Starting checkForArmorStands: totalArmorStands = " + totalArmorStands);

        for (String key : getArmorStandList()) {

            AdvancedArmorStands.debug("Checking armor stand key: " + key);

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
                AdvancedArmorStands.debug("No armor stand found near location: " + loc);
            }


        }

        AdvancedArmorStands.debug("Total armor stands expected: " + totalArmorStands + ", Found armor stands: " + foundArmorStands);
        setLoadedArmorStands(foundArmorStands);

        if (totalArmorStands > foundArmorStands) {

            AdvancedArmorStands.warn("It looks like some armor stands haven't been loaded by the world generator. To fix this, " +
                    "enable 'auto-load-armor-stands' in the config to automatically load all armor stands.");


        }

    }

    public static void loadArmorStand(String name) {
        if (AdvancedArmorStands.plugin.getConfig().getBoolean("auto-load-armor-stands")) {
            ConfigurationSection cs = ArmorStandsConfig.get().getConfigurationSection("armorstands");

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

            cs.set(name + ".UUID", armorStand.getUniqueId());

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
