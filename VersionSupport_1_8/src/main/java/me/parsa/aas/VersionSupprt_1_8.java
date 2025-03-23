package me.parsa.aas;

import me.parsa.aas.VersionSupport.IVersionSupport;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class VersionSupprt_1_8 implements IVersionSupport {


    @Override
    public void playSound(Player player, Location location, String soundName, float volume, float pitch) {
        try {
            Sound sound = Sound.valueOf(soundName); // Use sound enum, which may have new values in 1.21.1
            player.playSound(location, sound, volume, pitch);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Sound " + soundName + " not found in this version!");
        }
    }
}
