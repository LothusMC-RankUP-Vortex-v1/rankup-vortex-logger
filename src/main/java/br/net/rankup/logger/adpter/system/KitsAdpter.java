
package br.net.rankup.logger.adpter.system;

import br.net.rankup.logger.models.system.FragmentoModel;
import br.net.rankup.logger.models.system.KitsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class KitsAdpter {
    public static String serialize(final List<KitsModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final KitsModel kitsModel : list) {
            joiner.add(kitsModel.getKit()
                    + ":" + kitsModel.getDate());
        }
        return joiner.toString();
    }

    public static List<KitsModel> deserialize(final String data) {
        final List<KitsModel> list = new ArrayList<KitsModel>();
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

    private static void formatAndPut(final List<KitsModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String kit = split[0];
        final long sentDate = Long.parseLong(split[1]);
        final KitsModel eventModel = new KitsModel(kit, sentDate);
        list.add(eventModel);
    }

}
