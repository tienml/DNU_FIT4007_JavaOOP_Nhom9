package util;

public class Validator {

    public static boolean isValidScore(Double d) {
        if (d == null) return true;
        return d >= 0 && d <= 10;
    }

    public static boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}