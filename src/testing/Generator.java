package testing;

import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    private ThreadLocalRandom current;

    public Generator() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
    }

    public int nextInt(int lower, int upper) {
        return current.nextInt(lower, upper + 1);
    }

    public int[] nextPermutation(int n, int offset) {
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = i + offset;
        }

        for (int i = n - 1; i > 0; i--) {
            int k = this.nextInt(0, n - 1);
            int tmp = a[i];
            a[i] = a[k];
            a[k] = tmp;
        }

        return a;
    }
}
