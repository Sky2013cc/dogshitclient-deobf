package de.erichseifert.vectorgraphics2d.util;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/FormattingWriter.class */
public class FormattingWriter implements Closeable, Flushable, AutoCloseable {
    private final OutputStream a;
    private final String b;
    private final byte[] c;
    private long d;

    public FormattingWriter(OutputStream outputStream, String str, String str2) throws UnsupportedEncodingException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream cannot be null.");
        }
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("End-of-line string cannot be empty.");
        }
        this.a = outputStream;
        this.b = str;
        this.c = str2.getBytes(str);
    }

    public FormattingWriter write(byte[] bArr) throws IOException {
        this.a.write(bArr, 0, bArr.length);
        this.d += bArr.length;
        return this;
    }

    public FormattingWriter write(String str) throws IOException {
        return write(str.getBytes(this.b));
    }

    public FormattingWriter write(String str, Object... objArr) throws IOException {
        return write(String.format(null, str, objArr));
    }

    public FormattingWriter write(Number number) throws IOException {
        return write(DataUtils.format(number));
    }

    public FormattingWriter writeln() throws IOException {
        return write(this.c);
    }

    public FormattingWriter writeln(byte[] bArr) throws IOException {
        write(bArr);
        return writeln();
    }

    public FormattingWriter writeln(String str) throws IOException {
        write(str);
        return writeln();
    }

    public FormattingWriter writeln(String str, Object... objArr) throws IOException {
        write(String.format(null, str, objArr));
        return writeln();
    }

    public FormattingWriter writeln(Number number) throws IOException {
        write(number);
        return writeln();
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.a.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.a.close();
    }

    public long tell() {
        return this.d;
    }
}
