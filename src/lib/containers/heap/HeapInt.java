package lib.containers.heap;

public class HeapInt {
    final int DEFAULT_CAPACITY = 1024;
    int capacity;
    int[] keys;
    int size = 0;

    public HeapInt() {
        keys = new int[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    public HeapInt(int capacity) {
        keys = new int[capacity];
        this.capacity = capacity;
    }

    public HeapInt(int[] keys) {
        size = keys.length;
        capacity = Math.max(keys.length, DEFAULT_CAPACITY);
        this.keys = new int[capacity];
        System.arraycopy(keys, 0, this.keys, 0, size);
        build();
    }

    public int getParent(int i) {
        return (i - 1) / 2;
    }

    public int getLeft(int i) {
        return i * 2 + 1;
    }

    public int getRight(int i) {
        return i * 2 + 2;
    }

    public void swap(int i, int j) {
        int tmp = keys[i];
        keys[i] = keys[j];
        keys[j] = tmp;
    }

    public void heapify(int i) {
        int left = getLeft(i);
        int right = getRight(i);
        int smallest = i;

        if (left < size && keys[left] < keys[smallest]) {
            smallest = left;
        }

        if (right < size && keys[right] < keys[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    public void build() {
        for (int i = size / 2; i > -1; i--) {
            heapify(i);
        }
    }

    public void push(int key) {
        if (size == capacity) {
            reallocate();
        }
        keys[size] = key;
        size++;
        int i = size - 1;
        while (i > 0 && keys[i] < keys[getParent(i)]) {
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    private void reallocate() {
        int[] oldKeys = keys;
        keys = new int[capacity * 2];
        System.arraycopy(oldKeys, 0, keys, 0, capacity);
        capacity *= 2;
    }

    public int top() {
        if (size == 0) {
            throw new RuntimeException("Heap underflow");
        }
        return keys[0];
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("Heap underflow");
        }
        int key = keys[0];
        keys[0] = keys[size - 1];
        size--;
        heapify(0);
        return key;
    }
}
