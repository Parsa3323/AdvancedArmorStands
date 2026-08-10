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

public class Spanish extends Language {
    /*
    Made with AI
    */
    public Spanish() {
        super(AdvancedArmorStands.plugin, "es");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "acciones");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7Esto significa que si\n&7haces clic en este armor\n&7stand, se ejecutará\n&7el comando: %command%\n\n&6» &eClic para editar\n&6» &eShift + clic para eliminar");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eCrear una acción");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE, "&7Selecciona esto para crear\n&7una acción que ejecute\n&7comandos al hacer clic\n\n&eClic para crear");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aEscribe el comando que quieres poner (sin '/'). Escribe 'exit' para cancelar.");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "ajustes de la acción");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eTipo de activador");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Elige cómo se activará\n&7el comando cuando\n&7el jugador interactúe con\n&7el ArmorStand.\n\n%trigger_list%\n\n&eClic para cambiar");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eRemitente");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Elige quién enviará\n&7el comando. Esto afecta a\n&7cómo funcionan los permisos y la ejecución.\n\n%sender_list%\n\n&eClic para cambiar");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_NAME, "&ePrioridad");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_LORE, "&7Cambia la prioridad\n&7de este comando. Cuando\n&7la prioridad sea mayor\n&7que la de los demás, se\n&7ejecutará primero.\n\n&6» &e%priority_number%\n\n&eHaz clic para cambiar");
        yml.addDefault(Messages.ACTIONS_SETTING_PRIORITY_ALREADY_TAKEN, "&eDos o más acciones tienen la misma prioridad, lo que puede provocar conflictos entre ellas.");
        yml.addDefault(Messages.AI_MENU_TITLE, "ajustes de ia");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Pide ayuda a la IA");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7Pensando...");
        yml.addDefault(Messages.AI_OPTION_NAME, "&eOpciones de IA");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE, "&7Opciones relacionadas con\n&7la inteligencia artificial\n&7de este ArmorStand.\n\n&eClic para abrir");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE, "&7Activa la IA para los ArmorStands\n&7poniendo tu clave de API\n&7en la configuración.\n\n&4✘ &cNo activada");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Añade instrucciones propias\n&7para que la IA de este ArmorStand\nlas siga al responder.\n\n%current%\n\n&6» &eShift + clic para restablecer\n&6» &eClic para cambiar");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7Activa o desactiva la IA\n&7de este ArmorStand\n\n%ai_status%");
        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS, "&aHas salido de la sesión de memoria correctamente.");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS, "&aInstrucciones del ArmorStand actualizadas correctamente");
        yml.addDefault(Messages.AI_PREFIX, "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT, "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND, "Error de IA: no se encontró texto del asistente en la respuesta");
        yml.addDefault(Messages.AI_ERROR, "&cError de IA: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP, "&cError de IA: HTTP %code%, asegúrate de estar conectado a internet");
        yml.addDefault(Messages.AI_HTTP_ERROR, "Error de IA: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET, "Error de IA: HTTP {code}, asegúrate de estar conectado a internet");
        yml.addDefault(Messages.AI_PARSE_ERROR, "Error al procesar la IA: {error}");
        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Muestra los comandos de animación");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER, "&6&lAdvanced &e&lArmorStands &7&lcomandos de animación");
        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS, "&aAnimación %animation% asignada al ArmorStand %armorstand%");
        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION, "Elimina las &7animaciones de un as");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS, "&aAnimaciones de %armorstand% eliminadas correctamente");
        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION, "&7Crea o edita una &7animación con un as");
        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION, "&7Elimina una animación por completo");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS, "&aAnimación '%animation%' eliminada correctamente");
        yml.addDefault(Messages.ANIMATION_INVALID, "&cAnimación no válida");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION, "&cAnimación '%animation%' no válida. ¿Quisiste decir '%suggestion%'?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME, "&cNombre de animación no válido");
        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION, "&cNo puedes abrir el editor en un ArmorStand que ya tiene animación");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED, "&aTu modo de juego ha cambiado temporalmente a CREATIVO, porque las sesiones de edición no funcionan en modo AVENTURA. Se restaurará automáticamente al salir.");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED, "&aHas entrado correctamente en la sesión de creación/edición de animación");
        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aAnimación guardada correctamente, haz clic para añadirla a este ArmorStand");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "editor de fotogramas clave");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eCLIC DERECHO &7Añade un fotograma clave\n&eSHIFT + CLIC DERECHO &7Abre el menú de fotogramas clave\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&a¡Fotograma clave añadido!");
        yml.addDefault(Messages.KEYFRAME_STEP_LORE, "&7Este es el paso %step%%suffix%\n&7de la animación %animation%, que\n&7pondrá al ArmorStand en esta posición\n&7en su turno\n\n&eClic para eliminar");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME, "&eIntervalo: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE, "&7Define el retraso\n&7entre fotogramas de la animación.\n&7Cuanto más bajo el valor,\n&7más rápida será la animación.\n\n&6»&e Clic izquierdo para aumentar\n&6»&e Clic derecho para disminuir.\n&6»&e Mantén Shift para cambiar\n&6»&e de 10 en 10.\n\n&eClic para cambiar");
        yml.addDefault(Messages.KEYFRAME_LOOP_NAME, "&eBucle: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE, "&7Define si la animación\n&7debe repetirse en bucle\n&7desde el inicio infinitamente\n\n&eClic para cambiar");
        yml.addDefault(Messages.SAVE_MENU_TITLE, "menú de guardado");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE, "&aEscribe el nombre del tipo que quieres crear (se copiarán las propiedades de este ArmorStand). Escribe 'exit' para salir");
        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE, "&7Al seleccionar esto sobrescribirás\n&7tu antigua configuración de %type% con\n&7la configuración actual que has hecho\n\n&eClic para guardar");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED, "&aPropiedades del ArmorStand guardadas en '%type%'");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME, "&eCrear un tipo");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE, "&7Selecciona esto para crear\n&7un tipo con las propiedades\n&7de este armor stand\n\n&eClic para crear");
        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "ajustes");
        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cEste ArmorStand no está cargado");
        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cArmorStand no válido");
        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cArmorStand '%armorstand%' no válido. ¿Quisiste decir '%suggestion%'?");
        yml.addDefault(Messages.ARMORSTAND_NOT_FOUND, "&c¡ArmorStand no encontrado!");
        yml.addDefault(Messages.WORLD_NOT_FOUND, "&c¡Mundo no encontrado!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMORSTAND, "&aTeletransportado a %name%");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_SUCCESS, "&a¡ArmorStand actualizado correctamente!");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_FAILED, "&c¡No se pudo actualizar el ArmorStand!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE, "&c¡El ArmorStand ya no está disponible!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE_TITLE, "&cEl ArmorStand no está disponible");
        yml.addDefault(Messages.ARMORSTAND_MOVE_SUCCESS, "&aArmorStand movido correctamente.");
        yml.addDefault(Messages.ARMORSTANDS_NOT_LOADED, "&cParece que %amount% ArmorStands no han sido cargados por el generador del mundo. Para solucionarlo, activa 'auto-load-armor-stands' en la configuración para cargarlos todos automáticamente.");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN, "&eAdvancedArmorStands ha encontrado %amount% error(es) y advertencia(s) en esta sesión. Revisa el archivo de registro para más detalles.");
        yml.addDefault(Messages.ARMORSTAND_SAVE_CONFIRM, "&aHaz esto %amount% vez%plural% más para guardar este ArmorStand.");
        yml.addDefault(Messages.ARMORSTAND_SAVED_SUCCESS, "&e¡ArmorStand guardado como %name%!");
        yml.addDefault(Messages.ARMORSTAND_DELETE_CONFIRM, "&cHaz esto %amount% vez%plural% más para eliminar este ArmorStand.");
        yml.addDefault(Messages.ARMORSTAND_DELETED_SUCCESS, "&aEl ArmorStand ha sido eliminado, pero puedes restaurarlo con el comando restore. Se eliminará por completo al reiniciar el servidor");
        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&c¡No tienes permiso para usar este comando!");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&cEl comando '%command%' no es un subcomando válido.");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&cEl comando '%command%' no es un subcomando válido. ¿Quisiste decir '%suggestion%'?");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cPor favor, introduce un número de página válido.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cPágina no válida. Elige una página entre 1 y %pages%.");
        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eClic para autocompletar");
        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Ejemplos:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE, "&e&lClic para usar este comando");
        yml.addDefault(Messages.COMMAND_LABEL, "&7Comando: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL, "&7Descripción: &f%description%");
        yml.addDefault(Messages.CREATE_DESCRIPTION, "Crea un &7ArmorStand");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE, "&cUso: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER, "&cNúmero no válido para la parte %part%. Uso: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART, "&cParte desconocida: %part%");
        yml.addDefault(Messages.DELETE_DESCRIPTION, "Elimina un &7ArmorStand");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS, "&aTodos los ArmorStands eliminados correctamente");
        yml.addDefault(Messages.DELETE_SUCCESS, "&aArmorStand eliminado por completo");
        yml.addDefault(Messages.DEBUG_DESCRIPTION, "Muestra información de depuración");
        yml.addDefault(Messages.HEAD_DESCRIPTION, "Obtén la cabeza de un jugador");
        yml.addDefault(Messages.HEAD_SUCCESS, "&aTe han dado la cabeza de %player%");
        yml.addDefault(Messages.LIST_DESCRIPTION, "Muestra una lista de ArmorStands");
        yml.addDefault(Messages.LIST_EMPTY_TITLE, "&c&l     No se encontraron ArmorStands guardados ");
        yml.addDefault(Messages.LIST_EMPTY_HINT, "&7Usa &e/as create <type> <name>&7 para crear tu primer ArmorStand!");
        yml.addDefault(Messages.LOAD_DESCRIPTION, "Carga un ArmorStand");
        yml.addDefault(Messages.LOAD_NO_UNLOADED, "&eNo se encontraron ArmorStands sin cargar.");
        yml.addDefault(Messages.LOAD_FAILED, "&cFalló: %failed%");
        yml.addDefault(Messages.MOVE_DESCRIPTION, "Mueve un ArmorStand a tu &7ubicación");
        yml.addDefault(Messages.OPTIONS_DESCRIPTION, "Abre el menú de opciones");
        yml.addDefault(Messages.RELOAD_DESCRIPTION, "Recarga la configuración del plugin");
        yml.addDefault(Messages.RELOAD_SUCCESS, "&aConfiguración de AdvancedArmorStand recargada correctamente");
        yml.addDefault(Messages.RELOAD_ERROR, "&cError al recargar el plugin, revisa la consola para más detalles");
        yml.addDefault(Messages.RENAME_DESCRIPTION, "Cambia el nombre de un &7as");
        yml.addDefault(Messages.RENAME_SUCCESS, "&aArmorStand renombrado de '%old_name%' a '%new_name%'");
        yml.addDefault(Messages.RESTORE_DESCRIPTION, "Restaura un ArmorStand eliminado");
        yml.addDefault(Messages.RESTORE_NOT_DELETED, "&cEste ArmorStand no está eliminado o ya es demasiado tarde");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR, "&cError desconocido, revisa la consola para más información");
        yml.addDefault(Messages.RESTORE_SUCCESS, "&aArmorStand restaurado correctamente");
        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND, "&cEste ArmorStand no está en el suelo. ¿Seguro que quieres teletransportarte a él?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT, "&cUsa '/as teleport %armorstand% --force' para forzar el teletransporte");
        yml.addDefault(Messages.TELL_DESCRIPTION, "Hazle una pregunta a la IA de un ArmorStand");
        yml.addDefault(Messages.TELL_AI_DISABLED, "&cEste ArmorStand no tiene la IA activada");
        yml.addDefault(Messages.INVENTORY_EXIT_NAME, "&7» &cSALIR&7 (Clic derecho)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE, "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION, "&aHas entrado en la sesión de edición. Haz clic en el objeto SALIR para volver y recuperar tus objetos");
        yml.addDefault(Messages.EDITOR_EXIT_NAME, "&7» &cSALIR &7(Clic derecho)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE, "\n&8AdvancedArmorStands Editor Item");
        yml.addDefault(Messages.HEAD_ITEM_LORE, "&eCLIC DERECHO &8» &7Gira la cabeza a la derecha\n&eCLIC IZQUIERDO &8» &7Gira la cabeza a la izquierda\n&eSHIFT + CLIC DERECHO &8» &7Sube la cabeza\n&eSHIFT + CLIC IZQUIERDO &8» &7Baja la cabeza\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE, "&eCLIC DERECHO &8» &7Gira la mano izquierda a la derecha\n&eCLIC IZQUIERDO &8» &7Gira la mano izquierda a la izquierda\n&eSHIFT + CLIC DERECHO &8» &7Sube el brazo izquierdo\n&eSHIFT + CLIC IZQUIERDO &8» &7Baja el brazo izquierdo\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE, "&eCLIC DERECHO &8» &7Gira la mano derecha a la derecha\n&eCLIC IZQUIERDO &8» &7Gira la mano derecha a la izquierda\n&eSHIFT + CLIC DERECHO &8» &7Sube el brazo derecho\n&eSHIFT + CLIC IZQUIERDO &8» &7Baja el brazo izquierdo\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE, "&eCLIC DERECHO &8» &7Gira la pierna izquierda a la derecha\n&eCLIC IZQUIERDO &8» &7Gira la pierna izquierda a la izquierda\n&eSHIFT + CLIC DERECHO &8» &7Sube la pierna izquierda\n&eSHIFT + CLIC IZQUIERDO &8» &7Baja la pierna izquierda\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE, "&eCLIC DERECHO &8» &7Gira la pierna derecha a la derecha\n&eCLIC IZQUIERDO &8» &7Gira la pierna derecha a la izquierda\n&eSHIFT + CLIC DERECHO &8» &7Sube la pierna derecha\n&eSHIFT + CLIC IZQUIERDO &8» &7Baja la pierna derecha\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE, "&eCLIC DERECHO &8» &7Gira a la derecha\n&eCLIC IZQUIERDO &8» &7Gira a la izquierda\n&eSHIFT + CLIC DERECHO (Bloque) &8» &7Sube el cuerpo\n&eSHIFT + CLIC IZQUIERDO (Bloque) &8» &7Baja el cuerpo\n&eSHIFT + CLIC DERECHO (Aire) &8» &7Mueve el cuerpo a la derecha\n&eSHIFT + CLIC IZQUIERDO (Aire) &8» &7Mueve el cuerpo a la izquierda\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE, "&7Haz clic para guardar la configuración\n&7del ArmorStand como un tipo que podrás usar después\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK, "&cNo puedes romper bloques en la sesión de edición.");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK, "&cNo puedes romper bloques aquí.");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START, "&aEscribe en el chat el nombre que quieres poner. Para salir escribe 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY, "&cYa estás en una sesión para poner un nombre");
        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS, "&aHas salido correctamente de la sesión de nombre");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS, "&aNombre del ArmorStand puesto correctamente como '%name%'");
        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS, "&aHas salido correctamente de la sesión de creación de tipo");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS, "&cEste tipo ya existe. Elige otro nombre o selecciona el tipo en el menú de guardado");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS, "&aTipo '%type%' creado con las propiedades de este ArmorStand");
        yml.addDefault(Messages.CREATION_CANCELLED, "&cCreación cancelada por inactividad");
        yml.addDefault(Messages.DELETION_CANCELLED, "&cEliminación cancelada por inactividad.");
        yml.addDefault(Messages.ARMS_OPTION_NAME, "&eBrazos");
        yml.addDefault(Messages.ARMS_OPTION_LORE, "&7Activa o desactiva\n&7los brazos de este ArmorStand \n\n%status%");
        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME, "&ePlaca base");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE, "&7Activa o desactiva\n&7la placa base de este ArmorStand \n\n%status%");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME, "&eNombre personalizado");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE, "&7Pon un nombre personalizado\n&7para este ArmorStand");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME, "&eActual&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME, "&eNombre actual&6 » &eNinguno");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME, "&aEscribe en el chat el nombre que quieres poner. Para salir escribe 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING, "&cYa estás en una sesión para poner un nombre");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME, "&eNombre personalizado visible");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE, "&7Activa o desactiva\n&7el nombre personalizado de este ArmorStand\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_NAME, "&eBrillo");
        yml.addDefault(Messages.GLOWING_OPTION_LORE, "&7Activa o desactiva\n&7el brillo de este ArmorStand\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Brilla");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c No brilla");
        yml.addDefault(Messages.SMALL_OPTION_NAME, "&ePequeño");
        yml.addDefault(Messages.SMALL_OPTION_LORE, "&7Activa o desactiva\n&7el tamaño pequeño de este ArmorStand\n\n%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Es pequeño");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c No es pequeño");
        yml.addDefault(Messages.VISIBLE_OPTION_NAME, "&eVisibilidad");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE, "&7Activa o desactiva\n&7la visibilidad de este ArmorStand\n\n%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Es visible");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c No es visible");
        yml.addDefault(Messages.DELETE_TOOL_NAME, "&eEliminar");
        yml.addDefault(Messages.DELETE_TOOL_LORE, "&7Elimina este ArmorStand\n&7esta acción no se puede deshacer\n\n&eClic para eliminar");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE, "&aEl ArmorStand ha sido eliminado, pero puedes restaurarlo con el comando restore. Se eliminará por completo al reiniciar el servidor");
        yml.addDefault(Messages.DELETE_TOOL_DELETED, "&aEl ArmorStand ha sido eliminado, pero puedes restaurarlo con el comando restore. Se eliminará por completo al reiniciar el servidor");
        yml.addDefault(Messages.MOVE_TOOL_NAME, "&eMover");
        yml.addDefault(Messages.MOVE_TOOL_LORE, "&7Mueve el ArmorStand\n&7a donde quieras\n\n&7Recomendado: usa el\n&7comando /as move en su lugar\n\n&eClic para mover");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING, "&cYa estás en la sesión de movimiento");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE, "&aRompe el bloque sobre el que quieres que esté el ArmorStand");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY, "&cYa estás en la sesión de movimiento");
        yml.addDefault(Messages.MOVE_TOOL_START, "&aRompe el bloque sobre el que quieres que esté el ArmorStand");
        yml.addDefault(Messages.TELEPORT_TOOL_NAME, "&eTeletransportar");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE, "&7Teletranspórtate a\n&7la ubicación del ArmorStand\n\n&eClic para teletransportarte");
        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aPosición aceptada para este ArmorStand.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aNueva posición rechazada correctamente.");

        yml.options().copyDefaults(true);
        save();
    }
}