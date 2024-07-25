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

public class RedstoneInventory {
    ItemFilter plugin = ItemFilter.getInstance();

    private static RedstoneInventory ourInstance = new RedstoneInventory();

    private Inventory inventory;

    public static RedstoneInventory getInstance() {
        return ourInstance;
    }

    private List<ItemStack> itemStackList = new ArrayList<>();

    public Inventory redstoneInventory(Player paramPlayer) {
        String str = this.plugin.color(this.plugin.getConfig().getString("options.redstone-inventory.title"));
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
            this.itemStackList.add((new ItemBuilder("331")).display("Redstone").build());
            this.itemStackList.add((new ItemBuilder("123")).display("Redstone Lamp").build());
            this.itemStackList.add((new ItemBuilder("76")).display("Redstone Torch").build());
            this.itemStackList.add((new ItemBuilder("73")).display("Redstone Ore").build());
            this.itemStackList.add((new ItemBuilder("404")).display("Redstone Comparator").build());
            this.itemStackList.add((new ItemBuilder("356")).display("Redstone Repeater").build());
            this.itemStackList.add((new ItemBuilder("327")).display("Lava Bucket").build());
            this.itemStackList.add((new ItemBuilder("326")).display("Water Bucket").build());
            this.itemStackList.add((new ItemBuilder("46")).display("Tnt").build());
            this.itemStackList.add((new ItemBuilder("30")).display("Web").build());
            this.itemStackList.add((new ItemBuilder("19")).display("Sponge").build());
            this.itemStackList.add((new ItemBuilder("79")).display("Ice").build());
            this.itemStackList.add((new ItemBuilder("174")).display("Packed Ice").build());
            this.itemStackList.add((new ItemBuilder("23")).display("Dispenser").build());
            this.itemStackList.add((new ItemBuilder("158")).display("Dropper").build());
            this.itemStackList.add((new ItemBuilder("383")).display("Spawner Eggs").build());
            this.itemStackList.add((new ItemBuilder("33")).display("Piston").build());
            this.itemStackList.add((new ItemBuilder("29")).display("Sticky Piston").build());
            this.itemStackList.add((new ItemBuilder("154")).display("Hopper").build());
            this.itemStackList.add((new ItemBuilder("259")).display("Flint And Steel").build());
        }
        return this.itemStackList;
    }
}
