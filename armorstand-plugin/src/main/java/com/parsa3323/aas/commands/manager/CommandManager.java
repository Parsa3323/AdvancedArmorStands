/*
 *
 * Copyright
 * 2025 AdvancedArmorStands, Inc.
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

package com.parsa3323.aas.commands.manager;

import com.cryptomorin.xseries.XSound;
import com.parsa3323.aas.AdvancedArmorStands;
import com.parsa3323.aas.commands.*;
import com.parsa3323.aas.player.PlayerManager;
import com.parsa3323.aas.utils.ColorUtils;
import com.parsa3323.aas.utils.CommandUtils;
import com.parsa3323.aas.utils.VersionSupportUtil;
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
        subCommands.add(new LoadCommand());
        subCommands.add(new SettingsCommand());
        subCommands.add(new DeleteCommand());
        subCommands.add(new RenameCommand());
        subCommands.add(new OptionsCommand());
        subCommands.add(new HeadCommand());
        subCommands.add(new AnimationCommand());
        subCommands.add(new DebugCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("as")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("help")) {
                        return handleHelpCommand(args, player);
                    }

                    handleNoArg(args, player);

                } else if (args.length == 0) {

                    if (subCommands.stream().allMatch(SubCommand::isForOps)) {
                        if (handleNoCommands(player)) return true;
                    }

                    player.performCommand("as help 1");
                }
            } else {
                handleConsole(sender);
            }
            return true;
        }
        return false;
    }

    private static void handleConsole(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GOLD + "======================================");
        sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "    AdvancedArmorStands Plugin");
        sender.sendMessage(ChatColor.GRAY + "    Version: " + ChatColor.WHITE + AdvancedArmorStands.plugin.getDescription().getVersion());
        sender.sendMessage(ChatColor.GRAY + "    Author: " + ChatColor.YELLOW + "Parsa3323");
        sender.sendMessage(ChatColor.GRAY + "    Version Support: " + ChatColor.YELLOW + VersionSupportUtil.getVersionSupport().getClass().getSimpleName());
        sender.sendMessage("");
        sender.sendMessage(ChatColor.RED + "Note: " + ChatColor.GRAY + "This command must be run in-game to");
        sender.sendMessage(ChatColor.GRAY + "      access the interactive command menu.");
        sender.sendMessage(ChatColor.GOLD + "======================================");
        sender.sendMessage("");
    }

    private static boolean handleNoCommands(Player player) {
        if (!PlayerManager.getByBukkit(player).isAdmin()) {
            player.sendMessage("");
            TextComponent textComponent = new TextComponent(ChatColor.GRAY + "[" + ChatColor.GOLD + "»" + ChatColor.GRAY + "] " + ChatColor.GRAY + "AdvancedArmorStands " + ChatColor.GOLD + "v" + AdvancedArmorStands.plugin.getDescription().getVersion() + ChatColor.GRAY + " by " + ChatColor.GOLD + "Parsa3323");

            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(ChatColor.GREEN + "" + ChatColor.BOLD + "Visit GitHub Repository")
                            .append("\n" + ChatColor.GRAY + "Click to view the source code")
                            .append("\n" + ChatColor.GRAY + "and documentation")
                            .create()));
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Parsa3323/AdvancedArmorStands/"));

            player.spigot().sendMessage(textComponent);
            player.sendMessage("");
            return true;
        }
        return false;
    }

    private void handleNoArg(String[] args, Player player) {
        int count = 0;
        for (int i = 0; i < getSubCommands().size(); i++) {
            if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                count++;
                if (getSubCommands().get(i).isForOps()) {
                    if (player.hasPermission("advanced-armorstands.admin")) {
                        getSubCommands().get(i).perform(player, args);
                    } else {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Access Denied: " + ChatColor.RED + "You don't have permission to use this command!");
                    }
                } else {
                    getSubCommands().get(i).perform(player, args);
                }
            }
        }
        if (count == 0) {
            String suggestion = CommandUtils.getClosestCommand(args[0], getSubCommands());
            if (suggestion != null) {
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Unknown Command: " + ChatColor.RED + "' is not a valid subcommand" + args[0] + "'. Did you mean '/as " + suggestion + "'?");
            } else {
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Unknown Command: " + ChatColor.RED + "'" + args[0] + "' is not a valid subcommand.");
            }
        }
    }

    private boolean handleHelpCommand(String[] args, Player player) {
        int COMMANDS_PER_PAGE = 4;
        int page = 1;

        if (args.length >= 2) {
            try {
                page = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Error: " + ChatColor.RED + "Please enter a valid page number.");
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
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Invalid Page: " + ChatColor.RED + "Please choose a page between " + ChatColor.YELLOW + "1" + ChatColor.RED + " and " + ChatColor.YELLOW + totalPages + ChatColor.RED + ".");
            return true;
        }

        int startIndex = (page - 1) * COMMANDS_PER_PAGE;
        int endIndex = Math.min(startIndex + COMMANDS_PER_PAGE, visibleCommands.size());

        player.sendMessage("");
        player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        player.sendMessage("     " + ColorUtils.boldAndColor(ChatColor.GOLD) + "Advanced " + ColorUtils.boldAndColor(ChatColor.YELLOW) + "ArmorStands ");
        player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        player.sendMessage("");
        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + " Available Commands " + ChatColor.GRAY + "(Page " + ChatColor.WHITE + page + ChatColor.GRAY + " of " + ChatColor.WHITE + totalPages + ChatColor.GRAY + ")");
        player.sendMessage("");

        for (int i = startIndex; i < endIndex; i++) {
            SubCommand cmd = visibleCommands.get(i);
            String commands = cmd.getSyntax();
            String description = cmd.getDescription();

            TextComponent commandComponent = new TextComponent(ChatColor.GOLD + " » " + ChatColor.YELLOW + "" + ChatColor.BOLD + commands);
            TextComponent descriptionComponent = new TextComponent(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + description);

            commandComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(ColorUtils.boldAndColor(ChatColor.YELLOW) + "Click to use this command")
                            .append("\n" + ChatColor.GRAY + "Command: " + ChatColor.YELLOW + commands)
                            .append("\n" + ChatColor.GRAY + "Description: " + ChatColor.WHITE + description)
                            .create()));
            commandComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, commands));

            player.spigot().sendMessage(commandComponent, descriptionComponent);
        }

        player.sendMessage("");
        if (totalPages > 1) {
            player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Use " + ChatColor.YELLOW + "/as help <page>" + ChatColor.GRAY + "" + ChatColor.ITALIC + " to view other pages.");
        }
        player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Tip: Click on any command to auto-fill it in chat!");
        player.sendMessage(ChatColor.DARK_GRAY + "§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        player.sendMessage("");

        player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.2f);
        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    public int getAmount() {
        return subCommands.size();
    }
}