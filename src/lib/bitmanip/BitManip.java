package lib.bitmanip;

public class BitManip {
    static final int INT_MASK_SIZE = 31;

    private BitManip() {}

    public static int getBit(int x, int i) {
        return (x >> i) & 1;
    }

    public static int getBit(long x, int i) {
        return (int) ((x >> i) & 1L);
    }

    public static int shiftLeftFillOne(int x) {
        return (x << 1)  + 1;
    }

    public static boolean isPowerTwo(int x, int power) {
        return (x ^ (1 << power)) == 0;
    }

    public static int powerTwo(int power) {
        if (power > 30) throw new ArithmeticException();
        return 1 << power;
    }

    public static long powerTwoL(int power) {
        return 1L << power;
    }

    public static int countOneBits(int x) {
        int res = 0;

        while (x > 0) {
            if ((x & 1) == 1) {
                ++res;
            }

            x >>= 1;
        }

        return res;
    }

    public static int setBit(int x, int i) {
        return x | (1 << i);
    }

    public static long setBit(long x, int i) {
        return x | (1L << i);
    }

    public static String toString(int x) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 30; i > -1; i--) {
            stringBuilder.append(getBit(x, i));
        }

        return stringBuilder.toString();
    }
}
