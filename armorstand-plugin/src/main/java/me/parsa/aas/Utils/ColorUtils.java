package me.parsa.aas.Utils;

import org.bukkit.ChatColor;

public class ColorUtils  {

    public static String transform(String r) {
        return ChatColor.translateAlternateColorCodes('&', r);
    }

    public static String boldAndColor(ChatColor c) {
        return ChatColor.BOLD + "" + c;
    }

    public static String removeColors(String r) {
        return ChatColor.stripColor(r);
    }

}