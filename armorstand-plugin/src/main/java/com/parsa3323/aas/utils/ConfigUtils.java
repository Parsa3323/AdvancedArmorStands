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

import com.parsa3323.aas.AdvancedArmorStands;
import org.bukkit.util.EulerAngle;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigUtils {
    public static Map<String, Object> mapPose(EulerAngle angle) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("x", angle.getX());
        map.put("y", angle.getY());
        map.put("z", angle.getZ());
        return map;
    }

    public static void renameOldFileIfNeeded() {
        File oldAsConfig = new File(AdvancedArmorStands.plugin.getDataFolder(), "cache/armorstands.yml");
        File oldAiConfig = new File(AdvancedArmorStands.plugin.getDataFolder(), "cache/ai.yml");

        if (oldAsConfig.exists() && oldAiConfig.exists()) {
            oldAiConfig.renameTo(new File(AdvancedArmorStands.plugin.getDataFolder(), "cache/ai.aas"));
            oldAsConfig.renameTo(new File(AdvancedArmorStands.plugin.getDataFolder(), "cache/armorstands.aas"));
        }
    }
}
