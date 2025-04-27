/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.Q
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.parsa3323.aas.Listener;

import com.parsa3323.aas.Options.CustomNameOption;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent e) {

        if (CustomNameOption.players.containsKey(e.getPlayer().getUniqueId())) {

            if (e.getMessage().equalsIgnoreCase("exit")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Successfully quit the name set session");
                CustomNameOption.players.remove(e.getPlayer().getUniqueId());
                return;
            }

            e.setCancelled(true);

            CustomNameOption.players.get(e.getPlayer().getUniqueId()).setCustomName(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
            CustomNameOption.players.remove(e.getPlayer().getUniqueId());

            e.getPlayer().sendMessage(ChatColor.GREEN + "Successfully set armor stand's name to '" + e.getMessage() + "' ");
        }

    }

}
