/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.Q
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.parsa3323.aas.Options;

import com.parsa3323.aas.Options.Manager.SettingsOption;
import com.parsa3323.aas.Utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ArmsOptions extends SettingsOption {

    @Override
    public String getName() {
        return "ArmsOptions";
    }

    @Override
    public ItemStack getItemStack(ArmorStand armorStand) {
        ItemStack itemStack = PlayerUtils.getSkullFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGI2MzJiM2QwZjgyMGVjNjExNzA4ZTg5MjIyMjA1OWEzNjRkNzYyMjE3YzJjNmM5YmE3MWM1YWRiNDZmYzRiNCJ9fX0=");
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Arms");
        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Enable and disable");
        lore.add(ChatColor.GRAY + "arms for this armor stand ");
        lore.add("");
        lore.add((armorStand.hasArms()) ? ChatColor.GREEN + "✔ Has arms" : ChatColor.RED + "✘ Doesn't have arms");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void click(InventoryClickEvent e, ArmorStand armorStand) {
        armorStand.setArms(!armorStand.hasArms());
    }


}
