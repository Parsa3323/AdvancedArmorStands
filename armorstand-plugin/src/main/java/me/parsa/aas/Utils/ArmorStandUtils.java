package me.parsa.aas.Utils;

import me.parsa.aas.AdvancedArmorStands;
import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Events.ArmorStandDeleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class ArmorStandUtils {
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



}
