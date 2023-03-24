package br.net.rankup.logger.inventory.system;

import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.inventory.server.ServerInventory;
import br.net.rankup.logger.misc.BukkitUtils;
import br.net.rankup.logger.misc.ItemBuilder;
import br.net.rankup.logger.misc.Toolchain;
import br.net.rankup.logger.models.user.UserModel;
import com.google.common.collect.ImmutableList;
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

import java.util.concurrent.atomic.AtomicReference;

public class EconomyInventory implements InventoryProvider {


    UserModel userModel;

    public EconomyInventory(UserModel userModel) {
        this.userModel = userModel;
    }

    public RyseInventory build() {
        return RyseInventory.builder()
                .title("Logs - Economias".replace("&", "§"))
                .rows(6)
                .provider(this)
                .disableUpdateTask()
                .build(LogPlugin.getInstance());
    }

    @Override
    public void init(Player player, InventoryContents contents) {

        if (userModel.getEconomyModels().size() == 0) {
            ItemStack empty = new ItemBuilder(Material.WEB, 1, 0)
                    .owner(player.getName()).setName("§cVazio").setLore(ImmutableList.of(
                            "§7Esse jogador não tem", "§7nenhuma log registrada."
                    )).build();
            contents.set(22, empty);
        } else {

            Pagination pagination = contents.pagination();
            pagination.iterator(SlotIterator.builder().withPattern(SlotIteratorPattern.builder().define(
                                    "XXXXXXXXX",
                                    "XOOOOOOOX",
                                    "XOOOOOOOX",
                                    "XOOOOOOOX",
                                    "XOOOOOOOX",
                                    "XXXXXXXXX")
                            .attach('O')
                            .buildPattern())
                    .build());
            pagination.setItemsPerPage(28);


            AtomicReference<Integer> i = new AtomicReference<>((int) 1);
            userModel.getEconomyByOrder().forEach(economyModel -> {
                ItemStack itemStack = new ItemBuilder(Material.PAPER)
                        .setName("§aEconomia #" +i)
                        .addLoreLine("§fEconomia: §7"+economyModel.getType())
                        .addLoreLine("§fValor: §7"+ Toolchain.format(economyModel.getAmount()))
                        .addLoreLine("§fHorário: §7"+ BukkitUtils.formatTime(economyModel.getDate()))
                        .build();
                    pagination.addItem(itemStack);
                    i.getAndSet(new Integer((int) (i.get() + 1)));
            });


            if (pagination.isFirst()) {
                ItemStack itemStack = new ItemBuilder(Material.ARROW).setName("§cPágina anterior").toItemStack();
                IntelligentItem intelligentItem = IntelligentItem.of(itemStack, event -> {
                    if (event.isLeftClick()) {
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                        RyseInventory inventory = new SystemInventory(userModel).build();
                        inventory.open(player);
                    }
                });
                contents.set(45, intelligentItem);
            }

            if (!pagination.isFirst()) {
                ItemStack itemStack = new ItemBuilder(Material.ARROW).setName("§aPágina anterior").toItemStack();
                IntelligentItem intelligentItem = IntelligentItem.of(itemStack, event -> {
                    if (event.isLeftClick()) {
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                        pagination.inventory().open(player, pagination.previous().page());
                    }
                });
                contents.set(18, intelligentItem);
            }

            if (!pagination.isLast()) {
                ItemStack itemStack = new ItemBuilder(Material.ARROW).setName("§aPróxima página").toItemStack();
                IntelligentItem intelligentItem = IntelligentItem.of(itemStack, event -> {
                    if (event.isLeftClick()) {
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                        pagination.inventory().open(player, pagination.next().page());
                    }
                });
                contents.set(26, intelligentItem);
            }
        }
    }

}
