package br.net.rankup.logger.adpter.server;

import br.net.rankup.logger.models.server.ItemDropModel;
import br.net.rankup.logger.models.server.ItemPickUPModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ItemDropAdpter {
    public static String serialize(final List<ItemDropModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final ItemDropModel itemDropModel : list) {
            joiner.add(itemDropModel.getOwner()
                    + ":" + itemDropModel.getLocation()
                    + ":" + itemDropModel.getItemName()
                    + ":" + itemDropModel.getItemID()
                    + ":" + itemDropModel.getAmount()
                    + ":" + itemDropModel.getDate());
        }
        return joiner.toString();
    }

    public static List<ItemDropModel> deserialize(final String data) {
        final List<ItemDropModel> list = new ArrayList<ItemDropModel>();
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

    private static void formatAndPut(final List<ItemDropModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String sender = split[0];
        final String loc = split[1];
        final String itemName = split[2];
        final int itemID = Integer.parseInt(split[3]);
        final int amount = Integer.parseInt(split[4]);
        final long sentDate = Long.parseLong(split[5]);
        final ItemDropModel itemDropModel = new ItemDropModel(sender, loc, itemName, itemID, amount, sentDate);
        list.add(itemDropModel);
    }

}
