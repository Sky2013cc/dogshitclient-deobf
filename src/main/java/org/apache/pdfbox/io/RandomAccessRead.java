package org.apache.pdfbox.io;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessRead.class */
public interface RandomAccessRead extends Closeable {
    int read() throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    long getPosition() throws IOException;

    void seek(long j) throws IOException;

    long length() throws IOException;

    boolean isClosed();

    boolean isEOF() throws IOException;

    RandomAccessReadView createView(long j, long j2) throws IOException;

    default int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    default int peek() throws IOException {
        int result = read();
        if (result != -1) {
            rewind(1);
        }
        return result;
    }

    default void rewind(int bytes) throws IOException {
        seek(getPosition() - bytes);
    }

    default int available() throws IOException {
        return (int) Math.min(length() - getPosition(), 2147483647L);
    }

    default void skip(int length) throws IOException {
        seek(getPosition() + length);
    }
}
