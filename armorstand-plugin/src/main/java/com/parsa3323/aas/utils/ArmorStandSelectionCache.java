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

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArmorStandSelectionCache {

    private static boolean isInEditSession = false;

    private static final ArrayList<Player> editSessionPlayers = new ArrayList<>();

    private static final Map<UUID, ArmorStand> selectedArmorStands = new HashMap<>();

    public static void setSelectedArmorStand(UUID playerId, ArmorStand armorStand) {
        selectedArmorStands.put(playerId, armorStand);
    }

    public static boolean isIsInEditSession(Player player) {
        return editSessionPlayers.contains(player);
    }

    public static void addToEditSession(Player player) {
        editSessionPlayers.add(player);
    }

    public static void removeFromEditSession(Player player) {
        editSessionPlayers.remove(player);
    }

    public static ArmorStand getSelectedArmorStand(UUID playerId) {
        return selectedArmorStands.get(playerId);
    }

    public static void removeSelectedArmorStand(UUID playerId) {
        selectedArmorStands.remove(playerId);
    }

    @Deprecated
    public static boolean hasArmorStandsSelection(UUID playerId) {
        return selectedArmorStands.containsKey(playerId);
    }

}
