package com.aurorapvp.itemfilter.commands;

import com.aurorapvp.itemfilter.ItemFilter;
import com.aurorapvp.itemfilter.data.DataHandler;
import com.aurorapvp.itemfilter.utils.inventories.MainInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandOther implements CommandExecutor {
    ItemFilter plugin = ItemFilter.getInstance();

    @Override
    public boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString) {
        Player player = (Player) paramCommandSender;
        if (paramCommandSender instanceof Player) {
            if (paramArrayOfString.length == 0) {
                if (paramCommand.getName().equalsIgnoreCase("ft")) {
                    if (this.plugin.toggleList.contains(player.getUniqueId())) {
                        this.plugin.toggleList.remove(player.getUniqueId());
                        for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-disable"))
                            paramCommandSender.sendMessage(this.plugin.color(str));
                        return true;
                    }
                    this.plugin.toggleList.add(player.getUniqueId());
                    for (String str : DataHandler.getInstance().getLang().getStringList("messages.item-filter-enable"))
                        paramCommandSender.sendMessage(this.plugin.color(str));
                }
            } else if (paramArrayOfString.length > 0) {
                if (paramArrayOfString[0].equalsIgnoreCase("edit")) {
                    MainInventory.getInstance().mainInventory(player);
                    return true;
                }
            }
        }
        return false;
    }
}
