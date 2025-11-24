package com.parsa3323.aas.listener;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class BookInputListener {

    private final Map<UUID, Consumer<String>> waiting = new HashMap<>();

    public void waitFor(Player p, Consumer<String> callback) {
        waiting.put(p.getUniqueId(), callback);
    }

    public void register(Plugin plugin) {
        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.CUSTOM_PAYLOAD) {

                    @Override
                    public void onPacketReceiving(PacketEvent event) {
                        if (!event.getPacket().getStrings().read(0).equalsIgnoreCase("MC|BEdit"))
                            return;

                        Player p = event.getPlayer();
                        Consumer<String> callback = waiting.remove(p.getUniqueId());
                        if (callback == null) return;

                        ItemStack book = event.getPacket().getItemModifier().read(0);
                        if (!(book.getItemMeta() instanceof BookMeta)) return;

                        BookMeta meta = (BookMeta) book.getItemMeta();
                        String text = String.join("\n", meta.getPages());

                        callback.accept(text);
                    }
                }
        );
    }
}
