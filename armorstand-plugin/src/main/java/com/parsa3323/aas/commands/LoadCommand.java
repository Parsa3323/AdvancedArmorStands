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

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.exeption.ArmorStandLoadException;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.SoundUtils;
import com.parsa3323.aas.utils.TextUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LoadCommand extends SubCommand {
    @Override
    public String getName() {
        return "load";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        ArrayList<String> lore = new ArrayList<>();

        ArrayList<String> unloaded = new ArrayList<>(ArmorStandUtils.getArmorStandList());

        for (String armorStand : ArmorStandUtils.getArmorStandList()) {
            if (!ArmorStandUtils.isLoaded(ArmorStandUtils.getArmorStandByName(armorStand))){
                unloaded.add(armorStand);
            }
        }


        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as load " + TextUtils.getFirstContent(unloaded, "unloadedArmorStand"));
        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as load --all");
        return lore;
    }

    @Override
    public String getDescription() {
        return "Loads an ArmorStand";
    }

    @Override
    public String getSyntax() {
        return "/as load <name|--all>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) {
            sendUsage(player);
            return;
        }


        if (args[1].equalsIgnoreCase("--all")) {
            List<String> names = ArmorStandUtils.getArmorStandList();
            int loaded = 0;
            int failed = 0;
            List<String> failedNames = new ArrayList<>();

            for (String name : names) {
                try {
                    ArmorStand as = ArmorStandUtils.getArmorStandByName(name);
                    if (!ArmorStandUtils.isLoaded(as)) {
                        ArmorStandUtils.loadArmorStand(name);
                        loaded++;
                    }
                } catch (ArmorStandLoadException e) {
                    failed++;
                    failedNames.add(name);
                    AdvancedArmorStands.error("Error loading " + name + ": " + e.getMessage(), true);
                } catch (NullPointerException e) {
                    failed++;
                    failedNames.add(name + " [NULL]");
                    AdvancedArmorStands.error("Null error with " + name + ": " + e.getMessage(), true);
                }
            }

            if (loaded == 0 && failed == 0) {
                player.sendMessage(ChatColor.YELLOW + "No unloaded ArmorStands found.");
            } else {
                String message = ChatColor.GREEN + "Successfully loaded " + loaded + " ArmorStands.";
                if (failed > 0) {
                    message += ChatColor.RED + " Failed to load " + failed + " ArmorStands.";
                    if (!failedNames.isEmpty()) {
                        player.sendMessage(ChatColor.RED + "Failed: " + String.join(", ", failedNames));
                    }
                }
                player.sendMessage(message);
                SoundUtils.playSuccessSound(player);
            }
            return;
        }

        if (!ArmorStandsConfig.get().contains("armorstands." + args[1])) {
            String suggestion = getClosest(args[1], ArmorStandUtils.getArmorStandList());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "Invalid ArmorStand '" + args[1] + "'. Did you mean '" + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "Invalid ArmorStand");
            }
            return;
        }

        ArmorStand as = ArmorStandUtils.getArmorStandByName(args[1]);

        if (ArmorStandUtils.isLoaded(as)) {
            player.sendMessage(ChatColor.RED + "This ArmorStand is already loaded");
            return;
        }

        try {
            ArmorStandUtils.loadArmorStand(args[1]);
            player.sendMessage(ChatColor.GREEN + "Successfully loaded ArmorStand '" + args[1] + "'");
            SoundUtils.playSuccessSound(player);
        } catch (ArmorStandLoadException e) {
            player.sendMessage(ChatColor.RED + "Failed to load the ArmorStand check console for more details!");
            AdvancedArmorStands.error("Error loading an armorstand: " + e.getMessage(), true);
            e.printStackTrace();
            SoundUtils.playErrorSound(player);
        }


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
}
