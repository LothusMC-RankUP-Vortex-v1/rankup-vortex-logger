package br.net.rankup.logger.listener;

import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.models.user.UserModel;
import com.mysql.jdbc.log.Log;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerKillListener implements Listener {


    private static LogPlugin plugin;

    public PlayerKillListener(LogPlugin logPlugin) {
        this.plugin = logPlugin;
    }

    @EventHandler
    public void onCommand(PlayerDeathEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        if(!(event.getEntity().getKiller() instanceof Player)) return;
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        LogPlugin.getInstance().getLogManager().registerKill(killer, player, true);
        LogPlugin.getInstance().getLogManager().registerKill(player, killer, false);
    }

}
