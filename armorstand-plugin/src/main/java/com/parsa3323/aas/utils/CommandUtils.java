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

package com.parsa3323.aas.utils;

import com.parsa3323.aas.commands.manager.SubCommand;

import java.util.ArrayList;

public class CommandUtils {

    public static String getClosestCommand(String input, ArrayList<SubCommand> commands) {
        int minDistance = Integer.MAX_VALUE;
        String closest = null;

        for (SubCommand cmd : commands) {
            int distance = levenshteinDistance(input.toLowerCase(), cmd.getName().toLowerCase());
            if (distance < minDistance) {
                minDistance = distance;
                closest = cmd.getName();
            }
        }

        return (minDistance <= 3) ? closest : null;
    }

    private static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1])
                    );
                }
            }
        }

        return dp[a.length()][b.length()];
    }
}
