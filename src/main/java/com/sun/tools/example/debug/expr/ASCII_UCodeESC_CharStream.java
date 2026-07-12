package com.sun.tools.example.debug.expr;

import java.io.IOException;
import java.io.InputStream;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/example/debug/expr/ASCII_UCodeESC_CharStream.class */
public final class ASCII_UCodeESC_CharStream {
    public static final boolean staticFlag = false;
    public int bufpos;
    int bufsize;
    int available;
    int tokenBegin;
    private int[] bufline;
    private int[] bufcolumn;
    private int column;
    private int line;
    private InputStream inputStream;
    private boolean prevCharIsCR;
    private boolean prevCharIsLF;
    private byte[] nextCharBuf;
    private char[] buffer;
    private int maxNextCharInd;
    private int nextCharInd;
    private int inBuf;

    static final int hexval(char c) throws IOException {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            default:
                throw new IOException();
            case 'A':
            case 'a':
                return 10;
            case 'B':
            case 'b':
                return 11;
            case 'C':
            case 'c':
                return 12;
            case 'D':
            case 'd':
                return 13;
            case 'E':
            case 'e':
                return 14;
            case 'F':
            case 'f':
                return 15;
        }
    }

    private final void ExpandBuff(boolean z) {
        char[] cArr = new char[this.bufsize + 2048];
        int[] iArr = new int[this.bufsize + 2048];
        int[] iArr2 = new int[this.bufsize + 2048];
        try {
            if (z) {
                System.arraycopy(this.buffer, this.tokenBegin, cArr, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.buffer, 0, cArr, this.bufsize - this.tokenBegin, this.bufpos);
                this.buffer = cArr;
                System.arraycopy(this.bufline, this.tokenBegin, iArr, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.bufline, 0, iArr, this.bufsize - this.tokenBegin, this.bufpos);
                this.bufline = iArr;
                System.arraycopy(this.bufcolumn, this.tokenBegin, iArr2, 0, this.bufsize - this.tokenBegin);
                System.arraycopy(this.bufcolumn, 0, iArr2, this.bufsize - this.tokenBegin, this.bufpos);
                this.bufcolumn = iArr2;
                this.bufpos += this.bufsize - this.tokenBegin;
            } else {
                System.arraycopy(this.buffer, this.tokenBegin, cArr, 0, this.bufsize - this.tokenBegin);
                this.buffer = cArr;
                System.arraycopy(this.bufline, this.tokenBegin, iArr, 0, this.bufsize - this.tokenBegin);
                this.bufline = iArr;
                System.arraycopy(this.bufcolumn, this.tokenBegin, iArr2, 0, this.bufsize - this.tokenBegin);
                this.bufcolumn = iArr2;
                this.bufpos -= this.tokenBegin;
            }
            int i = this.bufsize + 2048;
            this.bufsize = i;
            this.available = i;
            this.tokenBegin = 0;
        } catch (Throwable th) {
            throw new Error(th.getMessage());
        }
    }

    private final void FillBuff() throws IOException {
        if (this.maxNextCharInd == 4096) {
            this.nextCharInd = 0;
            this.maxNextCharInd = 0;
        }
        try {
            int read = this.inputStream.read(this.nextCharBuf, this.maxNextCharInd, 4096 - this.maxNextCharInd);
            if (read == -1) {
                this.inputStream.close();
                throw new IOException();
            }
            this.maxNextCharInd += read;
        } catch (IOException e) {
            if (this.bufpos != 0) {
                this.bufpos--;
                backup(0);
            } else {
                this.bufline[this.bufpos] = this.line;
                this.bufcolumn[this.bufpos] = this.column;
            }
            throw e;
        }
    }

    private final byte ReadByte() throws IOException {
        int i = this.nextCharInd + 1;
        this.nextCharInd = i;
        if (i >= this.maxNextCharInd) {
            FillBuff();
        }
        return this.nextCharBuf[this.nextCharInd];
    }

    public final char BeginToken() throws IOException {
        int i;
        if (this.inBuf > 0) {
            this.inBuf--;
            char[] cArr = this.buffer;
            if (this.bufpos == this.bufsize - 1) {
                i = 0;
                this.bufpos = 0;
            } else {
                int i2 = this.bufpos + 1;
                i = i2;
                this.bufpos = i2;
            }
            int i3 = i;
            this.tokenBegin = i3;
            return cArr[i3];
        }
        this.tokenBegin = 0;
        this.bufpos = -1;
        return readChar();
    }

    private final void AdjustBuffSize() {
        if (this.available == this.bufsize) {
            if (this.tokenBegin > 2048) {
                this.bufpos = 0;
                this.available = this.tokenBegin;
                return;
            } else {
                ExpandBuff(false);
                return;
            }
        }
        if (this.available > this.tokenBegin) {
            this.available = this.bufsize;
        } else if (this.tokenBegin - this.available < 2048) {
            ExpandBuff(true);
        } else {
            this.available = this.tokenBegin;
        }
    }

    private final void UpdateLineColumn(char c) {
        this.column++;
        if (this.prevCharIsLF) {
            this.prevCharIsLF = false;
            int i = this.line;
            this.column = 1;
            this.line = i + 1;
        } else if (this.prevCharIsCR) {
            this.prevCharIsCR = false;
            if (c == '\n') {
                this.prevCharIsLF = true;
            } else {
                int i2 = this.line;
                this.column = 1;
                this.line = i2 + 1;
            }
        }
        switch (c) {
            case '\t':
                this.column--;
                this.column += 8 - (this.column & 7);
                break;
            case '\n':
                this.prevCharIsLF = true;
                break;
            case '\r':
                this.prevCharIsCR = true;
                break;
        }
        this.bufline[this.bufpos] = this.line;
        this.bufcolumn[this.bufpos] = this.column;
    }

    public final char readChar() throws IOException {
        char ReadByte;
        char ReadByte2;
        int i;
        if (this.inBuf > 0) {
            this.inBuf--;
            char[] cArr = this.buffer;
            if (this.bufpos == this.bufsize - 1) {
                i = 0;
                this.bufpos = 0;
            } else {
                int i2 = this.bufpos + 1;
                i = i2;
                this.bufpos = i2;
            }
            return cArr[i];
        }
        int i3 = this.bufpos + 1;
        this.bufpos = i3;
        if (i3 == this.available) {
            AdjustBuffSize();
        }
        char[] cArr2 = this.buffer;
        int i4 = this.bufpos;
        char ReadByte3 = (char) (255 & ReadByte());
        cArr2[i4] = ReadByte3;
        if (ReadByte3 == '\\') {
            UpdateLineColumn(ReadByte3);
            int i5 = 1;
            while (true) {
                int i6 = this.bufpos + 1;
                this.bufpos = i6;
                if (i6 == this.available) {
                    AdjustBuffSize();
                }
                try {
                    char[] cArr3 = this.buffer;
                    int i7 = this.bufpos;
                    ReadByte = (char) (255 & ReadByte());
                    cArr3[i7] = ReadByte;
                    if (ReadByte != '\\') {
                        break;
                    }
                    UpdateLineColumn(ReadByte);
                    i5++;
                } catch (IOException e) {
                    if (i5 > 1) {
                        backup(i5);
                        return '\\';
                    }
                    return '\\';
                }
            }
            UpdateLineColumn(ReadByte);
            if (ReadByte == 'u' && (i5 & 1) == 1) {
                int i8 = this.bufpos - 1;
                this.bufpos = i8;
                if (i8 < 0) {
                    this.bufpos = this.bufsize - 1;
                }
                while (true) {
                    try {
                        ReadByte2 = (char) (255 & ReadByte());
                        if (ReadByte2 != 'u') {
                            break;
                        }
                        this.column++;
                    } catch (IOException e2) {
                        throw new Error("Invalid escape character at line " + this.line + " column " + this.column + Constants.NAME_SEPARATOR);
                    }
                }
                char[] cArr4 = this.buffer;
                int i9 = this.bufpos;
                char hexval = (char) ((hexval(ReadByte2) << 12) | (hexval((char) (255 & ReadByte())) << 8) | (hexval((char) (255 & ReadByte())) << 4) | hexval((char) (255 & ReadByte())));
                cArr4[i9] = hexval;
                this.column += 4;
                if (i5 == 1) {
                    return hexval;
                }
                backup(i5 - 1);
                return '\\';
            }
            backup(i5);
            return '\\';
        }
        UpdateLineColumn(ReadByte3);
        return ReadByte3;
    }

    @Deprecated
    public final int getColumn() {
        return this.bufcolumn[this.bufpos];
    }

    @Deprecated
    public final int getLine() {
        return this.bufline[this.bufpos];
    }

    public final int getEndColumn() {
        return this.bufcolumn[this.bufpos];
    }

    public final int getEndLine() {
        return this.bufline[this.bufpos];
    }

    public final int getBeginColumn() {
        return this.bufcolumn[this.tokenBegin];
    }

    public final int getBeginLine() {
        return this.bufline[this.tokenBegin];
    }

    public final void backup(int i) {
        this.inBuf += i;
        int i2 = this.bufpos - i;
        this.bufpos = i2;
        if (i2 < 0) {
            this.bufpos += this.bufsize;
        }
    }

    public ASCII_UCodeESC_CharStream(InputStream inputStream, int i, int i2, int i3) {
        this.bufpos = -1;
        this.column = 0;
        this.line = 1;
        this.prevCharIsCR = false;
        this.prevCharIsLF = false;
        this.maxNextCharInd = 0;
        this.nextCharInd = -1;
        this.inBuf = 0;
        this.inputStream = inputStream;
        this.line = i;
        this.column = i2 - 1;
        this.bufsize = i3;
        this.available = i3;
        this.buffer = new char[i3];
        this.bufline = new int[i3];
        this.bufcolumn = new int[i3];
        this.nextCharBuf = new byte[4096];
    }

    public ASCII_UCodeESC_CharStream(InputStream inputStream, int i, int i2) {
        this(inputStream, i, i2, 4096);
    }

    public void ReInit(InputStream inputStream, int i, int i2, int i3) {
        this.inputStream = inputStream;
        this.line = i;
        this.column = i2 - 1;
        if (this.buffer == null || i3 != this.buffer.length) {
            this.bufsize = i3;
            this.available = i3;
            this.buffer = new char[i3];
            this.bufline = new int[i3];
            this.bufcolumn = new int[i3];
            this.nextCharBuf = new byte[4096];
        }
        this.prevCharIsCR = false;
        this.prevCharIsLF = false;
        this.maxNextCharInd = 0;
        this.inBuf = 0;
        this.tokenBegin = 0;
        this.bufpos = -1;
        this.nextCharInd = -1;
    }

    public void ReInit(InputStream inputStream, int i, int i2) {
        ReInit(inputStream, i, i2, 4096);
    }

    public final String GetImage() {
        if (this.bufpos >= this.tokenBegin) {
            return new String(this.buffer, this.tokenBegin, (this.bufpos - this.tokenBegin) + 1);
        }
        return new String(this.buffer, this.tokenBegin, this.bufsize - this.tokenBegin) + new String(this.buffer, 0, this.bufpos + 1);
    }

    public final char[] GetSuffix(int i) {
        char[] cArr = new char[i];
        if (this.bufpos + 1 >= i) {
            System.arraycopy(this.buffer, (this.bufpos - i) + 1, cArr, 0, i);
        } else {
            System.arraycopy(this.buffer, this.bufsize - ((i - this.bufpos) - 1), cArr, 0, (i - this.bufpos) - 1);
            System.arraycopy(this.buffer, 0, cArr, (i - this.bufpos) - 1, this.bufpos + 1);
        }
        return cArr;
    }

    public void Done() {
        this.nextCharBuf = null;
        this.buffer = null;
        this.bufline = null;
        this.bufcolumn = null;
    }

    public void adjustBeginLineColumn(int i, int i2) {
        int i3;
        int i4 = this.tokenBegin;
        if (this.bufpos >= this.tokenBegin) {
            i3 = (this.bufpos - this.tokenBegin) + this.inBuf + 1;
        } else {
            i3 = (this.bufsize - this.tokenBegin) + this.bufpos + 1 + this.inBuf;
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i3) {
            int[] iArr = this.bufline;
            int i8 = i4 % this.bufsize;
            i6 = i8;
            int i9 = iArr[i8];
            int[] iArr2 = this.bufline;
            i4++;
            int i10 = i4 % this.bufsize;
            if (i9 != iArr2[i10]) {
                break;
            }
            this.bufline[i6] = i;
            int i11 = (i7 + this.bufcolumn[i10]) - this.bufcolumn[i6];
            this.bufcolumn[i6] = i2 + i7;
            i7 = i11;
            i5++;
        }
        if (i5 < i3) {
            int i12 = i + 1;
            this.bufline[i6] = i;
            this.bufcolumn[i6] = i2 + i7;
            while (true) {
                int i13 = i5;
                i5++;
                if (i13 >= i3) {
                    break;
                }
                int[] iArr3 = this.bufline;
                int i14 = i4 % this.bufsize;
                i6 = i14;
                i4++;
                if (iArr3[i14] != this.bufline[i4 % this.bufsize]) {
                    int i15 = i12;
                    i12++;
                    this.bufline[i6] = i15;
                } else {
                    this.bufline[i6] = i12;
                }
            }
        }
        this.line = this.bufline[i6];
        this.column = this.bufcolumn[i6];
    }
}
