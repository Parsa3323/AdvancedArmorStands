package me.parsa.aas;

import me.parsa.aas.VersionSupport.IVersionSupport;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public final class VersionSupprt_1_8 implements IVersionSupport {


    @Override
    public void playSound(Player player, Location location, String soundName, float volume, float pitch) {
        try {
            Sound sound = Sound.valueOf(soundName);
            player.playSound(location, sound, volume, pitch);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Sound " + soundName + " not found in this version!");
        }
    }

    @Override
    public Material getMaterialForVersion(String s) {
        try {
            Material sound = Material.valueOf(s);
            return sound;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }

    }
}
