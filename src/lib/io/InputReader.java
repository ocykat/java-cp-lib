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

    private byte nextRawByte() {
        if (pointer > readLength) {
            pointer = 1;

            try {
                readLength = stream.read(buffer, 1, BUFFER_SIZE);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (readLength == -1) return -1;
        }

        return buffer[pointer++];
    }

    public int nextChar() {
        int c = nextRawByte();

        while (isWhiteSpace(c)) {
            c = nextRawByte();
        }

        return c;
    }


    public int nextInt() {
        int c = nextChar();
        int sign = 1;

        if (c == '-') {
            sign = -1;
            c = nextRawByte();
        }

        int abs = 0;

        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            abs = c - '0' + abs * 10;
            c = nextRawByte();
        } while (!isWhiteSpace(c));

        lastWhiteSpace = c;

        return abs * sign;
    }

    public long nextLong() {
        int c = nextChar();
        int sign = 1;

        if (c == '-') {
            sign = -1;
            c = nextRawByte();
        }

        long abs = 0;

        do {
            if (c < '0' || c > '9') throw new InputMismatchException();
            abs = c - '0' + abs * 10;
            c = nextRawByte();
        } while (!isWhiteSpace(c));

        lastWhiteSpace = c;

        return abs * sign;
    }

    public double nextDouble() {
        int c = nextChar();
        int sign = 1;

        if (c == '-') {
            sign = -1;
            c = nextRawByte();
        }

        double abs = 0;

        do {
            abs = (c - '0') + abs * 10;
        } while (!isWhiteSpace(c) && c != '.');

        if (c == '.') {
            int m = 1;

            do {
                abs = abs + (c - '0') * m;
                m /= 10;
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
            c = nextRawByte();
        } while (!isWhiteSpace(c));

        return builder.toString();
    }

    public String next() {
        return nextString();
    }

    public String nextLine() {
        if (lastWhiteSpace != '\r' && lastWhiteSpace != '\n') throw new InputMismatchException();

        int c = nextRawByte();

        if (lastWhiteSpace == '\r') {
            if (c == '\n') {
                lastWhiteSpace = '\n';
                c = nextRawByte();
            }
            else {
                --pointer;
            }
        }

        if (c == -1 || c == '\r' || c == '\n') return null; // empty line

        StringBuilder builder = new StringBuilder();

        do {
            builder.append(c);
            c = nextRawByte();
        } while (c != '\r' && c != '\n');

        return builder.toString();
    }

    public boolean isWhiteSpace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
}
