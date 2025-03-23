package me.parsa.aas.Player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private static final Map<Player, IPlayer> playerMap = new HashMap<>();

    public static IPlayer getCustomPlayerByBukkit(Player player) {
        return playerMap.computeIfAbsent(player, CustomPlayer::new);
    }

    public static void removeCustomPlayer(Player player) {
        playerMap.remove(player);
    }
}
