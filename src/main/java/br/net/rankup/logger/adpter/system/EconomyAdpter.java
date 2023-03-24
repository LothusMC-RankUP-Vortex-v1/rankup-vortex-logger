
package br.net.rankup.logger.adpter.system;

import br.net.rankup.logger.models.system.CrateModel;
import br.net.rankup.logger.models.system.EconomyModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class EconomyAdpter {
    public static String serialize(final List<EconomyModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final EconomyModel economyModel : list) {
            joiner.add(economyModel.getType()
                    + ":" + economyModel.getAmount()
                    + ":" + economyModel.getDate());
        }
        return joiner.toString();
    }

    public static List<EconomyModel> deserialize(final String data) {
        final List<EconomyModel> list = new ArrayList<EconomyModel>();
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

    private static void formatAndPut(final List<EconomyModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String type = split[0];
        final Double amount = Double.parseDouble(split[1]);
        final long sentDate = Long.parseLong(split[2]);
        final EconomyModel crateModel = new EconomyModel(type, amount, sentDate);
        list.add(crateModel);
    }

}
