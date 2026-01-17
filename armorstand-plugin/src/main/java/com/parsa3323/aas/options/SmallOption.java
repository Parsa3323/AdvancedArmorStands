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

import com.parsa3323.aas.options.manager.SettingsOption;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.TextUtils;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SmallOption extends SettingsOption {
    @Override
    public String getName() {
        return "SmallOption";
    }

    @Override
    public ItemStack getItemStack(ArmorStand armorStand) {
        ItemStack itemStack = VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzI4NTRhZjNkNDI4MTRhMmUwNjA5NDllNWE1Y2JmYWQ3M2M0YmEzZDNlNzcyODliMTJkODkwYjEyNDk0NzJlZSJ9fX0=");
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "Small");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Enable and disable");
        lore.add(ChatColor.GRAY + "small size for this ArmorStand");
        lore.add("");
        lore.add((armorStand.isSmall()) ? ColorUtils.boldAndColor(ChatColor.GOLD) + TextUtils.CHECK + ChatColor.YELLOW + " Is small" : ColorUtils.boldAndColor(ChatColor.DARK_RED) + TextUtils.CROSS + ChatColor.RED + " isn't small");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void click(InventoryClickEvent e, ArmorStand armorStand) {
        armorStand.setSmall(!armorStand.isSmall());
    }

    @Override
    public boolean updateInventory() {
        return true;
    }
}
