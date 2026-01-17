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

package com.parsa3323.aas.api.versionSupport;

import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface VersionSupport {

    ItemStack getSkull(String base64);

    boolean isGlowing(ArmorStand as);

    void setGlowing(ArmorStand as, boolean s);

    boolean canGlow();

    boolean canSetItemOffHand();

    ItemStack getItemInOffHand(ArmorStand as);

    void setItemInOffHand(ArmorStand as, ItemStack itemStack);

    Sound getEquipSound();

    void sendActionBar(Player player, String message);

    void rotateArmorStand(ArmorStand armorStand, float deltaYaw);

    void openBook(ItemStack book, Player p);
}
