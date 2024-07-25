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

public class PotionInventory {
    ItemFilter plugin = ItemFilter.getInstance();

    private static PotionInventory ourInstance = new PotionInventory();

    private Inventory inventory;

    public static PotionInventory getInstance() {
        return ourInstance;
    }

    private List<ItemStack> itemStackList = new ArrayList<>();

    public Inventory potionInventory(Player paramPlayer) {
        String str = this.plugin.color(this.plugin.getConfig().getString("options.potion-inventory.title"));
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
            this.itemStackList.add((new ItemBuilder("375")).display("Spider Eye").build());
            this.itemStackList.add((new ItemBuilder("378")).display("Magma Cream").build());
            this.itemStackList.add((new ItemBuilder("370")).display("Ghast Tear").build());
            this.itemStackList.add((new ItemBuilder("341")).display("Slime Ball").build());
            this.itemStackList.add((new ItemBuilder("372")).display("Nether Stalk").build());
            this.itemStackList.add((new ItemBuilder("382")).display("Speckled Melon").build());
            this.itemStackList.add((new ItemBuilder("353")).display("Sugar").build());
            this.itemStackList.add((new ItemBuilder("348")).display("Glowstone Dust").build());
            this.itemStackList.add((new ItemBuilder("89")).display("Glowstone").build());
            this.itemStackList.add((new ItemBuilder("396")).display("Golden Carrot").build());
            this.itemStackList.add((new ItemBuilder("373")).display("Potions").build());
            this.itemStackList.add((new ItemBuilder("374")).display("Glass Bottle").build());
            this.itemStackList.add((new ItemBuilder("111")).display("Water Lily").build());
            this.itemStackList.add((new ItemBuilder("391")).display("Carrot Item").build());
            this.itemStackList.add((new ItemBuilder("406")).display("Quartz").build());
            this.itemStackList.add((new ItemBuilder("40")).display("Red Mushroom").build());
            this.itemStackList.add((new ItemBuilder("260")).display("Apple").build());
            this.itemStackList.add((new ItemBuilder("39")).display("Brown Mushroom").build());
            this.itemStackList.add((new ItemBuilder("351")).display("Ink Sack").build());
            this.itemStackList.add((new ItemBuilder("394")).display("Poisonous Potato").build());
            this.itemStackList.add((new ItemBuilder("322")).display("Golden Apple").build());
        }
        return this.itemStackList;
    }
}
