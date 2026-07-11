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
import com.parsa3323.aas.api.language.Language;
import com.parsa3323.aas.api.language.Messages;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.SoundUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RestoreCommand extends SubCommand {
    @Override
    public String getName() {
        return "restore";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        return null;
    }

    @Override
    public String getDescription() {
        return Language.getMsg(Messages.RESTORE_DESCRIPTION);
    }

    @Override
    public String getSyntax() {
        return "/as restore <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) {
            sendUsage(player);
            return;
        }

        FileConfiguration config = ArmorStandsConfig.get();
        ConfigurationSection section = config.getConfigurationSection("armorstands");

        if (section == null || !section.contains(args[1])) {
            player.sendMessage(Language.getMsg(Messages.ARMOR_STAND_INVALID));
            return;
        }

        if (!ArmorStandsConfig.get().getBoolean("armorstands." + args[1] + ".deleted")) {
            player.sendMessage(Language.getMsg(Messages.RESTORE_NOT_DELETED));
            return;
        }

        ArmorStandsConfig.get().set("armorstands." + args[1] + ".deleted", false);
        ArmorStandsConfig.save();
        try {
            ArmorStandUtils.loadArmorStand(args[1]);
        } catch (ArmorStandLoadException e) {
            player.sendMessage(Language.getMsg(Messages.RESTORE_UNKNOWN_ERROR));
            AdvancedArmorStands.error(null, true, "Error while restoring ArmorStand: ", e.getMessage());
            e.printStackTrace();
        }
        player.sendMessage(Language.getMsg(Messages.RESTORE_SUCCESS));
        SoundUtils.playSuccessSound(player);
    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        ArrayList<String> armorStands = new ArrayList<>();

        ArmorStandUtils.getAllArmorStandList().forEach(name -> {
            if (ArmorStandUtils.isRestorable(name)) {
                armorStands.add(name);
            }
        });

        return armorStands;
    }

    @Override
    public boolean isForOps() {
        return true;
    }

    @Override
    public String getAlias() {
        return "rest";
    }
}
