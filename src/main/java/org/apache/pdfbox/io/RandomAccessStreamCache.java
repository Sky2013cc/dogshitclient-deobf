package org.apache.pdfbox.io;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessStreamCache.class */
public interface RandomAccessStreamCache extends Closeable {

    @FunctionalInterface
    /* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessStreamCache$StreamCacheCreateFunction.class */
    public interface StreamCacheCreateFunction {
        RandomAccessStreamCache create() throws IOException;
    }

    RandomAccess createBuffer() throws IOException;
}
