package fr.herllox.highsky.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VoidSpawn implements Listener {

    @EventHandler
    public void onVoidSpawn(EntityDamageEvent e){
        if(e.getEntityType() == EntityType.PLAYER){
            Player p = (Player) e.getEntity();
            if(p.getLastDamageCause() != null){
                if(e.getCause().equals(EntityDamageEvent.DamageCause.VOID)){
                    if(p.getLocation().getWorld().getName().equalsIgnoreCase("Vide")){
                        Bukkit.dispatchCommand(p, "spawn");
                        e.setCancelled(true);
                    }
                }

            }
        }

    }

}
