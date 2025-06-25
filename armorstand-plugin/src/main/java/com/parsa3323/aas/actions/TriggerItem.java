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
import com.parsa3323.aas.config.ActionConfig;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TriggerItem extends ActionItem {

    @Override
    public ItemStack getItemStack(String armorStandName, String commandPath) {
        ItemStack itemStack = new ItemStack(VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOThiMTA5YjAyOWYzZmQ1ODAzYzIxZjFkNzE0YmU1NTE0MmRmYzAzNTJkMGM0YzY1MjZiZGI1MmU3MTg5YWFmMiJ9fX0="));
        ItemMeta itemMeta = itemStack.getItemMeta();
        ConfigurationSection cs = ActionConfig.get().getConfigurationSection("armorstand" + "." + armorStandName + "." + commandPath);

        if (cs == null) {
            throw new IllegalStateException("'armorstand' section missing in ActionConfig.yml");
        }

        ArrayList<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Select how the command");
        lore.add(ChatColor.GRAY + "will be triggered when");
        lore.add(ChatColor.GRAY + "the player interacts with");
        lore.add(ChatColor.GRAY + "the armor stand.");

        lore.add("");

        switch (cs.getString("trigger")) {
            case "all":
                lore.add(ChatColor.GRAY + "» Right click");
                lore.add(ChatColor.GRAY + "» Left click");
                lore.add(ChatColor.GRAY + "» Shift right click");
                lore.add(ChatColor.GRAY + "» Shift left click");
                lore.add(ChatColor.YELLOW + "» All");
                break;
            case "right-click":
                lore.add(ChatColor.YELLOW + "» Right click");
                lore.add(ChatColor.GRAY + "» Left click");
                lore.add(ChatColor.GRAY + "» Shift right click");
                lore.add(ChatColor.GRAY + "» Shift left click");
                lore.add(ChatColor.GRAY + "» All");
                break;
            case "left-click":
                lore.add(ChatColor.GRAY + "» Right click");
                lore.add(ChatColor.YELLOW + "» Left click");
                lore.add(ChatColor.GRAY + "» Shift right click");
                lore.add(ChatColor.GRAY + "» Shift left click");
                lore.add(ChatColor.GRAY + "» All");
                break;
            case "shift-right-click":
                lore.add(ChatColor.GRAY + "» Right click");
                lore.add(ChatColor.GRAY + "» Left click");
                lore.add(ChatColor.YELLOW + "» Shift right click");
                lore.add(ChatColor.GRAY + "» Shift left click");
                lore.add(ChatColor.GRAY + "» All");
                break;
            case "shift-left-click":
                lore.add(ChatColor.GRAY + "» Right click");
                lore.add(ChatColor.GRAY + "» Left click");
                lore.add(ChatColor.GRAY + "» Shift right click");
                lore.add(ChatColor.YELLOW + "» Shift left click");
                lore.add(ChatColor.GRAY + "» All");
                break;

        }


        itemMeta.setLore(lore);
        itemMeta.setDisplayName(ChatColor.YELLOW + "Trigger Type");

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void execute(InventoryClickEvent e, String s, String commandPath) {
        ConfigurationSection cs = ActionConfig.get().getConfigurationSection("armorstand" + "." + s + "." + commandPath);

        if (cs == null) {
            throw new IllegalStateException("'armorstand' section missing in ActionConfig.yml");
        }

        String currentTrigger = cs.getString("trigger", "all");
        String nextTrigger;

        switch (currentTrigger) {
            case "right-click":
                nextTrigger = "left-click";
                break;
            case "left-click":
                nextTrigger = "shift-right-click";
                break;
            case "shift-right-click":
                nextTrigger = "shift-left-click";
                break;
            case "shift-left-click":
                nextTrigger = "all";
                break;
            case "all":
            default:
                nextTrigger = "right-click";
                break;
        }

        cs.set("trigger", nextTrigger);

        ActionConfig.save();

    }
}
