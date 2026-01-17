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

import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.utils.AnimationUtils;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.TextUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AnimRemoveCommand extends SubCommand {
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Completely remove an animation";
    }

    @Override
    public String getSyntax() {
        return "/as animation remove <name>";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ColorUtils.boldAndColor(ChatColor.YELLOW) + "/as animation remove " + TextUtils.getFirstContent(AnimationUtils.getAnimationsList(), "exampleAnimation"));
        return lore;
    }

    @Override
    public void perform(Player player, String[] args) {

        if (!AnimationConfig.get().contains("animations." + args[2])) {
            String suggestion = getClosest(args[2], AnimationUtils.getAnimationsList());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "Invalid animation '" + args[2] + "'. Did you mean '" + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "Invalid animation");
            }
            return;
        }


        for (String key : ArmorStandUtils.getArmorStandList()) {

            if (args[2].equalsIgnoreCase(ArmorStandsConfig.get().getString("armorstands." + key + ".animation"))) {
                ArmorStandsConfig.get().set("armorstands." + key + ".animation", null);
            }

        }

        AnimationConfig.get().set("animations." + args[2], null);

        AnimationConfig.save();
        ArmorStandsConfig.save();

        player.sendMessage(ChatColor.GREEN + "Successfully removed animation '" + args[2] + "'");

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        return AnimationUtils.getAnimationsList();
    }

    @Override
    public boolean isForOps() {
        return false;
    }
}
