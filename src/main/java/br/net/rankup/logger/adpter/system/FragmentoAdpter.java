
package br.net.rankup.logger.adpter.system;

import br.net.rankup.logger.models.system.EventModel;
import br.net.rankup.logger.models.system.FragmentoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class FragmentoAdpter {
    public static String serialize(final List<FragmentoModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final FragmentoModel fragmentoModel : list) {
            joiner.add(fragmentoModel.getAction()
                    + ":" + fragmentoModel.getItem()
                    + ":" + fragmentoModel.getDate());
        }
        return joiner.toString();
    }

    public static List<FragmentoModel> deserialize(final String data) {
        final List<FragmentoModel> list = new ArrayList<FragmentoModel>();
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

    private static void formatAndPut(final List<FragmentoModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String action = split[0];
        final String item = split[1];
        final long sentDate = Long.parseLong(split[2]);
        final FragmentoModel eventModel = new FragmentoModel(action, item, sentDate);
        list.add(eventModel);
    }

}
