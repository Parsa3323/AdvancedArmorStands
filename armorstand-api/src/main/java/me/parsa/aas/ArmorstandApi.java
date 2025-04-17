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

package me.parsa.aas;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;

public interface ArmorstandApi  {

    ConfigManager getConfigManager();


    interface ConfigManager {

        FileConfiguration getMainConfig();

        FileConfiguration getCacheConfig();

        FileConfiguration getTypesConfig();

    }

    LogsManager getLogManager();

    interface LogsManager {

        void debug(String args);

        void info(String args);

    }

    ArmorStandManager getArmorStandManager();

    interface ArmorStandManager {

        ArmorStand getArmorStandByName(String s);

        void removeArmorStand(ArmorStand armorStand);

        void removeArmorStand(String s);

    }

}
