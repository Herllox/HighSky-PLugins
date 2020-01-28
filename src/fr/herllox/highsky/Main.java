package fr.herllox.highsky;

import fr.herllox.highsky.Commands.Generator;
import fr.herllox.highsky.Commands.ReloadConfig;
import fr.herllox.highsky.Events.*;
import fr.herllox.highsky.HChest.SignClick;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Chat chat = null;


    @Override
    public void onEnable() {
        System.out.println("HEY BRO");
        saveDefaultConfig();

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        setupChat();

        getCommand("bitch").setExecutor(new AccesNether(this));
        getCommand("highsky").setExecutor(new ReloadConfig(this));
        getCommand("generator").setExecutor(new Generator());


        Bukkit.getPluginManager().registerEvents(new MOTD(this), this);
        Bukkit.getPluginManager().registerEvents(new Generator(), this);
        Bukkit.getPluginManager().registerEvents(new SignClick(), this);
        Bukkit.getPluginManager().registerEvents(new VoidSpawn(), this);
        //Bukkit.getPluginManager().registerEvents(new JoinBuilder(this), this);


    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }


    public static Economy getEconomy() {
        return econ;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Chat getChat() {
        return chat;
    }
}
