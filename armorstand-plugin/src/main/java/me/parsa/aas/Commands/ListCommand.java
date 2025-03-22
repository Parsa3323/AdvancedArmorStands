package me.parsa.aas.Commands;

import me.parsa.aas.Commands.Manager.SubCommand;
import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Events.ArmorStandDeleteEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

public class ListCommand extends SubCommand {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Shows a list of armor stands";
    }

    @Override
    public String getSyntax() {
        return "/as list";
    }

    @Override
    public void perform(Player player, String[] args) {
        ArrayList<String> armorStandList = getArmorStandList();

        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1.0f, 0.5f); // Play a sound when checking

        if (armorStandList.isEmpty()) {
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "✖ No ArmorStands found! ✖");
            player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f); // Sad sound
        } else {
            player.sendMessage(ChatColor.DARK_GRAY + "§m----------------------------------");
            player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  ✦ Saved ArmorStands ✦");
            player.sendMessage(ChatColor.DARK_GRAY + "§m----------------------------------");

            for (String name : armorStandList) {

                TextComponent textComponent = new TextComponent(ChatColor.YELLOW + "➤ " + ChatColor.GREEN + name);
                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + name).create()));
                TextComponent deleteButton = new TextComponent(ChatColor.RED + "" + ChatColor.BOLD + " [DELETE] ");
                deleteButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Delete armorstand").create()));
                deleteButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as delete " + name));
                TextComponent teleportButton = new TextComponent(ChatColor.AQUA + "" + ChatColor.BOLD + "[TELEPORT]");
                teleportButton.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + "Teleport to armorstand").create()));
                teleportButton.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/as teleport " + name));

                player.spigot().sendMessage(textComponent, deleteButton, teleportButton);
            }

            player.sendMessage(ChatColor.DARK_GRAY + "§m----------------------------------");
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.5f); // Success sound
        }


    }

    @Override
    public java.util.List<String> getTabComplete(Player player, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean isForOps() {
        return false;
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



        // Find and remove the ArmorStand
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

        // Remove from config
        config.set(path, null);
        ArmorStands.save();

        player.sendMessage(ChatColor.GREEN + "Deleted ArmorStand: " + name);
    }


}
