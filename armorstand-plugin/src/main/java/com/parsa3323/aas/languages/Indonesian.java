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

public class Indonesian extends Language {
    /*
    Made with AI
    */
    public Indonesian() {
        super(AdvancedArmorStands.plugin, "id");
        YamlConfiguration yml = getYml();

        yml.addDefault(Messages.MENU_FORMAT, "&7as &8» &7%section%");
        yml.addDefault(Messages.ACTIONS_MENU_TITLE, "aksi");
        yml.addDefault(Messages.ACTIONS_MENU_ITEM_LORE, "&7Ini artinya kalau kamu\n&7klik armor stand ini,\n&7perintah berikut\n&7akan dijalankan: %command%\n\n&6» &eKlik untuk edit\n&6» &eShift + klik untuk hapus");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_NAME, "&eBuat sebuah aksi");
        yml.addDefault(Messages.ACTIONS_MENU_CREATE_LORE, "&7Pilih ini untuk membuat\n&7aksi yang menjalankan\n&7perintah saat diklik\n\n&eKlik untuk membuat");
        yml.addDefault(Messages.ACTIONS_CREATION_MESSAGE, "&aKetik perintah yang mau kamu atur (tanpa '/'). Ketik 'exit' untuk membatalkan.");
        yml.addDefault(Messages.ACTIONS_SETTINGS_MENU_TITLE, "pengaturan aksi");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_NAME, "&eJenis Pemicu");
        yml.addDefault(Messages.ACTIONS_SETTINGS_TRIGGER_LORE, "&7Pilih bagaimana perintah\n&7akan dipicu saat\n&7pemain berinteraksi dengan\n&7ArmorStand ini.\n\n%trigger_list%\n\n&eKlik untuk mengubah");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_NAME, "&ePengirim");
        yml.addDefault(Messages.ACTIONS_SETTINGS_SENDER_LORE, "&7Pilih siapa yang akan mengirim\n&7perintah ini. Ini memengaruhi\n&7cara kerja izin dan eksekusinya.\n\n%sender_list%\n\n&eKlik untuk mengubah");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_NAME, "&ePrioritas");
        yml.addDefault(Messages.ACTIONS_SETTINGS_PRIORITY_LORE, "&7Ubah prioritas\n&7perintah ini. Jika\n&7prioritasnya lebih tinggi\n&7dari yang lain, perintah ini\n&7akan dijalankan lebih dulu.\n\n&6» &e%priority_number%\n\n&eKlik untuk mengubah");
        yml.addDefault(Messages.ACTIONS_SETTING_PRIORITY_ALREADY_TAKEN, "&eDua atau lebih aksi memiliki prioritas yang sama, yang dapat menyebabkan mereka saling bertentangan.");
        yml.addDefault(Messages.AI_MENU_TITLE, "pengaturan ai");
        yml.addDefault(Messages.AI_COMMAND_DESCRIPTION, "Minta bantuan AI");
        yml.addDefault(Messages.AI_COMMAND_THINKING, "&7Sedang berpikir...");
        yml.addDefault(Messages.AI_OPTION_NAME, "&eOpsi AI");
        yml.addDefault(Messages.AI_OPTION_ENABLED_LORE, "&7Opsi terkait\n&7kecerdasan buatan\n&7ArmorStand ini.\n\n&eKlik untuk membuka");
        yml.addDefault(Messages.AI_OPTION_DISABLED_LORE, "&7Aktifkan AI untuk ArmorStand\n&7dengan memasukkan API key kamu\n&7di config.\n\n&4✘ &cBelum Aktif");
        yml.addDefault(Messages.AI_SETTINGS_MEMORY_LORE, "&7Tambahkan instruksi khusus\n&7yang akan diikuti AI\nArmorStand ini saat membalas.\n\n%current%\n\n&6» &eShift + klik untuk reset\n&6» &eKlik untuk mengubah");
        yml.addDefault(Messages.AI_SETTINGS_TOGGLE_LORE, "&7Aktifkan atau matikan AI\n&7untuk ArmorStand ini\n\n%ai_status%");
        yml.addDefault(Messages.AI_MEMORY_EXIT_SUCCESS, "&aKamu berhasil keluar dari sesi pengaturan memori.");
        yml.addDefault(Messages.AI_MEMORY_UPDATE_SUCCESS, "&aInstruksi ArmorStand berhasil diperbarui");
        yml.addDefault(Messages.AI_PREFIX, "&7[&6»&7] &6");
        yml.addDefault(Messages.AI_RESPONSE_FORMAT, "&7[&6»&7] &6%response%");
        yml.addDefault(Messages.AI_RESPONSE_NOT_FOUND, "Error AI: teks asisten tidak ditemukan di respons");
        yml.addDefault(Messages.AI_ERROR, "&cError AI: %error%");
        yml.addDefault(Messages.AI_ERROR_HTTP, "&cError AI: HTTP %code%, pastikan kamu terhubung ke internet");
        yml.addDefault(Messages.AI_HTTP_ERROR, "Error AI: HTTP {code}");
        yml.addDefault(Messages.AI_HTTP_ERROR_WITH_INTERNET, "Error AI: HTTP {code}, pastikan kamu terhubung ke internet");
        yml.addDefault(Messages.AI_PARSE_ERROR, "Error saat memproses respons AI: {error}");
        yml.addDefault(Messages.ANIMATION_COMMAND_DESCRIPTION, "Menampilkan perintah animasi");
        yml.addDefault(Messages.ANIMATION_COMMAND_HEADER, "&6&lAdvanced &e&lArmorStands &7&lperintah animasi");
        yml.addDefault(Messages.ANIMATION_ADD_SUCCESS, "&aAnimasi %animation% berhasil diterapkan ke ArmorStand %armorstand%");
        yml.addDefault(Messages.ANIMATION_CLEAR_DESCRIPTION, "Menghapus &7animasi milik sebuah as");
        yml.addDefault(Messages.ANIMATION_CLEAR_SUCCESS, "&aAnimasi milik %armorstand% berhasil dihapus");
        yml.addDefault(Messages.ANIMATION_CREATE_DESCRIPTION, "&7Buat/edit &7animasi lewat sebuah as");
        yml.addDefault(Messages.ANIMATION_REMOVE_DESCRIPTION, "&7Menghapus animasi sepenuhnya");
        yml.addDefault(Messages.ANIMATION_REMOVE_SUCCESS, "&aAnimasi '%animation%' berhasil dihapus");
        yml.addDefault(Messages.ANIMATION_INVALID, "&cAnimasi tidak valid");
        yml.addDefault(Messages.ANIMATION_INVALID_WITH_SUGGESTION, "&cAnimasi '%animation%' tidak valid. Maksud kamu '%suggestion%'?");
        yml.addDefault(Messages.ANIMATION_INVALID_NAME, "&cNama animasi tidak valid");
        yml.addDefault(Messages.ANIMATION_EDITOR_ALREADY_HAS_ANIMATION, "&cTidak bisa membuka editor di ArmorStand yang sudah punya animasi");
        yml.addDefault(Messages.ANIMATION_EDITOR_GAMEMODE_CHANGED, "&aGameMode kamu untuk sementara diubah ke CREATIVE, karena sesi edit tidak mendukung mode ADVENTURE. Ini akan kembali otomatis saat kamu keluar.");
        yml.addDefault(Messages.ANIMATION_EDITOR_ENTERED, "&aKamu berhasil masuk ke sesi pembuatan/edit animasi");
        yml.addDefault(Messages.ANIMATIONS_MENU_SUCCESSFUL_CREATION, "&aAnimasi berhasil disimpan, klik untuk menambahkannya ke ArmorStand ini");
        yml.addDefault(Messages.KEYFRAME_MENU_TITLE, "editor keyframe");
        yml.addDefault(Messages.KEYFRAME_OPTION_LORE, "&eKLIK KANAN &7Tambah keyframe\n&eSHIFT + KLIK KANAN &7Buka menu keyframe\n\n&8&lAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.KEYFRAME_CREATED, "&aKeyframe ditambahkan!");
        yml.addDefault(Messages.KEYFRAME_STEP_LORE, "&7Ini adalah langkah %step%%suffix%\n&7dari animasi %animation%, yang\n&7akan menempatkan ArmorStand di posisi\n&7ini saat gilirannya\n\n&eKlik untuk menghapus");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_NAME, "&eInterval: %interval%");
        yml.addDefault(Messages.KEYFRAME_INTERVAL_LORE, "&7Mengatur jeda\n&7antar frame animasi.\n&7Semakin kecil nilainya,\n&7semakin cepat animasinya.\n\n&6»&e Klik kiri untuk menambah\n&6»&e Klik kanan untuk mengurangi.\n&6»&e Tahan Shift untuk mengubah\n&6»&e per kelipatan 10.\n\n&eKlik untuk mengubah");
        yml.addDefault(Messages.KEYFRAME_LOOP_NAME, "&ePengulangan: %loop%");
        yml.addDefault(Messages.KEYFRAME_LOOP_LORE, "&7Menentukan apakah animasi\n&7akan diulang terus-menerus\n&7dari awal\n\n&eKlik untuk mengubah");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_DISPLAY, "&eRealistis: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_NAME_LORE, "&7Animasi realistis melakukan interpolasi secara halus\n&7di antara langkah-langkah animasi, bukan langsung\n&7berpindah dari satu pose ke pose lainnya.\n\n&7Peringatan: dapat menyebabkan lag\n\n&eKlik untuk mengubah");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_DISPLAY, "&eFrame realistis: %value%");
        yml.addDefault(Messages.KEYFRAME_REALISTIC_FRAMES_LORE, "&7Jumlah frame yang dibuat\n&7selama animasi realistis\n\n&6»&e Klik kiri untuk menambah\n&6»&e Klik kanan untuk mengurangi.\n&6»&e Tahan Shift untuk mengubah\n&6»&e dengan kelipatan 10.\n\n&eKlik untuk mengubah");
        yml.addDefault(Messages.SAVE_MENU_TITLE, "menu simpan");
        yml.addDefault(Messages.SAVE_MENU_CREATE_MESSAGE, "&aKetik nama tipe yang mau kamu buat, properti ArmorStand ini akan disalin ke sana. Ketik 'exit' untuk keluar");
        yml.addDefault(Messages.SAVE_MENU_TYPE_LORE, "&7Memilih ini akan menimpa\n&7config lama %type% dengan\n&7config yang baru saja kamu buat\n\n&eKlik untuk menyimpan");
        yml.addDefault(Messages.SAVE_MENU_TYPE_SAVED, "&aProperti ArmorStand disimpan ke '%type%'");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_NAME, "&eBuat sebuah tipe");
        yml.addDefault(Messages.SAVE_MENU_CREATE_TYPE_LORE, "&7Pilih ini untuk membuat\n&7tipe dengan properti\n&7armor stand ini\n\n&eKlik untuk membuat");
        yml.addDefault(Messages.ARMOR_STAND_MENU_TITLE, "pengaturan");
        yml.addDefault(Messages.ARMOR_STAND_NOT_LOADED, "&cArmorStand ini belum dimuat");
        yml.addDefault(Messages.ARMOR_STAND_INVALID, "&cArmorStand tidak valid");
        yml.addDefault(Messages.ARMOR_STAND_INVALID_WITH_SUGGESTION, "&cArmorStand '%armorstand%' tidak valid. Maksud kamu '%suggestion%'?");
        yml.addDefault(Messages.ARMORSTAND_NOT_FOUND, "&cArmorStand tidak ditemukan!");
        yml.addDefault(Messages.WORLD_NOT_FOUND, "&cWorld tidak ditemukan!");
        yml.addDefault(Messages.TELEPORTED_TO_ARMORSTAND, "&aBerhasil teleport ke %name%");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_SUCCESS, "&aArmorStand berhasil diperbarui!");
        yml.addDefault(Messages.ARMORSTAND_UPDATED_FAILED, "&cGagal memperbarui ArmorStand!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE, "&cArmorStand ini sudah tidak ada!");
        yml.addDefault(Messages.ARMORSTAND_UNAVAILABLE_TITLE, "&cArmorStand tidak tersedia");
        yml.addDefault(Messages.ARMORSTAND_MOVE_SUCCESS, "&aArmorStand berhasil dipindahkan.");
        yml.addDefault(Messages.ARMORSTANDS_NOT_LOADED, "&cSepertinya %amount% ArmorStand belum dimuat oleh world generator. Untuk memperbaikinya, aktifkan 'auto-load-armor-stands' di config supaya semua ArmorStand dimuat otomatis.");
        yml.addDefault(Messages.ISSUES_FOUND_ON_JOIN, "&eAdvancedArmorStands menemukan %amount% error/peringatan di sesi ini. Cek file log untuk detailnya.");
        yml.addDefault(Messages.ARMORSTAND_SAVE_CONFIRM, "&aLakukan ini %amount% kali lagi untuk menyimpan ArmorStand ini.");
        yml.addDefault(Messages.ARMORSTAND_SAVED_SUCCESS, "&eArmorStand disimpan sebagai %name%!");
        yml.addDefault(Messages.ARMORSTAND_DELETE_CONFIRM, "&cLakukan ini %amount% kali lagi untuk menghapus ArmorStand ini.");
        yml.addDefault(Messages.ARMORSTAND_DELETED_SUCCESS, "&aArmorStand telah dihapus, tapi bisa dipulihkan dengan perintah restore, dan akan terhapus permanen saat server restart");
        yml.addDefault(Messages.COMMAND_NO_PERMISSION, "&cKamu tidak punya izin untuk memakai perintah ini!");
        yml.addDefault(Messages.COMMAND_UNKNOWN, "&cPerintah '%command%' bukan subperintah yang valid.");
        yml.addDefault(Messages.COMMAND_UNKNOWN_WITH_SUGGESTION, "&cPerintah '%command%' bukan subperintah yang valid. Maksud kamu '%suggestion%'?");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE_NUMBER, "&cTolong masukkan nomor halaman yang valid.");
        yml.addDefault(Messages.COMMAND_HELP_INVALID_PAGE, "&cHalaman tidak valid. Pilih halaman antara 1 dan %pages%.");
        yml.addDefault(Messages.COMMAND_USAGE_HOVER, "&eKlik untuk memakai saran ini");
        yml.addDefault(Messages.COMMAND_EXAMPLES, "&7Contoh:");
        yml.addDefault(Messages.COMMAND_CLICK_TO_USE, "&e&lKlik untuk memakai perintah ini");
        yml.addDefault(Messages.COMMAND_LABEL, "&7Perintah: &e%command%");
        yml.addDefault(Messages.COMMAND_DESCRIPTION_LABEL, "&7Deskripsi: &f%description%");
        yml.addDefault(Messages.CREATE_DESCRIPTION, "Membuat &7ArmorStand");
        yml.addDefault(Messages.CREATE_CUSTOM_USAGE, "&cPenggunaan: /as create custom <name> <part> <x> <y> <z> [<part> <x> <y> <z> ...]");
        yml.addDefault(Messages.CREATE_INVALID_PART_NUMBER, "&cAngka tidak valid untuk bagian %part%. Penggunaan: <part> <x> <y> <z>");
        yml.addDefault(Messages.CREATE_UNKNOWN_PART, "&cBagian tidak dikenal: %part%");
        yml.addDefault(Messages.DELETE_DESCRIPTION, "Menghapus &7ArmorStand");
        yml.addDefault(Messages.DELETE_ALL_SUCCESS, "&aSemua ArmorStand berhasil dihapus");
        yml.addDefault(Messages.DELETE_SUCCESS, "&aArmorStand terhapus sepenuhnya");
        yml.addDefault(Messages.UNLINK_SUCCESS, "&aArmor Stand berhasil dilepaskan dari AdvancedArmorStands");
        yml.addDefault(Messages.UNLINK_ALL_SUCCESS, "&aSemua Armor Stand berhasil dilepaskan dari AdvancedArmorStands");
        yml.addDefault(Messages.DEBUG_DESCRIPTION, "Menampilkan informasi debug");
        yml.addDefault(Messages.HEAD_DESCRIPTION, "Dapatkan kepala seorang pemain");
        yml.addDefault(Messages.HEAD_SUCCESS, "&aKamu diberi kepala %player%");
        yml.addDefault(Messages.LIST_DESCRIPTION, "Menampilkan daftar ArmorStand");
        yml.addDefault(Messages.LIST_EMPTY_TITLE, "&c&l     Tidak Ada ArmorStand Tersimpan ");
        yml.addDefault(Messages.LIST_EMPTY_HINT, "&7Pakai &e/as create <type> <name>&7 untuk membuat ArmorStand pertamamu!");
        yml.addDefault(Messages.LOAD_DESCRIPTION, "Memuat sebuah ArmorStand");
        yml.addDefault(Messages.LOAD_NO_UNLOADED, "&eTidak ada ArmorStand yang belum dimuat.");
        yml.addDefault(Messages.LOAD_FAILED, "&cGagal: %failed%");
        yml.addDefault(Messages.MOVE_DESCRIPTION, "Memindahkan ArmorStand ke &7lokasimu");
        yml.addDefault(Messages.OPTIONS_DESCRIPTION, "Membuka menu opsi");
        yml.addDefault(Messages.RELOAD_DESCRIPTION, "Memuat ulang config plugin");
        yml.addDefault(Messages.RELOAD_SUCCESS, "&aConfig AdvancedArmorStand berhasil dimuat ulang");
        yml.addDefault(Messages.RELOAD_ERROR, "&cTerjadi error saat memuat ulang plugin, cek console untuk detailnya");
        yml.addDefault(Messages.RENAME_DESCRIPTION, "Mengubah nama sebuah &7as");
        yml.addDefault(Messages.RENAME_SUCCESS, "&aArmorStand diganti nama dari '%old_name%' menjadi '%new_name%'");
        yml.addDefault(Messages.RESTORE_DESCRIPTION, "Memulihkan ArmorStand yang terhapus");
        yml.addDefault(Messages.RESTORE_NOT_DELETED, "&cArmorStand ini belum dihapus atau sudah terlalu terlambat");
        yml.addDefault(Messages.RESTORE_UNKNOWN_ERROR, "&cError tidak diketahui, cek console untuk info lebih lanjut");
        yml.addDefault(Messages.RESTORE_SUCCESS, "&aArmorStand berhasil dipulihkan");
        yml.addDefault(Messages.TELEPORT_NOT_ON_GROUND, "&cArmorStand ini tidak berada di tanah. Kamu yakin mau teleport ke sana?");
        yml.addDefault(Messages.TELEPORT_FORCE_HINT, "&cPakai '/as teleport %armorstand% --force' untuk memaksa teleport");
        yml.addDefault(Messages.TELL_DESCRIPTION, "Tanyakan sesuatu ke AI sebuah ArmorStand");
        yml.addDefault(Messages.TELL_AI_DISABLED, "&cArmorStand ini belum mengaktifkan AI");
        yml.addDefault(Messages.INVENTORY_EXIT_NAME, "&7» &cKELUAR&7 (Klik Kanan)");
        yml.addDefault(Messages.INVENTORY_EXIT_LORE, "\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.INVENTORY_ENTER_SESSION, "&aKamu masuk ke sesi edit, klik item KELUAR untuk kembali dan mengambil barang-barangmu lagi");
        yml.addDefault(Messages.EDITOR_EXIT_NAME, "&7» &cKELUAR &7(Klik Kanan)");
        yml.addDefault(Messages.EDITOR_EXIT_LORE, "\n&8AdvancedArmorStands Editor Item");
        yml.addDefault(Messages.HEAD_ITEM_LORE, "&eKLIK KANAN &8» &7Putar kepala ke kanan\n&eKLIK KIRI &8» &7Putar kepala ke kiri\n&eSHIFT + KLIK KANAN &8» &7Angkat kepala\n&eSHIFT + KLIK KIRI &8» &7Turunkan kepala\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_HAND_ITEM_LORE, "&eKLIK KANAN &8» &7Putar tangan kiri ke kanan\n&eKLIK KIRI &8» &7Putar tangan kiri ke kiri\n&eSHIFT + KLIK KANAN &8» &7Angkat lengan kiri\n&eSHIFT + KLIK KIRI &8» &7Turunkan lengan kiri\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_HAND_ITEM_LORE, "&eKLIK KANAN &8» &7Putar tangan kanan ke kanan\n&eKLIK KIRI &8» &7Putar tangan kanan ke kiri\n&eSHIFT + KLIK KANAN &8» &7Angkat lengan kanan\n&eSHIFT + KLIK KIRI &8» &7Turunkan lengan kiri\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.LEFT_LEG_ITEM_LORE, "&eKLIK KANAN &8» &7Putar kaki kiri ke kanan\n&eKLIK KIRI &8» &7Putar kaki kiri ke kiri\n&eSHIFT + KLIK KANAN &8» &7Angkat kaki kiri\n&eSHIFT + KLIK KIRI &8» &7Turunkan kaki kiri\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.RIGHT_LEG_ITEM_LORE, "&eKLIK KANAN &8» &7Putar kaki kanan ke kanan\n&eKLIK KIRI &8» &7Putar kaki kanan ke kiri\n&eSHIFT + KLIK KANAN &8» &7Angkat kaki kanan\n&eSHIFT + KLIK KIRI &8» &7Turunkan kaki kanan\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.ROTATE_ITEM_LORE, "&eKLIK KANAN &8» &7Putar ke kanan\n&eKLIK KIRI &8» &7Putar ke kiri\n&eSHIFT + KLIK KANAN (Block) &8» &7Angkat badan\n&eSHIFT + KLIK KIRI (Block) &8» &7Turunkan badan\n&eSHIFT + KLIK KANAN (Udara) &8» &7Geser badan ke kanan\n&eSHIFT + KLIK KIRI (Udara) &8» &7Geser badan ke kiri\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.SAVE_ITEM_LORE, "&7Klik untuk menyimpan pengaturan\n&7ArmorStand sebagai tipe, supaya bisa kamu pakai lagi nanti\n\n&8&oAdvancedArmorStands Editor Item");
        yml.addDefault(Messages.EDIT_SESSION_BLOCK_BREAK, "&cKamu tidak bisa menghancurkan blok di sesi edit.");
        yml.addDefault(Messages.MEMORY_SESSION_BLOCK_BREAK, "&cKamu tidak bisa menghancurkan blok di sini.");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_START, "&aKetik di chat nama yang mau kamu atur. Untuk keluar ketik 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_SESSION_ALREADY, "&cKamu sudah berada di sesi pengaturan nama");
        yml.addDefault(Messages.CUSTOM_NAME_EXIT_SUCCESS, "&aKamu berhasil keluar dari sesi pengaturan nama");
        yml.addDefault(Messages.CUSTOM_NAME_SET_SUCCESS, "&aNama kustom ArmorStand berhasil diatur ke '%name%'");
        yml.addDefault(Messages.TYPE_CREATE_EXIT_SUCCESS, "&aKamu berhasil keluar dari sesi pembuatan tipe");
        yml.addDefault(Messages.TYPE_ALREADY_EXISTS, "&cTipe ini sudah ada. Pilih nama lain atau pilih tipe ini di menu simpan");
        yml.addDefault(Messages.TYPE_CREATED_SUCCESS, "&aTipe '%type%' dibuat dengan properti ArmorStand ini");
        yml.addDefault(Messages.CREATION_CANCELLED, "&cPembuatan dibatalkan karena tidak ada aktivitas");
        yml.addDefault(Messages.DELETION_CANCELLED, "&cPenghapusan dibatalkan karena tidak ada aktivitas.");
        yml.addDefault(Messages.ARMS_OPTION_NAME, "&eLengan");
        yml.addDefault(Messages.ARMS_OPTION_LORE, "&7Aktifkan atau matikan\n&7lengan ArmorStand ini \n\n%status%");
        yml.addDefault(Messages.BASE_PLATE_OPTION_NAME, "&eAlas");
        yml.addDefault(Messages.BASE_PLATE_OPTION_LORE, "&7Aktifkan atau matikan\n&7alas ArmorStand ini \n\n%status%");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NAME, "&eNama kustom");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_LORE, "&7Atur nama kustom\n&7untuk ArmorStand ini");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_CURRENT_NAME, "&eSaat ini&6 » &e");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_NO_NAME, "&eNama saat ini&6 » &eTidak Ada");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ENTER_NAME, "&aKetik di chat nama yang mau kamu atur. Untuk keluar ketik 'exit'");
        yml.addDefault(Messages.CUSTOM_NAME_OPTION_ALREADY_EDITING, "&cKamu sudah berada di sesi pengaturan nama");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_NAME, "&eNama kustom terlihat");
        yml.addDefault(Messages.CUSTOM_NAME_VISIBLE_OPTION_LORE, "&7Aktifkan atau matikan\n&7nama kustom ArmorStand ini\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_NAME, "&eBersinar");
        yml.addDefault(Messages.GLOWING_OPTION_LORE, "&7Aktifkan atau matikan\n&7efek bersinar ArmorStand ini\n\n%status%");
        yml.addDefault(Messages.GLOWING_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Sedang bersinar");
        yml.addDefault(Messages.GLOWING_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Tidak bersinar");
        yml.addDefault(Messages.SMALL_OPTION_NAME, "&eKecil");
        yml.addDefault(Messages.SMALL_OPTION_LORE, "&7Aktifkan atau matikan\n&7ukuran kecil ArmorStand ini\n\n%status%");
        yml.addDefault(Messages.SMALL_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Berukuran kecil");
        yml.addDefault(Messages.SMALL_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Tidak kecil");
        yml.addDefault(Messages.VISIBLE_OPTION_NAME, "&eVisibilitas");
        yml.addDefault(Messages.VISIBLE_OPTION_LORE, "&7Aktifkan atau matikan\n&7visibilitas ArmorStand ini\n\n%status%");
        yml.addDefault(Messages.VISIBLE_OPTION_ENABLED, "&6" + TextUtils.CHECK + "&e Terlihat");
        yml.addDefault(Messages.VISIBLE_OPTION_DISABLED, "&4" + TextUtils.CROSS + "&c Tidak terlihat");
        yml.addDefault(Messages.DELETE_TOOL_NAME, "&eHapus");
        yml.addDefault(Messages.DELETE_TOOL_LORE, "&7Menghapus ArmorStand ini\n&7tindakan ini tidak bisa dibatalkan\n\n&eKlik untuk menghapus");
        yml.addDefault(Messages.DELETE_TOOL_MESSAGE, "&aArmorStand telah dihapus, tapi bisa dipulihkan dengan perintah restore, dan akan terhapus permanen saat server restart");
        yml.addDefault(Messages.DELETE_TOOL_DELETED, "&aArmorStand telah dihapus, tapi bisa dipulihkan dengan perintah restore, dan akan terhapus permanen saat server restart");
        yml.addDefault(Messages.MOVE_TOOL_NAME, "&ePindahkan");
        yml.addDefault(Messages.MOVE_TOOL_LORE, "&7Memindahkan ArmorStand\n&7ke tempat yang kamu mau\n\n&7Disarankan: pakai perintah\n&7/as move sebagai gantinya\n\n&eKlik untuk memindahkan");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY_MOVING, "&cKamu sudah berada di sesi pemindahan");
        yml.addDefault(Messages.MOVE_TOOL_START_MESSAGE, "&aHancurkan blok tempat kamu mau ArmorStand berdiri");
        yml.addDefault(Messages.MOVE_TOOL_ALREADY, "&cKamu sudah berada di sesi pemindahan");
        yml.addDefault(Messages.MOVE_TOOL_START, "&aHancurkan blok tempat kamu mau ArmorStand berdiri");
        yml.addDefault(Messages.TELEPORT_TOOL_NAME, "&eTeleport");
        yml.addDefault(Messages.TELEPORT_TOOL_LORE, "&7Teleport ke\n&7lokasi ArmorStand ini\n\n&eKlik untuk teleport");
        yml.addDefault(Messages.PREVIEW_ACCEPTED, "&aPosisi ini diterima untuk ArmorStand ini.");
        yml.addDefault(Messages.PREVIEW_DENIED, "&aPosisi baru berhasil ditolak.");

        yml.options().copyDefaults(true);
        save();
    }
}