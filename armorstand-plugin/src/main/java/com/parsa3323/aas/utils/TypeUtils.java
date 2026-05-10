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

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.config.TypesConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;

public class TypeUtils {
    public static int getTotalTypes() {
        return TypesConfig.get().getKeys(false).size();
    }

    public static ArrayList<String> getTypesList() {
        return new ArrayList<>(TypesConfig.get().getKeys(false));
    }

    public static void migrateTypes() {
        FileConfiguration cfg = TypesConfig.get();

        for (String type : TypeUtils.getTypesList()) {
            String base = type + ".";

            if (cfg.contains(base + "Arms")) {
                cfg.set(base + "arms", cfg.get(base + "Arms"));
                cfg.set(base + "Arms", null);
            }

            if (cfg.contains(base + "BasePlate")) {
                cfg.set(base + "basePlate", cfg.get(base + "BasePlate"));
                cfg.set(base + "BasePlate", null);
            }

            if (cfg.contains(base + "CustomName")) {
                cfg.set(base + "customName", cfg.get(base + "CustomName"));
                cfg.set(base + "CustomName", null);
            }

            if (cfg.contains(base + "HeadPos")) {
                cfg.set(base + "headPos", cfg.get(base + "HeadPos"));
                cfg.set(base + "HeadPos", null);
            }

            if (cfg.contains(base + "Gravity")) {
                cfg.set(base + "Gravity", null);
            }
        }
    }

    public static void saveAsType(ArmorStand armorStand, String typeName) {
        if (TypesConfig.get().contains(typeName)) {
            AdvancedArmorStands.warn("This type already exists, Either chose another name or select the type in the save menu",false);
            return;
        }

        TypesConfig.get().set( typeName + ".arms", armorStand.hasArms());
        TypesConfig.get().set(typeName + ".basePlate", armorStand.hasBasePlate());
        TypesConfig.get().set(typeName + ".customName", armorStand.getCustomName());
        TypesConfig.get().set(typeName + ".isCustomNameVisible", armorStand.isCustomNameVisible());
        TypesConfig.get().set(typeName + ".isVisible", armorStand.isVisible());
        TypesConfig.get().set(typeName + ".isSmall", armorStand.isSmall());
        TypesConfig.get().set(typeName +  ".itemInHandMaterial", armorStand.getItemInHand().getType().name());
        TypesConfig.get().set(typeName +  ".headPos.x", Math.toDegrees(armorStand.getHeadPose().getX()));
        TypesConfig.get().set(typeName + ".headPos.y", Math.toDegrees(armorStand.getHeadPose().getY()));
        TypesConfig.get().set(typeName + ".headPos.z", Math.toDegrees(armorStand.getHeadPose().getZ()));
        TypesConfig.get().set(typeName + ".rightArmPose.x", Math.toDegrees(armorStand.getRightArmPose().getX()));
        TypesConfig.get().set(typeName + ".rightArmPose.y", Math.toDegrees(armorStand.getRightArmPose().getY()));
        TypesConfig.get().set(typeName + ".rightArmPose.z", Math.toDegrees(armorStand.getRightArmPose().getZ()));
        TypesConfig.get().set(typeName + ".leftArmPose.x", Math.toDegrees(armorStand.getLeftArmPose().getX()));
        TypesConfig.get().set(typeName + ".leftArmPose.y", Math.toDegrees(armorStand.getLeftArmPose().getY()));
        TypesConfig.get().set(typeName + ".leftArmPose.z", Math.toDegrees(armorStand.getLeftArmPose().getZ()));
        TypesConfig.get().set(typeName + ".rightLegPose.x", Math.toDegrees(armorStand.getRightLegPose().getX()));
        TypesConfig.get().set(typeName + ".rightLegPose.y", Math.toDegrees(armorStand.getRightLegPose().getY()));
        TypesConfig.get().set(typeName + ".rightLegPose.z", Math.toDegrees(armorStand.getRightLegPose().getZ()));
        TypesConfig.get().set(typeName + ".leftLegPose.x", Math.toDegrees(armorStand.getLeftLegPose().getX()));
        TypesConfig.get().set(typeName + ".leftLegPose.y", Math.toDegrees(armorStand.getLeftLegPose().getY()));
        TypesConfig.get().set(typeName + ".leftLegPose.z", Math.toDegrees(armorStand.getLeftLegPose().getZ()));

        TypesConfig.save();
        TypesConfig.reload();
    }

}
