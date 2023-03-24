package br.net.rankup.logger.inventory;

import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.inventory.server.MessageInventory;
import br.net.rankup.logger.inventory.server.ServerInventory;
import br.net.rankup.logger.inventory.system.SystemInventory;
import br.net.rankup.logger.misc.InventoryUtils;
import br.net.rankup.logger.misc.ItemBuilder;
import br.net.rankup.logger.misc.SkullCreatorUtils;
import br.net.rankup.logger.models.user.UserModel;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LogInventory implements InventoryProvider {


    UserModel userModel;

    public LogInventory(UserModel userModel) {
        this.userModel = userModel;
    }

    public RyseInventory build() {
        return RyseInventory.builder()
                .title("Logs de "+userModel.getOwner().replace("&", "§"))
                .rows(4)
                .provider(this)
                .disableUpdateTask()
                .build(LogPlugin.getInstance());
    }

    @Override
    public void init(Player player, InventoryContents contents) {


        ItemStack itemStackSkull = new ItemBuilder(SkullCreatorUtils.itemFromName(userModel.getOwner()))
                .setName("§a"+userModel.getOwner())
                .addLoreLine("§7Você está vendo os registros desse jogador.").build();

        contents.set(4, itemStackSkull);

        ItemBuilder itemBuilder = new ItemBuilder(Material.FIREBALL)
                .setName("§eLog do Servidor")
                .addLoreLine("§7Essa categoria de logs estão")
                .addLoreLine("§7eventos simples do minecraft.")
                .addLoreLine("")
                .addLoreLine("§aClique para acessar.");


            IntelligentItem intelligentItem = IntelligentItem.of(itemBuilder.build(), event -> {
                if(!InventoryUtils.getList().contains(player.getName())) {
                    RyseInventory inventory = new ServerInventory(userModel).build();
                    inventory.open(player);
                    InventoryUtils.addDelay(player);
                    player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
                }
                event.setCancelled(true);
            });
            contents.set(20, intelligentItem);


        ItemBuilder itemBuilderSystem = new ItemBuilder(Material.NETHER_STAR)
                .setName("§eLog de Sistemas")
                .addLoreLine("§7Essa categoria de logs estão")
                .addLoreLine("§7alguns registros de sistemas.")
                .addLoreLine("")
                .addLoreLine("§aClique para acessar.");


        IntelligentItem intelligentSystem = IntelligentItem.of(itemBuilderSystem.build(), event -> {
            if(!InventoryUtils.getList().contains(player.getName())) {
                RyseInventory inventory = new SystemInventory(userModel).build();
                inventory.open(player);
                InventoryUtils.addDelay(player);
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            }
            event.setCancelled(true);
        });
        contents.set(24, intelligentSystem);
    }

}
