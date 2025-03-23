package me.parsa.aas.Player;

import me.parsa.aas.AdvancedArmorStands;
import org.bukkit.entity.Player;

public class CustomPlayer implements IPlayer{

    private final Player bukkitPlayer;

    public CustomPlayer(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
    }

    @Override
    public Player getBukkitPlayer() {
        return this.bukkitPlayer;
    }

    @Override
    public void playSound(String s) {
        AdvancedArmorStands.plugin.playVersionSpecificSound(bukkitPlayer, bukkitPlayer.getLocation(), s, 1, 1);
    }
}
