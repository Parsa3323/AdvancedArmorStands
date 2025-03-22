package me.parsa.aas.Events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArmorStandCreateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;

    Player player;

    ArmorStand armorStand;

    String name;

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public ArmorStandCreateEvent(Player player, ArmorStand armorStand, String name) {
        this.armorStand = armorStand;
        this.player = player;
        this.name = name;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }
}
