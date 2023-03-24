package br.net.rankup.logger.inventory.system;

import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.inventory.LogInventory;
import br.net.rankup.logger.inventory.server.*;
import br.net.rankup.logger.misc.InventoryUtils;
import br.net.rankup.logger.misc.ItemBuilder;
import br.net.rankup.logger.misc.SkullCreatorUtils;
import br.net.rankup.logger.models.user.UserModel;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.Pagination;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import io.github.rysefoxx.inventory.plugin.pagination.SlotIterator;
import io.github.rysefoxx.inventory.plugin.pattern.SlotIteratorPattern;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SystemInventory implements InventoryProvider {


    UserModel userModel;

    public SystemInventory(UserModel userModel) {
        this.userModel = userModel;
    }

    public RyseInventory build() {
        return RyseInventory.builder()
                .title("Logs - System de "+userModel.getOwner().replace("&", "§"))
                .rows(5)
                .provider(this)
                .disableUpdateTask()
                .build(LogPlugin.getInstance());
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        Pagination pagination = contents.pagination();
        pagination.iterator(SlotIterator.builder().withPattern(SlotIteratorPattern.builder().define(
                                "XXXXXXXXX",
                                "XXXXXXXXX",
                                "XXOOOOOXX",
                                "XXOOOOOXX",
                                "XXXXXXXXX",
                                "XXXXXXXXX")
                        .attach('O')
                        .buildPattern())
                .build());
        pagination.setItemsPerPage(10);


        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());

        ItemStack itemStackSkull = new ItemBuilder(SkullCreatorUtils.itemFromName(userModel.getOwner()))
                .setName("§a"+userModel.getOwner())
                .addLoreLine("§7Você está vendo os registros desse jogador.").build();

        contents.set(4, itemStackSkull);

        //SPAWNER
        ItemStack spawner = new ItemBuilder(Material.MOB_SPAWNER)
                .setName("§aGeradores")
                .addLoreLine("§7Essa log estão todas os eventos")
                .addLoreLine("§7relacionados ao geradores.").addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getSpawnerModels().size() + " registros").addLoreLine("")
                .addLoreLine("§aClique para acessar.").build();


            IntelligentItem intelligentItem = IntelligentItem.of(spawner , event -> {
                    RyseInventory inventory = new SpawnerInventory(userModel).build();
                    inventory.open(player);
                    player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
                    event.setCancelled(true);
            });
            pagination.addItem(intelligentItem);


            //BOSS

        ItemStack boss = new ItemBuilder(Material.ROTTEN_FLESH)
                .setName("§aBosses")
                .addLoreLine("§7Essa log estão todas os eventos")
                .addLoreLine("§7relacionados aos bosses.").addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getBossKillModels().size() + " registros").addLoreLine("")
                .addLoreLine("§aClique para acessar.").build();


        IntelligentItem intelligentBoss = IntelligentItem.of(boss , event -> {
            RyseInventory inventory = new BossInventory(userModel).build();
            inventory.open(player);
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            event.setCancelled(true);
        });
        pagination.addItem(intelligentBoss);


        //ECONOMY

        ItemStack economy = new ItemBuilder(Material.FIREWORK_CHARGE)
                .setName("§aEconomias")
                .addLoreLine("§7Essa log estão todas os eventos")
                .addLoreLine("§7relacionados as economias.").addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getEconomyModels().size() + " registros").addLoreLine("")
                .addLoreLine("§aClique para acessar.").build();


        IntelligentItem intelligentEconomy = IntelligentItem.of(economy , event -> {
            RyseInventory inventory = new EconomyInventory(userModel).build();
            inventory.open(player);
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            event.setCancelled(true);
        });
        pagination.addItem(intelligentEconomy);


        //ENCHANT

        ItemStack enchant = new ItemBuilder(Material.ENCHANTED_BOOK)
                .setName("§aEncantamentos")
                .addLoreLine("§7Essa log estão todas os eventos")
                .addLoreLine("§7relacionados aos encantamentos.").addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getEnchantModels().size() + " registros").addLoreLine("")
                .addLoreLine("§aClique para acessar.").build();


        IntelligentItem intelligentEnchant = IntelligentItem.of(enchant , event -> {
            RyseInventory inventory = new EnchantInventory(userModel).build();
            inventory.open(player);
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            event.setCancelled(true);
        });
        pagination.addItem(intelligentEnchant);


        //KITS

        ItemStack kits = new ItemBuilder(Material.CHEST)
                .setName("§aKits")
                .addLoreLine("§7Essa log estão todas os eventos")
                .addLoreLine("§7relacionados aos kits.").addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getKitsModels().size() + " registros").addLoreLine("")
                .addLoreLine("§aClique para acessar.").build();


        IntelligentItem intelligentKits = IntelligentItem.of(kits , event -> {
            RyseInventory inventory = new KitInventory(userModel).build();
            inventory.open(player);
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            event.setCancelled(true);
        });
        pagination.addItem(intelligentKits);

        //STORABLE

        ItemStack storable = new ItemBuilder(Material.MINECART)
                .setName("§aArmazém")
                .addLoreLine("§7Essa log estão todas os eventos")
                .addLoreLine("§7relacionados ao armazém.").addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getStorableModels().size() + " registros").addLoreLine("")
                .addLoreLine("§aClique para acessar.").build();


        IntelligentItem intelligentStorable = IntelligentItem.of(storable , event -> {
            RyseInventory inventory = new StorableInventory(userModel).build();
            inventory.open(player);
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            event.setCancelled(true);
        });
        pagination.addItem(intelligentStorable);


        //EVENTS

        ItemStack events = new ItemBuilder(Material.GOLD_INGOT)
                .setName("§aEventos")
                .addLoreLine("§7Essa log estão todas os eventos")
                .addLoreLine("§7relacionados aos eventos.").addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getEventModels().size() + " registros").addLoreLine("")
                .addLoreLine("§aClique para acessar.").build();


        IntelligentItem intelligentEvento = IntelligentItem.of(events , event -> {
            RyseInventory inventory = new EventInventory(userModel).build();
            inventory.open(player);
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            event.setCancelled(true);
        });
        pagination.addItem(intelligentEvento);


        //CRATES

        ItemStack crates = new ItemBuilder(Material.TRIPWIRE_HOOK)
                .setName("§aCaixas")
                .addLoreLine("§7Essa log estão todas os eventos")
                .addLoreLine("§7relacionados as caixas.").addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getCrateModels().size() + " registros").addLoreLine("")
                .addLoreLine("§aClique para acessar.").build();


        IntelligentItem intelligentCrates = IntelligentItem.of(crates , event -> {
            RyseInventory inventory = new CratesInventory(userModel).build();
            inventory.open(player);
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            event.setCancelled(true);
        });
        pagination.addItem(intelligentCrates);


        //FRAG

        ItemStack frag = new ItemBuilder(Material.QUARTZ)
                .setName("§aFragmentos")
                .addLoreLine("§7Essa log estão todas os eventos")
                .addLoreLine("§7relacionados aos fragmentos.").addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getFragmentoModels().size() + " registros").addLoreLine("")
                .addLoreLine("§aClique para acessar.").build();


        IntelligentItem intelligentFragmento = IntelligentItem.of(frag , event -> {
            RyseInventory inventory = new FragmentoInventory(userModel).build();
            inventory.open(player);
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            event.setCancelled(true);
        });
        pagination.addItem(intelligentFragmento);


        //RANK

        ItemStack rank = new ItemBuilder(Material.ENCHANTMENT_TABLE)
                .setName("§aRanks")
                .addLoreLine("§7Essa log estão todas os eventos")
                .addLoreLine("§7relacionados aos ranks.").addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getRankModels().size() + " registros").addLoreLine("")
                .addLoreLine("§aClique para acessar.").build();


        IntelligentItem intelligentRank = IntelligentItem.of(rank , event -> {
            RyseInventory inventory = new RankInventory(userModel).build();
            inventory.open(player);
            player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            event.setCancelled(true);
        });
        pagination.addItem(intelligentRank);

        if(pagination.isFirst()) {
            ItemStack itemStack = new ItemBuilder(Material.ARROW).setName("§cPágina anterior").toItemStack();
            IntelligentItem intelligentItemBack = IntelligentItem.of(itemStack, event -> {
                if (event.isLeftClick()) {
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    RyseInventory inventory = new LogInventory(userModel).build();
                    inventory.open(player);
                }
            });
            contents.set(36, intelligentItemBack);
        }

        if (!pagination.isFirst()) {
            ItemStack itemStack = new ItemBuilder(Material.ARROW).setName("§aPágina anterior").toItemStack();
            IntelligentItem intelligent = IntelligentItem.of(itemStack, event -> {
                if (event.isLeftClick()) {
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    pagination.inventory().open(player, pagination.previous().page());
                }
            });
            contents.set(18, intelligent);
        }

        if (!pagination.isLast()) {
            ItemStack itemStack = new ItemBuilder(Material.ARROW).setName("§aPróxima página").toItemStack();
            IntelligentItem intelligent = IntelligentItem.of(itemStack, event -> {
                if (event.isLeftClick()) {
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    pagination.inventory().open(player, pagination.next().page());
                }
            });
            contents.set(26, intelligent);
        }
    }

}
