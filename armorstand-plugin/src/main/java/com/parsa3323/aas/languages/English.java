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

public class English extends Language {
    public English() {
        super(AdvancedArmorStands.plugin, "en");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");

        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "actions");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7This means if you\n&7click this armor\n&7stand, command will\n&7be run: %command%\n\n&6» &eClick to edit\n&6» &eShift click to remove");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eCreate an action");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE,
                "&7Select this to create\n" +
                        "&7an action that runs\n" +
                        "&7commands on clicks\n\n" +
                        "&eClick to create");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aType the command to set (no '/'), Type 'exit' to cancel.");

        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "action Settings");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eTrigger Type");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Select how the command\n&7will be triggered when\n&7the player interacts with\n&7the ArmorStand.\n\n%trigger_list%\n\n&eClick to change");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eSender");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Select the sender of the\n&7command. This affects how\n&7permissions and execution work.\n\n%sender_list%\n\n&eClick to change");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_NAME, "&ePriority");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_LORE, "&7Change the priority of\n&7this command, when\n&7when the priority is higher\n&7then other, it will run sooner\n\n&6» &e%priority_number%\n\n&eClick to change");
        yml.addDefault(Messages.ACTIONS_SETTING_PRIORITY_ALREADY_TAKEN, "&eTwo or more actions have the same priority, which may cause them to conflict with each other.");

        yml.addDefault(Messages.AI_MENU_TITLE, "ai settings");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Ask the ai to assist you");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7Thinking...");

        yml.addDefault(Messages.AI_OPTION_NAME,
                "&eAI Options");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE,
                "&7Options related to the\n" +
                        "&7artificial intelligence\n" +
                        "&7of this ArmorStand.\n\n" +
                        "&eClick to open");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE,
                "&7Enable AI for ArmorStands\n" +
                        "&7by setting your API key\n" +
                        "&7in the config.\n\n" +
                        "&4✘ &cNot Enabled");

        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Add custom instructions\n&7for this ArmorStand's AI\nto follow when replying.\n\n%current%\n\n&6» &eShift-click to reset\n&6» &eClick to change");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7Enable or disable AI\n&7for this ArmorStand\n\n%ai_status%");

        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS,
                "&aSuccessfully exited memory set session.");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS,
                "&aSuccessfully updated ArmorStand's instructions");

        yml.addDefault(Messages.AI_PREFIX,
                "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT,
                "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND,
                "AI error: couldn't find assistant text in response");

        yml.addDefault(Messages.AI_ERROR,
                "&cAI error: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP,
                "&cAI error: HTTP %code%, make sure you are connected to internet");
        yml.addDefault(Messages.AI_HTTP_ERROR,
                "AI error: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET,
                "AI error: HTTP {code}, make sure you are connected to internet");
        yml.addDefault(Messages.AI_PARSE_ERROR,
                "AI parse error: {error}");

        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Show animation commands");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER,
                "&6&lAdvanced &e&lArmorStands &7&lanimation commands");

        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS,
                "&aSuccessfully set the animation %animation% to ArmorStand %armorstand%");

        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION,
                "Clear &7animations of an as");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS,
                "&aSuccessfully cleared %armorstand%'s animations");

        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION,
                "&7Create/edit an &7animation with an as");

        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION,
                "&7Completely remove an animation");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS,
                "&aSuccessfully removed animation '%animation%'");

        yml.addDefault(Messages.ANIMATION_INVALID,
                "&cInvalid animation");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION,
                "&cInvalid animation '%animation%'. Did you mean '%suggestion%'?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME,
                "&cInvalid animation name");

        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION,
                "&cCan't open the editor on an ArmorStand that has animation");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED,
                "&aYour GameMode has been temporarily switched to CREATIVE, because edit sessions do not support ADVENTURE mode. It will be restored automatically when you exit.");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED,
                "&aSuccessfully entered the animation edit/create session");

        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aAnimation saved successfully, click to add to this ArmorStand");

        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "keyframe editor");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eRIGHT CLICK &7Add a keyframe\n&eSHIFT RIGHT CLICK &7Open keyframe menu\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aAdded keyframe!");

        yml.addDefault(Messages.KEYFRAME_STEP_LORE,
                "&7This is the %step%%suffix% step\n" +
                        "&7for %animation% animation that\n" +
                        "&7will turn ArmorStand's position to this\n" +
                        "&7on its turn\n\n" +
                        "&eClick to remove");

        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME,
                "&eInterval: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE,
                "&7Sets the delay\n" +
                        "&7between animation frames.\n" +
                        "&7Lower value means\n" +
                        "&7faster animation speed.\n\n" +
                        "&6»&e Left-click to increase\n" +
                        "&6»&e Right-click to decrease.\n" +
                        "&6»&e Hold Shift to change\n" +
                        "&6»&e by increments of 10.\n\n" +
                        "&eClick to change");

        yml.addDefault(Messages.KEYFRAME_LOOP_NAME,
                "&eLoop: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE,
                "&7Defines whether the animation\n" +
                        "&7should loop repeat from\n" +
                        "&7the start infinitely\n\n" +
                        "&eClick to change");

        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_DISPLAY,
                "&eRealistic: %value%");

        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_LORE,
                "&7Realistic Animations smoothly interpolate\n" +
                "&7between animation steps instead of instantly\n" +
                "&7jumping from one pose to another.\n\n" +
                "&7Warning: could cause lag \n\n" +
                "&eClick to change");

        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_DISPLAY,
                "&eRealistic frames: %value%");

        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_LORE,
                "&7The number of generated\n" +
                "&7frames during the realistic animation\n\n" +
                        "&6»&e Left-click to increase\n" +
                        "&6»&e Right-click to decrease.\n" +
                        "&6»&e Hold Shift to change\n" +
                        "&6»&e by increments of 10.\n\n" +
                        "&eClick to change");

        yml.addDefault(Messages.SAVE_MENU_TITLE, "save menu");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE,
                "&aType the name of the type you want to create and copy this ArmorStand's properties to, Type 'exit' to exit");

        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE,
                "&7Selecting this will override\n" +
                        "&7your old %type% config to\n" +
                        "&7the current config you made\n\n" +
                        "&eClick to save");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED,
                "&aSaved ArmorStand's properties to '%type%'");

        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME,
                "&eCreate a type");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE,
                "&7Select this to create\n" +
                        "&7a type with this armor\n" +
                        "&7stand's properties\n\n" +
                        "&eClick to create");

        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "settings");

        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cThis ArmorStand is not loaded");

        yml.addDefault(Messages.ARMOR_STAND_LOAD_SUCCESS,
                "&aSuccessfully loaded ArmorStand '%name%'");

        yml.addDefault(Messages.ARMOR_STAND_LOAD_FAILED,
                "&cFailed to load the ArmorStand check console for more details!");

        yml.addDefault(Messages.ARMOR_STAND_LOAD_LOADED,
                "&cThis ArmorStand is already loaded");

        yml.addDefault(Messages.ARMOR_STANDS_LOAD_SUCCESS,
                "&aSuccessfully loaded %loaded% ArmorStands.");

        yml.addDefault(Messages.ARMOR_STANDS_LOAD_FAILED,
                "&c Failed to load %failed% ArmorStands.");


        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cInvalid ArmorStand");

        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cInvalid ArmorStand '%armorstand%'. Did you mean '%suggestion%'?");

        yml.addDefault(Messages.ARMORSTAND_NOT_FOUND,
                "&cArmorStand not found!");
        yml.addDefault(Messages.WORLD_NOT_FOUND,
                "&cWorld not found!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMORSTAND,
                "&aTeleported to %name%");

        yml.addDefault(Messages.ARMORSTAND_UPDATED_SUCCESS,
                "&aArmorStand updated successfully!");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_FAILED,
                "&cFailed to update ArmorStand!");

        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE,
                "&cThe ArmorStand is no longer available!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE_TITLE,
                "&cArmorStand is not available");

        yml.addDefault(Messages.ARMORSTAND_MOVE_SUCCESS,
                "&aSuccessfully moved the ArmorStand.");

        yml.addDefault(Messages.MESSAGES_NOT_LOADED,
                "&cIt looks like %amount% ArmorStands haven't been loaded by the world generator. To fix this, enable 'auto-load-armor-stands' in the config to automatically load all ArmorStands.");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN,
                "&eAdvancedArmorStands has encountered %amount% error(s) and warning(s) this session. Check the log file for details.");

        yml.addDefault(Messages.ARMORSTAND_SAVE_CONFIRM,
                "&aDo this %amount% more time%plural% to save this ArmorStand.");
        yml.addDefault(Messages.ARMORSTAND_SAVED_SUCCESS,
                "&eArmorStand saved as %name%!");

        yml.addDefault(Messages.ARMORSTAND_DELETE_CONFIRM,
                "&cDo this %amount% more time%plural% to delete this ArmorStand.");
        yml.addDefault(Messages.ARMORSTAND_DELETED_SUCCESS,
                "&aArmorStand has been deleted, but it can be restored using the restored command and it will be fully deleted with server restart");

        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cYou don't have permission to use this command!");

        yml.addDefault(Messages.COMMAND_UNKNOWN, "&cCommand '%command%' is not a valid subcommand.");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&cCommand '%command%' is not a valid subcommand. Did you mean '%suggestion%'?");

        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cPlease enter a valid page number.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cInvalid Page, Please choose a page between 1 and %pages%.");

        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eClick to suggest");

        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Examples:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE,
                "&e&lClick to use this command");
        yml.addDefault(Messages.COMMAND_LABEL,
                "&7Command: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL,
                "&7Description: &f%description%");

        yml.addDefault(Messages.CREATE_DESCRIPTION,
                "Create an &7ArmorStand");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE,
                "&cUsage: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER,
                "&cInvalid number for part %part%. Usage: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART,
                "&cUnknown part: %part%");

        yml.addDefault(Messages.DELETE_DESCRIPTION,
                "Delete an &7ArmorStand");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS,
                "&aSuccessfully deleted all ArmorStands");
        yml.addDefault(Messages.DELETE_SUCCESS,
                "&aFully deleted ArmorStand");

        yml.addDefault(Messages.UNLINK_SUCCESS,
                "&aSuccessfully unlinked ArmorStand from AdvancedArmorStands");

        yml.addDefault(Messages.UNLINK_ALL_SUCCESS,
                "&aSuccessfully unlinks all ArmorStands from AdvancedArmorStands");

        yml.addDefault(Messages.DEBUG_DESCRIPTION,
                "Shows debug information");

        yml.addDefault(Messages.HEAD_DESCRIPTION,
                "Get a player's head");
        yml.addDefault(Messages.HEAD_SUCCESS,
                "&aSuccessfully gave you %player%'s head");

        yml.addDefault(Messages.LIST_DESCRIPTION,
                "Shows a list of ArmorStands");
        yml.addDefault(Messages.LIST_EMPTY_TITLE,
                "&c&l     No Saved ArmorStands Found ");
        yml.addDefault(Messages.LIST_EMPTY_HINT,
                "&7Use &e/as create <type> <name>&7 to create your first ArmorStand!");

        yml.addDefault(Messages.LOAD_DESCRIPTION,
                "Loads an ArmorStand");
        yml.addDefault(Messages.LOAD_NO_UNLOADED,
                "&eNo unloaded ArmorStands found.");
        yml.addDefault(Messages.LOAD_FAILED,
                "&cFailed: %failed%");

        yml.addDefault(Messages.MOVE_DESCRIPTION,
                "Moves an ArmorStand to your &7location");

        yml.addDefault(Messages.OPTIONS_DESCRIPTION,
                "Opens the options menu");

        yml.addDefault(Messages.RELOAD_DESCRIPTION,
                "Reloads plugin's configs");
        yml.addDefault(Messages.RELOAD_SUCCESS,
                "&aSuccessfully reloaded AdvancedArmorStand's config");
        yml.addDefault(Messages.RELOAD_ERROR,
                "&cError while reloading plugin check the console for more details");

        yml.addDefault(Messages.RENAME_DESCRIPTION,
                "Rename an &7as's name");
        yml.addDefault(Messages.RENAME_SUCCESS,
                "&aRenamed ArmorStand from '%old_name%' to '%new_name%'");

        yml.addDefault(Messages.RESTORE_DESCRIPTION,
                "Restore a deleted ArmorStand");
        yml.addDefault(Messages.RESTORE_NOT_DELETED,
                "&cThis ArmorStand is not deleted or its too late");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR,
                "&cUnknown error, check the console for more info");
        yml.addDefault(Messages.RESTORE_SUCCESS,
                "&aSuccessfully restored the ArmorStand");

        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND,
                "&cThis ArmorStand is not on the ground. Are you sure you want to teleport to it?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT,
                "&cUse '/as teleport %armorstand% --force' to force teleport");

        yml.addDefault(Messages.TELL_DESCRIPTION,
                "Ask an ArmorStand AI a question");
        yml.addDefault(Messages.TELL_AI_DISABLED,
                "&cThis ArmorStand doesn't have AI enabled");

        yml.addDefault(Messages.INVENTORY_EXIT_NAME,
                "&7» &cEXIT&7 (Right Click)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE,
                "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION,
                "&aEntered edit session, click the EXIT item to get back and restore your items");

        yml.addDefault(Messages.EDITOR_EXIT_NAME,
                "&7» &cEXIT &7(Right Click)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE,
                "\n&8AdvancedArmorStands Editor Item");

        yml.addDefault(Messages.HEAD_ITEM_LORE,
                "&eRIGHT CLICK &8» &7Rotate head to right\n" +
                        "&eLEFT CLICK &8» &7Rotate head hand to left\n" +
                        "&eSHIFT + RIGHT CLICK &8» &7Move head up\n" +
                        "&eSHIFT + LEFT CLICK &8» &7Move head down\n" +
                        "\n" +
                        "&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE,
                "&eRIGHT CLICK &8» &7Rotate left hand to right\n" +
                        "&eLEFT CLICK &8» &7Rotate left hand to left\n" +
                        "&eSHIFT + RIGHT CLICK &8» &7Move left arm up\n" +
                        "&eSHIFT + LEFT CLICK &8» &7Move left arm down\n" +
                        "\n" +
                        "&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE,
                "&eRIGHT CLICK &8» &7Rotate right hand to right\n" +
                        "&eLEFT CLICK &8» &7Rotate right hand to left\n" +
                        "&eSHIFT + RIGHT CLICK &8» &7Move right arm up\n" +
                        "&eSHIFT + LEFT CLICK &8» &7Move left arm down\n" +
                        "\n" +
                        "&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE,
                "&eRIGHT CLICK &8» &7Rotate left leg to right\n" +
                        "&eLEFT CLICK &8» &7Rotate left leg to left\n" +
                        "&eSHIFT + RIGHT CLICK &8» &7Move left leg up\n" +
                        "&eSHIFT + LEFT CLICK &8» &7Move left leg down\n" +
                        "\n" +
                        "&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE,
                "&eRIGHT CLICK &8» &7Rotate right leg to right\n" +
                        "&eLEFT CLICK &8» &7Rotate right leg to left\n" +
                        "&eSHIFT + RIGHT CLICK &8» &7Move right leg up\n" +
                        "&eSHIFT + LEFT CLICK &8» &7Move right leg down\n" +
                        "\n" +
                        "&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE,
                "&eRIGHT CLICK &8» &7Rotate to right\n" +
                        "&eLEFT CLICK &8» &7Rotate to left\n" +
                        "&eSHIFT + RIGHT CLICK (Block) &8» &7Rove body up\n" +
                        "&eSHIFT + LEFT CLICK (Block) &8» &7Move body down\n" +
                        "&eSHIFT + RIGHT CLICK (Air) &8» &7Move body right\n" +
                        "&eSHIFT + LEFT CLICK (Air) &8» &7Move body left\n" +
                        "\n" +
                        "&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE,
                "&7Click to save the ArmorStand setting\n" +
                        "&7as a type, that you can use it later\n" +
                        "\n" +
                        "&8&oAdvancedArmorStands Editor Item");

        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK,
                "&cYou cannot break blocks in edit session.");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK,
                "&cYou cannot break blocks here.");

        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START,
                "&aType the name you want to set in the chat, To exit type 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY,
                "&cYou are already in a name set session");

        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS,
                "&aSuccessfully quit the name set session");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS,
                "&aSuccessfully set ArmorStand's custom name to '%name%'");

        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS,
                "&aSuccessfully quit the type create session");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS,
                "&cThis type already exists, Either chose another name or select the type in the save menu");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS,
                "&aCreated type '%type%' with this ArmorStand's properties");

        yml.addDefault(Messages.CREATION_CANCELLED,
                "&cCreation cancelled due to inactivity");
        yml.addDefault(Messages.DELETION_CANCELLED,
                "&cDeletion cancelled due to inactivity.");

        yml.addDefault(Messages.ARMS_OPTION_NAME,
                "&eArms");
        yml.addDefault(Messages.ARMS_OPTION_LORE,
                "&7Enable and disable\n" +
                        "&7arms for this ArmorStand \n\n" +
                        "%status%");

        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME,
                "&eBase Plate");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE,
                "&7Enable and disable\n" +
                        "&7base plate for this ArmorStand \n\n" +
                        "%status%");

        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME,
                "&eCustom name");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE,
                "&7Set a custom name for\n" +
                        "&7this ArmorStand");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME,
                "&eCurrent&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME,
                "&eCurrent name&6 » &eNone");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME,
                "&aType the name you want to set in the chat, To exit type 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING,
                "&cYou are already in a name set session");

        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME,
                "&eCustom name visible");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE,
                "&7Enable and disable\n" +
                        "&7custom name for this ArmorStand\n\n" +
                        "%status%");

        yml.addDefault(Messages.GLOWING_OPTION_NAME,
                "&eGlowing");
        yml.addDefault(Messages.GLOWING_OPTION_LORE,
                "&7Enable and disable\n" +
                        "&7glowing this ArmorStand\n\n" +
                        "%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED,
                "&6" + TextUtils.CHECK + "&e Is glowing");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED,
                "&4" + TextUtils.CROSS + "&c Isn't glowing");

        yml.addDefault(Messages.SMALL_OPTION_NAME,
                "&eSmall");
        yml.addDefault(Messages.SMALL_OPTION_LORE,
                "&7Enable and disable\n" +
                        "&7small size for this ArmorStand\n\n" +
                        "%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED,
                "&6" + TextUtils.CHECK + "&e Is small");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED,
                "&4" + TextUtils.CROSS + "&c Isn't small");

        yml.addDefault(Messages.VISIBLE_OPTION_NAME,
                "&eVisibility");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE,
                "&7Enable and disable\n" +
                        "&7visibility for this ArmorStand\n\n" +
                        "%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED,
                "&6" + TextUtils.CHECK + "&e Is visible");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED,
                "&4" + TextUtils.CROSS + "&c Isn't visible");

        yml.addDefault(Messages.DELETE_TOOL_NAME,
                "&eDelete");
        yml.addDefault(Messages.DELETE_TOOL_LORE,
                "&7Delete this ArmorStand\n" +
                        "&7this action cannot be undone\n\n" +
                        "&eClick to delete");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE,
                "&aArmorStand has been deleted, but it can be restored using the restored command and it will be fully deleted with server restart");
        yml.addDefault(Messages.DELETE_TOOL_DELETED,
                "&aArmorStand has been deleted, but it can be restored using the restored command and it will be fully deleted with server restart");

        yml.addDefault(Messages.MOVE_TOOL_NAME,
                "&eMove");
        yml.addDefault(Messages.MOVE_TOOL_LORE,
                "&7Move the ArmorStand\n" +
                        "&7to where you want it\n\n" +
                        "&7Recommended: use /as move\n" +
                        "&7command instead\n\n" +
                        "&eClick to move");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING,
                "&cYou are already in the move session");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE,
                "&aBreak the block you want the ArmorStand to be on");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY,
                "&cYou are already in the move session");
        yml.addDefault(Messages.MOVE_TOOL_START,
                "&aBreak the block you want the ArmorStand to be on");

        yml.addDefault(Messages.TELEPORT_TOOL_NAME,
                "&eTeleport");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE,
                "&7Teleport to the\n" +
                        "&7ArmorStand's location\n\n" +
                        "&eClick to teleport");

        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aAccepted this position for this ArmorStand.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aSuccessfully denied the new position.");

        yml.options().copyDefaults(true);
        save();
    }
}