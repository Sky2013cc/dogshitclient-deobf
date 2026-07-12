package jdk.nashorn.internal.runtime.regexp.joni;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:jdk/nashorn/internal/runtime/regexp/joni/StackEntry.class */
public final class StackEntry {
    int type;
    private int E1;
    private int E2;
    private int E3;
    private int E4;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatePCode(int pcode) {
        this.E1 = pcode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStatePCode() {
        return this.E1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatePStr(int pstr) {
        this.E2 = pstr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStatePStr() {
        return this.E2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatePStrPrev(int pstrPrev) {
        this.E3 = pstrPrev;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStatePStrPrev() {
        return this.E3;
    }

    void setStateCheck(int check) {
        this.E4 = check;
    }

    int getStateCheck() {
        return this.E4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRepeatCount(int count) {
        this.E1 = count;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRepeatCount() {
        return this.E1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void decreaseRepeatCount() {
        this.E1--;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void increaseRepeatCount() {
        this.E1++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRepeatPCode(int pcode) {
        this.E2 = pcode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRepeatPCode() {
        return this.E2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRepeatNum(int num) {
        this.E3 = num;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRepeatNum() {
        return this.E3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSi(int si) {
        this.E1 = si;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSi() {
        return this.E1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMemNum(int num) {
        this.E1 = num;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMemNum() {
        return this.E1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMemPstr(int pstr) {
        this.E2 = pstr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMemPStr() {
        return this.E2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMemStart(int start) {
        this.E3 = start;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMemStart() {
        return this.E3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMemEnd(int end) {
        this.E4 = end;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMemEnd() {
        return this.E4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNullCheckNum(int num) {
        this.E1 = num;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNullCheckNum() {
        return this.E1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNullCheckPStr(int pstr) {
        this.E2 = pstr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNullCheckPStr() {
        return this.E2;
    }

    void setCallFrameRetAddr(int addr) {
        this.E1 = addr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCallFrameRetAddr() {
        return this.E1;
    }

    void setCallFrameNum(int num) {
        this.E2 = num;
    }

    int getCallFrameNum() {
        return this.E2;
    }

    void setCallFramePStr(int pstr) {
        this.E3 = pstr;
    }

    int getCallFramePStr() {
        return this.E3;
    }
}
