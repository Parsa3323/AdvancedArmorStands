/*
 * .
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
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

package me.parsa.aas.Player;

import me.parsa.aas.Utils.VersionSupportUtil;
import org.bukkit.entity.Player;

public class CustomPlayer implements IPlayer{

    private final Player bukkitPlayer;

    public CustomPlayer(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
    }

    @Override
    public Player getBukkitPlayer() {
        return this.bukkitPlayer;
    }

    @Override
    public void playSound(String s) {
        VersionSupportUtil.playVersionSpecificSound(bukkitPlayer, bukkitPlayer.getLocation(), s, 1, 1);
    }

    @Override
    public boolean isAdmin() {
        return this.bukkitPlayer.hasPermission("advanced-armorstands.admin");
    }
}
