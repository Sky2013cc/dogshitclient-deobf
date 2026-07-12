package org.apache.pdfbox.io;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessWrite.class */
public interface RandomAccessWrite extends Closeable {
    void write(int i) throws IOException;

    void write(byte[] bArr) throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;

    void clear() throws IOException;
}
