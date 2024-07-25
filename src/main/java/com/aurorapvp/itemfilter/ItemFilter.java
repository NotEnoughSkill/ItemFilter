package com.aurorapvp.itemfilter;

import com.aurorapvp.itemfilter.commands.CommandFilter;
import com.aurorapvp.itemfilter.commands.CommandOther;
import com.aurorapvp.itemfilter.data.DataHandler;
import com.aurorapvp.itemfilter.data.UserDataHandler;
import com.aurorapvp.itemfilter.listeners.GlobalListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ItemFilter extends JavaPlugin {
    private static ItemFilter ins;

    public List<UUID> toggleList = new ArrayList<>();

    public void onEnable() {
        ins = this;
        DataHandler.getInstance().createDataFiles();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            UserDataHandler userDataHandler = new UserDataHandler(player.getUniqueId());
            userDataHandler.createUserData();
        }
        getCommand("filter").setExecutor((CommandExecutor) new CommandFilter());
        getCommand("ft").setExecutor((CommandExecutor) new CommandOther());
        getServer().getPluginManager().registerEvents((Listener) new GlobalListener(), (Plugin) this);
        Bukkit.getConsoleSender().sendMessage("Aurora Filter Loaded!");
    }

    public void onDisable() {
        ins = null;
        if (!this.toggleList.isEmpty()) {
            this.toggleList.clear();
        }
    }

    public static ItemFilter getInstance() {
        return ins;
    }

    public String color(String paramString) {
        return ChatColor.translateAlternateColorCodes('&', paramString);
    }

    public List<String> colorList(List<String> paramList) {
        for (byte b = 0; b < paramList.size(); b++)
            paramList.set(b, ChatColor.translateAlternateColorCodes('&', paramList.get(b)));
        return paramList;
    }
}
