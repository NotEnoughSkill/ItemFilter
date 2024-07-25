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

public class OtherInventory {
    ItemFilter plugin = ItemFilter.getInstance();

    private static OtherInventory ourInstance = new OtherInventory();

    private Inventory inventory;

    public static OtherInventory getInstance() {
        return ourInstance;
    }

    private List<ItemStack> itemStackList = new ArrayList<>();

    public Inventory otherInventory(Player paramPlayer) {
        String str = this.plugin.color(this.plugin.getConfig().getString("options.specialty-inventory.title"));
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
            this.itemStackList.add((new ItemBuilder("12")).display("Sand").build());
            this.itemStackList.add((new ItemBuilder("13")).display("Gravel").build());
            this.itemStackList.add((new ItemBuilder("2256")).display("Discs").build());
            this.itemStackList.add((new ItemBuilder("122")).display("All Other Blocks").build());
        }
        return this.itemStackList;
    }
}
