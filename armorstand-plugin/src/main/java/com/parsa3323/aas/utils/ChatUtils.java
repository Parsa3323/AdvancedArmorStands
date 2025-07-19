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

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String applyColorReset(String line, String prefix, String command, String sep, String description) {
        if (line.contains(command)) {
            if (line.contains(sep.trim())) return ChatColor.YELLOW + "" + ChatColor.BOLD + line;
            return ChatColor.YELLOW + "" + ChatColor.BOLD + line;
        } else if (line.contains(description.substring(0, Math.min(5, description.length())))) {
            return ChatColor.GRAY + line;
        }
        return ChatColor.GRAY + line;
    }


}
