package lib.containers.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @default minHeap
 * @base 0
 */

public class Heap<T> {
    public static final int DEFAULT_SIZE = 1024;
    private int maxSize;
    private int size;
    private T[] keys;
    private Comparator<T> comparator;

    public Heap() {
        this.maxSize = DEFAULT_SIZE;
        this.keys = (T[]) new Object[this.maxSize];
        this.size = 0;
    }

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.keys = (T[]) new Object[maxSize];
        this.size = 0;
    }

    public Heap(int maxSize, Comparator<T> comparator) {
        this.maxSize = maxSize;
        this.keys = (T[]) new Object[maxSize];
        this.size = 0;
        this.comparator = comparator;
    }

    public Heap(T[] keys) {
        this.maxSize = keys.length;
        this.size = keys.length;
        for (int i = 0; i < keys.length; i++) {
            this.keys[i] = keys[i];
        }
    }

    public Heap(T[] keys, Comparator<T> comparator) {
        this.maxSize = keys.length;
        this.size = keys.length;
        for (int i = 0; i < keys.length; i++) {
            this.keys[i] = keys[i];
        }
        this.comparator = comparator;
    }

    public Heap(ArrayList<T> keys) {
        this.maxSize = keys.size();
        this.size = keys.size();
        this.keys=  (T[]) keys.toArray();
    }

    public Heap(ArrayList<T> keys, Comparator<T> comparator) {
        this.maxSize = keys.size();
        this.size = keys.size();
        this.keys=  (T[]) keys.toArray();
        this.comparator = comparator;
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    private int getLeft(int i) {
        return i * 2 + 1;
    }

    private int getRight(int i) {
        return i * 2 + 2;
    }

    public void swapKey(int i, int j) {
        T tmp = this.keys[i];
        this.keys[i] = this.keys[j];
        this.keys[j] = tmp;
    }

    private void heapify(int i) {
        int left = this.getLeft(i);
        int right = this.getRight(i);
        int smallest = i;
        if (left < this.size && this.comparator.compare(this.keys[left], this.keys[smallest]) < 0) {
            smallest = left;
        }
        if (right < this.size && this.comparator.compare(this.keys[right], this.keys[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            this.swapKey(i, smallest);
            this.heapify(smallest);
        }
    }

    public void buildHeap() {
        for (int i = this.getParent(this.size - 1); i > -1; i--) {
            this.heapify(i);
        }
    }

    private void reallocate() {
        T[] oldKeys = this.keys;
        this.keys = (T[]) new Object[this.maxSize * 2];
        for (int i = 0; i < this.size; i++) {
            this.keys[i] = oldKeys[i];
        }
        this.maxSize *= 2;
    }

    public void push(T key) {
        if (this.size == this.maxSize) {
            this.reallocate();
        }
        this.keys[this.size] = key;
        this.size++;
        int i = this.size - 1;
        while (i > 0 && this.comparator.compare(this.keys[i], this.keys[this.getParent(i)]) < 0) {
            this.swapKey(i, this.getParent(i));
            i = this.getParent(i);
        }
    }

    public T top() {
        if (this.size == 0) {
            throw new RuntimeException("Heap underflow");
        }
        return this.keys[0];
    }

    public T pop() {
        if (this.size == 0) {
            throw new RuntimeException("Heap underflow");
        }
        T key = this.keys[0];
        this.keys[0] = this.keys[this.size - 1];
        this.size--;
        this.heapify(0);
        return key;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Heap: size = ");
        stringBuilder.append(this.size);
        stringBuilder.append("; keys = ");
        stringBuilder.append(Arrays.toString(this.keys));
        return stringBuilder.toString();
    }
}
