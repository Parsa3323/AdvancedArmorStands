/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.Q
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.parsa.aas.Commands;

import me.parsa.aas.Commands.Manager.SubCommand;
import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Utils.ArmorStandUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeleportCommand extends SubCommand {
    @Override
    public String getName() {
        return "teleport";
    }

    @Override
    public String getDescription() {
        return "Teleport to armor stand";
    }

    @Override
    public String getSyntax() {
        return "/as teleport <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 2) {
            sendUsage(player);
            return;
        }

        ArmorStandUtils.teleportToArmorStand(player, args[1]);

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        ArrayList<String> list = new ArrayList<>(ArmorStands.get().getConfigurationSection("armorstands").getKeys(false));

        return list;
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
