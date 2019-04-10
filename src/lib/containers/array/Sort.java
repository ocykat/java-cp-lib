package lib.containers.array;

import lib.containers.heap.HeapInt;

public class Sort {
    private Sort() {}

    public static void sort(int a[]) {
        sort(a, false);
    }

    public static void sort(int a[], boolean reverse) {
        HeapInt heap = new HeapInt(a);

        for (int i = 0; i < a.length; i++) {
            a[i] = heap.pop();
        }

        if (reverse) {
            ArrayUtils.reverse(a);
        }
    }
}
