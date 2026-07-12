package com.sun.tools.internal.xjc.util;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: target.jar:com/sun/tools/internal/xjc/util/NullStream.class */
public class NullStream extends OutputStream {
    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
    }
}
