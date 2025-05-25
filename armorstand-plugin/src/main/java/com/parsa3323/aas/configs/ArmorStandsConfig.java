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

package com.parsa3323.aas.configs;

import com.parsa3323.aas.AdvancedArmorStands;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ArmorStandsConfig {
    private static File file;

    private static FileConfiguration fileConfiguration;

    public static void init() {
        file = new File(AdvancedArmorStands.plugin.getDataFolder(), "cache/armorstands.yml");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);


    }

    public static FileConfiguration get() {
        return fileConfiguration;
    }

    public static void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while saving : " + e.getMessage());
        }

    }

    public static void reload(){
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    private static void moveOldFileIfNeeded() {
        File oldFile = new File(AdvancedArmorStands.plugin.getDataFolder(), "caches/armorstands.yml");
        File newFile = new File(AdvancedArmorStands.plugin.getDataFolder(), "cache/armorstands.yml");

        if (oldFile.exists() && !newFile.exists()) {
            try {
                Files.move(oldFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                AdvancedArmorStands.info("Moved armorstands.yml from caches/ to cache/ folder.");
            } catch (IOException e) {
                AdvancedArmorStands.error("Failed to move armorstands.yml to new cache folder: " + e.getMessage(), true);
            }
        }
    }

}
