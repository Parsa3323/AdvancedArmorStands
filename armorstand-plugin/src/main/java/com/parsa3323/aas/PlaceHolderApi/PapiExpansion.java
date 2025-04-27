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

package com.parsa3323.aas.PlaceHolderApi;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.Utils.ArmorStandUtils;
import com.parsa3323.aas.Utils.TypeUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PapiExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "aas";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Parsa3323";
    }

    @Override
    public @NotNull String getVersion() {
        return AdvancedArmorStands.plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) return "";

        if (params.equalsIgnoreCase("total_armor_stands")) return String.valueOf(ArmorStandUtils.getTotalArmorStands());

        if (params.equalsIgnoreCase("total_types")) return String.valueOf(TypeUtils.getTotalTypes());

        return "";
    }
}
