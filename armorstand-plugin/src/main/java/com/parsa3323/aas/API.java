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
import com.parsa3323.aas.api.data.ArmorStandPoseData;
import com.parsa3323.aas.api.events.ArmorStandCreateEvent;
import com.parsa3323.aas.api.exeption.*;
import com.parsa3323.aas.api.player.IPlayer;
import com.parsa3323.aas.api.versionSupport.IVersionSupport;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.config.TypesConfig;
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.menus.SaveMenu;
import com.parsa3323.aas.options.manager.SettingsManager;
import com.parsa3323.aas.player.PlayerManager;
import com.parsa3323.aas.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.UUID;

public class API implements ArmorstandApi {

    private final File addonFolder;

    public API() {
        this.addonFolder = new File(AdvancedArmorStands.plugin.getDataFolder(), "addons");
        AdvancedArmorStands.debug("Checking if addons folder exists");
        if (!addonFolder.exists()) {
            addonFolder.mkdirs();
        }
    }


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
            public FileConfiguration getMainConfig() throws ConfigException {
                try {
                    return AdvancedArmorStands.plugin.getConfig();
                } catch (Exception e) {
                    throw new ConfigException(e);
                }
            }

            @Override
            public FileConfiguration getCacheConfig() throws ConfigException {
                try {
                    return ArmorStandsConfig.get();
                } catch (Exception e) {
                    throw new ConfigException(e);
                }
            }

            @Override
            public FileConfiguration getAnimationConfig() throws ConfigException {
                try {
                    return AnimationConfig.get();
                } catch (Exception e) {
                    throw new ConfigException(e);
                }
            }

            @Override
            public FileConfiguration getTypesConfig() throws ConfigException {
                try {
                    return TypesConfig.get();
                } catch (Exception e) {
                    throw new ConfigException(e);
                }
            }
        };
    }
    @Override
    public EditSessionManager getEditSessionManager() {
        return new EditSessionManager() {
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
    public boolean reloadPlugin() throws ReloadException {
        try {


            TypesConfig.reload();
            ArmorStandsConfig.reload();
            AnimationConfig.reload();
            AnimationUtils.reloadAnimations();
            if (AdvancedArmorStands.plugin.getConfig().getBoolean("auto-load-armor-stands")) {
                for (String key : ArmorStandUtils.getArmorStandList()) {
                    ArmorStandUtils.autoLoadArmorStand(key);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ReloadException(ChatColor.RED + e.getMessage());
        }

    }

    @Override
    public boolean isMigrating() {
        return AdvancedArmorStands.isMigrating();
    }

    @Override
    public String getAddonsPath() {
        return addonFolder.getPath();
    }

    @Override
    public IPlayer getIPlayer(Player p) {
        return PlayerManager.getByBukkit(p);
    }

    @Override
    public AnimationManager getAnimationManager() {
        return new AnimationManager() {
            @Override
            public void reload() throws ReloadException {
                try {
                    AnimationUtils.reloadAnimations();
                } catch (Exception e) {
                    throw new ReloadException(e.getMessage());
                }
            }

            @Override
            public boolean hasAnimation(ArmorStand armorStand) {
                return ArmorStandsConfig.get().contains("armorstands." + ArmorStandUtils.getNameByArmorStand(armorStand) + ".animation");
            }

            @Override
            public boolean hasAnimation(String s) {
                return ArmorStandsConfig.get().contains("armorstands." + ArmorStandUtils.getArmorStandByName(s) + ".animation");
            }
        };
    }

    @Override
    public SkullUtils getSkullUtils() {
        return new SkullUtils() {
            @Override
            public ItemStack getPlayerHead(Player player) {
                return PlayerUtils.createSkullPlayer(player.getName());
            }

            @Override
            public ItemStack getPlayerHead(UUID uuid) {
                return PlayerUtils.createSkullPlayer(Bukkit.getPlayer(uuid).getName());
            }

            @Override
            public ItemStack getPlayerHead(String name) {
                return PlayerUtils.createSkullPlayer(name);
            }

            @Override
            public ItemStack getSkull(String base64) {
                return VersionSupportUtil.getVersionSupport().getSkull(base64);
            }
        };
    }

    @Override
    public boolean isInEditSession(UUID uuid) {
        return ArmorStandSelectionCache.isIsInEditSession(Bukkit.getPlayer(uuid));
    }

    @Override
    public boolean isInEditSession(Player p) {
        return ArmorStandSelectionCache.isIsInEditSession(p);
    }

    @Override
    public LogsManager getLogManager() {
        return new LogsManager() {
            @Override
            public boolean isDebug() {
                return AdvancedArmorStands.isDebug();
            }

            @Override
            public void debug(String args) {
                AdvancedArmorStands.debug(args);
            }

            @Override
            public void error(String message) {
                AdvancedArmorStands.error(message);
            }

            @Override
            public void error(String message, String troubleShootingLink) {
                AdvancedArmorStands.error(message, troubleShootingLink);
            }

            @Override
            public void error(String message, boolean b) {
                AdvancedArmorStands.error(message, b);
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
            public void loadArmorStand(ArmorStand armorStand) throws ArmorStandLoadException {
                ArmorStandUtils.loadArmorStand(ArmorStandUtils.getNameByArmorStand(armorStand));
            }

            @Override
            public void loadArmorStand(String name) throws ArmorStandLoadException {
                ArmorStandUtils.loadArmorStand(name);
            }

            @Override
            public void createArmorStand(String name, ArmorStandPoseData poseData, Location location, Player player) throws ArmorStandAlreadyExistsException {
                ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

                ArmorStandCreateEvent armorStandCreateEvent = new ArmorStandCreateEvent(player, armorStand, name);
                Bukkit.getPluginManager().callEvent(armorStandCreateEvent);

                if (armorStandCreateEvent.isCancelled()) {
                    armorStand.remove();
                    return;
                }

                armorStand.setHeadPose(poseData.getHead());
                armorStand.setRightArmPose(poseData.getRightArm());
                armorStand.setLeftArmPose(poseData.getLeftArm());
                armorStand.setRightLegPose(poseData.getRightLeg());
                armorStand.setLeftLegPose(poseData.getLeftLeg());
            }

            @Override
            public void setPose(String asName, ArmorStandPoseData poseData) {

            }

            @Override
            public boolean exists(String name) {
                return ArmorStandUtils.exists(name);
            }

            @Override
            public boolean exists(ArmorStand as) {
                return ArmorStandUtils.exists(as);
            }

            @Override
            public boolean isLoaded(String name) {
                return ArmorStandUtils.isLoaded(ArmorStandUtils.getArmorStandByName(name));
            }

            @Override
            public boolean isLoaded(ArmorStand armorStand) {
                return ArmorStandUtils.isLoaded(armorStand);
            }

            @Override
            public ArmorStand getArmorStandByName(String s) throws ArmorStandNotFoundException {
                ArmorStand stand = ArmorStandUtils.getArmorStandByName(s);

                if (stand == null) {
                    throw new ArmorStandNotFoundException("Armor stand not found: " + s);
                }

                return ArmorStandUtils.getArmorStandByName(s);
            }

            @Override
            public void removeArmorStand(ArmorStand armorStand) {

                armorStand.remove();

            }

            @Override
            public void removeArmorStand(String s) throws ArmorStandNotFoundException {

                ArmorStand as = ArmorStandUtils.getArmorStandByName(s);

                if (as == null) throw new ArmorStandNotFoundException("Armor stand not found: " + s);

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
