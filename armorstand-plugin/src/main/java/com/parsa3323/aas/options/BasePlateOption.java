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

package com.parsa3323.aas.options;

import com.parsa3323.aas.options.manager.SettingsOption;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.IconUtils;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BasePlateOption extends SettingsOption {

    @Override
    public String getName() {
        return "BasePlateOption";
    }

    @Override
    public ItemStack getItemStack(ArmorStand armorStand) {
        ItemStack itemStack = VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmI3NzNjNGNjNjc1ODgyYjkzNmViNDgxNWQ5NGY0ZmZiNjI0MTE5YWVjOWE0Y2Q2NGExNDM0ODE1YWY4YWJjYiJ9fX0=");
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "Base Plate");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Enable and disable");
        lore.add(ChatColor.GRAY + "base plate for this ArmorStand ");
        lore.add("");
        lore.add((armorStand.hasBasePlate()) ? ColorUtils.boldAndColor(ChatColor.GOLD) + IconUtils.CHECK() + ChatColor.YELLOW + " Has base plate" : ColorUtils.boldAndColor(ChatColor.DARK_RED) + IconUtils.CROSS() + ChatColor.RED + " Doesn't have base plate");


        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
   }

    @Override
    public void click(InventoryClickEvent e, ArmorStand armorStand) {
        armorStand.setBasePlate(!armorStand.hasBasePlate());}

    @Override
    public boolean updateInventory() {
        return true;
    }
}
