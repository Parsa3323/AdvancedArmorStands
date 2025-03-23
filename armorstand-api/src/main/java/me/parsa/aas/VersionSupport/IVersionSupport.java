package me.parsa.aas.VersionSupport;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IVersionSupport {

    void playSound(Player player, Location location, String soundName, float volume, float pitch);


}
