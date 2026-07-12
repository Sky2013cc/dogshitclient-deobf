package jdk.nashorn.internal.runtime.regexp.joni;

import jdk.nashorn.internal.runtime.regexp.joni.constants.TokenType;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/regexp/joni/Token.class */
final class Token {
    TokenType type;
    boolean escaped;
    int backP;
    private int INT1;
    private int INT2;
    private int INT3;
    private int INT4;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getC() {
        return this.INT1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setC(int c) {
        this.INT1 = c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCode() {
        return this.INT1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCode(int code) {
        this.INT1 = code;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getAnchor() {
        return this.INT1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAnchor(int anchor) {
        this.INT1 = anchor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRepeatLower() {
        return this.INT1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRepeatLower(int lower) {
        this.INT1 = lower;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRepeatUpper() {
        return this.INT2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRepeatUpper(int upper) {
        this.INT2 = upper;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getRepeatGreedy() {
        return this.INT3 != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRepeatGreedy(boolean greedy) {
        this.INT3 = greedy ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getRepeatPossessive() {
        return this.INT4 != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRepeatPossessive(boolean possessive) {
        this.INT4 = possessive ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBackrefRef() {
        return this.INT2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBackrefRef(int ref1) {
        this.INT2 = ref1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPropCType() {
        return this.INT1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPropCType(int ctype) {
        this.INT1 = ctype;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getPropNot() {
        return this.INT2 != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPropNot(boolean not) {
        this.INT2 = not ? 1 : 0;
    }
}
