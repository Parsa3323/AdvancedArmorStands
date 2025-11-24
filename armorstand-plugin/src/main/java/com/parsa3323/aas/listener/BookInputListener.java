package com.parsa3323.aas.listener;


import com.parsa3323.aas.ai.MemoryOption;
import com.parsa3323.aas.utils.AiUtils;
import com.parsa3323.aas.utils.InventoryUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Map;
import java.util.UUID;

public class BookInputListener implements Listener {

    @EventHandler
    public void onBookEdit(PlayerEditBookEvent e) {
        Map<UUID, ArmorStand> waiting = MemoryOption.waiting;

        Player p = e.getPlayer();
        if (!waiting.containsKey(p.getUniqueId())) return;

        BookMeta meta = e.getNewBookMeta();
        String text = String.join("\n", meta.getPages());

        AiUtils.setUserSetInstructions(waiting.get(p.getUniqueId()), text);

        waiting.remove(p.getUniqueId());

        System.out.println(text);
        p.sendMessage(ChatColor.GREEN + "Successfully updated armor stand's instructions");
        p.getInventory().setItemInHand(InventoryUtils.getOldHandItem(e.getPlayer()));

    }

}
