package br.net.rankup.logger.adpter.server;

import br.net.rankup.logger.models.server.ItemDropModel;
import br.net.rankup.logger.models.server.KillsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class KillAdpter {
    public static String serialize(final List<KillsModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final KillsModel itemDropModel : list) {
            joiner.add(itemDropModel.isKill()
                    + ":" + itemDropModel.getLocation()
                    + ":" + itemDropModel.getName()
                    + ":" + itemDropModel.getDate());
        }
        return joiner.toString();
    }

    public static List<KillsModel> deserialize(final String data) {
        final List<KillsModel> list = new ArrayList<KillsModel>();
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

    private static void formatAndPut(final List<KillsModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final boolean isKill = Boolean.getBoolean(split[0]);
        final String loc = split[1];
        final String name = split[2];
        final long sentDate = Long.parseLong(split[3]);
        final KillsModel killsModel = new KillsModel(isKill, loc, name, sentDate);
        list.add(killsModel);
    }

}
