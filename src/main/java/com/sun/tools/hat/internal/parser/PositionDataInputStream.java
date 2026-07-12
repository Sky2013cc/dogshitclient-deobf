package com.sun.tools.hat.internal.parser;

import java.io.DataInputStream;
import java.io.InputStream;

/* loaded from: target.jar:com/sun/tools/hat/internal/parser/PositionDataInputStream.class */
public class PositionDataInputStream extends DataInputStream {
    public PositionDataInputStream(InputStream inputStream) {
        super(inputStream instanceof PositionInputStream ? inputStream : new PositionInputStream(inputStream));
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
        return ((PositionInputStream) this.in).position();
    }
}
