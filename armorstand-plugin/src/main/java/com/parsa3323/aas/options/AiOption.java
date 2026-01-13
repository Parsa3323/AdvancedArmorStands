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

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.ai.manager.AiSettingsManager;
import com.parsa3323.aas.options.manager.SettingsOption;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import com.parsa3323.aas.utils.TextUtils;
import com.parsa3323.aas.utils.VersionSupportUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AiOption extends SettingsOption {

    @Override
    public String getName() {
        return "AiOption";
    }

    @Override
    public ItemStack getItemStack(ArmorStand armorStand) {
        if (AdvancedArmorStands.isIsAiEnabled()) {
            ItemStack itemStack = VersionSupportUtil.getVersionSupport().getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWI4YzYzMjA4ZTdkOWNiOTkwNDkyMDUxNmE4MWRmNmUzNWZmYTIyNjljZTAxYzYyNWQxMWE5YTM4MWVkOTNhIn19fQ==");
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.YELLOW + "AI Options");
            ArrayList<String> lore = new ArrayList<>();

            lore.add(ChatColor.GRAY + "Options related to the");
            lore.add(ChatColor.GRAY + "artificial intelligence");
            lore.add(ChatColor.GRAY + "of this ArmorStand.");
            lore.add("");
            lore.add(ChatColor.YELLOW + "Click to open");


            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            return itemStack;
        } else {
            ItemStack itemStack = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
            ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName(ChatColor.YELLOW + "AI Options");

            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Enable AI for ArmorStands");
            lore.add(ChatColor.GRAY + "by setting your API key");
            lore.add(ChatColor.GRAY + "in the config.");
            lore.add("");
            lore.add(ColorUtils.boldAndColor(ChatColor.DARK_RED) + TextUtils.CROSS + ChatColor.RED + " Not Enabled");


            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            return itemStack;
        }
    }

    @Override
    public void click(InventoryClickEvent e, ArmorStand armorStand) {
        Player p = (Player) e.getWhoClicked();
        if (!AdvancedArmorStands.isIsAiEnabled()) {
            p.playSound(p.getLocation(), XSound.BLOCK_NOTE_BLOCK_BASS.parseSound(), 0.8f, 0.5f);
            return;
        }
        AiSettingsManager aiSettingsManager = new AiSettingsManager(new PlayerMenuUtility(p), armorStand);
        aiSettingsManager.open();

    }

    @Override
    public boolean updateInventory() {
        return false;
    }


}
