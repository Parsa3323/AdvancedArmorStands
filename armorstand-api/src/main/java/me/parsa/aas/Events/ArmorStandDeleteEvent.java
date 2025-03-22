package me.parsa.aas.Events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArmorStandDeleteEvent extends Event {


    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;

    Player player;

    public Player getPlayer() {
        return player;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    ArmorStand armorStand;

    public ArmorStandDeleteEvent(Player player, ArmorStand armorStand) {
        this.player = player;
        this.armorStand = armorStand;
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
