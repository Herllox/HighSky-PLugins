package fr.herllox.highsky.Menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class PermTemporaire implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        Player p = (Player)sender;
        Inventory inv = Bukkit.createInventory(null, 54, "§6§lHigh§b§LSky §7§l>> §aPermissions Temporaire.");

        ItemStack full = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta fullx = full.getItemMeta();
        fullx.setDisplayName("§e");
        fullx.setLore(Collections.singletonList("§e"));
        full.setItemMeta(fullx);

        for(int i = 0; i <= 26; i++){
            inv.setItem(i, full);
        }




        return false;
    }
}
