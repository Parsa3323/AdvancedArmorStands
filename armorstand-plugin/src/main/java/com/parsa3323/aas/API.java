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
import com.parsa3323.aas.api.data.MemoryData;
import com.parsa3323.aas.api.events.ArmorStandCreateEvent;
import com.parsa3323.aas.api.exeption.*;
import com.parsa3323.aas.api.versionSupport.VersionSupport;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.config.TypesConfig;
import com.parsa3323.aas.menus.ActionMenu;
import com.parsa3323.aas.menus.ArmorStandMenu;
import com.parsa3323.aas.menus.SaveMenu;
import com.parsa3323.aas.options.manager.SettingsManager;
import com.parsa3323.aas.tools.manager.ToolsManager;
import com.parsa3323.aas.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;

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
    public VersionSupport getVersionSupport() {
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
            public ArmorStand getArmorStand(Player p) {
                return ArmorStandSelectionCache.getSelectedArmorStand(p.getUniqueId());
            }

            @Override
            public ArmorStand getArmorStand(UUID uuid) {
                return ArmorStandSelectionCache.getSelectedArmorStand(uuid);
            }
        };
    }

    @Override
    public boolean reloadPlugin() throws ReloadException {
        try {

            PluginUtils.reload();
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
    public PlayerManager getPlayerManager(Player player) {
        return new PlayerManager() {
            @Override
            public boolean hasAdminPermission() {
                return com.parsa3323.aas.player.PlayerManager.getByBukkit(player).isAdmin();
            }

            @Override
            public void restoreInventory() throws InventoryRestoreException {
                if (!player.isOnline()) throw new InventoryRestoreException("Player is not online");

                if (ArmorStandSelectionCache.isIsInEditSession(player)) {
                    if (InventoryUtils.hasGameMode(player)) {
                        player.setGameMode(InventoryUtils.getAndClearGameMode(player));
                        AdvancedArmorStands.debug("Restored " + player.getDisplayName() + "'s gamemode");
                    }
                    if (InventoryUtils.hasBackup(player)) {
                        InventoryUtils.restore(player);
                        InventoryUtils.save(player);
                        AdvancedArmorStands.debug("Restored " + player.getDisplayName() +"'s inventory");
                    }
                }

                if (ArmorStandSelectionCache.isInKeyFrameList(player)) {

                    InventoryUtils.restore(player);
                    ArmorStandSelectionCache.removeFromKeyFrameList(player);
                    ArmorStand armorStand = ArmorStandSelectionCache.getKeyFrameSelectedArmorStand(player.getUniqueId());
                    AdvancedArmorStands.debug(armorStand.getName());

                    ArmorStandPoseData savedPose = ArmorStandUtils.getPose(armorStand.getUniqueId());

                    armorStand.setRightArmPose(savedPose.getRightArm());
                    armorStand.setLeftArmPose(savedPose.getLeftArm());
                    armorStand.setRightLegPose(savedPose.getRightLeg());
                    armorStand.setLeftLegPose(savedPose.getLeftLeg());
                    armorStand.setHeadPose(savedPose.getHead());

                    ArmorStandSelectionCache.removeKeyFrameSelectedArmorStand(player.getUniqueId());

                    AdvancedArmorStands.debug("Restored " + player.getDisplayName() +"'s inventory");

                }
            }
        };
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
                return ArmorStandsConfig.get().contains("armorstands." + s + ".animation");
            }

            @Override
            public void setAnimation(String name, String animation) throws AnimationNotFoundException {
                if (!AnimationConfig.get().contains("animations." + animation)) throw new AnimationNotFoundException("Animation not found: " + animation);
                ArmorStandsConfig.get().set("armorstands." + name + ".animation", animation);
                ArmorStandsConfig.save();
            }

            @Override
            public void setAnimation(String armorStandName, String animationName, boolean loop, int interval, ArmorStandPoseData... animation) {
                ConfigurationSection cs = AnimationConfig.get().createSection("animations." + animationName);

                cs.set("interval", interval);
                cs.set("loop", loop);

                List<Map<String, Object>> steps = new ArrayList<>();

                for (ArmorStandPoseData poseData : animation) {
                    Map<String, Object> step = new LinkedHashMap<>();

                    step.put("head", ConfigUtils.mapPose(poseData.getHead()));
                    step.put("left_arm", ConfigUtils.mapPose(poseData.getLeftArm()));
                    step.put("right_arm", ConfigUtils.mapPose(poseData.getRightArm()));
                    step.put("left_leg", ConfigUtils.mapPose(poseData.getLeftLeg()));
                    step.put("right_leg", ConfigUtils.mapPose(poseData.getRightLeg()));

                    steps.add(step);
                }

                cs.set("steps", steps);

                AnimationConfig.save();
                ArmorStandsConfig.get().set("armorstands." + armorStandName + ".animation", animationName);
                ArmorStandsConfig.save();
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
                if (TextUtils.containsLink(message)) {
                    throw new LogException("Message can't have links, use troubleShootingLink instead!");
                }
                AdvancedArmorStands.error(message + " [NOT OFFICIAL]");
            }

            @Override
            public void error(String message, String troubleShootingLink) {
                if (TextUtils.containsLink(message)) {
                    throw new LogException("Message can't have links, use troubleShootingLink instead!");
                }
                AdvancedArmorStands.error(message + " [NOT OFFICIAL, CLICK AT YOUR OWN RISK]", troubleShootingLink);
            }

            @Override
            public void error(String message, boolean b) {
                if (TextUtils.containsLink(message)) {
                    throw new LogException("Message can't have links, use troubleShootingLink instead!");
                }
                AdvancedArmorStands.error(message + " [NOT OFFICIAL]", b);
            }

            @Override
            public void info(String args) {
                if (TextUtils.containsLink(args)) {
                    throw new LogException("Message can't have links, use troubleShootingLink instead!");
                }
                AdvancedArmorStands.info(args + " [NOT OFFICIAL]");
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
            public void moveArmorStand(ArmorStand armorStand, Location location) {
                String path = "armorstands." + ArmorStandUtils.getNameByArmorStand(armorStand);

                ArmorStandsConfig.get().set(path + ".World", location.getWorld().getName());
                ArmorStandsConfig.get().set(path + ".X", location.getX());
                ArmorStandsConfig.get().set(path + ".Y", location.getY());
                ArmorStandsConfig.get().set(path + ".Z", location.getZ());
                ArmorStandsConfig.save();

                armorStand.teleport(location);
            }

            @Override
            public void moveArmorStand(String name, Location location) {
                String path = "armorstands." + name;

                ArmorStandsConfig.get().set(path + ".World", location.getWorld().getName());
                ArmorStandsConfig.get().set(path + ".X", location.getX());
                ArmorStandsConfig.get().set(path + ".Y", location.getY());
                ArmorStandsConfig.get().set(path + ".Z", location.getZ());
                ArmorStandsConfig.save();

                ArmorStandUtils.getArmorStandByName(name).teleport(location);
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
            public void setPose(String asName, ArmorStandPoseData poseData) throws ArmorStandNotFoundException {
                ArmorStand armorStand = ArmorStandUtils.getArmorStandByName(asName);

                if (armorStand == null) {
                    throw new ArmorStandNotFoundException("Armor stand '" + asName + "' not found!");
                }

                armorStand.setHeadPose(poseData.getHead());
                armorStand.setRightArmPose(poseData.getRightArm());
                armorStand.setLeftArmPose(poseData.getRightArm());
                armorStand.setRightLegPose(poseData.getRightLeg());
                armorStand.setLeftLegPose(poseData.getLeftLeg());
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

            @Override
            public ArmorStandInfoUtils getUtils() {
                return new ArmorStandInfoUtils() {
                    @Override
                    public ArrayList<String> getArmorStandsList() {
                        return ArmorStandUtils.getArmorStandList();
                    }

                    @Override
                    public ArrayList<String> getArmorStandsWithAnimation() {
                        return ArmorStandUtils.getArmorStandsWithAnimation();
                    }

                    @Override
                    public int getTotalArmorStands() {
                        return ArmorStandUtils.getTotalArmorStands();
                    }

                    @Override
                    public int getTotalLoadedArmorStands() {
                        return ArmorStandUtils.getLoadedArmorStands();
                    }
                };
            }
        };
    }

    @Override
    public InventoryManager getInventoryManager() {
        return new InventoryManager() {
            @Override
            public void openToolsMenu(Player p, ArmorStand as) throws InventoryException {
                try {
                    new ToolsManager(new PlayerMenuUtility(p), as).open();
                } catch (Exception e) {
                    throw new InventoryException("Failed to open Tools Menu", e);
                }
            }

            @Override
            public void openActionsMenu(Player p, ArmorStand as) throws InventoryException {
                try {
                    ActionMenu actionMenu = new ActionMenu(new PlayerMenuUtility(p), as);
                    actionMenu.open();
                } catch (Exception e) {
                    throw new InventoryException("Failed to open Actions Menu", e);
                }

            }

            @Override
            public void openEditMenu(Player p, ArmorStand a) throws InventoryException {
                try {
                    new ArmorStandMenu(new PlayerMenuUtility(p), a).open();
                } catch (Exception e) {
                    throw new InventoryException("Failed to open Edit Menu", e);
                }
            }

            @Override
            public void openOptionsMenu(Player p, ArmorStand a, boolean isFromSettings) throws InventoryException {

                try {
                    new SettingsManager(new PlayerMenuUtility(p), a, isFromSettings).open();
                } catch (Exception e) {
                    throw new InventoryException("Failed to open Options Menu", e);
                }
            }


            @Override
            public void openSaveMenu(Player p, ArmorStand a) throws InventoryException {
                try {
                    new SaveMenu(new PlayerMenuUtility(p), a).open();
                } catch (Exception e) {
                    throw new InventoryException("Failed to open Save Menu", e);
                }
            }
        };
    }

    @Override
    public AiManager getAiManager() {
        return new AiManager() {
            @Override
            public void getResponseAsync(String apiKey, MemoryData data, String userInput, Consumer<String> callback) throws AiException {
                try {
                    AiUtils.getResponseAsync(apiKey, data, userInput, callback);
                } catch (Exception e) {
                    throw new AiException(e);
                }
            }

            @Override
            public void getResponseAsync(MemoryData data, String userInput, Consumer<String> callback) throws AiException {
                try {
                    AiUtils.getResponseAsync(AdvancedArmorStands.getAiApiKey(), data, userInput, callback);
                } catch (Exception e) {
                    throw new AiException(e);
                }
            }

            @Override
            public String getResponse(String apiKey, MemoryData data, String userInput) throws AiException {
                try {
                    return AiUtils.getResponse(apiKey, data, userInput);
                } catch (Exception e) {
                    throw new AiException(e);
                }
            }

            @Override
            public String getResponse(MemoryData data, String userInput) throws AiException {
                try {
                    return AiUtils.getResponse(AdvancedArmorStands.getAiApiKey(), data, userInput);
                } catch (Exception e) {
                    throw new AiException(e);
                }
            }
        };
    }
}
