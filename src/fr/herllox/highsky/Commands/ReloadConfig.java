package fr.herllox.highsky.Commands;

import fr.herllox.highsky.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfig implements CommandExecutor {
    private final Main main;

    public ReloadConfig(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        Player p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("highsky")){
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("reload")) {
                    if (p.isOp()) {
                        main.reloadConfig();
                    }
                }
            }
        }

        return false;
    }
}
