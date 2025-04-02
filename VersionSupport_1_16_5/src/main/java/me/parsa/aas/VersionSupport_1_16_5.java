/*
 * .
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

public final class VersionSupport_1_16_5 implements IVersionSupport {


    @Override
    public void playSound(Player player, Location location, String soundName, float volume, float pitch) {
        try {
            Sound sound = null;
            switch (soundName) {
                case "NOTE_PLING":
                    sound = Sound.BLOCK_NOTE_BLOCK_PLING;
                    break;
                case "NOTE_BASS":
                    sound  = Sound.BLOCK_NOTE_BLOCK_BASS;
                    break;
                case "VILLAGER_NO":
                    sound = Sound.ENTITY_VILLAGER_NO;
                    break;
                case "ORB_PICKUP":
                    sound = Sound.ENTITY_EXPERIENCE_ORB_PICKUP;
                    break;
                case "ITEM_PICKUP":
                    sound = Sound.ENTITY_ITEM_PICKUP;
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Unknown sound: " + soundName);
                    return;
            }
            player.playSound(location, sound, volume, pitch);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Sound " + soundName + " not found in this version!");
        }
    }

    @Override
    public Material getMaterialForVersion(String s) {


        Material material = null;

        switch (s) {
            case "STAINED_GLASS_PANE":
                material = Material.GRAY_STAINED_GLASS_PANE;
                break;
            default:
                System.out.println("Unknown material: " + s);
                return null;



        }

        return material;

    }

}
