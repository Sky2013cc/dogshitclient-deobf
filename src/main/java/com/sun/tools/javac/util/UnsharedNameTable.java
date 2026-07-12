package com.sun.tools.javac.util;

import com.sun.tools.javac.util.Name;
import java.lang.ref.WeakReference;

/* loaded from: target.jar:com/sun/tools/javac/util/UnsharedNameTable.class */
public class UnsharedNameTable extends Name.Table {
    private HashEntry[] hashes;
    private int hashMask;
    public int index;

    public static Name.Table create(Names names) {
        return new UnsharedNameTable(names);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javac/util/UnsharedNameTable$HashEntry.class */
    public static class HashEntry extends WeakReference<NameImpl> {
        HashEntry next;

        HashEntry(NameImpl nameImpl) {
            super(nameImpl);
        }
    }

    public UnsharedNameTable(Names names, int i) {
        super(names);
        this.hashes = null;
        this.hashMask = i - 1;
        this.hashes = new HashEntry[i];
    }

    public UnsharedNameTable(Names names) {
        this(names, 32768);
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromChars(char[] cArr, int i, int i2) {
        byte[] bArr = new byte[i2 * 3];
        return fromUtf(bArr, 0, Convert.chars2utf(cArr, i, bArr, 0, i2));
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public Name fromUtf(byte[] bArr, int i, int i2) {
        int hashValue = hashValue(bArr, i, i2) & this.hashMask;
        HashEntry hashEntry = this.hashes[hashValue];
        HashEntry hashEntry2 = null;
        HashEntry hashEntry3 = hashEntry;
        while (hashEntry != null && hashEntry != null) {
            NameImpl nameImpl = (NameImpl) hashEntry.get();
            if (nameImpl == null) {
                if (hashEntry3 == hashEntry) {
                    HashEntry[] hashEntryArr = this.hashes;
                    HashEntry hashEntry4 = hashEntry.next;
                    hashEntry3 = hashEntry4;
                    hashEntryArr[hashValue] = hashEntry4;
                } else {
                    Assert.checkNonNull(hashEntry2, "previousNonNullTableEntry cannot be null here.");
                    hashEntry2.next = hashEntry.next;
                }
            } else {
                if (nameImpl.getByteLength() == i2 && equals(nameImpl.bytes, 0, bArr, i, i2)) {
                    return nameImpl;
                }
                hashEntry2 = hashEntry;
            }
            hashEntry = hashEntry.next;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        int i3 = this.index;
        this.index = i3 + 1;
        NameImpl nameImpl2 = new NameImpl(this, bArr2, i3);
        HashEntry hashEntry5 = new HashEntry(nameImpl2);
        if (hashEntry2 == null) {
            this.hashes[hashValue] = hashEntry5;
        } else {
            Assert.checkNull((Object) hashEntry2.next, "previousNonNullTableEntry.next must be null.");
            hashEntry2.next = hashEntry5;
        }
        return nameImpl2;
    }

    @Override // com.sun.tools.javac.util.Name.Table
    public void dispose() {
        this.hashes = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javac/util/UnsharedNameTable$NameImpl.class */
    public static class NameImpl extends Name {
        final byte[] bytes;
        final int index;

        NameImpl(UnsharedNameTable unsharedNameTable, byte[] bArr, int i) {
            super(unsharedNameTable);
            this.bytes = bArr;
            this.index = i;
        }

        @Override // com.sun.tools.javac.util.Name
        public int getIndex() {
            return this.index;
        }

        @Override // com.sun.tools.javac.util.Name
        public int getByteLength() {
            return this.bytes.length;
        }

        @Override // com.sun.tools.javac.util.Name
        public byte getByteAt(int i) {
            return this.bytes[i];
        }

        @Override // com.sun.tools.javac.util.Name
        public byte[] getByteArray() {
            return this.bytes;
        }

        @Override // com.sun.tools.javac.util.Name
        public int getByteOffset() {
            return 0;
        }
    }
}
