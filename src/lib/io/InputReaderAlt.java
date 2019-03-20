package lib.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputReaderAlt {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReaderAlt(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream), 32768);
        tokenizer = null;
    }

    /**
     * @return String the whole line (including all whitespaces)
     *         or null if the line is empty
     */
    public String nextLine() {
        this.tokenizer = null;
        String line = null;
        try {
            line = reader.readLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (line.isEmpty()) {
            return null;
        }
        return line;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = nextLine();
            if (line == null || line.isEmpty()) return null;
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}


