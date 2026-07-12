package org.apache.pdfbox.io;

import java.io.IOException;

/* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessStreamCacheImpl.class */
public class RandomAccessStreamCacheImpl implements RandomAccessStreamCache {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // org.apache.pdfbox.io.RandomAccessStreamCache
    public RandomAccess createBuffer() throws IOException {
        return new RandomAccessReadWriteBuffer();
    }
}
