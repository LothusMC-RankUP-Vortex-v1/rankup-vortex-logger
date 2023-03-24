package br.net.rankup.logger.listener;

import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.inventory.LogInventory;
import br.net.rankup.logger.models.user.UserModel;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {


    private static LogPlugin plugin;

    public CommandListener(LogPlugin logPlugin) {
        this.plugin = logPlugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        LogPlugin.getInstance().getLogManager().registerCommand(player, event.getMessage(), player.getWorld());
    }

}
