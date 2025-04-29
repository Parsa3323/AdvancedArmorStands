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

package com.parsa3323.aas;

import com.parsa3323.aas.Configs.ArmorStands;
import com.parsa3323.aas.Configs.TypesConfig;
import com.parsa3323.aas.Menus.ArmorStandMenu;
import com.parsa3323.aas.Utils.ArmorStandSelectionCache;
import com.parsa3323.aas.Utils.ArmorStandUtils;
import com.parsa3323.aas.Utils.InventoryUtils;
import com.parsa3323.aas.Utils.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.UUID;

public class API implements ArmorstandApi{
    @Override
    public ConfigManager getConfigManager() {
        return new ConfigManager() {
            @Override
            public FileConfiguration getMainConfig() {
                return AdvancedArmorStands.plugin.getConfig();
            }

            @Override
            public FileConfiguration getCacheConfig() {
                return ArmorStands.get();
            }

            @Override
            public FileConfiguration getTypesConfig() {
                return TypesConfig.get();
            }
        };
    }
    @Override
    public SessionManager getSessionManager() {
        return new SessionManager() {
            @Override
            public void closeSession(UUID uuid) {
                InventoryUtils.restore(Bukkit.getPlayer(uuid));
                new ArmorStandMenu(new PlayerMenuUtility(Bukkit.getPlayer(uuid)), "menu", ArmorStandSelectionCache.getSelectedArmorStand(uuid)).open();
                ArmorStandSelectionCache.removeSelectedArmorStand(Bukkit.getPlayer(uuid).getUniqueId());
            }

            @Override
            public void closeSession(Player p) {
                InventoryUtils.restore(p);
                new ArmorStandMenu(new PlayerMenuUtility(p), "menu", ArmorStandSelectionCache.getSelectedArmorStand(p.getUniqueId())).open();
                ArmorStandSelectionCache.removeSelectedArmorStand(p.getUniqueId());
            }

            @Override
            public ArmorStand getArmorsStand(Player p) {
                return ArmorStandSelectionCache.getSelectedArmorStand(p.getUniqueId());
            }

            @Override
            public ArmorStand getArmorsStand(UUID uuid) {
                return ArmorStandSelectionCache.getSelectedArmorStand(uuid);
            }
        };
    }

    @Override
    public boolean isInEditSession(UUID uuid) {
        return ArmorStandSelectionCache.hasSelection(uuid);
    }

    @Override
    public boolean isInEditSession(Player p) {
        return ArmorStandSelectionCache.hasSelection(p.getUniqueId());
    }

    @Override
    public LogsManager getLogManager() {
        return new LogsManager() {
            @Override
            public void debug(String args) {
                AdvancedArmorStands.debug(args);
            }

            @Override
            public void info(String args) {
                AdvancedArmorStands.info(args);
            }
        };
    }

    @Override
    public ArmorStandManager getArmorStandManager() {
        return new ArmorStandManager() {
            @Override
            public ArmorStand getArmorStandByName(String s) {
                return ArmorStandUtils.getArmorStandByName(s);
            }

            @Override
            public void removeArmorStand(ArmorStand armorStand) {

                armorStand.remove();

            }

            @Override
            public void removeArmorStand(String s) {

                ArmorStand as = ArmorStandUtils.getArmorStandByName(s);

                if (as == null) return;

                as.remove();

            }
        };
    }
}
