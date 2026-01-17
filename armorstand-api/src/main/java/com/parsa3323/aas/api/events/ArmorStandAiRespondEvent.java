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

package com.parsa3323.aas.api.events;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArmorStandAiRespondEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    Player requester;

    ArmorStand armorStand;

    String response;

    String prompt;

    public String getPrompt() {
        return prompt;
    }

    public String getResponse() {
        return response;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public Player getRequester() {
        return requester;
    }

    public ArmorStandAiRespondEvent(ArmorStand armorStand, String response, String prompt, Player requester) {
        this.armorStand = armorStand;
        this.response = response;
        this.prompt = prompt;
        this.requester = requester;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }

}
