package sun.tools.tree;

import sun.tools.java.Constants;

/* loaded from: target.jar:sun/tools/tree/Vset.class */
public final class Vset implements Constants {
    long vset;
    long uset;
    long[] x;
    static final int VBITS = 64;
    static final long[] emptyX = new long[0];
    static final long[] fullX = new long[0];
    static final Vset DEAD_END = new Vset(-1, -1, fullX);

    public Vset() {
        this.x = emptyX;
    }

    private Vset(long j, long j2, long[] jArr) {
        this.vset = j;
        this.uset = j2;
        this.x = jArr;
    }

    public Vset copy() {
        if (this == DEAD_END) {
            return this;
        }
        Vset vset = new Vset(this.vset, this.uset, this.x);
        if (this.x.length > 0) {
            vset.growX(this.x.length);
        }
        return vset;
    }

    private void growX(int i) {
        long[] jArr = new long[i];
        long[] jArr2 = this.x;
        for (int i2 = 0; i2 < jArr2.length; i2++) {
            jArr[i2] = jArr2[i2];
        }
        this.x = jArr;
    }

    public boolean isDeadEnd() {
        return this == DEAD_END;
    }

    public boolean isReallyDeadEnd() {
        return this.x == fullX;
    }

    public Vset clearDeadEnd() {
        if (this == DEAD_END) {
            return new Vset(-1L, -1L, fullX);
        }
        return this;
    }

    public boolean testVar(int i) {
        long j = 1 << i;
        if (i < 64) {
            return (this.vset & j) != 0;
        }
        int i2 = ((i / 64) - 1) * 2;
        return i2 >= this.x.length ? this.x == fullX : (this.x[i2] & j) != 0;
    }

    public boolean testVarUnassigned(int i) {
        long j = 1 << i;
        if (i < 64) {
            return (this.uset & j) != 0;
        }
        int i2 = (((i / 64) - 1) * 2) + 1;
        return i2 >= this.x.length ? this.x == fullX : (this.x[i2] & j) != 0;
    }

    public Vset addVar(int i) {
        if (this.x == fullX) {
            return this;
        }
        long j = 1 << i;
        if (i >= 64) {
            int i2 = ((i / 64) - 1) * 2;
            if (i2 >= this.x.length) {
                growX(i2 + 1);
            }
            long[] jArr = this.x;
            jArr[i2] = jArr[i2] | j;
            if (i2 + 1 < this.x.length) {
                long[] jArr2 = this.x;
                int i3 = i2 + 1;
                jArr2[i3] = jArr2[i3] & (j ^ (-1));
            }
        } else {
            this.vset |= j;
            this.uset &= j ^ (-1);
        }
        return this;
    }

    public Vset addVarUnassigned(int i) {
        if (this.x == fullX) {
            return this;
        }
        long j = 1 << i;
        if (i >= 64) {
            int i2 = (((i / 64) - 1) * 2) + 1;
            if (i2 >= this.x.length) {
                growX(i2 + 1);
            }
            long[] jArr = this.x;
            jArr[i2] = jArr[i2] | j;
            long[] jArr2 = this.x;
            int i3 = i2 - 1;
            jArr2[i3] = jArr2[i3] & (j ^ (-1));
        } else {
            this.uset |= j;
            this.vset &= j ^ (-1);
        }
        return this;
    }

    public Vset clearVar(int i) {
        if (this.x == fullX) {
            return this;
        }
        long j = 1 << i;
        if (i >= 64) {
            int i2 = ((i / 64) - 1) * 2;
            if (i2 >= this.x.length) {
                return this;
            }
            long[] jArr = this.x;
            jArr[i2] = jArr[i2] & (j ^ (-1));
            if (i2 + 1 < this.x.length) {
                long[] jArr2 = this.x;
                int i3 = i2 + 1;
                jArr2[i3] = jArr2[i3] & (j ^ (-1));
            }
        } else {
            this.vset &= j ^ (-1);
            this.uset &= j ^ (-1);
        }
        return this;
    }

    public Vset join(Vset vset) {
        if (this == DEAD_END) {
            return vset.copy();
        }
        if (vset == DEAD_END) {
            return this;
        }
        if (this.x == fullX) {
            return vset.copy();
        }
        if (vset.x == fullX) {
            return this;
        }
        this.vset &= vset.vset;
        this.uset &= vset.uset;
        if (vset.x == emptyX) {
            this.x = emptyX;
        } else {
            long[] jArr = vset.x;
            int length = this.x.length;
            int length2 = jArr.length < length ? jArr.length : length;
            for (int i = 0; i < length2; i++) {
                long[] jArr2 = this.x;
                int i2 = i;
                jArr2[i2] = jArr2[i2] & jArr[i];
            }
            for (int i3 = length2; i3 < length; i3++) {
                this.x[i3] = 0;
            }
        }
        return this;
    }

    public Vset addDAandJoinDU(Vset vset) {
        if (this == DEAD_END) {
            return this;
        }
        if (vset == DEAD_END) {
            return vset;
        }
        if (this.x == fullX) {
            return this;
        }
        if (vset.x == fullX) {
            return vset.copy();
        }
        this.vset |= vset.vset;
        this.uset = this.uset & vset.uset & (vset.vset ^ (-1));
        int length = this.x.length;
        long[] jArr = vset.x;
        int length2 = jArr.length;
        if (jArr != emptyX) {
            if (length2 > length) {
                growX(length2);
            }
            int i = 0;
            while (i < length2) {
                long[] jArr2 = this.x;
                int i2 = i;
                jArr2[i2] = jArr2[i2] | jArr[i];
                int i3 = i + 1;
                if (i3 == length2) {
                    break;
                }
                this.x[i3] = this.x[i3] & jArr[i3] & (jArr[i3 - 1] ^ (-1));
                i = i3 + 1;
            }
        }
        for (int i4 = length2 | 1; i4 < length; i4 += 2) {
            this.x[i4] = 0;
        }
        return this;
    }

    public static Vset firstDAandSecondDU(Vset vset, Vset vset2) {
        if (vset.x == fullX) {
            return vset.copy();
        }
        long[] jArr = vset.x;
        int length = jArr.length;
        long[] jArr2 = vset2.x;
        int length2 = jArr2.length;
        int i = length > length2 ? length : length2;
        long[] jArr3 = emptyX;
        if (i > 0) {
            jArr3 = new long[i];
            for (int i2 = 0; i2 < length; i2 += 2) {
                jArr3[i2] = jArr[i2];
            }
            for (int i3 = 1; i3 < length2; i3 += 2) {
                jArr3[i3] = jArr2[i3];
            }
        }
        return new Vset(vset.vset, vset2.uset, jArr3);
    }

    public Vset removeAdditionalVars(int i) {
        if (this.x == fullX) {
            return this;
        }
        long j = 1 << i;
        if (i >= 64) {
            int i2 = ((i / 64) - 1) * 2;
            if (i2 < this.x.length) {
                long[] jArr = this.x;
                jArr[i2] = jArr[i2] & (j - 1);
                int i3 = i2 + 1;
                if (i3 < this.x.length) {
                    long[] jArr2 = this.x;
                    jArr2[i3] = jArr2[i3] & (j - 1);
                }
                while (true) {
                    i3++;
                    if (i3 >= this.x.length) {
                        break;
                    }
                    this.x[i3] = 0;
                }
            }
        } else {
            if (this.x.length > 0) {
                this.x = emptyX;
            }
            this.vset &= j - 1;
            this.uset &= j - 1;
        }
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0072, code lost:
    
        if (r7 == 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0075, code lost:
    
        r9 = r9 + 1;
        r7 = r7 >>> 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0080, code lost:
    
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int varLimit() {
        long j;
        int i;
        int length = (this.x.length / 2) * 2;
        while (true) {
            if (length >= 0) {
                if (length != this.x.length) {
                    j = this.x[length];
                    if (length + 1 < this.x.length) {
                        j |= this.x[length + 1];
                    }
                    if (j != 0) {
                        i = ((length / 2) + 1) * 64;
                        break;
                    }
                }
                length -= 2;
            } else {
                j = this.vset | this.uset;
                if (j != 0) {
                    i = 0;
                } else {
                    return 0;
                }
            }
        }
    }

    public String toString() {
        if (this == DEAD_END) {
            return "{DEAD_END}";
        }
        StringBuffer stringBuffer = new StringBuffer("{");
        int length = 64 * (1 + ((this.x.length + 1) / 2));
        for (int i = 0; i < length; i++) {
            if (!testVarUnassigned(i)) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(' ');
                }
                stringBuffer.append(i);
                if (!testVar(i)) {
                    stringBuffer.append('?');
                }
            }
        }
        if (this.x == fullX) {
            stringBuffer.append("...DEAD_END");
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
