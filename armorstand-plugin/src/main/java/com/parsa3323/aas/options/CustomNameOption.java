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

package com.parsa3323.aas.options;

import com.parsa3323.aas.api.language.Language;
import com.parsa3323.aas.api.language.Messages;
import com.parsa3323.aas.options.manager.SettingsOption;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomNameOption extends SettingsOption {

    public static final Map<UUID, ArmorStand> players = new HashMap<>();

    @Override
    public String getName() {
        return "CustomNameOption";
    }

    @Override
    public ItemStack getItemStack(ArmorStand armorStand) {

        ItemStack itemStack = VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWRlOGQwZjNlNDIxY2NlODU1NmViOGUzZTU4MzI1OWZmNjg3MzRiYTBmNGNhYjYzOWMyMzkwN2NkMmJlNGVmYyJ9fX0=");
        ItemMeta itemMeta = itemStack.getItemMeta();
        String currentName = armorStand.getCustomName();
        itemMeta.setDisplayName(Language.getMsg(Messages.CUSTOM_NAME_OPTION_NAME));
        ArrayList<String> lore = new ArrayList<>(Language.getLore(Messages.CUSTOM_NAME_OPTION_LORE));

        lore.add("");
        if (currentName != null) {
            String[] words = ChatColor.stripColor(currentName).split("\\s+");
            StringBuilder line = new StringBuilder(Language.getMsg(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME));
            for (int i = 0; i < words.length; i++) {
                line.append(words[i]).append(" ");
                if ((i + 1) % 2 == 0 || i == words.length - 1) {
                    lore.add(line.toString().trim());
                    line = new StringBuilder(ChatColor.YELLOW.toString());
                }
            }
        } else {
            lore.add(Language.getMsg(Messages.CUSTOM_NAME_OPTION_NO_NAME));
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void click(InventoryClickEvent e, ArmorStand armorStand) {
        Player p = (Player) e.getWhoClicked();

        if (!players.containsKey(p.getUniqueId())) {
            players.put(p.getUniqueId(), armorStand);
            p.sendMessage(Language.getMsg(Messages.CUSTOM_NAME_OPTION_ENTER_NAME));
            p.closeInventory();
        } else {
            p.sendMessage(Language.getMsg(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING));
        }
    }

    @Override
    public boolean updateInventory() {
        return false;
    }


}
