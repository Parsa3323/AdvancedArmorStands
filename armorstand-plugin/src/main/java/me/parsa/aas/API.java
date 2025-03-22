package me.parsa.aas;

import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Configs.TypesConfig;
import org.bukkit.configuration.file.FileConfiguration;

public class API implements ArmorstandApi{
    @Override
    public ConfigManager getConfigManager() {
        return new ConfigManager() {
            @Override
            public FileConfiguration getMainConfig() {
                return AdvancedArmorStands.plugin.getConfig();
            }

            @Override
            public FileConfiguration getCacheConfig() {
                return ArmorStands.get();
            }

            @Override
            public FileConfiguration getTypesConfig() {
                return TypesConfig.get();
            }
        };
    }
}
