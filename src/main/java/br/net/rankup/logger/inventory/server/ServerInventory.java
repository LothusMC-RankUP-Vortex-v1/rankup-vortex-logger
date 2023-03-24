package br.net.rankup.logger.inventory.server;

import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.inventory.LogInventory;
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

public class ServerInventory implements InventoryProvider {


    UserModel userModel;

    public ServerInventory(UserModel userModel) {
        this.userModel = userModel;
    }

    public RyseInventory build() {
        return RyseInventory.builder()
                .title("Logs - Servidor de "+userModel.getOwner().replace("&", "§"))
                .rows(5)
                .provider(this)
                .disableUpdateTask()
                .build(LogPlugin.getInstance());
    }

    @Override
    public void init(Player player, InventoryContents contents) {

        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(player.getName());

        ItemStack itemStackSkull = new ItemBuilder(SkullCreatorUtils.itemFromName(userModel.getOwner()))
                .setName("§a"+userModel.getOwner())
                .addLoreLine("§7Você está vendo os registros desse jogador.").build();

        contents.set(4, itemStackSkull);


        ItemBuilder itemBuilder = new ItemBuilder(Material.BOOK_AND_QUILL)
                .setName("§aMensagens")
                .addLoreLine("§7Essa log estão todas as mensagens")
                .addLoreLine("§7que o jogador digita nos canais.")
                .addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getMessages().size() + " mensagens")
                .addLoreLine("")
                .addLoreLine("§aClique para acessar.");


            IntelligentItem intelligentItem = IntelligentItem.of(itemBuilder.build(), event -> {
                if(!InventoryUtils.getList().contains(player.getName())) {
                    RyseInventory inventory = new MessageInventory(userModel).build();
                    inventory.open(player);
                    InventoryUtils.addDelay(player);
                    player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
                }
                event.setCancelled(true);
            });
            contents.set(20, intelligentItem);


        ItemBuilder itemBuilderCmd = new ItemBuilder(Material.ANVIL)
                .setName("§aComandos")
                .addLoreLine("§7Essa log estão todas os comandos")
                .addLoreLine("§7que o jogador utilizou no servidor.")
                .addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getCommands().size() + " comandos")
                .addLoreLine("")
                .addLoreLine("§aClique para acessar.");


        IntelligentItem intelligentItemCmd = IntelligentItem.of(itemBuilderCmd.build(), event -> {
            if(!InventoryUtils.getList().contains(player.getName())) {
                RyseInventory inventory = new CommandInventory(userModel).build();
                inventory.open(player);
                InventoryUtils.addDelay(player);
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            }
            event.setCancelled(true);
        });
        contents.set(21, intelligentItemCmd);


        ItemBuilder itemBuilderItemPickUP = new ItemBuilder(Material.STORAGE_MINECART)
                .setName("§aItem Recolhidos")
                .addLoreLine("§7Essa log estão todas os items")
                .addLoreLine("§7que o jogador recolheu do chão.")
                .addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getItemPickUP().size() + " items")
                .addLoreLine("")
                .addLoreLine("§aClique para acessar.");


        IntelligentItem intelligentItemPick = IntelligentItem.of(itemBuilderItemPickUP.build(), event -> {
            if(!InventoryUtils.getList().contains(player.getName())) {
                RyseInventory inventory = new ItemPickUPInventory(userModel).build();
                inventory.open(player);
                InventoryUtils.addDelay(player);
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            }
            event.setCancelled(true);
        });
        contents.set(22, intelligentItemPick);


        ItemBuilder itemBuilderItemDrop = new ItemBuilder(Material.EXPLOSIVE_MINECART)
                .setName("§aItem Dropados")
                .addLoreLine("§7Essa log estão todas os items")
                .addLoreLine("§7que o jogador dropou do chão.")
                .addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getItemDrop().size() + " items")
                .addLoreLine("")
                .addLoreLine("§aClique para acessar.");


        IntelligentItem intelligentItemDrop = IntelligentItem.of(itemBuilderItemDrop.build(), event -> {
            if(!InventoryUtils.getList().contains(player.getName())) {
                RyseInventory inventory = new ItemDropInventory(userModel).build();
                inventory.open(player);
                InventoryUtils.addDelay(player);
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            }
            event.setCancelled(true);
        });
        contents.set(23, intelligentItemDrop);


        ItemBuilder itemBuilderKills = new ItemBuilder(Material.DIAMOND_SWORD)
                .setName("§aAbates")
                .addLoreLine("§7Essa log estão todas os abates")
                .addLoreLine("§7e mortes do jogador.")
                .addLoreLine("")
                .addLoreLine(" §fQuantidade: §7"+userModel.getKills().size() + " mortes e abates")
                .addLoreLine("")
                .addLoreLine("§aClique para acessar.");


        IntelligentItem intelligentKills = IntelligentItem.of(itemBuilderKills.build(), event -> {
            if(!InventoryUtils.getList().contains(player.getName())) {
                RyseInventory inventory = new KillsInventory(userModel).build();
                inventory.open(player);
                InventoryUtils.addDelay(player);
                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
            }
            event.setCancelled(true);
        });
        contents.set(24, intelligentKills);

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

}
