package com.aurorapvp.itemfilter.utils.inventories;

import com.aurorapvp.itemfilter.ItemFilter;
import com.aurorapvp.itemfilter.data.UserDataHandler;
import com.aurorapvp.itemfilter.utils.ItemBuilder;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OresInventory {
    ItemFilter plugin = ItemFilter.getInstance();

    private static OresInventory ourInstance = new OresInventory();

    private Inventory inventory;

    public static OresInventory getInstance() {
        return ourInstance;
    }

    private List<ItemStack> itemStackList = new ArrayList<>();

    public Inventory oresInventory(Player paramPlayer) {
        String str = this.plugin.color(this.plugin.getConfig().getString("options.ores-inventory.title"));
        this.inventory = Bukkit.createInventory(null, 27, str);
        byte b = 0;
        UserDataHandler userDataHandler = new UserDataHandler(paramPlayer.getUniqueId());
        List list = userDataHandler.getUserdata().getStringList("filtered-items");
        for (ItemStack itemStack : getFilterItems()) {
            if (list.contains(String.valueOf(itemStack.getTypeId()))) {
                ItemStack itemStack1 = itemStack;
                ItemMeta itemMeta = itemStack1.getItemMeta();
                String str1 = this.plugin.color(this.plugin.getConfig().getString("options.Will.ItemName").replace("{2}", ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
                List list1 = this.plugin.colorList(this.plugin.getConfig().getStringList("options.Will.ItemLore"));
                itemMeta.setDisplayName(str1);
                itemMeta.setLore(list1);
                itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                itemStack1.setItemMeta(itemMeta);
                this.inventory.setItem(b, itemStack1);
            } else if (!list.contains(String.valueOf(itemStack.getTypeId()))) {
                ItemStack itemStack1 = itemStack;
                ItemMeta itemMeta = itemStack1.getItemMeta();
                String str1 = this.plugin.color(this.plugin.getConfig().getString("options.WillNot.ItemName").replace("{2}", ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
                List list1 = this.plugin.colorList(this.plugin.getConfig().getStringList("options.WillNot.ItemLore"));
                itemMeta.setDisplayName(str1);
                itemMeta.removeEnchant(Enchantment.DURABILITY);
                itemMeta.setLore(list1);
                itemStack1.setItemMeta(itemMeta);
                this.inventory.setItem(b, itemStack1);
            }
            b++;
        }
        paramPlayer.openInventory(this.inventory);
        return this.inventory;
    }

    public List<ItemStack> getFilterItems() {
        if (this.itemStackList.isEmpty()) {
            this.itemStackList.add((new ItemBuilder("15")).display("Iron Ore").build());
            this.itemStackList.add((new ItemBuilder("265")).display("Iron Ingot").build());
            this.itemStackList.add((new ItemBuilder("42")).display("Iron Block").build());
            this.itemStackList.add((new ItemBuilder("14")).display("Gold Ore").build());
            this.itemStackList.add((new ItemBuilder("266")).display("Gold Ingot").build());
            this.itemStackList.add((new ItemBuilder("41")).display("Gold Block").build());
            this.itemStackList.add((new ItemBuilder("73")).display("Redstone Ore").build());
            this.itemStackList.add((new ItemBuilder("331")).display("Redstone Ore").build());
            this.itemStackList.add((new ItemBuilder("152")).display("Redstone Block").build());
            this.itemStackList.add((new ItemBuilder("21")).display("Lapis Ore").build());
            this.itemStackList.add((new ItemBuilder("351:4")).display("Lapis Lazuli").build());
            this.itemStackList.add((new ItemBuilder("22")).display("Lapis Block").build());
            this.itemStackList.add((new ItemBuilder("16")).display("Coal Ore").build());
            this.itemStackList.add((new ItemBuilder("263")).display("Coal").build());
            this.itemStackList.add((new ItemBuilder("173")).display("Coal Block").build());
            this.itemStackList.add((new ItemBuilder("129")).display("Emerlad Ore").build());
            this.itemStackList.add((new ItemBuilder("388")).display("Emerald").build());
            this.itemStackList.add((new ItemBuilder("133")).display("Emerald Block").build());
            this.itemStackList.add((new ItemBuilder("56")).display("Diamond Ore").build());
            this.itemStackList.add((new ItemBuilder("264")).display("Diamond").build());
            this.itemStackList.add((new ItemBuilder("57")).display("Diamond Block").build());
        }
        return this.itemStackList;
    }
}
