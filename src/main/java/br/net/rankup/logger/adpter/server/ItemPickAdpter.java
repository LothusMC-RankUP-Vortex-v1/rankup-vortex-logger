package br.net.rankup.logger.adpter.server;

import br.net.rankup.logger.models.server.CommandModel;
import br.net.rankup.logger.models.server.ItemPickUPModel;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ItemPickAdpter {
    public static String serialize(final List<ItemPickUPModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final ItemPickUPModel itemPickUPModel : list) {
            joiner.add(itemPickUPModel.getOwner()
                    + ":" + itemPickUPModel.getLocation()
                    + ":" + itemPickUPModel.getItemName()
                    + ":" + itemPickUPModel.getItemID()
                    + ":" + itemPickUPModel.getAmount()
                    + ":" + itemPickUPModel.getDate());
        }
        return joiner.toString();
    }

    public static List<ItemPickUPModel> deserialize(final String data) {
        final List<ItemPickUPModel> list = new ArrayList<ItemPickUPModel>();
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

    private static void formatAndPut(final List<ItemPickUPModel> list, final String data) {
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
        final ItemPickUPModel itemPickUPModel = new ItemPickUPModel(sender, loc, itemName, itemID, amount, sentDate);
        list.add(itemPickUPModel);
    }

}
