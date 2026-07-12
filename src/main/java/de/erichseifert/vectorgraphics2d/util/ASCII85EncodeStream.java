package de.erichseifert.vectorgraphics2d.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/util/ASCII85EncodeStream.class */
public class ASCII85EncodeStream extends FilterOutputStream {
    private static final int[] a = {52200625, 614125, 7225, 85, 1};
    private static final char[] b = "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstu".toCharArray();
    private boolean c;
    private final byte[] d;
    private int e;
    private final byte[] f;
    private boolean g;
    private final byte[] h;
    private final byte[] i;

    public ASCII85EncodeStream(OutputStream outputStream, String str, String str2) {
        super(outputStream);
        this.f = str != null ? str.getBytes() : "".getBytes();
        this.h = str2 != null ? str2.getBytes() : "".getBytes();
        this.d = new byte[4];
        this.i = new byte[5];
    }

    public ASCII85EncodeStream(OutputStream outputStream) {
        this(outputStream, "", "~>");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.c) {
            return;
        }
        if (!this.g) {
            this.out.write(this.f);
            this.g = true;
        }
        if (this.e == 4) {
            a();
            this.e = 0;
        }
        byte[] bArr = this.d;
        int i2 = this.e;
        this.e = i2 + 1;
        bArr[i2] = (byte) i;
    }

    private void a() throws IOException {
        int i;
        if (this.e == 0) {
            return;
        }
        byte[] bArr = this.d;
        int i2 = this.e;
        long j = 0;
        for (int i3 = 0; i3 < 4 && i3 < i2; i3++) {
            j |= (bArr[i3] & 255) << ((3 - i3) << 3);
        }
        long j2 = j & 4294967295L;
        int i4 = 4 - this.e;
        Arrays.fill(this.i, (byte) 0);
        if (j2 == 0 && i4 == 0) {
            this.i[0] = 122;
            i = 1;
        } else {
            int i5 = 5 - i4;
            for (int i6 = 0; i6 < i5; i6++) {
                this.i[i6] = (byte) b[(int) ((j2 / a[i6]) % 85)];
            }
            i = i5;
        }
        this.out.write(this.i, 0, i);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.c) {
            return;
        }
        a();
        this.out.write(this.h);
        super.close();
        this.c = true;
    }
}
