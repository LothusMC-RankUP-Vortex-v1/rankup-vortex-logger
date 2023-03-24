
package br.net.rankup.logger.adpter.system;

import br.net.rankup.logger.models.system.EventModel;
import br.net.rankup.logger.models.system.RankModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class RankAdpter {
    public static String serialize(final List<RankModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final RankModel rankModel : list) {
            joiner.add(rankModel.getRank()
                    + ":" + rankModel.getPrice()
                    + ":" + rankModel.getRubis()
                    + ":" + rankModel.getDate());
        }
        return joiner.toString();
    }

    public static List<RankModel> deserialize(final String data) {
        final List<RankModel> list = new ArrayList<RankModel>();
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

    private static void formatAndPut(final List<RankModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String rank = split[0];
        final Double price = Double.parseDouble(split[1]);
        final Double rubis = Double.parseDouble(split[2]);
        final long sentDate = Long.parseLong(split[3]);
        final RankModel rankModel = new RankModel(rank, price, rubis, sentDate);
        list.add(rankModel);
    }

}
