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
import com.parsa3323.aas.api.data.MemoryData;
import com.parsa3323.aas.commands.manager.SubCommand;
import com.parsa3323.aas.utils.AiUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TellCommand extends SubCommand {
    @Override
    public String getName() {
        return "tell";
    }

    @Override
    public ArrayList<String> getExampleLore() {
        return null;
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
        player.sendMessage(AiUtils.getResponse(AdvancedArmorStands.getAiApiKey(), new MemoryData("", "If you are told 'hey' answer lalo"), args[1]));
    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean isForOps() {
        return false;
    }
}
