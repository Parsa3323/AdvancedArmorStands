/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.parsa3323.aas.listener;

import com.parsa3323.aas.options.CustomNameOption;
import com.parsa3323.aas.options.manager.SettingsManager;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerChat(AsyncPlayerChatEvent e) {

        if (CustomNameOption.players.containsKey(e.getPlayer().getUniqueId())) {

            if (e.getMessage().equalsIgnoreCase("exit")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.GREEN + "Successfully quit the name set session");
                SettingsManager settingsManager = new SettingsManager(new PlayerMenuUtility(e.getPlayer()), CustomNameOption.players.get(e.getPlayer().getUniqueId()), CustomNameOption.IS_FROM_SETTINGS);
                settingsManager.open();
                CustomNameOption.players.remove(e.getPlayer().getUniqueId());
                return;
            }

            e.setCancelled(true);

            CustomNameOption.players.get(e.getPlayer().getUniqueId()).setCustomName(ChatColor.translateAlternateColorCodes('&', e.getMessage()));

            e.getPlayer().sendMessage(ChatColor.GREEN + "Successfully set armor stand's name to '" + e.getMessage() + "' ");
            SettingsManager settingsManager = new SettingsManager(new PlayerMenuUtility(e.getPlayer()), CustomNameOption.players.get(e.getPlayer().getUniqueId()), CustomNameOption.IS_FROM_SETTINGS);
            settingsManager.open();
            CustomNameOption.players.remove(e.getPlayer().getUniqueId());


            return;
        }

    }

}
