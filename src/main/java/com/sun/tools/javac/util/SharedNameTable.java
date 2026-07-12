package com.sun.tools.javac.util;

import com.sun.tools.javac.util.Name;
import java.lang.ref.SoftReference;

/* loaded from: target.jar:com/sun/tools/javac/util/SharedNameTable.class */
public class SharedNameTable extends Name.Table {
    private static List<SoftReference<SharedNameTable>> freelist = List.nil();
    private NameImpl[] hashes;
    public byte[] bytes;
    private int hashMask;
    private int nc;

    public static synchronized SharedNameTable create(Names names) {
        while (freelist.nonEmpty()) {
            SharedNameTable sharedNameTable = freelist.head.get();
            freelist = freelist.tail;
            if (sharedNameTable != null) {
                return sharedNameTable;
            }
        }
        return new SharedNameTable(names);
    }

    private static synchronized void dispose(SharedNameTable sharedNameTable) {
        freelist = freelist.prepend(new SoftReference<>(sharedNameTable));
    }

    public SharedNameTable(Names names, int i, int i2) {
        super(names);
        this.nc = 0;
        this.hashMask = i - 1;
        this.hashes = new NameImpl[i];
        this.bytes = new byte[i2];
    }

    public SharedNameTable(Names names) {
        this(names, 32768, 131072);
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromChars(char[] cArr, int i, int i2) {
        NameImpl nameImpl;
        int i3 = this.nc;
        byte[] ensureCapacity = ArrayUtils.ensureCapacity(this.bytes, i3 + (i2 * 3));
        this.bytes = ensureCapacity;
        int chars2utf = Convert.chars2utf(cArr, i, ensureCapacity, i3, i2) - i3;
        int hashValue = hashValue(ensureCapacity, i3, chars2utf) & this.hashMask;
        NameImpl nameImpl2 = this.hashes[hashValue];
        while (true) {
            nameImpl = nameImpl2;
            if (nameImpl == null || (nameImpl.getByteLength() == chars2utf && equals(ensureCapacity, nameImpl.index, ensureCapacity, i3, chars2utf))) {
                break;
            }
            nameImpl2 = nameImpl.next;
        }
        if (nameImpl == null) {
            nameImpl = new NameImpl(this);
            nameImpl.index = i3;
            nameImpl.length = chars2utf;
            nameImpl.next = this.hashes[hashValue];
            this.hashes[hashValue] = nameImpl;
            this.nc = i3 + chars2utf;
            if (chars2utf == 0) {
                this.nc++;
            }
        }
        return nameImpl;
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromUtf(byte[] bArr, int i, int i2) {
        int hashValue = hashValue(bArr, i, i2) & this.hashMask;
        NameImpl nameImpl = this.hashes[hashValue];
        byte[] bArr2 = this.bytes;
        while (nameImpl != null && (nameImpl.getByteLength() != i2 || !equals(bArr2, nameImpl.index, bArr, i, i2))) {
            nameImpl = nameImpl.next;
        }
        if (nameImpl == null) {
            int i3 = this.nc;
            byte[] ensureCapacity = ArrayUtils.ensureCapacity(bArr2, i3 + i2);
            this.bytes = ensureCapacity;
            System.arraycopy(bArr, i, ensureCapacity, i3, i2);
            nameImpl = new NameImpl(this);
            nameImpl.index = i3;
            nameImpl.length = i2;
            nameImpl.next = this.hashes[hashValue];
            this.hashes[hashValue] = nameImpl;
            this.nc = i3 + i2;
            if (i2 == 0) {
                this.nc++;
            }
        }
        return nameImpl;
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public void dispose() {
        dispose(this);
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/SharedNameTable$NameImpl.class */
    static class NameImpl extends Name {
        NameImpl next;
        int index;
        int length;

        NameImpl(SharedNameTable sharedNameTable) {
            super(sharedNameTable);
        }

        @Override // com.sun.tools.javac.util.Name
        public int getIndex() {
            return this.index;
        }

        @Override // com.sun.tools.javac.util.Name
        public int getByteLength() {
            return this.length;
        }

        @Override // com.sun.tools.javac.util.Name
        public byte getByteAt(int i) {
            return getByteArray()[this.index + i];
        }

        @Override // com.sun.tools.javac.util.Name
        public byte[] getByteArray() {
            return ((SharedNameTable) this.table).bytes;
        }

        @Override // com.sun.tools.javac.util.Name
        public int getByteOffset() {
            return this.index;
        }

        public int hashCode() {
            return this.index;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Name) && this.table == ((Name) obj).table && this.index == ((Name) obj).getIndex();
        }
    }
}
