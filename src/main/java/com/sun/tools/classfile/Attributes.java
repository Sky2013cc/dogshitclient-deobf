package com.sun.tools.classfile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: target.jar:com/sun/tools/classfile/Attributes.class */
public class Attributes implements Iterable<Attribute> {
    public final Attribute[] attrs;
    public final Map<String, Attribute> map = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Attributes(ClassReader classReader) throws IOException {
        int readUnsignedShort = classReader.readUnsignedShort();
        this.attrs = new Attribute[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            Attribute read = Attribute.read(classReader);
            this.attrs[i] = read;
            try {
                this.map.put(read.getName(classReader.getConstantPool()), read);
            } catch (ConstantPoolException e) {
            }
        }
    }

    public Attributes(ConstantPool constantPool, Attribute[] attributeArr) {
        this.attrs = attributeArr;
        for (Attribute attribute : attributeArr) {
            try {
                this.map.put(attribute.getName(constantPool), attribute);
            } catch (ConstantPoolException e) {
            }
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Attribute> iterator() {
        return Arrays.asList(this.attrs).iterator();
    }

    public Attribute get(int i) {
        return this.attrs[i];
    }

    public Attribute get(String str) {
        return this.map.get(str);
    }

    public int getIndex(ConstantPool constantPool, String str) {
        for (int i = 0; i < this.attrs.length; i++) {
            Attribute attribute = this.attrs[i];
            if (attribute != null) {
                try {
                    if (attribute.getName(constantPool).equals(str)) {
                        return i;
                    }
                } catch (ConstantPoolException e) {
                }
            }
        }
        return -1;
    }

    public int size() {
        return this.attrs.length;
    }

    public int byteLength() {
        int i = 2;
        for (Attribute attribute : this.attrs) {
            i += attribute.byteLength();
        }
        return i;
    }
}
