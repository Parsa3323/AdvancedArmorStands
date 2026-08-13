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

package com.parsa3323.aas.languages;

import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.api.language.Language;
import com.parsa3323.aas.api.language.Messages;
import com.parsa3323.aas.utils.TextUtils;
import org.bukkit.configuration.file.YamlConfiguration;

public class Bangla extends Language {
    /*
    Made with AI
    */
    public Bangla() {
        super(AdvancedArmorStands.plugin, "bn");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "অ্যাকশনগুলো");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7মানে তুমি যদি এই আর্মার\n&7স্ট্যান্ডে ক্লিক করো,\n&7তাহলে এই কমান্ডটা\n&7চলবে: %command%\n\n&6» &eএডিট করতে ক্লিক করো\n&6» &eমুছতে Shift + ক্লিক করো");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eঅ্যাকশন তৈরি করো");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE, "&7ক্লিক করলে কমান্ড\n&7চালায় এমন একটা\n&7অ্যাকশন বানাতে এটা বেছে নাও\n\n&eতৈরি করতে ক্লিক করো");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aযে কমান্ডটা সেট করতে চাও সেটা লেখো ('/' ছাড়া)। বাতিল করতে 'exit' লেখো।");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "অ্যাকশন সেটিংস");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eট্রিগারের ধরন");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7প্লেয়ার যখন আর্মারস্ট্যান্ডের\n&7সাথে ইন্টার‍্যাক্ট করবে তখন\n&7কমান্ডটা কীভাবে ট্রিগার\n&7হবে সেটা বেছে নাও।\n\n%trigger_list%\n\n&eবদলাতে ক্লিক করো");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eপ্রেরক");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7কমান্ডটা কার তরফ থেকে\n&7পাঠানো হবে সেটা বেছে নাও। এটা\n&7পারমিশন আর এক্সিকিউশন কীভাবে কাজ করে তার উপর প্রভাব ফেলে।\n\n%sender_list%\n\n&eবদলাতে ক্লিক করো");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_NAME, "&eঅগ্রাধিকার");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_LORE, "&7এই কমান্ডের অগ্রাধিকার\n&7পরিবর্তন করুন। যখন\n&7অগ্রাধিকার বেশি হবে,\n&7তখন এটি অন্যগুলোর চেয়ে\n&7আগে কার্যকর হবে।\n\n&6» &e%priority_number%\n\n&eপরিবর্তন করতে ক্লিক করুন");
        yml.addDefault(Messages.ACTIONS_SETTING_PRIORITY_ALREADY_TAKEN, "&eদুই বা ততোধিক অ্যাকশনের একই অগ্রাধিকার রয়েছে, যার ফলে তারা একে অপরের সাথে সংঘর্ষ করতে পারে।");
        yml.addDefault(Messages.AI_MENU_TITLE, "এআই সেটিংস");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "এআই-কে সাহায্যের জন্য বলো");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7ভাবছে...");
        yml.addDefault(Messages.AI_OPTION_NAME, "&eএআই অপশনগুলো");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE, "&7এই আর্মারস্ট্যান্ডের\n&7আর্টিফিশিয়াল ইন্টেলিজেন্স\n&7সংক্রান্ত অপশনগুলো।\n\n&eখুলতে ক্লিক করো");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE, "&7কনফিগে তোমার API key\n&7বসিয়ে আর্মারস্ট্যান্ডের জন্য\n&7এআই চালু করো।\n\n&4✘ &cচালু নেই");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7এই আর্মারস্ট্যান্ডের এআই\n&7উত্তর দেওয়ার সময় যেটা মেনে চলবে\nসেই কাস্টম নির্দেশনা যোগ করো।\n\n%current%\n\n&6» &eরিসেট করতে Shift + ক্লিক করো\n&6» &eবদলাতে ক্লিক করো");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7এই আর্মারস্ট্যান্ডের\n&7এআই চালু বা বন্ধ করো\n\n%ai_status%");
        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS, "&aতুমি সফলভাবে মেমরি সেশন থেকে বেরিয়ে গেছো।");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS, "&aআর্মারস্ট্যান্ডের নির্দেশনা সফলভাবে আপডেট হয়েছে");
        yml.addDefault(Messages.AI_PREFIX, "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT, "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND, "এআই এরর: রেসপন্সে অ্যাসিস্ট্যান্টের টেক্সট পাওয়া যায়নি");
        yml.addDefault(Messages.AI_ERROR, "&cএআই এরর: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP, "&cএআই এরর: HTTP %code%, ইন্টারনেট কানেকশন চেক করো");
        yml.addDefault(Messages.AI_HTTP_ERROR, "এআই এরর: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET, "এআই এরর: HTTP {code}, ইন্টারনেট কানেকশন চেক করো");
        yml.addDefault(Messages.AI_PARSE_ERROR, "এআই রেসপন্স পার্স করতে সমস্যা: {error}");
        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "অ্যানিমেশন কমান্ডগুলো দেখায়");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER, "&6&lAdvanced &e&lArmorStands &7&lঅ্যানিমেশন কমান্ডগুলো");
        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS, "&aঅ্যানিমেশন %animation% আর্মারস্ট্যান্ড %armorstand%-এ সফলভাবে সেট করা হয়েছে");
        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION, "একটা as-এর &7অ্যানিমেশন মুছে দেয়");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS, "&a%armorstand%-এর অ্যানিমেশনগুলো সফলভাবে মুছে ফেলা হয়েছে");
        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION, "&7একটা as দিয়ে &7অ্যানিমেশন তৈরি/এডিট করো");
        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION, "&7একটা অ্যানিমেশন পুরোপুরি মুছে দেয়");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS, "&aঅ্যানিমেশন '%animation%' সফলভাবে মুছে ফেলা হয়েছে");
        yml.addDefault(Messages.ANIMATION_INVALID, "&cঅ্যানিমেশনটা সঠিক না");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION, "&cঅ্যানিমেশন '%animation%' সঠিক না। তুমি কি '%suggestion%' বলতে চেয়েছিলে?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME, "&cঅ্যানিমেশনের নামটা সঠিক না");
        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION, "&cযে আর্মারস্ট্যান্ডে আগে থেকেই অ্যানিমেশন আছে সেখানে এডিটর খোলা যাবে না");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED, "&aতোমার গেমমোড সাময়িকভাবে CREATIVE-এ পাল্টে দেওয়া হয়েছে, কারণ এডিট সেশনে ADVENTURE মোড সাপোর্ট করে না। বেরিয়ে গেলে এটা আপনা-আপনি আগের মতো হয়ে যাবে।");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED, "&aতুমি সফলভাবে অ্যানিমেশন তৈরি/এডিট সেশনে ঢুকেছো");
        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aঅ্যানিমেশনটা সফলভাবে সেভ হয়েছে, এই আর্মারস্ট্যান্ডে যোগ করতে ক্লিক করো");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "কিফ্রেম এডিটর");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eরাইট ক্লিক &7একটা কিফ্রেম যোগ করো\n&eSHIFT + রাইট ক্লিক &7কিফ্রেম মেনু খোলো\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aকিফ্রেম যোগ হয়েছে!");
        yml.addDefault(Messages.KEYFRAME_STEP_LORE, "&7এটা %animation% অ্যানিমেশনের\n&7%step%%suffix% ধাপ, যেটা এর\n&7পালা এলে আর্মারস্ট্যান্ডকে\n&7এই পজিশনে নিয়ে আসবে\n\n&eমুছতে ক্লিক করো");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME, "&eইন্টারভাল: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE, "&7অ্যানিমেশন ফ্রেমগুলোর মধ্যে\n&7বিরতি ঠিক করে দেয়।\n&7ভ্যালু যত কম হবে,\n&7অ্যানিমেশন তত দ্রুত চলবে।\n\n&6»&e বাড়াতে লেফট-ক্লিক করো\n&6»&e কমাতে রাইট-ক্লিক করো।\n&6»&e ১০ করে বদলাতে\n&6»&e Shift ধরে রাখো।\n\n&eবদলাতে ক্লিক করো");
        yml.addDefault(Messages.KEYFRAME_LOOP_NAME, "&eলুপ: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE, "&7অ্যানিমেশনটা শুরু থেকে\n&7অসীমবার লুপ হয়ে\n&7চলবে কিনা সেটা ঠিক করে\n\n&eবদলাতে ক্লিক করো");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_DISPLAY, "&eবাস্তবসম্মত: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_LORE, "&7বাস্তবসম্মত অ্যানিমেশনগুলো অ্যানিমেশনের ধাপগুলোর\n&7মধ্যে মসৃণভাবে ইন্টারপোলেশন করে, ফলে তাৎক্ষণিকভাবে\n&7একটি পোজ থেকে অন্য পোজে লাফ দেয় না।\n\n&7সতর্কতা: এর কারণে ল্যাগ হতে পারে\n\n&eপরিবর্তন করতে ক্লিক করুন");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_DISPLAY, "&eবাস্তবসম্মত ফ্রেম: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_LORE, "&7বাস্তবসম্মত অ্যানিমেশনের সময়\n&7তৈরি হওয়া ফ্রেমের সংখ্যা\n\n&6»&e বাড়াতে বাম-ক্লিক করুন\n&6»&e কমাতে ডান-ক্লিক করুন।\n&6»&e ১০ করে পরিবর্তন করতে\n&6»&e Shift ধরে রাখুন।\n\n&eপরিবর্তন করতে ক্লিক করুন");
        yml.addDefault(Messages.SAVE_MENU_TITLE, "সেভ মেনু");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE, "&aযে টাইপটা তৈরি করতে চাও তার নাম লেখো, এই আর্মারস্ট্যান্ডের প্রপার্টিগুলো সেখানে কপি হয়ে যাবে। বেরোতে 'exit' লেখো");
        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE, "&7এটা বেছে নিলে %type%-এর\n&7পুরোনো কনফিগ তুমি এখন যা\n&7সেট করেছো সেটা দিয়ে বদলে যাবে\n\n&eসেভ করতে ক্লিক করো");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED, "&aআর্মারস্ট্যান্ডের প্রপার্টিগুলো '%type%'-এ সেভ হয়েছে");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME, "&eএকটা টাইপ তৈরি করো");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE, "&7এই আর্মার স্ট্যান্ডের\n&7প্রপার্টি দিয়ে একটা টাইপ\n&7তৈরি করতে এটা বেছে নাও\n\n&eতৈরি করতে ক্লিক করো");
        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "সেটিংস");
        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cএই আর্মারস্ট্যান্ডটা লোড হয়নি");
        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cআর্মারস্ট্যান্ডটা সঠিক না");
        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cআর্মারস্ট্যান্ড '%armorstand%' সঠিক না। তুমি কি '%suggestion%' বলতে চেয়েছিলে?");
        yml.addDefault(Messages.ARMORSTAND_NOT_FOUND, "&cআর্মারস্ট্যান্ড খুঁজে পাওয়া যায়নি!");
        yml.addDefault(Messages.WORLD_NOT_FOUND, "&cওয়ার্ল্ড খুঁজে পাওয়া যায়নি!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMORSTAND, "&a%name%-এর কাছে টেলিপোর্ট হয়েছে");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_SUCCESS, "&aআর্মারস্ট্যান্ড সফলভাবে আপডেট হয়েছে!");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_FAILED, "&cআর্মারস্ট্যান্ড আপডেট করা যায়নি!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE, "&cআর্মারস্ট্যান্ডটা আর নেই!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE_TITLE, "&cআর্মারস্ট্যান্ডটা পাওয়া যাচ্ছে না");
        yml.addDefault(Messages.ARMORSTAND_MOVE_SUCCESS, "&aআর্মারস্ট্যান্ড সফলভাবে সরানো হয়েছে।");
        yml.addDefault(Messages.ARMORSTANDS_NOT_LOADED, "&cমনে হচ্ছে %amount% টা আর্মারস্ট্যান্ড ওয়ার্ল্ড জেনারেটর লোড করেনি। ঠিক করতে, সবগুলো অটোমেটিক লোড করতে কনফিগে 'auto-load-armor-stands' চালু করো।");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN, "&eAdvancedArmorStands এই সেশনে %amount% টা এরর/ওয়ার্নিং পেয়েছে। বিস্তারিত জানতে লগ ফাইল চেক করো।");
        yml.addDefault(Messages.ARMORSTAND_SAVE_CONFIRM, "&aএই আর্মারস্ট্যান্ডটা সেভ করতে এটা আরও %amount% বার করো।");
        yml.addDefault(Messages.ARMORSTAND_SAVED_SUCCESS, "&eআর্মারস্ট্যান্ড %name% নামে সেভ হয়েছে!");
        yml.addDefault(Messages.ARMORSTAND_DELETE_CONFIRM, "&cএই আর্মারস্ট্যান্ডটা মুছতে এটা আরও %amount% বার করো।");
        yml.addDefault(Messages.ARMORSTAND_DELETED_SUCCESS, "&aআর্মারস্ট্যান্ডটা মোছা হয়েছে, তবে restore কমান্ড দিয়ে এটা ফিরিয়ে আনা যাবে, সার্ভার রিস্টার্ট হলে এটা পুরোপুরি মুছে যাবে");
        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cএই কমান্ডটা ব্যবহার করার পারমিশন তোমার নেই!");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&c'%command%' কমান্ডটা কোনো সঠিক সাবকমান্ড না।");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&c'%command%' কমান্ডটা কোনো সঠিক সাবকমান্ড না। তুমি কি '%suggestion%' বলতে চেয়েছিলে?");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cদয়া করে সঠিক একটা পেজ নাম্বার দাও।");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cপেজটা সঠিক না। ১ থেকে %pages%-এর মধ্যে একটা পেজ বেছে নাও।");
        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eসাজেশনটা ব্যবহার করতে ক্লিক করো");
        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7উদাহরণ:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE, "&e&lএই কমান্ডটা ব্যবহার করতে ক্লিক করো");
        yml.addDefault(Messages.COMMAND_LABEL, "&7কমান্ড: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL, "&7বিবরণ: &f%description%");
        yml.addDefault(Messages.CREATE_DESCRIPTION, "একটা &7আর্মারস্ট্যান্ড তৈরি করে");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE, "&cব্যবহার: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER, "&c%part% অংশের জন্য সংখ্যাটা সঠিক না। ব্যবহার: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART, "&cঅজানা অংশ: %part%");
        yml.addDefault(Messages.DELETE_DESCRIPTION, "একটা &7আর্মারস্ট্যান্ড মুছে দেয়");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS, "&aসব আর্মারস্ট্যান্ড সফলভাবে মুছে ফেলা হয়েছে");
        yml.addDefault(Messages.DELETE_SUCCESS, "&aআর্মারস্ট্যান্ড পুরোপুরি মুছে ফেলা হয়েছে");
        yml.addDefault(Messages.DEBUG_DESCRIPTION, "ডিবাগ তথ্য দেখায়");
        yml.addDefault(Messages.HEAD_DESCRIPTION, "একজন প্লেয়ারের মাথা নাও");
        yml.addDefault(Messages.HEAD_SUCCESS, "&aতোমাকে %player%-এর মাথা দেওয়া হয়েছে");
        yml.addDefault(Messages.LIST_DESCRIPTION, "আর্মারস্ট্যান্ডের একটা লিস্ট দেখায়");
        yml.addDefault(Messages.LIST_EMPTY_TITLE, "&c&l     কোনো সেভ করা আর্মারস্ট্যান্ড পাওয়া যায়নি ");
        yml.addDefault(Messages.LIST_EMPTY_HINT, "&7তোমার প্রথম আর্মারস্ট্যান্ড তৈরি করতে &e/as create <type> <name>&7 ব্যবহার করো!");
        yml.addDefault(Messages.LOAD_DESCRIPTION, "একটা আর্মারস্ট্যান্ড লোড করে");
        yml.addDefault(Messages.LOAD_NO_UNLOADED, "&eলোড হয়নি এমন কোনো আর্মারস্ট্যান্ড পাওয়া যায়নি।");
        yml.addDefault(Messages.LOAD_FAILED, "&cফেইল হয়েছে: %failed%");
        yml.addDefault(Messages.MOVE_DESCRIPTION, "একটা আর্মারস্ট্যান্ডকে তোমার &7লোকেশনে নিয়ে আসে");
        yml.addDefault(Messages.OPTIONS_DESCRIPTION, "অপশন মেনু খোলে");
        yml.addDefault(Messages.RELOAD_DESCRIPTION, "প্লাগইনের কনফিগগুলো রিলোড করে");
        yml.addDefault(Messages.RELOAD_SUCCESS, "&aAdvancedArmorStand-এর কনফিগ সফলভাবে রিলোড হয়েছে");
        yml.addDefault(Messages.RELOAD_ERROR, "&cপ্লাগইন রিলোড করার সময় এরর হয়েছে, বিস্তারিত জানতে কনসোল চেক করো");
        yml.addDefault(Messages.RENAME_DESCRIPTION, "একটা &7as-এর নাম বদলায়");
        yml.addDefault(Messages.RENAME_SUCCESS, "&aআর্মারস্ট্যান্ডের নাম '%old_name%' থেকে '%new_name%'-এ বদলানো হয়েছে");
        yml.addDefault(Messages.RESTORE_DESCRIPTION, "মোছা একটা আর্মারস্ট্যান্ড ফিরিয়ে আনে");
        yml.addDefault(Messages.RESTORE_NOT_DELETED, "&cএই আর্মারস্ট্যান্ডটা মোছা হয়নি অথবা অনেক দেরি হয়ে গেছে");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR, "&cঅজানা এরর, বিস্তারিত জানতে কনসোল চেক করো");
        yml.addDefault(Messages.RESTORE_SUCCESS, "&aআর্মারস্ট্যান্ড সফলভাবে ফিরিয়ে আনা হয়েছে");
        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND, "&cএই আর্মারস্ট্যান্ডটা মাটিতে দাঁড়িয়ে নেই। তুমি কি সত্যিই সেখানে টেলিপোর্ট করতে চাও?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT, "&cজোর করে টেলিপোর্ট করতে '/as teleport %armorstand% --force' ব্যবহার করো");
        yml.addDefault(Messages.TELL_DESCRIPTION, "একটা আর্মারস্ট্যান্ডের এআই-কে একটা প্রশ্ন জিজ্ঞেস করো");
        yml.addDefault(Messages.TELL_AI_DISABLED, "&cএই আর্মারস্ট্যান্ডের এআই চালু নেই");
        yml.addDefault(Messages.INVENTORY_EXIT_NAME, "&7» &cবেরিয়ে যাও&7 (রাইট ক্লিক)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE, "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION, "&aতুমি এডিট সেশনে ঢুকেছো, ফিরে গিয়ে তোমার জিনিসপত্র ফেরত পেতে বেরিয়ে যাও আইটেমে ক্লিক করো");
        yml.addDefault(Messages.EDITOR_EXIT_NAME, "&7» &cবেরিয়ে যাও &7(রাইট ক্লিক)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE, "\n&8AdvancedArmorStands Editor Item");
        yml.addDefault(Messages.HEAD_ITEM_LORE, "&eরাইট ক্লিক &8» &7মাথা ডানদিকে ঘোরাও\n&eলেফট ক্লিক &8» &7মাথা বাঁদিকে ঘোরাও\n&eSHIFT + রাইট ক্লিক &8» &7মাথা উপরে তোলো\n&eSHIFT + লেফট ক্লিক &8» &7মাথা নিচে নামাও\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE, "&eরাইট ক্লিক &8» &7বাঁ হাত ডানদিকে ঘোরাও\n&eলেফট ক্লিক &8» &7বাঁ হাত বাঁদিকে ঘোরাও\n&eSHIFT + রাইট ক্লিক &8» &7বাঁ হাত উপরে তোলো\n&eSHIFT + লেফট ক্লিক &8» &7বাঁ হাত নিচে নামাও\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE, "&eরাইট ক্লিক &8» &7ডান হাত ডানদিকে ঘোরাও\n&eলেফট ক্লিক &8» &7ডান হাত বাঁদিকে ঘোরাও\n&eSHIFT + রাইট ক্লিক &8» &7ডান হাত উপরে তোলো\n&eSHIFT + লেফট ক্লিক &8» &7বাঁ হাত নিচে নামাও\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE, "&eরাইট ক্লিক &8» &7বাঁ পা ডানদিকে ঘোরাও\n&eলেফট ক্লিক &8» &7বাঁ পা বাঁদিকে ঘোরাও\n&eSHIFT + রাইট ক্লিক &8» &7বাঁ পা উপরে তোলো\n&eSHIFT + লেফট ক্লিক &8» &7বাঁ পা নিচে নামাও\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE, "&eরাইট ক্লিক &8» &7ডান পা ডানদিকে ঘোরাও\n&eলেফট ক্লিক &8» &7ডান পা বাঁদিকে ঘোরাও\n&eSHIFT + রাইট ক্লিক &8» &7ডান পা উপরে তোলো\n&eSHIFT + লেফট ক্লিক &8» &7ডান পা নিচে নামাও\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE, "&eরাইট ক্লিক &8» &7ডানদিকে ঘোরাও\n&eলেফট ক্লিক &8» &7বাঁদিকে ঘোরাও\n&eSHIFT + রাইট ক্লিক (ব্লক) &8» &7শরীর উপরে তোলো\n&eSHIFT + লেফট ক্লিক (ব্লক) &8» &7শরীর নিচে নামাও\n&eSHIFT + রাইট ক্লিক (বাতাস) &8» &7শরীর ডানদিকে সরাও\n&eSHIFT + লেফট ক্লিক (বাতাস) &8» &7শরীর বাঁদিকে সরাও\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE, "&7পরে ব্যবহার করার জন্য আর্মারস্ট্যান্ডের\n&7সেটিংসকে একটা টাইপ হিসেবে\n&7সেভ করতে ক্লিক করো\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK, "&cএডিট সেশনে তুমি ব্লক ভাঙতে পারবে না।");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK, "&cএখানে তুমি ব্লক ভাঙতে পারবে না।");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START, "&aযে নামটা সেট করতে চাও সেটা চ্যাটে লেখো। বেরোতে 'exit' লেখো");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY, "&cতুমি আগে থেকেই নাম সেট করার সেশনে আছো");
        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS, "&aতুমি সফলভাবে নাম সেট করার সেশন থেকে বেরিয়ে গেছো");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS, "&aআর্মারস্ট্যান্ডের কাস্টম নাম সফলভাবে '%name%' সেট করা হয়েছে");
        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS, "&aতুমি সফলভাবে টাইপ তৈরির সেশন থেকে বেরিয়ে গেছো");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS, "&cএই টাইপটা আগে থেকেই আছে। অন্য নাম বেছে নাও অথবা সেভ মেনু থেকে এই টাইপটা সিলেক্ট করো");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS, "&aএই আর্মারস্ট্যান্ডের প্রপার্টি দিয়ে '%type%' টাইপটা তৈরি হয়েছে");
        yml.addDefault(Messages.CREATION_CANCELLED, "&cনিষ্ক্রিয় থাকার কারণে তৈরি করা বাতিল হয়েছে");
        yml.addDefault(Messages.DELETION_CANCELLED, "&cনিষ্ক্রিয় থাকার কারণে মোছা বাতিল হয়েছে।");
        yml.addDefault(Messages.ARMS_OPTION_NAME, "&eহাত");
        yml.addDefault(Messages.ARMS_OPTION_LORE, "&7এই আর্মারস্ট্যান্ডের হাত\n&7চালু বা বন্ধ করো \n\n%status%");
        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME, "&eবেস প্লেট");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE, "&7এই আর্মারস্ট্যান্ডের বেস প্লেট\n&7চালু বা বন্ধ করো \n\n%status%");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME, "&eকাস্টম নাম");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE, "&7এই আর্মারস্ট্যান্ডের জন্য\n&7একটা কাস্টম নাম সেট করো");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME, "&eবর্তমান&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME, "&eবর্তমান নাম&6 » &eনেই");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME, "&aযে নামটা সেট করতে চাও সেটা চ্যাটে লেখো। বেরোতে 'exit' লেখো");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING, "&cতুমি আগে থেকেই নাম সেট করার সেশনে আছো");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME, "&eকাস্টম নাম দেখা যাবে");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE, "&7এই আর্মারস্ট্যান্ডের কাস্টম নাম\n&7দেখা যাবে কিনা সেটা চালু বা বন্ধ করো\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_NAME, "&eচমক");
        yml.addDefault(Messages.GLOWING_OPTION_LORE, "&7এই আর্মারস্ট্যান্ডের চমক\n&7চালু বা বন্ধ করো\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e চমকাচ্ছে");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c চমকাচ্ছে না");
        yml.addDefault(Messages.SMALL_OPTION_NAME, "&eছোট");
        yml.addDefault(Messages.SMALL_OPTION_LORE, "&7এই আর্মারস্ট্যান্ডের ছোট\n&7সাইজ চালু বা বন্ধ করো\n\n%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e ছোট");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c ছোট না");
        yml.addDefault(Messages.VISIBLE_OPTION_NAME, "&eভিজিবিলিটি");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE, "&7এই আর্মারস্ট্যান্ডের\n&7ভিজিবিলিটি চালু বা বন্ধ করো\n\n%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e দেখা যাচ্ছে");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c দেখা যাচ্ছে না");
        yml.addDefault(Messages.DELETE_TOOL_NAME, "&eমুছে ফেলো");
        yml.addDefault(Messages.DELETE_TOOL_LORE, "&7এই আর্মারস্ট্যান্ডটা মুছে দেয়\n&7এই কাজটা আর ফেরানো যাবে না\n\n&eমুছতে ক্লিক করো");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE, "&aআর্মারস্ট্যান্ডটা মোছা হয়েছে, তবে restore কমান্ড দিয়ে এটা ফিরিয়ে আনা যাবে, সার্ভার রিস্টার্ট হলে এটা পুরোপুরি মুছে যাবে");
        yml.addDefault(Messages.DELETE_TOOL_DELETED, "&aআর্মারস্ট্যান্ডটা মোছা হয়েছে, তবে restore কমান্ড দিয়ে এটা ফিরিয়ে আনা যাবে, সার্ভার রিস্টার্ট হলে এটা পুরোপুরি মুছে যাবে");
        yml.addDefault(Messages.MOVE_TOOL_NAME, "&eসরাও");
        yml.addDefault(Messages.MOVE_TOOL_LORE, "&7আর্মারস্ট্যান্ডটাকে তুমি\n&7যেখানে চাও সেখানে সরায়\n\n&7পরামর্শ: এর বদলে\n&7/as move কমান্ড ব্যবহার করো\n\n&eসরাতে ক্লিক করো");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING, "&cতুমি আগে থেকেই সরানোর সেশনে আছো");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE, "&aআর্মারস্ট্যান্ডকে যেখানে দাঁড় করাতে চাও সেই ব্লকটা ভাঙো");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY, "&cতুমি আগে থেকেই সরানোর সেশনে আছো");
        yml.addDefault(Messages.MOVE_TOOL_START, "&aআর্মারস্ট্যান্ডকে যেখানে দাঁড় করাতে চাও সেই ব্লকটা ভাঙো");
        yml.addDefault(Messages.TELEPORT_TOOL_NAME, "&eটেলিপোর্ট");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE, "&7আর্মারস্ট্যান্ডের\n&7লোকেশনে টেলিপোর্ট করে\n\n&eটেলিপোর্ট করতে ক্লিক করো");
        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aএই আর্মারস্ট্যান্ডের জন্য এই পজিশনটা গ্রহণ করা হয়েছে।");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aনতুন পজিশনটা সফলভাবে বাতিল করা হয়েছে।");

        yml.options().copyDefaults(true);
        save();
    }
}