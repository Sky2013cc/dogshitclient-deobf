package com.sun.tools.javac.util;

import java.util.Arrays;
import java.util.Random;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import okhttp3.internal.url._UrlKt;

/* loaded from: target.jar:com/sun/tools/javac/util/Bits.class */
public class Bits {
    private static final int wordlen = 32;
    private static final int wordshift = 5;
    private static final int wordmask = 31;
    public int[] bits;
    private static final int[] unassignedBits = new int[0];
    protected BitsState currentState;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: target.jar:com/sun/tools/javac/util/Bits$BitsState.class */
    public enum BitsState {
        UNKNOWN,
        UNINIT,
        NORMAL;

        static BitsState getState(int[] iArr, boolean z) {
            if (z) {
                return UNKNOWN;
            }
            if (iArr != Bits.unassignedBits) {
                return NORMAL;
            }
            return UNINIT;
        }
    }

    public Bits() {
        this(false);
    }

    public Bits(Bits bits) {
        this(bits.dup().bits, BitsState.getState(bits.bits, false));
    }

    public Bits(boolean z) {
        this(unassignedBits, BitsState.getState(unassignedBits, z));
    }

    protected Bits(int[] iArr, BitsState bitsState) {
        this.bits = null;
        this.bits = iArr;
        this.currentState = bitsState;
        switch (bitsState) {
            case UNKNOWN:
                this.bits = null;
                return;
            case NORMAL:
                Assert.check(iArr != unassignedBits);
                return;
            default:
                return;
        }
    }

    protected void sizeTo(int i) {
        if (this.bits.length < i) {
            this.bits = Arrays.copyOf(this.bits, i);
        }
    }

    public void clear() {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        for (int i = 0; i < this.bits.length; i++) {
            this.bits[i] = 0;
        }
        this.currentState = BitsState.NORMAL;
    }

    public void reset() {
        internalReset();
    }

    protected void internalReset() {
        this.bits = null;
        this.currentState = BitsState.UNKNOWN;
    }

    public boolean isReset() {
        return this.currentState == BitsState.UNKNOWN;
    }

    public Bits assign(Bits bits) {
        this.bits = bits.dup().bits;
        this.currentState = BitsState.NORMAL;
        return this;
    }

    public Bits dup() {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        Bits bits = new Bits();
        bits.bits = dupBits();
        this.currentState = BitsState.NORMAL;
        return bits;
    }

    protected int[] dupBits() {
        int[] iArr;
        if (this.currentState != BitsState.NORMAL) {
            iArr = this.bits;
        } else {
            iArr = new int[this.bits.length];
            System.arraycopy(this.bits, 0, iArr, 0, this.bits.length);
        }
        return iArr;
    }

    public void incl(int i) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        Assert.check(i >= 0, "Value of x " + i);
        sizeTo((i >>> 5) + 1);
        this.bits[i >>> 5] = this.bits[i >>> 5] | (1 << (i & 31));
        this.currentState = BitsState.NORMAL;
    }

    public void inclRange(int i, int i2) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        sizeTo((i2 >>> 5) + 1);
        for (int i3 = i; i3 < i2; i3++) {
            this.bits[i3 >>> 5] = this.bits[i3 >>> 5] | (1 << (i3 & 31));
        }
        this.currentState = BitsState.NORMAL;
    }

    public void excludeFrom(int i) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        Bits bits = new Bits();
        bits.sizeTo(this.bits.length);
        bits.inclRange(0, i);
        internalAndSet(bits);
        this.currentState = BitsState.NORMAL;
    }

    public void excl(int i) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        Assert.check(i >= 0);
        sizeTo((i >>> 5) + 1);
        this.bits[i >>> 5] = this.bits[i >>> 5] & ((1 << (i & 31)) ^ (-1));
        this.currentState = BitsState.NORMAL;
    }

    public boolean isMember(int i) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        return 0 <= i && i < (this.bits.length << 5) && (this.bits[i >>> 5] & (1 << (i & 31))) != 0;
    }

    public Bits andSet(Bits bits) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        internalAndSet(bits);
        this.currentState = BitsState.NORMAL;
        return this;
    }

    protected void internalAndSet(Bits bits) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        sizeTo(bits.bits.length);
        for (int i = 0; i < bits.bits.length; i++) {
            this.bits[i] = this.bits[i] & bits.bits[i];
        }
    }

    public Bits orSet(Bits bits) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        sizeTo(bits.bits.length);
        for (int i = 0; i < bits.bits.length; i++) {
            this.bits[i] = this.bits[i] | bits.bits[i];
        }
        this.currentState = BitsState.NORMAL;
        return this;
    }

    public Bits diffSet(Bits bits) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        for (int i = 0; i < this.bits.length; i++) {
            if (i < bits.bits.length) {
                this.bits[i] = this.bits[i] & (bits.bits[i] ^ (-1));
            }
        }
        this.currentState = BitsState.NORMAL;
        return this;
    }

    public Bits xorSet(Bits bits) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        sizeTo(bits.bits.length);
        for (int i = 0; i < bits.bits.length; i++) {
            this.bits[i] = this.bits[i] ^ bits.bits[i];
        }
        this.currentState = BitsState.NORMAL;
        return this;
    }

    private static int trailingZeroBits(int i) {
        Assert.check(true);
        if (i == 0) {
            return 32;
        }
        int i2 = 1;
        if ((i & 65535) == 0) {
            i2 = 1 + 16;
            i >>>= 16;
        }
        if ((i & 255) == 0) {
            i2 += 8;
            i >>>= 8;
        }
        if ((i & 15) == 0) {
            i2 += 4;
            i >>>= 4;
        }
        if ((i & 3) == 0) {
            i2 += 2;
            i >>>= 2;
        }
        return i2 - (i & 1);
    }

    public int nextBit(int i) {
        Assert.check(this.currentState != BitsState.UNKNOWN);
        int i2 = i >>> 5;
        if (i2 >= this.bits.length) {
            return -1;
        }
        int i3 = this.bits[i2] & (((1 << (i & 31)) - 1) ^ (-1));
        while (true) {
            int i4 = i3;
            if (i4 != 0) {
                return (i2 << 5) + trailingZeroBits(i4);
            }
            i2++;
            if (i2 >= this.bits.length) {
                return -1;
            }
            i3 = this.bits[i2];
        }
    }

    public String toString() {
        if (this.bits != null && this.bits.length > 0) {
            char[] cArr = new char[this.bits.length * 32];
            for (int i = 0; i < this.bits.length * 32; i++) {
                cArr[i] = isMember(i) ? '1' : '0';
            }
            return new String(cArr);
        }
        return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
    }

    public static void main(String[] strArr) {
        int nextInt;
        Random random = new Random();
        Bits bits = new Bits();
        for (int i = 0; i < 125; i++) {
            do {
                nextInt = random.nextInt(LinkerCallSite.ARGLIMIT);
            } while (bits.isMember(nextInt));
            System.out.println("adding " + nextInt);
            bits.incl(nextInt);
        }
        int i2 = 0;
        int nextBit = bits.nextBit(0);
        while (true) {
            int i3 = nextBit;
            if (i3 < 0) {
                break;
            }
            System.out.println("found " + i3);
            i2++;
            nextBit = bits.nextBit(i3 + 1);
        }
        if (i2 != 125) {
            throw new Error();
        }
    }
}
