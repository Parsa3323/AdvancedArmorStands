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

package com.parsa3323.aas;

import com.parsa3323.aas.api.ArmorstandApi;
import com.parsa3323.aas.api.versionSupport.IVersionSupport;
import com.parsa3323.aas.commands.CreateCommand;
import com.parsa3323.aas.commands.manager.CommandManager;
import com.parsa3323.aas.commands.manager.TabComp;
import com.parsa3323.aas.configs.AnimationConfig;
import com.parsa3323.aas.configs.ArmorStands;
import com.parsa3323.aas.configs.TypesConfig;
import com.parsa3323.aas.inventory.manager.InventoryManager;
import com.parsa3323.aas.listener.*;
import com.parsa3323.aas.menus.manager.MenuListener;
import com.parsa3323.aas.placeholderapi.PapiExpansion;
import com.parsa3323.aas.utils.AnimationUtils;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AdvancedArmorStands extends JavaPlugin {

    private static Logger logger;
    public static Level logLevel;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    public IVersionSupport versionSupport;

    public static ArmorstandApi api;

    public static AdvancedArmorStands plugin;

    @Override
    public void onEnable() {
        logger = getLogger();
        api = new API();

        getServer().getServicesManager().register(ArmorstandApi.class, api, this, ServicePriority.Normal);

        getLogger().info("Loading " + getDescription().getName() + " v" + getDescription().getVersion());

        versionSupport = VersionSupportUtil.getVersionSupport();

        if (versionSupport == null) {
            getLogger().severe("Could not find a version support for " + VersionSupportUtil.getVersion());
            setEnabled(false);
            return;
        }

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        plugin = this;


        saveDefaultConfig();

        boolean levelName = getConfig().getBoolean("debug", false);


        logLevel = (levelName) ? Level.FINE : Level.INFO;
        logger.setLevel(logLevel);

        Metrics metrics = new Metrics(this, 25568);

        status("Registering events");
        PluginManager ev = getServer().getPluginManager();
        ev.registerEvents(new ChatListener(), this);
        ev.registerEvents(new PlayerDieListener(), this);
        ev.registerEvents(new InventoryManager(), this);
        ev.registerEvents(new PlayerLeaveEvent(), this);
        ev.registerEvents(new StateListener(), this);
        ev.registerEvents(new PlayerJoin(), this);
        ev.registerEvents(new CreateCommand(), this);
        ev.registerEvents(new PlayerIntractListener(), this);
        ev.registerEvents(new MenuListener(), this);
        ev.registerEvents(new ItemDropListener(), this);

        status("Loading configs");

        AnimationConfig.init();

        FileConfiguration animations = AnimationConfig.get();

        animations.addDefault("animations.wave.interval", 10);
        animations.addDefault("animations.wave.loop", true);
        animations.addDefault("animations.wave.steps", new ArrayList<>());
        animations.options().copyDefaults(true);


        AnimationConfig.get().options().copyDefaults(true);
        AnimationConfig.save();


        TypesConfig.init();

        TypesConfig.get().addDefault("default.Arms", true);
        TypesConfig.get().addDefault("default.Gravity", false);
        TypesConfig.get().addDefault("default.BasePlate", false);
        TypesConfig.get().addDefault("default.CustomName", "&7Made with &6AdvancedArmorStands");
        TypesConfig.get().addDefault("default.isCustomNameVisible", false);
        TypesConfig.get().addDefault("default.itemInHandMaterial", VersionSupportUtil.getVersionSupport().getMaterialForVersion("WOOD_SWORD"));
        TypesConfig.get().addDefault("default.HeadPos.x", null);
        TypesConfig.get().addDefault("default.HeadPos.y", null);
        TypesConfig.get().addDefault("default.HeadPos.z", null);
        TypesConfig.get().addDefault("default.rightArmPose.x", -45);
        TypesConfig.get().addDefault("default.rightArmPose.y", 0);
        TypesConfig.get().addDefault("default.rightArmPose.z", 0);
        TypesConfig.get().addDefault("default.leftArmPose.x", 45);
        TypesConfig.get().addDefault("default.leftArmPose.y", 0);
        TypesConfig.get().addDefault("default.leftArmPose.z", 0);
        TypesConfig.get().addDefault("default.rightLegPose.x", 45);
        TypesConfig.get().addDefault("default.rightLegPose.y", 0);
        TypesConfig.get().addDefault("default.rightLegPose.z", 0);
        TypesConfig.get().addDefault("default.leftLegPose.x", -45);
        TypesConfig.get().addDefault("default.leftLegPose.y", 0);
        TypesConfig.get().addDefault("default.leftLegPose.z", 0);

        TypesConfig.get().options().copyDefaults(true);
        TypesConfig.save();

        ArmorStands.init();
        ArmorStands.get().options().copyDefaults(true);
        ArmorStands.save();
        status("Registering Commands");
        getCommand("as").setTabCompleter(new TabComp());
        getCommand("as").setExecutor(new CommandManager());

        status("Registering papi expansion");
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            error("PlaceholderAPI was not found. Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        new PapiExpansion().register();

        if (getConfig().getBoolean("auto-load-armor-stands")) {
            status("Spawning armor stands");
        }
        for (String key : ArmorStandUtils.getArmorStandList()) {
            ArmorStandUtils.loadArmorStand(key);
        }

        status("Checking for armor stands");

        ArmorStandUtils.checkForArmorStands();


        status("Load done");

        AnimationUtils.init();

    }

    @Override
    public void onDisable() {
        status("Bye Bye...");
    }

    public static void debug(String message) {
        if (logLevel.intValue() <= Level.FINE.intValue()) {
            logger.info("[DEBUG] " + message);
        }
    }

    public static void info(String message) {
        if (logLevel.intValue() <= Level.INFO.intValue()) {
            logger.info("[INFO] " + message);
        }
    }

    public static void status(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        if (logLevel.intValue() <= Level.WARNING.intValue()) {
            logger.warning("[WARNING] " + message);
        }
    }

    public static void error(String message) {
        if (logLevel.intValue() <= Level.SEVERE.intValue()) {
            logger.severe("[ERROR] " + message);
        }
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (playerMenuUtilityMap.containsKey(p)) {
            return playerMenuUtilityMap.get(p);
        } else {
            playerMenuUtility = new PlayerMenuUtility(p);

            playerMenuUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        }
    }




}
