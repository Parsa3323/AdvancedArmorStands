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

package com.parsa3323.aas.menus;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.api.exeption.ConfigException;
import com.parsa3323.aas.api.language.Language;
import com.parsa3323.aas.api.language.Messages;
import com.parsa3323.aas.commands.AnimCreateCommand;
import com.parsa3323.aas.config.AnimationConfig;
import com.parsa3323.aas.menus.manager.PaginatedMenu;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.KeyFrameUtils;
import com.parsa3323.aas.utils.PlayerMenuUtility;
import com.parsa3323.aas.utils.TextUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
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
        return Language.getMsg(Messages.KEYFRAME_MENU_TITLE);
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
            return;
        }

        if (e.getSlot() == 50) {
            boolean current = AnimationConfig.get().getBoolean("animations." + animationName + ".realistic-animations.enabled");
            AnimationConfig.get().set("animations." + animationName + ".realistic-animations.enabled", !current);

            AnimationConfig.save();
            super.open();
            return;
        }

        if (e.getSlot() == 51) {
            int currentFrames = AnimationConfig.get().getInt("animations." + animationName + ".realistic-animations.frames");

            if (e.getClick() == ClickType.SHIFT_LEFT) {
                AnimationConfig.get().set("animations." + animationName + ".realistic-animations.frames", (currentFrames + 10));
                super.open();
            } else if (e.getClick() == ClickType.LEFT) {
                AnimationConfig.get().set("animations." + animationName + ".realistic-animations.frames", (currentFrames + 1));
                super.open();
            } else if (e.getClick() == ClickType.SHIFT_RIGHT) {
                AnimationConfig.get().set("animations." + animationName + ".realistic-animations.frames", (currentFrames - 10));
                super.open();
            } else if (e.getClick() == ClickType.RIGHT) {
                AnimationConfig.get().set("animations." + animationName + ".realistic-animations.frames", (currentFrames - 1));
                super.open();
            }

            AnimationConfig.save();
            return;
        }

        if (e.getSlot() == 47) {

            int currentInterval = AnimationConfig.get().getInt("animations." + animationName + ".interval");

            if (e.getClick() == ClickType.SHIFT_LEFT) {
                AnimationConfig.get().set("animations." + animationName + ".interval", (currentInterval + 10));
                super.open();
            } else if (e.getClick() == ClickType.LEFT) {
                AnimationConfig.get().set("animations." + animationName + ".interval", (currentInterval + 1));
                super.open();
            } else if (e.getClick() == ClickType.SHIFT_RIGHT) {
                AnimationConfig.get().set("animations." + animationName + ".interval", (currentInterval - 10));
                super.open();
            } else if (e.getClick() == ClickType.RIGHT) {
                AnimationConfig.get().set("animations." + animationName + ".interval", (currentInterval - 1));
                super.open();
            }

            AnimationConfig.save();
            return;
        }

        if (e.getSlot() == 48) {
            boolean currentLoop = AnimationConfig.get().getBoolean("animations." + animationName + ".loop");

            AnimationConfig.get().set("animations." + animationName + ".loop", !currentLoop);

            AnimationConfig.save();
            super.open();
            return;
        }

        KeyFrameUtils.removeStep(AnimCreateCommand.animationNames.get(playerMenuUtility.getOwner().getUniqueId()), Integer.parseInt(ColorUtils.removeColors(displayName)));

        super.open();

        player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.2f);
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

            for (String line : Language.getLore(Messages.KEYFRAME_STEP_LORE)) {
                lore.add(line
                        .replace("%step%", String.valueOf(index + 1))
                        .replace("%suffix%", TextUtils.getOriginalSuffix(index + 1))
                        .replace("%animation%", animationName));
            }

            ItemStack itemStack = new ItemStack(XMaterial.GREEN_TERRACOTTA.parseMaterial());
            ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName(ChatColor.YELLOW + "" + (index + 1));
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            inventory.addItem(itemStack);
            index++;

        }
        ItemStack realistic = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
        ItemMeta iRealistic = realistic.getItemMeta();

        ArrayList<String> rLore = new ArrayList<>(Language.getLore(Messages.KEYFRAME_REALISTIC_NAME_LORE));

        iRealistic.setDisplayName(Language.getMsg(Messages.KEYFRAME_REALISTIC_NAME_DISPLAY).replaceAll("%value%", AnimationConfig.get().getString("animations." + animationName + ".realistic-animations.enabled")));

        iRealistic.setLore(rLore);

        realistic.setItemMeta(iRealistic);

        inventory.setItem(50, realistic);

        ItemStack frames = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
        ItemMeta iFrame = frames.getItemMeta();

        ArrayList<String> fLore = new ArrayList<>(Language.getLore(Messages.KEYFRAME_REALISTIC_FRAMES_LORE));

        iFrame.setDisplayName(Language.getMsg(Messages.KEYFRAME_REALISTIC_FRAMES_DISPLAY).replaceAll("%value%", AnimationConfig.get().getString("animations." + animationName + ".realistic-animations.frames")));

        iFrame.setLore(fLore);

        frames.setItemMeta(iFrame);

        inventory.setItem(51, frames);

        ItemStack interval = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());
        ItemMeta iInterval = interval.getItemMeta();

        ArrayList<String> lore = new ArrayList<>(Language.getLore(Messages.KEYFRAME_INTERVAL_LORE));

        iInterval.setLore(lore);

        iInterval.setDisplayName(Language.getMsg(Messages.KEYFRAME_INTERVAL_NAME).replace("%interval%", String.valueOf(AnimationConfig.get().getInt("animations." + animationName + ".interval"))));

        interval.setItemMeta(iInterval);

        inventory.setItem(47, interval);

        ItemStack loop = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial());

        ItemMeta iLoop = loop.getItemMeta();

        ArrayList<String> iLore = new ArrayList<>(Language.getLore(Messages.KEYFRAME_LOOP_LORE));

        iLoop.setLore(iLore);

        iLoop.setDisplayName(Language.getMsg(Messages.KEYFRAME_LOOP_NAME).replace("%loop%", String.valueOf(AnimationConfig.get().getBoolean("animations." + animationName + ".loop"))));

        loop.setItemMeta(iLoop);

        inventory.setItem(48, loop);
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

    @Override
    public void addMenuBorder(int total) {
        if (page > 0) {
            inventory.setItem(46, makeItem(Material.ARROW, ChatColor.GREEN + "Left"));
        } else {
            inventory.setItem(46, super.FILLER_GLASS);
        }

        inventory.setItem(49, makeItem(Material.BARRIER, ChatColor.RED + "Close"));

        if ((page + 1) * maxItemsPerPage < total) {
            inventory.setItem(52, makeItem(Material.ARROW, ChatColor.GREEN + "Right"));
        } else {
            inventory.setItem(52, super.FILLER_GLASS);
        }

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
        inventory.setItem(17, super.FILLER_GLASS);
        inventory.setItem(18, super.FILLER_GLASS);
        inventory.setItem(26, super.FILLER_GLASS);
        inventory.setItem(27, super.FILLER_GLASS);
        inventory.setItem(35, super.FILLER_GLASS);
        inventory.setItem(36, super.FILLER_GLASS);

        for (int i = 44; i < 54; i++) {
            if (i == 50) continue;
            if (i == 48) continue;
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
    }


}