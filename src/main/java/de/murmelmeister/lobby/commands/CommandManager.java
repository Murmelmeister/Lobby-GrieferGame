package de.murmelmeister.lobby.commands;

import de.murmelmeister.lobby.Lobby;
import de.murmelmeister.lobby.api.Locations;
import de.murmelmeister.lobby.configs.Messages;
import de.murmelmeister.lobby.utils.HexColor;
import de.murmelmeister.lobby.utils.Lists;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public abstract class CommandManager implements TabExecutor {

    protected Lobby instance = Lobby.getInstance();

    protected Lists lists = this.instance.getInitPlugin().getLists();
    protected Messages messages = this.instance.getInitPlugin().getMessages();
    protected Locations locations = this.instance.getInitPlugin().getLocations();

    protected void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(this.messages.getPrefix() + HexColor.format(message));
    }

}
