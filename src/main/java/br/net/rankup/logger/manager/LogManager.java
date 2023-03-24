package br.net.rankup.logger.manager;

import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.models.server.*;
import br.net.rankup.logger.models.system.*;
import br.net.rankup.logger.models.user.UserModel;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LogManager {


    private HashMap<String, UserModel> users;

    public void loadAll() {
        users = new HashMap<>();
    }

    public HashMap<String, UserModel> getUsers() {
        return users;
    }

    public void registerMessage(Player player, String message) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            MessageModel messageModel = new MessageModel(player.getName(), System.currentTimeMillis(), message);
            userModel.getMessages().add(messageModel);
        } else {
            System.out.println("Não foi possivel registrar a mensagem do jogador "+player.getName());
        }
    }

    public void registerCommand(Player player, String command, World world) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            CommandModel commandModel = new CommandModel(player.getName(), world.getName(), System.currentTimeMillis(), command);
            userModel.getCommands().add(commandModel);
        } else {
            System.out.println("Não foi possivel registrar o comando do jogador "+player.getName());
        }
    }

    public void registerKill(Player player, Player target, boolean isKill) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        KillsModel killsModel;
        String loc = target.getWorld().getName() + " (<x>,<y>,<z>)"
                .replace("<z>", ""+target.getLocation().getBlockZ())
                .replace("<y>", ""+target.getLocation().getBlockY())
                .replace("<x>", ""+target.getLocation().getBlockX());
        if(userModel != null) {
            if(isKill) {
                killsModel = new KillsModel(false, loc, target.getName(), System.currentTimeMillis());
            } else {
                killsModel = new KillsModel(true, loc, target.getName(), System.currentTimeMillis());
            }
            userModel.getKills().add(killsModel);
        } else {
            System.out.println("Não foi possivel registrar kill do jogador "+player.getName());
        }
    }

    public void registerItemPickUP(Player player, Location location, ItemStack itemStack) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            String item = "Sem nome";
            if(itemStack.hasItemMeta()) {
                if(itemStack.getItemMeta().hasDisplayName()) {
                    item = itemStack.getItemMeta().getDisplayName().replace("&", "§");
                }
            }
            String loc = location.getWorld().getName() + " (<x>,<y>,<z>)"
                    .replace("<z>", ""+location.getBlockZ())
                    .replace("<y>", ""+location.getBlockY())
                    .replace("<x>", ""+location.getBlockX());
            ItemPickUPModel itemPickUPModel = new ItemPickUPModel(player.getName(), loc, item, itemStack.getTypeId(), itemStack.getAmount(), System.currentTimeMillis());
            userModel.getItemPickUP().add(itemPickUPModel);
        } else {
            System.out.println("Não foi possivel registrar o item pick up do jogador "+player.getName());
        }
    }

    public void registerItemDrop(Player player, Location location, ItemStack itemStack) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            String item = "Sem nome";
            if(itemStack.hasItemMeta()) {
                if(itemStack.getItemMeta().hasDisplayName()) {
                    item = itemStack.getItemMeta().getDisplayName().replace("&", "§");
                }
            }
            String loc = location.getWorld().getName() + " (<x>,<y>,<z>)"
                    .replace("<z>", ""+location.getBlockZ())
                    .replace("<y>", ""+location.getBlockY())
                    .replace("<x>", ""+location.getBlockX());
            ItemDropModel itemPickUPModel = new ItemDropModel(player.getName(), loc, item, itemStack.getTypeId(), itemStack.getAmount(), System.currentTimeMillis());
            userModel.getItemDrop().add(itemPickUPModel);
        } else {
            System.out.println("Não foi possivel registrar o item drop do jogador "+player.getName());
        }
    }

    public void registerBoss(Player player, String boss, String action, double amount) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            BossModel bossModel = new BossModel(boss, action, amount, System.currentTimeMillis());
            userModel.getBossKillModels().add(bossModel);
        } else {
            System.out.println("Não foi possivel registrar o boss do jogador "+player.getName());
        }
    }

    public void registerCrate(Player player, String crate, String item) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            CrateModel crateModel = new CrateModel(crate, item, System.currentTimeMillis());
            userModel.getCrateModels().add(crateModel);
        } else {
            System.out.println("Não foi possivel registrar a crate do jogador "+player.getName());
        }
    }

    public void registerEconomy(Player player, String type, Double amount) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            EconomyModel economyModel = new EconomyModel(type, amount, System.currentTimeMillis());
            userModel.getEconomyModels().add(economyModel);
        } else {
            System.out.println("Não foi possivel registrar o economy do jogador "+player.getName());
        }
    }

    public void registerEvent(Player player, String event, Double amount) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            EventModel eventModel = new EventModel(event, amount, System.currentTimeMillis());
            userModel.getEventModels().add(eventModel);
        } else {
            System.out.println("Não foi possivel registrar o evento do jogador "+player.getName());
        }
    }

    public void registerFragmento(Player player, String event, String item) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            FragmentoModel fragmentoModel = new FragmentoModel(event, item, System.currentTimeMillis());
            userModel.getFragmentoModels().add(fragmentoModel);
        } else {
            System.out.println("Não foi possivel registrar o fragmento do jogador "+player.getName());
        }
    }

    public void registerKit(Player player, String kit) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            KitsModel kitsModel = new KitsModel(kit, System.currentTimeMillis());
            userModel.getKitsModels().add(kitsModel);
        } else {
            System.out.println("Não foi possivel registrar o kit do jogador "+player.getName());
        }
    }


    public void registerRank(Player player, String rank, double coins, double rubis) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            RankModel rankModel = new RankModel(rank, coins, rubis, System.currentTimeMillis());
            userModel.getRankModels().add(rankModel);
        } else {
            System.out.println("Não foi possivel registrar o rank do jogador "+player.getName());
        }
    }



    public void registerSpawner(Player player, String action, String type, double amount) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            SpawnerModel spawnerModel = new SpawnerModel(action, type, amount, System.currentTimeMillis());
            userModel.getSpawnerModels().add(spawnerModel);
        } else {
            System.out.println("Não foi possivel registrar o spawner do jogador "+player.getName());
        }
    }

    public void registerStorable(Player player, double amount, double price) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            StorableModel storableModel = new StorableModel(amount, price, System.currentTimeMillis());
            userModel.getStorableModels().add(storableModel);
        } else {
            System.out.println("Não foi possivel registrar o storable do jogador "+player.getName());
        }
    }

    public void registerEnchant(Player player, String enchant, double nivel) {
        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());
        if(userModel != null) {
            EnchantModel enchantModel = new EnchantModel(enchant, nivel, System.currentTimeMillis());
            userModel.getEnchantModels().add(enchantModel);
        } else {
            System.out.println("Não foi possivel registrar a enchant do jogador "+player.getName());
        }
    }
}
