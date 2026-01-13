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
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AiToggleOption extends AiSettingsOption {
    @Override
    public ItemStack getItemStack(ArmorStand armorStand, Player player) {
        ItemStack itemStack = VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzI1MDhlMmNhNjUwMGJjZTMwNTM5YzM4ODg0MmE1NjcyYjdiYzI5YTY4NzZkZDZhNTAyNTY3MmUyNTJkMjVkYSJ9fX0=");

        ItemMeta itemMeta = itemStack.getItemMeta();

        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Enable or disable AI");
        lore.add(ChatColor.GRAY + "for this armor stand");

        lore.add("");
        lore.add((ArmorStandUtils.hasAi(ArmorStandUtils.getNameByArmorStand(armorStand))) ? ColorUtils.boldAndColor(ChatColor.GOLD) + TextUtils.CHECK + ChatColor.YELLOW + "✔ Enabled" : ColorUtils.boldAndColor(ChatColor.DARK_RED) + TextUtils.CROSS + ChatColor.RED + "✘ Disabled");

        itemMeta.setLore(lore);
        itemMeta.setDisplayName(ChatColor.YELLOW + "Toggle AI");

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    @Override
    public void execute(ArmorStand armorStand, Player player) {
        ArmorStandUtils.setAi(ArmorStandUtils.getNameByArmorStand(armorStand), !ArmorStandUtils.hasAi(ArmorStandUtils.getNameByArmorStand(armorStand)));
    }

    @Override
    public boolean updateInventory() {
        return true;
    }
}
