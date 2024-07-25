package com.aurorapvp.itemfilter.commands;

import java.util.ArrayList;

import com.aurorapvp.itemfilter.ItemFilter;
import com.aurorapvp.itemfilter.data.DataHandler;
import com.aurorapvp.itemfilter.data.UserDataHandler;
import com.aurorapvp.itemfilter.utils.inventories.MainInventory;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFilter implements CommandExecutor {
    ItemFilter plugin = ItemFilter.getInstance();

    public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString) {
        Player player = (Player) paramCommandSender;
        if (paramCommandSender instanceof Player) {
            if (paramArrayOfString.length >= 0) {
                switch (paramArrayOfString.length) {
                    case 1:
                        if (paramCommandSender.hasPermission("itemfilter.use")) {
                            if (paramArrayOfString[0].equalsIgnoreCase("edit")) {
                                MainInventory.getInstance().mainInventory(player);
                                return true;
                            }
                            if (paramArrayOfString[0].equalsIgnoreCase("toggle")) {
                                if (this.plugin.toggleList.contains(player.getUniqueId())) {
                                    this.plugin.toggleList.remove(player.getUniqueId());
                                    for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-disable"))
                                        paramCommandSender.sendMessage(this.plugin.color(str));
                                    return true;
                                }
                                this.plugin.toggleList.add(player.getUniqueId());
                                for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-enable"))
                                    paramCommandSender.sendMessage(this.plugin.color(str));
                                return true;
                            }
                        } else {
                            paramCommandSender.sendMessage("No perms");
                            return true;
                        }
                        if (paramCommandSender.hasPermission("itemfilter.admin")) {
                            if (paramArrayOfString[0].equalsIgnoreCase("reload")) {
                                DataHandler.getInstance().reloadFiles();
                                this.plugin.reloadConfig();
                                for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-reload"))
                                    paramCommandSender.sendMessage(this.plugin.color(str));
                                return true;
                            }
                        } else {
                            for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-no-permission"))
                                paramCommandSender.sendMessage(this.plugin.color(str));
                            return true;
                        }
                        if (paramCommandSender instanceof Player)
                            if (paramCommandSender.hasPermission("itemfilter.admin")) {
                                if (paramArrayOfString[0].equalsIgnoreCase("reset")) {
                                    UserDataHandler userDataHandler = new UserDataHandler(((Player)paramCommandSender).getUniqueId());
                                    userDataHandler.getUserdata().set("filtered-items", new ArrayList());
                                    userDataHandler.saveUserData();
                                    for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-reset-self"))
                                        paramCommandSender.sendMessage(this.plugin.color(str));
                                    return true;
                                }
                            } else {
                                for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-no-permission"))
                                    paramCommandSender.sendMessage(this.plugin.color(str));
                                return true;
                            }
                        return false;
                    case 2:
                        if (paramCommandSender.hasPermission("itemfilter.admin")) {
                            if (paramArrayOfString[0].equalsIgnoreCase("reset")) {
                                OfflinePlayer offlinePlayer = Bukkit.getServer().getOfflinePlayer(paramArrayOfString[0]);
                                UserDataHandler userDataHandler = new UserDataHandler(offlinePlayer.getUniqueId());
                                if (userDataHandler.checkFiles()) {
                                    userDataHandler.getUserdata().set("filtered-items", new ArrayList());
                                    userDataHandler.saveUserData();
                                    for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-reset-other"))
                                        paramCommandSender.sendMessage(this.plugin.color(str.replace("{player}", offlinePlayer.getName())));
                                    return true;
                                }
                                for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-target-not-found"))
                                    paramCommandSender.sendMessage(this.plugin.color(str.replace("{player}", paramArrayOfString[1])));
                            }
                        } else {
                            for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-no-permission"))
                                paramCommandSender.sendMessage(this.plugin.color(str));
                            return true;
                        }
                        return false;
                }
                for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-usage"))
                    paramCommandSender.sendMessage(this.plugin.color(str));
            }
        }
        return false;
    }
}
