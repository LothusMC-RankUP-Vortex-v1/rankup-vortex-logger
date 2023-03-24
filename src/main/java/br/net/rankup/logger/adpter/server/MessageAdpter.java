package br.net.rankup.logger.adpter.server;

import br.net.rankup.logger.models.server.MessageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MessageAdpter {
    public static String serialize(final List<MessageModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final MessageModel messageModel : list) {
            joiner.add(messageModel.getOwner() + ":" + messageModel.getDate() + ":" + messageModel.getMessage());
        }
        return joiner.toString();
    }

    public static List<MessageModel> deserialize(final String data) {
        final List<MessageModel> list = new ArrayList<MessageModel>();
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

    private static void formatAndPut(final List<MessageModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String sender = split[0];
        final long sentDate = Long.parseLong(split[1]);
        final MessageModel messageModel = new MessageModel(sender, sentDate, split[2]);
        list.add(messageModel);
    }

}
