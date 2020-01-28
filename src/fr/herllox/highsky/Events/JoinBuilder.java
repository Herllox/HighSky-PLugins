package fr.herllox.highsky.Events;

import fr.herllox.highsky.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinBuilder implements Listener {

    private final Main main;

    public JoinBuilder(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();
        e.getPlayer().sendMessage("%highsky_tag%");

        if(p.getName() == "Estanos"){
            (new BukkitRunnable() {
                public void run() {
                    if(!p.getWorld().getName().equalsIgnoreCase("Build")){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + p.getName() + "Build");
                    }
                }
            }).runTaskLater(main, 40L);
            
        }

    }

    @EventHandler
    public void onChange(PlayerChangedWorldEvent e){

        Player p = e.getPlayer();
        if(p.getName() == "Estanos") {
            if (!(p.getWorld().getName() == "Build")) {

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + p.getName() + "Build");
            }
        }
    }

}
