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
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.InventoryUtils;
import com.parsa3323.aas.utils.PoseManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.*;

public class AnimCreateCommand extends SubCommand {

    public static final Map<UUID, String> animationNames = new HashMap<>();

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create an animation with an as";
    }

    @Override
    public String getSyntax() {
        return "/as animation create <name> <animation-name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length < 4) {
            sendUsage(player);
            return;
        }

        ArmorStand as = ArmorStandUtils.getArmorStandByName(args[2]);

        if (as == null) {
            String suggestion = getClosest(args[2], ArmorStandUtils.getArmorStandList());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "Invalid armor stand '" + args[2] + "'. Did you mean '" + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "Invalid armor stand");
            }
            return;

        }

        if (args[3] == null) {
            player.sendMessage(ChatColor.RED + "Invalid animation name");
            return;
        }

        InventoryUtils.save(player);
        ArmorStandSelectionCache.setKeyFrameSelectedArmorStand(player.getUniqueId(), as);
        PoseManager.savePose(as);
        animationNames.put(player.getUniqueId(), args[3]);
        ArmorStandSelectionCache.addToKeyFrameList(player);
        InventoryUtils.setEditorItems(player);
        player.closeInventory();
    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        if (args.length == 2) {
            return ArmorStandUtils.getArmorStandList();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
