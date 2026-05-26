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

package com.parsa3323.aas.api.language;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Language {

    private static final Map<String, Language> languages = new HashMap<>();

    private static Language defaultLanguage;

    private final Plugin plugin;

    private final File configFile;

    private YamlConfiguration yml;

    private final String prefix = "";

    public Language(Plugin plugin, String iso) {
        this.plugin = plugin;

        File folder = new File(plugin.getDataFolder(), "languages");

        if (!folder.exists()) folder.mkdirs();


        this.configFile = new File(
                folder,
                "messages_" + iso + ".yml"
        );

        load();
        languages.put(iso, this);
    }


    public static Language getDefaultLanguage() {
        return defaultLanguage;
    }

    public static void loadLanguages(Plugin plugin, String configuredLanguage) {
        File folder = new File(plugin.getDataFolder(), "languages");

        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {

                if (!file.isFile()) continue;


                String name = file.getName();
                if (!name.startsWith("messages_") || !name.endsWith(".yml")) continue;

                String iso = name.replace("messages_", "").replace(".yml", "");

                if (getLang(iso) == null) new Language(plugin, iso);
            }
        }

        Language lang = getLang(configuredLanguage.toLowerCase());

        if (lang == null) lang = getLang("en");

        defaultLanguage = lang;
    }

    public void load() {
        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
            }

            this.yml = YamlConfiguration.loadConfiguration(configFile);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void save() {
        try {
            yml.save(configFile);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void reload() {
        this.yml = YamlConfiguration.loadConfiguration(configFile);
    }

    public String getString(String path) {
        return yml.getString(path);
    }

    public YamlConfiguration getYml() {
        return yml;
    }

    public static String getMsg(String path) {
        String msg = defaultLanguage.getString(path);

        if (msg == null) {
            return "Missing: " + path;
        }

        return msg;
    }

    public static Language getLang(String iso) {
        return languages.get(iso);
    }

    public static void setDefaultLanguage(Language lang) {
        defaultLanguage = lang;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public File getConfigFile() {
        return configFile;
    }
}