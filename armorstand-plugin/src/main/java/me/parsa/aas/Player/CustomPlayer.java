package me.parsa.aas.Player;

import me.parsa.aas.Utils.VersionSupportUtil;
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
        VersionSupportUtil.playVersionSpecificSound(bukkitPlayer, bukkitPlayer.getLocation(), s, 1, 1);
    }

    @Override
    public boolean isAdmin() {
        return this.bukkitPlayer.hasPermission("advanced-armorstands.admin");
    }
}
