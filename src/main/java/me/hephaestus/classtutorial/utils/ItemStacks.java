package me.hephaestus.classtutorial.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class ItemStacks {
    public static ItemStack Pickaxe() {
        // Create an iron pickaxe item stack
        ItemStack pickaxe = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta px = pickaxe.getItemMeta();

        // Set the pickaxe's properties
        px.setUnbreakable(true);
        px.setDisplayName("Miner pickaxe");
        px.addEnchant(Enchantment.DIG_SPEED, 1, false);
        px.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false);

        pickaxe.setItemMeta(px);

        return pickaxe;
    }

    public static ItemStack Sword() {
        // Create an iron sword item stack
        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemMeta sw = sword.getItemMeta();

        // Set the sword's properties
        sw.setUnbreakable(true);
        sw.setDisplayName("Dps sword");

        sword.setItemMeta(sw);

        return sword;
    }

    public static ItemStack Shield() {
        // Create a shield item stack
        ItemStack shield = new ItemStack(Material.SHIELD);
        ItemMeta sd = shield.getItemMeta();

        // Set the shield's properties
        sd.setUnbreakable(true);
        sd.setDisplayName("Tank shield");

        shield.setItemMeta(sd);

        return shield;
    }

    public static ItemStack Arrow() {
        // Create a tipped arrow item stack with instant heal potion effect
        ItemStack arrow = new ItemStack(Material.TIPPED_ARROW, 64);
        PotionMeta ta;
        ta = (PotionMeta) arrow.getItemMeta();

        // Set the arrow's properties
        ta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));

        arrow.setItemMeta(ta);

        return arrow;
    }

    public static ItemStack Bow() {
        // Create a bow item stack
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemMeta healer = bow.getItemMeta();

        // Set the bow's properties
        healer.setUnbreakable(true);
        healer.setDisplayName("Healer bow");

        bow.setItemMeta(healer);

        return bow;
    }

    public static ItemStack Trident() {
        // Create a trident item stack with Loyalty enchantment
        ItemStack trident = new ItemStack(Material.TRIDENT);
        trident.addEnchantment(Enchantment.LOYALTY, 3);
        ItemMeta diver = trident.getItemMeta();

        // Set the trident's properties
        diver.setDisplayName("Diver trident");

        trident.setItemMeta(diver);

        return trident;
    }
}
