package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberFormatter {

    private static final DecimalFormatSymbols symbols;
    private static final DecimalFormat df2;
    private static final DecimalFormat df1;

    static {
        symbols = new DecimalFormatSymbols(Locale.ROOT);
        symbols.setDecimalSeparator('.');

        df2 = new DecimalFormat("#.##", symbols);
        df1 = new DecimalFormat("#.#", symbols);
    }

    public static String format2(Double d) {
        if (d == null) return "N/A";
        return df2.format(d);
    }

    public static String format1(Double d) {
        if (d == null) return "N/A";
        return df1.format(d);
    }

    public static String formatCSV(Double d) {
        if (d == null) return "";
        return df2.format(d);
    }
}