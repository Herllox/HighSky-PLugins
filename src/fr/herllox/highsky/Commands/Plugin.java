package fr.herllox.highsky.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Plugin implements CommandExecutor {

    ArrayList<Player> build = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {


        Player p = (Player) sender;

        if(args.length == 0){
            if(!p.isOp()){
                p.sendMessage("§fPlugins (10)");

            }
        }

        return false;
    }
}
