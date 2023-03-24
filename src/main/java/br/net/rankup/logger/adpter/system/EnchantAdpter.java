
package br.net.rankup.logger.adpter.system;

import br.net.rankup.logger.models.system.EconomyModel;
import br.net.rankup.logger.models.system.EnchantModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class EnchantAdpter {
    public static String serialize(final List<EnchantModel> list) {
        final StringJoiner joiner = new StringJoiner(";");
        for (final EnchantModel enchantModel : list) {
            joiner.add(enchantModel.getEnchant()
                    + ":" + enchantModel.getNivel()
                    + ":" + enchantModel.getDate());
        }
        return joiner.toString();
    }

    public static List<EnchantModel> deserialize(final String data) {
        final List<EnchantModel> list = new ArrayList<EnchantModel>();
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

    private static void formatAndPut(final List<EnchantModel> list, final String data) {
        if (!data.contains(":")) {
            return;
        }
        final String[] split = data.split(":");
        final String type = split[0];
        final Double nivel = Double.parseDouble(split[1]);
        final long sentDate = Long.parseLong(split[2]);
        final EnchantModel enchantModel = new EnchantModel(type, nivel, sentDate);
        list.add(enchantModel);
    }

}
