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

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PoseManager {
    private static final Map<UUID, ArmorStandPoseData> poseMap = new ConcurrentHashMap<>();

    public static void savePose(ArmorStand armorStand) {
        ArmorStandPoseData pose = new ArmorStandPoseData(
                armorStand.getRightArmPose(),
                armorStand.getLeftArmPose(),
                armorStand.getRightLegPose(),
                armorStand.getLeftLegPose(),
                armorStand.getHeadPose()
        );
        poseMap.put(armorStand.getUniqueId(), pose);
    }

    public static ArmorStandPoseData getPose(UUID armorStandId) {
        return poseMap.get(armorStandId);
    }
}
