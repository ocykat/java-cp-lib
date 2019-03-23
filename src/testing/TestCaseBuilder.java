package testing;

public class TestCaseBuilder {
    StringBuilder builder;

    public TestCaseBuilder() {
        this.builder = new StringBuilder();
    }

    public void print(Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            builder.append(objects[i]);
        }
    }

    public void println(Object... objects) {
        print(objects);
        builder.append('\n');
    }

    public void print(int[] a) {
        for (int x : a) {
            print(x, ' ');
        }
        print('\n');
    }

    public String toString() {
        return builder.toString();
    }
}
