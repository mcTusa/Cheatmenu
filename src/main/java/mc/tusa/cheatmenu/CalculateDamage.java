package mc.tusa.cheatmenu;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

// LOL FUN CLASS FOR CALCULATING DAMAGE FROM WEAPON
public class CalculateDamage {
    public static void CalculateItemAttackDamage(Player player, Player victim, ItemStack item) {
        double attackDamage = getDamage(item);
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
        PotionEffect weakness = p.getPotionEffect(PotionEffectType.WEAKNESS);
        int weaknessInt = weakness == null ? 0 : weakness.getAmplifier() + 1;
        PotionEffect strength = p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE);
        int strengthInt = strength == null ? 0 : strength.getAmplifier();
        double points = p.getAttribute(Attribute.GENERIC_ARMOR).getValue();
        double toughness = p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
        PotionEffect effect = p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        int resistance = effect == null ? 0 : effect.getAmplifier();
        int epf = getEPF(p.getInventory());

        p.damage(calculateDamageApplied((damage - weaknessInt * 3 + strengthInt * 3), points, toughness, resistance, epf), damager);
    }

    public static double calculateDamageApplied(double damage, double points, double toughness, int resistance, int epf) {
        double withArmorAndToughness = damage * (1 - Math.min(20, Math.max(points / 5, points - damage / (2 + toughness / 4))) / 25);
        double withResistance = withArmorAndToughness * (1 - (resistance * 0.2));
        double withEnchants = withResistance * (1 - (Math.min(20.0, epf) / 25));
        return withEnchants;
    }

    public static int getEPF(PlayerInventory inv) {
        ItemStack helm = inv.getHelmet();
        ItemStack chest = inv.getChestplate();
        ItemStack legs = inv.getLeggings();
        ItemStack boot = inv.getBoots();

        return (helm != null ? helm.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) : 0) +
                (chest != null ? chest.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) : 0) +
                (legs != null ? legs.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) : 0) +
                (boot != null ? boot.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) : 0);
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

        //AXE
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
        }

        //PICKAXE
        else if (item.getType().equals(Material.NETHERITE_PICKAXE)) {
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
        }
        else if (item.getType().equals(Material.NETHERITE_SHOVEL)) {
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
        }
        else if (item.getType().equals(Material.TRIDENT)) {
            attackDamage = 9;
        }
        else {
            attackDamage = 1;
        }

        return attackDamage;
    }
}
