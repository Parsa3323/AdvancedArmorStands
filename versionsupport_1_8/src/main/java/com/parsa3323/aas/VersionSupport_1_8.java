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

package com.parsa3323.aas;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import com.cryptomorin.xseries.profiles.builder.XSkull;
import com.cryptomorin.xseries.profiles.objects.Profileable;
import com.parsa3323.aas.api.versionSupport.IVersionSupport;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class VersionSupport_1_8 implements IVersionSupport {

    @Override
    public ItemStack getSkull(String base64) {
        ItemStack head = XMaterial.PLAYER_HEAD.parseItem();

        if(head == null) throw new RuntimeException("Failed to get skull");

        ItemMeta itemMeta = head.getItemMeta();

        if(itemMeta == null) return head;

        itemMeta = XSkull.of(itemMeta).profile(Profileable.detect(base64)).lenient().apply();

        head.setItemMeta(itemMeta);

        return head;
    }

    @Override
    public boolean isGlowing(ArmorStand as) {
        return false;
    }

    @Override
    public void setGlowing(ArmorStand as, boolean s) {

    }

    @Override
    public boolean canGlow() {
        return false;
    }

    @Override
    public boolean canSetItemOffHand() {
        return false;
    }

    @Override
    public ItemStack getItemInOffHand(ArmorStand as) {
        return null;
    }

    @Override
    public void setItemInOffHand(ArmorStand as, ItemStack itemStack) {

    }

    @Override
    public Sound getEquipSound() {
        return XSound.UI_BUTTON_CLICK.parseSound();
    }
}
