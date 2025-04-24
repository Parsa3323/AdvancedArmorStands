/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.Q
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.parsa.aas;

import me.parsa.aas.Commands.CreateCommand;
import me.parsa.aas.Commands.Manager.CommandManager;
import me.parsa.aas.Commands.Manager.TabComp;
import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Configs.TypesConfig;
import me.parsa.aas.Listener.ItemDropListener;
import me.parsa.aas.Listener.PlayerIntractListener;
import me.parsa.aas.Listener.PlayerLeaveEvent;
import me.parsa.aas.Menus.Manager.MenuListener;
import me.parsa.aas.PlaceHolderApi.PapiExpansion;
import me.parsa.aas.Utils.PlayerMenuUtility;
import me.parsa.aas.Utils.VersionSupportUtil;
import me.parsa.aas.VersionSupport.IVersionSupport;
import me.parsa.aas.inventory.manager.InventoryManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

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

        info("Registering events");
        PluginManager ev = getServer().getPluginManager();
        ev.registerEvents(new InventoryManager(), this);
        ev.registerEvents(new PlayerLeaveEvent(), this);
        ev.registerEvents(new CreateCommand(), this);
        ev.registerEvents(new PlayerIntractListener(), this);
        ev.registerEvents(new MenuListener(), this);
        ev.registerEvents(new ItemDropListener(), this);

        info("Loading configs");

        TypesConfig.init();


        TypesConfig.get().addDefault("default.Arms", true);
        TypesConfig.get().addDefault("default.Gravity", false);
        TypesConfig.get().addDefault("default.BasePlate", false);
        TypesConfig.get().addDefault("default.CustomName", "&cMade with aas");
        TypesConfig.get().addDefault("default.isCustomNameVisible", false);
        TypesConfig.get().addDefault("default.itemInHandMaterial", "WOOD_SWORD");
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
        info("Registering Commands");
        getCommand("as").setTabCompleter(new TabComp());
        getCommand("as").setExecutor(new CommandManager());

        info("Registering papi expansion");
        new PapiExpansion().register();

        info("Load done");


    }

    @Override
    public void onDisable() {
        info("Bye Bye...");
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
