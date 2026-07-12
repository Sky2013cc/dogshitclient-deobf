package com.sun.xml.internal.rngom.parse.compact;

import com.sun.xml.internal.rngom.ast.builder.BuildException;
import com.sun.xml.internal.rngom.util.Utf16;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/compact/UCode_UCodeESC_CharStream.class */
public final class UCode_UCodeESC_CharStream {
    public static final boolean staticFlag = false;
    public int bufpos;
    int bufsize;
    int available;
    int tokenBegin;
    private int[] bufline;
    private int[] bufcolumn;
    private int column;
    private int line;
    private Reader inputStream;
    private boolean closed;
    private boolean prevCharIsLF;
    private char[] nextCharBuf;
    private char[] buffer;
    private int maxNextCharInd;
    private int nextCharInd;
    private int inBuf;
    private static final char NEWLINE_MARKER = 0;
    private static final char BOM = 65279;

    static final int hexval(char c) {
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
                return -1;
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

    private final void ExpandBuff(boolean wrapAround) {
        char[] newbuffer = new char[this.bufsize + 2048];
        int[] newbufline = new int[this.bufsize + 2048];
        int[] newbufcolumn = new int[this.bufsize + 2048];
        if (wrapAround) {
            System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
            System.arraycopy(this.buffer, 0, newbuffer, this.bufsize - this.tokenBegin, this.bufpos);
            this.buffer = newbuffer;
            System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
            System.arraycopy(this.bufline, 0, newbufline, this.bufsize - this.tokenBegin, this.bufpos);
            this.bufline = newbufline;
            System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
            System.arraycopy(this.bufcolumn, 0, newbufcolumn, this.bufsize - this.tokenBegin, this.bufpos);
            this.bufcolumn = newbufcolumn;
            this.bufpos += this.bufsize - this.tokenBegin;
        } else {
            System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize - this.tokenBegin);
            this.buffer = newbuffer;
            System.arraycopy(this.bufline, this.tokenBegin, newbufline, 0, this.bufsize - this.tokenBegin);
            this.bufline = newbufline;
            System.arraycopy(this.bufcolumn, this.tokenBegin, newbufcolumn, 0, this.bufsize - this.tokenBegin);
            this.bufcolumn = newbufcolumn;
            this.bufpos -= this.tokenBegin;
        }
        int i = this.bufsize + 2048;
        this.bufsize = i;
        this.available = i;
        this.tokenBegin = 0;
    }

    private final void FillBuff() throws EOFException {
        if (this.maxNextCharInd == 4096) {
            this.nextCharInd = 0;
            this.maxNextCharInd = 0;
        }
        if (this.closed) {
            throw new EOFException();
        }
        try {
            int i = this.inputStream.read(this.nextCharBuf, this.maxNextCharInd, 4096 - this.maxNextCharInd);
            if (i == -1) {
                this.closed = true;
                this.inputStream.close();
                throw new EOFException();
            }
            this.maxNextCharInd += i;
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }

    private final char ReadChar() throws EOFException {
        int i = this.nextCharInd + 1;
        this.nextCharInd = i;
        if (i >= this.maxNextCharInd) {
            FillBuff();
        }
        return this.nextCharBuf[this.nextCharInd];
    }

    private final char PeekChar() throws EOFException {
        char c = ReadChar();
        this.nextCharInd--;
        return c;
    }

    public final char BeginToken() throws EOFException {
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
        }
        switch (c) {
            case 0:
                this.prevCharIsLF = true;
                break;
            case '\t':
                this.column--;
                this.column += 8 - (this.column & 7);
                break;
        }
        this.bufline[this.bufpos] = this.line;
        this.bufcolumn[this.bufpos] = this.column;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x003e. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:73:0x02b8. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x018c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final char readChar() throws EOFException {
        int i;
        int i2;
        if (this.inBuf > 0) {
            this.inBuf--;
            char[] cArr = this.buffer;
            if (this.bufpos == this.bufsize - 1) {
                i2 = 0;
                this.bufpos = 0;
            } else {
                int i3 = this.bufpos + 1;
                i2 = i3;
                this.bufpos = i3;
            }
            return cArr[i2];
        }
        try {
            char c = ReadChar();
            switch (c) {
                case '\t':
                    i = this.bufpos + 1;
                    this.bufpos = i;
                    if (i == this.available) {
                        AdjustBuffSize();
                    }
                    this.buffer[this.bufpos] = c;
                    UpdateLineColumn(c);
                    if (c == '\\') {
                        try {
                            if (PeekChar() == 'x') {
                                int xCnt = 1;
                                while (true) {
                                    ReadChar();
                                    int i4 = this.bufpos + 1;
                                    this.bufpos = i4;
                                    if (i4 == this.available) {
                                        AdjustBuffSize();
                                    }
                                    this.buffer[this.bufpos] = 'x';
                                    UpdateLineColumn('x');
                                    try {
                                        char c2 = PeekChar();
                                        if (c2 == '{') {
                                            ReadChar();
                                            this.column++;
                                            this.bufpos -= xCnt;
                                            if (this.bufpos < 0) {
                                                this.bufpos += this.bufsize;
                                            }
                                            try {
                                                int scalarValue = hexval(ReadChar());
                                                this.column++;
                                                if (scalarValue < 0) {
                                                    throw new EscapeSyntaxException("illegal_hex_digit", this.line, this.column);
                                                }
                                                do {
                                                    char c3 = ReadChar();
                                                    if (c3 != '}') {
                                                        this.column++;
                                                        int n = hexval(c3);
                                                        if (n < 0) {
                                                            throw new EscapeSyntaxException("illegal_hex_digit", this.line, this.column);
                                                        }
                                                        scalarValue = (scalarValue << 4) | n;
                                                    } else {
                                                        this.column++;
                                                        if (scalarValue <= 65535) {
                                                            char c4 = (char) scalarValue;
                                                            switch (c4) {
                                                                case '\t':
                                                                case '\n':
                                                                case '\r':
                                                                    this.buffer[this.bufpos] = c4;
                                                                    return c4;
                                                                default:
                                                                    if (c4 >= ' ' && !Utf16.isSurrogate(c4)) {
                                                                        this.buffer[this.bufpos] = c4;
                                                                        return c4;
                                                                    }
                                                                    break;
                                                                case 65534:
                                                                case 65535:
                                                                    throw new EscapeSyntaxException("illegal_char_code_ref", this.line, this.column);
                                                            }
                                                        } else {
                                                            char c5 = Utf16.surrogate1(scalarValue);
                                                            this.buffer[this.bufpos] = c5;
                                                            int bufpos1 = this.bufpos;
                                                            int i5 = this.bufpos + 1;
                                                            this.bufpos = i5;
                                                            if (i5 == this.bufsize) {
                                                                this.bufpos = 0;
                                                            }
                                                            this.buffer[this.bufpos] = Utf16.surrogate2(scalarValue);
                                                            this.bufline[this.bufpos] = this.bufline[bufpos1];
                                                            this.bufcolumn[this.bufpos] = this.bufcolumn[bufpos1];
                                                            backup(1);
                                                            return c5;
                                                        }
                                                    }
                                                } while (scalarValue < 1114112);
                                                throw new EscapeSyntaxException("char_code_too_big", this.line, this.column);
                                            } catch (EOFException e) {
                                                throw new EscapeSyntaxException("incomplete_escape", this.line, this.column);
                                            }
                                        }
                                        if (c2 != 'x') {
                                            backup(xCnt);
                                            return '\\';
                                        }
                                        xCnt++;
                                    } catch (EOFException e2) {
                                        backup(xCnt);
                                        return '\\';
                                    }
                                }
                            }
                        } catch (EOFException e3) {
                            return c;
                        }
                    }
                    return c;
                case '\n':
                    c = 0;
                    i = this.bufpos + 1;
                    this.bufpos = i;
                    if (i == this.available) {
                    }
                    this.buffer[this.bufpos] = c;
                    UpdateLineColumn(c);
                    if (c == '\\') {
                    }
                    return c;
                case '\r':
                    c = 0;
                    try {
                        if (PeekChar() == '\n') {
                            ReadChar();
                        }
                    } catch (EOFException e4) {
                    }
                    i = this.bufpos + 1;
                    this.bufpos = i;
                    if (i == this.available) {
                    }
                    this.buffer[this.bufpos] = c;
                    UpdateLineColumn(c);
                    if (c == '\\') {
                    }
                    return c;
                default:
                    if (c >= ' ') {
                        if (Utf16.isSurrogate(c)) {
                            if (Utf16.isSurrogate2(c)) {
                                throw new EscapeSyntaxException("illegal_surrogate_pair", this.line, this.column + 1);
                            }
                            int i6 = this.bufpos + 1;
                            this.bufpos = i6;
                            if (i6 == this.available) {
                                AdjustBuffSize();
                            }
                            this.buffer[this.bufpos] = c;
                            try {
                                c = ReadChar();
                                if (!Utf16.isSurrogate2(c)) {
                                    throw new EscapeSyntaxException("illegal_surrogate_pair", this.line, this.column + 2);
                                }
                            } catch (EOFException e5) {
                                throw new EscapeSyntaxException("illegal_surrogate_pair", this.line, this.column + 1);
                            }
                        }
                        i = this.bufpos + 1;
                        this.bufpos = i;
                        if (i == this.available) {
                        }
                        this.buffer[this.bufpos] = c;
                        UpdateLineColumn(c);
                        if (c == '\\') {
                        }
                        return c;
                    }
                    break;
                case 65534:
                case 65535:
                    throw new EscapeSyntaxException("illegal_char_code", this.line, this.column + 1);
            }
        } catch (EOFException e6) {
            if (this.bufpos == -1) {
                int i7 = this.bufpos + 1;
                this.bufpos = i7;
                if (i7 == this.available) {
                    AdjustBuffSize();
                }
                this.bufline[this.bufpos] = this.line;
                this.bufcolumn[this.bufpos] = this.column;
            }
            throw e6;
        }
    }

    public final int getColumn() {
        return this.bufcolumn[this.bufpos];
    }

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

    public final void backup(int amount) {
        this.inBuf += amount;
        int i = this.bufpos - amount;
        this.bufpos = i;
        if (i < 0) {
            this.bufpos += this.bufsize;
        }
    }

    public UCode_UCodeESC_CharStream(Reader dstream, int startline, int startcolumn, int buffersize) {
        this.bufpos = -1;
        this.column = 0;
        this.line = 1;
        this.closed = false;
        this.prevCharIsLF = false;
        this.maxNextCharInd = 0;
        this.nextCharInd = -1;
        this.inBuf = 0;
        this.inputStream = dstream;
        this.line = startline;
        this.column = startcolumn - 1;
        this.bufsize = buffersize;
        this.available = buffersize;
        this.buffer = new char[buffersize];
        this.bufline = new int[buffersize];
        this.bufcolumn = new int[buffersize];
        this.nextCharBuf = new char[4096];
        skipBOM();
    }

    public UCode_UCodeESC_CharStream(Reader dstream, int startline, int startcolumn) {
        this(dstream, startline, startcolumn, 4096);
    }

    public void ReInit(Reader dstream, int startline, int startcolumn, int buffersize) {
        this.inputStream = dstream;
        this.closed = false;
        this.line = startline;
        this.column = startcolumn - 1;
        if (this.buffer == null || buffersize != this.buffer.length) {
            this.bufsize = buffersize;
            this.available = buffersize;
            this.buffer = new char[buffersize];
            this.bufline = new int[buffersize];
            this.bufcolumn = new int[buffersize];
            this.nextCharBuf = new char[4096];
        }
        this.prevCharIsLF = false;
        this.maxNextCharInd = 0;
        this.inBuf = 0;
        this.tokenBegin = 0;
        this.bufpos = -1;
        this.nextCharInd = -1;
        skipBOM();
    }

    public void ReInit(Reader dstream, int startline, int startcolumn) {
        ReInit(dstream, startline, startcolumn, 4096);
    }

    public UCode_UCodeESC_CharStream(InputStream dstream, int startline, int startcolumn, int buffersize) {
        this(new InputStreamReader(dstream), startline, startcolumn, 4096);
    }

    public UCode_UCodeESC_CharStream(InputStream dstream, int startline, int startcolumn) {
        this(dstream, startline, startcolumn, 4096);
    }

    public void ReInit(InputStream dstream, int startline, int startcolumn, int buffersize) {
        ReInit(new InputStreamReader(dstream), startline, startcolumn, 4096);
    }

    public void ReInit(InputStream dstream, int startline, int startcolumn) {
        ReInit(dstream, startline, startcolumn, 4096);
    }

    private void skipBOM() {
        try {
            if (PeekChar() == BOM) {
                ReadChar();
            }
        } catch (EOFException e) {
        }
    }

    public final String GetImage() {
        if (this.bufpos >= this.tokenBegin) {
            return new String(this.buffer, this.tokenBegin, (this.bufpos - this.tokenBegin) + 1);
        }
        return new String(this.buffer, this.tokenBegin, this.bufsize - this.tokenBegin) + new String(this.buffer, 0, this.bufpos + 1);
    }

    public final char[] GetSuffix(int len) {
        char[] ret = new char[len];
        if (this.bufpos + 1 >= len) {
            System.arraycopy(this.buffer, (this.bufpos - len) + 1, ret, 0, len);
        } else {
            System.arraycopy(this.buffer, this.bufsize - ((len - this.bufpos) - 1), ret, 0, (len - this.bufpos) - 1);
            System.arraycopy(this.buffer, 0, ret, (len - this.bufpos) - 1, this.bufpos + 1);
        }
        return ret;
    }

    public void Done() {
        this.nextCharBuf = null;
        this.buffer = null;
        this.bufline = null;
        this.bufcolumn = null;
    }

    public void adjustBeginLineColumn(int newLine, int newCol) {
        int len;
        int start = this.tokenBegin;
        if (this.bufpos >= this.tokenBegin) {
            len = (this.bufpos - this.tokenBegin) + this.inBuf + 1;
        } else {
            len = (this.bufsize - this.tokenBegin) + this.bufpos + 1 + this.inBuf;
        }
        int i = 0;
        int j = 0;
        int columnDiff = 0;
        while (i < len) {
            int[] iArr = this.bufline;
            int i2 = start % this.bufsize;
            j = i2;
            int i3 = iArr[i2];
            int[] iArr2 = this.bufline;
            start++;
            int k = start % this.bufsize;
            if (i3 != iArr2[k]) {
                break;
            }
            this.bufline[j] = newLine;
            int nextColDiff = (columnDiff + this.bufcolumn[k]) - this.bufcolumn[j];
            this.bufcolumn[j] = newCol + columnDiff;
            columnDiff = nextColDiff;
            i++;
        }
        if (i < len) {
            int newLine2 = newLine + 1;
            this.bufline[j] = newLine;
            this.bufcolumn[j] = newCol + columnDiff;
            while (true) {
                int i4 = i;
                i++;
                if (i4 >= len) {
                    break;
                }
                int[] iArr3 = this.bufline;
                int i5 = start % this.bufsize;
                j = i5;
                start++;
                if (iArr3[i5] != this.bufline[start % this.bufsize]) {
                    int i6 = newLine2;
                    newLine2++;
                    this.bufline[j] = i6;
                } else {
                    this.bufline[j] = newLine2;
                }
            }
        }
        this.line = this.bufline[j];
        this.column = this.bufcolumn[j];
    }
}
