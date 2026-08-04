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

public class Turkish extends Language {
    /*
    Made with AI
    */
    public Turkish() {
        super(AdvancedArmorStands.plugin, "tr");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "eylemler");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7Bu, bu armor stand'a\n&7tıkladığında\n&7şu komutun çalışacağı\n&7anlamına gelir: %command%\n\n&6» &eDüzenlemek için tıkla\n&6» &eSilmek için Shift + tıkla");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eBir eylem oluştur");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE, "&7Tıklandığında\n&7komut çalıştıran bir\n&7eylem oluşturmak için bunu seç\n\n&eOluşturmak için tıkla");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aAyarlamak istediğin komutu yaz ('/' olmadan). İptal etmek için 'exit' yaz.");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "eylem ayarları");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eTetikleme Türü");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Oyuncu ArmorStand ile\n&7etkileşime girdiğinde\n&7komutun nasıl tetikleneceğini\n&7seç.\n\n%trigger_list%\n\n&eDeğiştirmek için tıkla");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&eGönderen");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Komutu kimin göndereceğini\n&7seç. Bu, izinlerin ve\n&7çalıştırmanın nasıl işlediğini etkiler.\n\n%sender_list%\n\n&eDeğiştirmek için tıkla");
        yml.addDefault(Messages.AI_MENU_TITLE, "yapay zeka ayarları");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Yapay zekadan sana yardım etmesini iste");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7Düşünüyor...");
        yml.addDefault(Messages.AI_OPTION_NAME, "&eYapay Zeka Seçenekleri");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE, "&7Bu ArmorStand'ın\n&7yapay zekasıyla ilgili\n&7seçenekler.\n\n&eAçmak için tıkla");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE, "&7Yapay zekayı ArmorStand'lar için\n&7API anahtarını\n&7config'e girerek etkinleştir.\n\n&4✘ &cEtkin Değil");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Bu ArmorStand'ın yapay zekasının\n&7yanıt verirken uyacağı\n&7özel talimatlar ekle.\n\n%current%\n\n&6» &eSıfırlamak için Shift + tıkla\n&6» &eDeğiştirmek için tıkla");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7Bu ArmorStand için\n&7yapay zekayı aç veya kapat\n\n%ai_status%");
        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS, "&aHafıza ayarlama oturumundan başarıyla çıktın.");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS, "&aArmorStand'ın talimatları başarıyla güncellendi");
        yml.addDefault(Messages.AI_PREFIX, "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT, "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND, "Yapay zeka hatası: yanıtta asistan metni bulunamadı");
        yml.addDefault(Messages.AI_ERROR, "&cYapay zeka hatası: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP, "&cYapay zeka hatası: HTTP %code%, internete bağlı olduğundan emin ol");
        yml.addDefault(Messages.AI_HTTP_ERROR, "Yapay zeka hatası: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET, "Yapay zeka hatası: HTTP {code}, internete bağlı olduğundan emin ol");
        yml.addDefault(Messages.AI_PARSE_ERROR, "Yapay zeka ayrıştırma hatası: {error}");
        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Animasyon komutlarını gösterir");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER, "&6&lAdvanced &e&lArmorStands &7&lanimasyon komutları");
        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS, "&a%animation% animasyonu ArmorStand %armorstand%'a başarıyla ayarlandı");
        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION, "Bir as'ın &7animasyonlarını temizler");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS, "&a%armorstand%'ın animasyonları başarıyla temizlendi");
        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION, "&7Bir as ile &7animasyon oluştur/düzenle");
        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION, "&7Bir animasyonu tamamen kaldırır");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS, "&a'%animation%' animasyonu başarıyla kaldırıldı");
        yml.addDefault(Messages.ANIMATION_INVALID, "&cGeçersiz animasyon");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION, "&c'%animation%' geçersiz animasyon. '%suggestion%'i mi demek istedin?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME, "&cGeçersiz animasyon adı");
        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION, "&cZaten animasyonu olan bir ArmorStand'da editör açılamaz");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED, "&aDüzenleme oturumları ADVENTURE modunu desteklemediği için oyun modun geçici olarak CREATIVE'e değiştirildi. Çıktığında otomatik olarak eski haline dönecek.");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED, "&aAnimasyon oluşturma/düzenleme oturumuna başarıyla girdin");
        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aAnimasyon başarıyla kaydedildi, bu ArmorStand'a eklemek için tıkla");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "keyframe editörü");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eSAĞ TIK &7Bir keyframe ekle\n&eSHIFT + SAĞ TIK &7Keyframe menüsünü aç\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aKeyframe eklendi!");
        yml.addDefault(Messages.KEYFRAME_STEP_LORE, "&7Bu, %animation% animasyonunun\n&7%step%%suffix% adımı, sırası\n&7geldiğinde ArmorStand'ı\n&7bu konuma getirecek\n\n&eKaldırmak için tıkla");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME, "&eAralık: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE, "&7Animasyon kareleri arasındaki\n&7gecikmeyi ayarlar.\n&7Değer ne kadar düşükse\n&7animasyon o kadar hızlı olur.\n\n&6»&e Artırmak için sol tık\n&6»&e Azaltmak için sağ tık.\n&6»&e 10'ar 10'ar değiştirmek\n&6»&e için Shift'e basılı tut.\n\n&eDeğiştirmek için tıkla");
        yml.addDefault(Messages.KEYFRAME_LOOP_NAME, "&eDöngü: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE, "&7Animasyonun baştan itibaren\n&7sonsuz döngüde tekrarlanıp\n&7tekrarlanmayacağını belirler\n\n&eDeğiştirmek için tıkla");
        yml.addDefault(Messages.SAVE_MENU_TITLE, "kayıt menüsü");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE, "&aOluşturmak istediğin türün adını yaz, bu ArmorStand'ın özellikleri oraya kopyalanacak. Çıkmak için 'exit' yaz");
        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE, "&7Bunu seçmek, eski %type%\n&7config'ini az önce yaptığın\n&7güncel config ile değiştirir\n\n&eKaydetmek için tıkla");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED, "&aArmorStand'ın özellikleri '%type%'a kaydedildi");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME, "&eBir tür oluştur");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE, "&7Bu armor stand'ın\n&7özellikleriyle bir tür\n&7oluşturmak için bunu seç\n\n&eOluşturmak için tıkla");
        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "ayarlar");
        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cBu ArmorStand yüklü değil");
        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cGeçersiz ArmorStand");
        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&c'%armorstand%' geçersiz ArmorStand. '%suggestion%'ı mi demek istedin?");
        yml.addDefault(Messages.ARMORSTAND_NOT_FOUND, "&cArmorStand bulunamadı!");
        yml.addDefault(Messages.WORLD_NOT_FOUND, "&cDünya bulunamadı!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMORSTAND, "&a%name%'e ışınlandın");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_SUCCESS, "&aArmorStand başarıyla güncellendi!");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_FAILED, "&cArmorStand güncellenemedi!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE, "&cArmorStand artık mevcut değil!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE_TITLE, "&cArmorStand mevcut değil");
        yml.addDefault(Messages.ARMORSTAND_MOVE_SUCCESS, "&aArmorStand başarıyla taşındı.");
        yml.addDefault(Messages.ARMORSTANDS_NOT_LOADED, "&c%amount% ArmorStand'ın dünya oluşturucu tarafından yüklenmediği görünüyor. Bunu düzeltmek için, hepsinin otomatik yüklenmesi adına config'te 'auto-load-armor-stands'i etkinleştir.");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN, "&eAdvancedArmorStands bu oturumda %amount% hata/uyarıyla karşılaştı. Detaylar için log dosyasını kontrol et.");
        yml.addDefault(Messages.ARMORSTAND_SAVE_CONFIRM, "&aBu ArmorStand'ı kaydetmek için bunu %amount% kez daha yap.");
        yml.addDefault(Messages.ARMORSTAND_SAVED_SUCCESS, "&eArmorStand %name% olarak kaydedildi!");
        yml.addDefault(Messages.ARMORSTAND_DELETE_CONFIRM, "&cBu ArmorStand'ı silmek için bunu %amount% kez daha yap.");
        yml.addDefault(Messages.ARMORSTAND_DELETED_SUCCESS, "&aArmorStand silindi, ancak restore komutuyla geri getirebilirsin ve sunucu yeniden başladığında tamamen silinecek");
        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cBu komutu kullanma iznin yok!");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&c'%command%' komutu geçerli bir alt komut değil.");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&c'%command%' komutu geçerli bir alt komut değil. '%suggestion%'ı mi demek istedin?");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cLütfen geçerli bir sayfa numarası gir.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cGeçersiz sayfa. Lütfen 1 ile %pages% arasında bir sayfa seç.");
        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eÖnerilen komutu kullanmak için tıkla");
        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Örnekler:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE, "&e&lBu komutu kullanmak için tıkla");
        yml.addDefault(Messages.COMMAND_LABEL, "&7Komut: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL, "&7Açıklama: &f%description%");
        yml.addDefault(Messages.CREATE_DESCRIPTION, "Bir &7ArmorStand oluşturur");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE, "&cKullanım: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER, "&c%part% parçası için geçersiz sayı. Kullanım: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART, "&cBilinmeyen parça: %part%");
        yml.addDefault(Messages.DELETE_DESCRIPTION, "Bir &7ArmorStand siler");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS, "&aTüm ArmorStand'lar başarıyla silindi");
        yml.addDefault(Messages.DELETE_SUCCESS, "&aArmorStand tamamen silindi");
        yml.addDefault(Messages.DEBUG_DESCRIPTION, "Debug bilgilerini gösterir");
        yml.addDefault(Messages.HEAD_DESCRIPTION, "Bir oyuncunun kafasını al");
        yml.addDefault(Messages.HEAD_SUCCESS, "&aSana %player%'ın kafası verildi");
        yml.addDefault(Messages.LIST_DESCRIPTION, "ArmorStand'ların listesini gösterir");
        yml.addDefault(Messages.LIST_EMPTY_TITLE, "&c&l     Kayıtlı ArmorStand Bulunamadı ");
        yml.addDefault(Messages.LIST_EMPTY_HINT, "&7İlk ArmorStand'ını oluşturmak için &e/as create <type> <name>&7 komutunu kullan!");
        yml.addDefault(Messages.LOAD_DESCRIPTION, "Bir ArmorStand yükler");
        yml.addDefault(Messages.LOAD_NO_UNLOADED, "&eYüklenmemiş ArmorStand bulunamadı.");
        yml.addDefault(Messages.LOAD_FAILED, "&cBaşarısız: %failed%");
        yml.addDefault(Messages.MOVE_DESCRIPTION, "Bir ArmorStand'ı senin &7konumuna taşır");
        yml.addDefault(Messages.OPTIONS_DESCRIPTION, "Seçenekler menüsünü açar");
        yml.addDefault(Messages.RELOAD_DESCRIPTION, "Eklentinin config'lerini yeniden yükler");
        yml.addDefault(Messages.RELOAD_SUCCESS, "&aAdvancedArmorStand'ın config'i başarıyla yeniden yüklendi");
        yml.addDefault(Messages.RELOAD_ERROR, "&cEklenti yeniden yüklenirken hata oluştu, detaylar için konsolu kontrol et");
        yml.addDefault(Messages.RENAME_DESCRIPTION, "Bir &7as'ın adını değiştirir");
        yml.addDefault(Messages.RENAME_SUCCESS, "&aArmorStand '%old_name%'den '%new_name%'e yeniden adlandırıldı");
        yml.addDefault(Messages.RESTORE_DESCRIPTION, "Silinmiş bir ArmorStand'ı geri getirir");
        yml.addDefault(Messages.RESTORE_NOT_DELETED, "&cBu ArmorStand silinmemiş ya da çok geç kalındı");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR, "&cBilinmeyen hata, daha fazla bilgi için konsolu kontrol et");
        yml.addDefault(Messages.RESTORE_SUCCESS, "&aArmorStand başarıyla geri getirildi");
        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND, "&cBu ArmorStand yerde durmuyor. Yine de ona ışınlanmak istediğinden emin misin?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT, "&cZorla ışınlanmak için '/as teleport %armorstand% --force' komutunu kullan");
        yml.addDefault(Messages.TELL_DESCRIPTION, "Bir ArmorStand'ın yapay zekasına soru sor");
        yml.addDefault(Messages.TELL_AI_DISABLED, "&cBu ArmorStand'ın yapay zekası etkin değil");
        yml.addDefault(Messages.INVENTORY_EXIT_NAME, "&7» &cÇIKIŞ&7 (Sağ Tık)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE, "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION, "&aDüzenleme oturumuna girdin, geri dönmek ve eşyalarını geri almak için ÇIKIŞ eşyasına tıkla");
        yml.addDefault(Messages.EDITOR_EXIT_NAME, "&7» &cÇIKIŞ &7(Sağ Tık)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE, "\n&8AdvancedArmorStands Editor Item");
        yml.addDefault(Messages.HEAD_ITEM_LORE, "&eSAĞ TIK &8» &7Kafayı sağa döndür\n&eSOL TIK &8» &7Kafayı sola döndür\n&eSHIFT + SAĞ TIK &8» &7Kafayı yukarı kaldır\n&eSHIFT + SOL TIK &8» &7Kafayı aşağı indir\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE, "&eSAĞ TIK &8» &7Sol eli sağa döndür\n&eSOL TIK &8» &7Sol eli sola döndür\n&eSHIFT + SAĞ TIK &8» &7Sol kolu yukarı kaldır\n&eSHIFT + SOL TIK &8» &7Sol kolu aşağı indir\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE, "&eSAĞ TIK &8» &7Sağ eli sağa döndür\n&eSOL TIK &8» &7Sağ eli sola döndür\n&eSHIFT + SAĞ TIK &8» &7Sağ kolu yukarı kaldır\n&eSHIFT + SOL TIK &8» &7Sol kolu aşağı indir\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE, "&eSAĞ TIK &8» &7Sol bacağı sağa döndür\n&eSOL TIK &8» &7Sol bacağı sola döndür\n&eSHIFT + SAĞ TIK &8» &7Sol bacağı yukarı kaldır\n&eSHIFT + SOL TIK &8» &7Sol bacağı aşağı indir\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE, "&eSAĞ TIK &8» &7Sağ bacağı sağa döndür\n&eSOL TIK &8» &7Sağ bacağı sola döndür\n&eSHIFT + SAĞ TIK &8» &7Sağ bacağı yukarı kaldır\n&eSHIFT + SOL TIK &8» &7Sağ bacağı aşağı indir\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE, "&eSAĞ TIK &8» &7Sağa döndür\n&eSOL TIK &8» &7Sola döndür\n&eSHIFT + SAĞ TIK (Blok) &8» &7Vücudu yukarı kaldır\n&eSHIFT + SOL TIK (Blok) &8» &7Vücudu aşağı indir\n&eSHIFT + SAĞ TIK (Hava) &8» &7Vücudu sağa taşı\n&eSHIFT + SOL TIK (Hava) &8» &7Vücudu sola taşı\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE, "&7ArmorStand ayarını daha sonra\n&7kullanabileceğin bir tür olarak\n&7kaydetmek için tıkla\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK, "&cDüzenleme oturumunda blok kıramazsın.");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK, "&cBurada blok kıramazsın.");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START, "&aBelirlemek istediğin ismi sohbete yaz. Çıkmak için 'exit' yaz");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY, "&cZaten bir isim ayarlama oturumundasın");
        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS, "&aİsim ayarlama oturumundan başarıyla çıktın");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS, "&aArmorStand'ın özel adı başarıyla '%name%' olarak ayarlandı");
        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS, "&aTür oluşturma oturumundan başarıyla çıktın");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS, "&cBu tür zaten mevcut. Başka bir isim seç ya da kayıt menüsünden bu türü seç");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS, "&a'%type%' türü bu ArmorStand'ın özellikleriyle oluşturuldu");
        yml.addDefault(Messages.CREATION_CANCELLED, "&cHareketsizlik nedeniyle oluşturma iptal edildi");
        yml.addDefault(Messages.DELETION_CANCELLED, "&cHareketsizlik nedeniyle silme iptal edildi.");
        yml.addDefault(Messages.ARMS_OPTION_NAME, "&eKollar");
        yml.addDefault(Messages.ARMS_OPTION_LORE, "&7Bu ArmorStand'ın\n&7kollarını aç veya kapat \n\n%status%");
        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME, "&eTaban");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE, "&7Bu ArmorStand'ın\n&7tabanını aç veya kapat \n\n%status%");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME, "&eÖzel isim");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE, "&7Bu ArmorStand için\n&7özel bir isim belirle");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME, "&eŞu anki&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME, "&eŞu anki isim&6 » &eYok");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME, "&aBelirlemek istediğin ismi sohbete yaz. Çıkmak için 'exit' yaz");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING, "&cZaten bir isim ayarlama oturumundasın");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME, "&eÖzel isim görünür");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE, "&7Bu ArmorStand'ın\n&7özel isminin görünürlüğünü aç veya kapat\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_NAME, "&eParlama");
        yml.addDefault(Messages.GLOWING_OPTION_LORE, "&7Bu ArmorStand'ın\n&7parlamasını aç veya kapat\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Parlıyor");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Parlamıyor");
        yml.addDefault(Messages.SMALL_OPTION_NAME, "&eKüçük");
        yml.addDefault(Messages.SMALL_OPTION_LORE, "&7Bu ArmorStand'ın\n&7küçük boyutunu aç veya kapat\n\n%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Küçük");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Küçük Değil");
        yml.addDefault(Messages.VISIBLE_OPTION_NAME, "&eGörünürlük");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE, "&7Bu ArmorStand'ın\n&7görünürlüğünü aç veya kapat\n\n%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Görünür");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Görünür Değil");
        yml.addDefault(Messages.DELETE_TOOL_NAME, "&eSil");
        yml.addDefault(Messages.DELETE_TOOL_LORE, "&7Bu ArmorStand'ı siler\n&7bu işlem geri alınamaz\n\n&eSilmek için tıkla");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE, "&aArmorStand silindi, ancak restore komutuyla geri getirebilirsin ve sunucu yeniden başladığında tamamen silinecek");
        yml.addDefault(Messages.DELETE_TOOL_DELETED, "&aArmorStand silindi, ancak restore komutuyla geri getirebilirsin ve sunucu yeniden başladığında tamamen silinecek");
        yml.addDefault(Messages.MOVE_TOOL_NAME, "&eTaşı");
        yml.addDefault(Messages.MOVE_TOOL_LORE, "&7ArmorStand'ı istediğin\n&7yere taşır\n\n&7Önerilen: bunun yerine\n&7/as move komutunu kullan\n\n&eTaşımak için tıkla");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING, "&cZaten taşıma oturumundasın");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE, "&aArmorStand'ın üzerinde durmasını istediğin bloğu kır");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY, "&cZaten taşıma oturumundasın");
        yml.addDefault(Messages.MOVE_TOOL_START, "&aArmorStand'ın üzerinde durmasını istediğin bloğu kır");
        yml.addDefault(Messages.TELEPORT_TOOL_NAME, "&eIşınlan");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE, "&7ArmorStand'ın\n&7konumuna ışınlanır\n\n&eIşınlanmak için tıkla");
        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aBu ArmorStand için bu konum kabul edildi.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aYeni konum başarıyla reddedildi.");

        yml.options().copyDefaults(true);
        save();
    }
}