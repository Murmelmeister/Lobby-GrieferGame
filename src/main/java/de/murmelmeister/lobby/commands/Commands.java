package de.murmelmeister.lobby.commands;

import de.murmelmeister.lobby.Lobby;
import org.bukkit.command.TabExecutor;

import java.util.Objects;

public class Commands {

    public void registerCommands() {
        addCommand("build", new BuildCommand());
        addCommand("deathHeight", new DeathHeightCommand());
        addCommand("setdeathheight", new SetDeathHeightCommand());
        addCommand("setspawn", new SetSpawnCommand());
        addCommand("spawn", new SpawnCommand());
        addCommand("lobbysystem", new LobbySystemCommand());
    }

    private void addCommand(String commandName, TabExecutor command) {
        Objects.requireNonNull(Lobby.getInstance().getCommand(commandName)).setExecutor(command);
        Objects.requireNonNull(Lobby.getInstance().getCommand(commandName)).setTabCompleter(command);
    }

}
