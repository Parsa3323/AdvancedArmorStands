package me.parsa.aas.Commands;

import me.parsa.aas.Commands.Manager.SubCommand;
import me.parsa.aas.Configs.ArmorStands;
import me.parsa.aas.Utils.ArmorStandUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends SubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Delete an armor stand";
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

        ArmorStandUtils.deleteArmorStand(args[1], player);

    }

    @Override
    public List<String> getTabComplete(Player player, String[] args) {

        ArrayList<String> list = new ArrayList<>(ArmorStands.get().getConfigurationSection("armorstands").getKeys(false));


        return list;
    }

    @Override
    public boolean isForOps() {
        return true;
    }
}
