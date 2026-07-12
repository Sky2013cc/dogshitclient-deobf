package org.apache.pdfbox.io;

import java.io.IOException;

/* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessReadView.class */
public class RandomAccessReadView implements RandomAccessRead {
    private RandomAccessRead randomAccessRead;
    private final long startPosition;
    private final long streamLength;
    private final boolean closeInput;
    private long currentPosition;

    public RandomAccessReadView(RandomAccessRead randomAccessRead, long startPosition, long streamLength) {
        this(randomAccessRead, startPosition, streamLength, false);
    }

    public RandomAccessReadView(RandomAccessRead randomAccessRead, long startPosition, long streamLength, boolean closeInput) {
        this.currentPosition = 0L;
        this.randomAccessRead = randomAccessRead;
        this.startPosition = startPosition;
        this.streamLength = streamLength;
        this.closeInput = closeInput;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long getPosition() throws IOException {
        checkClosed();
        return this.currentPosition;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public void seek(long newOffset) throws IOException {
        checkClosed();
        if (newOffset < 0) {
            throw new IOException("Invalid position " + newOffset);
        }
        this.randomAccessRead.seek(this.startPosition + Math.min(newOffset, this.streamLength));
        this.currentPosition = newOffset;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public int read() throws IOException {
        if (isEOF()) {
            return -1;
        }
        restorePosition();
        int readValue = this.randomAccessRead.read();
        if (readValue > -1) {
            this.currentPosition++;
        }
        return readValue;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public int read(byte[] b, int off, int len) throws IOException {
        if (isEOF()) {
            return -1;
        }
        restorePosition();
        int readBytes = this.randomAccessRead.read(b, off, Math.min(len, available()));
        this.currentPosition += readBytes;
        return readBytes;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public long length() throws IOException {
        checkClosed();
        return this.streamLength;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closeInput && this.randomAccessRead != null) {
            this.randomAccessRead.close();
        }
        this.randomAccessRead = null;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isClosed() {
        return this.randomAccessRead == null || this.randomAccessRead.isClosed();
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public void rewind(int bytes) throws IOException {
        checkClosed();
        restorePosition();
        this.randomAccessRead.rewind(bytes);
        this.currentPosition -= bytes;
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public boolean isEOF() throws IOException {
        checkClosed();
        return this.currentPosition >= this.streamLength;
    }

    private void restorePosition() throws IOException {
        this.randomAccessRead.seek(this.startPosition + this.currentPosition);
    }

    private void checkClosed() throws IOException {
        if (isClosed()) {
            throw new IOException("RandomAccessReadView already closed");
        }
    }

    @Override // org.apache.pdfbox.io.RandomAccessRead
    public RandomAccessReadView createView(long startPosition, long streamLength) throws IOException {
        throw new IOException(getClass().getName() + ".createView isn't supported.");
    }
}
