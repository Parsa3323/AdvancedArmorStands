package me.parsa.aas.Player;

import org.bukkit.entity.Player;

public interface IPlayer {

    Player getBukkitPlayer();

    void playSound(String s);
}
