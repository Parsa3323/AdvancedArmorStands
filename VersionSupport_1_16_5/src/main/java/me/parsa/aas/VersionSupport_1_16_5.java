package me.parsa.aas;

import me.parsa.aas.VersionSupport.IVersionSupport;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public final class VersionSupport_1_16_5 implements IVersionSupport {


    @Override
    public void playSound(Player player, Location location, String soundName, float volume, float pitch) {
        try {
            Sound sound = null;
            switch (soundName) {
                case "NOTE_PLING":
                    sound = Sound.BLOCK_NOTE_BLOCK_PLING;
                    break;
                case "NOTE_BASS":
                    sound  = Sound.BLOCK_NOTE_BLOCK_BASS;
                    break;
                case "VILLAGER_NO":
                    sound = Sound.ENTITY_VILLAGER_NO;
                    break;
                case "ORB_PICKUP":
                    sound = Sound.ENTITY_EXPERIENCE_ORB_PICKUP;
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Unknown sound: " + soundName);
                    return;
            }
            player.playSound(location, sound, volume, pitch);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Sound " + soundName + " not found in this version!");
        }
    }

}
