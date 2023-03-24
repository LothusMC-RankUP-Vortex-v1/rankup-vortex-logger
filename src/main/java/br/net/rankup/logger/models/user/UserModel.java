package br.net.rankup.logger.models.user;


import br.net.rankup.logger.models.server.*;
import br.net.rankup.logger.models.system.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class UserModel {

    public List<MessageModel> getMessagesByOrder() {
        return (this.messages.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<CommandModel> getCommandByOrder() {
        return (this.commands.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<ItemPickUPModel> getItemPickUPByOrder() {
        return (this.itemPickUP.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<ItemDropModel> getItemDropByOrder() {
        return (this.itemDrop.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<KillsModel> getKillsByOrder() {
        return (this.kills.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    private String owner;
    private List<MessageModel> messages;
    private List<CommandModel> commands;
    private List<ItemPickUPModel> itemPickUP;
    private List<ItemDropModel> itemDrop;
    private List<KillsModel> kills;

    //system

    public List<BossModel> getBossByOrder() {
        return (this.bossKillModels.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<CrateModel> getCratesByOrder() {
        return (this.crateModels.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<EconomyModel> getEconomyByOrder() {
        return (this.economyModels.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<EnchantModel> getEnchantByOrder() {
        return (this.enchantModels.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<EventModel> getEventsByOrder() {
        return (this.eventModels.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<FragmentoModel> getFragmentoByOrder() {
        return (this.fragmentoModels.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<KitsModel> getKitByOrder() {
        return (this.kitsModels.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<RankModel> getRankByOrder() {
        return (this.rankModels.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<SpawnerModel> getSpawnerByOrder() {
        return (this.spawnerModels.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }

    public List<StorableModel> getStorableByOrder() {
        return (this.storableModels.stream().sorted((o1, o2) -> Boolean.compare(Timestamp.from(Instant.ofEpochMilli(o1.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o2.getDate()))), Timestamp.from(Instant.ofEpochMilli(o2.getDate())).after(Timestamp.from(Instant.ofEpochMilli(o1.getDate()))))).collect(Collectors.toList()));
    }



    private List<BossModel> bossKillModels;
    private List<CrateModel> crateModels;
    private List<EconomyModel> economyModels;
    private List<EnchantModel> enchantModels;
    private List<EventModel> eventModels;
    private List<FragmentoModel> fragmentoModels;
    private List<KitsModel> kitsModels;
    private List<RankModel> rankModels;
    private List<SpawnerModel> spawnerModels;
    private List<StorableModel> storableModels;

}
