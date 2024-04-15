package mc.tusa.cheatmenu.Listeners;

import mc.tusa.cheatmenu.Cheatmenu;
import mc.tusa.cheatmenu.Functions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CheatGUI implements Listener {
    private final Inventory inv;

    Cheatmenu cheatmenu;

    public CheatGUI(Cheatmenu cheatmenu) {
        this.cheatmenu = cheatmenu;
        inv = Bukkit.createInventory(null, 9, "CheatGUI");

        initializeItems();
    }

    public void initializeItems() {
        inv.addItem(createGuiItem(Material.DIAMOND_SWORD, "§c§lAimAssist", "§aUseful AimAssist."));
        inv.addItem(createGuiItem(Material.IRON_HELMET, "§4KillAura", "§aTargets all entities around you."));
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player player = (Player) e.getWhoClicked();

        switch (e.getRawSlot())
        {
            case 0:
                boolean killAura;
                try {
                    killAura = cheatmenu.cheaters.get(player).killAura;
                }
                catch (Exception ignored) {
                    killAura = false;
                }
                if (!cheatmenu.cheaters.containsKey(player))
                {
                    cheatmenu.cheaters.put(player, new Functions(true, false));
                    player.sendMessage("You enabled AimAssist.");
                    cheatmenu.cheatMenuCommand.runTaskAim(player);
                }
                else if (cheatmenu.cheaters.containsKey(player) && cheatmenu.cheaters.get(player).aimAssist)
                {
                    cheatmenu.cheaters.remove(player, new Functions(true, killAura));
                    cheatmenu.cheaters.put(player, new Functions(false, killAura));
                    player.sendMessage("You disabled AimAssist.");
                }
                else if (cheatmenu.cheaters.containsKey(player) && !cheatmenu.cheaters.get(player).aimAssist)
                {
                    cheatmenu.cheaters.remove(player, new Functions(false, killAura));
                    cheatmenu.cheaters.put(player, new Functions(true, killAura));
                    player.sendMessage("You enabled AimAssist.");
                    cheatmenu.cheatMenuCommand.runTaskAim(player);
                }
                break;
            case 1:
                boolean aimAssist;
                try {
                    aimAssist = cheatmenu.cheaters.get(player).aimAssist;
                }
                catch (Exception ignored) {
                    aimAssist = false;
                }
                if (!cheatmenu.cheaters.containsKey(player))
                {
                    cheatmenu.cheaters.put(player, new Functions(false, true));
                    player.sendMessage("You enabled KillAura.");
                    cheatmenu.cheatMenuCommand.runTaskAura(player);
                }
                else if (cheatmenu.cheaters.containsKey(player) && cheatmenu.cheaters.get(player).killAura)
                {
                    cheatmenu.cheaters.remove(player, new Functions(aimAssist, true));
                    cheatmenu.cheaters.put(player, new Functions(aimAssist, false));
                    player.sendMessage("You disabled KillAura.");
                }
                else if (cheatmenu.cheaters.containsKey(player) && !cheatmenu.cheaters.get(player).killAura)
                {
                    cheatmenu.cheaters.remove(player, new Functions(aimAssist, false));
                    cheatmenu.cheaters.put(player, new Functions(aimAssist, true));
                    player.sendMessage("You enabled KillAura.");
                    cheatmenu.cheatMenuCommand.runTaskAura(player);
                }
                break;
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }
}
