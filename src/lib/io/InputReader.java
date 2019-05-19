package lib.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class InputReader {
    private final int BUFFER_SIZE = 32768;
    private InputStream stream;
    private byte[] buffer = new byte[BUFFER_SIZE + 1];
    private int pointer = 1;
    private int readLength = 0;
    private int lastWhiteSpace = '\n';

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    private void fillBuffer() {
        try {
            readLength = stream.read(buffer, 1, BUFFER_SIZE);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte get() {
        if (pointer > readLength) {
            pointer = 1;
            fillBuffer();
            if (readLength <= 0) return -1;
        }
        return buffer[pointer++];
    }

    private byte peek() {
        if (pointer > readLength) {
            pointer = 1;
            fillBuffer();
            if (readLength <= 0) return -1;
        }
        return buffer[pointer];
    }

    public boolean hasNext() {
        int c;
        while (isWhiteSpace(c = peek()) && c != -1) {
            get();
        }
        return c != -1;
    }

    public char nextChar() {
        int c = get();

        while (isWhiteSpace(c)) {
            c = get();
        }

        return (char) c;
    }

    public int nextInt() {
        int c = nextChar();
        int sign = 1;

        if (c == '-') {
            sign = -1;
            c = get();
        }

        int abs = 0;

        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            abs *= 10;
            abs += c - '0';
            c = get();
        } while (!isWhiteSpace(c));

        lastWhiteSpace = c;

        return abs * sign;
    }

    public long nextLong() {
        int c = nextChar();
        long sign = 1;

        if (c == '-') {
            sign = -1;
            c = get();
        }

        long abs = 0;

        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            abs *= 10;
            abs += c - '0';
            c = get();
        } while (!isWhiteSpace(c));

        lastWhiteSpace = c;

        return abs * sign;
    }

    public double nextDouble() {
        int c = nextChar();
        int sign = 1;

        if (c == '-') {
            sign = -1;
            c = get();
        }

        double abs = 0;

        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            abs *= 10;
            abs += (c - '0');
            c = get();
        } while (!isWhiteSpace(c) && c != '.');

        if (c == '.') {
            c = get();
            double m = 1.0;

            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                m /= 10;
                abs += (c - '0') * m;
                c = get();
            } while (!isWhiteSpace(c));
        }

        return abs * sign;
    }

    public String nextString() {
        int c = nextChar();

        if (c == -1) return null;

        StringBuilder builder = new StringBuilder();

        do {
            builder.append((char) c);
            c = get();
        } while (!isWhiteSpace(c));

        return builder.toString();
    }

    public String next() {
        return nextString();
    }

    public String nextLine() {
        if (lastWhiteSpace != '\r' && lastWhiteSpace != '\n') throw new InputMismatchException();

        int c = get();

        if (lastWhiteSpace == '\r') {
            if (c == '\n') {
                lastWhiteSpace = '\n';
                c = get();
            }
            else {
                --pointer;
            }
        }

        if (c == -1 || c == '\r' || c == '\n') return null; // empty line

        StringBuilder builder = new StringBuilder();

        do {
            builder.append(c);
            c = get();
        } while (c != '\r' && c != '\n');

        return builder.toString();
    }

    public boolean isWhiteSpace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
}
