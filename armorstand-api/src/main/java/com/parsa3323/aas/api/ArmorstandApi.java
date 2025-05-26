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


import com.parsa3323.aas.api.exeption.ArmorStandNotFoundException;
import com.parsa3323.aas.api.player.IPlayer;
import com.parsa3323.aas.api.versionSupport.IVersionSupport;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface ArmorstandApi  {

    IVersionSupport getVersionSupport();

    boolean isFirstTimeRunning();

    ConfigManager getConfigManager();

    SessionManager getSessionManager();

    @Deprecated
    String getAddonsPath();

    @Deprecated
    IPlayer getIPlayer(Player p);

    interface SessionManager {

        void closeSession(UUID uuid);

        void closeSession(Player p);

        ArmorStand getArmorsStand(Player p);

        ArmorStand getArmorsStand(UUID uuid);

    }

    AnimationManager getAnimationManager();

    interface AnimationManager {

        void reload();

        boolean hasAnimation(ArmorStand armorStand);

        boolean hasAnimation(String name);

    }

    boolean isInEditSession(UUID uuid);

    boolean isInEditSession(Player p);

    interface ConfigManager {

        FileConfiguration getMainConfig();

        FileConfiguration getCacheConfig();

        FileConfiguration getAnimationConfig();

        FileConfiguration getTypesConfig();

    }

    LogsManager getLogManager();

    interface LogsManager {

        void debug(String args);

        void error(String message);

        void error(String message, boolean b);

        void info(String args);

    }

    ArmorStandManager getArmorStandManager();

    interface ArmorStandManager {

        ArmorStand getArmorStandByName(String s) throws ArmorStandNotFoundException;

        void removeArmorStand(ArmorStand armorStand);

        void removeArmorStand(String s) throws ArmorStandNotFoundException;

    }

    InventoryManager getInventoryManager();

    interface InventoryManager {

        void openEditMenu(Player p, ArmorStand a);

        void openOptionsMenu(Player p, ArmorStand a, boolean isFromSettings);

        void openSaveMenu(Player p, ArmorStand a);

    }

}
