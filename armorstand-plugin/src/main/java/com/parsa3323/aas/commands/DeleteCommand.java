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

import com.parsa3323.aas.api.exeption.ArmorStandNotFoundException;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.SoundUtils;
import com.parsa3323.aas.utils.TextUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends SubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as delete " + TextUtils.getFirstContent(ArmorStandUtils.getArmorStandList(), "exampleStand"));
        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as delete " + TextUtils.getFirstContent(ArmorStandUtils.getArmorStandList(), "exampleStand") + " --full");
        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as delete --all");
        return lore;
    }

    @Override
    public String getDescription() {
        return "Delete an ArmorStand";
    }


    @Override
    public String getSyntax() {
        return "/as delete <name|--all> [--full]";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length < 2) {
            sendUsage(player);
            return;
        }

        if (args[1].equalsIgnoreCase("--all")) {
            for (String name : ArmorStandUtils.getArmorStandList()) {
                try {
                    ArmorStandUtils.deleteArmorStand(name);
                } catch (ArmorStandNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            player.sendMessage(ChatColor.GREEN + "Successfully deleted all ArmorStands");
            return;
        }

        if (args[1].equalsIgnoreCase("--full")) {
            ArmorStandUtils.deleteArmorStand(args[1]);
            ArmorStandsConfig.get().set("armorstands." + args[1], null);
            ArmorStandsConfig.save();
            player.sendMessage(ChatColor.GREEN + "Fully deleted ArmorStand");
            return;
        }

        FileConfiguration config = ArmorStandsConfig.get();
        ConfigurationSection section = config.getConfigurationSection("armorstands");

        if (section == null || !section.contains(args[1])) {
            String suggestion = getClosest(args[1], ArmorStandUtils.getArmorStandList());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "Invalid ArmorStand '" + args[1] + "'. Did you mean '" + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "Invalid ArmorStand");
            }
            return;
        }

        SoundUtils.playSuccessSound(player);

        if (ArmorStandsConfig.get().getBoolean("armorstands." + args[1] + ".deleted")) {
            ArmorStandsConfig.get().set("armorstands." + args[1], null);
            ArmorStandsConfig.save();
            player.sendMessage(ChatColor.GREEN + "Fully deleted ArmorStand");
            return;
        }
        player.sendMessage(ChatColor.GREEN + "ArmorStand has been deleted, but it can be restored using the restored command, restart the server or run this command again to fully delete");
        ArmorStandUtils.deleteArmorStand(args[1]);
    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        List<String> list = new ArrayList<>(ArmorStandUtils.getArmorStandList());
        list.add("--all");
        return list;

    }

    @Override
    public boolean isForOps() {
        return true;
    }

    @Override
    public String getAlias() {
        return "dl";
    }
}
