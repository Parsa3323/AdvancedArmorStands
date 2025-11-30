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

import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.utils.ActionBarTimer;
import com.parsa3323.aas.utils.AiUtils;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.ColorUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AiCommand extends SubCommand {
    @Override
    public String getName() {
        return "ai";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        ArrayList<String> lore = new ArrayList<>();

        String exampleName = (ArmorStandUtils.getArmorStandList().get(0) == null ? "test" : ArmorStandUtils.getArmorStandList().get(0));

        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as ai" + " make armor stand: " + exampleName + " standing");
        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as ai" + " please delete armor stand '" + exampleName + "'");
        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as ai" + " please create an armor stand named: '" + exampleName + "'");
        return lore;
    }
    @Override
    public String getDescription() {
        return "Ask the ai to assist you";
    }

    @Override
    public String getSyntax() {
        return "/as ai <prompt>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Prompt should not be empty");
            return;
        }

        String userInput = String.join("_", java.util.Arrays.copyOfRange(args, 1, args.length));

        player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.2f);

        ActionBarTimer actionBarTimer = new ActionBarTimer(player, ChatColor.GRAY + "Thinking...");
        actionBarTimer.start();

        AiUtils.getAssistWithAi(AdvancedArmorStands.getAiApiKey(), userInput, player, s -> {
            AiUtils.sendResponse(player, s);
            actionBarTimer.stop();
        });


    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
