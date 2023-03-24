
package br.net.rankup.logger;

import br.net.rankup.logger.commands.LogCommand;
import br.net.rankup.logger.database.HikariDataBase;
import br.net.rankup.logger.listener.*;
import br.net.rankup.logger.manager.LogManager;
import br.net.rankup.logger.misc.ConfigUtils;
import br.net.rankup.logger.models.user.UserModel;
import br.net.rankup.logger.repository.UserRepository;
import br.net.rankup.logger.misc.BukkitUtils;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import lombok.Getter;
import lombok.Setter;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import me.saiintbrisson.minecraft.command.message.MessageHolder;
import me.saiintbrisson.minecraft.command.message.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public final class LogPlugin extends JavaPlugin {

    private LogManager logManager;

    @Override
    public void onEnable() {
        instance = this;

        start = System.currentTimeMillis();
        configuration = new ConfigUtils(this,"config.yml");
        configuration.saveDefaultConfig();

        bukkitFrame = new BukkitFrame(LogPlugin.getInstance());
        loadCommands();
        inventoryManager = new InventoryManager(this);
        inventoryManager.invoke();

        (this.logManager = new LogManager()).loadAll();

        bukkitFrame.registerCommands(new LogCommand());

        this.getServer().getPluginManager().registerEvents(new MessageListener(this), this);
        this.getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        this.getServer().getPluginManager().registerEvents(new ItemListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerKillListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        HikariDataBase.prepareDatabase();

        BukkitUtils.sendMessage(Bukkit.getConsoleSender(), "&aplugin started successfully ({time} ms)"
                .replace("{time}",""+(System.currentTimeMillis() - start)));
        enable.set(true);
    }

    @Override
    public void onDisable() {
        if(enable.get()) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                UserModel userModel = this.getLogManager().getUsers().get(player.getName());
                if(userModel != null) {
                    usersRepository.update(userModel);
                }
            });
            BukkitUtils.sendMessage(Bukkit.getConsoleSender(), "&cplugin successfully turned off!");
        } else {
            BukkitUtils.sendMessage(Bukkit.getConsoleSender(), "&cplugin suffered a some problem");
        }
    }


    private HikariDataBase hikariDataBase;
    private UserRepository usersRepository;
    static long start = 0;
    private AtomicBoolean enable = new AtomicBoolean(false);
    private static ConfigUtils configuration;
    private static LogPlugin instance;
    public static LogPlugin getInstance() { return instance; }
    @Getter
    private InventoryManager inventoryManager;
    @Getter
    private  BukkitFrame bukkitFrame;

    private void loadCommands() {
        MessageHolder messageHolder = getBukkitFrame().getMessageHolder();
        messageHolder.setMessage(MessageType.ERROR, "§cOcorreu um erro durante a execução deste comando, erro: §7{error}§c.");
        messageHolder.setMessage(MessageType.INCORRECT_USAGE, "§cUtilize: /{usage}");
        messageHolder.setMessage(MessageType.NO_PERMISSION, "§cVocê não tem permissão para executar esse comando.");
        messageHolder.setMessage(MessageType.INCORRECT_TARGET, "§cVocê não pode utilizar este comando pois ele é direcionado apenas para {target}.");
    }
        }
