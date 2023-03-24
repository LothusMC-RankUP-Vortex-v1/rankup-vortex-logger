
package br.net.rankup.logger.adpter.system;

import br.net.rankup.logger.models.system.RankModel;
import br.net.rankup.logger.models.system.SpawnerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SpawnerAdpter {
    public static String serialize(final List<SpawnerModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final SpawnerModel spawnerModel : list) {
            joiner.add(spawnerModel.getAction()
                    + ":" + spawnerModel.getType()
                    + ":" + spawnerModel.getAmount()
                    + ":" + spawnerModel.getDate());
        }
        return joiner.toString();
    }

    public static List<SpawnerModel> deserialize(final String data) {
        final List<SpawnerModel> list = new ArrayList<SpawnerModel>();
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

    private static void formatAndPut(final List<SpawnerModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String action = split[0];
        final String type = split[1];
        final Double amount = Double.parseDouble(split[2]);
        final long sentDate = Long.parseLong(split[3]);
        final SpawnerModel spawnerModel = new SpawnerModel(action, type, amount, sentDate);
        list.add(spawnerModel);
    }

}
