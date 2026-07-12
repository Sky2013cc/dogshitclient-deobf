package com.sun.tools.javac.util;

/* loaded from: target.jar:com/sun/tools/javac/util/Name.class */
public abstract class Name implements javax.lang.model.element.Name {
    public final Table table;

    public abstract int getIndex();

    public abstract int getByteLength();

    public abstract byte getByteAt(int i);

    public abstract byte[] getByteArray();

    public abstract int getByteOffset();

    /* JADX INFO: Access modifiers changed from: protected */
    public Name(Table table) {
        this.table = table;
    }

    public boolean contentEquals(CharSequence charSequence) {
        return toString().equals(charSequence.toString());
    }

    public int length() {
        return toString().length();
    }

    public char charAt(int i) {
        return toString().charAt(i);
    }

    public CharSequence subSequence(int i, int i2) {
        return toString().subSequence(i, i2);
    }

    public Name append(Name name) {
        int byteLength = getByteLength();
        byte[] bArr = new byte[byteLength + name.getByteLength()];
        getBytes(bArr, 0);
        name.getBytes(bArr, byteLength);
        return this.table.fromUtf(bArr, 0, bArr.length);
    }

    public Name append(char c, Name name) {
        int byteLength = getByteLength();
        byte[] bArr = new byte[byteLength + 1 + name.getByteLength()];
        getBytes(bArr, 0);
        bArr[byteLength] = (byte) c;
        name.getBytes(bArr, byteLength + 1);
        return this.table.fromUtf(bArr, 0, bArr.length);
    }

    public int compareTo(Name name) {
        return name.getIndex() - getIndex();
    }

    public boolean isEmpty() {
        return getByteLength() == 0;
    }

    public int lastIndexOf(byte b) {
        byte[] byteArray = getByteArray();
        int byteOffset = getByteOffset();
        int byteLength = getByteLength() - 1;
        while (byteLength >= 0 && byteArray[byteOffset + byteLength] != b) {
            byteLength--;
        }
        return byteLength;
    }

    public boolean startsWith(Name name) {
        byte[] byteArray = getByteArray();
        int byteOffset = getByteOffset();
        int byteLength = getByteLength();
        byte[] byteArray2 = name.getByteArray();
        int byteOffset2 = name.getByteOffset();
        int byteLength2 = name.getByteLength();
        int i = 0;
        while (i < byteLength2 && i < byteLength && byteArray[byteOffset + i] == byteArray2[byteOffset2 + i]) {
            i++;
        }
        return i == byteLength2;
    }

    public Name subName(int i, int i2) {
        if (i2 < i) {
            i2 = i;
        }
        return this.table.fromUtf(getByteArray(), getByteOffset() + i, i2 - i);
    }

    public String toString() {
        return Convert.utf2string(getByteArray(), getByteOffset(), getByteLength());
    }

    public byte[] toUtf() {
        byte[] bArr = new byte[getByteLength()];
        getBytes(bArr, 0);
        return bArr;
    }

    public void getBytes(byte[] bArr, int i) {
        System.arraycopy(getByteArray(), getByteOffset(), bArr, i, getByteLength());
    }

    /* loaded from: target.jar:com/sun/tools/javac/util/Name$Table.class */
    public static abstract class Table {
        public final Names names;

        public abstract Name fromChars(char[] cArr, int i, int i2);

        public abstract Name fromUtf(byte[] bArr, int i, int i2);

        public abstract void dispose();

        /* JADX INFO: Access modifiers changed from: package-private */
        public Table(Names names) {
            this.names = names;
        }

        public Name fromString(String str) {
            char[] charArray = str.toCharArray();
            return fromChars(charArray, 0, charArray.length);
        }

        public Name fromUtf(byte[] bArr) {
            return fromUtf(bArr, 0, bArr.length);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static int hashValue(byte[] bArr, int i, int i2) {
            int i3 = 0;
            int i4 = i;
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = i4;
                i4++;
                i3 = ((i3 << 5) - i3) + bArr[i6];
            }
            return i3;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static boolean equals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3 && bArr[i + i4] == bArr2[i2 + i4]) {
                i4++;
            }
            return i4 == i3;
        }
    }
}
