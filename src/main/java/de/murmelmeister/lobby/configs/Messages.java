package de.murmelmeister.lobby.configs;

import de.murmelmeister.lobby.Lobby;
import de.murmelmeister.lobby.utils.HexColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Messages {

    private File folder;
    private File file;
    private YamlConfiguration config;

    public Messages() {
        createFile();
        saveFile();
    }

    public void createFile() {
        setFolder(new File("plugins//GrieferGame//" + Lobby.getInstance().getPluginName() + "//"));
        if (!(getFolder().exists())) {
            boolean aBoolean = getFolder().mkdirs();
            if (!(aBoolean)) Lobby.getInstance().getSLF4JLogger().warn("The folder cannot be created a second time.");
        }

        setFile(new File(getFolder(), "messages.yml"));
        if (!(getFile().exists())) {
            try {
                boolean aBoolean = getFile().createNewFile();
                if (!(aBoolean))
                    Lobby.getInstance().getSLF4JLogger().warn("The file 'message.yml' cannot be created a second time.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            setConfig(YamlConfiguration.loadConfiguration(getFile()));
            firstConfig();
            return;
        }
        setConfig(YamlConfiguration.loadConfiguration(getFile()));
        loadConfig();
    }

    public void saveFile() {
        try {
            getConfig().save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void firstConfig() {
        Lobby.getInstance().setPrefix(HexColor.format("§8[§6GrieferGame§8] §r"));
        setConfigMessage("Prefix", Lobby.getInstance().getPrefix());
        setConfigMessage("Permission.Build.Use", "lobby.command.build.use");
        setConfigMessage("Permission.Build.Other", "lobby.command.build.other");
        setConfigMessage("Permission.DeathHeight", "lobby.command.deathheight");
        setConfigMessage("Permission.SetDeathHeight", "lobby.command.setdeathheight");
        setConfigMessage("Permission.SetSpawn", "lobby.command.setspawn");
        setConfigMessage("Permission.Spawn", "lobby.command.spawn");
        setConfigMessage("Permission.LobbySystem.Use", "lobby.command.lobbysystem.use");
        setConfigMessage("Permission.LobbySystem.Reload", "lobby.command.lobbysystem.reload");
        setConfigMessage("Message.NoPermission", "§cDazu hast du keine Rechte.");
        setConfigMessage("Message.NoConsole", "§cDieser Command funktioniert nicht in der Console.");
        setConfigMessage("Message.PlayerIsNotOnline", "§7Der Spieler §e[PLAYER] §7ist §c§nnicht §7auf diesem Server §cOnline§7.");
        setConfigMessage("Message.LobbySystem.ReloadMessage", "§aDie Konfigurationen wurden neugeladen.");
        setConfigMessage("Message.LobbySystem.Syntax", "§7Syntax: §c/lobbysystem [reload]");
        setConfigMessage("Message.Build.OnBuild.Use", "§7Du bist im §aBuildmode§7.");
        setConfigMessage("Message.Build.OutBuild.Use", "§7Du bist nicht mehr im §cBuildmode§7.");
        setConfigMessage("Message.Build.OnBuild.Other", "§7Du hast §e[PLAYER] §7im §aBuildmode §7gesetzt.");
        setConfigMessage("Message.Build.OutBuild.Other", "§7Du hast §e[PLAYER] §7aus dem §cBuildmode §7gesetzt.");
        setConfigMessage("Message.Build.Syntax", "§7Syntax: §c/build [player]");
        setConfigMessage("Message.SetDeathHeight", "§7Du hast die §atodes Höhe §7gesetzt. §8(§aHöhe§7: §a[HEIGHT]§8)");
        setConfigMessage("Message.DeathHeight", "§7Die §atodes Höhe §7ist auf §a[HEIGHT]§7.");
        setConfigMessage("Message.SetSpawn", "§7Du hast den §aSpawn §7gesetzt.");
        setConfigMessage("Message.Spawn.Teleport", "§7Du hast dich zum §aSpawn §7teleportiert.");
    }

    private void loadConfig() {
        getConfigMessage("Prefix");
        getConfigMessage("Permission.Build.Use");
        getConfigMessage("Permission.Build.Other");
        getConfigMessage("Permission.DeathHeight");
        getConfigMessage("Permission.SetDeathHeight");
        getConfigMessage("Permission.SetSpawn");
        getConfigMessage("Permission.Spawn");
        getConfigMessage("Permission.LobbySystem.Use");
        getConfigMessage("Permission.LobbySystem.Reload");
        getConfigMessage("Permission.Event.Join.Enderbow");
        getConfigMessage("Message.NoPermission");
        getConfigMessage("Message.NoConsole");
        getConfigMessage("Message.PlayerIsNotOnline");
        getConfigMessage("Message.LobbySystem.ReloadMessage");
        getConfigMessage("Message.LobbySystem.Syntax");
        getConfigMessage("Message.Build.OnBuild.Use");
        getConfigMessage("Message.Build.OutBuild.Use");
        getConfigMessage("Message.Build.OnBuild.Other");
        getConfigMessage("Message.Build.OutBuild.Other");
        getConfigMessage("Message.Build.Syntax");
        getConfigMessage("Message.SetDeathHeight");
        getConfigMessage("Message.DeathHeight");
        getConfigMessage("Message.SetSpawn");
        getConfigMessage("Message.Spawn.Teleport");
    }

    private void setConfigMessage(String path, Object value) {
        getConfig().set(path, value);
    }

    public String getConfigMessage(String path) {
        return HexColor.format(getConfig().getString(path));
    }

    public String getPrefix() {
        return getConfigMessage("Prefix");
    }

    public File getFolder() {
        return folder;
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void setConfig(YamlConfiguration config) {
        this.config = config;
    }
}
