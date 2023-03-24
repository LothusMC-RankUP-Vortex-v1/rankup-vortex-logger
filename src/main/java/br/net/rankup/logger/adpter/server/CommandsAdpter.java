package br.net.rankup.logger.adpter.server;

import br.net.rankup.logger.models.server.CommandModel;
import br.net.rankup.logger.models.server.MessageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CommandsAdpter {
    public static String serialize(final List<CommandModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final CommandModel commandModel : list) {
            joiner.add(commandModel.getOwner() + ":" + commandModel.getWorld() + ":" + commandModel.getDate() + ":" + commandModel.getMessage());
        }
        return joiner.toString();
    }

    public static List<CommandModel> deserialize(final String data) {
        final List<CommandModel> list = new ArrayList<CommandModel>();
        if (data.contains(";")) {
            for (final String value : data.split(";")) {
                formatAndPut(list, value);
            }
        }
        else {
            formatAndPut(list, data);
        }
        return list;
    }

    private static void formatAndPut(final List<CommandModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String sender = split[0];
        final String world = split[1];
        final long sentDate = Long.parseLong(split[2]);
        final CommandModel commandModel = new CommandModel(sender, world, sentDate, split[3]);
        list.add(commandModel);
    }

}
