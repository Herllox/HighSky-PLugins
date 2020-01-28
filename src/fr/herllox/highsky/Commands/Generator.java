package fr.herllox.highsky.Commands;

import fr.herllox.highsky.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class Generator implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        Player p = (Player)sender;


        if(args.length == 0){

            Inventory inv = Bukkit.createInventory(null, 27, "§a§lLevel des Générateurs");

            ItemStack full = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta fullx = full.getItemMeta();
            fullx.setDisplayName("§e");
            fullx.setLore(Collections.singletonList("§e"));
            full.setItemMeta(fullx);
            for(int i = 0; i <= 26; i++){
                inv.setItem(i, full);
            }

            ItemStack coal = new ItemStack(Material.COAL);
            ItemMeta coalx = coal.getItemMeta();
            coalx.setDisplayName("§a§lGénérateur: §f§lNiveau 1");
            coalx.setLore(Arrays.asList("", "§eDescription:", "", "  §7Cobblestone: §f60%", "  §7Stone: §f15%", "  §7Coal Ore: §f 15%", "  §7Iron Ore: §f8%", "  §7Gold Ore: §f2%", "" ,"§3§lPrix: §6100.000"));
            coal.setItemMeta(coalx);

            ItemStack iron = new ItemStack(Material.IRON_INGOT);
            ItemMeta ironx = iron.getItemMeta();
            ironx.setDisplayName("§a§lGénérateur: §f§lNiveau 2");
            ironx.setLore(Arrays.asList("", "§eDescription:", "", "  §7Cobblestone: §f50%", "  §7Stone: §f10%", "  §7Coal Ore: §f 20%", "  §7Iron Ore: §f15%", "  §7Gold Ore: §f5%", "" ,"§3§lPrix: §6200.000"));
            iron.setItemMeta(ironx);

            ItemStack gold = new ItemStack(Material.GOLD_INGOT);
            ItemMeta goldx = gold.getItemMeta();
            goldx.setDisplayName("§a§lGénérateur: §f§lNiveau 3");
            goldx.setLore(Arrays.asList("", "§eDescription:", "", "  §7Cobblestone: §f40%", "  §7Stone: §f8%", "  §7Coal Ore: §f 22%", "  §7Iron Ore: §f20%", "  §7Gold Ore: §f8%", "  §7Diamond: §f2%", "" ,"§3§lPrix: §6300.000"));
            gold.setItemMeta(goldx);

            ItemStack diam = new ItemStack(Material.DIAMOND);
            ItemMeta diamx = diam.getItemMeta();
            diamx.setDisplayName("§a§lGénérateur: §f§lNiveau 4");
            diamx.setLore(Arrays.asList("", "§eDescription:", "", "  §7Cobblestone: §f35%", "  §7Stone: §f10%", "  §7Coal Ore: §f 25%", "  §7Iron Ore: §f20%", "  §7Gold Ore: §f10%", "  §7Diamond: §f5%", "" ,"§3§lPrix: §6400.000"));
            diam.setItemMeta(diamx);

            ItemStack eme = new ItemStack(Material.EMERALD);
            ItemMeta emex = eme.getItemMeta();
            emex.setDisplayName("§a§lGénérateur: §f§lNiveau 5");
            emex.setLore(Arrays.asList("", "§eDescription:", "", "  §7Cobblestone: §f25%", "  §7Stone: §f10%", "  §7Coal Ore: §f 25%", "  §7Iron Ore: §f20%", "  §7Gold Ore: §f10%", "  §7Diamond: §f10%", "" ,"§3§lPrix: §6500.000"));
            eme.setItemMeta(emex);

            ItemStack bar = new ItemStack(Material.BARRIER);
            ItemMeta barx = bar.getItemMeta();
            barx.setDisplayName("§c§l! Attention !");
            barx.setLore(Arrays.asList("", "§cLes générateurs doivent être","§cacheté par le chef d'île."));
            bar.setItemMeta(barx);

            inv.setItem(9, coal);
            inv.setItem(11, iron);
            inv.setItem(13, gold);
            inv.setItem(15, diam);
            inv.setItem(17, eme);
            inv.setItem(22, bar);

            p.openInventory(inv);

        }


        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if(e.getClickedInventory() == null){
            e.setCancelled(true);
        }
        if(e.getView().getTitle().equalsIgnoreCase("§a§lLevel des Générateurs")) {
            e.setCancelled(true);
            if(e.getInventory() == null || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta()) {
                p.closeInventory();
                return;
            }
            switch (e.getCurrentItem().getType()){
                case BLACK_STAINED_GLASS_PANE:
                    e.setCancelled(true);
                    break;
                case COAL:
                    if(!p.hasPermission("fabledskyblock.generator.niveau_1")) {
                        if (Main.econ.has(p, 100000)) {
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as acheté le générateur de §eNiveau 1§a.");
                            Main.econ.withdrawPlayer(p, 100000);
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add fabledskyblock.generator.niveau_1");
                            p.closeInventory();
                        } else {
                            p.closeInventory();
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu n'as pas assez d'argent.");
                        }
                    }else{
                        p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as dèjà acheté ce Niveau.");
                        p.closeInventory();
                    }
                    break;
                case IRON_INGOT:
                    if(!p.hasPermission("fabledskyblock.generator.niveau_2")) {
                        if (p.hasPermission("fabledskyblock.generator.niveau_1")) {
                            if (Main.econ.has(p, 200000)) {
                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as acheté le générateur de §eNiveau 2§a.");
                                Main.econ.withdrawPlayer(p, 200000);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add fabledskyblock.generator.niveau_2");
                                p.closeInventory();
                            } else {
                                p.closeInventory();
                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu n'as pas assez d'argent.");
                            }
                        } else {
                            p.closeInventory();
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu dois d'abord acheté le §eNiveau 1§c.");
                        }
                    }else{
                        p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as dèjà acheté ce Niveau.");
                        p.closeInventory();
                    }
                    break;
                case GOLD_INGOT:
                    if(!p.hasPermission("fabledskyblock.generator.niveau_3")) {
                        if (p.hasPermission("fabledskyblock.generator.niveau_2")) {
                            if (Main.econ.has(p, 300000)) {
                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as acheté le générateur de §eNiveau 3§a.");
                                Main.econ.withdrawPlayer(p, 300000);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add fabledskyblock.generator.niveau_3");
                                p.closeInventory();
                            } else {
                                p.closeInventory();
                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu n'as pas assez d'argent.");
                            }
                        } else {
                            p.closeInventory();
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu dois d'abord acheté le §eNiveau 2§c.");
                        }
                    }else{
                        p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as dèjà acheté ce Niveau.");
                        p.closeInventory();
                    }
                    break;
                case DIAMOND:
                    if(!p.hasPermission("fabledskyblock.generator.niveau_4")) {
                        if (p.hasPermission("fabledskyblock.generator.niveau_3")) {
                            if (Main.econ.has(p, 400000)) {
                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as acheté le générateur de §eNiveau 4§a.");
                                Main.econ.withdrawPlayer(p, 400000);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add fabledskyblock.generator.niveau_4");
                                p.closeInventory();
                            } else {
                                p.closeInventory();
                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu n'as pas assez d'argent.");
                            }
                        } else {
                            p.closeInventory();
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu dois d'abord acheté le §eNiveau 3§c.");
                        }
                    }else{
                        p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as dèjà acheté ce Niveau.");
                        p.closeInventory();
                    }
                    break;
                case EMERALD:
                    if(!p.hasPermission("fabledskyblock.generator.niveau_5")) {
                        if (p.hasPermission("fabledskyblock.generator.niveau_4")) {
                            if (Main.econ.has(p, 500000)) {
                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as acheté le générateur de §eNiveau 5§a.");
                                Main.econ.withdrawPlayer(p, 500000);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add fabledskyblock.generator.niveau_5");
                                p.closeInventory();
                            } else {
                                p.closeInventory();
                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu n'as pas assez d'argent.");
                            }
                        } else {
                            p.closeInventory();
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu dois d'abord acheté le §eNiveau 4§c.");
                        }
                    }else{
                        p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as dèjà acheté ce Niveau.");
                        p.closeInventory();
                    }
                    break;
                default:
                    break;
            }


        }
    }
}
