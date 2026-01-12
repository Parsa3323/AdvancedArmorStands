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
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TextUtils {
    public static String getOriginalSuffix(int value) {
        if (value >= 11 && value <= 13) return "th";
        switch (value % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }

    public static boolean containsLink(String text) {
        return Pattern.compile(
                "(?i)(https?:\\/\\/|www\\.|\\.[a-z]{2,})"
        ).matcher(text).find();
    }

    public static boolean checkName(String name, Player player) {
        if (name.contains(",") || name.contains("[") || name.contains("]") ||  name.contains(".") || name.contains("/") || name.contains(";")
                || name.contains("'") || name.contains("{" ) || name.contains("}") || name.contains("+") || name.contains("=")
                || name.contains("@") || name.contains("!") || name.contains("#") || name.contains("$") || name.contains("%")
                || name.contains("^" ) || name.contains("&") || name.contains("*") || name.contains("(") || name.contains(")")
                || name.contains(":")) {
            player.sendMessage(ChatColor.RED + "ArmorStand name cannot contain symbols: , [ ] . / ; ' { } + = @ ! # $ % ^ & * ( ) :");
            return false;
        }
        return true;
    }

    public static String getFirstContent(ArrayList<String> list, String placeHolder) {
        return (list.get(0) == null) ? placeHolder : list.get(0);
    }

}
