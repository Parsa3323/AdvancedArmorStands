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
import org.bukkit.configuration.file.YamlConfiguration;

public class English extends Language {
    public English() {
        super(AdvancedArmorStands.plugin, "en");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "actions");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7This means if you\n&7click this armor\n&7stand, command will\n&7be run: %command%\n\n&6» &eClick to edit\n&6» &eShift click to remove");

        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aType the command to set (no '/'), Type 'exit' to cancel.");

        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eTrigger Type");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Select how the command\n&7will be triggered when\n&7the player interacts with\n&7the ArmorStand.\n\n%trigger_list%\n\n&eClick to change");

        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eSender");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Select the sender of the\n&7command. This affects how\n&7permissions and execution work.\n\n%sender_list%\n\n&eClick to change");


        yml.addDefault(Messages.AI_MENU_TITLE, "ai settings");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Add custom instructions\n&7for this ArmorStand's AI\nto follow when replying.\n\n%current%\n\n&6» &eShift-click to reset\n&6» &eClick to change");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7Enable or disable AI\n&7for this ArmorStand\n\n%ai_status%");

        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aAnimation saved successfully, click to add to this ArmorStand");

        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cPlease enter a valid page number.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cInvalid Page, Please choose a page between 1 and %pages%.");

        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aAccepted this position for this ArmorStand.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aSuccessfully denied the new position.");

        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cYou don't have permission to use this command!");

        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&cCommand '%command%' is not a valid subcommand. Did you mean '%suggestion%'?");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&cCommand '%command%' is not a valid subcommand.");

        yml.addDefault(Messages.SAVE_MENU_TITLE, "save menu");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "keyframe editor");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eRIGHT CLICK &7Add a keyframe\n&eSHIFT RIGHT CLICK &7Open keyframe menu\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aAdded keyframe!");

        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "settings");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "action Settings");

        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eClick to suggest");

        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cThis ArmorStand is not loaded");

        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cInvalid ArmorStand '%armorstand%'. Did you mean '%suggestion%'?");

        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION,
                "Clear &7animations of an as");

        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS,
                "&aSuccessfully cleared %armorstand%'s animations");

        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION,
                "&7Create/edit an &7animation with an as");

        yml.addDefault(Messages.ANIMATION_INVALID_NAME,
                "&cInvalid animation name");

        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION,
                "&cCan't open the editor on an ArmorStand that has animation");

        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED,
                "&aYour GameMode has been temporarily switched to CREATIVE, because edit sessions do not support ADVENTURE mode. It will be restored automatically when you exit.");

        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED,
                "&aSuccessfully entered the animation edit/create session");

        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION,
                "&7Completely remove an animation");

        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS,
                "&aSuccessfully removed animation '%animation%'");

        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cInvalid ArmorStand");

        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Ask the ai to assist you");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7Thinking...");

        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION,
                "&cInvalid animation '%animation%'. Did you mean '%suggestion%'?");

        yml.addDefault(Messages.ANIMATION_INVALID,
                "&cInvalid animation");

        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS,
                "&aSuccessfully set the animation %animation% to ArmorStand %armorstand%");

        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Show animation commands");

        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER,
                "&6&lAdvanced &e&lArmorStands &7&lanimation commands");

        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Examples:");

        yml.addDefault(Messages.COMMAND_CLICK_TO_USE,
                "&e&lClick to use this command");

        yml.addDefault(Messages.COMMAND_LABEL,
                "&7Command: &e%command%");

        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL,
                "&7Description: &f%description%");

        // 1
        yml.addDefault(Messages.CREATE_DESCRIPTION,
                "Create an &7ArmorStand");

        yml.addDefault(Messages.CREATE_CUSTOM_USAGE,
                "&cUsage: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");

        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER,
                "&cInvalid number for part %part%. Usage: <part> <x> <y> <z>");

        yml.addDefault(Messages.CREATE_UNKNOWN_PART,
                "&cUnknown part: %part%");


        yml.addDefault(Messages.DEBUG_DESCRIPTION,
                "Shows debug information");


        yml.addDefault(Messages.DELETE_DESCRIPTION,
                "Delete an &7ArmorStand");

        yml.addDefault(Messages.DELETE_ALL_SUCCESS,
                "&aSuccessfully deleted all ArmorStands");

        yml.addDefault(Messages.DELETE_SUCCESS,
                "&aFully deleted ArmorStand");


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

        yml.addDefault(Messages.MOVE_WORLD_NOT_FOUND,
                "&cWorld not found!");


        yml.addDefault(Messages.OPTIONS_DESCRIPTION,
                "Opens the options menu");


        yml.addDefault(Messages.RELOAD_DESCRIPTION,
                "Reloads plugin's configs");

        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND,
                "&cThis ArmorStand is not on the ground. Are you sure you want to teleport to it?");

        yml.addDefault(Messages.RELOAD_SUCCESS,
                "&aSuccessfully reloaded AdvancedArmorStand's config");

        yml.addDefault(Messages.RELOAD_ERROR,
                "&cError while reloading plugin check the console for more details");

        yml.addDefault(Messages.TELEPORT_FORCE_HINT,
                "&cUse '/as teleport %armorstand% --force' to force teleport");

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


        yml.addDefault(Messages.TELL_DESCRIPTION,
                "Ask an ArmorStand AI a question");

        yml.addDefault(Messages.TELL_AI_DISABLED,
                "&cThis ArmorStand doesn't have AI enabled");

        //-

        yml.options().copyDefaults(true);
        save();
    }
}

