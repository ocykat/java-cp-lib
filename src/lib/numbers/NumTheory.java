package lib.numbers;

public class NumTheory {
    private NumTheory() {}

    public static int eulerPhi(int n) {
        int result = n;

        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                result = result / i * (i - 1);
            }
        }
        if (n > 1) {
            result = result / n * (n - 1);
        }

        return result;
    }
}
