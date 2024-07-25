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

public class FoodInventory {
    ItemFilter plugin = ItemFilter.getInstance();

    private static FoodInventory ourInstance = new FoodInventory();

    private Inventory inventory;

    public static FoodInventory getInstance() {
        return ourInstance;
    }

    private List<ItemStack> itemStackList = new ArrayList<>();

    public Inventory foodInventory(Player paramPlayer) {
        String str = this.plugin.color(this.plugin.getConfig().getString("options.food-inventory.title"));
        this.inventory = Bukkit.createInventory(null, 18, str);
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
            this.itemStackList.add((new ItemBuilder("352")).display("Bone").build());
            this.itemStackList.add((new ItemBuilder("391")).display("Carrot Item").build());
            this.itemStackList.add((new ItemBuilder("86")).display("Pumpkin").build());
            this.itemStackList.add((new ItemBuilder("103")).display("Melon Block").build());
            this.itemStackList.add((new ItemBuilder("360")).display("Melon").build());
            this.itemStackList.add((new ItemBuilder("363")).display("Raw Beef").build());
            this.itemStackList.add((new ItemBuilder("364")).display("Cooked Beef").build());
            this.itemStackList.add((new ItemBuilder("338")).display("Sugar Cane").build());
            this.itemStackList.add((new ItemBuilder("81")).display("Cactus").build());
            this.itemStackList.add((new ItemBuilder("319")).display("Pork").build());
            this.itemStackList.add((new ItemBuilder("320")).display("Grilled Pork").build());
            this.itemStackList.add((new ItemBuilder("322")).display("Golden Apple").build());
            this.itemStackList.add((new ItemBuilder("361")).display("Pumpkin Seeds").build());
            this.itemStackList.add((new ItemBuilder("362")).display("Melon Seeds").build());
            this.itemStackList.add((new ItemBuilder("295")).display("Seeds").build());
        }
        return this.itemStackList;
    }
}
