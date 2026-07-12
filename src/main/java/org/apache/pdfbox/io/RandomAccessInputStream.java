package org.apache.pdfbox.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: target.jar:org/apache/pdfbox/io/RandomAccessInputStream.class */
public class RandomAccessInputStream extends InputStream {
    private static final Log LOG = LogFactory.getLog(RandomAccessInputStream.class);
    private final RandomAccessRead input;
    private long position = 0;

    public RandomAccessInputStream(RandomAccessRead randomAccessRead) {
        this.input = randomAccessRead;
    }

    void restorePosition() throws IOException {
        this.input.seek(this.position);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return (int) Math.max(0L, Math.min(this.input.length() - this.position, 2147483647L));
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        restorePosition();
        if (this.input.isEOF()) {
            return -1;
        }
        int b = this.input.read();
        if (b != -1) {
            this.position++;
        } else {
            LOG.error("read() returns -1, assumed position: " + this.position + ", actual position: " + this.input.getPosition());
        }
        return b;
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        restorePosition();
        if (this.input.isEOF()) {
            return -1;
        }
        int n = this.input.read(b, off, len);
        if (n != -1) {
            this.position += n;
        } else {
            LOG.error("read() returns -1, assumed position: " + this.position + ", actual position: " + this.input.getPosition());
        }
        return n;
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        if (n <= 0) {
            return 0L;
        }
        restorePosition();
        this.input.seek(this.position + n);
        this.position += n;
        return n;
    }
}
