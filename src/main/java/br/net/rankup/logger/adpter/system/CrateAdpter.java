
package br.net.rankup.logger.adpter.system;

import br.net.rankup.logger.models.system.BossModel;
import br.net.rankup.logger.models.system.CrateModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class CrateAdpter {
    public static String serialize(final List<CrateModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final CrateModel crateModel : list) {
            joiner.add(crateModel.getCrate()
                    + ":" + crateModel.getItem()
                    + ":" + crateModel.getDate());
        }
        return joiner.toString();
    }

    public static List<CrateModel> deserialize(final String data) {
        final List<CrateModel> list = new ArrayList<CrateModel>();
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

    private static void formatAndPut(final List<CrateModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String crate = split[0];
        final String item = split[1];
        final long sentDate = Long.parseLong(split[2]);
        final CrateModel crateModel = new CrateModel(crate, item, sentDate);
        list.add(crateModel);
    }

}
