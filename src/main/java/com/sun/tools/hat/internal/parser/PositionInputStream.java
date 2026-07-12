package com.sun.tools.hat.internal.parser;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: target.jar:com/sun/tools/hat/internal/parser/PositionInputStream.class */
public class PositionInputStream extends FilterInputStream {
    private long position;

    public PositionInputStream(InputStream inputStream) {
        super(inputStream);
        this.position = 0L;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        if (read != -1) {
            this.position++;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.position += read;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long skip = super.skip(j);
        this.position += skip;
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        throw new UnsupportedOperationException("mark");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
        throw new UnsupportedOperationException("reset");
    }

    public long position() {
        return this.position;
    }
}
