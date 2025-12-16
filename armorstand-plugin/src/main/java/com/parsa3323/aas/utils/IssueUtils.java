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

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.actions.IssueLevel;
import com.parsa3323.aas.api.data.IssueData;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class IssueUtils {

    private static final Map<String, IssueData> ISSUES = new HashMap<>();
    private static File file;

    public static void init() {
        file = new File(AdvancedArmorStands.plugin.getDataFolder(), "errors.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                AdvancedArmorStands.error("Error while creating errors.txt: " + e.getMessage(), true);
            }
        }
    }

    public static void record(IssueLevel level, String message, String readMore) {
        String key = level.name() + ":" + normalize(message);

        IssueData issue = ISSUES.get(key);
        if (issue == null) {
            ISSUES.put(key, new IssueData(message, readMore, level));
        } else {
            issue.occurrences++;
        }

        save();
    }


    public static void save() {
        if (file == null || ISSUES.isEmpty()) return;

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            writer.write("AdvancedArmorStands - Error Report");
            writer.newLine();
            writer.write("Generated at: " + sdf.format(new Date()));
            writer.newLine();
            writer.write("Total unique issues: " + ISSUES.size());
            writer.newLine();
            writer.newLine();

            for (IssueData issue : topIssues(Integer.MAX_VALUE)) {
                writer.write("- " + issue.message);
                writer.newLine();
                writer.write("  Occurrences: " + issue.occurrences);
                writer.newLine();

                if (issue.readMore != null) {
                    writer.write("  Read more: " + issue.readMore);
                    writer.newLine();
                }

                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            AdvancedArmorStands.error("Error while saving errors.txt: " + e.getMessage(), true);
        }
    }

    public static Collection<IssueData> all() {
        return Collections.unmodifiableCollection(ISSUES.values());
    }

    public static int getTotalIssues() {
        int count = 0;
        for (IssueData issue : ISSUES.values()) {
            if (issue.level == IssueLevel.ERROR) {
                count++;
            }
        }
        return count;
    }

    public static boolean hasIssues() {
        for (IssueData issue : ISSUES.values()) {
            if (issue.level == IssueLevel.ERROR) {
                return true;
            }
        }
        return false;
    }

    public static int getTotalWarnings() {
        int count = 0;
        for (IssueData issue : ISSUES.values()) {
            if (issue.level == IssueLevel.WARNING) {
                count++;
            }
        }
        return count;
    }

    public static boolean hasWarnings() {
        for (IssueData issue : ISSUES.values()) {
            if (issue.level == IssueLevel.WARNING) {
                return true;
            }
        }
        return false;
    }


    public static List<IssueData> topIssues(int limit) {
        return ISSUES.values().stream()
                .sorted((a, b) -> Integer.compare(b.occurrences, a.occurrences))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public static List<IssueData> getErrorsList() {
        return ISSUES.values().stream()
                .filter(issue -> issue.level == IssueLevel.ERROR)
                .sorted((a, b) -> Integer.compare(b.occurrences, a.occurrences))
                .collect(Collectors.toList());
    }

    public static List<IssueData> getWarnsList() {
        return ISSUES.values().stream()
                .filter(issue -> issue.level == IssueLevel.WARNING)
                .sorted((a, b) -> Integer.compare(b.occurrences, a.occurrences))
                .collect(Collectors.toList());
    }


    private static String normalize(String msg) {
        return msg.toLowerCase().trim();
    }
}
