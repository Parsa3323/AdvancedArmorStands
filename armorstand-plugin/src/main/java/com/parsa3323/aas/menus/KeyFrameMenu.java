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

package com.parsa3323.aas.menus;

import com.cryptomorin.xseries.XMaterial;
import com.parsa3323.aas.api.exeption.ConfigException;
import com.parsa3323.aas.commands.AnimCreateCommand;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.menus.manager.PaginatedMenu;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import com.parsa3323.aas.utils.TextUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeyFrameMenu extends PaginatedMenu {

    public KeyFrameMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "IDK";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        String animationName = AnimCreateCommand.animationNames.get(playerMenuUtility.getOwner().getUniqueId());
        ConfigurationSection cs = AnimationConfig.get().getConfigurationSection("animations." + animationName);

        List<Map<?, ?>> steps = cs.getMapList("steps");

        if (e.getCurrentItem() == null) return;

        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null) return;

        ItemMeta meta = clickedItem.getItemMeta();
        if (meta == null) return;

        String displayName = meta.getDisplayName();
        if (displayName == null) return;

        String itemName = ChatColor.stripColor(displayName);

        if (clickedItem.getType() == Material.ARROW) {
            if (itemName.equalsIgnoreCase("Left")) {
                if (page > 0) {
                    page--;
                    super.open();
                }
            } else if (itemName.equalsIgnoreCase("Right")) {
                if (!((index + 1) >= steps.size())) {
                    page++;

                    super.open();
                }
            }
            return;
        }

        if (clickedItem.getType() == XMaterial.BARRIER.parseMaterial()) {
            player.closeInventory();
        }

    }

    @Override
    public void setMenuItems() {
        String animationName = AnimCreateCommand.animationNames.get(playerMenuUtility.getOwner().getUniqueId());
        ConfigurationSection cs = AnimationConfig.get().getConfigurationSection("animations." + animationName);

        if (cs == null) {
            throw new ConfigException(ChatColor.RED + "Animation config not found for " + animationName);
        }

        List<Map<?, ?>> steps = cs.getMapList("steps");

        addMenuBorder(steps.size());

        index = (page * maxItemsPerPage);

        for (int i = 0; i < maxItemsPerPage; i++) {
            if (index >= steps.size()) break;

            ArrayList<String> lore = new ArrayList<>();

            lore.add(ChatColor.GRAY + "This is the " + (index + 1) + TextUtils.getOrdinalSuffix(index + 1) + " step");
            lore.add(ChatColor.GRAY + "for " + animationName + " animation that");
            lore.add(ChatColor.GRAY + "will turn armor stand's position to this");
            lore.add(ChatColor.GRAY + "on its turn");

            lore.add(" ");

            lore.add(ChatColor.YELLOW + "Click to remove");

            ItemStack itemStack = new ItemStack(XMaterial.GREEN_TERRACOTTA.parseMaterial());
            ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName(ChatColor.YELLOW + "" + (index + 1) + TextUtils.getOrdinalSuffix(index + 1));
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            inventory.addItem(itemStack);
            index++;

        }

    }

    @Override
    public ItemStack getFillEmpty() {
        return null;
    }

    @Override
    public Sound getOpenSound() {
        return null;
    }

    @Override
    public boolean cancelClicks() {
        return true;
    }

    @Override
    public void close(InventoryCloseEvent e) {

    }
}
