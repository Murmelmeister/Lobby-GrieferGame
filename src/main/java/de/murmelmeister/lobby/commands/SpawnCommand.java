package de.murmelmeister.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpawnCommand extends CommandManager {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender.hasPermission(this.messages.getConfigMessage("Permission.Spawn")))) {
            sendMessage(sender, this.messages.getConfigMessage("Message.NoPermission"));
            return true;
        }

        Player player = sender instanceof Player ? (Player) sender : null;

        if (player == null) {
            sendMessage(sender, this.messages.getConfigMessage("Message.NoConsole"));
            return true;
        }

        player.teleport(this.locations.getSpawn());
        sendMessage(player, this.messages.getConfigMessage("Message.Spawn.Teleport"));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
