package org.apache.fontbox.cmap;

/* loaded from: target.jar:org/apache/fontbox/cmap/CIDRange.class */
class CIDRange {
    private final int from;
    private int to;
    private final int unicode;
    private final int codeLength;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CIDRange(int from, int to, int unicode, int codeLength) {
        this.from = from;
        this.to = to;
        this.unicode = unicode;
        this.codeLength = codeLength;
    }

    public int getCodeLength() {
        return this.codeLength;
    }

    public int map(byte[] bytes) {
        int ch;
        if (bytes.length == this.codeLength && this.from <= (ch = CMap.toInt(bytes)) && ch <= this.to) {
            return this.unicode + (ch - this.from);
        }
        return -1;
    }

    public int map(int code, int length) {
        if (length == this.codeLength && this.from <= code && code <= this.to) {
            return this.unicode + (code - this.from);
        }
        return -1;
    }

    public int unmap(int code) {
        if (this.unicode <= code && code <= this.unicode + (this.to - this.from)) {
            return this.from + (code - this.unicode);
        }
        return -1;
    }

    public boolean extend(int newFrom, int newTo, int newCid, int length) {
        if (this.codeLength == length && newFrom == this.to + 1 && newCid == ((this.unicode + this.to) - this.from) + 1) {
            this.to = newTo;
            return true;
        }
        return false;
    }
}
