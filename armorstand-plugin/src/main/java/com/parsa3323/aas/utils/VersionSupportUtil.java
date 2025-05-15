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
import com.parsa3323.aas.VersionSupport_1_16_5;
import com.parsa3323.aas.VersionSupport_1_8;
import com.parsa3323.aas.api.versionSupport.IVersionSupport;
import com.parsa3323.versionSupport_1_20_1.VersionSupport_1_20_1;
import com.parsa3323.versionsupport_1_12.VersionSupport_1_12;
import com.parsa3323.versionsupport_v1_18.Versionsupport_v1_18;
import com.parsa3323.versionsupport_v1_19.Versionsupport_v1_19;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VersionSupportUtil {

    private static final String PACKAGE_NAME = Bukkit.getServer().getClass().getPackage().getName();
    private static final String SERVER_VERSION = PACKAGE_NAME.substring(PACKAGE_NAME.lastIndexOf('.') + 1)
            .replace("_", ".").replace("v", "")
            .replaceAll("\\.R\\d+$", "");

    public static boolean isHigherThan(String version) {
        return compareVersions(SERVER_VERSION, version) > 0;
    }

    public static boolean isLowerThan(String version) {
        return compareVersions(SERVER_VERSION, version) < 0;
    }

    public static String getVersion() {
        return SERVER_VERSION;
    }

    private static int compareVersions(String version1, String version2) {
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");

        int length = Math.max(parts1.length, parts2.length);

        for (int i = 0; i < length; i++) {
            int part1 = i < parts1.length ? Integer.parseInt(parts1[i]) : 0;
            int part2 = i < parts2.length ? Integer.parseInt(parts2[i]) : 0;

            if (part1 < part2) {
                return -1;
            }
            if (part1 > part2) {
                return 1;
            }
        }

        return 0;
    }
    public static IVersionSupport getVersionSupport(){
        IVersionSupport versionSupport = null;

        if (VersionSupportUtil.isLowerThan("1.12")) {
            versionSupport = new VersionSupport_1_8();
        } else if (VersionSupportUtil.isLowerThan("1.13")) {
            versionSupport = new VersionSupport_1_12();
        } else if (VersionSupportUtil.isLowerThan("1.17")) {
            versionSupport = new VersionSupport_1_16_5();
        } else if (VersionSupportUtil.isLowerThan("1.18")) {
            versionSupport = new Versionsupport_v1_18();
        } else if (VersionSupportUtil.isLowerThan("1.19")) {
            versionSupport = new Versionsupport_v1_18();
        } else if (VersionSupportUtil.isLowerThan("1.20")) {
            versionSupport = new Versionsupport_v1_19();
        } else {
            versionSupport = new VersionSupport_1_20_1();
        }


        return versionSupport;
    }

    public static ItemStack getSkull(String base64) {
        return AdvancedArmorStands.plugin.versionSupport.getSkull(base64);
    }

    public static Material getMaterialForCurrentVersion(String s) {
        return AdvancedArmorStands.plugin.versionSupport.getMaterialForVersion(s);
    }

    public static void playVersionSpecificSound(Player player, Location location, String soundName, float volume, float pitch) {
        AdvancedArmorStands.plugin.versionSupport.playSound(player, location, soundName, volume, pitch);
        AdvancedArmorStands.debug(soundName);
    }

}