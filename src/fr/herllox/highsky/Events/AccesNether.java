package fr.herllox.highsky.Events;

import fr.herllox.highsky.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class AccesNether implements Listener, CommandExecutor {


    private final Main main;

    public AccesNether(Main main) {
        this.main = main;
    }

    @Deprecated
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!(sender instanceof Player)){
            System.out.println("Uniquement via un Joueur.");
        }

        Player p = (Player) sender;
        p.getUniqueId();

        if(cmd.getName().equalsIgnoreCase("bitch")){
            if(p.isOp()){
                //p.sendMessage(String.format("Money %s", Main.econ.format(Main.econ.getBalance(p.getName()))));
                if(Main.econ.getBalance((OfflinePlayer)Bukkit.getPlayer(p.getUniqueId())) == 600){

                    p.sendMessage("work");

                }
            }
        }

        return false;
    }
}
