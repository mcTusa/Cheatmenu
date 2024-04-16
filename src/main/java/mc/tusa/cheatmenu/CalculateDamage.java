package mc.tusa.cheatmenu;

import mc.tusa.cheatmenu.Commands.CheatMenuCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.logging.Level;

public class CalculateDamage {
    public static void CalculateItemAttackDamage(Player player, Player victim, ItemStack item) {
        double attackDamage = getDamage(item);
        if (CheatMenuCommand.canCrit(player))
            attackDamage *= 1.5;
        int sharpnessLVL;
        try
        {
            sharpnessLVL = item.getEnchantments().size() > 0 ? item.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0;
        }
        catch (Exception e)
        {
            sharpnessLVL = 0;
        }

        if (sharpnessLVL > 0) {
            if (sharpnessLVL == 1) {
                attackDamage += 1;
            } else if (sharpnessLVL == 2) {
                attackDamage += 1.5;
            } else if (sharpnessLVL == 3) {
                attackDamage += 2;
            } else if (sharpnessLVL == 4) {
                attackDamage += 2.5;
            } else if (sharpnessLVL == 5) {
                attackDamage += 3;
            }
        }

        damagePlayer(victim, attackDamage, player);
    }
    public static void damagePlayer(Player p, double damage, Player damager) {
        PotionEffect weakness = damager.getPotionEffect(PotionEffectType.WEAKNESS);
        int weaknessInt = weakness == null ? 0 : weakness.getAmplifier();
        PotionEffect strength = damager.getPotionEffect(PotionEffectType.INCREASE_DAMAGE);
        int strengthInt = strength == null ? 0 : strength.getAmplifier();

        p.damage(damage - weaknessInt * 3 + strengthInt * 3, damager);
    }

    public static double getDamage(ItemStack item)
    {
        double attackDamage;
        if (item.getType().equals(Material.NETHERITE_SWORD)) {
            attackDamage = 8;
        } else if (item.getType().equals(Material.DIAMOND_SWORD)) {
            attackDamage = 7;
        } else if (item.getType().equals(Material.IRON_SWORD)) {
            attackDamage = 6;
        } else if (item.getType().equals(Material.STONE_SWORD)) {
            attackDamage = 5;
        } else if (item.getType().equals(Material.GOLDEN_SWORD)) {
            attackDamage = 4;
        } else if (item.getType().equals(Material.WOODEN_SWORD)) {
            attackDamage = 4;
        }
        else if (item.getType().equals(Material.NETHERITE_AXE)) {
            attackDamage = 10;
        } else if (item.getType().equals(Material.DIAMOND_AXE)) {
            attackDamage = 9;
        } else if (item.getType().equals(Material.IRON_AXE)) {
            attackDamage = 9;
        } else if (item.getType().equals(Material.STONE_AXE)) {
            attackDamage = 9;
        } else if (item.getType().equals(Material.GOLDEN_AXE)) {
            attackDamage = 7;
        } else if (item.getType().equals(Material.WOODEN_AXE)) {
            attackDamage = 7;
        } else if (item.getType().equals(Material.NETHERITE_PICKAXE)) {
            attackDamage = 6;
        } else if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
            attackDamage = 5;
        } else if (item.getType().equals(Material.IRON_PICKAXE)) {
            attackDamage = 4;
        } else if (item.getType().equals(Material.STONE_PICKAXE)) {
            attackDamage = 3;
        } else if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
            attackDamage = 2;
        } else if (item.getType().equals(Material.WOODEN_PICKAXE)) {
            attackDamage = 2;
        } else if (item.getType().equals(Material.NETHERITE_SHOVEL)) {
            attackDamage = 6.5;
        } else if (item.getType().equals(Material.DIAMOND_SHOVEL)) {
            attackDamage = 5.5;
        } else if (item.getType().equals(Material.IRON_SHOVEL)) {
            attackDamage = 4.5;
        } else if (item.getType().equals(Material.STONE_SHOVEL)) {
            attackDamage = 3.5;
        } else if (item.getType().equals(Material.GOLDEN_SHOVEL)) {
            attackDamage = 2.5;
        } else if (item.getType().equals(Material.WOODEN_SHOVEL)) {
            attackDamage = 2.5;
        } else if (item.getType().equals(Material.TRIDENT)) {
            attackDamage = 9;
        }
        else {
            attackDamage = 1;
        }

        return attackDamage;
    }
}
