package com.sun.tools.javac.util;

/* loaded from: target.jar:com/sun/tools/javac/util/IntHashTable.class */
public class IntHashTable {
    private static final int DEFAULT_INITIAL_SIZE = 64;
    protected Object[] objs;
    protected int[] ints;
    protected int mask;
    protected int num_bindings;
    private static final Object DELETED = new Object();

    public IntHashTable() {
        this.objs = new Object[64];
        this.ints = new int[64];
        this.mask = 63;
    }

    public IntHashTable(int i) {
        int i2 = 4;
        while (i > (1 << i2)) {
            i2++;
        }
        int i3 = 1 << i2;
        this.objs = new Object[i3];
        this.ints = new int[i3];
        this.mask = i3 - 1;
    }

    public int hash(Object obj) {
        return System.identityHashCode(obj);
    }

    public int lookup(Object obj, int i) {
        int i2 = (i ^ (i << 6)) | 1;
        int i3 = -1;
        int i4 = i ^ (i >>> 15);
        int i5 = this.mask;
        while (true) {
            int i6 = i4 & i5;
            Object obj2 = this.objs[i6];
            if (obj2 == obj) {
                return i6;
            }
            if (obj2 == null) {
                return i3 >= 0 ? i3 : i6;
            }
            if (obj2 == DELETED && i3 < 0) {
                i3 = i6;
            }
            i4 = i6 + i2;
            i5 = this.mask;
        }
    }

    public int lookup(Object obj) {
        return lookup(obj, hash(obj));
    }

    public int getFromIndex(int i) {
        Object obj = this.objs[i];
        if (obj == null || obj == DELETED) {
            return -1;
        }
        return this.ints[i];
    }

    public int putAtIndex(Object obj, int i, int i2) {
        Object obj2 = this.objs[i2];
        if (obj2 == null || obj2 == DELETED) {
            this.objs[i2] = obj;
            this.ints[i2] = i;
            if (obj2 != DELETED) {
                this.num_bindings++;
            }
            if (3 * this.num_bindings >= 2 * this.objs.length) {
                rehash();
                return -1;
            }
            return -1;
        }
        int i3 = this.ints[i2];
        this.ints[i2] = i;
        return i3;
    }

    public int remove(Object obj) {
        int lookup = lookup(obj);
        Object obj2 = this.objs[lookup];
        if (obj2 == null || obj2 == DELETED) {
            return -1;
        }
        this.objs[lookup] = DELETED;
        return this.ints[lookup];
    }

    protected void rehash() {
        Object[] objArr = this.objs;
        int[] iArr = this.ints;
        int length = objArr.length << 1;
        this.objs = new Object[length];
        this.ints = new int[length];
        this.mask = length - 1;
        this.num_bindings = 0;
        int length2 = iArr.length;
        while (true) {
            length2--;
            if (length2 >= 0) {
                Object obj = objArr[length2];
                if (obj != null && obj != DELETED) {
                    putAtIndex(obj, iArr[length2], lookup(obj, hash(obj)));
                }
            } else {
                return;
            }
        }
    }

    public void clear() {
        int length = this.objs.length;
        while (true) {
            length--;
            if (length >= 0) {
                this.objs[length] = null;
            } else {
                this.num_bindings = 0;
                return;
            }
        }
    }
}
