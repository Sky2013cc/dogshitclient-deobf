package org.apache.pdfbox.pdfwriter.compress;

/* loaded from: target.jar:org/apache/pdfbox/pdfwriter/compress/CompressParameters.class */
public class CompressParameters {
    public static final CompressParameters DEFAULT_COMPRESSION = new CompressParameters();
    public static final CompressParameters NO_COMPRESSION = new CompressParameters(0);
    public static final int DEFAULT_OBJECT_STREAM_SIZE = 200;
    private final int objectStreamSize;

    public CompressParameters() {
        this(200);
    }

    public CompressParameters(int objectStreamSize) {
        if (objectStreamSize < 0) {
            throw new IllegalArgumentException("Object stream size can't be a negative value");
        }
        this.objectStreamSize = objectStreamSize;
    }

    public int getObjectStreamSize() {
        return this.objectStreamSize;
    }

    public boolean isCompress() {
        return this.objectStreamSize > 0;
    }
}
