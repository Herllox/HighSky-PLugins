package fr.herllox.highsky.Events;

import fr.herllox.highsky.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTD implements Listener {

    private final Main main;

    public MOTD(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPing(ServerListPingEvent e){
        e.setMotd(main.getConfig().getString("MOTD.MOTD-1").replace("&" , "§") + "\n" + main.getConfig().getString("MOTD.MOTD-2").replace("&", "§"));

    }

}
