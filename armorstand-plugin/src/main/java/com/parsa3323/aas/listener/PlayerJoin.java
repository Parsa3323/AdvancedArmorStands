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

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.player.PlayerManager;
import com.parsa3323.aas.utils.ArmorStandUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        if (PlayerManager.getCustomPlayerByBukkit(e.getPlayer()).isAdmin()) {

            AdvancedArmorStands.debug(e.getPlayer() + " is an advanced armor stand admin");
            AdvancedArmorStands.debug("Total: " + ArmorStandUtils.getTotalArmorStands() + ", Found: " + ArmorStandUtils.getLoadedArmorStands());

            if (ArmorStandUtils.getLoadedArmorStands() < ArmorStandUtils.getTotalArmorStands()) {
                int unloaded = ArmorStandUtils.getTotalArmorStands() - ArmorStandUtils.getLoadedArmorStands();

                e.getPlayer().sendMessage(ChatColor.RED + "It looks like " + unloaded + " armor stands haven't been" +
                        " loaded by the world generator. To fix this, " +
                        "enable 'auto-load-armor-stands' in the config to automatically load all armor stands.");

            }
        }
    }
}
