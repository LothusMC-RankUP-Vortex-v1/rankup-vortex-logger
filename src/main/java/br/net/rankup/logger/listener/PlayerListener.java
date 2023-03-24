package br.net.rankup.logger.listener;

import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.models.server.*;
import br.net.rankup.logger.models.system.*;
import br.net.rankup.logger.models.user.UserModel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if (userModel != null) {
            LogPlugin.getInstance().getUsersRepository().update(userModel);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if (userModel == null) {
            List<MessageModel> messages = new ArrayList<>();
            List<CommandModel> commnds = new ArrayList<>();
            List<ItemPickUPModel> itemPickUP = new ArrayList<>();
            List<ItemDropModel> itemDrop = new ArrayList<>();
            List<KillsModel> kills = new ArrayList<>();

            List<BossModel> bossModels = new ArrayList<>();
            List<CrateModel> crateModels = new ArrayList<>();
            List<EconomyModel> economyModels = new ArrayList<>();
            List<EnchantModel> enchantModels = new ArrayList<>();
            List<EventModel> eventModels = new ArrayList<>();
            List<FragmentoModel> fragmentoModels = new ArrayList<>();
            List<KitsModel> kitsModels = new ArrayList<>();
            List<RankModel> rankModels = new ArrayList<>();
            List<SpawnerModel> spawnerModels = new ArrayList<>();
            List<StorableModel> storableModels = new ArrayList<>();


            userModel = new UserModel(player.getName(), messages, commnds, itemPickUP, itemDrop, kills,
                    bossModels, crateModels, economyModels, enchantModels, eventModels, fragmentoModels
                    , kitsModels, rankModels, spawnerModels, storableModels);
            LogPlugin.getInstance().getLogManager().getUsers().put(player.getName(), userModel);
        }
    }

}
