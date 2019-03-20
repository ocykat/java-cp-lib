package lib.io;


import java.io.*;

public class OutputWriter {
    private PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object... objects) {
        for (Object object : objects) {
            writer.print(object);
        }
    }

    public void println(Object... objects) {
        print(objects);
        writer.println();
    }

    public void printf(String format, Object... objects) {
        writer.printf(format, objects);
    }

    public void close() {
        writer.close();
    }
}

