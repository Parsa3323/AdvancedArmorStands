/*
 *
 * Copyright
 * 2026 AdvancedArmorStands, Inc.
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

import com.parsa3323.aas.config.ActionConfig;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ActionUtils {

    public static ArrayList<String> getTotalActionsForArmorStand(String name) {
        ConfigurationSection cs = ActionConfig.get().getConfigurationSection("armorstand." + name);

        if (null == cs) {
            return new ArrayList<>();
        }

        return new ArrayList<>(cs.getKeys(false));
    }

    public static List<String> prioritize(ConfigurationSection section) {
        List<String> keys = new ArrayList<>(section.getKeys(false));

        keys.sort((a, b) -> {
            int priorityA = section.getInt(a + ".priority", 0);
            int priorityB = section.getInt(b + ".priority", 0);

            if (priorityA == 0) priorityA = Integer.MAX_VALUE;
            if (priorityB == 0) priorityB = Integer.MAX_VALUE;

            return Integer.compare(priorityA, priorityB);
        });

        return keys;
    }
}
