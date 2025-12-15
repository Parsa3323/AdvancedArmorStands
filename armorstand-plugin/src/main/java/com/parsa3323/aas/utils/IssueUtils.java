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

import com.parsa3323.aas.api.data.IssueData;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IssueUtils {

    private static final Map<String, IssueData> ISSUES = new HashMap<>();

    public static void record(String message, String readMore) {
        String key = normalize(message);

        IssueData issue = ISSUES.get(key);
        if (issue == null) {
            ISSUES.put(key, new IssueData(message, readMore));
        } else {
            issue.occurrences++;
        }
    }

    public static Collection<IssueData> all() {
        return Collections.unmodifiableCollection(ISSUES.values());
    }

    private static String normalize(String msg) {
        return msg.toLowerCase().trim();
    }


}
