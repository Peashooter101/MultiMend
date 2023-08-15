package com.adhdmc.MultiMend.listeners;

import net.minecraft.world.inventory.AnvilMenu;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftAnvil;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryAnvil;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerItemMendEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public class MendItem implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onMendItem(PlayerItemMendEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onAnvilInteract(PrepareAnvilEvent event) {
        AnvilInventory anvil = event.getInventory();
        if (anvil.getFirstItem() == null || anvil.getSecondItem() == null) return;
        if (!anvil.getFirstItem().containsEnchantment(Enchantment.MENDING)) return;
        if (!anvil.getSecondItem().canRepair(anvil.getFirstItem())) return;
        if (anvil.getResult() == null) return;
        /* Game reads repair cost as 2x + 1, where x is the number of uses.
         * Cost of any repair will double the price.
         */
//        ItemStack result = anvil.getFirstItem();
//        net.minecraft.world.item.ItemStack resultHandle = ((CraftItemStack) result).handle;
//        resultHandle.setRepairCost(anvil.getRepairCost()-1);
//        anvil.setRepairCost(0);
//        anvil.setResult(result);
        anvil.setRepairCost(0);
        anvil.setMaximumRepairCost();
    }

    public int calculatePreviousCost(int cost) {
        return Math.max(((cost - 1) / 2), 0);
    }

}
