package lib.containers.tree.segment;

public abstract class SegmentTreeBase<T> {
    public int size;
    private T[] tree;

    public SegmentTreeBase(T[] a) {
        this.size = a.length;
        this.tree = (T[]) new Object[this.size * 4];
        build(a);
    }

    private int getLeft(int node) {
        return node * 2 + 1;
    }

    private int getRight(int node) {
        return node * 2 + 2;
    }

    public abstract T combine(T x, T y);

    private void build(T[] a, int node, int low, int high) {
        if (low == high) {
            tree[node] = a[low];
        }
        else {
            int mid_n = (low + high) / 2;
            build(a, getLeft(node), low, mid_n);
            build(a, getRight(node), mid_n + 1, high);
            tree[node] = combine(tree[getLeft(node)], tree[getRight(node)]);
        }
    }

    public void build(T[] a) {
        build(a, 0, 0, size - 1);
    }

    private T query(int node, int low, int high, int from, int to) {
        if (low == from && high == to) {
            return tree[node];
        }
        else {
            int mid = (low + high) / 2;
            if (to <= mid) {       // only the left child stores query range
                return query(getLeft(node), low, mid, from, to);
            }
            else if (from > mid) { // only the right child stores query range
                return query(getRight(node), mid + 1, high, from, to);
            }
            else {                 // both children store query range
                return combine(
                    query(getLeft(node), low, mid, from, mid),
                    query(getRight(node), mid + 1, high, mid + 1, to)
                );
            }
        }
    }

    public T query(int from, int to) {
        return query(0, 0, size - 1, from, to);
    }

    private void update(int node, int low, int high, int pos, T new_val) {
        if (low == high) {
            tree[node] = new_val;
        }
        else {
            int mid = (low + high) / 2;

            if (pos <= mid) {
                update(getLeft(node), low, mid, pos, new_val);
            }
            else {
                update(getRight(node), mid + 1, high, pos, new_val);
            }

            tree[node] = combine(tree[getLeft(node)], tree[getRight(node)]);
        }
    }

    public void update(int pos, T new_val) {
        update(0, 0, size - 1, pos, new_val);
    }

    private void add(int node, int low, int high, int pos, T delta) {
        if (low == high) {
            tree[node] = combine(tree[node], delta);
        }
        else {
            int mid = (low + high) / 2;

            if (pos <= mid) {
                add(getLeft(node), low, mid, pos, delta);
            }
            else {
                add(getRight(node), mid + 1, high, pos, delta);
            }

            tree[node] = combine(tree[getLeft(node)], tree[getRight(node)]);
        }
    }

    public void add(int pos, T delta) {
        add(0, 0, size - 1, pos, delta);
    }

    private T get(int node, int low, int high, int pos) {
        if (low == high) {
            return tree[node];
        }
        else {
            int mid = (low + high) / 2;

            if (pos <= mid) {
                return get(getLeft(node), low, mid, pos);
            }
            else {
                return get(getRight(node), mid + 1, high, pos);
            }
        }
    }

    public T get(int pos) {
        return get(0, 0, size - 1, pos);
    }
}
