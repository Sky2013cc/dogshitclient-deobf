package org.apache.fontbox.ttf;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: target.jar:org/apache/fontbox/ttf/TTCDataStream.class */
class TTCDataStream extends TTFDataStream {
    private final TTFDataStream stream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TTCDataStream(TTFDataStream stream) {
        this.stream = stream;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public int read() throws IOException {
        return this.stream.read();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public long readLong() throws IOException {
        return this.stream.readLong();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public void seek(long pos) throws IOException {
        this.stream.seek(pos);
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public int read(byte[] b, int off, int len) throws IOException {
        return this.stream.read(b, off, len);
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public long getCurrentPosition() throws IOException {
        return this.stream.getCurrentPosition();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public InputStream getOriginalData() throws IOException {
        return this.stream.getOriginalData();
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public long getOriginalDataSize() {
        return this.stream.getOriginalDataSize();
    }
}
