package me.parsa.aas.Commands.Manager;

import me.parsa.aas.AdvancedArmorStands;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class SubCommand {
    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void perform(Player player, String args[]);

    public abstract List<String> getTabComplete(Player player, String args[]);

    public abstract boolean isForOps();

    public void sendUsage(Player player) {
        TextComponent textComponent = new TextComponent(AdvancedArmorStands.prefix + ChatColor.GRAY + "Usage: ");
        TextComponent main = new TextComponent(ChatColor.GOLD + getSyntax());
        main.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, getSyntax()));
        main.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + "Click to suggest").create()));
        player.spigot().sendMessage(textComponent, main);
    }

}
