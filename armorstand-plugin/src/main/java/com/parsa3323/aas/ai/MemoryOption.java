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

import com.cryptomorin.xseries.XMaterial;
import com.parsa3323.aas.ai.manager.AiSettingsOption;
import com.parsa3323.aas.utils.AiUtils;
import com.parsa3323.aas.utils.InventoryUtils;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

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

        InventoryUtils.save(player);
        player.getInventory().clear();

        ItemStack itemStack = new ItemStack(XMaterial.WRITABLE_BOOK.parseMaterial());
        BookMeta meta = (BookMeta) itemStack.getItemMeta();

        String text = AiUtils.getUserSetInstructions(armorStand);
        List<String> pages = new ArrayList<>();

        if (text != null && !text.isEmpty()) {
            int maxCharsPerPage = 256;
            String[] words = text.split("\\s+");
            StringBuilder currentPage = new StringBuilder();

            for (String word : words) {
                if (currentPage.length() + word.length() + 1 > maxCharsPerPage) {
                    pages.add(currentPage.toString());
                    currentPage = new StringBuilder();
                }
                if (currentPage.length() > 0) currentPage.append(" ");
                currentPage.append(word);
            }


            if (currentPage.length() > 0) {
                currentPage.append(" ");
                pages.add(currentPage.toString());
            } else {
                pages.add(" ");
            }
        } else {
            pages.add(" ");
        }

        meta.setPages(pages);
        meta.setTitle("Custom Instructions");

        itemStack.setItemMeta(meta);
        player.getInventory().setItem(4, itemStack);

        waiting.put(player.getUniqueId(), armorStand);

        VersionSupportUtil.getVersionSupport().openBook(itemStack, player);

        player.sendMessage(ChatColor.GREEN + "Open the book to change the instructions of the armor stand's AI.");
    }


    @Override
    public boolean updateInventory() {
        return false;
    }

}
