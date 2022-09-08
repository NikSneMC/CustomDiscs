package me.Navoei.customdiscsplugin.command;

import me.Navoei.customdiscsplugin.command.SubCommands.ConvertCommand;
import me.Navoei.customdiscsplugin.command.SubCommands.CreateCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new CreateCommand());
        subCommands.add(new ConvertCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "\u0422\u043e\u043b\u044c\u043a\u043e \u0438\u0433\u0440\u043e\u043a\u0438 \u043c\u043e\u0433\u0443\u0442 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u044d\u0442\u0443 \u043a\u043e\u043c\u0430\u043d\u0434\u0443!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 0) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    getSubCommands().get(i).perform(player, args);
                }
            }
        } else {
            player.sendMessage(ChatColor.AQUA + "----[ Custom Discs Translated]----");
            for (int i = 0; i < getSubCommands().size(); i++) {
                player.sendMessage(getSubCommands().get(i).getSyntax() + ChatColor.DARK_GRAY + " - " + getSubCommands().get(i).getDescription());
            }
            player.sendMessage(ChatColor.AQUA + "---------------------");
            return true;
        }

        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            for (int i = 0; i < getSubCommands().size(); i++) {
                arguments.add(getSubCommands().get(i).getName());
            }
            return arguments;
        }

        return null;
    }
}
