package fr.herllox.highsky.Challenges;

import com.songoda.skyblock.SkyBlock;
import com.songoda.skyblock.api.SkyBlockAPI;
import com.songoda.skyblock.api.island.IslandLevel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.Collections;

public class CommandChallenges implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        Player p = (Player) sender;

        if (args.length == 0) {
            if (p.hasPermission("challenges.see")) {

                Inventory inv = Bukkit.createInventory(null, 27, "§9Challenges");

                ItemStack full = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta fullx = full.getItemMeta();
                fullx.setDisplayName("§e");
                fullx.setLore(Collections.singletonList("§e"));
                full.setItemMeta(fullx);

                for(int i = 0; i <= 26; i++) {
                    inv.setItem(i, full);
                }

                ItemStack book1 = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta book1x = book1.getItemMeta();
                book1x.setDisplayName("§aChallenges: §dNiveau 1");
                book1x.setLore(Collections.singletonList("§3Challenges de difficulté Simple."));
                book1.setItemMeta(book1x);

                ItemStack book2 = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta book2x = book2.getItemMeta();
                book2x.setDisplayName("§aChallenges: §dNiveau 2");
                book2x.setLore(Collections.singletonList("§3Challenges de difficulté Normal."));
                book2.setItemMeta(book2x);

                ItemStack book3 = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta book3x = book3.getItemMeta();
                book3x.setDisplayName("§aChallenges: §dNiveau 3");
                book3x.setLore(Collections.singletonList("§3Challenges de difficulté Moyenne."));
                book3.setItemMeta(book3x);

                ItemStack book4 = new ItemStack(Material.ENCHANTED_BOOK);
                ItemMeta book4x = book4.getItemMeta();
                book4x.setDisplayName("§aChallenges: §dNiveau 4");
                book4x.setLore(Collections.singletonList("§3Challenges de difficulté Difficile."));
                book4x.addEnchant(Enchantment.ARROW_DAMAGE, 1,true);
                book4x.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                book4.setItemMeta(book4x);

                inv.setItem(10, book1);
                inv.setItem(12, book2);
                inv.setItem(14, book3);
                inv.setItem(16, book4);

                p.openInventory(inv);

                IslandLevel skyBlock = SkyBlockAPI.getIslandManager().getIsland(p).getLevel();
                p.sendMessage("" + skyBlock.getLevel());

            }
        }
        return false;
    }
}
