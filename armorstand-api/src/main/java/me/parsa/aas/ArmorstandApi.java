package me.parsa.aas;


import org.bukkit.configuration.file.FileConfiguration;

public interface ArmorstandApi  {

    ConfigManager getConfigManager();

    interface ConfigManager {

        FileConfiguration getMainConfig();

        FileConfiguration getCacheConfig();

        FileConfiguration getTypesConfig();

    }

    LogsManager getLogManager();

    interface LogsManager {

        void debug(String args);

        void info(String args);

    }

}
