/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.Q
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.parsa.aas.Commands.Manager;

import me.parsa.aas.AdvancedArmorStands;
import me.parsa.aas.Commands.*;
import me.parsa.aas.Player.PlayerManager;
import me.parsa.aas.Utils.ColorUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    public ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new CreateCommand());
        subCommands.add(new ListCommand());
        subCommands.add(new TeleportCommand());
        subCommands.add(new ReloadCommand());
        subCommands.add(new MoveCommand());
        subCommands.add(new EditCommand());
        subCommands.add(new DeleteCommand());
        subCommands.add(new RenameCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("as")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("help")) {
                        int COMMANDS_PER_PAGE = 4;
                        int page = 1;

                        if (args.length >= 2) {
                            try {
                                page = Integer.parseInt(args[1]);
                            } catch (NumberFormatException e) {
                                player.sendMessage(ChatColor.RED + "Invalid page number.");
                                return true;
                            }
                        }

                        ArrayList<SubCommand> visibleCommands = new ArrayList<>();
                        for (SubCommand cmd : subCommands) {
                            if (!cmd.isForOps() || player.hasPermission("advanced-armorstands.admin")) {
                                visibleCommands.add(cmd);
                            }
                        }

                        int totalPages = (int) Math.ceil((double) visibleCommands.size() / COMMANDS_PER_PAGE);
                        if (page < 1 || page > totalPages) {
                            player.sendMessage(ChatColor.RED + "Invalid page. There are only " + totalPages + " pages.");
                            return true;
                        }

                        int startIndex = (page - 1) * COMMANDS_PER_PAGE;
                        int endIndex = Math.min(startIndex + COMMANDS_PER_PAGE, visibleCommands.size());

                        player.sendMessage(ChatColor.DARK_GRAY + "§m--------------------------------------------------");
                        player.sendMessage("   " + ColorUtils.boldAndColor(ChatColor.GOLD) + "Advanced " + ColorUtils.boldAndColor(ChatColor.YELLOW) + "ArmorStands");
                        player.sendMessage(ChatColor.DARK_GRAY + "§m--------------------------------------------------");
                        player.sendMessage(" ");
                        player.sendMessage(ChatColor.YELLOW + " Commands (Page " + page + "/" + totalPages + ")");
                        player.sendMessage(" ");

                        for (int i = startIndex; i < endIndex; i++) {
                            SubCommand cmd = visibleCommands.get(i);
                            String commands = cmd.getSyntax();
                            String description = cmd.getDescription();

                            TextComponent commandComponent = new TextComponent(ChatColor.GOLD + "» " + commands + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + description);
                            commandComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + "Click to suggest this command ").create()));
                            commandComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, commands));
                            player.spigot().sendMessage(commandComponent);
                            player.sendMessage(" ");
                        }

                        player.sendMessage(ChatColor.GRAY + "Use /as help [page] to view other pages.");
                        player.sendMessage(ChatColor.DARK_GRAY + "§m--------------------------------------------------");
                        PlayerManager.getCustomPlayerByBukkit(player).playSound("ORB_PICKUP");
                        return true;
                    }

                    int count = 0;
                    for (int i = 0; i < getSubCommands().size(); i++) {
                        if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                            count++;
                            if (getSubCommands().get(i).isForOps()) {
                                if (player.hasPermission("advanced-armorstands.admin")) {
                                    getSubCommands().get(i).perform(player, args);
                                } else {
                                    player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                                }
                            } else {
                                getSubCommands().get(i).perform(player, args);
                            }
                        }
                    }
                    if (count == 0) {
                        player.sendMessage(ChatColor.RED + "Invalid subcommand '" + args[0] + "' ");
                    }

                } else if (args.length == 0) {

                    if (subCommands.stream().allMatch(SubCommand::isForOps)) {
                        if (!PlayerManager.getCustomPlayerByBukkit(player).isAdmin()) {
                            TextComponent textComponent = new TextComponent(ChatColor.GRAY + "[" + ChatColor.GOLD + "»" + ChatColor.GRAY + "] " + ChatColor.GRAY + "AdvancedArmorStands " + ChatColor.GOLD + "v" + AdvancedArmorStands.plugin.getDescription().getVersion() + ChatColor.GRAY + " by " + ChatColor.GOLD + "Parsa3323");
                            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "Click to view github").create()));
                            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Parsa3323/AdvancedArmorStands/"));
                            player.spigot().sendMessage(textComponent);
                            return true;
                        }
                    }

                    player.performCommand("as help 1");
                }
            } else {
                sender.sendMessage("This command can only be used by players!");
            }
            return true;
        }
        return false;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
