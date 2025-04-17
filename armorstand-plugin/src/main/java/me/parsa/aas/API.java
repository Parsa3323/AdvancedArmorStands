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

import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Configs.TypesConfig;
import me.parsa.aas.Utils.ArmorStandUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;

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
    public boolean isInEditSession(UUID s) {
        return false;
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
