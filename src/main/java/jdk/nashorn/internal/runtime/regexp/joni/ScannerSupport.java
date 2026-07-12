package jdk.nashorn.internal.runtime.regexp.joni;

import jdk.nashorn.internal.runtime.regexp.joni.encoding.IntHolder;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/regexp/joni/ScannerSupport.class */
abstract class ScannerSupport extends IntHolder implements ErrorMessages {
    protected final char[] chars;
    protected int p;
    protected int stop;
    private int lastFetched;
    protected int c;
    private final int begin;
    private final int end;
    protected int _p;
    private static final int INT_SIGN_BIT = Integer.MIN_VALUE;

    /* JADX INFO: Access modifiers changed from: protected */
    public ScannerSupport(char[] chars, int p, int end) {
        this.chars = chars;
        this.begin = p;
        this.end = end;
        reset();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getBegin() {
        return this.begin;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getEnd() {
        return this.end;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int scanUnsignedNumber() {
        int onum;
        int last = this.c;
        int num = 0;
        do {
            if (left()) {
                fetch();
                if (Character.isDigit(this.c)) {
                    onum = num;
                    num = (num * 10) + EncodingHelper.digitVal(this.c);
                } else {
                    unfetch();
                }
            }
            this.c = last;
            return num;
        } while (((onum ^ num) & Integer.MIN_VALUE) == 0);
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int scanUnsignedHexadecimalNumber(int maxLength) {
        int onum;
        int last = this.c;
        int num = 0;
        int ml = maxLength;
        do {
            if (left()) {
                int i = ml;
                ml--;
                if (i != 0) {
                    fetch();
                    if (EncodingHelper.isXDigit(this.c)) {
                        onum = num;
                        int val = EncodingHelper.xdigitVal(this.c);
                        num = (num << 4) + val;
                    } else {
                        unfetch();
                    }
                }
            }
            this.c = last;
            return num;
        } while (((onum ^ num) & Integer.MIN_VALUE) == 0);
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int scanUnsignedOctalNumber(int maxLength) {
        int last = this.c;
        int num = 0;
        int ml = maxLength;
        while (left()) {
            int i = ml;
            ml--;
            if (i == 0) {
                break;
            }
            fetch();
            if (Character.isDigit(this.c) && this.c < 56) {
                int onum = num;
                int val = EncodingHelper.odigitVal(this.c);
                num = (num << 3) + val;
                if (((onum ^ num) & Integer.MIN_VALUE) != 0) {
                    return -1;
                }
            } else {
                unfetch();
                break;
            }
        }
        this.c = last;
        return num;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void reset() {
        this.p = this.begin;
        this.stop = this.end;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void mark() {
        this._p = this.p;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void restore() {
        this.p = this._p;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void inc() {
        this.lastFetched = this.p;
        this.p++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void fetch() {
        this.lastFetched = this.p;
        char[] cArr = this.chars;
        int i = this.p;
        this.p = i + 1;
        this.c = cArr[i];
    }

    protected int fetchTo() {
        this.lastFetched = this.p;
        char[] cArr = this.chars;
        int i = this.p;
        this.p = i + 1;
        return cArr[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void unfetch() {
        this.p = this.lastFetched;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int peek() {
        if (this.p < this.stop) {
            return this.chars[this.p];
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean peekIs(int ch) {
        return peek() == ch;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean left() {
        return this.p < this.stop;
    }
}
