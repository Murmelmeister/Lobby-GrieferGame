package de.murmelmeister.lobby;

import de.murmelmeister.lobby.api.Locations;
import de.murmelmeister.lobby.commands.Commands;
import de.murmelmeister.lobby.configs.Messages;
import de.murmelmeister.lobby.listeners.Listeners;
import de.murmelmeister.lobby.utils.Lists;

public class InitPlugin {

    private Messages messages;
    private Locations locations;

    private Lists lists;

    private Listeners listeners;
    private Commands commands;

    public void onDisable() {

    }

    public void onEnable() {
        setLists(new Lists());
        setMessages(new Messages());
        setLocations(new Locations());
        setListeners(new Listeners());
        setCommands(new Commands());
        getListeners().registerListeners();
        getCommands().registerCommands();
    }

    public Lists getLists() {
        return lists;
    }

    public void setLists(Lists lists) {
        this.lists = lists;
    }

    public Listeners getListeners() {
        return listeners;
    }

    public void setListeners(Listeners listeners) {
        this.listeners = listeners;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public Commands getCommands() {
        return commands;
    }

    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public Locations getLocations() {
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }
}
