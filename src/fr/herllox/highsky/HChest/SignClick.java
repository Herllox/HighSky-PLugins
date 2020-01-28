package fr.herllox.highsky.HChest;

import fr.herllox.highsky.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_14_R1.block.CraftBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Directional;

import java.util.Arrays;
import java.util.List;


public class SignClick implements Listener {

    private String Object;
    private String Amount;
    private String Price;


    @EventHandler
    public void onSignChange(SignChangeEvent e){

        Player p = e.getPlayer();
        String[] line = e.getLines();

        if (line[0].equalsIgnoreCase(p.getName()) && line[1].isEmpty() && !line[2].isEmpty() && !line[3].isEmpty()) {
            int number = 0;
            try {
                number = Integer.parseInt(e.getLine(2));
            }catch(NumberFormatException e1){
                e.setCancelled(true);
                return;
            }
            if(e.getLine(2).equals("0") || number >= 65){
                p.sendMessage("§6§lHigh§b§lSky §7§l>> §cErreur lors de la création.");
            }else {
                Amount = line[2];
                Price = line[3];
                e.setLine(0, "§f§l"+ p.getName());
                e.setLine(2, "§3Nbr: §e" + Amount);
                e.setLine(3, "§3Prix: §e" + Price);
                Block block = e.getBlock().getRelative(BlockFace.SOUTH);
            }

        }


    }

    @Deprecated
    @EventHandler
    public void onClickSignP(PlayerInteractEvent e){

        Player p = e.getPlayer();
        Action a = e.getAction();


        if (e.getClickedBlock() != null && a == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getType() == Material.OAK_WALL_SIGN) {
                Material pb = p.getInventory().getItemInMainHand().getType();
                Sign sign = (Sign) e.getClickedBlock().getState();
                Block bb = sign.getBlock();
                Chest chest = null;
                List<Block> blockList = Arrays.asList(bb.getRelative(BlockFace.NORTH), bb.getRelative(BlockFace.EAST), bb.getRelative(BlockFace.SOUTH), bb.getRelative(BlockFace.WEST));
                for (int i = 0; i < blockList.size(); i++){
                    if(blockList.get(i).getType() == Material.CHEST){
                        chest = (Chest) blockList.get(i).getState();
                    }
                }

                String line0 = sign.getLine(0);
                if (line0.contains(p.getName())) {
                    if (sign.getLine(1).isEmpty()) {
                        if(!(pb == null)) {
                            if(pb != Material.AIR){
                                sign.setLine(1, pb.toString());
                                sign.update();
                            }else {
                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu ne peux pas vendre de l'Air.");
                            }

                        }else{
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu dois avoir quelque chose dans la main.");
                        }
                    }else{
                        p.sendMessage("§6§lHigh§b§lSky §7§l>> §cShop déjà mis en place.");
                    }
                } else {
                    if(!sign.getLine(2).isEmpty() && !sign.getLine(0).equals(p.getName().replace("§f§l", ""))) {
                        String Number = sign.getLine(2).replace("§3", "").replace("§e", "").replace(" ", "");
                        String[] Numbers = Number.split(":");
                        Integer Numberss = Integer.valueOf(Numbers[1]);


                        String Price = sign.getLine(3).replace("§3", "").replace("§e", "").replace(" ", "");
                        String[] Prices = Price.split(":");
                        Integer Pricess = 0;
                        try{
                            Pricess = Integer.valueOf(Prices[1]);
                        }catch(NumberFormatException e1){
                            e.setCancelled(true);
                            return;
                        }
                        Material Mat = Material.getMaterial(sign.getLine(1));
                        if (chest.getBlockInventory().contains(Material.getMaterial(sign.getLine(1)), Numberss)) {
                            if (Main.econ.has(p, Pricess)) {
                                Main.econ.depositPlayer(sign.getLine(0).replace("§f", "").replace("§l", ""), Pricess);
                                Main.econ.withdrawPlayer(p, Pricess);
                                chest.getInventory().removeItem(new ItemStack(Material.getMaterial(sign.getLine(1)), Numberss));
                                p.getInventory().addItem(new ItemStack(Material.getMaterial(sign.getLine(1)), Numberss));

                                String ss = sign.getLine(0).replace("§f§l", "");
                                Player sss = Bukkit.getPlayer(ss);
                                Player pp = (Player) Bukkit.getOfflinePlayer(sss.getUniqueId());

                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §aTu as acheté§e §ex" + sign.getLine(2).replace("§3Nbr: §e", "") + " " + sign.getLine(1).replace("§f§l", "") + "§a pour§e " + Pricess + "$§a.");
                                pp.sendMessage("§6§lHigh§b§lSky §7§l>> §a"+ p.getName() + " a acheté§e x" + sign.getLine(2).replace("§3Nbr: §e", "") + " "+ sign.getLine(1).replace("§f§l", "") + "§a pour§e " + Pricess + "$§a.");


                            }
                        } else {
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §cShop vide.");
                        }
                    } else {
                        if(sign.getLine(2).contains("Nbr") && sign.getLine(3).contains("Prix:")){
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §cSe shop n'est pas encore crée.");
                        }
                        else {
                        }
                    }
                }

            }else if (e.getClickedBlock().getType() == Material.CHEST){
                Block b = e.getClickedBlock().getLocation().getBlock().getRelative(BlockFace.NORTH);
                if (b.getState().getType() == Material.OAK_WALL_SIGN) {
                    Sign sign = (Sign) b.getState();

                    if(!sign.getLine(0).isEmpty() && !sign.getLine(1).isEmpty() && sign.getLine(2).contains("Nbr:") && sign.getLine(3).contains("Prix:")){
                        if(!sign.getLine(0).replace("§f§l", "").equalsIgnoreCase(p.getName())){
                            e.setCancelled(true);
                            p.sendMessage("§6§lHigh§b§lSky §7§l>> §cCe shop ne t'appartient pas.");
                        }
                    }
                }
            }
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){

        Player p = e.getPlayer();
        Block b = e.getBlock();
        Block bb = b.getRelative(BlockFace.NORTH);
        Block bbb = b.getRelative(BlockFace.SOUTH);

        if(b.getType() == Material.CHEST){
            if(bb.getType() == Material.OAK_WALL_SIGN){
                Sign sign = (Sign) bb.getState();
                if(!p.hasPermission("hsign.breakmodo")) {
                    if (!sign.getLine(0).replace("§f§l", "").equalsIgnoreCase(p.getName()) && !sign.getLine(1).isEmpty() && sign.getLine(2).replace("§3Nbr: §e", "Nbr:").contains("Nbr:") && sign.getLine(3).replace("§3Prix: §e", "Prix:").contains("Prix:")) {
                        p.sendMessage("§6§lHigh§b§lSky §7§l>> §cCe shop ne t'appartient pas.");
                        e.setCancelled(true);
                    }
                }
            }
        }
        if(b.getType() == Material.OAK_WALL_SIGN){
            if(bbb.getType() == Material.CHEST){
                Sign sign = (Sign) b.getState();
                if(!p.hasPermission("hsign.breakmodo")) {
                    if (!sign.getLine(0).replace("§f§l", "").equalsIgnoreCase(p.getName()) && !sign.getLine(1).isEmpty() && sign.getLine(2).replace("§3Nbr: §e", "Nbr:").contains("Nbr:") && sign.getLine(3).replace("§3Prix: §e", "Prix:").contains("Prix:")) {
                        p.sendMessage("§6§lHigh§b§lSky §7§l>> §cCe shop ne t'appartient pas.");
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
