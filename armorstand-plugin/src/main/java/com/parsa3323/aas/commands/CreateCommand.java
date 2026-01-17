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

package com.parsa3323.aas.commands;

import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.events.ArmorStandCreateEvent;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.config.TypesConfig;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.TypeUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.text.SimpleDateFormat;
import java.util.*;

public class CreateCommand extends SubCommand implements Listener {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create an ArmorStand";
    }

    @Override
    public String getSyntax() {
        return "/as create <type> <name>";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as create " + ((TypeUtils.getTypesList().get(0) == null) ? "default (example)" : TypeUtils.getTypesList().get(0)) + " testStand");
        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as create custom testStand head 1 2 3 right-hand 1 2 3");
        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as create none testStand");
        return lore;
    }

    @Override
    public void perform(Player player, String[] args) {
        AdvancedArmorStands.debug("Args: " + args.length);
        if (args.length < 3) {
            sendUsage(player);
            return;
        }
        String type = args[1].toLowerCase();

        String armorStandName = String.join("_", java.util.Arrays.copyOfRange(args, 2, args.length));

        if (type.equalsIgnoreCase("custom")) {
            if (args.length < 6) {
                player.sendMessage(ChatColor.RED + "Usage: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
                return;
            }

            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

            ArmorStandCreateEvent armorStandCreateEvent = new ArmorStandCreateEvent(player, armorStand, armorStandName);
            Bukkit.getPluginManager().callEvent(armorStandCreateEvent);

            if (armorStandCreateEvent.isCancelled()) {
                armorStand.remove();
                return;
            }

            armorStand.setArms(true);
            armorStand.setGravity(false);
            armorStand.setBasePlate(false);
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&7Made with &6&lA&e&ld&6&lv&e&la&6&ln&e&lc&6&le&e&ld&6&lA&e&lr&6&lm&e&lo&6&lr&e&lS&6&lt&e&la&6&ln&e&ld&6&ls"));
            armorStand.setCustomNameVisible(false);

            for (int i = 3; i + 3 < args.length; i += 4) {
                String part = args[i].toLowerCase();

                double x, y, z;
                try {
                    x = Double.parseDouble(args[i + 1]);
                    y = Double.parseDouble(args[i + 2]);
                    z = Double.parseDouble(args[i + 3]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Invalid number for part " + part + ". Usage: <part> <x> <y> <z>");
                    armorStand.remove();
                    return;
                }

                EulerAngle pose = new EulerAngle(Math.toRadians(x), Math.toRadians(y), Math.toRadians(z));

                switch (part) {
                    case "head":
                        armorStand.setHeadPose(pose);
                        break;
                    case "right-arm":
                    case "rightarm":
                        armorStand.setRightArmPose(pose);
                        break;
                    case "left-arm":
                    case "leftarm":
                        armorStand.setLeftArmPose(pose);
                        break;
                    case "right-leg":
                    case "rightleg":
                        armorStand.setRightLegPose(pose);
                        break;
                    case "left-leg":
                    case "leftleg":
                        armorStand.setLeftLegPose(pose);
                        break;
                    default:
                        player.sendMessage(ChatColor.RED + "Unknown part: " + part);
                        break;
                }
            }

            ArmorStandUtils.saveArmorStand(armorStandName, armorStand);

            player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1, 1);
            player.sendMessage(ChatColor.GREEN + "Successfully created custom ArmorStand: " + armorStandName);
            if (ArmorStandUtils.isIsFirstTimeCreatingArmorStand()) {
                player.sendMessage(ChatColor.YELLOW + "Did you know you can shift-right click on an ArmorStand to open its settings?");
                ArmorStandUtils.setIsFirstTimeCreatingArmorStand(false);
            }

            return;
        }

        if (type.equalsIgnoreCase("none")) {
            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

            ArmorStandCreateEvent armorStandCreateEvent = new ArmorStandCreateEvent(player, armorStand, armorStandName);
            Bukkit.getPluginManager().callEvent(armorStandCreateEvent);

            if (armorStandCreateEvent.isCancelled()) {
                armorStand.remove();
                return;
            }

            armorStand.setArms(true);
            armorStand.setGravity(false);
            armorStand.setBasePlate(false);
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', "&7Made with &6&lA&e&ld&6&lv&e&la&6&ln&e&lc&6&le&e&ld&6&lA&e&lr&6&lm&e&lo&6&lr&e&lS&6&lt&e&la&6&ln&e&ld&6&ls"));
            armorStand.setCustomNameVisible(false);

            ArmorStandUtils.saveArmorStand(armorStandName, armorStand);

            player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1, 1);
            player.sendMessage(ChatColor.GREEN + "Successfully created custom ArmorStand: " + armorStandName);
            if (ArmorStandUtils.isIsFirstTimeCreatingArmorStand()) {
                player.sendMessage(ChatColor.YELLOW + "Did you know you can shift-right click on an ArmorStand to open its settings?");
                ArmorStandUtils.setIsFirstTimeCreatingArmorStand(false);
            }

            return;
        }

        if (!TypesConfig.get().contains(args[1])) {
            String suggestion = getClosest(args[1], TypeUtils.getTypesList());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "No type found named: " + args[1] + ". Did you mean '" + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "No type found named: " + args[1]);
            }
            return;
        }

        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);

        ArmorStandCreateEvent armorStandCreateEvent = new ArmorStandCreateEvent(player, armorStand, armorStandName);
        Bukkit.getPluginManager().callEvent(armorStandCreateEvent);

        if (armorStandCreateEvent.isCancelled()) {
            armorStand.remove();
            return;
        }



        armorStand.setArms(TypesConfig.get().getBoolean(args[1] + ".arms"));
        armorStand.setGravity(false);
        armorStand.setBasePlate(TypesConfig.get().getBoolean(args[1] + ".basePlate"));
        String path = args[1] + ".customName";
        String rawName = TypesConfig.get().getString(path);
        String replacedName = (AdvancedArmorStands.isIsPapiAvailable()) ? PlaceholderAPI.setPlaceholders(player, rawName) : rawName;
        String coloredName = ChatColor.translateAlternateColorCodes('&', replacedName);
        armorStand.setCustomName(coloredName);

        armorStand.setCustomNameVisible(TypesConfig.get().getBoolean(args[1] + ".isCustomNameVisible"));
        armorStand.setVisible(TypesConfig.get().getBoolean(args[1] + ".isVisible"));
        armorStand.setSmall(TypesConfig.get().getBoolean(args[1] + ".isSmall"));
        armorStand.setItemInHand(new ItemStack(Material.valueOf(TypesConfig.get().getString(args[1] + ".itemInHandMaterial"))));

        armorStand.setHeadPose(new EulerAngle(

                Math.toRadians(getConfigDouble(args[1] + ".headPos.x")),
                Math.toRadians(getConfigDouble(args[1] + ".headPos.y")),
                Math.toRadians(getConfigDouble(args[1] + ".headPos.z"))
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

        ArmorStandUtils.saveArmorStand(armorStandName, armorStand);


        player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1,  1);
        player.sendMessage(ChatColor.GREEN + "Successfully created an ArmorStand");
        if (ArmorStandUtils.isIsFirstTimeCreatingArmorStand()) {
            player.sendMessage(ChatColor.YELLOW + "Did you know you can shift-right click on an ArmorStand to open its settings?");
            ArmorStandUtils.setIsFirstTimeCreatingArmorStand(false);
        }

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        if (args.length == 2) {
            List<String> types = new ArrayList<>(TypesConfig.get().getKeys(false));
            types.add("custom");
            types.add("none");
            return types;
        }

        if (args.length == 3 && args[1].equalsIgnoreCase("custom")) {
            return Collections.singletonList("<name>");
        }

        if (args.length == 4 && args[1].equalsIgnoreCase("custom")) {
            return Arrays.asList("head", "body", "leftArm", "rightArm", "leftLeg", "rightLeg");
        }

        if (args[1].equalsIgnoreCase("custom")) {
            switch (args.length % 4) {
                case 1:
                    return Arrays.asList("head", "body", "leftArm", "rightArm", "leftLeg", "rightLeg");
                case 2:
                    return Collections.singletonList("<x>");
                case 3:
                    return Collections.singletonList("<y>");
                case 0:
                    return Collections.singletonList("<z>");
            }
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isForOps() {
        return true;
    }

    private double getConfigDouble(String path) {
        if (!TypesConfig.get().contains(path)) {
            AdvancedArmorStands.debug("Config path not found: " + path);
            return 0.0;
        }
        return TypesConfig.get().getDouble(path);
    }

    @EventHandler
    public void onArmorStandCreate(ArmorStandCreateEvent event) {
        ArmorStand armorStand = event.getArmorStand();
        String name = event.getName();
        FileConfiguration config = ArmorStandsConfig.get();
        String path = "armorstands." + name;

        if (config.contains(path)) {
            event.getPlayer().sendMessage(ChatColor.RED + "An ArmorStand with this name already exists!");
            event.setCancelled(true);
            return;
        }

        config.set(path + ".UUID", armorStand.getUniqueId().toString());
        config.set(path + ".World", armorStand.getLocation().getWorld().getName());
        config.set(path + ".X", armorStand.getLocation().getX());
        config.set(path + ".Y", armorStand.getLocation().getY());
        config.set(path + ".Z", armorStand.getLocation().getZ());
        config.set(path + ".yaw", armorStand.getLocation().getYaw());
        config.set(path + ".pitch", armorStand.getLocation().getPitch());

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = format.format(now);
        config.set(path + ".info.date_created", formattedDate);
        config.set(path + ".info.created_by", event.getPlayer().getDisplayName());
        config.set(path + ".info.world", event.getArmorStand().getWorld().getName());
        config.set(path + ".info.X", armorStand.getLocation().getX());
        config.set(path + ".info.Y", armorStand.getLocation().getY());
        config.set(path + ".info.Z", armorStand.getLocation().getZ());


        ArmorStandsConfig.save();
    }


}
