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

package com.parsa3323.aas.listener;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.utils.ArmorStandUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatTabListener implements Listener {

    @EventHandler
    public void onChatTab(PlayerChatTabCompleteEvent e) {
        String msg = e.getChatMessage();

        if (!msg.startsWith("@")) return;

        if (!AdvancedArmorStands.plugin.getConfig().getBoolean("ai.allow-players")) return;

        String currentInput = msg.substring(1).toLowerCase();

        List<String> allNames = ArmorStandUtils.getArmorStandList();
        List<String> out = new ArrayList<>();

        for (String name : allNames) {
            if (name.toLowerCase().startsWith(currentInput)) {
                out.add("@" + name);
            }
        }

        if (currentInput.isEmpty()) {
            for (String name : allNames) out.add("@" + name);
        }

        e.getTabCompletions().clear();
        e.getTabCompletions().addAll(out);

    }

}
