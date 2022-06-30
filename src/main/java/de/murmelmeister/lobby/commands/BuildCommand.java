package de.murmelmeister.lobby.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BuildCommand extends CommandManager {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender.hasPermission(this.messages.getConfigMessage("Permission.Build.Use")))) {
            sendMessage(sender, this.messages.getConfigMessage("Message.NoPermission"));
            return true;
        }

        if (args.length == 0) {
            Player player = sender instanceof Player ? (Player) sender : null;

            if (player == null) {
                sendMessage(sender, this.messages.getConfigMessage("Message.NoConsole"));
                return true;
            }

            if (this.lists.getBuild().contains(player.getUniqueId())) {
                this.lists.getBuild().remove(player.getUniqueId());
                player.setGameMode(GameMode.ADVENTURE);
                sendMessage(sender, this.messages.getConfigMessage("Message.Build.OutBuild.Use"));
            } else {
                this.lists.getBuild().add(player.getUniqueId());
                player.setGameMode(GameMode.CREATIVE);
                sendMessage(player, this.messages.getConfig().getString("Message.Build.OnBuild.Use"));
            }

        } else if (args.length == 1) {
            if (!(sender.hasPermission(this.messages.getConfigMessage("Permission.Build.Other")))) {
                sendMessage(sender, this.messages.getConfigMessage("Message.NoPermission"));
                return true;
            }

            Player target = sender.getServer().getPlayer(args[0]);

            if (target == null) {
                sendMessage(sender, this.messages.getConfigMessage("Message.PlayerIsNotOnline").replace("[PLAYER]", args[0]));
                return true;
            }

            if (this.lists.getBuild().contains(target.getUniqueId())) {
                this.lists.getBuild().remove(target.getUniqueId());
                target.setGameMode(GameMode.ADVENTURE);
                sendMessage(target, this.messages.getConfigMessage("Message.Build.OutBuild.Use"));
                sendMessage(sender, this.messages.getConfigMessage("Message.Build.OutBuild.Other").replace("[PLAYER]", target.getName()));
            } else {
                this.lists.getBuild().add(target.getUniqueId());
                target.setGameMode(GameMode.CREATIVE);
                sendMessage(target, this.messages.getConfigMessage("Message.Build.OnBuild.Use"));
                sendMessage(sender, this.messages.getConfigMessage("Message.Build.OnBuild.Other").replace("[PLAYER]", target.getName()));
            }
        } else {
            sendMessage(sender, this.messages.getConfigMessage("Message.Build.Syntax"));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tabComplete = new ArrayList<>();

        if (args.length == 1) {

            String lastWord = args[args.length - 1];

            Player senderPlayer = sender instanceof Player ? (Player) sender : null;

            for (Player all : sender.getServer().getOnlinePlayers()) {

                String name = all.getName();

                if ((senderPlayer == null || senderPlayer.canSee(all)) && StringUtil.startsWithIgnoreCase(name, lastWord)) {
                    tabComplete.add(name);
                }
            }
            tabComplete.sort(String.CASE_INSENSITIVE_ORDER);
        }

        return tabComplete;
    }
}
