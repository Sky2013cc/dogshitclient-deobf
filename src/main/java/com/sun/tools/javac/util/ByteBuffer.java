package com.sun.tools.javac.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/javac/util/ByteBuffer.class */
public class ByteBuffer {
    public byte[] elems;
    public int length;

    public ByteBuffer() {
        this(64);
    }

    public ByteBuffer(int i) {
        this.elems = new byte[i];
        this.length = 0;
    }

    public void appendByte(int i) {
        this.elems = ArrayUtils.ensureCapacity(this.elems, this.length);
        byte[] bArr = this.elems;
        int i2 = this.length;
        this.length = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public void appendBytes(byte[] bArr, int i, int i2) {
        this.elems = ArrayUtils.ensureCapacity(this.elems, this.length + i2);
        System.arraycopy(bArr, i, this.elems, this.length, i2);
        this.length += i2;
    }

    public void appendBytes(byte[] bArr) {
        appendBytes(bArr, 0, bArr.length);
    }

    public void appendChar(int i) {
        this.elems = ArrayUtils.ensureCapacity(this.elems, this.length + 1);
        this.elems[this.length] = (byte) ((i >> 8) & 255);
        this.elems[this.length + 1] = (byte) (i & 255);
        this.length += 2;
    }

    public void appendInt(int i) {
        this.elems = ArrayUtils.ensureCapacity(this.elems, this.length + 3);
        this.elems[this.length] = (byte) ((i >> 24) & 255);
        this.elems[this.length + 1] = (byte) ((i >> 16) & 255);
        this.elems[this.length + 2] = (byte) ((i >> 8) & 255);
        this.elems[this.length + 3] = (byte) (i & 255);
        this.length += 4;
    }

    public void appendLong(long j) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
        try {
            new DataOutputStream(byteArrayOutputStream).writeLong(j);
            appendBytes(byteArrayOutputStream.toByteArray(), 0, 8);
        } catch (IOException e) {
            throw new AssertionError("write");
        }
    }

    public void appendFloat(float f) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);
        try {
            new DataOutputStream(byteArrayOutputStream).writeFloat(f);
            appendBytes(byteArrayOutputStream.toByteArray(), 0, 4);
        } catch (IOException e) {
            throw new AssertionError("write");
        }
    }

    public void appendDouble(double d) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
        try {
            new DataOutputStream(byteArrayOutputStream).writeDouble(d);
            appendBytes(byteArrayOutputStream.toByteArray(), 0, 8);
        } catch (IOException e) {
            throw new AssertionError("write");
        }
    }

    public void appendName(Name name) {
        appendBytes(name.getByteArray(), name.getByteOffset(), name.getByteLength());
    }

    public void reset() {
        this.length = 0;
    }

    public Name toName(Names names) {
        return names.fromUtf(this.elems, 0, this.length);
    }
}
