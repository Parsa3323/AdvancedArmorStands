/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
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

import me.parsa.aas.VersionSupport.IVersionSupport;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public final class VersionSupprt_1_8 implements IVersionSupport {


    @Override
    public void playSound(Player player, Location location, String soundName, float volume, float pitch) {
        try {
            Sound sound = Sound.valueOf(soundName);
            player.playSound(location, sound, volume, pitch);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Sound " + soundName + " not found in this version!");
        }
    }

    @Override
    public Material getMaterialForVersion(String s) {
        try {
            Material sound = Material.valueOf(s);
            return sound;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }

    }
}
