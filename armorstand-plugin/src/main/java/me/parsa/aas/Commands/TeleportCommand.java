package me.parsa.aas.Commands;

import me.parsa.aas.Commands.Manager.SubCommand;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class TeleportCommand extends SubCommand {
    @Override
    public String getName() {
        return "teleport";
    }

    @Override
    public String getDescription() {
        return "teleport to armor stand";
    }

    @Override
    public String getSyntax() {
        return "/as teleport <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 2) {
            sendUsage(player);
            return;
        }

        ListCommand.teleportToArmorStand(player, args[1]);

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
