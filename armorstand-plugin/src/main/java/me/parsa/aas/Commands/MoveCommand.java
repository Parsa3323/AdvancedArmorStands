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

package me.parsa.aas.Commands;

import me.parsa.aas.Commands.Manager.SubCommand;
import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Events.PlayerMoveArmorStandEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MoveCommand extends SubCommand {
    @Override
    public String getName() {
        return "move";
    }

    @Override
    public String getDescription() {
        return "Moves armor stand to your location";
    }

    @Override
    public String getSyntax() {
        return "/as move <name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length < 2) {
            sendUsage(player);
            return;
        }

        String name = args[1];
        FileConfiguration config = ArmorStands.get();
        String path = "armorstands." + name;

        if (!config.contains(path)) {
            player.sendMessage(ChatColor.RED + "ArmorStand not found!");
            return;
        }

        UUID uuid = UUID.fromString(config.getString(path + ".UUID"));
        World world = Bukkit.getWorld(config.getString(path + ".World"));

        if (world == null) {
            player.sendMessage(ChatColor.RED + "World not found!");
            return;
        }


        for (Entity entity : world.getEntities()) {
            if (entity instanceof ArmorStand && entity.getUniqueId().equals(uuid)) {
                ArmorStand armorStand = (ArmorStand) entity;


                PlayerMoveArmorStandEvent armorStandMoveEvent = new PlayerMoveArmorStandEvent(player.getLocation(), armorStand, player);
                Bukkit.getPluginManager().callEvent(armorStandMoveEvent);
                if (armorStandMoveEvent.isCancelled()) {
                    return;
                }

                armorStand.teleport(player.getLocation());

                config.set(path + ".World", player.getWorld().getName());
                config.set(path + ".X", player.getLocation().getX());
                config.set(path + ".Y", player.getLocation().getY());
                config.set(path + ".Z", player.getLocation().getZ());
                ArmorStands.save();

                player.sendMessage(ChatColor.GREEN + "Moved ArmorStand: " + name + " to your location.");
                return;
            }
        }

        player.sendMessage(ChatColor.RED + "ArmorStand not found in the world!");
    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        ArrayList<String> list = new ArrayList<>(ArmorStands.get().getConfigurationSection("armorstands").getKeys(false));

        return list;
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
