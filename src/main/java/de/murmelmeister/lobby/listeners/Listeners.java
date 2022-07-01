package de.murmelmeister.lobby.listeners;

import de.murmelmeister.lobby.Lobby;
import de.murmelmeister.lobby.api.Locations;
import de.murmelmeister.lobby.configs.Messages;
import de.murmelmeister.lobby.utils.HexColor;
import de.murmelmeister.lobby.utils.Lists;
import de.murmelmeister.playtime.PlayTimeAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class Listeners implements Listener {

    protected Lobby instance = Lobby.getInstance();

    protected Lists lists = this.instance.getInitPlugin().getLists();
    protected Messages messages = this.instance.getInitPlugin().getMessages();
    protected Locations locations = this.instance.getInitPlugin().getLocations();
    protected PlayTimeAPI playTimeAPI = this.instance.getInitPlugin().getPlayTimeAPI();

    public void registerListeners() {
        addListener(new ConnectListener());
        addListener(new DamageListener());
        addListener(new OthersListener());
    }

    private void addListener(Listener listener) {
        this.instance.getServer().getPluginManager().registerEvents(listener, this.instance);
    }

    protected void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(this.messages.getPrefix() + HexColor.format(message));
    }

}
