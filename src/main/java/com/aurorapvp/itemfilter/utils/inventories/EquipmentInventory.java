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

public class EquipmentInventory {
    ItemFilter plugin = ItemFilter.getInstance();

    private static EquipmentInventory ourInstance = new EquipmentInventory();

    private Inventory inventory;

    public static EquipmentInventory getInstance() {
        return ourInstance;
    }

    private List<ItemStack> itemStackList = new ArrayList<>();

    public Inventory equipmentInventory(Player paramPlayer) {
        String str = this.plugin.color(this.plugin.getConfig().getString("options.equipment-inventory.title"));
        this.inventory = Bukkit.createInventory(null, 36, str);
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
            this.itemStackList.add((new ItemBuilder("306")).display("Iron Helmet").build());
            this.itemStackList.add((new ItemBuilder("307")).display("Iron Chestplate").build());
            this.itemStackList.add((new ItemBuilder("308")).display("Iron Leggings").build());
            this.itemStackList.add((new ItemBuilder("309")).display("Iron Boots").build());
            this.itemStackList.add((new ItemBuilder("267")).display("Iron Sword").build());
            this.itemStackList.add((new ItemBuilder("258")).display("Iron Axe").build());
            this.itemStackList.add((new ItemBuilder("257")).display("Iron Pick").build());
            this.itemStackList.add((new ItemBuilder("256")).display("Iron Shovel").build());
            this.itemStackList.add((new ItemBuilder("292")).display("Iron Hoe").build());
            this.itemStackList.add((new ItemBuilder("314")).display("Gold Helmet").build());
            this.itemStackList.add((new ItemBuilder("315")).display("Gold Chestplate").build());
            this.itemStackList.add((new ItemBuilder("316")).display("Gold Leggings").build());
            this.itemStackList.add((new ItemBuilder("317")).display("Gold Boots").build());
            this.itemStackList.add((new ItemBuilder("283")).display("Gold Sword").build());
            this.itemStackList.add((new ItemBuilder("286")).display("Gold Axe").build());
            this.itemStackList.add((new ItemBuilder("285")).display("Gold Pick").build());
            this.itemStackList.add((new ItemBuilder("284")).display("Gold Shovel").build());
            this.itemStackList.add((new ItemBuilder("294")).display("Gold Hoe").build());
            this.itemStackList.add((new ItemBuilder("310")).display("Diamond Helmet").build());
            this.itemStackList.add((new ItemBuilder("311")).display("Diamond Chestplate").build());
            this.itemStackList.add((new ItemBuilder("312")).display("Diamond Leggings").build());
            this.itemStackList.add((new ItemBuilder("313")).display("Diamond Boots").build());
            this.itemStackList.add((new ItemBuilder("276")).display("Diamond Sword").build());
            this.itemStackList.add((new ItemBuilder("279")).display("Diamond Axe").build());
            this.itemStackList.add((new ItemBuilder("278")).display("Diamond Pick").build());
            this.itemStackList.add((new ItemBuilder("277")).display("Diamond Shovel").build());
            this.itemStackList.add((new ItemBuilder("293")).display("Diamond Hoe").build());
            this.itemStackList.add((new ItemBuilder("298")).display("Leather Helmet").build());
            this.itemStackList.add((new ItemBuilder("299")).display("Leather Chestplate").build());
            this.itemStackList.add((new ItemBuilder("300")).display("Leather Legs").build());
            this.itemStackList.add((new ItemBuilder("301")).display("Leather Boots").build());
            this.itemStackList.add((new ItemBuilder("261")).display("Bow").build());
            this.itemStackList.add((new ItemBuilder("262")).display("Arrow").build());
            this.itemStackList.add((new ItemBuilder("368")).display("Ender Pearl").build());
            this.itemStackList.add((new ItemBuilder("373")).display("Potions").build());
            this.itemStackList.add((new ItemBuilder("397:3")).display("Player Skulls").build());
        }
        return this.itemStackList;
    }
}
