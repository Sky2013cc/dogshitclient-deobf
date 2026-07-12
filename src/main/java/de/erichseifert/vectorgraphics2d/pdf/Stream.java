package de.erichseifert.vectorgraphics2d.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/pdf/Stream.class */
final class Stream extends OutputStream implements PDFObject {
    private final ByteArrayOutputStream a = new ByteArrayOutputStream();
    private final List<Filter> b;
    private OutputStream c;
    private boolean d;

    /* loaded from: target.jar:de/erichseifert/vectorgraphics2d/pdf/Stream$Filter.class */
    public enum Filter {
        FLATE
    }

    public Stream(Filter... filterArr) {
        this.b = new ArrayList(filterArr.length);
        this.b.addAll(Arrays.asList(filterArr));
        this.c = this.a;
        for (Filter filter : filterArr) {
            if (filter == Filter.FLATE) {
                this.c = new DeflaterOutputStream(this.c);
            }
        }
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        if (this.d) {
            throw new IOException("Unable to write to closed stream.");
        }
        try {
            this.c.write(i);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to the output stream", e);
        }
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) throws IOException {
        if (this.d) {
            throw new IOException("Unable to write to closed stream.");
        }
        try {
            this.c.write(bArr);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to the output stream", e);
        }
    }

    public final int a() {
        if (!this.d) {
            throw new IllegalStateException("Unable to determine the length of an open Stream. Close the stream first.");
        }
        return this.a.size();
    }

    public final byte[] b() {
        if (!this.d) {
            throw new IllegalStateException("Unable to retrieve the content of an open Stream. Close the stream first.");
        }
        return this.a.toByteArray();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.d = true;
        try {
            this.c.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final List<Filter> c() {
        return Collections.unmodifiableList(this.b);
    }
}
