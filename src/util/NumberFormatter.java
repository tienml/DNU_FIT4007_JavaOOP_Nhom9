package util;

import java.text.DecimalFormat;

public class NumberFormatter {

    private static final DecimalFormat df2 = new DecimalFormat("#.##");
    private static final DecimalFormat df1 = new DecimalFormat("#.#");

    public static String format2(Double d) {
        if (d == null) return "N/A";
        return df2.format(d);
    }

    public static String format1(Double d) {
        if (d == null) return "N/A";
        return df1.format(d);
    }
}