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

package com.parsa3323.aas;

import com.cryptomorin.xseries.XMaterial;
import com.parsa3323.aas.animation.manager.EditorManager;
import com.parsa3323.aas.api.ArmorstandApi;
import com.parsa3323.aas.api.actions.IssueLevel;
import com.parsa3323.aas.api.data.ArmorStandPoseData;
import com.parsa3323.aas.api.exeption.ArmorStandNotFoundException;
import com.parsa3323.aas.api.versionSupport.VersionSupport;
import com.parsa3323.aas.commands.CreateCommand;
import com.parsa3323.aas.commands.manager.CommandManager;
import com.parsa3323.aas.commands.manager.TabComplete;
import com.parsa3323.aas.config.*;
import com.parsa3323.aas.inventory.manager.InventoryManager;
import com.parsa3323.aas.listener.*;
import com.parsa3323.aas.menus.manager.MenuListener;
import com.parsa3323.aas.placeholderapi.PapiExpansion;
import com.parsa3323.aas.utils.*;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AdvancedArmorStands extends JavaPlugin {

    private static Logger logger;

    public static Level logLevel;

    private final String CURRENT_CONFIG_VERSION = "1.0.1";

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    public VersionSupport versionSupport;

    public static ArmorstandApi api;

    public static boolean isFirstTimeRunning = false;

    private static boolean isPapiAvailable = false;

    private static boolean migrating = false;

    private static String aiApiKey;

    private static boolean isAiEnabled = false;

    public static boolean CONFIG_OUTDATED = false;

    public static boolean isMigrating() {
        return migrating;
    }

    public static void setMigrating(boolean migrating) {
        AdvancedArmorStands.migrating = migrating;
    }

    public static AdvancedArmorStands plugin;

    public static ArmorstandApi getApi() {
        return api;
    }

    public static boolean isIsPapiAvailable() {
        return isPapiAvailable;
    }

    public static void setIsPapiAvailable(boolean isPapiAvailable) {
        AdvancedArmorStands.isPapiAvailable = isPapiAvailable;
    }

    public static String getAiApiKey() {
        return aiApiKey;
    }

    public static boolean isIsAiEnabled() {
        return isAiEnabled;
    }

    @Override
    public void onEnable() {
        logger = getLogger();

        getLogger().info("Loading " + getDescription().getName() + " v" + getDescription().getVersion());

        versionSupport = VersionSupportUtil.getVersionSupport();

        if (!getDataFolder().exists()) {
            isFirstTimeRunning = true;
            getDataFolder().mkdirs();
        }
        plugin = this;


        boolean levelName = getConfig().getBoolean("debug", false);


        logLevel = (levelName) ? Level.FINE : Level.INFO;
        logger.setLevel(logLevel);

        Metrics metrics = new Metrics(this, 25568);

        if (isFirstTimeRunning) {
            status("§aThank you for installing this plugin ❤");
            status("§e" + getDescription().getDescription());
        }

        status("Registering event listeners...");

        PluginManager pm = getServer().getPluginManager();

        Listener[] listeners = new Listener[] {
                new ChatListener(),
                new PlayerDieListener(),
                new InventoryManager(),
                new EditorManager(),
                new PlayerLeaveListener(),
                new InventoryClickListener(),
                new StateChangeListener(),
                new PlayerJoinListener(),
                new CreateCommand(),
                new PlayerInteractListener(),
                new MenuListener(),
                new ItemDropListener(),
                new PlayerBlockBreakListener(),
                new BookInputListener(),
                new ChatTabListener()
        };


        for (Listener listener : listeners) {
            pm.registerEvents(listener, this);
            getLogger().info("Loaded listener: " + listener.getClass().getSimpleName());
        }

        status("Checking requirements...");


        VersionSupportUtil.getVersionSupport();
        try {

            VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTVjZWQzNTI4N2JmYTAxNjY1ZGE3MjQ3MjM5YmEyNDE0YzE5MzZjNTZkMmU1YjIwMjdkMDUzMGQ5Yjk3MjUzMCJ9fX0=");
            debug("XSeries support is available.");
        } catch (Throwable t) {
            error("XSeries are not supported on this server: " + t.getClass().getSimpleName() + ": " + t.getMessage());
            error(" ");
            error("XSeries features are not supported on this server. Please use plugin version 1.0.0-beta.15 or older (not recommended): https://github.com/Parsa3323/AdvancedArmorStands/releases/tag/v1.0.0-beta.15");
            warn("Read more: https://docs.advancedarmorstands.ir/version-support-error");
            warn("Using older versions is not recommended and may lead to other issues.");

            Bukkit.getPluginManager().disablePlugin(this);
        }

        status("Loading configuration files...");

        checkConfig();

        api = new API();
        getServer().getServicesManager().register(ArmorstandApi.class, api, this, ServicePriority.Normal);

        AnimationConfig.init();

        FileConfiguration animations = AnimationConfig.get();

        animations.addDefault("animations.wave.interval", 10);
        animations.addDefault("animations.wave.loop", true);
        animations.addDefault("animations.wave.steps", new ArrayList<>());
        animations.options().copyDefaults(true);

        AnimationConfig.get().options().copyDefaults(true);
        AnimationConfig.save();
        TypesConfig.init();

        IssueUtils.init();

        ActionConfig.init();
        ActionConfig.get().options().copyDefaults(true);
        ActionConfig.save();

        TypesConfig.get().addDefault("default.arms", true);
        TypesConfig.get().addDefault("default.basePlate", false);
        TypesConfig.get().addDefault("default.customName", "&7Made with &6&lA&e&ld&6&lv&e&la&6&ln&e&lc&6&le&e&ld&6&lA&e&lr&6&lm&e&lo&6&lr&e&lS&6&lt&e&la&6&ln&e&ld&6&ls");
        TypesConfig.get().addDefault("default.isCustomNameVisible", false);
        TypesConfig.get().addDefault("default.isVisible", true);
        TypesConfig.get().addDefault("default.isSmall", false);
        TypesConfig.get().addDefault("default.itemInHandMaterial", XMaterial.WOODEN_SWORD.parseMaterial().name());
        TypesConfig.get().addDefault("default.headPos.x", null);
        TypesConfig.get().addDefault("default.headPos.y", null);
        TypesConfig.get().addDefault("default.headPos.z", null);
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

        TypeUtils.migrateTypes();

        TypesConfig.save();

        ArmorStandsConfig.init();
        ArmorStandsConfig.get().options().copyDefaults(true);
        ArmorStandsConfig.save();

        if (checkForArmorStandConflictAndDelete()) {
            warn("ArmorStand name conflicts found. Deleting one...");
        }

        AiConfig.init();
        AiConfig.get().options().copyDefaults(true);
        AiConfig.save();

        status("Loading Artificial Intelligence...");

        aiApiKey = getConfig().getString("ai.token");

        if (aiApiKey != null && !aiApiKey.equalsIgnoreCase("PLACE_YOUR_TOKEN_HERE") && !aiApiKey.trim().isEmpty()) {
            isAiEnabled = true;

            status("Artificial Intelligence is enabled.");
        } else {
            status("Artificial Intelligence is disabled, see config for more details.");
        }

        status("Registering commands...");

        CommandManager commandManager = new CommandManager();
        getCommand("as").setTabCompleter(new TabComplete());
        getCommand("as").setExecutor(commandManager);

        commandManager.getSubCommands().forEach(subCommand -> {
            status("Registered '" + subCommand.getName() + "' command");
        });

        status("Registered " + commandManager.getAmount() + " commands");

        status("Hooking into PlaceholderAPI...");
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            status("PlaceholderAPI was found. Registering expansions");
            new PapiExpansion().register();
            setIsPapiAvailable(true);
        }

        if (getConfig().getBoolean("auto-load-armor-stands")) {
            status("Spawning armor stands...");
        }

        for (String key : ArmorStandUtils.getArmorStandList()) {
            ArmorStandUtils.autoLoadArmorStand(key);
        }

        status("Checking armor stands...");
        ArmorStandUtils.checkArmorStandsFirstTime();
        ArmorStandUtils.checkForArmorStands();

        status("Preparing animations...");

        AnimationUtils.init();

        AnimationUtils.getAnimationsList().forEach(s -> {
            status("Loaded '" + s + "'!");
        });

        status("All systems loaded successfully!");
    }

    @Override
    public void onDisable() {
        status("Restoring gamemodes for players in edit session...");

        Bukkit.getOnlinePlayers().forEach(player -> {
            if (ArmorStandSelectionCache.isIsInEditSession(player)) {
                if (InventoryUtils.hasGameMode(player)) {
                    player.setGameMode(InventoryUtils.getAndClearGameMode(player));
                    status("Restored " + player.getDisplayName() + "'s gamemode");
                }
            }
        });

        status("Restoring inventories for players in edit session...");

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (InventoryUtils.hasBackup(player)) {
                InventoryUtils.restore(player);
                InventoryUtils.save(player);
                status("Restored " + player.getDisplayName() +"'s inventory");
            }

            if (ArmorStandSelectionCache.isInKeyFrameList(player)) {

                ArmorStandSelectionCache.removeFromKeyFrameList(player);
                ArmorStand armorStand = ArmorStandSelectionCache.getKeyFrameSelectedArmorStand(player.getUniqueId());
                AdvancedArmorStands.debug(armorStand.getName());

                ArmorStandPoseData savedPose = ArmorStandUtils.getPose(armorStand.getUniqueId());

                armorStand.setRightArmPose(savedPose.getRightArm());
                armorStand.setLeftArmPose(savedPose.getLeftArm());
                armorStand.setRightLegPose(savedPose.getRightLeg());
                armorStand.setLeftLegPose(savedPose.getLeftLeg());
                armorStand.setHeadPose(savedPose.getHead());

                ArmorStandSelectionCache.removeKeyFrameSelectedArmorStand(player.getUniqueId());

                status("Restored " + player.getDisplayName() +"'s inventory");
            }

        }

        status("Plugin has been successfully disabled!");
    }

    public void checkConfig() {
        File configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            saveDefaultConfig();
            return;
        }

        String configVersion = getConfig().getString("config-version");
        if (!CURRENT_CONFIG_VERSION.equals(configVersion)) {
            debug("Plugin version and config version mismatch!");
            debug("Plugin expects: " + CURRENT_CONFIG_VERSION + " but config has: " + configVersion);
        }


        String existingVersion = getConfig().getString("config-version", "unknown");

        if (!CURRENT_CONFIG_VERSION.equals(existingVersion)) {
            debug("Old config version detected (" + existingVersion + ").");
            error("Config version is outdated! Please consider updating config.yml manually.", "https://docs.advancedarmorstands.ir/config-version-outdated/");
            CONFIG_OUTDATED = true;
        }

        ConfigUtils.renameOldFileIfNeeded();
    }

    public static boolean checkForArmorStandConflictAndDelete() {
        List<String> list = ArmorStandUtils.getArmorStandList();
        Set<String> seen = new HashSet<>();

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String name = it.next();
            String key = name.toLowerCase(Locale.ROOT);

            if (!seen.add(key)) {
                it.remove();

                try {
                    ArmorStandUtils.deleteArmorStand(name);
                } catch (ArmorStandNotFoundException e) {
                    error("Error while deleting armorstand: " + e.getMessage());
                }
                return true;
            }
        }
        return false;
    }


    public static boolean isDebug() {
        return logLevel.intValue() <= Level.FINE.intValue() || plugin.getConfig().getBoolean("debug");
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
            IssueUtils.record(IssueLevel.WARNING, message, null);
        }
    }

    public static void error(String message) {
        error(message, null, false);
    }

    public static void error(String message, boolean sendTbLink) {
        error(message, "https://docs.advancedarmorstands.ir/troubleshooting", sendTbLink);
    }

    public static void error(String message, String readMoreLink) {
        error(message, readMoreLink, false);
    }

    private static void error(String message, String readMoreLink, boolean sendTbLink) {
        if (logLevel.intValue() <= Level.SEVERE.intValue()) {
            logger.severe("[ERROR] " + message);

            if (readMoreLink != null) {
                warn("Learn more: " + readMoreLink);
            }

            if (sendTbLink) {
                warn("Troubleshooting: https://docs.advancedarmorstands.ir/troubleshooting");
            }

            IssueUtils.record(IssueLevel.ERROR, message, readMoreLink);
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
