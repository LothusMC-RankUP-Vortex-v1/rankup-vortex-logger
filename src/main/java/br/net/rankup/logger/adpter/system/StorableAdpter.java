
package br.net.rankup.logger.adpter.system;

import br.net.rankup.logger.models.system.EventModel;
import br.net.rankup.logger.models.system.StorableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class StorableAdpter {
    public static String serialize(final List<StorableModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final StorableModel storableModel : list) {
            joiner.add(storableModel.getAmount()
                    + ":" + storableModel.getPrice()
                    + ":" + storableModel.getDate());
        }
        return joiner.toString();
    }

    public static List<StorableModel> deserialize(final String data) {
        final List<StorableModel> list = new ArrayList<StorableModel>();
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

    private static void formatAndPut(final List<StorableModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final Double amount = Double.parseDouble(split[0]);
        final Double price = Double.parseDouble(split[0]);
        final long sentDate = Long.parseLong(split[2]);
        final StorableModel storableModel = new StorableModel(amount, price, sentDate);
        list.add(storableModel);
    }

}
