package sun.rmi.rmic.iiop;

import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:sun/rmi/rmic/iiop/StaticStringsHash.class */
public class StaticStringsHash {
    public String[] strings;
    public int[] keys;
    public int[][] buckets;
    private int length;
    private int[] tempKeys;
    private int[] bucketSizes;
    private int bucketCount;
    private int maxDepth;
    private int keyKind;
    private int charAt;
    private static final int LENGTH = 0;
    private static final int CHAR_AT = 1;
    private static final int HASH_CODE = 2;
    private static final int CHAR_AT_MAX_LINES = 50;
    private static final int CHAR_AT_MAX_CHARS = 1000;
    public String method = null;
    private int minStringLength = Integer.MAX_VALUE;

    public int getKey(String str) {
        switch (this.keyKind) {
            case 0:
                return str.length();
            case 1:
                return str.charAt(this.charAt);
            case 2:
                return str.hashCode();
            default:
                throw new Error("Bad keyKind");
        }
    }

    /* JADX WARN: Type inference failed for: r1v27, types: [int[], int[][]] */
    public StaticStringsHash(String[] strArr) {
        boolean z;
        this.strings = null;
        this.keys = null;
        this.buckets = (int[][]) null;
        this.strings = strArr;
        this.length = strArr.length;
        this.tempKeys = new int[this.length];
        this.bucketSizes = new int[this.length];
        setMinStringLength();
        int keys = getKeys(0);
        int i = -1;
        boolean z2 = false;
        if (keys > 1) {
            int i2 = this.minStringLength;
            if (this.length > 50 && this.length * i2 > 1000) {
                i2 = this.length / 1000;
            }
            this.charAt = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                int keys2 = getKeys(1);
                if (keys2 < keys) {
                    keys = keys2;
                    i = i3;
                    if (keys == 1) {
                        break;
                    }
                }
                this.charAt++;
            }
            this.charAt = i;
            if (keys > 1 && getKeys(2) < keys - 3) {
                z2 = true;
            }
            if (!z2) {
                if (i >= 0) {
                    getKeys(1);
                } else {
                    getKeys(0);
                }
            }
        }
        this.keys = new int[this.bucketCount];
        System.arraycopy(this.tempKeys, 0, this.keys, 0, this.bucketCount);
        do {
            z = false;
            for (int i4 = 0; i4 < this.bucketCount - 1; i4++) {
                if (this.keys[i4] > this.keys[i4 + 1]) {
                    int i5 = this.keys[i4];
                    this.keys[i4] = this.keys[i4 + 1];
                    this.keys[i4 + 1] = i5;
                    int i6 = this.bucketSizes[i4];
                    this.bucketSizes[i4] = this.bucketSizes[i4 + 1];
                    this.bucketSizes[i4 + 1] = i6;
                    z = true;
                }
            }
        } while (z);
        int findUnusedKey = findUnusedKey();
        this.buckets = new int[this.bucketCount];
        for (int i7 = 0; i7 < this.bucketCount; i7++) {
            this.buckets[i7] = new int[this.bucketSizes[i7]];
            for (int i8 = 0; i8 < this.bucketSizes[i7]; i8++) {
                this.buckets[i7][i8] = findUnusedKey;
            }
        }
        for (int i9 = 0; i9 < strArr.length; i9++) {
            int key = getKey(strArr[i9]);
            int i10 = 0;
            while (true) {
                if (i10 >= this.bucketCount) {
                    break;
                }
                if (this.keys[i10] != key) {
                    i10++;
                } else {
                    int i11 = 0;
                    while (this.buckets[i10][i11] != findUnusedKey) {
                        i11++;
                    }
                    this.buckets[i10][i11] = i9;
                }
            }
        }
    }

    public static void main(String[] strArr) {
        StaticStringsHash staticStringsHash = new StaticStringsHash(strArr);
        System.out.println();
        System.out.println("    public boolean contains(String key) {");
        System.out.println("        switch (key." + staticStringsHash.method + ") {");
        for (int i = 0; i < staticStringsHash.buckets.length; i++) {
            System.out.println("            case " + staticStringsHash.keys[i] + ": ");
            for (int i2 = 0; i2 < staticStringsHash.buckets[i].length; i2++) {
                if (i2 > 0) {
                    System.out.print("                } else ");
                } else {
                    System.out.print("                ");
                }
                System.out.println("if (key.equals(\"" + staticStringsHash.strings[staticStringsHash.buckets[i][i2]] + "\")) {");
                System.out.println("                    return true;");
            }
            System.out.println("                }");
        }
        System.out.println("        }");
        System.out.println("        return false;");
        System.out.println("    }");
    }

    private void resetKeys(int i) {
        this.keyKind = i;
        switch (i) {
            case 0:
                this.method = "length()";
                break;
            case 1:
                this.method = "charAt(" + this.charAt + RuntimeConstants.SIG_ENDMETHOD;
                break;
            case 2:
                this.method = "hashCode()";
                break;
        }
        this.maxDepth = 1;
        this.bucketCount = 0;
        for (int i2 = 0; i2 < this.length; i2++) {
            this.tempKeys[i2] = 0;
            this.bucketSizes[i2] = 0;
        }
    }

    private void setMinStringLength() {
        for (int i = 0; i < this.length; i++) {
            if (this.strings[i].length() < this.minStringLength) {
                this.minStringLength = this.strings[i].length();
            }
        }
    }

    private int findUnusedKey() {
        int i = 0;
        int length = this.keys.length;
        while (true) {
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (this.keys[i2] != i) {
                    i2++;
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                i--;
            } else {
                return i;
            }
        }
    }

    private int getKeys(int i) {
        resetKeys(i);
        for (int i2 = 0; i2 < this.strings.length; i2++) {
            addKey(getKey(this.strings[i2]));
        }
        return this.maxDepth;
    }

    private void addKey(int i) {
        boolean z = true;
        int i2 = 0;
        while (true) {
            if (i2 >= this.bucketCount) {
                break;
            }
            if (this.tempKeys[i2] != i) {
                i2++;
            } else {
                z = false;
                int[] iArr = this.bucketSizes;
                int i3 = i2;
                iArr[i3] = iArr[i3] + 1;
                if (this.bucketSizes[i2] > this.maxDepth) {
                    this.maxDepth = this.bucketSizes[i2];
                }
            }
        }
        if (z) {
            this.tempKeys[this.bucketCount] = i;
            this.bucketSizes[this.bucketCount] = 1;
            this.bucketCount++;
        }
    }
}
