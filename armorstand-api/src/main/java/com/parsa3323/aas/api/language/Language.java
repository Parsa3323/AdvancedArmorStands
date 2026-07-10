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

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Language {

    private static final Map<String, Language> languages = new HashMap<>();
    private static Language defaultLanguage;

    private final Plugin plugin;
    private final String iso;
    private final File configFile;
    private YamlConfiguration yml;

    private final String prefix = "";

    protected Language(Plugin plugin, String iso) {
        this.plugin = plugin;
        this.iso = iso.toLowerCase();

        File folder = new File(plugin.getDataFolder(), "languages");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        this.configFile = new File(folder, "messages_" + this.iso + ".yml");

        load();
        registerDefaults();
        save();

        languages.put(this.iso, this);
    }

    protected void registerDefaults() {
    }

    public static void loadLanguages(Plugin plugin, String configuredLanguage) {
        File folder = new File(plugin.getDataFolder(), "languages");
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (!file.isFile()) {
                    continue;
                }

                String name = file.getName();
                if (!name.startsWith("messages_") || !name.endsWith(".yml")) {
                    continue;
                }
            }
        }

        Language lang = getLang(configuredLanguage.toLowerCase());
        if (lang == null) {
            lang = getLang("en");
        }

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
            this.yml = new YamlConfiguration();
        }
    }

    public void save() {
        try {
            yml.options().copyDefaults(true);
            yml.save(configFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void reload() {
        load();
    }

    public String getString(String path) {
        return yml.getString(path);
    }

    public YamlConfiguration getYml() {
        return yml;
    }

    public static String getMsg(String path) {
        if (defaultLanguage == null) {
            return "Missing: " + path;
        }

        String msg = defaultLanguage.getString(path);
        if (msg == null) {
            return "Missing: " + path;
        }

        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static Language getLang(String iso) {
        return languages.get(iso.toLowerCase());
    }


    public static List<String> getLore(String path) {

        List<String> lore = new ArrayList<>();

        if (defaultLanguage == null || defaultLanguage.yml == null) {
            lore.add("Missing language");
            return lore;
        }

        Object value = defaultLanguage.yml.get(path);

        if (value == null) {
            lore.add("Missing: " + path);
            return lore;
        }

        if (value instanceof List) {

            List<?> raw = (List<?>) value;

            for (Object obj : raw) {
                lore.add(ChatColor.translateAlternateColorCodes('&', String.valueOf(obj)));
            }

            return lore;
        }

        String raw = String.valueOf(value);

        String[] lines = raw.split("\\r?\\n");

        for (String line : lines) {
            lore.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        return lore;
    }

    public static void setDefaultLanguage(Language lang) {
        defaultLanguage = lang;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public String getIso() {
        return iso;
    }

    public File getConfigFile() {
        return configFile;
    }

}