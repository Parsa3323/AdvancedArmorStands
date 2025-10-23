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

import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HeadCommand extends SubCommand {
    @Override
    public String getName() {
        return "head";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Gets a player's head";
    }

    @Override
    public String getSyntax() {
        return "/as head <username>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) {
            sendUsage(player);
            return;
        }

        player.getInventory().addItem(PlayerUtils.createSkullPlayer(args[1]));
        player.sendMessage(ChatColor.GREEN + "Successfully gave you " + args[1] + "'s head");

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        ArrayList<String> players = new ArrayList<>();

        Bukkit.getOnlinePlayers().forEach(player1 -> {
            players.add(player1.getName());
        });

        return players;
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
