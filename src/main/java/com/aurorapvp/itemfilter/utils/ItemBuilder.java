package com.aurorapvp.itemfilter.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
    private Material material;

    private int amount = 1;

    private final short damage = 0;

    private byte data = 0;

    private boolean glow = false;

    private String display = "";

    private List<String> lore = new ArrayList<>();

    public ItemBuilder(Material paramMaterial) {
        this.material = paramMaterial;
    }

    public ItemBuilder(String paramString, Material paramMaterial) {
        paramString = paramString.toUpperCase().replace(" ", "_");
        if (paramString.contains(":")) {
            String[] arrayOfString = paramString.split(":");
            try {
                this.material = Material.getMaterial(Integer.parseInt(arrayOfString[0]));
                this.material.getData();
            } catch (Exception exception) {
                this.material = Material.getMaterial(arrayOfString[0]);
            }
            try {
                this.data = Byte.parseByte(arrayOfString[1]);
            } catch (Exception exception) {}
        } else {
            try {
                this.material = Material.getMaterial(Integer.parseInt(paramString));
                this.material.getData();
            } catch (Exception exception) {
                this.material = Material.getMaterial(paramString);
            }
        }
        if (this.material == null)
            this.material = paramMaterial;
    }

    public ItemBuilder(String paramString) {
        paramString = paramString.toUpperCase().replace(" ", "_");
        if (paramString != null)
            if (paramString.contains(":")) {
                String[] arrayOfString = paramString.split(":");
                try {
                    this.material = Material.getMaterial(Integer.parseInt(arrayOfString[0]));
                } catch (Exception exception) {
                    this.material = Material.getMaterial(arrayOfString[0]);
                }
                try {
                    this.data = Byte.parseByte(arrayOfString[1]);
                } catch (Exception exception) {}
            } else {
                try {
                    this.material = Material.getMaterial(Integer.parseInt(paramString));
                } catch (Exception exception) {
                    this.material = Material.getMaterial(paramString);
                }
            }
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(this.material);
        itemStack.setDurability((short)this.data);
        if (this.amount > 0) {
            itemStack.setAmount(this.amount);
        } else {
            itemStack.setAmount(1);
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!this.display.equals(""))
            itemMeta.setDisplayName(this.display);
        if (this.glow) {
            itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        }
        if (!this.lore.isEmpty())
            itemMeta.setLore(this.lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemBuilder amount(int paramInt) {
        this.amount = paramInt;
        return this;
    }

    public ItemBuilder display(String paramString) {
        this.display = paramString;
        return this;
    }

    public ItemBuilder display(char paramChar, String paramString) {
        this.display = ChatColor.translateAlternateColorCodes(paramChar, paramString);
        return this;
    }

    public ItemBuilder lore(List<String> paramList) {
        this.lore = paramList;
        return this;
    }

    public ItemBuilder lore(char paramChar, List<String> paramList) {
        for (byte b = 0; b < paramList.size(); b++)
            paramList.set(b, ChatColor.translateAlternateColorCodes(paramChar, paramList.get(b)));
        this.lore = paramList;
        return this;
    }

    public ItemBuilder glow(boolean paramBoolean) {
        this.glow = paramBoolean;
        return this;
    }

    public static ItemStack getPlayerSkull(String paramString1, String paramString2) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
        SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', paramString2));
        skullMeta.setOwner(paramString1);
        itemStack.setItemMeta((ItemMeta)skullMeta);
        return itemStack;
    }

    public static ItemStack replaceInMeta(ItemStack paramItemStack, String... paramVarArgs) {
        paramItemStack = paramItemStack.clone();
        ItemMeta itemMeta = paramItemStack.getItemMeta();
        for (byte b = 0; b < paramVarArgs.length; b += 2) {
            if (paramVarArgs.length > b) {
                String str1 = paramVarArgs[b];
                String str2 = paramVarArgs[b + 1];
                itemMeta.setDisplayName(itemMeta.getDisplayName().replace(str1, str2));
                List<String> list = itemMeta.hasLore() ? itemMeta.getLore() : Collections.emptyList();
                for (byte b1 = 0; b1 < list.size(); b1++)
                    list.set(b1, ((String)list.get(b1)).replace(str1, str2));
                itemMeta.setLore(list);
            }
        }
        paramItemStack.setItemMeta(itemMeta);
        return paramItemStack;
    }
}
