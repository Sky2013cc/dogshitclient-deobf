package sun.tools.java;

import java.io.CharConversionException;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/* loaded from: target.jar:sun/tools/java/ScannerInputReader.class */
public class ScannerInputReader extends FilterReader implements Constants {
    Environment env;
    long pos;
    private long chpos;
    private int pushBack;
    private static final int BUFFERLEN = 10240;
    private final char[] buffer;
    private int currentIndex;
    private int numChars;

    public ScannerInputReader(Environment environment, InputStream inputStream) throws UnsupportedEncodingException {
        super(environment.getCharacterEncoding() != null ? new InputStreamReader(inputStream, environment.getCharacterEncoding()) : new InputStreamReader(inputStream));
        this.pushBack = -1;
        this.buffer = new char[BUFFERLEN];
        this.currentIndex = 0;
        this.numChars = 0;
        this.env = environment;
        this.chpos = Scanner.LINEINC;
    }

    private int getNextChar() throws IOException {
        if (this.currentIndex >= this.numChars) {
            this.numChars = this.in.read(this.buffer);
            if (this.numChars == -1) {
                return -1;
            }
            this.currentIndex = 0;
        }
        char[] cArr = this.buffer;
        int i = this.currentIndex;
        this.currentIndex = i + 1;
        return cArr[i];
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read(char[] cArr, int i, int i2) {
        throw new CompilerError("ScannerInputReader is not a fully implemented reader.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [int] */
    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        int i;
        int i2;
        this.pos = this.chpos;
        this.chpos++;
        char c = this.pushBack;
        if (c == 65535) {
            try {
                if (this.currentIndex >= this.numChars) {
                    this.numChars = this.in.read(this.buffer);
                    if (this.numChars == -1) {
                        c = 65535;
                    } else {
                        this.currentIndex = 0;
                    }
                }
                char[] cArr = this.buffer;
                int i3 = this.currentIndex;
                this.currentIndex = i3 + 1;
                c = cArr[i3];
            } catch (CharConversionException e) {
                this.env.error(this.pos, "invalid.encoding.char");
                return -1;
            }
        } else {
            this.pushBack = -1;
        }
        switch (c) {
            case 65534:
                return 92;
            case '\n':
                this.chpos += Scanner.LINEINC;
                return 10;
            case '\r':
                int nextChar = getNextChar();
                if (nextChar != 10) {
                    this.pushBack = nextChar;
                } else {
                    this.chpos++;
                }
                this.chpos += Scanner.LINEINC;
                return 10;
            case '\\':
                int nextChar2 = getNextChar();
                if (nextChar2 != 117) {
                    this.pushBack = nextChar2 == 92 ? -2 : nextChar2;
                    return 92;
                }
                this.chpos++;
                while (true) {
                    int nextChar3 = getNextChar();
                    int i4 = nextChar3;
                    if (nextChar3 == 117) {
                        this.chpos++;
                    } else {
                        int i5 = 0;
                        int i6 = 0;
                        while (i6 < 4) {
                            switch (i4) {
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                    i = (i5 << 4) + i4;
                                    i2 = 48;
                                    break;
                                case 58:
                                case 59:
                                case 60:
                                case 61:
                                case 62:
                                case 63:
                                case 64:
                                case 71:
                                case 72:
                                case 73:
                                case 74:
                                case 75:
                                case 76:
                                case 77:
                                case 78:
                                case 79:
                                case 80:
                                case 81:
                                case 82:
                                case 83:
                                case 84:
                                case 85:
                                case 86:
                                case 87:
                                case 88:
                                case 89:
                                case 90:
                                case 91:
                                case 92:
                                case 93:
                                case 94:
                                case 95:
                                case 96:
                                default:
                                    this.env.error(this.pos, "invalid.escape.char");
                                    this.pushBack = i4;
                                    return i5;
                                case 65:
                                case 66:
                                case 67:
                                case 68:
                                case 69:
                                case 70:
                                    i = (i5 << 4) + 10 + i4;
                                    i2 = 65;
                                    break;
                                case 97:
                                case 98:
                                case 99:
                                case 100:
                                case 101:
                                case 102:
                                    i = (i5 << 4) + 10 + i4;
                                    i2 = 97;
                                    break;
                            }
                            i5 = i - i2;
                            i6++;
                            this.chpos++;
                            i4 = getNextChar();
                        }
                        this.pushBack = i4;
                        switch (i5) {
                            case 10:
                                this.chpos += Scanner.LINEINC;
                                return 10;
                            case 13:
                                int nextChar4 = getNextChar();
                                if (nextChar4 != 10) {
                                    this.pushBack = nextChar4;
                                } else {
                                    this.chpos++;
                                }
                                this.chpos += Scanner.LINEINC;
                                return 10;
                            default:
                                return i5;
                        }
                    }
                }
            default:
                return c;
        }
    }
}
