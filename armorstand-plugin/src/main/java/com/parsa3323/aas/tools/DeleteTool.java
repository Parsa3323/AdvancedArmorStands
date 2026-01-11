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

package com.parsa3323.aas.tools;

import com.parsa3323.aas.tools.manager.ToolsOption;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DeleteTool extends ToolsOption {
    @Override
    public ItemStack getItemStack(ArmorStand armorStand) {
        ItemStack itemStack = VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmZiNmJjMWExNjU4MTljNzRhYjIxMDMzZDRmOGIwMzlmZjg0M2M1YmZiYjJjMjQxY2ZhNDc2NDcxZWU5MWZlIn19fQ==");
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "Delete");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Delete this ArmorStand");
        lore.add(ChatColor.GRAY + "this action cannot be undone");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Click to delete");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void execute(InventoryClickEvent e, ArmorStand armorStand) {
        ArmorStandUtils.deleteArmorStand(ArmorStandUtils.getNameByArmorStand(armorStand), (Player) e.getWhoClicked());
    }
}
