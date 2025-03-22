package me.parsa.aas;

import me.parsa.aas.Commands.CreateCommand;
import me.parsa.aas.Commands.Manager.CommandManager;
import me.parsa.aas.Commands.Manager.TabComp;
import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Configs.TypesConfig;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class AdvancedArmorStands extends JavaPlugin {

    private static Logger logger;
    public static Level logLevel;

    public static ArmorstandApi api;

    public static String prefix = ChatColor.GOLD + "Advanced" + ChatColor.YELLOW + "ArmorStands " + ChatColor.GOLD + "» ";

    public static AdvancedArmorStands plugin;

    @Override
    public void onEnable() {
        logger = getLogger();
        api = new API();

        getServer().getServicesManager().register(ArmorstandApi.class, api, this, ServicePriority.Normal);

        getLogger().info("Loading " + getDescription().getName() + " v" + getDescription().getVersion());

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        plugin = this;
        saveDefaultConfig();

        boolean levelName = getConfig().getBoolean("debug", false);

        logLevel = (levelName) ? Level.FINE : Level.INFO;
        logger.setLevel(logLevel);

        info("Registering events");
        PluginManager ev = getServer().getPluginManager();
        ev.registerEvents(new CreateCommand(), this);

        info("Loading configs");
        TypesConfig.setup();


        // Add default types
        TypesConfig.get().addDefault("running.Arms", true);
        TypesConfig.get().addDefault("running.Gravity", false);
        TypesConfig.get().addDefault("running.BasePlate", false);
        TypesConfig.get().addDefault("running.CustomName", "&cMade with aas");
        TypesConfig.get().addDefault("running.isCustomNameVisible", false);
        TypesConfig.get().addDefault("running.itemInHandMaterial", "WOOD_SWORD");
        TypesConfig.get().addDefault("running.HeadPos.x", null);
        TypesConfig.get().addDefault("running.HeadPos.y", null);
        TypesConfig.get().addDefault("running.HeadPos.z", null);
        TypesConfig.get().addDefault("running.rightArmPose.x", -45);
        TypesConfig.get().addDefault("running.rightArmPose.y", 0);
        TypesConfig.get().addDefault("running.rightArmPose.z", 0);
        TypesConfig.get().addDefault("running.leftArmPose.x", 45);
        TypesConfig.get().addDefault("running.leftArmPose.y", 0);
        TypesConfig.get().addDefault("running.leftArmPose.z", 0);
        TypesConfig.get().addDefault("running.rightLegPose.x", 45);
        TypesConfig.get().addDefault("running.rightLegPose.y", 0);
        TypesConfig.get().addDefault("running.rightLegPose.z", 0);
        TypesConfig.get().addDefault("running.leftLegPose.x", -45);
        TypesConfig.get().addDefault("running.leftLegPose.y", 0);
        TypesConfig.get().addDefault("running.leftLegPose.z", 0);

        TypesConfig.get().options().copyDefaults(true);
        TypesConfig.save();

        ArmorStands.setup();
        ArmorStands.get().options().copyDefaults(true);
        ArmorStands.save();
        info("Registering Commands");
        getCommand("as").setTabCompleter(new TabComp());
        getCommand("as").setExecutor(new CommandManager());

        info("Registering papi expansion");
        // TODO: Register expansions

        info("Load done");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
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
}
