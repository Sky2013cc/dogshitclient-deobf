package com.sun.tools.classfile;

import com.sun.tools.classfile.Attribute;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: target.jar:com/sun/tools/classfile/ClassReader.class */
public class ClassReader {
    private DataInputStream in;
    private ClassFile classFile;
    private Attribute.Factory attributeFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassReader(ClassFile classFile, InputStream inputStream, Attribute.Factory factory) throws IOException {
        classFile.getClass();
        factory.getClass();
        this.classFile = classFile;
        this.in = new DataInputStream(new BufferedInputStream(inputStream));
        this.attributeFactory = factory;
    }

    ClassFile getClassFile() {
        return this.classFile;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstantPool getConstantPool() {
        return this.classFile.constant_pool;
    }

    public Attribute readAttribute() throws IOException {
        int readUnsignedShort = readUnsignedShort();
        byte[] bArr = new byte[readInt()];
        readFully(bArr);
        DataInputStream dataInputStream = this.in;
        this.in = new DataInputStream(new ByteArrayInputStream(bArr));
        try {
            Attribute createAttribute = this.attributeFactory.createAttribute(this, readUnsignedShort, bArr);
            this.in = dataInputStream;
            return createAttribute;
        } catch (Throwable th) {
            this.in = dataInputStream;
            throw th;
        }
    }

    public void readFully(byte[] bArr) throws IOException {
        this.in.readFully(bArr);
    }

    public int readUnsignedByte() throws IOException {
        return this.in.readUnsignedByte();
    }

    public int readUnsignedShort() throws IOException {
        return this.in.readUnsignedShort();
    }

    public int readInt() throws IOException {
        return this.in.readInt();
    }

    public long readLong() throws IOException {
        return this.in.readLong();
    }

    public float readFloat() throws IOException {
        return this.in.readFloat();
    }

    public double readDouble() throws IOException {
        return this.in.readDouble();
    }

    public String readUTF() throws IOException {
        return this.in.readUTF();
    }
}
