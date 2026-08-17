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

public class Italian extends Language {
    /*
    Made with AI
    */
    public Italian() {
        super(AdvancedArmorStands.plugin, "it");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "azioni");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7Questo significa che se\n&7clicchi su questo armor\n&7stand, verrà eseguito\n&7il comando: %command%\n\n&6» &eClicca per modificare\n&6» &eShift + clic per rimuovere");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eCrea un'azione");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE, "&7Seleziona questo per creare\n&7un'azione che esegue\n&7comandi al clic\n\n&eClicca per creare");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aScrivi il comando che vuoi impostare (senza '/'). Scrivi 'exit' per annullare.");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "impostazioni azione");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eTipo di attivazione");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Scegli come verrà\n&7attivato il comando quando\n&7il giocatore interagisce con\n&7l'ArmorStand.\n\n%trigger_list%\n\n&eClicca per cambiare");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eMittente");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Scegli chi invierà\n&7il comando. Questo influisce su\n&7come funzionano i permessi e l'esecuzione.\n\n%sender_list%\n\n&eClicca per cambiare");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_NAME, "&ePriorità");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_LORE, "&7Modifica la priorità\n&7di questo comando. Quando\n&7la priorità è più alta\n&7delle altre, verrà eseguito\n&7prima delle altre.\n\n&6» &e%priority_number%\n\n&eClicca per modificare");
        yml.addDefault(Messages.ACTIONS_SETTING_PRIORITY_ALREADY_TAKEN, "&eDue o più azioni hanno la stessa priorità, il che potrebbe causare conflitti tra loro.");
        yml.addDefault(Messages.AI_MENU_TITLE, "impostazioni ia");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Chiedi aiuto all'IA");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7Sta pensando...");
        yml.addDefault(Messages.AI_OPTION_NAME, "&eOpzioni IA");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE, "&7Opzioni relative\n&7all'intelligenza artificiale\n&7di questo ArmorStand.\n\n&eClicca per aprire");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE, "&7Attiva l'IA per gli ArmorStand\n&7impostando la tua chiave API\n&7nella configurazione.\n\n&4✘ &cNon attivata");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Aggiungi istruzioni personalizzate\n&7che l'IA di questo ArmorStand\nseguirà quando risponde.\n\n%current%\n\n&6» &eShift + clic per ripristinare\n&6» &eClicca per cambiare");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7Attiva o disattiva l'IA\n&7di questo ArmorStand\n\n%ai_status%");
        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS, "&aSei uscito correttamente dalla sessione di memoria.");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS, "&aIstruzioni dell'ArmorStand aggiornate correttamente");
        yml.addDefault(Messages.AI_PREFIX, "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT, "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND, "Errore IA: non è stato trovato il testo dell'assistente nella risposta");
        yml.addDefault(Messages.AI_ERROR, "&cErrore IA: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP, "&cErrore IA: HTTP %code%, assicurati di essere connesso a internet");
        yml.addDefault(Messages.AI_HTTP_ERROR, "Errore IA: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET, "Errore IA: HTTP {code}, assicurati di essere connesso a internet");
        yml.addDefault(Messages.AI_PARSE_ERROR, "Errore di elaborazione IA: {error}");
        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Mostra i comandi di animazione");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER, "&6&lAdvanced &e&lArmorStands &7&lcomandi di animazione");
        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS, "&aAnimazione %animation% assegnata all'ArmorStand %armorstand%");
        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION, "Rimuove le &7animazioni di un as");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS, "&aAnimazioni di %armorstand% rimosse correttamente");
        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION, "&7Crea o modifica un'&7animazione con un as");
        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION, "&7Rimuove completamente un'animazione");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS, "&aAnimazione '%animation%' rimossa correttamente");
        yml.addDefault(Messages.ANIMATION_INVALID, "&cAnimazione non valida");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION, "&cAnimazione '%animation%' non valida. Intendevi '%suggestion%'?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME, "&cNome animazione non valido");
        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION, "&cNon puoi aprire l'editor su un ArmorStand che ha già un'animazione");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED, "&aLa tua modalità di gioco è stata temporaneamente cambiata in CREATIVA, perché le sessioni di modifica non supportano la modalità AVVENTURA. Verrà ripristinata automaticamente quando uscirai.");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED, "&aSei entrato correttamente nella sessione di creazione/modifica animazione");
        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aAnimazione salvata correttamente, clicca per aggiungerla a questo ArmorStand");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "editor fotogrammi chiave");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eCLIC DESTRO &7Aggiungi un fotogramma chiave\n&eSHIFT + CLIC DESTRO &7Apri il menu dei fotogrammi chiave\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aFotogramma chiave aggiunto!");
        yml.addDefault(Messages.KEYFRAME_STEP_LORE, "&7Questo è il passo %step%%suffix%\n&7dell'animazione %animation%, che\n&7metterà l'ArmorStand in questa posizione\n&7al suo turno\n\n&eClicca per rimuovere");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME, "&eIntervallo: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE, "&7Imposta il ritardo\n&7tra i fotogrammi dell'animazione.\n&7Più basso è il valore,\n&7più veloce sarà l'animazione.\n\n&6»&e Clic sinistro per aumentare\n&6»&e Clic destro per diminuire.\n&6»&e Tieni premuto Shift per cambiare\n&6»&e a incrementi di 10.\n\n&eClicca per cambiare");
        yml.addDefault(Messages.KEYFRAME_LOOP_NAME, "&eLoop: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE, "&7Stabilisce se l'animazione\n&7deve ripetersi in loop\n&7dall'inizio all'infinito\n\n&eClicca per cambiare");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_DISPLAY, "&eRealistica: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_LORE, "&7Le animazioni realistiche interpolano\n&7fluidamente tra i passaggi dell'animazione invece di\n&7passare istantaneamente da una posa all'altra.\n\n&7Attenzione: potrebbe causare lag\n\n&eClicca per modificare");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_DISPLAY, "&eFrame realistici: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_LORE, "&7Il numero di frame generati\n&7durante l'animazione realistica\n\n&6»&e Clic sinistro per aumentare\n&6»&e Clic destro per diminuire.\n&6»&e Tieni premuto Shift per modificare\n&6»&e con incrementi di 10.\n\n&eClicca per modificare");
        yml.addDefault(Messages.SAVE_MENU_TITLE, "menu di salvataggio");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE, "&aScrivi il nome del tipo che vuoi creare (verranno copiate le proprietà di questo ArmorStand). Scrivi 'exit' per uscire");
        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE, "&7Selezionando questo sovrascriverai\n&7la vecchia configurazione di %type% con\n&7quella attuale che hai appena creato\n\n&eClicca per salvare");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED, "&aProprietà dell'ArmorStand salvate in '%type%'");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME, "&eCrea un tipo");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE, "&7Seleziona questo per creare\n&7un tipo con le proprietà\n&7di questo armor stand\n\n&eClicca per creare");
        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "impostazioni");
        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cQuesto ArmorStand non è caricato");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_SUCCESS, "&aLo stand per armature '%name%' è stato caricato con successo");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_FAILED, "&cImpossibile caricare lo stand per armature. Controlla la console per maggiori dettagli!");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_LOADED, "&cQuesto stand per armature è già caricato");
        yml.addDefault(Messages.ARMOR_STANDS_LOAD_SUCCESS, "&a%loaded% stand per armature caricati con successo.");
        yml.addDefault(Messages.ARMOR_STANDS_LOAD_FAILED, "&cImpossibile caricare %failed% stand per armature.");
        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cArmorStand non valido");
        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cArmorStand '%armorstand%' non valido. Intendevi '%suggestion%'?");
        yml.addDefault(Messages.ARMOR_STAND_NOT_FOUND, "&cArmorStand non trovato!");
        yml.addDefault(Messages.WORLD_NOT_FOUND, "&cMondo non trovato!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMOR_STAND, "&aTeletrasportato a %name%");
        yml.addDefault(Messages.ARMOR_STAND_UPDATED_SUCCESS, "&aArmorStand aggiornato correttamente!");
        yml.addDefault(Messages.ARMOR_STAND_UPDATED_FAILED, "&cImpossibile aggiornare l'ArmorStand!");
        yml.addDefault(Messages.ARMOR_STAND_UNAVAILABLE, "&cL'ArmorStand non è più disponibile!");
        yml.addDefault(Messages.ARMOR_STAND_UNAVAILABLE_TITLE, "&cL'ArmorStand non è disponibile");
        yml.addDefault(Messages.ARMOR_STAND_MOVE_SUCCESS, "&aArmorStand spostato correttamente.");
        yml.addDefault(Messages.MESSAGES_NOT_LOADED, "&cSembra che %amount% ArmorStand non siano stati caricati dal generatore del mondo. Per risolvere, attiva 'auto-load-armor-stands' nella configurazione per caricarli tutti automaticamente.");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN, "&eAdvancedArmorStands ha riscontrato %amount% errore/i e avviso/i in questa sessione. Controlla il file di log per i dettagli.");
        yml.addDefault(Messages.ARMOR_STAND_SAVE_CONFIRM, "&aFallo ancora %amount% volta%plural% per salvare questo ArmorStand.");
        yml.addDefault(Messages.ARMOR_STAND_SAVED_SUCCESS, "&eArmorStand salvato come %name%!");
        yml.addDefault(Messages.ARMOR_STAND_DELETE_CONFIRM, "&cFallo ancora %amount% volta%plural% per eliminare questo ArmorStand.");
        yml.addDefault(Messages.ARMOR_STAND_DELETED_SUCCESS, "&aL'ArmorStand è stato eliminato, ma puoi ripristinarlo con il comando restore. Verrà eliminato definitivamente al riavvio del server");
        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cNon hai il permesso di usare questo comando!");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&cIl comando '%command%' non è un sottocomando valido.");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&cIl comando '%command%' non è un sottocomando valido. Intendevi '%suggestion%'?");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cPer favore, inserisci un numero di pagina valido.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cPagina non valida. Scegli una pagina tra 1 e %pages%.");
        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eClicca per usare il suggerimento");
        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Esempi:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE, "&e&lClicca per usare questo comando");
        yml.addDefault(Messages.COMMAND_LABEL, "&7Comando: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL, "&7Descrizione: &f%description%");
        yml.addDefault(Messages.CREATE_DESCRIPTION, "Crea un &7ArmorStand");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE, "&cUso: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER, "&cNumero non valido per la parte %part%. Uso: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART, "&cParte sconosciuta: %part%");
        yml.addDefault(Messages.DELETE_DESCRIPTION, "Elimina un &7ArmorStand");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS, "&aTutti gli ArmorStand eliminati correttamente");
        yml.addDefault(Messages.DELETE_SUCCESS, "&aArmorStand eliminato completamente");
        yml.addDefault(Messages.UNLINK_SUCCESS, "&aLo stand per armature è stato scollegato con successo da AdvancedArmorStands");
        yml.addDefault(Messages.UNLINK_ALL_SUCCESS, "&aTutti gli stand per armature sono stati scollegati con successo da AdvancedArmorStands");
        yml.addDefault(Messages.DEBUG_DESCRIPTION, "Mostra informazioni di debug");
        yml.addDefault(Messages.HEAD_DESCRIPTION, "Ottieni la testa di un giocatore");
        yml.addDefault(Messages.HEAD_SUCCESS, "&aTi è stata data la testa di %player%");
        yml.addDefault(Messages.LIST_DESCRIPTION, "Mostra un elenco di ArmorStand");
        yml.addDefault(Messages.LIST_EMPTY_TITLE, "&c&l     Nessun ArmorStand salvato trovato ");
        yml.addDefault(Messages.LIST_EMPTY_HINT, "&7Usa &e/as create <type> <name>&7 per creare il tuo primo ArmorStand!");
        yml.addDefault(Messages.LOAD_DESCRIPTION, "Carica un ArmorStand");
        yml.addDefault(Messages.LOAD_NO_UNLOADED, "&eNessun ArmorStand non caricato trovato.");
        yml.addDefault(Messages.LOAD_FAILED, "&cFallito: %failed%");
        yml.addDefault(Messages.MOVE_DESCRIPTION, "Sposta un ArmorStand nella tua &7posizione");
        yml.addDefault(Messages.OPTIONS_DESCRIPTION, "Apre il menu delle opzioni");
        yml.addDefault(Messages.RELOAD_DESCRIPTION, "Ricarica le configurazioni del plugin");
        yml.addDefault(Messages.RELOAD_SUCCESS, "&aConfigurazione di AdvancedArmorStand ricaricata correttamente");
        yml.addDefault(Messages.RELOAD_ERROR, "&cErrore durante il ricaricamento del plugin, controlla la console per maggiori dettagli");
        yml.addDefault(Messages.RENAME_DESCRIPTION, "Cambia il nome di un &7as");
        yml.addDefault(Messages.RENAME_SUCCESS, "&aArmorStand rinominato da '%old_name%' a '%new_name%'");
        yml.addDefault(Messages.RESTORE_DESCRIPTION, "Ripristina un ArmorStand eliminato");
        yml.addDefault(Messages.RESTORE_NOT_DELETED, "&cQuesto ArmorStand non è eliminato o è troppo tardi");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR, "&cErrore sconosciuto, controlla la console per maggiori informazioni");
        yml.addDefault(Messages.RESTORE_SUCCESS, "&aArmorStand ripristinato correttamente");
        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND, "&cQuesto ArmorStand non è sul terreno. Sei sicuro di volerti teletrasportare lì?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT, "&cUsa '/as teleport %armorstand% --force' per forzare il teletrasporto");
        yml.addDefault(Messages.TELL_DESCRIPTION, "Fai una domanda all'IA di un ArmorStand");
        yml.addDefault(Messages.TELL_AI_DISABLED, "&cQuesto ArmorStand non ha l'IA attivata");
        yml.addDefault(Messages.INVENTORY_EXIT_NAME, "&7» &cUSCITA&7 (Clic destro)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE, "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION, "&aSei entrato nella sessione di modifica, clicca sull'oggetto USCITA per tornare indietro e recuperare i tuoi oggetti");
        yml.addDefault(Messages.EDITOR_EXIT_NAME, "&7» &cUSCITA &7(Clic destro)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE, "\n&8AdvancedArmorStands Editor Item");
        yml.addDefault(Messages.HEAD_ITEM_LORE, "&eCLIC DESTRO &8» &7Ruota la testa verso destra\n&eCLIC SINISTRO &8» &7Ruota la testa verso sinistra\n&eSHIFT + CLIC DESTRO &8» &7Alza la testa\n&eSHIFT + CLIC SINISTRO &8» &7Abbassa la testa\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE, "&eCLIC DESTRO &8» &7Ruota la mano sinistra verso destra\n&eCLIC SINISTRO &8» &7Ruota la mano sinistra verso sinistra\n&eSHIFT + CLIC DESTRO &8» &7Alza il braccio sinistro\n&eSHIFT + CLIC SINISTRO &8» &7Abbassa il braccio sinistro\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE, "&eCLIC DESTRO &8» &7Ruota la mano destra verso destra\n&eCLIC SINISTRO &8» &7Ruota la mano destra verso sinistra\n&eSHIFT + CLIC DESTRO &8» &7Alza il braccio destro\n&eSHIFT + CLIC SINISTRO &8» &7Abbassa il braccio sinistro\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE, "&eCLIC DESTRO &8» &7Ruota la gamba sinistra verso destra\n&eCLIC SINISTRO &8» &7Ruota la gamba sinistra verso sinistra\n&eSHIFT + CLIC DESTRO &8» &7Alza la gamba sinistra\n&eSHIFT + CLIC SINISTRO &8» &7Abbassa la gamba sinistra\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE, "&eCLIC DESTRO &8» &7Ruota la gamba destra verso destra\n&eCLIC SINISTRO &8» &7Ruota la gamba destra verso sinistra\n&eSHIFT + CLIC DESTRO &8» &7Alza la gamba destra\n&eSHIFT + CLIC SINISTRO &8» &7Abbassa la gamba destra\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE, "&eCLIC DESTRO &8» &7Ruota verso destra\n&eCLIC SINISTRO &8» &7Ruota verso sinistra\n&eSHIFT + CLIC DESTRO (Blocco) &8» &7Alza il corpo\n&eSHIFT + CLIC SINISTRO (Blocco) &8» &7Abbassa il corpo\n&eSHIFT + CLIC DESTRO (Aria) &8» &7Sposta il corpo a destra\n&eSHIFT + CLIC SINISTRO (Aria) &8» &7Sposta il corpo a sinistra\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE, "&7Clicca per salvare la configurazione\n&7dell'ArmorStand come tipo, così potrai usarla in seguito\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK, "&cNon puoi rompere blocchi nella sessione di modifica.");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK, "&cNon puoi rompere blocchi qui.");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START, "&aScrivi in chat il nome che vuoi impostare. Per uscire scrivi 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY, "&cSei già in una sessione di impostazione nome");
        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS, "&aSei uscito correttamente dalla sessione di impostazione nome");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS, "&aNome personalizzato dell'ArmorStand impostato correttamente su '%name%'");
        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS, "&aSei uscito correttamente dalla sessione di creazione tipo");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS, "&cQuesto tipo esiste già. Scegli un altro nome oppure seleziona il tipo nel menu di salvataggio");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS, "&aTipo '%type%' creato con le proprietà di questo ArmorStand");
        yml.addDefault(Messages.CREATION_CANCELLED, "&cCreazione annullata per inattività");
        yml.addDefault(Messages.DELETION_CANCELLED, "&cEliminazione annullata per inattività.");
        yml.addDefault(Messages.ARMS_OPTION_NAME, "&eBraccia");
        yml.addDefault(Messages.ARMS_OPTION_LORE, "&7Attiva o disattiva\n&7le braccia di questo ArmorStand \n\n%status%");
        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME, "&eBase");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE, "&7Attiva o disattiva\n&7la base di questo ArmorStand \n\n%status%");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME, "&eNome personalizzato");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE, "&7Imposta un nome personalizzato\n&7per questo ArmorStand");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME, "&eAttuale&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME, "&eNome attuale&6 » &eNessuno");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME, "&aScrivi in chat il nome che vuoi impostare. Per uscire scrivi 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING, "&cSei già in una sessione di impostazione nome");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME, "&eNome personalizzato visibile");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE, "&7Attiva o disattiva\n&7il nome personalizzato di questo ArmorStand\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_NAME, "&eBagliore");
        yml.addDefault(Messages.GLOWING_OPTION_LORE, "&7Attiva o disattiva\n&7il bagliore di questo ArmorStand\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e È luminoso");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Non è luminoso");
        yml.addDefault(Messages.SMALL_OPTION_NAME, "&ePiccolo");
        yml.addDefault(Messages.SMALL_OPTION_LORE, "&7Attiva o disattiva\n&7la dimensione piccola di questo ArmorStand\n\n%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e È piccolo");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Non è piccolo");
        yml.addDefault(Messages.VISIBLE_OPTION_NAME, "&eVisibilità");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE, "&7Attiva o disattiva\n&7la visibilità di questo ArmorStand\n\n%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e È visibile");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Non è visibile");
        yml.addDefault(Messages.DELETE_TOOL_NAME, "&eElimina");
        yml.addDefault(Messages.DELETE_TOOL_LORE, "&7Elimina questo ArmorStand\n&7questa azione non può essere annullata\n\n&eClicca per eliminare");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE, "&aL'ArmorStand è stato eliminato, ma puoi ripristinarlo con il comando restore. Verrà eliminato definitivamente al riavvio del server");
        yml.addDefault(Messages.DELETE_TOOL_DELETED, "&aL'ArmorStand è stato eliminato, ma puoi ripristinarlo con il comando restore. Verrà eliminato definitivamente al riavvio del server");
        yml.addDefault(Messages.MOVE_TOOL_NAME, "&eSposta");
        yml.addDefault(Messages.MOVE_TOOL_LORE, "&7Sposta l'ArmorStand\n&7dove preferisci\n\n&7Consigliato: usa invece\n&7il comando /as move\n\n&eClicca per spostare");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING, "&cSei già nella sessione di spostamento");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE, "&aRompi il blocco su cui vuoi che si trovi l'ArmorStand");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY, "&cSei già nella sessione di spostamento");
        yml.addDefault(Messages.MOVE_TOOL_START, "&aRompi il blocco su cui vuoi che si trovi l'ArmorStand");
        yml.addDefault(Messages.TELEPORT_TOOL_NAME, "&eTeletrasporto");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE, "&7Teletrasportati\n&7nella posizione dell'ArmorStand\n\n&eClicca per teletrasportarti");
        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aPosizione accettata per questo ArmorStand.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aNuova posizione rifiutata correttamente.");

        yml.options().copyDefaults(true);
        save();
    }
}