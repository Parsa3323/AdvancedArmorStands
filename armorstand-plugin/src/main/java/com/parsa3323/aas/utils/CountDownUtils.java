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

package com.parsa3323.aas.utils;

import com.parsa3323.aas.AdvancedArmorStands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class CountDownUtils {

    private static final Map<UUID, Integer> countdowns = new HashMap<>();
    private static final Map<UUID, Integer> countdownTaskIds = new HashMap<>();

    public static void startCountdown(
            Player player,
            UUID id,
            int seconds,
            Consumer<Integer> onTick,
            Runnable onFinish
    ) {
        stopCountdown(id);

        countdowns.put(id, seconds);

        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(
                AdvancedArmorStands.plugin,
                () -> {
                    int time = countdowns.getOrDefault(id, -1);
                    if (time == -1) return;

                    if (time <= 0) {
                        stopCountdown(id);
                        onFinish.run();
                        return;
                    }

                    onTick.accept(time);
                    countdowns.put(id, time - 1);

                },
                0L, 20L
        );

        countdownTaskIds.put(id, taskId);
    }

    public static void resetCountdown(UUID id, int seconds) {
        if (countdowns.containsKey(id)) {
            countdowns.put(id, seconds);
        }
    }

    public static void stopCountdown(UUID id) {
        if (countdownTaskIds.containsKey(id)) {
            Bukkit.getScheduler().cancelTask(countdownTaskIds.get(id));
            countdownTaskIds.remove(id);
        }
        countdowns.remove(id);
    }

    public static boolean isRunning(UUID id) {
        return countdowns.containsKey(id);
    }
}
