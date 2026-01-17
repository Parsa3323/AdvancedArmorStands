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

import com.parsa3323.aas.config.AnimationConfig;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Map;

public class KeyFrameUtils {
    public static void removeStep(String animationName, int stepNumber) {
        FileConfiguration config = AnimationConfig.get();
        String path = "animations." + animationName + ".steps";

        List<Map<?, ?>> steps = config.getMapList(path);

        if (steps == null || stepNumber <= 0 || stepNumber > steps.size()) return;

        steps.remove(stepNumber - 1);

        config.set(path, steps);
        AnimationConfig.save();
    }

}
