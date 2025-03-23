package me.parsa.aas.Menus.Manager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem() == null) {
            return;
        }


        InventoryHolder holder = e.getClickedInventory().getHolder();
        if (holder instanceof Menu) {

            Menu menu = (Menu) holder;

            if (menu.cancelClicks()) {
                e.setCancelled(true);
            }






            menu.handleMenu(e);
        }
    }
}