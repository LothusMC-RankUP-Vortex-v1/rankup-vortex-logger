package br.net.rankup.logger.adpter.system;

import br.net.rankup.logger.models.server.ItemPickUPModel;
import br.net.rankup.logger.models.system.BossModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class BossAdpter {
    public static String serialize(final List<BossModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final BossModel bossModel : list) {
            joiner.add(bossModel.getBoss()
                    + ":" + bossModel.getAction()
                    + ":" + bossModel.getAmountPrice()
                    + ":" + bossModel.getDate());
        }
        return joiner.toString();
    }

    public static List<BossModel> deserialize(final String data) {
        final List<BossModel> list = new ArrayList<BossModel>();
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

    private static void formatAndPut(final List<BossModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String boss = split[0];
        final String action = split[1];
        final Double amount = Double.parseDouble(split[2]);
        final long sentDate = Long.parseLong(split[3]);
        final BossModel bossModel = new BossModel(boss, action, amount, sentDate);
        list.add(bossModel);
    }

}
