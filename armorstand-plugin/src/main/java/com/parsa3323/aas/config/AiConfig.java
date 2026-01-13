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

package com.parsa3323.aas.config;

import com.parsa3323.aas.AdvancedArmorStands;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class AiConfig {
    private static File file;
    private static YamlConfiguration fileConfiguration;

    private static final String KEY = "1234567890123456";

    public static void init() {
        file = new File(AdvancedArmorStands.plugin.getDataFolder(), "cache/ai.yml");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
                fileConfiguration = new YamlConfiguration();
                save();
            } catch (IOException e) {
                e.printStackTrace();
                AdvancedArmorStands.error("Failed to load " + file.getName() +  ": " + e.getMessage(), true);
            }
        } else {
            reload();
        }
    }

    public static YamlConfiguration get() {
        return fileConfiguration;
    }

    public static void save() {
        try {
            String yamlString = fileConfiguration.saveToString();
            byte[] encrypted = encrypt(yamlString, KEY);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(encrypted);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AdvancedArmorStands.error("Failed to save " + file.getName() +  ": " + e.getMessage(), true);

        }
    }

    public static void reload() {
        try {
            byte[] encryptedBytes = Files.readAllBytes(file.toPath());
            if (encryptedBytes.length == 0) {
                fileConfiguration = new YamlConfiguration();
                return;
            }
            String decryptedYaml = decrypt(encryptedBytes, KEY);
            fileConfiguration = YamlConfiguration.loadConfiguration(new StringReader(decryptedYaml));
        } catch (Exception e) {
            e.printStackTrace();
            fileConfiguration = new YamlConfiguration();
            AdvancedArmorStands.error("Failed to reload " + file.getName() +  ": " + e.getMessage(), true);
        }
    }


    private static byte[] encrypt(String data, String key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }

    private static String decrypt(byte[] encrypted, String key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}
