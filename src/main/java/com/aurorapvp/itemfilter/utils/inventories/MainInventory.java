package com.aurorapvp.itemfilter.utils.inventories;

import com.aurorapvp.itemfilter.ItemFilter;
import com.aurorapvp.itemfilter.data.UserDataHandler;
import com.aurorapvp.itemfilter.utils.ItemBuilder;
import com.mojang.authlib.yggdrasil.response.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MainInventory {
    ItemFilter plugin = ItemFilter.getInstance();

    private static MainInventory ins = new MainInventory();

    private Inventory inventory;

    public static MainInventory getInstance() {
        return ins;
    }

    public Inventory mainInventory(Player paramPlayer) {
        int i = this.plugin.getConfig().getInt("options.main-inventory.size");
        String str = this.plugin.color(this.plugin.getConfig().getString("options.main-inventory.title"));
        this.inventory = Bukkit.createInventory(null, i, str);
        if (this.plugin.getConfig().get("options.main-inventory.settings.spaces") != null &&
                this.plugin.getConfig().getBoolean("options.main-inventory.settings.spaces.enable")) {
            String str1 = this.plugin.color(this.plugin.getConfig().getString("options.main-inventory.settings.spaces.name"));
            String str2 = this.plugin.getConfig().getString("options.main-inventory.settings.spaces.id");
            List list = this.plugin.getConfig().getStringList("options.main-inventory.settings.spaces.lore");
            boolean bool = this.plugin.getConfig().getBoolean("options.main-inventory.settings.spaces.glow");
            ItemStack itemStack = (new ItemBuilder(str2)).display(str1).lore('&', list).glow(bool).build();
            for (byte b = 0; b < i; b++) {
                this.inventory.setItem(b, itemStack);
            }
        }
        if (this.plugin.getConfig().get("options.main-inventory.items") != null) {
            this.inventory.setItem(this.plugin.getConfig().getInt("options.main-inventory.items.Equipment.slot"), loadEquipment(paramPlayer));
            this.inventory.setItem(this.plugin.getConfig().getInt("options.main-inventory.items.Food.slot"), loadFood(paramPlayer));
            this.inventory.setItem(this.plugin.getConfig().getInt("options.main-inventory.items.Ores.slot"), loadOres(paramPlayer));
            this.inventory.setItem(this.plugin.getConfig().getInt("options.main-inventory.items.Potion.slot"), loadPotions(paramPlayer));
            this.inventory.setItem(this.plugin.getConfig().getInt("options.main-inventory.items.Redstone.slot"), loadRedstone(paramPlayer));
            this.inventory.setItem(this.plugin.getConfig().getInt("options.main-inventory.items.Specialty.slot"), loadSpecialty(paramPlayer));
            this.inventory.setItem(this.plugin.getConfig().getInt("options.main-inventory.items.Other.slot"), loadOther(paramPlayer));
        }

        paramPlayer.openInventory(this.inventory);
        return this.inventory;
    }

    private ItemStack loadEquipment(Player paramPlayer) {
        UserDataHandler userDataHandler = new UserDataHandler(paramPlayer.getUniqueId());
        List list = userDataHandler.getUserdata().getStringList("filtered-items");
        ArrayList<String> arrayList1 = new ArrayList<>();
        int i = 1;
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (ItemStack itemStack : EquipmentInventory.getInstance().getFilterItems()) {
            if (list.contains(String.valueOf(itemStack.getType()))) {
                arrayList1.add(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()));
            }
        }
        if (!arrayList1.isEmpty()) {
            i = arrayList1.size();
            arrayList2.add(" ");
            arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-items-line").replace("{0}", String.valueOf(arrayList1.size()))));
            for (String str : arrayList1) {
                arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-list-format").replace("{1}", str)));
            }
        }
        return (new ItemBuilder(this.plugin.getConfig().getString("options.main-inventory.items.Equipment.id"))).display('&', this.plugin.getConfig().getString("options.main-inventory.items.Equipment.name")).lore(arrayList2).amount(i).build();
    }

    private ItemStack loadPotions(Player paramPlayer) {
        UserDataHandler userDataHandler = new UserDataHandler(paramPlayer.getUniqueId());
        List list = userDataHandler.getUserdata().getStringList("filtered-items");
        ArrayList<String> arrayList1 = new ArrayList();
        int i = 1;
        ArrayList<String> arrayList2 = new ArrayList();
        for (ItemStack itemStack : PotionInventory.getInstance().getFilterItems()) {
            if (list.contains(String.valueOf(itemStack.getTypeId())))
                arrayList1.add(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()));
        }
        if (!arrayList1.isEmpty()) {
            i = arrayList1.size();
            arrayList2.add(" ");
            arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-items-line").replace("{0}", String.valueOf(arrayList1.size()))));
            for (String str : arrayList1) {
                arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-list-format").replace("{1}", str)));
            }
        }
        return (new ItemBuilder(this.plugin.getConfig().getString("options.main-inventory.items.Potion.id"))).display('&', this.plugin.getConfig().getString("options.main-inventory.items.Potion.name")).lore(arrayList2).amount(i).build();
    }

    private ItemStack loadRedstone(Player paramPlayer) {
        UserDataHandler userDataHandler = new UserDataHandler(paramPlayer.getUniqueId());
        List list = userDataHandler.getUserdata().getStringList("filtered-items");
        ArrayList<String> arrayList1 = new ArrayList();
        int i = 1;
        ArrayList<String> arrayList2 = new ArrayList();
        for (ItemStack itemStack : RedstoneInventory.getInstance().getFilterItems()) {
            if (list.contains(String.valueOf(itemStack.getTypeId())))
                arrayList1.add(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()));
        }
        if (!arrayList1.isEmpty()) {
            i = arrayList1.size();
            arrayList2.add(" ");
            arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-items-line").replace("{0}", String.valueOf(arrayList1.size()))));
            for (String str : arrayList1) {
                arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-list-format").replace("{1}", str)));
            }
        }
        return (new ItemBuilder(this.plugin.getConfig().getString("options.main-inventory.items.Redstone.id"))).display('&', this.plugin.getConfig().getString("options.main-inventory.items.Redstone.name")).lore(arrayList2).amount(i).build();
    }

    private ItemStack loadFood(Player paramPlayer) {
        UserDataHandler userDataHandler = new UserDataHandler(paramPlayer.getUniqueId());
        List list = userDataHandler.getUserdata().getStringList("filtered-items");
        ArrayList<String> arrayList1 = new ArrayList();
        int i = 1;
        ArrayList<String> arrayList2 = new ArrayList();
        for (ItemStack itemStack : FoodInventory.getInstance().getFilterItems()) {
            if (list.contains(String.valueOf(itemStack.getTypeId())))
                arrayList1.add(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()));
        }
        if (!arrayList1.isEmpty()) {
            i = arrayList1.size();
            arrayList2.add(" ");
            arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-items-line").replace("{0}", String.valueOf(arrayList1.size()))));
            for (String str : arrayList1) {
                arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-list-format").replace("{1}", str)));
            }
        }
        return (new ItemBuilder(this.plugin.getConfig().getString("options.main-inventory.items.Food.id"))).display('&', this.plugin.getConfig().getString("options.main-inventory.items.Food.name")).lore(arrayList2).amount(i).build();
    }

    private ItemStack loadSpecialty(Player paramPlayer) {
        UserDataHandler userDataHandler = new UserDataHandler(paramPlayer.getUniqueId());
        List list = userDataHandler.getUserdata().getStringList("filtered-items");
        ArrayList<String> arrayList1 = new ArrayList();
        int i = 1;
        ArrayList<String> arrayList2 = new ArrayList();
        for (ItemStack itemStack : SpecialtyInventory.getInstance().getFilterItems()) {
            if (list.contains(String.valueOf(itemStack.getTypeId())))
                arrayList1.add(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()));
        }
        if (!arrayList1.isEmpty()) {
            i = arrayList1.size();
            arrayList2.add(" ");
            arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-items-line").replace("{0}", String.valueOf(arrayList1.size()))));
            for (String str : arrayList1) {
                arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-list-format").replace("{1}", str)));
            }
        }
        return (new ItemBuilder(this.plugin.getConfig().getString("options.main-inventory.items.Specialty.id"))).display('&', this.plugin.getConfig().getString("options.main-inventory.items.Specialty.name")).lore(arrayList2).amount(i).build();
    }

    private ItemStack loadOres(Player paramPlayer) {
        UserDataHandler userDataHandler = new UserDataHandler(paramPlayer.getUniqueId());
        List list = userDataHandler.getUserdata().getStringList("filtered-items");
        ArrayList<String> arrayList1 = new ArrayList();
        int i = 1;
        ArrayList<String> arrayList2 = new ArrayList();
        for (ItemStack itemStack : OresInventory.getInstance().getFilterItems()) {
            if (list.contains(String.valueOf(itemStack.getTypeId())))
                arrayList1.add(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()));
        }
        if (!arrayList1.isEmpty()) {
            i = arrayList1.size();
            arrayList2.add(" ");
            arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-items-line").replace("{0}", String.valueOf(arrayList1.size()))));
            arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-items-line").replace("{0}", String.valueOf(arrayList1.size()))));
            for (String str : arrayList1) {
                arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-list-format").replace("{1}", str)));
            }
        }
        return (new ItemBuilder(this.plugin.getConfig().getString("options.main-inventory.items.Ores.id"))).display('&', this.plugin.getConfig().getString("options.main-inventory.items.Ores.name")).lore(arrayList2).amount(i).build();
    }

    private ItemStack loadOther(Player paramPlayer) {
        UserDataHandler userDataHandler = new UserDataHandler(paramPlayer.getUniqueId());
        List list = userDataHandler.getUserdata().getStringList("filtered-items");
        ArrayList<String> arrayList1 = new ArrayList();
        int i = 1;
        ArrayList<String> arrayList2 = new ArrayList();
        for (ItemStack itemStack : OtherInventory.getInstance().getFilterItems()) {
            if (list.contains(String.valueOf(itemStack.getTypeId())))
                arrayList1.add(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName()));
        }
        if (!arrayList1.isEmpty()) {
            i = arrayList1.size();
            arrayList2.add(" ");
            arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-items-line").replace("{0}", String.valueOf(arrayList1.size()))));
            arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-items-line").replace("{0}", String.valueOf(arrayList1.size()))));
            for (String str : arrayList1) {
                arrayList2.add(this.plugin.color(this.plugin.getConfig().getString("options.filtered-list-format").replace("{1}", str)));
            }
        }
        return (new ItemBuilder(this.plugin.getConfig().getString("options.main-inventory.items.Other.id"))).display('&', this.plugin.getConfig().getString("options.main-inventory.items.Other.name")).lore(arrayList2).amount(i).build();
    }
}
