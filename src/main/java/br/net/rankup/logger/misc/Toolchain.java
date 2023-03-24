package br.net.rankup.logger.misc;

import java.text.*;

public class Toolchain
{
    private static final DecimalFormat DECIMAL_FORMAT;
    private static final String[] MONEY_FORMATS;
    
    public static String format(final double value) {
        if (value <= 999999.0) {
            return Toolchain.DECIMAL_FORMAT.format(value);
        }
        final int zeros = (int)Math.log10(value);
        final int thou = zeros / 3;
        final int arrayIndex = Math.min(thou - 2, Toolchain.MONEY_FORMATS.length - 1);
        return Toolchain.DECIMAL_FORMAT.format(value / Math.pow(1000.0, arrayIndex + 2.0)) + Toolchain.MONEY_FORMATS[arrayIndex];
    }
    
    static {
        DECIMAL_FORMAT = new DecimalFormat("#,###.#");
        MONEY_FORMATS = new String[] { "M", "B", "T", "Q", "QQ", "S", "SS", "OC", "N", "D", "UN", "DD", "TR", "QT", "QN", "SD", "SPD", "OD", "ND", "VG" };
    }
}
