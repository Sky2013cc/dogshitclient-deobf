package com.sun.xml.internal.rngom.binary;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: target.jar:com/sun/xml/internal/rngom/binary/PatternInterner.class */
public final class PatternInterner {
    private static final int INIT_SIZE = 256;
    private static final float LOAD_FACTOR = 0.3f;
    private Pattern[] table;
    private int used;
    private int usedLimit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PatternInterner() {
        this.table = null;
        this.used = 0;
        this.usedLimit = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PatternInterner(PatternInterner parent) {
        this.table = parent.table;
        if (this.table != null) {
            this.table = (Pattern[]) this.table.clone();
        }
        this.used = parent.used;
        this.usedLimit = parent.usedLimit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Pattern intern(Pattern p) {
        int h;
        int j;
        if (this.table == null) {
            this.table = new Pattern[256];
            this.usedLimit = 76;
            h = firstIndex(p);
        } else {
            int firstIndex = firstIndex(p);
            while (true) {
                h = firstIndex;
                if (this.table[h] == null) {
                    break;
                }
                if (!p.samePattern(this.table[h])) {
                    firstIndex = nextIndex(h);
                } else {
                    return this.table[h];
                }
            }
        }
        if (this.used >= this.usedLimit) {
            Pattern[] oldTable = this.table;
            this.table = new Pattern[this.table.length << 1];
            int i = oldTable.length;
            while (i > 0) {
                i--;
                if (oldTable[i] != null) {
                    int firstIndex2 = firstIndex(oldTable[i]);
                    while (true) {
                        j = firstIndex2;
                        if (this.table[j] == null) {
                            break;
                        }
                        firstIndex2 = nextIndex(j);
                    }
                    this.table[j] = oldTable[i];
                }
            }
            int firstIndex3 = firstIndex(p);
            while (true) {
                h = firstIndex3;
                if (this.table[h] == null) {
                    break;
                }
                firstIndex3 = nextIndex(h);
            }
            this.usedLimit = (int) (this.table.length * LOAD_FACTOR);
        }
        this.used++;
        this.table[h] = p;
        return p;
    }

    private int firstIndex(Pattern p) {
        return p.patternHashCode() & (this.table.length - 1);
    }

    private int nextIndex(int i) {
        return i == 0 ? this.table.length - 1 : i - 1;
    }
}
