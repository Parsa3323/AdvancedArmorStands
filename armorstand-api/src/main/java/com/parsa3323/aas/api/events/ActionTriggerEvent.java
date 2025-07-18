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

package com.parsa3323.aas.api.events;

import com.parsa3323.aas.api.actions.SenderType;
import com.parsa3323.aas.api.actions.TriggerType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ActionTriggerEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;

    SenderType senderType;

    Player player;

    TriggerType triggerType;

    ArmorStand armorStand;

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public TriggerType getTriggerType() {
        return triggerType;
    }


    public SenderType getSenderType() {
        return senderType;
    }

    public Player getPlayer() {
        return player;
    }

    public ActionTriggerEvent(SenderType senderType, Player player, TriggerType triggerType, ArmorStand armorStand) {
        this.senderType = senderType;
        this.player = player;
        this.triggerType = triggerType;
        this.armorStand = armorStand;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }
}
