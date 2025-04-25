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
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestOption extends SettingsOption {

    private String thing = "THING";

    @Override
    public String getName() {
        return "YEPO";
    }

    @Override
    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(Material.BOW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(thing);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void click(InventoryClickEvent e, ArmorStand armorStand) {
        System.out.println("CLICKKKKKKKKK");
         Player p = (Player) e.getWhoClicked();
         thing = "LING";
    }


}
