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
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.config.TypesConfig;
import com.parsa3323.aas.player.PlayerManager;
import com.parsa3323.aas.utils.AnimationUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class ReloadCommand extends SubCommand {
    @Override
    public String getName() {
        return "Reload";
    }

    @Override
    public String getDescription() {
        return "Reloads plugin's configs";
    }

    @Override
    public String getSyntax() {
        return "/as reload";
    }

    @Override
    public void perform(Player player, String[] args) {

        try {


            TypesConfig.reload();
            ArmorStandsConfig.reload();
            AnimationConfig.reload();
            AnimationUtils.reloadAnimations();
            player.sendMessage(ChatColor.GREEN + "✔ Successfully reloaded plugin's configs");
            PlayerManager.getByBukkit(player).playSound("ORB_PICKUP");
        } catch (Exception e) {
            e.printStackTrace();
            AdvancedArmorStands.info(ChatColor.RED + e.getMessage());
        }

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
