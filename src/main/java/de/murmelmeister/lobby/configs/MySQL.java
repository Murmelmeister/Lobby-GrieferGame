package de.murmelmeister.lobby.configs;

import de.murmelmeister.lobby.Lobby;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private File folder;
    private File file;
    private YamlConfiguration config;

    public MySQL() {
        createFile();
        saveFile();
    }

    public void createFile() {
        setFolder(new File("plugins//GrieferGame//" + Lobby.getInstance().getPluginName() + "//"));
        if (!(getFolder().exists())) {
            boolean aBoolean = getFolder().mkdirs();
            if (!(aBoolean)) Lobby.getInstance().getSLF4JLogger().warn("The folder cannot be created a second time.");
        }

        setFile(new File(getFolder(), "mysql.yml"));
        if (!(getFile().exists())) {
            try {
                boolean aBoolean = getFile().createNewFile();
                if (!(aBoolean))
                    Lobby.getInstance().getSLF4JLogger().warn("The file 'mysql.yml' cannot be created a second time.");
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
        this.getConfig().set("PlayTime.Hostname", "localhost");
        this.getConfig().set("PlayTime.Port", 3306);
        this.getConfig().set("PlayTime.Database", "PlayTimeBase");
        this.getConfig().set("PlayTime.Username", "root");
        this.getConfig().set("PlayTime.Password", "12345");
    }

    private void loadConfig() {
        this.getConfig().getString("PlayTime.Hostname");
        this.getConfig().getInt("PlayTime.Port");
        this.getConfig().getString("PlayTime.Database");
        this.getConfig().getString("PlayTime.Username");
        this.getConfig().getString("PlayTime.Password");
    }

    private Connection connection;

    public void connectPlayTime() {
        if (!isConnected()) {
            try {
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.getConfig().getString("PlayTime.Hostname") + ":" + this.getConfig().getInt("PlayTime.Port") + "/"
                                + this.getConfig().getString("PlayTime.Database") + "?autoReconnect=true&useUnicode=yes",
                        this.getConfig().getString("PlayTime.Username"), this.getConfig().getString("PlayTime.Password")));
                Lobby.getInstance().getSLF4JLogger().info("MySQL connected!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                getConnection().close();
                Lobby.getInstance().getSLF4JLogger().warn("MySQL connection closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isConnected() {
        return this.connection == null ? false : true;
    }
}
