package de.murmelmeister.lobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener extends Listeners {

    /*
    Handle the player join event.
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer(); // Player
        event.joinMessage(null);

        player.getInventory().clear();

        try {
            player.teleport(this.locations.getSpawn()); // Teleport to the spawn
        } catch (NullPointerException | IllegalArgumentException e) {
            // When the spawn does not exist
            sendMessage(player, "§8--- §cSetup §8---");
            sendMessage(player, "§7Bitte setze den Spawn. §8(§c/setspawn§8)");
        }

        // When the death height does not exist
        if (this.locations.getConfig().get("Locations.DeathHeight") == null) {
            sendMessage(player, "§8--- §cSetup §8---");
            sendMessage(player, "§7Bitte setze die Todeshöhe. §8(§c/setdeathheight§8)");
        }
    }

    /*
    Handle the player quit event.
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerQuit(PlayerQuitEvent event) {
        // When the player disconnect
        event.quitMessage(null); // Disconnect message
        this.lists.getBuild().remove(event.getPlayer().getUniqueId());
    }

}
