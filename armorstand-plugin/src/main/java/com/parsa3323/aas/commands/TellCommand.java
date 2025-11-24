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

package com.parsa3323.aas.commands;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.actions.AiRole;
import com.parsa3323.aas.api.data.MemoryData;
import com.parsa3323.aas.api.events.ArmorStandAiRespondEvent;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.utils.AiUtils;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TellCommand extends SubCommand {
    @Override
    public String getName() {
        return "tell";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as tell " + ((ArmorStandUtils.getArmorStandList().isEmpty()) ? "test (example" : ArmorStandUtils.getArmorStandList().get(0)) + " who are you?");
        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as tell " + ((ArmorStandUtils.getArmorStandList().isEmpty()) ? "test (example" : ArmorStandUtils.getArmorStandList().get(0)) + " what is your name?");
        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as tell " + ((ArmorStandUtils.getArmorStandList().isEmpty()) ? "test (example" : ArmorStandUtils.getArmorStandList().get(0)) + " whats 2 + 2?");
        return lore;
    }

    @Override
    public String getDescription() {
        return "Tell the armorstand ai something";
    }

    @Override
    public String getSyntax() {
        return "/as tell <name> <messages>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) {
            sendUsage(player);
            return;
        }

        ArmorStand stand = checkArmorStandAndNotify(player, args[1]);
        if (stand == null) return;

        String name = args[1];

        if (!ArmorStandUtils.hasAi(name)) {
            player.sendMessage(ChatColor.RED + "This armorstand doesn't have AI enabled");
            return;
        }

        String userInput = String.join("_", java.util.Arrays.copyOfRange(args, 2, args.length));

        MemoryData memoryData = new MemoryData(AiUtils.getHistory(player.getName(), name), AiUtils.getDefaultInstructions(name, AiUtils.getUserSetInstructions(stand)));

        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "»" + ChatColor.GRAY + "] " + ChatColor.GRAY + "Thinking");

        AiUtils.getResponseAsync(AdvancedArmorStands.getAiApiKey(), memoryData, userInput, response -> {
            player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "»" + ChatColor.GRAY + "] " + ChatColor.GRAY + response);

            Bukkit.getPluginManager().callEvent(new ArmorStandAiRespondEvent(ArmorStandUtils.getArmorStandByName(name), response, userInput, player));

            AiUtils.addToHistory(player.getName(), name, AiRole.PLAYER, userInput);
            AiUtils.addToHistory(player.getName(), name, AiRole.AI, response);
        });

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        if (args.length == 2) {
            return ArmorStandUtils.getArmorStandList();

        }
        return null;
    }

    @Override
    public boolean isForOps() {
        return false;
    }
}
