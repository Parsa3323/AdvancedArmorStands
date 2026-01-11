package com.parsa3323.aas.listener;


import com.parsa3323.aas.ai.MemoryOption;
import com.parsa3323.aas.ai.manager.AiSettingsManager;
import com.parsa3323.aas.utils.AiUtils;
import com.parsa3323.aas.utils.InventoryUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
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

        if (text.equalsIgnoreCase("exit")) {
            InventoryUtils.restore(e.getPlayer());
            p.sendMessage(ChatColor.GREEN + "Successfully exited memory set session.");
            AiSettingsManager aiSettingsManager = new AiSettingsManager(new PlayerMenuUtility(e.getPlayer()), waiting.get(p.getUniqueId()));
            aiSettingsManager.open();
            return;
        }

        AiUtils.setUserSetInstructions(waiting.get(p.getUniqueId()), text);
        InventoryUtils.restore(e.getPlayer());
        AiSettingsManager aiSettingsManager = new AiSettingsManager(new PlayerMenuUtility(e.getPlayer()), waiting.get(p.getUniqueId()));
        aiSettingsManager.open();
        p.sendMessage(ChatColor.GREEN + "Successfully updated ArmorStand's instructions");

        waiting.remove(p.getUniqueId());
    }

}
