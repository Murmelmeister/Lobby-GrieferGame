package de.murmelmeister.lobby.api;

import de.murmelmeister.lobby.Lobby;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Locations {

    private File folder;
    private File file;
    private YamlConfiguration config;

    public void createFile() {
        setFolder(new File("plugins//GrieferGame//" + Lobby.getInstance().getPluginName() + "//"));
        if (!(getFolder().exists())) {
            boolean aBoolean = getFolder().mkdirs();
            if (!(aBoolean)) Lobby.getInstance().getSLF4JLogger().warn("The folder cannot be created a second time.");
        }

        setFile(new File(getFolder(), "locations.yml"));
        if (!(getFile().exists())) {
            try {
                boolean aBoolean = getFile().createNewFile();
                if (!(aBoolean))
                    Lobby.getInstance().getSLF4JLogger().warn("The file 'locations.yml' cannot be created a second time.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        setConfig(YamlConfiguration.loadConfiguration(getFile()));
    }

    public void saveFile() {
        try {
            getConfig().save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDeathHeight(double height) {
        createFile();
        getConfig().set("Locations.DeathHeight", height);
        saveFile();
    }

    public double getDeathHeight() {
        createFile();
        return getConfig().getDouble("Locations.DeathHeight");
    }

    public void setSpawn(Location location) {
        createFile();
        double x = location.getBlockX() + 0.5D;
        double y = location.getBlockY();
        double z = location.getBlockZ() + 0.5D;
        double yaw = (Math.round(location.getYaw() / 45.0F) * 45);
        double pitch = (Math.round(location.getPitch() / 45.0F) * 45);
        String worldName = location.getWorld().getName();
        String environmentName = location.getWorld().getEnvironment().name();
        getConfig().set("Locations.Spawn.X", x);
        getConfig().set("Locations.Spawn.Y", y);
        getConfig().set("Locations.Spawn.Z", z);
        getConfig().set("Locations.Spawn.Yaw", yaw);
        getConfig().set("Locations.Spawn.Pitch", pitch);
        getConfig().set("Locations.Spawn.WorldName", worldName);
        getConfig().set("Locations.Spawn.EnvironmentName", environmentName);
        saveFile();
    }

    public Location getSpawn() {
        createFile();
        double x = getConfig().getDouble("Locations.Spawn.X");
        double y = getConfig().getDouble("Locations.Spawn.Y");
        double z = getConfig().getDouble("Locations.Spawn.Z");
        double yaw = getConfig().getDouble("Locations.Spawn.Yaw");
        double pitch = getConfig().getDouble("Locations.Spawn.Pitch");
        String worldName = getConfig().getString("Locations.Spawn.WorldName");
        assert worldName != null;
        Location location = new Location(Lobby.getInstance().getServer().getWorld(worldName), x, y, z);
        location.setYaw((float) yaw);
        location.setPitch((float) pitch);
        return location;
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
