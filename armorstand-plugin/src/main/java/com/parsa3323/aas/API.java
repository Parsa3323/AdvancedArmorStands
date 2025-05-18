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

package com.parsa3323.aas;

import com.parsa3323.aas.api.ArmorstandApi;
import com.parsa3323.aas.api.player.IPlayer;
import com.parsa3323.aas.api.versionSupport.IVersionSupport;
import com.parsa3323.aas.configs.AnimationConfig;
import com.parsa3323.aas.configs.ArmorStands;
import com.parsa3323.aas.configs.TypesConfig;
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.menus.SaveMenu;
import com.parsa3323.aas.options.manager.SettingsManager;
import com.parsa3323.aas.player.PlayerManager;
import com.parsa3323.aas.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.UUID;

public class API implements ArmorstandApi {
    @Override
    public IVersionSupport getVersionSupport() {
        return VersionSupportUtil.getVersionSupport();
    }

    @Override
    public boolean isFirstTimeRunning() {
        return AdvancedArmorStands.isFirstTimeRunning;
    }

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
            public FileConfiguration getAnimationConfig() {
                return AnimationConfig.get();
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
                new ArmorStandMenu(new PlayerMenuUtility(Bukkit.getPlayer(uuid)), ArmorStandSelectionCache.getSelectedArmorStand(uuid)).open();
                ArmorStandSelectionCache.removeSelectedArmorStand(Bukkit.getPlayer(uuid).getUniqueId());
            }

            @Override
            public void closeSession(Player p) {
                InventoryUtils.restore(p);
                new ArmorStandMenu(new PlayerMenuUtility(p), ArmorStandSelectionCache.getSelectedArmorStand(p.getUniqueId())).open();
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
    public IPlayer getIPlayer(Player p) {
        return PlayerManager.getCustomPlayerByBukkit(p);
    }

    @Override
    public AnimationManager getAnimationManager() {
        return new AnimationManager() {
            @Override
            public void reload() {
                AnimationUtils.reloadAnimations();
            }

            @Override
            public boolean hasAnimation(ArmorStand armorStand) {
                return ArmorStands.get().contains("armorstands." + ArmorStandUtils.getNameByArmorStand(armorStand) + ".animation");
            }

            @Override
            public boolean hasAnimation(String s) {
                return ArmorStands.get().contains("armorstands." + ArmorStandUtils.getArmorStandByName(s) + ".animation");
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

    @Override
    public InventoryManager getInventoryManager() {
        return new InventoryManager() {
            @Override
            public void openEditMenu(Player p, ArmorStand a) {
                new ArmorStandMenu(new PlayerMenuUtility(p), a).open();
            }

            @Override
            public void openOptionsMenu(Player p, ArmorStand a, boolean isFromSettings) {
                new SettingsManager(new PlayerMenuUtility(p), a, isFromSettings).open();
            }


            @Override
            public void openSaveMenu(Player p, ArmorStand a) {
                new SaveMenu(new PlayerMenuUtility(p), a).open();
            }
        };
    }
}
