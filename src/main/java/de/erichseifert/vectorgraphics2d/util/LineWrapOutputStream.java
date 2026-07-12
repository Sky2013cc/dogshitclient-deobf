package de.erichseifert.vectorgraphics2d.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/LineWrapOutputStream.class */
public class LineWrapOutputStream extends FilterOutputStream {
    public static final String STANDARD_EOL = "\r\n";
    private final int a;
    private final byte[] b;
    private int c;

    public LineWrapOutputStream(OutputStream outputStream, int i, String str) {
        super(outputStream);
        this.a = i;
        this.b = str.getBytes();
        if (i <= 0) {
            throw new IllegalArgumentException("Width must be at least 0.");
        }
    }

    public LineWrapOutputStream(OutputStream outputStream, int i) {
        this(outputStream, i, "\r\n");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.c == this.a) {
            this.out.write(this.b);
            this.c = 0;
        }
        this.out.write(i);
        this.c++;
    }
}
