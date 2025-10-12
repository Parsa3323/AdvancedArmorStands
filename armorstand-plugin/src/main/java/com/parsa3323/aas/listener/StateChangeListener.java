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

package com.parsa3323.aas.listener;

import com.parsa3323.aas.api.events.ArmorStandStateChangeEvent;
import com.parsa3323.aas.config.ArmorStandsConfig;
import com.parsa3323.aas.utils.ArmorStandUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StateChangeListener implements Listener {

    @EventHandler
    public void onStateChange(ArmorStandStateChangeEvent e) {
        ArmorStandUtils.saveArmorStand(e.getName(), e.getArmorStand());

        String path = "armorstands." + ArmorStandUtils.getNameByArmorStand(e.getArmorStand());

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = format.format(now);

        ArmorStandsConfig.get().set(path + ".info.last_changed_date", formattedDate);
        ArmorStandsConfig.get().set(path + ".info.last_changed_player", e.getPlayer().getDisplayName());

        ArmorStandsConfig.save();

    }
}
