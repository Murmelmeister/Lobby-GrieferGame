package de.murmelmeister.lobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener extends Listeners {

    /*
    Disable the player fall damage.
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void handlePlayerFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(event.getCause().equals(EntityDamageEvent.DamageCause.FALL));
        }
    }

    /*
    Disable the player damage.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleEntityDamage(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

}
