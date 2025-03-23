package me.parsa.aas.Listener;


import me.parsa.aas.Player.IPlayer;
import me.parsa.aas.Player.PlayerManager;
import me.parsa.aas.Utils.ArmorStandUtils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerIntractListener implements Listener {

    @EventHandler
    public void intractWIthE(PlayerInteractAtEntityEvent e) {
        IPlayer player = PlayerManager.getCustomPlayerByBukkit(e.getPlayer());
        if (e.getRightClicked() instanceof ArmorStand) {
            if (ArmorStandUtils.isConfiguredArmorStand(e.getRightClicked())) {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void damge(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof ArmorStand) {
            if (ArmorStandUtils.isConfiguredArmorStand(e.getEntity())) {
                e.setCancelled(true);
            }
        }

    }
}
