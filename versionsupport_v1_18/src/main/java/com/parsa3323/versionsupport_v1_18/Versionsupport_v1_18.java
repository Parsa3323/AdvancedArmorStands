package com.parsa3323.versionsupport_v1_18;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.profiles.builder.XSkull;
import com.cryptomorin.xseries.profiles.objects.Profileable;
import com.parsa3323.aas.api.versionSupport.IVersionSupport;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Versionsupport_v1_18 implements IVersionSupport {



    @Override
    public void playSound(Player player, Location location, String soundName, float volume, float pitch) {
        try {
            Sound sound = null;
            switch (soundName) {
                case "NOTE_PLING":
                    sound = Sound.BLOCK_NOTE_BLOCK_PLING;
                    break;
                case "NOTE_BASS":
                    sound  = Sound.BLOCK_NOTE_BLOCK_BASS;
                    break;
                case "VILLAGER_NO":
                    sound = Sound.ENTITY_VILLAGER_NO;

                    break;
                case "ORB_PICKUP":
                    sound = Sound.ENTITY_EXPERIENCE_ORB_PICKUP;
                    break;
                case "ITEM_PICKUP":
                    sound = Sound.ENTITY_ITEM_PICKUP;
                    break;
                case "CLICK":
                    sound = Sound.UI_BUTTON_CLICK;
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Unknown sound: " + soundName);
                    return;
            }
            player.playSound(location, sound, volume, pitch);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Sound " + soundName + " not found in this version!");
        }
    }

    @Override
    public ItemStack getSkull(String base64) {
        ItemStack head = XMaterial.PLAYER_HEAD.parseItem();

        if(head == null) throw new RuntimeException("Failed to get skull");

        ItemMeta itemMeta = head.getItemMeta();

        if(itemMeta == null) return head;

        itemMeta = XSkull.of(itemMeta).profile(Profileable.detect(base64)).lenient().apply();

        head.setItemMeta(itemMeta);

        return head;
    }

    @Override
    public Material getMaterialForVersion(String s) {


        Material mat = Material.matchMaterial(s);

        if (mat == null) {
            mat = Material.matchMaterial(s, true);
        }

        return mat;

    }

    @Override
    public boolean isGlowing(ArmorStand as) {
        return as.isGlowing();
    }

    @Override
    public void setGlowing(ArmorStand as, boolean s) {
        as.setGlowing(s);
    }

    @Override
    public boolean canGlow() {
        return true;
    }
}
