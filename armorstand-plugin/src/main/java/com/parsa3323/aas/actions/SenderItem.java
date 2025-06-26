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

package com.parsa3323.aas.actions;

import com.parsa3323.aas.actions.manager.ActionItem;
import com.parsa3323.aas.api.exeption.ConfigException;
import com.parsa3323.aas.config.ActionConfig;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SenderItem extends ActionItem {
    @Override
    public ItemStack getItemStack(String armorStandName, String commandPath) {
        ItemStack itemStack = new ItemStack(VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQ1NDgyOTY0ZDAzMGQyYWQ0YmVlZmQzYWJjZGY1YjliMGU0MTM0YjdhOWRkYTAwMzc4NWY2ZDg5ZWE5ZmZiMyJ9fX0="));
        ItemMeta itemMeta = itemStack.getItemMeta();
        ConfigurationSection cs = ActionConfig.get().getConfigurationSection("armorstand");

        if (cs == null) {
            throw new ConfigException("'armorstand' section missing in ActionConfig.yml");
        }

        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Select the sender of the");
        lore.add(ChatColor.GRAY + "command. This affects how");
        lore.add(ChatColor.GRAY + "permissions and execution work.");
        lore.add("");

        switch (cs.getString(armorStandName + "." + commandPath + ".type")) {
            case "server":
                lore.add(ChatColor.GRAY + "» Player");
                lore.add(ChatColor.YELLOW + "» Console");
                break;

            case "player":
                lore.add(ChatColor.YELLOW + "» Player");
                lore.add(ChatColor.GRAY + "» Console");
                break;

        }

        itemMeta.setLore(lore);

        itemMeta.setDisplayName(ChatColor.YELLOW + "Sender");

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void execute(InventoryClickEvent e, String s, String commandPath) {
        ConfigurationSection cs = ActionConfig.get().getConfigurationSection("armorstand");


        switch (cs.getString(s + "." + commandPath + ".type")) {
            case "server":
                cs.set(s + "." + commandPath + ".type", "player");
                ActionConfig.save();
                break;

            case "player":
                cs.set(s + "." + commandPath + ".type", "server");
                ActionConfig.save();
                break;

        }
    }
}
