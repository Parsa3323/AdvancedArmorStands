package me.parsa.aas.Commands;

import me.parsa.aas.AdvancedArmorStands;
import me.parsa.aas.Commands.Manager.SubCommand;
import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Configs.TypesConfig;
import me.parsa.aas.Player.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class ReloadCommand extends SubCommand {
    @Override
    public String getName() {
        return "Reload";
    }

    @Override
    public String getDescription() {
        return "Reloads plugin's configs";
    }

    @Override
    public String getSyntax() {
        return "/as reload";
    }

    @Override
    public void perform(Player player, String[] args) {

        try {
            TypesConfig.reload();
            ArmorStands.reload();
            player.sendMessage(ChatColor.GREEN + "✔ Successfully reloaded plugin's configs");
            PlayerManager.getCustomPlayerByBukkit(player).playSound("ORB_PICKUP");
        } catch (Exception e) {
            e.printStackTrace();
            AdvancedArmorStands.info(ChatColor.RED + e.getMessage());
        }

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
