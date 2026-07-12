package de.erichseifert.vectorgraphics2d.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/Base64EncodeStream.class */
public class Base64EncodeStream extends FilterOutputStream {
    private static final int[] a = {262144, 4096, 64, 1};
    private static final char[] b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private boolean c;
    private final byte[] d;
    private int e;
    private final byte[] f;

    public Base64EncodeStream(OutputStream outputStream) {
        super(outputStream);
        this.d = new byte[3];
        this.f = new byte[4];
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.c) {
            return;
        }
        if (this.e == 3) {
            a();
            this.e = 0;
        }
        byte[] bArr = this.d;
        int i2 = this.e;
        this.e = i2 + 1;
        bArr[i2] = (byte) i;
    }

    private void a() throws IOException {
        if (this.e == 0) {
            return;
        }
        byte[] bArr = this.d;
        int i = this.e;
        long j = 0;
        int i2 = (3 - i) << 3;
        for (int i3 = i - 1; i3 >= 0; i3--) {
            j |= (bArr[i3] & 255) << i2;
            i2 += 8;
        }
        long j2 = j & 4294967295L;
        int i4 = 3 - this.e;
        Arrays.fill(this.f, (byte) 61);
        int i5 = 4 - i4;
        for (int i6 = 0; i6 < i5; i6++) {
            this.f[i6] = (byte) b[(int) ((j2 / a[i6]) % 64)];
        }
        this.out.write(this.f, 0, 4);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.c) {
            return;
        }
        a();
        super.close();
        this.c = true;
    }
}
