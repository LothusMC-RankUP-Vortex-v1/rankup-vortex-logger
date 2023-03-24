package br.net.rankup.logger.commands;

import br.net.rankup.logger.LogPlugin;
import br.net.rankup.logger.inventory.LogInventory;
import br.net.rankup.logger.misc.BukkitUtils;
import br.net.rankup.logger.models.user.UserModel;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LogCommand {


    @Command(name = "log", permission = "commands.log", target = CommandTarget.PLAYER, usage = "log <jogador>")
    public void handleLogCommand(final Context<CommandSender> execution, String target) {
        Player player = (Player)execution.getSender();

        UserModel userModel = LogPlugin.getInstance().getLogManager().getUsers().get(target);
        if(userModel == null) {
            BukkitUtils.sendMessage(player, "&cEsse jogador não foi encontrado.");
            return;
        }
        BukkitUtils.sendMessage(player, "&aAbrindo os registros do jogador...");

        RyseInventory ryseInventory = new LogInventory(userModel).build();
        ryseInventory.open(player);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 5.0f, 1.0f);
    }

}
