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

public class Persian extends Language {
    /*
    Made with AI
    */
    public Persian() {
        super(AdvancedArmorStands.plugin, "fa");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "actionha");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7yani age ru in armor stand\n&7click koni, in command\n&7ejra mishe: %command%\n\n&6» &eBezan click baraye edit\n&6» &eShift click baraye hazf");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eYek action besaz");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE, "&7in ro bezan ta yek\n&7action besazi ke ba\n&7click ha command run mikone\n\n&eBezan baraye sakht");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aCommandi ke mikhay bezari ro benevis (bedune '/'), baraye cancel 'exit' benevis.");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "tanzimate action");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eNoe Trigger");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Entekhab kon command chetori\n&7ejra beshe vaghti\n&7player ba ArmorStand\n&7interact mikone.\n\n%trigger_list%\n\n&eBezan baraye avaz kardan");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eFerestande");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Ferestande command ro\n&7entekhab kon. In ru tarze\n&7karkarde permission ha tasir mizare.\n\n%sender_list%\n\n&eBezan baraye avaz kardan");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_NAME, "&eOloviyat");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_LORE, "&7Oloviyate in dastor ra\n&7taghir dahid. Vaghti\n&7oloviate an bishtar az\n&7baghie bashad, zoodtar\n&7ejra mishavad.\n\n&6» &e%priority_number%\n\n&eBaraye taghir کلیک konid");
        yml.addDefault(Messages.ACTIONS_SETTING_PRIORITY_ALREADY_TAKEN, "&eDo ya bishtar action oloviate yeksan darand, ke momk‌en ast baes-e takhalof ya tadakhol bein anha shavad.");
        yml.addDefault(Messages.AI_MENU_TITLE, "tanzimate ai");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Az ai bekhah komaket kone");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7dare fekr mikone...");
        yml.addDefault(Messages.AI_OPTION_NAME, "&eTanzimate AI");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE, "&7Tanzimate marbut be\n&7hoshe masnooi\n&7in ArmorStand.\n\n&eBezan baraye baz kardan");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE, "&7AI ro baraye ArmorStand ha\n&7ba gozashtane API key\n&7too config faal kon.\n\n&4✘ &cFaal Nist");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Dastoolamale khodeto\n&7baraye AI in ArmorStand\nezafe kon ta vaghte javab dadan donbal kone.\n\n%current%\n\n&6» &eShift-click baraye reset\n&6» &eClick baraye avaz kardan");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7AI ro baraye in ArmorStand\n&7faal ya gheyre faal kon\n\n%ai_status%");
        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS, "&aBa movafaghiat az session tanzime memory kharej shodi.");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS, "&aDastoolamalhaye ArmorStand ba movafaghiat update shod");
        yml.addDefault(Messages.AI_PREFIX, "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT, "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND, "Khataye AI: matne assistant too response peida nashod");
        yml.addDefault(Messages.AI_ERROR, "&cKhataye AI: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP, "&cKhataye AI: HTTP %code%, motmaen sho be internet vasli");
        yml.addDefault(Messages.AI_HTTP_ERROR, "Khataye AI: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET, "Khataye AI: HTTP {code}, motmaen sho be internet vasli");
        yml.addDefault(Messages.AI_PARSE_ERROR, "Khataye parse AI: {error}");
        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Commandhaye animation ro neshun bede");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER, "&6&lAdvanced &e&lArmorStands &7&lcommandhaye animation");
        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS, "&aAnimation %animation% ro ru ArmorStand %armorstand% ba movafaghiat gozashti");
        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION, "Animation haye ye as ro &7pak kon");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS, "&aAnimation haye %armorstand% ba movafaghiat pak shod");
        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION, "&7Ba ye as, animation &7besaz ya edit kon");
        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION, "&7Ye animation ro kamelan pak kon");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS, "&aAnimation '%animation%' ba movafaghiat pak shod");
        yml.addDefault(Messages.ANIMATION_INVALID, "&cAnimation nadorost");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION, "&cAnimation '%animation%' nadorost. Manzuret '%suggestion%' bud?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME, "&cEsme animation nadorost");
        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION, "&cNemitoni editor ro ru ArmorStandi ke animation dare baz koni");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED, "&aGameModet movaghatan be CREATIVE avaz shod, chon session haye edit az ADVENTURE poshtibani nemikonan. Vaghti biroon biai khodkar bar migarde.");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED, "&aBa movafaghiat vared session sakht/edite animation shodi");
        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aAnimation ba movafaghiat save shod, bezan ta be in ArmorStand ezafash koni");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "editore keyframe");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eRIGHT CLICK &7Ye keyframe ezafe kon\n&eSHIFT RIGHT CLICK &7Menuye keyframe ro baz kon\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aKeyframe ezafe shod!");
        yml.addDefault(Messages.KEYFRAME_STEP_LORE, "&7In step %step%%suffix% baraye\n&7animation %animation% e ke\n&7position ArmorStand ro too\n&7navbatesh be in bar migardune\n\n&eBezan baraye hazf");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME, "&eFasele: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE, "&7Fasele beyne\n&7frame haye animation ro tanzim mikone.\n&7Har che adad kamtar\n&7animation sari-tar mishe.\n\n&6»&e Left-click baraye ziyad kardan\n&6»&e Right-click baraye kam kardan.\n&6»&e Shift ro negah dar ta\n&6»&e 10 ta 10 avaz beshe.\n\n&eBezan baraye avaz kardan");
        yml.addDefault(Messages.KEYFRAME_LOOP_NAME, "&eTekrar: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE, "&7Moshakhas mikone ke animation\n&7bayad az aval bi nahayat\n&7tekrar beshe ya na\n\n&eBezan baraye avaz kardan");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_DISPLAY, "&eRealistic: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_LORE, "&7Animation haye realistic be sorate narm be\n&7bein marhale haye animation interpolation mikonan، be jaye inke\n&7bedoon-e mokhalefat az yek pose be pose digar jump konan.\n\n&7Hoshdar: momkene baes-e lag beshe\n\n&eBaraye taghir کلیک konid");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_DISPLAY, "&eFrame haye realistic: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_LORE, "&7Tedade frame haye تولid shode\n&7dar hengame animation realistic\n\n&6»&e Click-e chap baraye afzayesh\n&6»&e Click-e rast baraye kahesh.\n&6»&e Shift ra negah darid ta taghirat\n&6»&e be meghdar haye 10 ta 10 anjam shavad.\n\n&eBaraye taghir کلیک konid");
        yml.addDefault(Messages.SAVE_MENU_TITLE, "menuye save");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE, "&aEsme typei ke mikhay besazi va propertie in ArmorStand ro behesh copy koni benevis, baraye kharoj 'exit' benevis");
        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE, "&7In ro entekhab koni config\n&7ghadimie %type% ba\n&7config alanit override mishe\n\n&eBezan baraye save");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED, "&aPropertie haye ArmorStand ru '%type%' save shod");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME, "&eYe type besaz");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE, "&7In ro bezan ta ye type\n&7ba propertie in armor\n&7stand besazi\n\n&eBezan baraye sakht");
        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "tanzimat");
        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cIn ArmorStand load nashode");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_SUCCESS, "&aArmor Stand '%name%' ba movafaghiat load shod");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_FAILED, "&cLoad kardane Armor Stand namovafagh bood. Baraye jozeyate bishtar console ra check konid!");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_LOADED, "&cIn Armor Stand ghablan load shode ast");
        yml.addDefault(Messages.ARMOR_STANDS_LOAD_SUCCESS, "&a%loaded% ta Armor Stand ba movafaghiat load shodand.");
        yml.addDefault(Messages.ARMOR_STANDS_LOAD_FAILED, "&c%failed% ta Armor Stand load nashodand.");
        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cArmorStand nadorost");
        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cArmorStand '%armorstand%' nadorost. Manzuret '%suggestion%' bud?");
        yml.addDefault(Messages.ARMORSTAND_NOT_FOUND, "&cArmorStand peida nashod!");
        yml.addDefault(Messages.WORLD_NOT_FOUND, "&cWorld peida nashod!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMORSTAND, "&aBe %name% teleport shodi");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_SUCCESS, "&aArmorStand ba movafaghiat update shod!");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_FAILED, "&cUpdate ArmorStand shekast khord!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE, "&cIn ArmorStand digge mojud nist!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE_TITLE, "&cArmorStand mojud nist");
        yml.addDefault(Messages.ARMORSTAND_MOVE_SUCCESS, "&aArmorStand ba movafaghiat jabeja shod.");
        yml.addDefault(Messages.MESSAGES_NOT_LOADED, "&cBe nazar mirese %amount% ta ArmorStand tavassote world generator load nashodan. Baraye dorost kardanesh, 'auto-load-armor-stands' ro too config faal kon ta hamashun khodkar load beshan.");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN, "&eAdvancedArmorStands too in session be %amount% ta error ya warning barkhord karde. Baraye jozeiyat log file ro check kon.");
        yml.addDefault(Messages.ARMORSTAND_SAVE_CONFIRM, "&aIn karo %amount% bar%plural% dige anjam bede ta in ArmorStand save beshe.");
        yml.addDefault(Messages.ARMORSTAND_SAVED_SUCCESS, "&eArmorStand ba esme %name% save shod!");
        yml.addDefault(Messages.ARMORSTAND_DELETE_CONFIRM, "&cIn karo %amount% bar%plural% dige anjam bede ta in ArmorStand hazf beshe.");
        yml.addDefault(Messages.ARMORSTAND_DELETED_SUCCESS, "&aArmorStand hazf shod, vali mitoni ba command restore bargardunish, va ba restart shodane server kamelan pak mishe");
        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cToo permission nadari in commando estefade koni!");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&cCommand '%command%' ye subcommande dorost nist.");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&cCommand '%command%' ye subcommande dorost nist. Manzuret '%suggestion%' bud?");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cLotfan ye shomare safhe dorost vared kon.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cSafhe nadorost, lotfan ye safhe beyne 1 ta %pages% entekhab kon.");
        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eBezan baraye pishnahad");
        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Mesalha:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE, "&e&lBezan ta in command ejra beshe");
        yml.addDefault(Messages.COMMAND_LABEL, "&7Dastoor: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL, "&7Tozihat: &f%description%");
        yml.addDefault(Messages.CREATE_DESCRIPTION, "Ye &7ArmorStand besaz");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE, "&cTarze estefade: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER, "&cAdad baraye part %part% nadoroste. Tarze estefade: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART, "&cParte nashenakhte: %part%");
        yml.addDefault(Messages.DELETE_DESCRIPTION, "Ye &7ArmorStand hazf kon");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS, "&aHame ArmorStand ha ba movafaghiat hazf shodan");
        yml.addDefault(Messages.DELETE_SUCCESS, "&aArmorStand kamelan hazf shod");
        yml.addDefault(Messages.UNLINK_SUCCESS, "&aArmor Stand ba movafaghiat az AdvancedArmorStands unlink shod");
        yml.addDefault(Messages.UNLINK_ALL_SUCCESS, "&aHame Armor Stand ha ba movafaghiat az AdvancedArmorStands unlink shodand");
        yml.addDefault(Messages.DEBUG_DESCRIPTION, "Etelaate debug ro neshun mide");
        yml.addDefault(Messages.HEAD_DESCRIPTION, "Kalleye ye player ro begir");
        yml.addDefault(Messages.HEAD_SUCCESS, "&aKalleye %player% behet dade shod");
        yml.addDefault(Messages.LIST_DESCRIPTION, "Listeye ArmorStand ha ro neshun mide");
        yml.addDefault(Messages.LIST_EMPTY_TITLE, "&c&l     Hich ArmorStande Save shodei Peida Nashod ");
        yml.addDefault(Messages.LIST_EMPTY_HINT, "&7Az &e/as create <type> <name>&7 estefade kon ta avvalin ArmorStandeto besazi!");
        yml.addDefault(Messages.LOAD_DESCRIPTION, "Ye ArmorStand ro load mikone");
        yml.addDefault(Messages.LOAD_NO_UNLOADED, "&eHich ArmorStande load-nashodei peida nashod.");
        yml.addDefault(Messages.LOAD_FAILED, "&cShekast khord: %failed%");
        yml.addDefault(Messages.MOVE_DESCRIPTION, "Ye ArmorStand ro be &7mokanet montaghel mikone");
        yml.addDefault(Messages.OPTIONS_DESCRIPTION, "Menuye tanzimat ro baz mikone");
        yml.addDefault(Messages.RELOAD_DESCRIPTION, "Confighaye plugino reload mikone");
        yml.addDefault(Messages.RELOAD_SUCCESS, "&aConfige AdvancedArmorStand ba movafaghiat reload shod");
        yml.addDefault(Messages.RELOAD_ERROR, "&cToo reload plugin error oftad, baraye jozeiyat console ro check kon");
        yml.addDefault(Messages.RENAME_DESCRIPTION, "Esme ye &7as ro avaz kon");
        yml.addDefault(Messages.RENAME_SUCCESS, "&aEsme ArmorStand az '%old_name%' be '%new_name%' avaz shod");
        yml.addDefault(Messages.RESTORE_DESCRIPTION, "Ye ArmorStande hazf shode ro bargardun");
        yml.addDefault(Messages.RESTORE_NOT_DELETED, "&cIn ArmorStand hazf nashode ya dige dire");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR, "&cError nashenakhte, baraye etelaate bishtar console ro check kon");
        yml.addDefault(Messages.RESTORE_SUCCESS, "&aArmorStand ba movafaghiat bargardande shod");
        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND, "&cIn ArmorStand ru zamin nist. Motmaeni mikhay behesh teleport koni?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT, "&cAz '/as teleport %armorstand% --force' estefade kon ta be zoor teleport koni");
        yml.addDefault(Messages.TELL_DESCRIPTION, "Az AI ye ArmorStand ye soal bepors");
        yml.addDefault(Messages.TELL_AI_DISABLED, "&cIn ArmorStand AI faal nadare");
        yml.addDefault(Messages.INVENTORY_EXIT_NAME, "&7» &cKHOROJ&7 (Right Click)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE, "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION, "&aVarede session edit shodi, ru item EXIT click kon ta bargardi va itemhat bargarde");
        yml.addDefault(Messages.EDITOR_EXIT_NAME, "&7» &cKHOROJ &7(Right Click)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE, "\n&8AdvancedArmorStands Editor Item");
        yml.addDefault(Messages.HEAD_ITEM_LORE, "&eRIGHT CLICK &8» &7Kalle ro bechargun be rast\n&eLEFT CLICK &8» &7Kalle ro bechargun be chap\n&eSHIFT + RIGHT CLICK &8» &7Kalle ro bebar bala\n&eSHIFT + LEFT CLICK &8» &7Kalle ro bebar paeen\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE, "&eRIGHT CLICK &8» &7Daste chap ro bechargun be rast\n&eLEFT CLICK &8» &7Daste chap ro bechargun be chap\n&eSHIFT + RIGHT CLICK &8» &7Baazu chap ro bebar bala\n&eSHIFT + LEFT CLICK &8» &7Baazu chap ro bebar paeen\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE, "&eRIGHT CLICK &8» &7Daste rast ro bechargun be rast\n&eLEFT CLICK &8» &7Daste rast ro bechargun be chap\n&eSHIFT + RIGHT CLICK &8» &7Baazu rast ro bebar bala\n&eSHIFT + LEFT CLICK &8» &7Baazu chap ro bebar paeen\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE, "&eRIGHT CLICK &8» &7Paye chap ro bechargun be rast\n&eLEFT CLICK &8» &7Paye chap ro bechargun be chap\n&eSHIFT + RIGHT CLICK &8» &7Paye chap ro bebar bala\n&eSHIFT + LEFT CLICK &8» &7Paye chap ro bebar paeen\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE, "&eRIGHT CLICK &8» &7Paye rast ro bechargun be rast\n&eLEFT CLICK &8» &7Paye rast ro bechargun be chap\n&eSHIFT + RIGHT CLICK &8» &7Paye rast ro bebar bala\n&eSHIFT + LEFT CLICK &8» &7Paye rast ro bebar paeen\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE, "&eRIGHT CLICK &8» &7Bechargun be rast\n&eLEFT CLICK &8» &7Bechargun be chap\n&eSHIFT + RIGHT CLICK (Block) &8» &7Badan ro bebar bala\n&eSHIFT + LEFT CLICK (Block) &8» &7Badan ro bebar paeen\n&eSHIFT + RIGHT CLICK (Air) &8» &7Badan ro bebar rast\n&eSHIFT + LEFT CLICK (Air) &8» &7Badan ro bebar chap\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE, "&7Bezan ta tanzimate ArmorStand\n&7ro be onvane ye type save koni, ta badan estefadash koni\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK, "&cToo session edit nemitoni block ha ro bekoni.");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK, "&cInja nemitoni block ha ro bekoni.");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START, "&aEsmi ke mikhay bezari too chat benevis, baraye khoroj 'exit' benevis");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY, "&cToo alan too ye session tanzime esm hasti");
        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS, "&aBa movafaghiat az session tanzime esm khareej shodi");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS, "&aEsme ArmorStand ba movafaghiat ru '%name%' tanzim shod");
        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS, "&aBa movafaghiat az session sakhte type khareej shodi");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS, "&cIn type az ghabl vojud dare, ya ye esme dige entekhab kon ya too menuye save khode typero entekhab kon");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS, "&aType '%type%' ba propertie haye in ArmorStand sakhte shod");
        yml.addDefault(Messages.CREATION_CANCELLED, "&cSakht be dalile bi harekati cancel shod");
        yml.addDefault(Messages.DELETION_CANCELLED, "&cHazf be dalile bi harekati cancel shod.");
        yml.addDefault(Messages.ARMS_OPTION_NAME, "&eDastha");
        yml.addDefault(Messages.ARMS_OPTION_LORE, "&7Dastaye in ArmorStand ro\n&7faal ya gheyre faal kon \n\n%status%");
        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME, "&eSafhe Paeen");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE, "&7Safhe paeene in ArmorStand ro\n&7faal ya gheyre faal kon \n\n%status%");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME, "&eEsme delkhah");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE, "&7Baraye in ArmorStand\n&7ye esme delkhah bezar");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME, "&eFeli&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME, "&eEsme feli&6 » &eHich");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME, "&aEsmi ke mikhay bezari too chat benevis, baraye khoroj 'exit' benevis");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING, "&cToo alan too ye session tanzime esm hasti");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME, "&eNemayeshe esme delkhah");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE, "&7Nemayeshe esme delkhahe in ArmorStand ro\n&7faal ya gheyre faal kon\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_NAME, "&eDorakhshesh");
        yml.addDefault(Messages.GLOWING_OPTION_LORE, "&7Dorakhshane in ArmorStand ro\n&7faal ya gheyre faal kon\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Dorakhshan e");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Dorakhshan nist");
        yml.addDefault(Messages.SMALL_OPTION_NAME, "&eKoochik");
        yml.addDefault(Messages.SMALL_OPTION_LORE, "&7Andazeye koochike in ArmorStand ro\n&7faal ya gheyre faal kon\n\n%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Koochike");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Koochik Nist");
        yml.addDefault(Messages.VISIBLE_OPTION_NAME, "&eNemayan Budan");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE, "&7Nemayan budane in ArmorStand ro\n&7faal ya gheyre faal kon\n\n%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Nemayane");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Nemayan Nist");
        yml.addDefault(Messages.DELETE_TOOL_NAME, "&eHazf");
        yml.addDefault(Messages.DELETE_TOOL_LORE, "&7In ArmorStand ro hazf kon\n&7in kar ghabele bargasht nist\n\n&eBezan baraye hazf");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE, "&aArmorStand hazf shod, vali mitoni ba command restore bargardunish, va ba restart shodane server kamelan pak mishe");
        yml.addDefault(Messages.DELETE_TOOL_DELETED, "&aArmorStand hazf shod, vali mitoni ba command restore bargardunish, va ba restart shodane server kamelan pak mishe");
        yml.addDefault(Messages.MOVE_TOOL_NAME, "&eJabeja Kon");
        yml.addDefault(Messages.MOVE_TOOL_LORE, "&7ArmorStand ro be jayi\n&7ke mikhay javebeja koni\n\n&7Pishnahad: az command\n&7/as move estefade kon\n\n&eBezan baraye jabeja kardan");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING, "&cToo alan too session jabejai hasti");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE, "&aBlocki ke mikhay ArmorStand ruye un bashe ro bekon");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY, "&cToo alan too session jabejai hasti");
        yml.addDefault(Messages.MOVE_TOOL_START, "&aBlocki ke mikhay ArmorStand ruye un bashe ro bekon");
        yml.addDefault(Messages.TELEPORT_TOOL_NAME, "&eTeleport");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE, "&7Be mokane\n&7in ArmorStand teleport kon\n\n&eBezan baraye teleport");
        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aIn mokan baraye in ArmorStand ghabul shod.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aMokane jadid ba movafaghiat rad shod.");

        yml.options().copyDefaults(true);
        save();
    }
}