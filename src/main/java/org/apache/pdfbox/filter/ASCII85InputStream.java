package org.apache.pdfbox.filter;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: target.jar:org/apache/pdfbox/filter/ASCII85InputStream.class */
final class ASCII85InputStream extends FilterInputStream {
    private int index;
    private int n;
    private boolean eof;
    private byte[] ascii;
    private byte[] b;
    private static final char TERMINATOR = '~';
    private static final char OFFSET = '!';
    private static final char NEWLINE = '\n';
    private static final char RETURN = '\r';
    private static final char SPACE = ' ';
    private static final char PADDING_U = 'u';
    private static final char Z = 'z';

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASCII85InputStream(InputStream is) {
        super(is);
        this.index = 0;
        this.n = 0;
        this.eof = false;
        this.ascii = new byte[5];
        this.b = new byte[4];
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c1, code lost:
    
        r11.ascii[r12] = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00cb, code lost:
    
        if (r0 != 126) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ce, code lost:
    
        r11.ascii[r12] = 117;
     */
    @Override // java.io.FilterInputStream, java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int read() throws IOException {
        if (this.index >= this.n) {
            if (this.eof) {
                return -1;
            }
            this.index = 0;
            while (true) {
                int zz = (byte) this.in.read();
                if (zz == -1) {
                    this.eof = true;
                    return -1;
                }
                byte z = (byte) zz;
                if (z != 10 && z != 13 && z != 32) {
                    if (z == 126) {
                        this.eof = true;
                        this.b = null;
                        this.ascii = null;
                        this.n = 0;
                        return -1;
                    }
                    if (z == 122) {
                        byte[] bArr = this.b;
                        byte[] bArr2 = this.b;
                        byte[] bArr3 = this.b;
                        this.b[3] = 0;
                        bArr3[2] = 0;
                        bArr2[1] = 0;
                        bArr[0] = 0;
                        this.n = 4;
                    } else {
                        this.ascii[0] = z;
                        int k = 1;
                        while (true) {
                            if (k >= 5) {
                                break;
                            }
                            while (true) {
                                int zz2 = (byte) this.in.read();
                                if (zz2 == -1) {
                                    this.eof = true;
                                    return -1;
                                }
                                byte z2 = (byte) zz2;
                                if (z2 != 10 && z2 != 13 && z2 != 32) {
                                    break;
                                }
                            }
                            k++;
                        }
                        this.n = k - 1;
                        if (this.n == 0) {
                            this.eof = true;
                            this.ascii = null;
                            this.b = null;
                            return -1;
                        }
                        if (k < 5) {
                            while (true) {
                                k++;
                                if (k >= 5) {
                                    break;
                                }
                                this.ascii[k] = 117;
                            }
                            this.eof = true;
                        }
                        long t = 0;
                        for (int k2 = 0; k2 < 5; k2++) {
                            byte z3 = (byte) (this.ascii[k2] - 33);
                            if (z3 < 0 || z3 > 93) {
                                this.n = 0;
                                this.eof = true;
                                this.ascii = null;
                                this.b = null;
                                throw new IOException("Invalid data in Ascii85 stream");
                            }
                            t = (t * 85) + z3;
                        }
                        for (int k3 = 3; k3 >= 0; k3--) {
                            this.b[k3] = (byte) (t & 255);
                            t >>>= 8;
                        }
                    }
                }
            }
        }
        byte[] bArr4 = this.b;
        int i = this.index;
        this.index = i + 1;
        return bArr4[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] data, int offset, int len) throws IOException {
        if (this.eof && this.index >= this.n) {
            return -1;
        }
        for (int i = 0; i < len; i++) {
            if (this.index < this.n) {
                byte[] bArr = this.b;
                int i2 = this.index;
                this.index = i2 + 1;
                data[i + offset] = bArr[i2];
            } else {
                int t = read();
                if (t == -1) {
                    return i;
                }
                data[i + offset] = (byte) t;
            }
        }
        return len;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.ascii = null;
        this.eof = true;
        this.b = null;
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long nValue) {
        return 0L;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int readlimit) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        throw new IOException("Reset is not supported");
    }
}
