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

package com.parsa3323.aas.utils;

import com.parsa3323.aas.config.TypesConfig;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class TypeUtils {
    public static int getTotalTypes() {
        return TypesConfig.get().getKeys(false).size();
    }

    public static ArrayList<String> getTypesList() {
        return new ArrayList<>(TypesConfig.get().getKeys(false));
    }

    public static void migrateTypes() {
        FileConfiguration cfg = TypesConfig.get();

        for (String type : TypeUtils.getTypesList()) {
            String base = type + ".";

            if (cfg.contains(base + "Arms")) {
                cfg.set(base + "arms", cfg.get(base + "Arms"));
                cfg.set(base + "Arms", null);
            }

            if (cfg.contains(base + "BasePlate")) {
                cfg.set(base + "basePlate", cfg.get(base + "BasePlate"));
                cfg.set(base + "BasePlate", null);
            }

            if (cfg.contains(base + "CustomName")) {
                cfg.set(base + "customName", cfg.get(base + "CustomName"));
                cfg.set(base + "CustomName", null);
            }

            if (cfg.contains(base + "HeadPos")) {
                cfg.set(base + "headPos", cfg.get(base + "HeadPos"));
                cfg.set(base + "HeadPos", null);
            }

            if (cfg.contains(base + "Gravity")) {
                cfg.set(base + "Gravity", null);
            }
        }
    }
}
