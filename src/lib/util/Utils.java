package lib.util;

public class Utils {
    private Utils() {}

    public static int upperToInt(char c) {
        if (c < 'A' || c > 'Z') throw new RuntimeException();
        return c - 'A';
    }

    public static int lowerToInt(char c) {
        if (c < 'a' || c > 'z') throw new RuntimeException();
        return c - 'a';
    }
}
