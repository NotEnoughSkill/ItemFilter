package com.aurorapvp.itemfilter.listeners;

import java.util.List;

import com.aurorapvp.itemfilter.ItemFilter;
import com.aurorapvp.itemfilter.data.UserDataHandler;
import com.aurorapvp.itemfilter.utils.inventories.*;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GlobalListener implements Listener {
    ItemFilter plugin = ItemFilter.getInstance();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent paramInventoryClickEvent) {
        Inventory inventory = paramInventoryClickEvent.getInventory();
        Player player = (Player)paramInventoryClickEvent.getWhoClicked();
        if (inventory.getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.main-inventory.title")))) {
            paramInventoryClickEvent.setCancelled(true);
            int i = paramInventoryClickEvent.getRawSlot();
            if (i <= inventory.getSize() &&
                    paramInventoryClickEvent.getCurrentItem() != null) {
                if (i == this.plugin.getConfig().getInt("options.main-inventory.items.Equipment.slot")) {
                    EquipmentInventory.getInstance().equipmentInventory(player);
                    if (this.plugin.getConfig().getBoolean("options.main-inventory.settings.category-open.enable"))
                        player.playSound(player.getLocation(), Sound.valueOf(this.plugin.getConfig().getString("options.main-inventory.settings.category-open.sound-effect")), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-volume"), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-pitch"));
                }
                if (i == this.plugin.getConfig().getInt("options.main-inventory.items.Potion.slot")) {
                    PotionInventory.getInstance().potionInventory(player);
                    if (this.plugin.getConfig().getBoolean("options.main-inventory.settings.category-open.enable"))
                        player.playSound(player.getLocation(), Sound.valueOf(this.plugin.getConfig().getString("options.main-inventory.settings.category-open.sound-effect")), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-volume"), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-pitch"));
                }
                if (i == this.plugin.getConfig().getInt("options.main-inventory.items.Redstone.slot")) {
                    RedstoneInventory.getInstance().redstoneInventory(player);
                    if (this.plugin.getConfig().getBoolean("options.main-inventory.settings.category-open.enable"))
                        player.playSound(player.getLocation(), Sound.valueOf(this.plugin.getConfig().getString("options.main-inventory.settings.category-open.sound-effect")), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-volume"), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-pitch"));
                }
                if (i == this.plugin.getConfig().getInt("options.main-inventory.items.Food.slot")) {
                    FoodInventory.getInstance().foodInventory(player);
                    if (this.plugin.getConfig().getBoolean("options.main-inventory.settings.category-open.enable"))
                        player.playSound(player.getLocation(), Sound.valueOf(this.plugin.getConfig().getString("options.main-inventory.settings.category-open.sound-effect")), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-volume"), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-pitch"));
                }
                if (i == this.plugin.getConfig().getInt("options.main-inventory.items.Specialty.slot")) {
                    SpecialtyInventory.getInstance().specialtyInventory(player);
                    if (this.plugin.getConfig().getBoolean("options.main-inventory.settings.category-open.enable"))
                        player.playSound(player.getLocation(), Sound.valueOf(this.plugin.getConfig().getString("options.main-inventory.settings.category-open.sound-effect")), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-volume"), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-pitch"));
                }
                if (i == this.plugin.getConfig().getInt("options.main-inventory.items.Ores.slot")) {
                    OresInventory.getInstance().oresInventory(player);
                    if (this.plugin.getConfig().getBoolean("options.main-inventory.settings.category-open.enable"))
                        player.playSound(player.getLocation(), Sound.valueOf(this.plugin.getConfig().getString("options.main-inventory.settings.category-open.sound-effect")), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-volume"), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-pitch"));
                }
                if (i == this.plugin.getConfig().getInt("options.main-inventory.items.Other.slot")) {
                    OtherInventory.getInstance().otherInventory(player);
                    if (this.plugin.getConfig().getBoolean("options.main-inventory.settings.category-open.enable"))
                        player.playSound(player.getLocation(), Sound.valueOf(this.plugin.getConfig().getString("options.main-inventory.settings.category-open.sound-effect")), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-volume"), this.plugin.getConfig().getInt("options.main-inventory.settings.category-open.sound-pitch"));
                }
            }
        }
        if (inventory.getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.equipment-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.ores-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.specialty-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.food-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.redstone-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.other-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.potion-inventory.title"))))  {
            paramInventoryClickEvent.setCancelled(true);
            ItemStack itemStack = paramInventoryClickEvent.getCurrentItem();
            UserDataHandler userDataHandler = new UserDataHandler(player.getUniqueId());
            List<String> list = userDataHandler.getUserdata().getStringList("filtered-items");
            if (itemStack.hasItemMeta() &&
                    itemStack.getItemMeta().hasDisplayName()) {
                if (list.contains(String.valueOf(itemStack.getTypeId()))) {
                    ItemStack itemStack2 = itemStack;
                    ItemMeta itemMeta1 = itemStack2.getItemMeta();
                    String str1 = this.plugin.color(this.plugin.getConfig().getString("options.WillNot.ItemName").replace("{2}", ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
                    List list2 = this.plugin.colorList(this.plugin.getConfig().getStringList("options.WillNot.ItemLore"));
                    itemMeta1.setDisplayName(str1);
                    itemMeta1.removeEnchant(Enchantment.DURABILITY);
                    itemMeta1.setLore(list2);
                    itemStack2.setItemMeta(itemMeta1);
                    player.updateInventory();
                    list.remove(String.valueOf(itemStack.getTypeId()));
                    userDataHandler.getUserdata().set("filtered-items", list);
                    userDataHandler.saveUserData();
                    if (this.plugin.getConfig().getBoolean("options.main-inventory.settings.filter-item-select.enable"))
                        player.playSound(player.getLocation(), Sound.valueOf(this.plugin.getConfig().getString("options.main-inventory.settings.filter-item-select.sound-effect")), this.plugin.getConfig().getInt("options.main-inventory.settings.filter-item-select.sound-volume"), this.plugin.getConfig().getInt("options.main-inventory.settings.filter-item-select.sound-pitch"));
                    return;
                }
                ItemStack itemStack1 = itemStack;
                ItemMeta itemMeta = itemStack1.getItemMeta();
                String str = this.plugin.color(this.plugin.getConfig().getString("options.Will.ItemName").replace("{2}", ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
                List list1 = this.plugin.colorList(this.plugin.getConfig().getStringList("options.Will.ItemLore"));
                itemMeta.setDisplayName(str);
                itemMeta.setLore(list1);
                itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                itemStack1.setItemMeta(itemMeta);
                player.updateInventory();
                list.add(String.valueOf(itemStack.getTypeId()));
                userDataHandler.getUserdata().set("filtered-items", list);
                userDataHandler.saveUserData();
                if (this.plugin.getConfig().getBoolean("options.main-inventory.options.filter-item-select.enable"))
                    player.playSound(player.getLocation(), Sound.valueOf(this.plugin.getConfig().getString("options.main-inventory.settings.filter-item-select.sound-effect")), this.plugin.getConfig().getInt("options.main-inventory.settings.filter-item-select.sound-volume"), this.plugin.getConfig().getInt("options.main-inventory.settings.filter-item-select.sound-pitch"));
                return;
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent paramPlayerJoinEvent) {
        Player player = paramPlayerJoinEvent.getPlayer();
        UserDataHandler userDataHandler = new UserDataHandler(player.getUniqueId());
        userDataHandler.createUserData();
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent paramPlayerPickupItemEvent) {
        Player player = paramPlayerPickupItemEvent.getPlayer();
        Item item = paramPlayerPickupItemEvent.getItem();
        ItemStack itemStack = item.getItemStack();
        UserDataHandler userDataHandler = new UserDataHandler(player.getUniqueId());
        List list = userDataHandler.getUserdata().getStringList("filtered-items");
        if (this.plugin.toggleList.contains(player.getUniqueId()) &&
                !list.contains(String.valueOf(itemStack.getTypeId())))
            paramPlayerPickupItemEvent.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent paramInventoryCloseEvent) {
        Inventory inventory = paramInventoryCloseEvent.getInventory();
        final Player player = (Player)paramInventoryCloseEvent.getPlayer();
        if (inventory.getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.equipment-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.ores-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.specialty-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.food-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.redstone-inventory.title"))) || inventory
                .getTitle().equalsIgnoreCase(this.plugin.color(this.plugin.getConfig().getString("options.potion-inventory.title")))) {
            (new BukkitRunnable() {
                public void run() {
                    MainInventory.getInstance().mainInventory(player);
                    cancel();
                }
            }).runTaskTimer((Plugin)this.plugin, 5L, 0L);
            if (this.plugin.getConfig().getBoolean("options.main-inventory.settings.category-close.enable"))
                player.playSound(player.getLocation(), Sound.valueOf(this.plugin.getConfig().getString("options.main-inventory.settings.category-close.sound-effect")), this.plugin.getConfig().getInt("options.main-inventory.settings.category-close.sound-volume"), this.plugin.getConfig().getInt("options.main-inventory.settings.category-close.sound-pitch"));
        }
    }
}
