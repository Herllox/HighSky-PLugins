package fr.herllox.highsky.HChest;

import fr.herllox.highsky.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;



import java.util.Arrays;
import java.util.List;


public class SignClick implements Listener {

    List<Material> blacklisted = Arrays.asList(
            Material.DIAMOND_SHOVEL,
            Material.DIAMOND_HOE,
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_AXE,
            Material.DIAMOND_SWORD,
            Material.DIAMOND_BOOTS,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_HELMET,
            Material.BOW,
            Material.GOLDEN_AXE,
            Material.GOLDEN_HOE,
            Material.GOLDEN_PICKAXE,
            Material.GOLDEN_SHOVEL,
            Material.GOLDEN_SWORD,
            Material.GOLDEN_BOOTS,
            Material.GOLDEN_CHESTPLATE,
            Material.GOLDEN_LEGGINGS,
            Material.GOLDEN_HELMET,
            Material.IRON_AXE,
            Material.IRON_HOE,
            Material.IRON_PICKAXE,
            Material.IRON_AXE,
            Material.IRON_PICKAXE,
            Material.IRON_SHOVEL,
            Material.IRON_SWORD,
            Material.IRON_BOOTS,
            Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS,
            Material.IRON_HELMET,
            Material.WOODEN_AXE,
            Material.WOODEN_HOE,
            Material.WOODEN_PICKAXE,
            Material.WOODEN_SHOVEL,
            Material.WOODEN_SWORD,
            Material.LEATHER_BOOTS,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_LEGGINGS,
            Material.LEATHER_HELMET,
            Material.CHAINMAIL_BOOTS,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS,
            Material.CHAINMAIL_HELMET,
            Material.GOLDEN_HORSE_ARMOR,
            Material.DIAMOND_HORSE_ARMOR,
            Material.IRON_HORSE_ARMOR,
            Material.LEATHER_HORSE_ARMOR,
            Material.TURTLE_HELMET,
            Material.SHIELD,
            Material.TRIDENT,
            Material.CROSSBOW,
            Material.TOTEM_OF_UNDYING,
            Material.SHULKER_BOX,
            Material.LIME_SHULKER_BOX,
            Material.BLACK_SHULKER_BOX,
            Material.BLUE_SHULKER_BOX,
            Material.BROWN_SHULKER_BOX,
            Material.CYAN_SHULKER_BOX,
            Material.GRAY_SHULKER_BOX,
            Material.LIGHT_BLUE_SHULKER_BOX,
            Material.GREEN_SHULKER_BOX,
            Material.LIGHT_GRAY_SHULKER_BOX,
            Material.MAGENTA_SHULKER_BOX,
            Material.ORANGE_SHULKER_BOX,
            Material.PINK_SHULKER_BOX,
            Material.PURPLE_SHULKER_BOX,
            Material.RED_SHULKER_BOX,
            Material.WHITE_SHULKER_BOX,
            Material.YELLOW_SHULKER_BOX,
            Material.BLACK_BED,
            Material.BLUE_BED,
            Material.BROWN_BED,
            Material.CYAN_BED,
            Material.GRAY_BED,
            Material.GREEN_BED,
            Material.LIGHT_BLUE_BED,
            Material.LIGHT_GRAY_BED,
            Material.LIME_BED,
            Material.MAGENTA_BED,
            Material.ORANGE_BED,
            Material.PINK_BED,
            Material.PURPLE_BED,
            Material.RED_BED,
            Material.WHITE_BED,
            Material.YELLOW_BED,
            Material.AIR,
            Material.ENCHANTED_BOOK,
            Material.WRITABLE_BOOK,
            Material.POTION,
            Material.LINGERING_POTION,
            Material.SPLASH_POTION,
            Material.DRAGON_BREATH,
            Material.EXPERIENCE_BOTTLE,
            Material.LAVA_BUCKET,
            Material.WATER_BUCKET,
            Material.MILK_BUCKET,
            Material.COD_BUCKET,
            Material.PUFFERFISH_BUCKET,
            Material.TROPICAL_FISH_BUCKET,
            Material.SALMON_BUCKET,
            Material.MUSIC_DISC_11,
            Material.MUSIC_DISC_13,
            Material.MUSIC_DISC_BLOCKS,
            Material.MUSIC_DISC_CAT,
            Material.MUSIC_DISC_CHIRP,
            Material.MUSIC_DISC_FAR,
            Material.MUSIC_DISC_MALL,
            Material.MUSIC_DISC_MELLOHI,
            Material.MUSIC_DISC_STAL,
            Material.MUSIC_DISC_STRAD,
            Material.MUSIC_DISC_WAIT,
            Material.MUSIC_DISC_WARD,
            Material.GLOBE_BANNER_PATTERN,
            Material.CREEPER_BANNER_PATTERN,
            Material.FLOWER_BANNER_PATTERN,
            Material.MOJANG_BANNER_PATTERN,
            Material.SKULL_BANNER_PATTERN,
            Material.ACACIA_BOAT,
            Material.BIRCH_BOAT,
            Material.DARK_OAK_BOAT,
            Material.JUNGLE_BOAT,
            Material.OAK_BOAT,
            Material.SPRUCE_BOAT,
            Material.ELYTRA,
            Material.MINECART,
            Material.CHEST_MINECART,
            Material.HOPPER_MINECART,
            Material.TNT_MINECART,
            Material.SADDLE,
            Material.FISHING_ROD,
            Material.CARROT_ON_A_STICK,
            Material.SHEARS,
            Material.FLINT_AND_STEEL
    );

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
                            if(!blacklisted.contains(pb)){
                                sign.setLine(1, pb.toString());
                                sign.update();
                            }else {
                                p.sendMessage("§6§lHigh§b§lSky §7§l>> §cTu ne peux pas vendre cet Item.");
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
