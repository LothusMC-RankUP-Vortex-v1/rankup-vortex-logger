package br.net.rankup.logger.listener;

import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.inventory.LogInventory;
import br.net.rankup.logger.misc.BukkitUtils;
import br.net.rankup.logger.models.server.MessageModel;
import br.net.rankup.logger.models.user.UserModel;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class MessageListener implements Listener {


    private static LogPlugin plugin;

    public MessageListener(LogPlugin logPlugin) {
        this.plugin = logPlugin;
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        LogPlugin.getInstance().getLogManager().registerMessage(player, event.getMessage());
    }

}
