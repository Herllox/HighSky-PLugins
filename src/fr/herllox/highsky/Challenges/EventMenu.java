package fr.herllox.highsky.Challenges;

import fr.herllox.highsky.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class EventMenu implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();

        if(e.getClickedInventory() == null){
            e.setCancelled(true);
        }
        if(e.getView().getTitle().equalsIgnoreCase("§9Challenges")) {
            e.setCancelled(true);
            if(e.getInventory() == null || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta()) {
                p.closeInventory();
                return;
            }
            switch (e.getCurrentItem().getType()){
                case BLACK_STAINED_GLASS_PANE:
                    e.setCancelled(true);
                    break;
                case ENCHANTED_BOOK:
                    if(e.getSlot() == 10){
                        Inventory inv = Bukkit.createInventory(null, 27, "§9Challenges: §aFacile");

                        ItemStack full = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                        ItemMeta fullx = full.getItemMeta();
                        fullx.setDisplayName("§e");
                        fullx.setLore(Collections.singletonList("§e"));
                        full.setItemMeta(fullx);

                        for(int i = 0; i <= 26; i++) {
                            inv.setItem(i, full);
                        }

                        ItemStack cob = new ItemStack(Material.COBBLESTONE);
                        ItemMeta cobx = cob.getItemMeta();
                        cobx.setDisplayName("§7Cobblestone");
                        cobx.setLore(Arrays.asList("","§3Missions:" ,"","  §fMiner 256 cobblestones.", "", "§3Récompense:","","  §aArgent: §f500", "  §aBloques: §f16 Grass Blocks" ,"" ,"§cLes items doivent être dans l'inventaire."));
                        cob.setItemMeta(cobx);

                        inv.setItem(9, cob);

                        p.openInventory(inv);
                    }
                    break;
                default:
                    break;
            }


        }if(e.getView().getTitle().equalsIgnoreCase("§9Challenges: §aFacile")) {
            e.setCancelled(true);
            if(e.getInventory() == null || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta()) {
                p.closeInventory();
                return;
            }
            switch (e.getCurrentItem().getType()){
                case BLACK_STAINED_GLASS_PANE:
                    e.setCancelled(true);
                    break;
                case COBBLESTONE:
                    if(p.getInventory().contains(Material.COBBLESTONE, 256)){
                        p.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 256));
                        p.updateInventory();
                        Main.econ.depositPlayer(p, 500);
                        p.getInventory().addItem(new ItemStack(Material.GRASS_BLOCK, 16));
                        p.closeInventory();
                        Bukkit.broadcastMessage("§b==================================\n§6" + p.getName() + " §aviens de compléter le challenge:\n§eCobblestone.\n" + "§b==================================");
                    }else{
                        p.closeInventory();
                        p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu dois avoir 256 de cobblestone dans ton inventaire.");
                    }
                    break;
                default:
                    break;
            }


        }


    }


}
