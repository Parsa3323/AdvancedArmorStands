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

package com.parsa3323.aas.Utils;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.Configs.ArmorStands;
import com.parsa3323.aas.Events.ArmorStandDeleteEvent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ArmorStandUtils {

    public static List<ArmorStand> getArmorStands() {
        List<ArmorStand> armorStands = new ArrayList<>();
        FileConfiguration config = ArmorStands.get();

        if (!config.contains("armorstands")) {
            return armorStands;
        }

        Set<String> keys = config.getConfigurationSection("armorstands").getKeys(false);

        for (String key : keys) {
            String path = "armorstands." + key;
            UUID uuid = UUID.fromString(config.getString(path + ".UUID"));
            String worldName = config.getString(path + ".World");

            if (worldName == null) continue;

            World world = Bukkit.getWorld(worldName);
            if (world == null) continue;

            for (Entity entity : world.getEntities()) {
                if (entity instanceof ArmorStand && entity.getUniqueId().equals(uuid)) {
                    armorStands.add((ArmorStand) entity);
                    break;
                }
            }
        }

        return armorStands;
    }


    public static String getNameByArmorStand(ArmorStand armorStand) {
        FileConfiguration config = ArmorStands.get();
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
        FileConfiguration config = ArmorStands.get();
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
        FileConfiguration config = ArmorStands.get();
        Set<String> keys = config.getConfigurationSection("armorstands").getKeys(false);
        return new ArrayList<>(keys);
    }

    public static void deleteArmorStand(String name, Player player) {
        FileConfiguration config = ArmorStands.get();
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
        ArmorStands.save();

        player.sendMessage(ChatColor.GREEN + "Deleted ArmorStand: " + name);
    }
    public static boolean isConfiguredArmorStand(Entity entity) {
        if (!(entity instanceof ArmorStand)) {
            AdvancedArmorStands.debug("Entity is not an ArmorStand: " + entity.getType());
            return false;
        }

        FileConfiguration config = ArmorStands.get();
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

        FileConfiguration config = ArmorStands.get();
        String path = "armorstands." + s;

        if (!config.contains(path)) {
            AdvancedArmorStands.error(ChatColor.RED + "ArmorStand not found!");
            return null;
        }

        UUID uuid = UUID.fromString(config.getString(path + ".UUID"));
        World world = Bukkit.getWorld(config.getString(path + ".World"));

        if (world == null) {
            AdvancedArmorStands.error(ChatColor.RED + "World not found!");
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
        FileConfiguration config = ArmorStands.get();
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
            case 40: return true;
            default: return false;
        }
    }

    public static boolean canBeHelmet(Material mat) {
        return mat.name().endsWith("_HELMET") ||
                mat == Material.PUMPKIN ||
                mat == Material.SKULL_ITEM;
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
