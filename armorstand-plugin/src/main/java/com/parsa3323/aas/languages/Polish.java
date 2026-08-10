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

public class Polish extends Language {
    /*
    Made with AI
    */
    public Polish() {
        super(AdvancedArmorStands.plugin, "pl");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "akcje");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7Oznacza to, że jeśli\n&7klikniesz ten armor\n&7stand, wykona się\n&7komenda: %command%\n\n&6» &eKliknij, aby edytować\n&6» &eShift + klik, aby usunąć");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eStwórz akcję");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE, "&7Wybierz to, aby stworzyć\n&7akcję, która wykonuje\n&7komendy po kliknięciu\n\n&eKliknij, aby stworzyć");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aWpisz komendę, którą chcesz ustawić (bez '/'). Wpisz 'exit', aby anulować.");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "ustawienia akcji");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eTyp Wyzwalacza");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Wybierz, jak komenda\n&7będzie się uruchamiać, gdy\n&7gracz wejdzie w interakcję\n&7z ArmorStandem.\n\n%trigger_list%\n\n&eKliknij, aby zmienić");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eNadawca");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Wybierz, kto wyśle\n&7komendę. Wpływa to na\n&7działanie uprawnień i wykonania.\n\n%sender_list%\n\n&eKliknij, aby zmienić");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_NAME, "&ePriorytet");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_LORE, "&7Zmień priorytet\n&7tej komendy. Gdy\n&7priorytet jest wyższy\n&7niż pozostałych, zostanie\n&7wykonana wcześniej.\n\n&6» &e%priority_number%\n\n&eKliknij, aby zmienić");
        yml.addDefault(Messages.ACTIONS_SETTING_PRIORITY_ALREADY_TAKEN, "&eDwie lub więcej akcji ma ten sam priorytet, co może powodować ich wzajemne konflikty.");
        yml.addDefault(Messages.AI_MENU_TITLE, "ustawienia ai");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Poproś AI o pomoc");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7Myśli...");
        yml.addDefault(Messages.AI_OPTION_NAME, "&eOpcje AI");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE, "&7Opcje związane ze\n&7sztuczną inteligencją\n&7tego ArmorStanda.\n\n&eKliknij, aby otworzyć");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE, "&7Włącz AI dla ArmorStandów,\n&7ustawiając swój klucz API\n&7w konfiguracji.\n\n&4✘ &cNie włączone");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Dodaj własne instrukcje,\n&7których AI tego ArmorStanda\nbędzie się trzymać podczas odpowiadania.\n\n%current%\n\n&6» &eShift + klik, aby zresetować\n&6» &eKliknij, aby zmienić");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7Włącz lub wyłącz AI\n&7dla tego ArmorStanda\n\n%ai_status%");
        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS, "&aPomyślnie opuściłeś sesję ustawiania pamięci.");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS, "&aInstrukcje ArmorStanda zostały zaktualizowane");
        yml.addDefault(Messages.AI_PREFIX, "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT, "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND, "Błąd AI: nie znaleziono tekstu asystenta w odpowiedzi");
        yml.addDefault(Messages.AI_ERROR, "&cBłąd AI: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP, "&cBłąd AI: HTTP %code%, upewnij się, że masz połączenie z internetem");
        yml.addDefault(Messages.AI_HTTP_ERROR, "Błąd AI: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET, "Błąd AI: HTTP {code}, upewnij się, że masz połączenie z internetem");
        yml.addDefault(Messages.AI_PARSE_ERROR, "Błąd przetwarzania odpowiedzi AI: {error}");
        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Pokazuje komendy animacji");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER, "&6&lAdvanced &e&lArmorStands &7&lkomendy animacji");
        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS, "&aAnimacja %animation% została przypisana do ArmorStanda %armorstand%");
        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION, "Usuwa &7animacje danego as");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS, "&aAnimacje %armorstand% zostały usunięte");
        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION, "&7Stwórz lub edytuj &7animację za pomocą as");
        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION, "&7Całkowicie usuwa animację");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS, "&aAnimacja '%animation%' została usunięta");
        yml.addDefault(Messages.ANIMATION_INVALID, "&cNieprawidłowa animacja");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION, "&cNieprawidłowa animacja '%animation%'. Czy chodziło Ci o '%suggestion%'?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME, "&cNieprawidłowa nazwa animacji");
        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION, "&cNie można otworzyć edytora na ArmorStandzie, który ma już animację");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED, "&aTwój tryb gry został tymczasowo zmieniony na CREATIVE, ponieważ sesje edycji nie obsługują trybu ADVENTURE. Zostanie przywrócony automatycznie, gdy wyjdziesz.");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED, "&aPomyślnie wszedłeś do sesji tworzenia/edycji animacji");
        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aAnimacja została zapisana, kliknij, aby dodać ją do tego ArmorStanda");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "edytor klatek kluczowych");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&ePRAWY PRZYCISK &7Dodaje klatkę kluczową\n&eSHIFT + PRAWY PRZYCISK &7Otwiera menu klatek kluczowych\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aDodano klatkę kluczową!");
        yml.addDefault(Messages.KEYFRAME_STEP_LORE, "&7To jest %step%%suffix% krok\n&7animacji %animation%, który\n&7ustawi ArmorStanda w tej pozycji\n&7w swojej kolejce\n\n&eKliknij, aby usunąć");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME, "&eOdstęp: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE, "&7Ustawia opóźnienie\n&7między klatkami animacji.\n&7Im niższa wartość,\n&7tym szybsza animacja.\n\n&6»&e Lewy klik, aby zwiększyć\n&6»&e Prawy klik, aby zmniejszyć.\n&6»&e Przytrzymaj Shift, aby zmieniać\n&6»&e co 10.\n\n&eKliknij, aby zmienić");
        yml.addDefault(Messages.KEYFRAME_LOOP_NAME, "&ePętla: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE, "&7Określa, czy animacja\n&7ma się powtarzać\n&7od początku w nieskończoność\n\n&eKliknij, aby zmienić");
        yml.addDefault(Messages.SAVE_MENU_TITLE, "menu zapisu");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE, "&aWpisz nazwę typu, który chcesz stworzyć (zostaną skopiowane właściwości tego ArmorStanda). Wpisz 'exit', aby wyjść");
        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE, "&7Wybranie tego nadpisze\n&7starą konfigurację %type% obecną\n&7konfiguracją, którą właśnie stworzyłeś\n\n&eKliknij, aby zapisać");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED, "&aWłaściwości ArmorStanda zapisano jako '%type%'");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME, "&eStwórz typ");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE, "&7Wybierz to, aby stworzyć\n&7typ z właściwościami\n&7tego armor standa\n\n&eKliknij, aby stworzyć");
        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "ustawienia");
        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cTen ArmorStand nie jest załadowany");
        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cNieprawidłowy ArmorStand");
        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cNieprawidłowy ArmorStand '%armorstand%'. Czy chodziło Ci o '%suggestion%'?");
        yml.addDefault(Messages.ARMORSTAND_NOT_FOUND, "&cNie znaleziono ArmorStanda!");
        yml.addDefault(Messages.WORLD_NOT_FOUND, "&cNie znaleziono świata!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMORSTAND, "&aTeleportowano do %name%");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_SUCCESS, "&aArmorStand zaktualizowany pomyślnie!");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_FAILED, "&cNie udało się zaktualizować ArmorStanda!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE, "&cArmorStand nie jest już dostępny!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE_TITLE, "&cArmorStand jest niedostępny");
        yml.addDefault(Messages.ARMORSTAND_MOVE_SUCCESS, "&aArmorStand przeniesiono pomyślnie.");
        yml.addDefault(Messages.ARMORSTANDS_NOT_LOADED, "&cWygląda na to, że %amount% ArmorStandów nie zostało załadowanych przez generator świata. Aby to naprawić, włącz 'auto-load-armor-stands' w konfiguracji, żeby wszystkie ładowały się automatycznie.");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN, "&eAdvancedArmorStands napotkał %amount% błąd(ów)/ostrzeżenie(ń) w tej sesji. Sprawdź plik logów, aby poznać szczegóły.");
        yml.addDefault(Messages.ARMORSTAND_SAVE_CONFIRM, "&aZrób to jeszcze %amount% raz%plural%, aby zapisać tego ArmorStanda.");
        yml.addDefault(Messages.ARMORSTAND_SAVED_SUCCESS, "&eArmorStand zapisano jako %name%!");
        yml.addDefault(Messages.ARMORSTAND_DELETE_CONFIRM, "&cZrób to jeszcze %amount% raz%plural%, aby usunąć tego ArmorStanda.");
        yml.addDefault(Messages.ARMORSTAND_DELETED_SUCCESS, "&aArmorStand został usunięty, ale można go przywrócić komendą restore. Zostanie usunięty całkowicie po restarcie serwera");
        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cNie masz uprawnień, aby użyć tej komendy!");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&cKomenda '%command%' nie jest prawidłową podkomendą.");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&cKomenda '%command%' nie jest prawidłową podkomendą. Czy chodziło Ci o '%suggestion%'?");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cProszę wpisać prawidłowy numer strony.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cNieprawidłowa strona. Wybierz stronę pomiędzy 1 a %pages%.");
        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eKliknij, aby użyć podpowiedzi");
        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Przykłady:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE, "&e&lKliknij, aby użyć tej komendy");
        yml.addDefault(Messages.COMMAND_LABEL, "&7Komenda: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL, "&7Opis: &f%description%");
        yml.addDefault(Messages.CREATE_DESCRIPTION, "Tworzy &7ArmorStanda");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE, "&cUżycie: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER, "&cNieprawidłowa liczba dla części %part%. Użycie: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART, "&cNieznana część: %part%");
        yml.addDefault(Messages.DELETE_DESCRIPTION, "Usuwa &7ArmorStanda");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS, "&aWszystkie ArmorStandy zostały usunięte");
        yml.addDefault(Messages.DELETE_SUCCESS, "&aArmorStand w pełni usunięty");
        yml.addDefault(Messages.DEBUG_DESCRIPTION, "Pokazuje informacje debugowania");
        yml.addDefault(Messages.HEAD_DESCRIPTION, "Pobierz głowę gracza");
        yml.addDefault(Messages.HEAD_SUCCESS, "&aOtrzymałeś głowę gracza %player%");
        yml.addDefault(Messages.LIST_DESCRIPTION, "Pokazuje listę ArmorStandów");
        yml.addDefault(Messages.LIST_EMPTY_TITLE, "&c&l     Nie znaleziono zapisanych ArmorStandów ");
        yml.addDefault(Messages.LIST_EMPTY_HINT, "&7Użyj &e/as create <type> <name>&7, aby stworzyć swojego pierwszego ArmorStanda!");
        yml.addDefault(Messages.LOAD_DESCRIPTION, "Ładuje ArmorStanda");
        yml.addDefault(Messages.LOAD_NO_UNLOADED, "&eNie znaleziono niezaładowanych ArmorStandów.");
        yml.addDefault(Messages.LOAD_FAILED, "&cNie udało się: %failed%");
        yml.addDefault(Messages.MOVE_DESCRIPTION, "Przenosi ArmorStanda do Twojej &7lokalizacji");
        yml.addDefault(Messages.OPTIONS_DESCRIPTION, "Otwiera menu opcji");
        yml.addDefault(Messages.RELOAD_DESCRIPTION, "Przeładowuje konfiguracje pluginu");
        yml.addDefault(Messages.RELOAD_SUCCESS, "&aKonfiguracja AdvancedArmorStand została przeładowana");
        yml.addDefault(Messages.RELOAD_ERROR, "&cBłąd podczas przeładowywania pluginu, sprawdź konsolę, aby poznać szczegóły");
        yml.addDefault(Messages.RENAME_DESCRIPTION, "Zmienia nazwę &7as");
        yml.addDefault(Messages.RENAME_SUCCESS, "&aZmieniono nazwę ArmorStanda z '%old_name%' na '%new_name%'");
        yml.addDefault(Messages.RESTORE_DESCRIPTION, "Przywraca usuniętego ArmorStanda");
        yml.addDefault(Messages.RESTORE_NOT_DELETED, "&cTen ArmorStand nie jest usunięty lub jest już za późno");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR, "&cNieznany błąd, sprawdź konsolę, aby uzyskać więcej informacji");
        yml.addDefault(Messages.RESTORE_SUCCESS, "&aArmorStand został przywrócony");
        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND, "&cTen ArmorStand nie stoi na ziemi. Na pewno chcesz się do niego teleportować?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT, "&cUżyj '/as teleport %armorstand% --force', aby wymusić teleportację");
        yml.addDefault(Messages.TELL_DESCRIPTION, "Zadaj pytanie AI ArmorStanda");
        yml.addDefault(Messages.TELL_AI_DISABLED, "&cTen ArmorStand nie ma włączonego AI");
        yml.addDefault(Messages.INVENTORY_EXIT_NAME, "&7» &cWYJŚCIE&7 (Prawy przycisk)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE, "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION, "&aWszedłeś do sesji edycji, kliknij przedmiot WYJŚCIE, aby wrócić i odzyskać swoje przedmioty");
        yml.addDefault(Messages.EDITOR_EXIT_NAME, "&7» &cWYJŚCIE &7(Prawy przycisk)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE, "\n&8AdvancedArmorStands Editor Item");
        yml.addDefault(Messages.HEAD_ITEM_LORE, "&ePRAWY PRZYCISK &8» &7Obraca głowę w prawo\n&eLEWY PRZYCISK &8» &7Obraca głowę w lewo\n&eSHIFT + PRAWY PRZYCISK &8» &7Podnosi głowę\n&eSHIFT + LEWY PRZYCISK &8» &7Opuszcza głowę\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE, "&ePRAWY PRZYCISK &8» &7Obraca lewą rękę w prawo\n&eLEWY PRZYCISK &8» &7Obraca lewą rękę w lewo\n&eSHIFT + PRAWY PRZYCISK &8» &7Podnosi lewe ramię\n&eSHIFT + LEWY PRZYCISK &8» &7Opuszcza lewe ramię\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE, "&ePRAWY PRZYCISK &8» &7Obraca prawą rękę w prawo\n&eLEWY PRZYCISK &8» &7Obraca prawą rękę w lewo\n&eSHIFT + PRAWY PRZYCISK &8» &7Podnosi prawe ramię\n&eSHIFT + LEWY PRZYCISK &8» &7Opuszcza lewe ramię\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE, "&ePRAWY PRZYCISK &8» &7Obraca lewą nogę w prawo\n&eLEWY PRZYCISK &8» &7Obraca lewą nogę w lewo\n&eSHIFT + PRAWY PRZYCISK &8» &7Podnosi lewą nogę\n&eSHIFT + LEWY PRZYCISK &8» &7Opuszcza lewą nogę\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE, "&ePRAWY PRZYCISK &8» &7Obraca prawą nogę w prawo\n&eLEWY PRZYCISK &8» &7Obraca prawą nogę w lewo\n&eSHIFT + PRAWY PRZYCISK &8» &7Podnosi prawą nogę\n&eSHIFT + LEWY PRZYCISK &8» &7Opuszcza prawą nogę\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE, "&ePRAWY PRZYCISK &8» &7Obraca w prawo\n&eLEWY PRZYCISK &8» &7Obraca w lewo\n&eSHIFT + PRAWY PRZYCISK (Blok) &8» &7Podnosi ciało\n&eSHIFT + LEWY PRZYCISK (Blok) &8» &7Opuszcza ciało\n&eSHIFT + PRAWY PRZYCISK (Powietrze) &8» &7Przesuwa ciało w prawo\n&eSHIFT + LEWY PRZYCISK (Powietrze) &8» &7Przesuwa ciało w lewo\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE, "&7Kliknij, aby zapisać ustawienia\n&7ArmorStanda jako typ, którego będziesz mógł użyć później\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK, "&cNie możesz niszczyć bloków w sesji edycji.");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK, "&cNie możesz tu niszczyć bloków.");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START, "&aWpisz na czacie nazwę, którą chcesz ustawić. Aby wyjść, wpisz 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY, "&cJesteś już w sesji ustawiania nazwy");
        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS, "&aPomyślnie opuściłeś sesję ustawiania nazwy");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS, "&aNazwa własna ArmorStanda została ustawiona na '%name%'");
        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS, "&aPomyślnie opuściłeś sesję tworzenia typu");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS, "&cTen typ już istnieje. Wybierz inną nazwę albo wybierz ten typ w menu zapisu");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS, "&aStworzono typ '%type%' z właściwościami tego ArmorStanda");
        yml.addDefault(Messages.CREATION_CANCELLED, "&cTworzenie anulowane z powodu bezczynności");
        yml.addDefault(Messages.DELETION_CANCELLED, "&cUsuwanie anulowane z powodu bezczynności.");
        yml.addDefault(Messages.ARMS_OPTION_NAME, "&eRamiona");
        yml.addDefault(Messages.ARMS_OPTION_LORE, "&7Włącz lub wyłącz\n&7ramiona tego ArmorStanda \n\n%status%");
        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME, "&ePodstawa");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE, "&7Włącz lub wyłącz\n&7podstawę tego ArmorStanda \n\n%status%");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME, "&eWłasna nazwa");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE, "&7Ustaw własną nazwę\n&7dla tego ArmorStanda");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME, "&eObecna&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME, "&eObecna nazwa&6 » &eBrak");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME, "&aWpisz na czacie nazwę, którą chcesz ustawić. Aby wyjść, wpisz 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING, "&cJesteś już w sesji ustawiania nazwy");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME, "&eWidoczność własnej nazwy");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE, "&7Włącz lub wyłącz\n&7widoczność nazwy tego ArmorStanda\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_NAME, "&eŚwiecenie");
        yml.addDefault(Messages.GLOWING_OPTION_LORE, "&7Włącz lub wyłącz\n&7świecenie tego ArmorStanda\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Świeci");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Nie świeci");
        yml.addDefault(Messages.SMALL_OPTION_NAME, "&eMały");
        yml.addDefault(Messages.SMALL_OPTION_LORE, "&7Włącz lub wyłącz\n&7mały rozmiar tego ArmorStanda\n\n%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Jest mały");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Nie jest mały");
        yml.addDefault(Messages.VISIBLE_OPTION_NAME, "&eWidoczność");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE, "&7Włącz lub wyłącz\n&7widoczność tego ArmorStanda\n\n%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Jest widoczny");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Nie jest widoczny");
        yml.addDefault(Messages.DELETE_TOOL_NAME, "&eUsuń");
        yml.addDefault(Messages.DELETE_TOOL_LORE, "&7Usuwa tego ArmorStanda\n&7tej akcji nie można cofnąć\n\n&eKliknij, aby usunąć");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE, "&aArmorStand został usunięty, ale można go przywrócić komendą restore. Zostanie usunięty całkowicie po restarcie serwera");
        yml.addDefault(Messages.DELETE_TOOL_DELETED, "&aArmorStand został usunięty, ale można go przywrócić komendą restore. Zostanie usunięty całkowicie po restarcie serwera");
        yml.addDefault(Messages.MOVE_TOOL_NAME, "&ePrzenieś");
        yml.addDefault(Messages.MOVE_TOOL_LORE, "&7Przenosi ArmorStanda\n&7tam, gdzie chcesz\n\n&7Zalecane: użyj zamiast tego\n&7komendy /as move\n\n&eKliknij, aby przenieść");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING, "&cJesteś już w sesji przenoszenia");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE, "&aZniszcz blok, na którym ma stanąć ArmorStand");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY, "&cJesteś już w sesji przenoszenia");
        yml.addDefault(Messages.MOVE_TOOL_START, "&aZniszcz blok, na którym ma stanąć ArmorStand");
        yml.addDefault(Messages.TELEPORT_TOOL_NAME, "&eTeleportuj");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE, "&7Teleportuje Cię do\n&7lokalizacji ArmorStanda\n\n&eKliknij, aby się teleportować");
        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aTa pozycja została zaakceptowana dla tego ArmorStanda.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aNowa pozycja została odrzucona.");

        yml.options().copyDefaults(true);
        save();
    }
}