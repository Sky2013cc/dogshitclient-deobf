package sun.tools.native2ascii;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

/* loaded from: target.jar:sun/tools/native2ascii/A2NFilter.class */
class A2NFilter extends FilterReader {
    private char[] trailChars;

    public A2NFilter(Reader reader) {
        super(reader);
        this.trailChars = null;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3 = 0;
        int i4 = 0;
        char[] cArr2 = new char[i2];
        boolean z = false;
        if (this.trailChars != null) {
            for (int i5 = 0; i5 < this.trailChars.length; i5++) {
                cArr2[i5] = this.trailChars[i5];
            }
            i3 = this.trailChars.length;
            this.trailChars = null;
        }
        int read = this.in.read(cArr2, i3, i2 - i3);
        if (read < 0) {
            z = true;
            if (i3 == 0) {
                return -1;
            }
        } else {
            i3 += read;
        }
        int i6 = 0;
        while (true) {
            if (i6 >= i3) {
                break;
            }
            int i7 = i6;
            i6++;
            char c = cArr2[i7];
            if (c != '\\' || (z && i3 <= 5)) {
                int i8 = i4;
                i4++;
                cArr[i8] = c;
            } else {
                int i9 = i3 - i6;
                if (i9 < 5) {
                    this.trailChars = new char[1 + i9];
                    this.trailChars[0] = c;
                    for (int i10 = 0; i10 < i9; i10++) {
                        this.trailChars[1 + i10] = cArr2[i6 + i10];
                    }
                } else {
                    i6++;
                    char c2 = cArr2[i6];
                    if (c2 != 'u') {
                        int i11 = i4;
                        int i12 = i4 + 1;
                        cArr[i11] = '\\';
                        i4 = i12 + 1;
                        cArr[i12] = c2;
                    } else {
                        char c3 = 0;
                        boolean z2 = true;
                        try {
                            c3 = (char) Integer.parseInt(new String(cArr2, i6, 4), 16);
                        } catch (NumberFormatException e) {
                            z2 = false;
                        }
                        if (z2 && Main.canConvert(c3)) {
                            int i13 = i4;
                            i4++;
                            cArr[i13] = c3;
                            i6 += 4;
                        } else {
                            int i14 = i4;
                            int i15 = i4 + 1;
                            cArr[i14] = '\\';
                            i4 = i15 + 1;
                            cArr[i15] = 'u';
                        }
                    }
                }
            }
        }
        return i4;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        char[] cArr = new char[1];
        if (read(cArr, 0, 1) == -1) {
            return -1;
        }
        return cArr[0];
    }
}
