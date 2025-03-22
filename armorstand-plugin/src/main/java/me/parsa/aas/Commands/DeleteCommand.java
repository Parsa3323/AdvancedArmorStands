package me.parsa.aas.Commands;

import me.parsa.aas.Commands.Manager.SubCommand;
import org.bukkit.entity.Player;

import java.util.List;

public class DeleteCommand extends SubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Deletes a armor stand";
    }

    @Override
    public String getSyntax() {
        return "/as delete <name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length > 2) {
            return;
        }

        ListCommand.deleteArmorStand(args[1], player);

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {
        return null;
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
