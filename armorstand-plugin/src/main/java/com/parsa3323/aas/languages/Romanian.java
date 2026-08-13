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

public class Romanian extends Language {
    /*
    Made with AI
    */
    public Romanian() {
        super(AdvancedArmorStands.plugin, "ro");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "acțiuni");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7Asta înseamnă că dacă\n&7dai clic pe acest armor\n&7stand, se va executa\n&7comanda: %command%\n\n&6» &eClic pentru a edita\n&6» &eShift + clic pentru a elimina");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eCreează o acțiune");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE, "&7Selectează asta pentru a crea\n&7o acțiune care execută\n&7comenzi la clic\n\n&eClic pentru a crea");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aScrie comanda pe care vrei să o setezi (fără '/'). Scrie 'exit' ca să anulezi.");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "setările acțiunii");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eTip de Declanșare");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Alege cum se va\n&7declanșa comanda când\n&7jucătorul interacționează cu\n&7ArmorStand-ul.\n\n%trigger_list%\n\n&eClic pentru a schimba");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eExpeditor");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Alege cine va trimite\n&7comanda. Asta afectează\n&7cum funcționează permisiunile și execuția.\n\n%sender_list%\n\n&eClic pentru a schimba");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_NAME, "&ePrioritate");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_LORE, "&7Schimbă prioritatea\n&7acestei comenzi. Când\n&7prioritatea este mai mare\n&7decât a celorlalte, aceasta\n&7va fi executată mai întâi.\n\n&6» &e%priority_number%\n\n&eClick pentru a schimba");
        yml.addDefault(Messages.ACTIONS_SETTING_PRIORITY_ALREADY_TAKEN, "&eDouă sau mai multe acțiuni au aceeași prioritate, ceea ce poate cauza conflicte între ele.");
        yml.addDefault(Messages.AI_MENU_TITLE, "setări ai");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Cere ajutor de la AI");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7Se gândește...");
        yml.addDefault(Messages.AI_OPTION_NAME, "&eOpțiuni AI");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE, "&7Opțiuni legate de\n&7inteligența artificială\n&7a acestui ArmorStand.\n\n&eClic pentru a deschide");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE, "&7Activează AI pentru ArmorStand-uri\n&7punându-ți cheia API\n&7în configurație.\n\n&4✘ &cNu e activat");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Adaugă instrucțiuni personalizate\n&7pe care AI-ul acestui ArmorStand\nle va urma când răspunde.\n\n%current%\n\n&6» &eShift + clic pentru a reseta\n&6» &eClic pentru a schimba");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7Activează sau dezactivează AI\n&7pentru acest ArmorStand\n\n%ai_status%");
        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS, "&aAi ieșit cu succes din sesiunea de memorie.");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS, "&aInstrucțiunile ArmorStand-ului au fost actualizate cu succes");
        yml.addDefault(Messages.AI_PREFIX, "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT, "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND, "Eroare AI: nu s-a găsit textul asistentului în răspuns");
        yml.addDefault(Messages.AI_ERROR, "&cEroare AI: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP, "&cEroare AI: HTTP %code%, asigură-te că ești conectat la internet");
        yml.addDefault(Messages.AI_HTTP_ERROR, "Eroare AI: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET, "Eroare AI: HTTP {code}, asigură-te că ești conectat la internet");
        yml.addDefault(Messages.AI_PARSE_ERROR, "Eroare la procesarea răspunsului AI: {error}");
        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Arată comenzile de animație");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER, "&6&lAdvanced &e&lArmorStands &7&lcomenzi de animație");
        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS, "&aAnimația %animation% a fost atribuită cu succes ArmorStand-ului %armorstand%");
        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION, "Șterge &7animațiile unui as");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS, "&aAnimațiile lui %armorstand% au fost șterse cu succes");
        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION, "&7Creează sau editează o &7animație cu un as");
        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION, "&7Elimină complet o animație");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS, "&aAnimația '%animation%' a fost eliminată cu succes");
        yml.addDefault(Messages.ANIMATION_INVALID, "&cAnimație invalidă");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION, "&cAnimația '%animation%' este invalidă. Ai vrut să spui '%suggestion%'?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME, "&cNume de animație invalid");
        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION, "&cNu poți deschide editorul pe un ArmorStand care are deja o animație");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED, "&aModul tău de joc a fost schimbat temporar în CREATIVE, deoarece sesiunile de editare nu suportă modul ADVENTURE. Va fi restaurat automat când ieși.");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED, "&aAi intrat cu succes în sesiunea de creare/editare a animației");
        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aAnimația a fost salvată cu succes, dă clic pentru a o adăuga la acest ArmorStand");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "editor de keyframe-uri");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eCLIC DREAPTA &7Adaugă un keyframe\n&eSHIFT + CLIC DREAPTA &7Deschide meniul de keyframe-uri\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aKeyframe adăugat!");
        yml.addDefault(Messages.KEYFRAME_STEP_LORE, "&7Acesta este pasul %step%%suffix%\n&7din animația %animation%, care\n&7va aduce ArmorStand-ul în această\n&7poziție la rândul lui\n\n&eClic pentru a elimina");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME, "&eInterval: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE, "&7Setează întârzierea\n&7dintre cadrele animației.\n&7Cu cât valoarea e mai mică,\n&7cu atât animația e mai rapidă.\n\n&6»&e Clic stânga pentru a crește\n&6»&e Clic dreapta pentru a scădea.\n&6»&e Ține apăsat Shift pentru a schimba\n&6»&e din 10 în 10.\n\n&eClic pentru a schimba");
        yml.addDefault(Messages.KEYFRAME_LOOP_NAME, "&eRepetare: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE, "&7Stabilește dacă animația\n&7trebuie să se repete în buclă\n&7de la început la infinit\n\n&eClic pentru a schimba");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_DISPLAY, "&eRealist: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_LORE, "&7Animațiile realiste interpolează lin\n&7între pașii animației în loc să sară instantaneu\n&7de la o poziție la alta.\n\n&7Avertisment: poate cauza lag\n\n&eClick pentru a schimba");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_DISPLAY, "&eCadre realiste: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_LORE, "&7Numărul de cadre generate\n&7în timpul animației realiste\n\n&6»&e Click stânga pentru a crește\n&6»&e Click dreapta pentru a scădea.\n&6»&e Ține apăsat Shift pentru a modifica\n&6»&e în pași de 10.\n\n&eClick pentru a schimba");
        yml.addDefault(Messages.SAVE_MENU_TITLE, "meniu de salvare");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE, "&aScrie numele tipului pe care vrei să-l creezi (proprietățile acestui ArmorStand vor fi copiate). Scrie 'exit' ca să ieși");
        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE, "&7Selectând asta vei suprascrie\n&7vechea configurație a lui %type% cu\n&7configurația actuală pe care ai făcut-o\n\n&eClic pentru a salva");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED, "&aProprietățile ArmorStand-ului au fost salvate în '%type%'");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME, "&eCreează un tip");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE, "&7Selectează asta pentru a crea\n&7un tip cu proprietățile\n&7acestui armor stand\n\n&eClic pentru a crea");
        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "setări");
        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cAcest ArmorStand nu este încărcat");
        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cArmorStand invalid");
        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cArmorStand '%armorstand%' invalid. Ai vrut să spui '%suggestion%'?");
        yml.addDefault(Messages.ARMORSTAND_NOT_FOUND, "&cArmorStand negăsit!");
        yml.addDefault(Messages.WORLD_NOT_FOUND, "&cLume negăsită!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMORSTAND, "&aTeleportat la %name%");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_SUCCESS, "&aArmorStand actualizat cu succes!");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_FAILED, "&cActualizarea ArmorStand-ului a eșuat!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE, "&cArmorStand-ul nu mai este disponibil!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE_TITLE, "&cArmorStand-ul nu este disponibil");
        yml.addDefault(Messages.ARMORSTAND_MOVE_SUCCESS, "&aArmorStand mutat cu succes.");
        yml.addDefault(Messages.ARMORSTANDS_NOT_LOADED, "&cSe pare că %amount% ArmorStand-uri nu au fost încărcate de generatorul de lume. Pentru a repara asta, activează 'auto-load-armor-stands' în configurație ca să se încarce toate automat.");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN, "&eAdvancedArmorStands a întâmpinat %amount% eroare/avertisment în această sesiune. Verifică fișierul de log pentru detalii.");
        yml.addDefault(Messages.ARMORSTAND_SAVE_CONFIRM, "&aFă asta încă %amount% dată%plural% ca să salvezi acest ArmorStand.");
        yml.addDefault(Messages.ARMORSTAND_SAVED_SUCCESS, "&eArmorStand salvat ca %name%!");
        yml.addDefault(Messages.ARMORSTAND_DELETE_CONFIRM, "&cFă asta încă %amount% dată%plural% ca să ștergi acest ArmorStand.");
        yml.addDefault(Messages.ARMORSTAND_DELETED_SUCCESS, "&aArmorStand-ul a fost șters, dar poate fi restaurat cu comanda restore și va fi șters complet la repornirea serverului");
        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cNu ai permisiunea să folosești această comandă!");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&cComanda '%command%' nu este o subcomandă validă.");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&cComanda '%command%' nu este o subcomandă validă. Ai vrut să spui '%suggestion%'?");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cTe rog introdu un număr de pagină valid.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cPagină invalidă. Alege o pagină între 1 și %pages%.");
        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eClic pentru a folosi sugestia");
        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Exemple:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE, "&e&lClic pentru a folosi această comandă");
        yml.addDefault(Messages.COMMAND_LABEL, "&7Comandă: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL, "&7Descriere: &f%description%");
        yml.addDefault(Messages.CREATE_DESCRIPTION, "Creează un &7ArmorStand");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE, "&cUtilizare: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER, "&cNumăr invalid pentru partea %part%. Utilizare: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART, "&cParte necunoscută: %part%");
        yml.addDefault(Messages.DELETE_DESCRIPTION, "Șterge un &7ArmorStand");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS, "&aToate ArmorStand-urile au fost șterse cu succes");
        yml.addDefault(Messages.DELETE_SUCCESS, "&aArmorStand șters complet");
        yml.addDefault(Messages.DEBUG_DESCRIPTION, "Arată informații de depanare");
        yml.addDefault(Messages.HEAD_DESCRIPTION, "Obține capul unui jucător");
        yml.addDefault(Messages.HEAD_SUCCESS, "&aȚi-a fost dat capul lui %player%");
        yml.addDefault(Messages.LIST_DESCRIPTION, "Arată o listă cu ArmorStand-uri");
        yml.addDefault(Messages.LIST_EMPTY_TITLE, "&c&l     Niciun ArmorStand Salvat Găsit ");
        yml.addDefault(Messages.LIST_EMPTY_HINT, "&7Folosește &e/as create <type> <name>&7 ca să creezi primul tău ArmorStand!");
        yml.addDefault(Messages.LOAD_DESCRIPTION, "Încarcă un ArmorStand");
        yml.addDefault(Messages.LOAD_NO_UNLOADED, "&eNu s-a găsit niciun ArmorStand neîncărcat.");
        yml.addDefault(Messages.LOAD_FAILED, "&cA eșuat: %failed%");
        yml.addDefault(Messages.MOVE_DESCRIPTION, "Mută un ArmorStand la &7locația ta");
        yml.addDefault(Messages.OPTIONS_DESCRIPTION, "Deschide meniul de opțiuni");
        yml.addDefault(Messages.RELOAD_DESCRIPTION, "Reîncarcă configurațiile plugin-ului");
        yml.addDefault(Messages.RELOAD_SUCCESS, "&aConfigurația AdvancedArmorStand a fost reîncărcată cu succes");
        yml.addDefault(Messages.RELOAD_ERROR, "&cEroare la reîncărcarea plugin-ului, verifică consola pentru mai multe detalii");
        yml.addDefault(Messages.RENAME_DESCRIPTION, "Redenumește un &7as");
        yml.addDefault(Messages.RENAME_SUCCESS, "&aArmorStand redenumit din '%old_name%' în '%new_name%'");
        yml.addDefault(Messages.RESTORE_DESCRIPTION, "Restaurează un ArmorStand șters");
        yml.addDefault(Messages.RESTORE_NOT_DELETED, "&cAcest ArmorStand nu este șters sau e prea târziu");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR, "&cEroare necunoscută, verifică consola pentru mai multe informații");
        yml.addDefault(Messages.RESTORE_SUCCESS, "&aArmorStand restaurat cu succes");
        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND, "&cAcest ArmorStand nu este pe sol. Ești sigur că vrei să te teleportezi la el?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT, "&cFolosește '/as teleport %armorstand% --force' ca să forțezi teleportarea");
        yml.addDefault(Messages.TELL_DESCRIPTION, "Pune o întrebare AI-ului unui ArmorStand");
        yml.addDefault(Messages.TELL_AI_DISABLED, "&cAcest ArmorStand nu are AI activat");
        yml.addDefault(Messages.INVENTORY_EXIT_NAME, "&7» &cIEȘIRE&7 (Clic Dreapta)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE, "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION, "&aAi intrat în sesiunea de editare, dă clic pe obiectul IEȘIRE ca să te întorci și să-ți recuperezi obiectele");
        yml.addDefault(Messages.EDITOR_EXIT_NAME, "&7» &cIEȘIRE &7(Clic Dreapta)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE, "\n&8AdvancedArmorStands Editor Item");
        yml.addDefault(Messages.HEAD_ITEM_LORE, "&eCLIC DREAPTA &8» &7Rotește capul spre dreapta\n&eCLIC STÂNGA &8» &7Rotește capul spre stânga\n&eSHIFT + CLIC DREAPTA &8» &7Ridică capul\n&eSHIFT + CLIC STÂNGA &8» &7Coboară capul\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE, "&eCLIC DREAPTA &8» &7Rotește mâna stângă spre dreapta\n&eCLIC STÂNGA &8» &7Rotește mâna stângă spre stânga\n&eSHIFT + CLIC DREAPTA &8» &7Ridică brațul stâng\n&eSHIFT + CLIC STÂNGA &8» &7Coboară brațul stâng\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE, "&eCLIC DREAPTA &8» &7Rotește mâna dreaptă spre dreapta\n&eCLIC STÂNGA &8» &7Rotește mâna dreaptă spre stânga\n&eSHIFT + CLIC DREAPTA &8» &7Ridică brațul drept\n&eSHIFT + CLIC STÂNGA &8» &7Coboară brațul stâng\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE, "&eCLIC DREAPTA &8» &7Rotește piciorul stâng spre dreapta\n&eCLIC STÂNGA &8» &7Rotește piciorul stâng spre stânga\n&eSHIFT + CLIC DREAPTA &8» &7Ridică piciorul stâng\n&eSHIFT + CLIC STÂNGA &8» &7Coboară piciorul stâng\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE, "&eCLIC DREAPTA &8» &7Rotește piciorul drept spre dreapta\n&eCLIC STÂNGA &8» &7Rotește piciorul drept spre stânga\n&eSHIFT + CLIC DREAPTA &8» &7Ridică piciorul drept\n&eSHIFT + CLIC STÂNGA &8» &7Coboară piciorul drept\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE, "&eCLIC DREAPTA &8» &7Rotește spre dreapta\n&eCLIC STÂNGA &8» &7Rotește spre stânga\n&eSHIFT + CLIC DREAPTA (Bloc) &8» &7Ridică corpul\n&eSHIFT + CLIC STÂNGA (Bloc) &8» &7Coboară corpul\n&eSHIFT + CLIC DREAPTA (Aer) &8» &7Mută corpul la dreapta\n&eSHIFT + CLIC STÂNGA (Aer) &8» &7Mută corpul la stânga\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE, "&7Dă clic pentru a salva configurația\n&7ArmorStand-ului ca un tip, ca să-l poți folosi mai târziu\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK, "&cNu poți sparge blocuri în sesiunea de editare.");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK, "&cNu poți sparge blocuri aici.");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START, "&aScrie în chat numele pe care vrei să-l setezi. Ca să ieși scrie 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY, "&cEști deja într-o sesiune de setare a numelui");
        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS, "&aAi ieșit cu succes din sesiunea de setare a numelui");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS, "&aNumele personalizat al ArmorStand-ului a fost setat cu succes la '%name%'");
        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS, "&aAi ieșit cu succes din sesiunea de creare a tipului");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS, "&cAcest tip există deja. Alege alt nume sau selectează tipul din meniul de salvare");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS, "&aTipul '%type%' a fost creat cu proprietățile acestui ArmorStand");
        yml.addDefault(Messages.CREATION_CANCELLED, "&cCreare anulată din cauza inactivității");
        yml.addDefault(Messages.DELETION_CANCELLED, "&cȘtergere anulată din cauza inactivității.");
        yml.addDefault(Messages.ARMS_OPTION_NAME, "&eBrațe");
        yml.addDefault(Messages.ARMS_OPTION_LORE, "&7Activează sau dezactivează\n&7brațele acestui ArmorStand \n\n%status%");
        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME, "&ePlacă de Bază");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE, "&7Activează sau dezactivează\n&7placa de bază a acestui ArmorStand \n\n%status%");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME, "&eNume personalizat");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE, "&7Setează un nume personalizat\n&7pentru acest ArmorStand");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME, "&eActual&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME, "&eNume actual&6 » &eNiciunul");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME, "&aScrie în chat numele pe care vrei să-l setezi. Ca să ieși scrie 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING, "&cEști deja într-o sesiune de setare a numelui");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME, "&eNume personalizat vizibil");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE, "&7Activează sau dezactivează\n&7numele personalizat al acestui ArmorStand\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_NAME, "&eStrălucire");
        yml.addDefault(Messages.GLOWING_OPTION_LORE, "&7Activează sau dezactivează\n&7strălucirea acestui ArmorStand\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Strălucește");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Nu strălucește");
        yml.addDefault(Messages.SMALL_OPTION_NAME, "&eMic");
        yml.addDefault(Messages.SMALL_OPTION_LORE, "&7Activează sau dezactivează\n&7dimensiunea mică a acestui ArmorStand\n\n%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Este mic");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Nu este mic");
        yml.addDefault(Messages.VISIBLE_OPTION_NAME, "&eVizibilitate");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE, "&7Activează sau dezactivează\n&7vizibilitatea acestui ArmorStand\n\n%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Este vizibil");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Nu este vizibil");
        yml.addDefault(Messages.DELETE_TOOL_NAME, "&eȘterge");
        yml.addDefault(Messages.DELETE_TOOL_LORE, "&7Șterge acest ArmorStand\n&7această acțiune nu poate fi anulată\n\n&eClic pentru a șterge");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE, "&aArmorStand-ul a fost șters, dar poate fi restaurat cu comanda restore și va fi șters complet la repornirea serverului");
        yml.addDefault(Messages.DELETE_TOOL_DELETED, "&aArmorStand-ul a fost șters, dar poate fi restaurat cu comanda restore și va fi șters complet la repornirea serverului");
        yml.addDefault(Messages.MOVE_TOOL_NAME, "&eMută");
        yml.addDefault(Messages.MOVE_TOOL_LORE, "&7Mută ArmorStand-ul\n&7unde vrei tu\n\n&7Recomandat: folosește\n&7comanda /as move în loc\n\n&eClic pentru a muta");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING, "&cEști deja în sesiunea de mutare");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE, "&aSparge blocul pe care vrei să stea ArmorStand-ul");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY, "&cEști deja în sesiunea de mutare");
        yml.addDefault(Messages.MOVE_TOOL_START, "&aSparge blocul pe care vrei să stea ArmorStand-ul");
        yml.addDefault(Messages.TELEPORT_TOOL_NAME, "&eTeleportare");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE, "&7Te teleportează la\n&7locația ArmorStand-ului\n\n&eClic pentru a te teleporta");
        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aAceastă poziție a fost acceptată pentru acest ArmorStand.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aNoua poziție a fost respinsă cu succes.");

        yml.options().copyDefaults(true);
        save();
    }
}