package lib.containers.array;

public class ArrayUtils {
    private ArrayUtils() {}

    public static void reverse(int[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            int tmp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = tmp;
        }
    }

    public static void reverse(long[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            long tmp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = tmp;
        }
    }
}
