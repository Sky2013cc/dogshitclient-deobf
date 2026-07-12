package com.sun.tools.classfile;

import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/classfile/Method.class */
public class Method {
    public final AccessFlags access_flags;
    public final int name_index;
    public final Descriptor descriptor;
    public final Attributes attributes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Method(ClassReader classReader) throws IOException {
        this.access_flags = new AccessFlags(classReader);
        this.name_index = classReader.readUnsignedShort();
        this.descriptor = new Descriptor(classReader);
        this.attributes = new Attributes(classReader);
    }

    public Method(AccessFlags accessFlags, int i, Descriptor descriptor, Attributes attributes) {
        this.access_flags = accessFlags;
        this.name_index = i;
        this.descriptor = descriptor;
        this.attributes = attributes;
    }

    public int byteLength() {
        return 6 + this.attributes.byteLength();
    }

    public String getName(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getUTF8Value(this.name_index);
    }
}
