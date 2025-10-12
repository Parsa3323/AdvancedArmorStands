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
import com.parsa3323.aas.config.ActionConfig;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.config.TypesConfig;

public class PluginUtils {

    public static void reload() throws Exception {
        try {
            TypesConfig.reload();
            ArmorStandsConfig.reload();
            AnimationConfig.reload();
            AnimationUtils.reloadAnimations();
            ActionConfig.reload();
            if (AdvancedArmorStands.plugin.getConfig().getBoolean("auto-load-armor-stands")) {
                for (String key : ArmorStandUtils.getArmorStandList()) {
                    ArmorStandUtils.autoLoadArmorStand(key);
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

}
