package de.murmelmeister.lobby;

import de.murmelmeister.lobby.api.Locations;
import de.murmelmeister.lobby.commands.Commands;
import de.murmelmeister.lobby.configs.Messages;
import de.murmelmeister.lobby.configs.MySQL;
import de.murmelmeister.lobby.listeners.Listeners;
import de.murmelmeister.lobby.utils.Lists;
import de.murmelmeister.playtime.PlayTimeAPI;

public class InitPlugin {

    private Messages messages;
    private Locations locations;

    private Lists lists;

    private MySQL mySQL;
    private PlayTimeAPI playTimeAPI;

    private Listeners listeners;
    private Commands commands;

    public void onDisable() {
        getMySQL().disconnect();
    }

    public void onEnable() {
        setLists(new Lists());
        setMessages(new Messages());
        setLocations(new Locations());
        setMySQL(new MySQL());
        getMySQL().connectPlayTime();
        setPlayTimeAPI(new PlayTimeAPI(getMySQL().getConnection()));
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

    public MySQL getMySQL() {
        return mySQL;
    }

    public void setMySQL(MySQL mySQL) {
        this.mySQL = mySQL;
    }

    public PlayTimeAPI getPlayTimeAPI() {
        return playTimeAPI;
    }

    public void setPlayTimeAPI(PlayTimeAPI playTimeAPI) {
        this.playTimeAPI = playTimeAPI;
    }
}
