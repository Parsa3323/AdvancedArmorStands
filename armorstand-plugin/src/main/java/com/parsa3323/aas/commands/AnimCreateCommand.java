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
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.utils.AnimationUtils;
import com.parsa3323.aas.utils.ArmorStandSelectionCache;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.InventoryUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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
        return "Create/edit an animation with an as";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        return null;
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

        ArmorStand as = checkArmorStandAndNotify(player, args[2]);

        if (as == null) return;

        if (args[3] == null) {
            player.sendMessage(ChatColor.RED + "Invalid animation name");
            return;
        }

        if (ArmorStandUtils.hasAnimation(as)) {
            player.sendMessage(ChatColor.RED + "Can't open the editor on an armor stand that has animation");
            return;
        }

        InventoryUtils.save(player);
        ArmorStandSelectionCache.setKeyFrameSelectedArmorStand(player.getUniqueId(), as);
        ArmorStandUtils.savePose(as);

        AnimationConfig.get().addDefault("animations." + args[3] + ".interval", 10);

        AnimationConfig.get().addDefault("animations." + args[3] + ".loop", true);

        AnimationConfig.save();

        animationNames.put(player.getUniqueId(), args[3]);
        ArmorStandSelectionCache.addToKeyFrameList(player);
        InventoryUtils.setEditorItems(player);
        if (player.getGameMode() == GameMode.ADVENTURE) {
            InventoryUtils.setGameMode(player, player.getGameMode());
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(
                    ChatColor.GREEN + "Your gamemode has been temporarily switched to CREATIVE, " +
                            "because edit sessions do not support ADVENTURE mode. " +
                            "It will be restored automatically when you exit."
            );
        }
        player.closeInventory();
        player.sendMessage(ChatColor.GREEN + "Successfully entered the animation edit/create session");
        player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.2f);

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        if (args.length == 3) {
            return ArmorStandUtils.getArmorStandList();
        }
        if (args.length == 4) {
            return AnimationUtils.getTotalAnimations();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
