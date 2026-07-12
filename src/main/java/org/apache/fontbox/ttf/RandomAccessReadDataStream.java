package org.apache.fontbox.ttf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.io.RandomAccessRead;

/* loaded from: target.jar:org/apache/fontbox/ttf/RandomAccessReadDataStream.class */
class RandomAccessReadDataStream extends TTFDataStream {
    private final long length;
    private final byte[] data;
    private int currentPosition = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RandomAccessReadDataStream(RandomAccessRead randomAccessRead) throws IOException {
        this.length = randomAccessRead.length();
        this.data = new byte[(int) this.length];
        int length = this.data.length;
        while (true) {
            int remainingBytes = length;
            int amountRead = randomAccessRead.read(this.data, this.data.length - remainingBytes, remainingBytes);
            if (amountRead > 0) {
                length = remainingBytes - amountRead;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RandomAccessReadDataStream(InputStream inputStream) throws IOException {
        this.data = IOUtils.toByteArray(inputStream);
        this.length = this.data.length;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public long getCurrentPosition() throws IOException {
        return this.currentPosition;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public int read() throws IOException {
        if (this.currentPosition >= this.length) {
            return -1;
        }
        byte[] bArr = this.data;
        int i = this.currentPosition;
        this.currentPosition = i + 1;
        return bArr[i] & 255;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public final long readLong() throws IOException {
        return (readInt() << 32) + (readInt() & 4294967295L);
    }

    private int readInt() throws IOException {
        int b1 = read();
        int b2 = read();
        int b3 = read();
        int b4 = read();
        return (b1 << 24) + (b2 << 16) + (b3 << 8) + b4;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public void seek(long pos) throws IOException {
        if (pos < 0) {
            throw new IOException("Invalid position " + pos);
        }
        this.currentPosition = pos < this.length ? (int) pos : (int) this.length;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.currentPosition >= this.length) {
            return -1;
        }
        int remainingBytes = (int) (this.length - this.currentPosition);
        int bytesToRead = Math.min(remainingBytes, len);
        System.arraycopy(this.data, this.currentPosition, b, off, bytesToRead);
        this.currentPosition += bytesToRead;
        return bytesToRead;
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public InputStream getOriginalData() throws IOException {
        return new ByteArrayInputStream(this.data);
    }

    @Override // org.apache.fontbox.ttf.TTFDataStream
    public long getOriginalDataSize() {
        return this.length;
    }
}
