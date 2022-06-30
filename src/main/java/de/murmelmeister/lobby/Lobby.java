package de.murmelmeister.lobby;

public final class Lobby extends Main {

    private static Lobby instance;

    private InitPlugin initPlugin;

    @Override
    public void onDisable() {
        getInitPlugin().onDisable();
        handleDisableMessage();
    }

    @Override
    public void onEnable() {
        setInitPlugin(new InitPlugin());
        getInitPlugin().onEnable();
        handleEnableMessage();
    }

    @Override
    public void onLoad() {
        setInstance(this);
    }

    public static Lobby getInstance() {
        return instance;
    }

    public static void setInstance(Lobby instance) {
        Lobby.instance = instance;
    }

    public InitPlugin getInitPlugin() {
        return initPlugin;
    }

    public void setInitPlugin(InitPlugin initPlugin) {
        this.initPlugin = initPlugin;
    }
}
