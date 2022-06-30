package de.murmelmeister.lobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public class OthersListener extends Listeners {

    @EventHandler(priority = EventPriority.LOW)
    public void handlePlayerFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void handlePlayerDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.LOW)
    public void handlePlayerPickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handleEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(!(event.getEntity() instanceof Player));
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handleExplodeDamage(BlockExplodeEvent event) {
        event.setCancelled(true);
        event.setYield(0f);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void handleXpBar(PlayerLevelChangeEvent event) {
        event.getPlayer().setLevel(2022);
        event.getPlayer().setExp(1F / 12 * 6);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void handleBlockBreak(BlockBreakEvent event) {
        event.setExpToDrop(0);
        event.setCancelled(!this.lists.getBuild().contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void handleBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(!this.lists.getBuild().contains(event.getPlayer().getUniqueId()));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void handlePlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (this.lists.getBuild().contains(player.getUniqueId())) {
            event.setCancelled(false);
            return;
        }

        try {
            if (player.getLocation().getBlockY() <= this.locations.getDeathHeight()) {
                player.teleport(this.locations.getSpawn());
            }
        } catch (NullPointerException ignored) {
            sendMessage(player.getServer().getConsoleSender(), "§cError NullPointerException [OthersListener.java:72]");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void handlePlayerDeath(PlayerDeathEvent event) {
        event.setDroppedExp(0);
        event.getDrops().clear();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void handlePlayerInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (this.lists.getBuild().contains(player.getUniqueId())) {
            event.setCancelled(!event.getView().getPlayer().equals(player));
        } else {
            event.setCancelled(event.getView().getPlayer().equals(player));
        }
    }

}
