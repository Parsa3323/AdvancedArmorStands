package me.parsa.aas.Commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.parsa.aas.AdvancedArmorStands;
import me.parsa.aas.Commands.Manager.SubCommand;
import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Configs.TypesConfig;
import me.parsa.aas.Events.ArmorStandCreateEvent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CreateCommand extends SubCommand implements Listener {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create an armor stand";
    }

    @Override
    public String getSyntax() {
        return "/as create <type> <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        AdvancedArmorStands.debug("Args: " + args.length);
        if (args.length < 3) {
            sendUsage(player);
            return;
        }
        if (!args[1].equalsIgnoreCase("custom")) {

            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

            ArmorStandCreateEvent armorStandCreateEvent = new ArmorStandCreateEvent(player, armorStand, args[2]);
            Bukkit.getPluginManager().callEvent(armorStandCreateEvent);

            if (armorStandCreateEvent.isCancelled()) {
                armorStand.remove();
                return;
            }

            if (!TypesConfig.get().contains(args[1])) {
                player.sendMessage(ChatColor.RED + "No type found named: " + args[1]);
                return;
            }
            armorStand.setArms(TypesConfig.get().getBoolean(args[1] + ".Arms"));
            armorStand.setGravity(TypesConfig.get().getBoolean(args[1] + ".Gravity"));
            armorStand.setBasePlate(TypesConfig.get().getBoolean(args[1] + ".BasePlate"));
            String path = args[1] + ".CustomName"; // Ensure args[1] is a valid key
            String rawName = TypesConfig.get().getString(path, "&cDefault Name"); // Default in case key doesn't exist
            String replacedName = PlaceholderAPI.setPlaceholders(player, rawName); // Apply PlaceholderAPI replacements
            String coloredName = ChatColor.translateAlternateColorCodes('&', replacedName); // Convert color codes
            armorStand.setCustomName(coloredName);

            armorStand.setCustomNameVisible(TypesConfig.get().getBoolean(args[1] + ".isCustomNameVisible"));
            armorStand.setItemInHand(new ItemStack(Material.valueOf(TypesConfig.get().getString(args[1] + ".itemInHandMaterial"))));

            armorStand.setHeadPose(new EulerAngle(
                    Math.toRadians(getConfigDouble(args[1] + ".HeadPos.x")),
                    Math.toRadians(getConfigDouble(args[1] + ".HeadPos.y")),
                    Math.toRadians(getConfigDouble(args[1] + ".HeadPos.z")) // <-- Fixed missing dot
            ));

            armorStand.setRightArmPose(new EulerAngle(
                    Math.toRadians(getConfigDouble(args[1] + ".rightArmPose.x")),
                    Math.toRadians(getConfigDouble(args[1] + ".rightArmPose.y")),
                    Math.toRadians(getConfigDouble(args[1] + ".rightArmPose.z"))
            ));
            AdvancedArmorStands.debug("rightArmPose.x = " + getConfigDouble(args[1] + ".rightArmPose.x"));
            AdvancedArmorStands.debug("rightArmPose.y = " + getConfigDouble(args[1] + ".rightArmPose.y"));
            AdvancedArmorStands.debug("rightArmPose.z = " + getConfigDouble(args[1] + ".rightArmPose.z"));

            armorStand.setLeftArmPose(new EulerAngle(
                    Math.toRadians(getConfigDouble(args[1] + ".leftArmPose.x")),
                    Math.toRadians(getConfigDouble(args[1] + ".leftArmPose.y")),
                    Math.toRadians(getConfigDouble(args[1] + ".leftArmPose.z"))
            ));
            AdvancedArmorStands.debug("leftArmPose.x = " + getConfigDouble(args[1] + ".leftArmPose.x"));
            AdvancedArmorStands.debug("leftArmPose.y = " + getConfigDouble(args[1] + ".leftArmPose.y"));
            AdvancedArmorStands.debug("leftArmPose.z = " + getConfigDouble(args[1] + ".leftArmPose.z"));

            armorStand.setRightLegPose(new EulerAngle(
                    Math.toRadians(getConfigDouble(args[1] + ".rightLegPose.x")),
                    Math.toRadians(getConfigDouble(args[1] + ".rightLegPose.y")),
                    Math.toRadians(getConfigDouble(args[1] + ".rightLegPose.z"))
            ));
            AdvancedArmorStands.debug("rightLegPose.x = " + getConfigDouble(args[1] + ".rightLegPose.x"));
            AdvancedArmorStands.debug("rightLegPose.y = " + getConfigDouble(args[1] + ".rightLegPose.y"));
            AdvancedArmorStands.debug("rightLegPose.z = " + getConfigDouble(args[1] + ".rightLegPose.z"));

            armorStand.setLeftLegPose(new EulerAngle(
                    Math.toRadians(getConfigDouble(args[1] + ".leftLegPose.x")),
                    Math.toRadians(getConfigDouble(args[1] + ".leftLegPose.y")),
                    Math.toRadians(getConfigDouble(args[1] + ".leftLegPose.z"))
            ));
            AdvancedArmorStands.debug("leftLegPose.x = " + getConfigDouble(args[1] + ".leftLegPose.x"));
            AdvancedArmorStands.debug("leftLegPose.y = " + getConfigDouble(args[1] + ".leftLegPose.y"));
            AdvancedArmorStands.debug("leftLegPose.z = " + getConfigDouble(args[1] + ".leftLegPose.z"));
          //  saveArmorStand(args[2], armorStand);
        } else {
            if (args.length <= 7) {
                player.sendMessage(AdvancedArmorStands.prefix + ChatColor.GRAY + "Usage: " + ChatColor.BOLD + "/as create custom <rightArm> <leftArm> <rightLeg> <leftLeg> <Headpos> <Name>");
                return;
            }


            int rightArm = Integer.parseInt(args[2]);
            int leftArm = Integer.parseInt(args[3]);
            int rightLeg = Integer.parseInt(args[4]);
            int leftLeg = Integer.parseInt(args[5]);
            int Headpos = Integer.parseInt(args[6]);
            String Name = args[7];

            ArmorStand stand = spawnCustomArmorStand(player.getWorld(), player.getLocation(), rightArm, leftArm, rightLeg, leftLeg, Headpos, player, Name);


           // saveArmorStand(Name, stand);

        }
    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        if (args.length == 2) {
            ArrayList<String> list = new ArrayList<>(TypesConfig.get().getKeys(false));
            list.add("custom");
            return list;
        } else {
            ArrayList<String> ll = new ArrayList<>();

            ll.add("");
            return ll;
        }
    }

    @Override
    public boolean isForOps() {
        return true;
    }

    public static ArmorStand spawnCustomArmorStand(@NotNull World world, @NotNull Location location, int rightArmPoseint, int leftArmPoseint, int rightLegPoseint, int leftLegPoseint, int Headpos, Player player, String name ) {


        ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);

        ArmorStandCreateEvent armorStandCreateEvent = new ArmorStandCreateEvent(player, armorStand, name);
        Bukkit.getPluginManager().callEvent(armorStandCreateEvent);

        if (armorStandCreateEvent.isCancelled()) {
            armorStand.remove();
            return null;

        }

        armorStand.setArms(true);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setCustomName("Runner");
        armorStand.setCustomNameVisible(false);

        armorStand.setItemInHand(new ItemStack(Material.IRON_PICKAXE));

        EulerAngle rightArmPose = new EulerAngle(Math.toRadians(rightArmPoseint), 0, 0);  // Right arm back
        EulerAngle leftArmPose = new EulerAngle(Math.toRadians(leftArmPoseint), 0, 0); // Left arm forward

        EulerAngle rightLegPose = new EulerAngle(Math.toRadians(rightLegPoseint), 0, 0);  // Right leg forward
        EulerAngle leftLegPose = new EulerAngle(Math.toRadians(leftLegPoseint), 0, 0); // Left leg backward

        armorStand.setHeadPose(new EulerAngle(Math.toRadians(0), Math.toRadians(Headpos), 0)); // Head facing forward

        armorStand.setRightArmPose(rightArmPose);
        armorStand.setLeftArmPose(leftArmPose);
        armorStand.setRightLegPose(rightLegPose);
        armorStand.setLeftLegPose(leftLegPose);
        return armorStand;
    }

    private double getConfigDouble(String path) {
        if (!TypesConfig.get().contains(path)) {
            AdvancedArmorStands.debug("Config path not found: " + path);
            return 0.0;
        }
        return TypesConfig.get().getDouble(path);
    }

    public static void saveArmorStand(String name, ArmorStand armorStand) {
        FileConfiguration config = ArmorStands.get();
        String path = "armorstands." + name; // Unique name for each ArmorStand

        config.set(path + ".UUID", armorStand.getUniqueId().toString()); // Store UUID
        config.set(path + ".World", armorStand.getLocation().getWorld().getName());
        config.set(path + ".X", armorStand.getLocation().getX());
        config.set(path + ".Y", armorStand.getLocation().getY());
        config.set(path + ".Z", armorStand.getLocation().getZ());

        ArmorStands.save(); // Save config
    }

    @EventHandler
    public void onArmorStandCreate(ArmorStandCreateEvent event) {
        ArmorStand armorStand = event.getArmorStand();
        String name = event.getName();

        FileConfiguration config = ArmorStands.get();
        String path = "armorstands." + name;

        if (config.contains(path)) {
            event.getPlayer().sendMessage(ChatColor.RED + "An ArmorStand with this name already exists!");
            event.setCancelled(true);
            return;
        }

        config.set(path + ".UUID", armorStand.getUniqueId().toString()); // Store UUID
        config.set(path + ".World", armorStand.getLocation().getWorld().getName());
        config.set(path + ".X", armorStand.getLocation().getX());
        config.set(path + ".Y", armorStand.getLocation().getY());
        config.set(path + ".Z", armorStand.getLocation().getZ());

        ArmorStands.save(); // Save config
    }


}
