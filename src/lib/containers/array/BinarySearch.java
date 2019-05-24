package lib.containers.array;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearch {
    private BinarySearch() {}

    public static int search(int[] a, int x) {
        int left = 0;
        int right = a.length - 1;

        return search(a, x, left, right);
    }

    public static int search(int[] a, int x, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (a[mid] == x) {
                return mid;
            }
            else if (a[mid] < x) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int search(long[] a, long x) {
        int left = 0;
        int right = a.length - 1;

        return search(a, x, left, right);
    }

    public static int search(long[] a, long x, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (a[mid] == x) {
                return mid;
            }
            else if (a[mid] < x) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int search(char[] a, char x) {
        int left = 0;
        int right = a.length - 1;

        return search(a, x, left, right);
    }

    public static int search(char[] a, char x, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (a[mid] == x) {
                return mid;
            }
            else if (a[mid] < x) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static <T extends Comparable<? super T>> int search(T[] a, T x) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (x.compareTo(a[mid]) == 0) {
                return mid;
            }
            else if (x.compareTo(a[mid]) > 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static <T extends Comparable<? super T>> int search(ArrayList<T> a, T x) {
        int left = 0;
        int right = a.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (x.compareTo(a.get(mid)) == 0) {
                return mid;
            }
            else if (x.compareTo(a.get(mid)) > 0) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return -1;
    }

    public static <T> int search(ArrayList<T> a, T x, Comparator<T> cmp) {
        int left = 0;
        int right = a.size() - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (cmp.compare(x, a.get(mid)) == 0) {
                return mid;
            }
            else if (cmp.compare(x, a.get(mid)) > 0) {
                left = mid + 1;
            }
            else {
                right = mid + 1;
            }
        }

        return -1;
    }

    /**
     *
     * @param a
     * @param x
     * @return the index of the first element that does not compare less than x
     */
    public static int lowerBound(int[] a, int x) {
        return lowerBound(a, x, 0, a.length);
    }

    /**
     *
     * @param a
     * @param x
     * @param left  leftmost index, inclusive
     * @param right rightmost index, exclusive
     * @return the index of the first element that does not compare less than x
     */
    public static int lowerBound(int[] a, int x, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (a[mid] < x) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left;
    }

    public static <T extends Comparable<T>> int lowerBound(T[] a, T x) {
        int left = 0;
        int right = a.length;
        return lowerBound(a, x, left, right);
    }

    public static <T extends Comparable<T>> int lowerBound(T[] a, T x, int left, int right) {
         while (left < right) {
            int mid = (left + right) / 2;

            if (a[mid].compareTo(x) < 0) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left;
    }

    public static int upperBound(int[] a, int x) {
        return upperBound(a, x, 0, a.length);
    }

    public static int upperBound(int[] a, int x, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (a[mid] <= x) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left;
    }

    public static <T extends Comparable<T>> int upperBound(T[] a, T x) {
        int left = 0;
        int right = a.length;
        return upperBound(a, x, left, right);
    }

    public static <T extends Comparable<T>> int upperBound(T[] a, T x, int left, int right) {
         while (left < right) {
            int mid = (left + right) / 2;

            if (a[mid].compareTo(x) <= 0) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left;
    }
}

