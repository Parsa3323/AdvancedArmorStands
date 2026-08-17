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

public class Russian extends Language {
    /*
    Made with AI
    */
    public Russian() {
        super(AdvancedArmorStands.plugin, "ru");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "действия");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7Это значит, что если\n&7ты кликнешь по этому армор\n&7стенду, выполнится\n&7команда: %command%\n\n&6» &eНажми, чтобы изменить\n&6» &eShift + клик, чтобы удалить");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eСоздать действие");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE, "&7Выбери это, чтобы создать\n&7действие, которое выполняет\n&7команды по клику\n\n&eНажми, чтобы создать");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aНапиши команду, которую хочешь задать (без '/'). Напиши 'exit', чтобы отменить.");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "настройки действия");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eТип триггера");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Выбери, как будет\n&7срабатывать команда, когда\n&7игрок взаимодействует\n&7с ArmorStand.\n\n%trigger_list%\n\n&eНажми, чтобы изменить");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eОтправитель");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Выбери, от чьего имени\n&7будет отправляться команда. Это влияет\n&7на права и то, как она выполняется.\n\n%sender_list%\n\n&eНажми, чтобы изменить");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_NAME, "&eПриоритет");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_LORE, "&7Измените приоритет\n&7этой команды. Если\n&7приоритет выше, чем у\n&7остальных, она будет\n&7выполнена раньше.\n\n&6» &e%priority_number%\n\n&eНажмите, чтобы изменить");
        yml.addDefault(Messages.ACTIONS_SETTING_PRIORITY_ALREADY_TAKEN, "&eДва или более действия имеют одинаковый приоритет, что может привести к конфликтам между ними.");
        yml.addDefault(Messages.AI_MENU_TITLE, "настройки ии");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Попроси ИИ помочь тебе");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7Думает...");
        yml.addDefault(Messages.AI_OPTION_NAME, "&eНастройки ИИ");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE, "&7Настройки, связанные с\n&7искусственным интеллектом\n&7этого ArmorStand.\n\n&eНажми, чтобы открыть");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE, "&7Включи ИИ для ArmorStand'ов,\n&7указав свой API-ключ\n&7в конфиге.\n\n&4✘ &cНе включено");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Добавь свои инструкции,\n&7которым ИИ этого ArmorStand\nбудет следовать при ответе.\n\n%current%\n\n&6» &eShift + клик, чтобы сбросить\n&6» &eНажми, чтобы изменить");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7Включи или выключи ИИ\n&7для этого ArmorStand\n\n%ai_status%");
        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS, "&aТы успешно вышел из сессии настройки памяти.");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS, "&aИнструкции ArmorStand успешно обновлены");
        yml.addDefault(Messages.AI_PREFIX, "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT, "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND, "Ошибка ИИ: не удалось найти текст ответа ассистента");
        yml.addDefault(Messages.AI_ERROR, "&cОшибка ИИ: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP, "&cОшибка ИИ: HTTP %code%, убедись, что ты подключён к интернету");
        yml.addDefault(Messages.AI_HTTP_ERROR, "Ошибка ИИ: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET, "Ошибка ИИ: HTTP {code}, убедись, что ты подключён к интернету");
        yml.addDefault(Messages.AI_PARSE_ERROR, "Ошибка обработки ответа ИИ: {error}");
        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Показывает команды анимации");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER, "&6&lAdvanced &e&lArmorStands &7&lкоманды анимации");
        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS, "&aАнимация %animation% успешно назначена ArmorStand %armorstand%");
        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION, "Удаляет &7анимации у as");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS, "&aАнимации %armorstand% успешно удалены");
        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION, "&7Создай или отредактируй &7анимацию через as");
        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION, "&7Полностью удаляет анимацию");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS, "&aАнимация '%animation%' успешно удалена");
        yml.addDefault(Messages.ANIMATION_INVALID, "&cНеверная анимация");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION, "&cНеверная анимация '%animation%'. Может, ты имел в виду '%suggestion%'?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME, "&cНеверное название анимации");
        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION, "&cНельзя открыть редактор у ArmorStand, у которого уже есть анимация");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED, "&aТвой режим игры временно переключён на CREATIVE, потому что сессии редактирования не поддерживают режим ADVENTURE. Он автоматически вернётся, когда ты выйдешь.");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED, "&aТы успешно вошёл в сессию создания/редактирования анимации");
        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aАнимация успешно сохранена, нажми, чтобы добавить её этому ArmorStand");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "редактор ключевых кадров");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eПКМ &7Добавить ключевой кадр\n&eSHIFT + ПКМ &7Открыть меню ключевых кадров\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aКлючевой кадр добавлен!");
        yml.addDefault(Messages.KEYFRAME_STEP_LORE, "&7Это %step%%suffix% шаг\n&7анимации %animation%, который\n&7в свою очередь поставит ArmorStand\n&7в это положение\n\n&eНажми, чтобы удалить");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME, "&eИнтервал: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE, "&7Задаёт задержку\n&7между кадрами анимации.\n&7Чем меньше значение,\n&7тем быстрее анимация.\n\n&6»&e ЛКМ, чтобы увеличить\n&6»&e ПКМ, чтобы уменьшить.\n&6»&e Держи Shift, чтобы менять\n&6»&e шагами по 10.\n\n&eНажми, чтобы изменить");
        yml.addDefault(Messages.KEYFRAME_LOOP_NAME, "&eЗацикливание: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE, "&7Определяет, должна ли\n&7анимация бесконечно\n&7повторяться с начала\n\n&eНажми, чтобы изменить");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_DISPLAY, "&eРеалистичная: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_LORE, "&7Реалистичная анимация плавно интерполирует\n&7между этапами анимации, вместо мгновенного\n&7перехода от одной позы к другой.\n\n&7Предупреждение: может вызвать лаги\n\n&eНажмите, чтобы изменить");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_DISPLAY, "&eРеалистичные кадры: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_LORE, "&7Количество созданных кадров\n&7во время реалистичной анимации\n\n&6»&e ЛКМ для увеличения\n&6»&e ПКМ для уменьшения.\n&6»&e Удерживайте Shift, чтобы изменять\n&6»&e с шагом 10.\n\n&eНажмите, чтобы изменить");
        yml.addDefault(Messages.SAVE_MENU_TITLE, "меню сохранения");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE, "&aНапиши название типа, который хочешь создать (в него скопируются свойства этого ArmorStand). Напиши 'exit', чтобы выйти");
        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE, "&7Выбрав это, ты перезапишешь\n&7старый конфиг %type% текущим,\n&7который ты только что настроил\n\n&eНажми, чтобы сохранить");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED, "&aСвойства ArmorStand сохранены в '%type%'");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME, "&eСоздать тип");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE, "&7Выбери это, чтобы создать\n&7тип со свойствами\n&7этого армор стенда\n\n&eНажми, чтобы создать");
        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "настройки");
        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cЭтот ArmorStand не загружен");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_SUCCESS, "&aСтойка для брони '%name%' успешно загружена");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_FAILED, "&cНе удалось загрузить стойку для брони. Проверьте консоль для получения дополнительной информации!");
        yml.addDefault(Messages.ARMOR_STAND_LOAD_LOADED, "&cЭта стойка для брони уже загружена");
        yml.addDefault(Messages.ARMOR_STANDS_LOAD_SUCCESS, "&aУспешно загружено стоек для брони: %loaded%.");
        yml.addDefault(Messages.ARMOR_STANDS_LOAD_FAILED, "&cНе удалось загрузить стоек для брони: %failed%.");
        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cНеверный ArmorStand");
        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cНеверный ArmorStand '%armorstand%'. Может, ты имел в виду '%suggestion%'?");
        yml.addDefault(Messages.ARMORSTAND_NOT_FOUND, "&cArmorStand не найден!");
        yml.addDefault(Messages.WORLD_NOT_FOUND, "&cМир не найден!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMORSTAND, "&aТы телепортирован к %name%");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_SUCCESS, "&aArmorStand успешно обновлён!");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_FAILED, "&cНе удалось обновить ArmorStand!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE, "&cArmorStand больше недоступен!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE_TITLE, "&cArmorStand недоступен");
        yml.addDefault(Messages.ARMORSTAND_MOVE_SUCCESS, "&aArmorStand успешно перемещён.");
        yml.addDefault(Messages.MESSAGES_NOT_LOADED, "&cПохоже, %amount% ArmorStand(ов) не были загружены генератором мира. Чтобы исправить это, включи 'auto-load-armor-stands' в конфиге, чтобы все ArmorStand'ы загружались автоматически.");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN, "&eAdvancedArmorStands столкнулся с %amount% ошибкой/предупреждением за эту сессию. Проверь лог-файл для подробностей.");
        yml.addDefault(Messages.ARMORSTAND_SAVE_CONFIRM, "&aСделай это ещё %amount% раз%plural%, чтобы сохранить этот ArmorStand.");
        yml.addDefault(Messages.ARMORSTAND_SAVED_SUCCESS, "&eArmorStand сохранён как %name%!");
        yml.addDefault(Messages.ARMORSTAND_DELETE_CONFIRM, "&cСделай это ещё %amount% раз%plural%, чтобы удалить этот ArmorStand.");
        yml.addDefault(Messages.ARMORSTAND_DELETED_SUCCESS, "&aArmorStand удалён, но его можно восстановить командой restore. Полностью он удалится после перезапуска сервера");
        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cУ тебя нет прав на использование этой команды!");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&cКоманда '%command%' не является допустимой подкомандой.");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&cКоманда '%command%' не является допустимой подкомандой. Может, ты имел в виду '%suggestion%'?");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cПожалуйста, введи правильный номер страницы.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cНеверная страница. Выбери страницу от 1 до %pages%.");
        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eНажми, чтобы подставить");
        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Примеры:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE, "&e&lНажми, чтобы использовать эту команду");
        yml.addDefault(Messages.COMMAND_LABEL, "&7Команда: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL, "&7Описание: &f%description%");
        yml.addDefault(Messages.CREATE_DESCRIPTION, "Создаёт &7ArmorStand");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE, "&cИспользование: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER, "&cНеверное число для части %part%. Использование: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART, "&cНеизвестная часть: %part%");
        yml.addDefault(Messages.DELETE_DESCRIPTION, "Удаляет &7ArmorStand");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS, "&aВсе ArmorStand'ы успешно удалены");
        yml.addDefault(Messages.DELETE_SUCCESS, "&aArmorStand полностью удалён");
        yml.addDefault(Messages.UNLINK_SUCCESS, "&aСтойка для брони успешно отвязана от AdvancedArmorStands");
        yml.addDefault(Messages.UNLINK_ALL_SUCCESS, "&aВсе стойки для брони успешно отвязаны от AdvancedArmorStands");
        yml.addDefault(Messages.DEBUG_DESCRIPTION, "Показывает отладочную информацию");
        yml.addDefault(Messages.HEAD_DESCRIPTION, "Получи голову игрока");
        yml.addDefault(Messages.HEAD_SUCCESS, "&aТебе выдана голова %player%");
        yml.addDefault(Messages.LIST_DESCRIPTION, "Показывает список ArmorStand'ов");
        yml.addDefault(Messages.LIST_EMPTY_TITLE, "&c&l     Сохранённых ArmorStand'ов не найдено ");
        yml.addDefault(Messages.LIST_EMPTY_HINT, "&7Используй &e/as create <type> <name>&7, чтобы создать свой первый ArmorStand!");
        yml.addDefault(Messages.LOAD_DESCRIPTION, "Загружает ArmorStand");
        yml.addDefault(Messages.LOAD_NO_UNLOADED, "&eНезагруженных ArmorStand'ов не найдено.");
        yml.addDefault(Messages.LOAD_FAILED, "&cНе удалось: %failed%");
        yml.addDefault(Messages.MOVE_DESCRIPTION, "Перемещает ArmorStand к твоему &7местоположению");
        yml.addDefault(Messages.OPTIONS_DESCRIPTION, "Открывает меню настроек");
        yml.addDefault(Messages.RELOAD_DESCRIPTION, "Перезагружает конфиги плагина");
        yml.addDefault(Messages.RELOAD_SUCCESS, "&aКонфиг AdvancedArmorStand успешно перезагружен");
        yml.addDefault(Messages.RELOAD_ERROR, "&cОшибка при перезагрузке плагина, проверь консоль для подробностей");
        yml.addDefault(Messages.RENAME_DESCRIPTION, "Меняет название &7as");
        yml.addDefault(Messages.RENAME_SUCCESS, "&aArmorStand переименован с '%old_name%' на '%new_name%'");
        yml.addDefault(Messages.RESTORE_DESCRIPTION, "Восстанавливает удалённый ArmorStand");
        yml.addDefault(Messages.RESTORE_NOT_DELETED, "&cЭтот ArmorStand не удалён или уже слишком поздно");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR, "&cНеизвестная ошибка, проверь консоль для подробностей");
        yml.addDefault(Messages.RESTORE_SUCCESS, "&aArmorStand успешно восстановлен");
        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND, "&cЭтот ArmorStand не стоит на земле. Точно хочешь телепортироваться к нему?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT, "&cИспользуй '/as teleport %armorstand% --force', чтобы телепортироваться принудительно");
        yml.addDefault(Messages.TELL_DESCRIPTION, "Задай вопрос ИИ ArmorStand'а");
        yml.addDefault(Messages.TELL_AI_DISABLED, "&cУ этого ArmorStand не включён ИИ");
        yml.addDefault(Messages.INVENTORY_EXIT_NAME, "&7» &cВЫХОД&7 (ПКМ)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE, "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION, "&aТы вошёл в сессию редактирования, нажми на предмет ВЫХОД, чтобы вернуться и получить обратно свои вещи");
        yml.addDefault(Messages.EDITOR_EXIT_NAME, "&7» &cВЫХОД &7(ПКМ)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE, "\n&8AdvancedArmorStands Editor Item");
        yml.addDefault(Messages.HEAD_ITEM_LORE, "&eПКМ &8» &7Повернуть голову вправо\n&eЛКМ &8» &7Повернуть голову влево\n&eSHIFT + ПКМ &8» &7Поднять голову\n&eSHIFT + ЛКМ &8» &7Опустить голову\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE, "&eПКМ &8» &7Повернуть левую руку вправо\n&eЛКМ &8» &7Повернуть левую руку влево\n&eSHIFT + ПКМ &8» &7Поднять левую руку\n&eSHIFT + ЛКМ &8» &7Опустить левую руку\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE, "&eПКМ &8» &7Повернуть правую руку вправо\n&eЛКМ &8» &7Повернуть правую руку влево\n&eSHIFT + ПКМ &8» &7Поднять правую руку\n&eSHIFT + ЛКМ &8» &7Опустить левую руку\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE, "&eПКМ &8» &7Повернуть левую ногу вправо\n&eЛКМ &8» &7Повернуть левую ногу влево\n&eSHIFT + ПКМ &8» &7Поднять левую ногу\n&eSHIFT + ЛКМ &8» &7Опустить левую ногу\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE, "&eПКМ &8» &7Повернуть правую ногу вправо\n&eЛКМ &8» &7Повернуть правую ногу влево\n&eSHIFT + ПКМ &8» &7Поднять правую ногу\n&eSHIFT + ЛКМ &8» &7Опустить правую ногу\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE, "&eПКМ &8» &7Повернуть вправо\n&eЛКМ &8» &7Повернуть влево\n&eSHIFT + ПКМ (Блок) &8» &7Поднять тело\n&eSHIFT + ЛКМ (Блок) &8» &7Опустить тело\n&eSHIFT + ПКМ (Воздух) &8» &7Сдвинуть тело вправо\n&eSHIFT + ЛКМ (Воздух) &8» &7Сдвинуть тело влево\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE, "&7Нажми, чтобы сохранить настройки\n&7ArmorStand как тип, который сможешь использовать позже\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK, "&cВ сессии редактирования нельзя ломать блоки.");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK, "&cЗдесь нельзя ломать блоки.");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START, "&aНапиши в чат имя, которое хочешь задать. Чтобы выйти, напиши 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY, "&cТы уже находишься в сессии установки имени");
        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS, "&aТы успешно вышел из сессии установки имени");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS, "&aИмя ArmorStand успешно установлено на '%name%'");
        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS, "&aТы успешно вышел из сессии создания типа");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS, "&cЭтот тип уже существует. Выбери другое название или выбери тип в меню сохранения");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS, "&aТип '%type%' создан со свойствами этого ArmorStand");
        yml.addDefault(Messages.CREATION_CANCELLED, "&cСоздание отменено из-за бездействия");
        yml.addDefault(Messages.DELETION_CANCELLED, "&cУдаление отменено из-за бездействия.");
        yml.addDefault(Messages.ARMS_OPTION_NAME, "&eРуки");
        yml.addDefault(Messages.ARMS_OPTION_LORE, "&7Включи или выключи\n&7руки у этого ArmorStand \n\n%status%");
        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME, "&eПодставка");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE, "&7Включи или выключи\n&7подставку у этого ArmorStand \n\n%status%");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME, "&eСвоё имя");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE, "&7Задай своё имя\n&7для этого ArmorStand");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME, "&eТекущее&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME, "&eТекущее имя&6 » &eНет");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME, "&aНапиши в чат имя, которое хочешь задать. Чтобы выйти, напиши 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING, "&cТы уже находишься в сессии установки имени");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME, "&eВидимость своего имени");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE, "&7Включи или выключи\n&7видимость имени у этого ArmorStand\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_NAME, "&eСвечение");
        yml.addDefault(Messages.GLOWING_OPTION_LORE, "&7Включи или выключи\n&7свечение этого ArmorStand\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Светится");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Не светится");
        yml.addDefault(Messages.SMALL_OPTION_NAME, "&eМаленький");
        yml.addDefault(Messages.SMALL_OPTION_LORE, "&7Включи или выключи\n&7маленький размер у этого ArmorStand\n\n%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Маленький");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Не маленький");
        yml.addDefault(Messages.VISIBLE_OPTION_NAME, "&eВидимость");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE, "&7Включи или выключи\n&7видимость этого ArmorStand\n\n%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Виден");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Не виден");
        yml.addDefault(Messages.DELETE_TOOL_NAME, "&eУдалить");
        yml.addDefault(Messages.DELETE_TOOL_LORE, "&7Удаляет этот ArmorStand\n&7это действие нельзя отменить\n\n&eНажми, чтобы удалить");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE, "&aArmorStand удалён, но его можно восстановить командой restore. Полностью он удалится после перезапуска сервера");
        yml.addDefault(Messages.DELETE_TOOL_DELETED, "&aArmorStand удалён, но его можно восстановить командой restore. Полностью он удалится после перезапуска сервера");
        yml.addDefault(Messages.MOVE_TOOL_NAME, "&eПереместить");
        yml.addDefault(Messages.MOVE_TOOL_LORE, "&7Перемещает ArmorStand\n&7туда, куда ты хочешь\n\n&7Рекомендуем вместо этого\n&7использовать команду /as move\n\n&eНажми, чтобы переместить");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING, "&cТы уже находишься в сессии перемещения");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE, "&aСломай блок, на котором должен стоять ArmorStand");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY, "&cТы уже находишься в сессии перемещения");
        yml.addDefault(Messages.MOVE_TOOL_START, "&aСломай блок, на котором должен стоять ArmorStand");
        yml.addDefault(Messages.TELEPORT_TOOL_NAME, "&eТелепорт");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE, "&7Телепортирует к\n&7местоположению ArmorStand\n\n&eНажми, чтобы телепортироваться");
        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aЭта позиция принята для этого ArmorStand.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aНовая позиция успешно отклонена.");

        yml.options().copyDefaults(true);
        save();
    }
}