package br.net.rankup.logger.repository;

import br.net.rankup.logger.adpter.server.*;
import br.net.rankup.logger.adpter.system.*;
import br.net.rankup.logger.database.HikariDataBase;
import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.models.server.*;
import br.net.rankup.logger.models.system.*;
import br.net.rankup.logger.models.user.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository {

    private final HikariDataBase hikariDataBase = LogPlugin.getInstance().getHikariDataBase();
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS log_users (" +
            "id INTEGER NOT NULL AUTO_INCREMENT, " +
            "name CHAR(36) NOT NULL UNIQUE, " +
            "messages TEXT NOT NULL, " +
            "commands TEXT NOT NULL, " +
            "itemPickUP TEXT NOT NULL, " +
            "itemDrop TEXT NOT NULL, " +
            "kills TEXT NOT NULL, " +
            "system_boss TEXT NOT NULL, " +
            "system_crate TEXT NOT NULL, " +
            "system_economy TEXT NOT NULL, " +
            "system_enchant TEXT NOT NULL, " +
            "system_event TEXT NOT NULL, " +
            "system_fragmento TEXT NOT NULL, " +
            "system_kit TEXT NOT NULL, " +
            "system_rank TEXT NOT NULL, " +
            "system_spawner TEXT NOT NULL, " +
            "system_storable TEXT NOT NULL, " +
            "PRIMARY KEY (id));";
    public static final String CLEAR_TABLE = "DELETE FROM log_users;";
    public static final String SELECT_QUERY = "SELECT * FROM log_users;";
    public static final String UPDATE_QUERY = "INSERT INTO log_users " +
            "(name, messages, commands, itemPickUP, itemDrop, kills" +
            ", system_boss, system_crate, system_economy, system_enchant, system_event, system_fragmento" +
            ", system_kit, system_rank, system_spawner, system_storable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE messages = ?, commands = ?, itemPickUP = ?, itemDrop = ?, kills = ?" +
            ", system_boss = ?, system_crate = ?, system_economy = ?, system_enchant = ?, system_event = ?, system_fragmento = ?, system_kit = ?, system_rank = ?, system_spawner = ?, system_storable = ?;";

    public void createTable() {
        try (final Connection connection = hikariDataBase.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE)) {
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTable() {
        Runnable runnable = () -> {
            try (Connection connection = hikariDataBase.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(CLEAR_TABLE)) {
                    statement.execute();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
        LogPlugin.getInstance().getHikariDataBase().executeAsync(runnable);
    }

    public void loadAll() {
        try (Connection connection = hikariDataBase.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        List<MessageModel> messageModel = MessageAdpter.deserialize(resultSet.getString("messages"));
                        List<CommandModel> commandModel = CommandsAdpter.deserialize(resultSet.getString("commands"));
                        List<ItemPickUPModel> itemPickUPModel = ItemPickAdpter.deserialize(resultSet.getString("itemPickUP"));
                        List<ItemDropModel> itemDropModel = ItemDropAdpter.deserialize(resultSet.getString("itemDrop"));
                        List<KillsModel> killsModel = KillAdpter.deserialize(resultSet.getString("kills"));

                        List<BossModel> bossModels = BossAdpter.deserialize(resultSet.getString("system_boss"));
                        List<CrateModel> crateModels = CrateAdpter.deserialize(resultSet.getString("system_crate"));
                        List<EconomyModel> economyModels = EconomyAdpter.deserialize(resultSet.getString("system_economy"));
                        List<EnchantModel> enchantModels = EnchantAdpter.deserialize(resultSet.getString("system_enchant"));
                        List<EventModel> eventModels = EventAdpter.deserialize(resultSet.getString("system_event"));
                        List<FragmentoModel> fragmentoModels = FragmentoAdpter.deserialize(resultSet.getString("system_fragmento"));
                        List<KitsModel> kitsModels = KitsAdpter.deserialize(resultSet.getString("system_kit"));
                        List<RankModel> rankModels = RankAdpter.deserialize(resultSet.getString("system_rank"));
                        List<SpawnerModel> spawnerModels = SpawnerAdpter.deserialize(resultSet.getString("system_spawner"));
                        List<StorableModel> storableModels = StorableAdpter.deserialize(resultSet.getString("system_storable"));

                        UserModel userModel = new UserModel(resultSet.getString("name"), messageModel, commandModel, itemPickUPModel, itemDropModel, killsModel,
                        bossModels, crateModels, economyModels, enchantModels, eventModels, fragmentoModels, kitsModels, rankModels, spawnerModels, storableModels);
                        LogPlugin.getInstance().getLogManager().getUsers().put(userModel.getOwner(), userModel);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String name) {
        try (Connection connection = hikariDataBase.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
                statement.setString(1, name);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void update(UserModel userModel) {
        Runnable runnable = () -> {
            try (Connection connection = hikariDataBase.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
                    statement.setString(1, userModel.getOwner());

                    statement.setString(2, MessageAdpter.serialize(userModel.getMessages()));
                    statement.setString(3, CommandsAdpter.serialize(userModel.getCommands()));
                    statement.setString(4, ItemPickAdpter.serialize(userModel.getItemPickUP()));
                    statement.setString(5, ItemDropAdpter.serialize(userModel.getItemDrop()));
                    statement.setString(6, KillAdpter.serialize(userModel.getKills()));
                    statement.setString(7, BossAdpter.serialize(userModel.getBossKillModels()));
                    statement.setString(8, CrateAdpter.serialize(userModel.getCrateModels()));
                    statement.setString(9, EconomyAdpter.serialize(userModel.getEconomyModels()));
                    statement.setString(10, EnchantAdpter.serialize(userModel.getEnchantModels()));
                    statement.setString(11, EventAdpter.serialize(userModel.getEventModels()));
                    statement.setString(12, FragmentoAdpter.serialize(userModel.getFragmentoModels()));
                    statement.setString(13, KitsAdpter.serialize(userModel.getKitsModels()));
                    statement.setString(14, RankAdpter.serialize(userModel.getRankModels()));
                    statement.setString(15, SpawnerAdpter.serialize(userModel.getSpawnerModels()));
                    statement.setString(16, StorableAdpter.serialize(userModel.getStorableModels()));

                    statement.setString(17, MessageAdpter.serialize(userModel.getMessages()));
                    statement.setString(18, CommandsAdpter.serialize(userModel.getCommands()));
                    statement.setString(19, ItemPickAdpter.serialize(userModel.getItemPickUP()));
                    statement.setString(20, ItemDropAdpter.serialize(userModel.getItemDrop()));
                    statement.setString(21, KillAdpter.serialize(userModel.getKills()));
                    statement.setString(22, BossAdpter.serialize(userModel.getBossKillModels()));
                    statement.setString(23, CrateAdpter.serialize(userModel.getCrateModels()));
                    statement.setString(24, EconomyAdpter.serialize(userModel.getEconomyModels()));
                    statement.setString(25, EnchantAdpter.serialize(userModel.getEnchantModels()));
                    statement.setString(26, EventAdpter.serialize(userModel.getEventModels()));
                    statement.setString(27, FragmentoAdpter.serialize(userModel.getFragmentoModels()));
                    statement.setString(28, KitsAdpter.serialize(userModel.getKitsModels()));
                    statement.setString(29, RankAdpter.serialize(userModel.getRankModels()));
                    statement.setString(30, SpawnerAdpter.serialize(userModel.getSpawnerModels()));
                    statement.setString(31, StorableAdpter.serialize(userModel.getStorableModels()));
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
        LogPlugin.getInstance().getHikariDataBase().executeAsync(runnable);
    }

}
