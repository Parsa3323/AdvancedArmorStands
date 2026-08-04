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

package com.parsa3323.aas.tools;

import com.parsa3323.aas.api.language.Language;
import com.parsa3323.aas.api.language.Messages;
import com.parsa3323.aas.tools.manager.ToolsOption;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MoveTool extends ToolsOption {

    public static final Map<UUID, ArmorStand> moveList = new HashMap<>();

    @Override
    public ItemStack getItemStack(ArmorStand armorStand) {
        ItemStack itemStack = VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZjYjEzNjZjYTc3OGRjOGNhODFmMTE1YzA4NzA0Mzk4ZDM1OTkzNDI1MDFjMGI1ZjRiNGJhZGNiY2Q2NWM5NCJ9fX0=");
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(Language.getMsg(Messages.MOVE_TOOL_NAME));
        ArrayList<String> lore = new ArrayList<>(Language.getLore(Messages.MOVE_TOOL_LORE));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void execute(InventoryClickEvent e, ArmorStand armorStand) {
        Player p = (Player) e.getWhoClicked();

        if (moveList.containsKey(p.getUniqueId())) {
            p.sendMessage(Language.getMsg(Messages.MOVE_TOOL_ALREADY_MOVING));
            return;
        }

        moveList.put(p.getUniqueId(), armorStand);
        p.sendMessage(Language.getMsg(Messages.MOVE_TOOL_START_MESSAGE));
        p.closeInventory();
    }
}
