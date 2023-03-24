package br.net.rankup.logger.listener;

import br.net.rankup.logger.LogPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {


    private static LogPlugin plugin;

    public ItemListener(LogPlugin logPlugin) {
        this.plugin = logPlugin;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItemDrop().getItemStack();
        LogPlugin.getInstance().getLogManager().registerItemDrop(player, event.getItemDrop().getLocation(), itemStack);
    }

    @EventHandler
    public void onPickUP(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem().getItemStack();
        LogPlugin.getInstance().getLogManager().registerItemPickUP(player, event.getItem().getLocation(), itemStack);
    }


}
