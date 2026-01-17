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

package com.parsa3323.aas.actions.manager;

import com.cryptomorin.xseries.XMaterial;
import com.parsa3323.aas.actions.SenderOption;
import com.parsa3323.aas.actions.TriggerOption;
import com.parsa3323.aas.menus.ActionMenu;
import com.parsa3323.aas.menus.manager.PaginatedMenu;
import com.parsa3323.aas.utils.ArmorStandUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ActionManager extends PaginatedMenu {

    public ArrayList<ActionItem> actions = new ArrayList<>();

    private final String armorStandName;

    private final String commandPath;

    public ActionManager(PlayerMenuUtility playerMenuUtility, String armorStandName, String commandPath) {
        super(playerMenuUtility);

        this.armorStandName = armorStandName;
        this.commandPath = commandPath;

        actions.add(new SenderOption());
        actions.add(new TriggerOption());
    }

    @Override
    public String getMenuName() {
        return ChatColor.DARK_GRAY + "As " + ChatColor.GRAY + "» " + ChatColor.DARK_GRAY + "Action Settings";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
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
                if (!((index + 1) >= actions.size())) {
                    page++;

                    super.open();
                }
            }
            return;
        }

        if (clickedItem.getType() == XMaterial.BARRIER.parseMaterial()) {
            ActionMenu actionMenu = new ActionMenu(new PlayerMenuUtility(player), ArmorStandUtils.getArmorStandByName(armorStandName));
            actionMenu.open();
        }

        for (int i = 0; i < actions.size(); i++) {

            if (e.getCurrentItem().equals(actions.get(i).getItemStack(armorStandName, commandPath))) {

                actions.get(i).execute(e, armorStandName, commandPath);
                super.open();

                return;
            }

        }
    }

    @Override
    public void setMenuItems() {

        addMenuBorder(actions.size());

        index = page * maxItemsPerPage;

        for (int i = 0; i < actions.size(); i++) {
            if (index >= actions.size()) break;

            ItemStack itemStack = actions.get(i).getItemStack(armorStandName, commandPath);
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
