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

package com.parsa3323.aas.listener;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.data.ArmorStandPoseData;
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.InventoryUtils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveEvent implements Listener {

    @EventHandler
    public void quit(PlayerQuitEvent e) {
        if (InventoryUtils.hasBackup(e.getPlayer())) {
            InventoryUtils.restore(e.getPlayer());
            InventoryUtils.save(e.getPlayer());
        }

        if (ArmorStandSelectionCache.isIsInEditSession(e.getPlayer())) {
            if (InventoryUtils.hasGameMode(e.getPlayer())) {
                AdvancedArmorStands.debug("Player " + e.getPlayer().getName() + " is leaving restoring gamemode...");
                e.getPlayer().setGameMode(InventoryUtils.getAndClearGameMode(e.getPlayer()));
            }
            ArmorStandSelectionCache.removeSelectedArmorStand(e.getPlayer().getUniqueId());
        }

        if (ArmorStandSelectionCache.isInKeyFrameList(e.getPlayer())) {
            InventoryUtils.restore(e.getPlayer());
            ArmorStandSelectionCache.removeFromKeyFrameList(e.getPlayer());
            ArmorStand armorStand = ArmorStandSelectionCache.getKeyFrameSelectedArmorStand(e.getPlayer().getUniqueId());
            AdvancedArmorStands.debug(armorStand.getName());

            ArmorStandPoseData savedPose = ArmorStandUtils.getPose(armorStand.getUniqueId());

            armorStand.setRightArmPose(savedPose.getRightArm());
            armorStand.setLeftArmPose(savedPose.getLeftArm());
            armorStand.setRightLegPose(savedPose.getRightLeg());
            armorStand.setLeftLegPose(savedPose.getLeftLeg());
            armorStand.setHeadPose(savedPose.getHead());

            ArmorStandSelectionCache.removeKeyFrameSelectedArmorStand(e.getPlayer().getUniqueId());
        }
    }
}
