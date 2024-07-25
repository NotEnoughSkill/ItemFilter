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

public class SpecialtyInventory {
    ItemFilter plugin = ItemFilter.getInstance();

    private static SpecialtyInventory ourInstance = new SpecialtyInventory();

    private Inventory inventory;

    public static SpecialtyInventory getInstance() {
        return ourInstance;
    }

    private List<ItemStack> itemStackList = new ArrayList<>();

    public Inventory specialtyInventory(Player paramPlayer) {
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
            this.itemStackList.add((new ItemBuilder("52")).display("Monster Spawners").build());
            this.itemStackList.add((new ItemBuilder("383")).display("Spawner Eggs").build());
            this.itemStackList.add((new ItemBuilder("421")).display("Name Tag").build());
            this.itemStackList.add((new ItemBuilder("54")).display("Chest").build());
            this.itemStackList.add((new ItemBuilder("146")).display("Trapped Chest").build());
            this.itemStackList.add((new ItemBuilder("130")).display("Ender Chest").build());
            this.itemStackList.add((new ItemBuilder("339")).display("Paper").build());
            this.itemStackList.add((new ItemBuilder("395")).display("Empty Map").build());
            this.itemStackList.add((new ItemBuilder("384")).display("Exp Bottle").build());
            this.itemStackList.add((new ItemBuilder("76")).display("Redstone Torch").build());
            this.itemStackList.add((new ItemBuilder("340")).display("Book").build());
            this.itemStackList.add((new ItemBuilder("351")).display("Ink Sack").build());
            this.itemStackList.add((new ItemBuilder("50")).display("Torch").build());
            this.itemStackList.add((new ItemBuilder("388")).display("Emerald").build());
            this.itemStackList.add((new ItemBuilder("378")).display("Magma Cream").build());
            this.itemStackList.add((new ItemBuilder("322")).display("Golden Apple").build());
            this.itemStackList.add((new ItemBuilder("264")).display("Diamond").build());
            this.itemStackList.add((new ItemBuilder("352")).display("Bone").build());
            this.itemStackList.add((new ItemBuilder("368")).display("Ender Pearl").build());
            this.itemStackList.add((new ItemBuilder("369")).display("Blaze Rod").build());
            this.itemStackList.add((new ItemBuilder("399")).display("Nether Star").build());
            this.itemStackList.add((new ItemBuilder("402")).display("Firework Charge").build());
        }
        return this.itemStackList;
    }
}
