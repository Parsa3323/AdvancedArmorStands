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

package com.parsa3323.aas.api;


import com.parsa3323.aas.api.data.ArmorStandPoseData;
import com.parsa3323.aas.api.data.MemoryData;
import com.parsa3323.aas.api.exeption.*;
import com.parsa3323.aas.api.versionSupport.VersionSupport;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public interface ArmorstandApi  {

    VersionSupport getVersionSupport();

    boolean isFirstTimeRunning();

    ConfigManager getConfigManager();

    EditSessionManager getEditSessionManager();

    boolean reloadPlugin() throws ReloadException;

    boolean isMigrating();

    String getAddonsPath();

    PlayerManager getPlayerManager(Player player);

    interface PlayerManager {
        boolean hasAdminPermission();

        void restoreInventory() throws InventoryRestoreException;
    }

    interface EditSessionManager {

        void closeSession(UUID uuid);

        void closeSession(Player p);

        ArmorStand getArmorStand(Player p);

        ArmorStand getArmorStand(UUID uuid);

    }

    AnimationManager getAnimationManager();

    interface AnimationManager {

        void reload() throws ReloadException;

        boolean hasAnimation(ArmorStand armorStand);

        boolean hasAnimation(String name);

        void setAnimation(String name, String animation) throws AnimationNotFoundException;

        void setAnimation(String armorStandName, String animationName, boolean loop, int interval, ArmorStandPoseData... animation);
    }

    SkullUtils getSkullUtils();

    interface SkullUtils {

        ItemStack getPlayerHead(Player player);

        ItemStack getPlayerHead(UUID uuid);

        ItemStack getPlayerHead(String name);

        ItemStack getSkull(String base64);

    }

    boolean isInEditSession(UUID uuid);

    boolean isInEditSession(Player p);

    interface ConfigManager {

        FileConfiguration getMainConfig() throws ConfigException;

        FileConfiguration getCacheConfig() throws ConfigException;

        FileConfiguration getAnimationConfig() throws ConfigException;

        FileConfiguration getTypesConfig() throws ConfigException;

    }

    LogsManager getLogManager();

    interface LogsManager {
        boolean isDebug();

        void debug(String args) throws LogException;

        @Deprecated
        void error(String message) throws LogException;

        void error(String message, String troubleShootingLink) throws LogException;

        void error(String message, boolean b) throws LogException;

        void info(String args);
    }

    ArmorStandManager getArmorStandManager();

    interface ArmorStandInfoUtils {

        ArrayList<String> getArmorStandsList();

        ArrayList<String> getArmorStandsWithAnimation();

        int getTotalArmorStands();

        int getTotalLoadedArmorStands();

    }

    interface ArmorStandManager {

        void loadArmorStand(ArmorStand armorStand) throws ArmorStandLoadException;

        void moveArmorStand(ArmorStand armorStand, Location location);

        void moveArmorStand(String name, Location location);

        void loadArmorStand(String name) throws ArmorStandLoadException;

        void createArmorStand(String name, ArmorStandPoseData poseData, Location location, Player player) throws ArmorStandAlreadyExistsException;

        void setPose(String asName, ArmorStandPoseData poseData) throws ArmorStandNotFoundException;

        void previewPose(String asName, ArmorStandPoseData poseData, Player p) throws ArmorStandNotFoundException;

        boolean exists(String name);

        boolean exists(ArmorStand as);

        boolean isLoaded(String name);

        boolean isLoaded(ArmorStand armorStand);

        ArmorStand getArmorStandByName(String s) throws ArmorStandNotFoundException;

        void removeArmorStand(ArmorStand armorStand) throws ArmorStandNotFoundException;

        void removeArmorStand(String s) throws ArmorStandNotFoundException;

        ArmorStandInfoUtils getUtils();

        boolean hasAiEnabled(String name);

        boolean hasAiEnabled(ArmorStand armorStand);

        void setAi(ArmorStand armorStand, boolean value);

        void setAi(String name, boolean value);

    }

    InventoryManager getInventoryManager();

    interface InventoryManager {

        void openToolsMenu(Player p, ArmorStand as) throws InventoryException;

        void openActionsMenu(Player p, ArmorStand as) throws InventoryException;

        void openEditMenu(Player p, ArmorStand a) throws InventoryException;

        void openOptionsMenu(Player p, ArmorStand a, boolean isFromSettings) throws InventoryException;

        void openSaveMenu(Player p, ArmorStand a) throws InventoryException;

    }

    AiManager getAiManager();

    interface AiManager {

        void getResponseAsync(String apiKey, MemoryData data, String userInput, java.util.function.Consumer<String> callback) throws AiException;

        void getResponseAsync(MemoryData data, String userInput, java.util.function.Consumer<String> callback) throws AiException;

        @Deprecated
        String getResponse(String apiKey, MemoryData data, String userInput) throws AiException;

        @Deprecated
        String getResponse(MemoryData data, String userInput) throws AiException;


    }

}
