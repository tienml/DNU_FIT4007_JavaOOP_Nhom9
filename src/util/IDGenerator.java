package util;

import java.util.UUID;

public class IDGenerator {

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String nextId(String prefix, int number) {
        return prefix + String.format("%04d", number);
    }
}