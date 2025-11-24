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

package com.parsa3323.aas.ai;

import com.parsa3323.aas.ai.manager.AiSettingsOption;
import com.parsa3323.aas.utils.AiUtils;
import com.parsa3323.aas.utils.InventoryUtils;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MemoryOption extends AiSettingsOption {

    public static final Map<UUID, ArmorStand> waiting = new HashMap<>();

    @Override
    public ItemStack getItemStack(ArmorStand armorStand, Player player) {
        ItemStack itemStack = VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzI1MDhlMmNhNjUwMGJjZTMwNTM5YzM4ODg0MmE1NjcyYjdiYzI5YTY4NzZkZDZhNTAyNTY3MmUyNTJkMjVkYSJ9fX0=");

        ItemMeta itemMeta = itemStack.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Add custom instructions");
        lore.add(ChatColor.GRAY + "for the armorstand's ai");
        lore.add(ChatColor.GRAY + "to follow when responding.");

        String currentInstructions = AiUtils.getUserSetInstructions(armorStand);

        lore.add("");

        if (currentInstructions != null) {
            String[] words = ChatColor.stripColor(currentInstructions).split("\\s+");
            StringBuilder line = new StringBuilder(ChatColor.YELLOW + "Current: ");
            for (int i = 0; i < words.length; i++) {
                line.append(words[i]).append(" ");
                if ((i + 1) % 2 == 0 || i == words.length - 1) {
                    lore.add(line.toString().trim());
                    line = new StringBuilder(ChatColor.YELLOW.toString());
                }
            }
        } else {
            lore.add(ChatColor.YELLOW + "Current: None");
        }

        lore.add("");

        lore.add(ChatColor.YELLOW + "Click to change");

        itemMeta.setLore(lore);
        itemMeta.setDisplayName(ChatColor.YELLOW + "Memory");

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void execute(ArmorStand armorStand, Player player) {
        player.closeInventory();
        InventoryUtils.openBookInHand(player);
        waiting.put(player.getUniqueId(), armorStand);

        player.sendMessage("Write your text in the book and press Done.");
    }

}
