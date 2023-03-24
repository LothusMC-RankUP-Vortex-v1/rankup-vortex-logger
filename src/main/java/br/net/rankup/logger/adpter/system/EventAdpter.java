
package br.net.rankup.logger.adpter.system;

import br.net.rankup.logger.models.system.EnchantModel;
import br.net.rankup.logger.models.system.EventModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class EventAdpter {
    public static String serialize(final List<EventModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final EventModel eventModel : list) {
            joiner.add(eventModel.getEvent()
                    + ":" + eventModel.getPrice()
                    + ":" + eventModel.getDate());
        }
        return joiner.toString();
    }

    public static List<EventModel> deserialize(final String data) {
        final List<EventModel> list = new ArrayList<EventModel>();
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

    private static void formatAndPut(final List<EventModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String event = split[0];
        final Double price = Double.parseDouble(split[1]);
        final long sentDate = Long.parseLong(split[2]);
        final EventModel eventModel = new EventModel(event, price, sentDate);
        list.add(eventModel);
    }

}
