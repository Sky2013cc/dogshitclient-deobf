package com.sun.tools.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/classfile/ConstantPool.class */
public class ConstantPool {
    public static final int CONSTANT_Utf8 = 1;
    public static final int CONSTANT_Integer = 3;
    public static final int CONSTANT_Float = 4;
    public static final int CONSTANT_Long = 5;
    public static final int CONSTANT_Double = 6;
    public static final int CONSTANT_Class = 7;
    public static final int CONSTANT_String = 8;
    public static final int CONSTANT_Fieldref = 9;
    public static final int CONSTANT_Methodref = 10;
    public static final int CONSTANT_InterfaceMethodref = 11;
    public static final int CONSTANT_NameAndType = 12;
    public static final int CONSTANT_MethodHandle = 15;
    public static final int CONSTANT_MethodType = 16;
    public static final int CONSTANT_InvokeDynamic = 18;
    private CPInfo[] pool;

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$Visitor.class */
    public interface Visitor<R, P> {
        R visitClass(CONSTANT_Class_info cONSTANT_Class_info, P p);

        R visitDouble(CONSTANT_Double_info cONSTANT_Double_info, P p);

        R visitFieldref(CONSTANT_Fieldref_info cONSTANT_Fieldref_info, P p);

        R visitFloat(CONSTANT_Float_info cONSTANT_Float_info, P p);

        R visitInteger(CONSTANT_Integer_info cONSTANT_Integer_info, P p);

        R visitInterfaceMethodref(CONSTANT_InterfaceMethodref_info cONSTANT_InterfaceMethodref_info, P p);

        R visitInvokeDynamic(CONSTANT_InvokeDynamic_info cONSTANT_InvokeDynamic_info, P p);

        R visitLong(CONSTANT_Long_info cONSTANT_Long_info, P p);

        R visitNameAndType(CONSTANT_NameAndType_info cONSTANT_NameAndType_info, P p);

        R visitMethodref(CONSTANT_Methodref_info cONSTANT_Methodref_info, P p);

        R visitMethodHandle(CONSTANT_MethodHandle_info cONSTANT_MethodHandle_info, P p);

        R visitMethodType(CONSTANT_MethodType_info cONSTANT_MethodType_info, P p);

        R visitString(CONSTANT_String_info cONSTANT_String_info, P p);

        R visitUtf8(CONSTANT_Utf8_info cONSTANT_Utf8_info, P p);
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$InvalidIndex.class */
    public static class InvalidIndex extends ConstantPoolException {
        private static final long serialVersionUID = -4350294289300939730L;

        InvalidIndex(int i) {
            super(i);
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "invalid index #" + this.index;
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$UnexpectedEntry.class */
    public static class UnexpectedEntry extends ConstantPoolException {
        private static final long serialVersionUID = 6986335935377933211L;
        public final int expected_tag;
        public final int found_tag;

        UnexpectedEntry(int i, int i2, int i3) {
            super(i);
            this.expected_tag = i2;
            this.found_tag = i3;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "unexpected entry at #" + this.index + " -- expected tag " + this.expected_tag + ", found " + this.found_tag;
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$InvalidEntry.class */
    public static class InvalidEntry extends ConstantPoolException {
        private static final long serialVersionUID = 1000087545585204447L;
        public final int tag;

        InvalidEntry(int i, int i2) {
            super(i);
            this.tag = i2;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "unexpected tag at #" + this.index + ": " + this.tag;
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$EntryNotFound.class */
    public static class EntryNotFound extends ConstantPoolException {
        private static final long serialVersionUID = 2885537606468581850L;
        public final Object value;

        EntryNotFound(Object obj) {
            super(-1);
            this.value = obj;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "value not found: " + this.value;
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$RefKind.class */
    public enum RefKind {
        REF_getField(1, "getfield"),
        REF_getStatic(2, "getstatic"),
        REF_putField(3, "putfield"),
        REF_putStatic(4, "putstatic"),
        REF_invokeVirtual(5, "invokevirtual"),
        REF_invokeStatic(6, "invokestatic"),
        REF_invokeSpecial(7, "invokespecial"),
        REF_newInvokeSpecial(8, "newinvokespecial"),
        REF_invokeInterface(9, "invokeinterface");

        public final int tag;
        public final String name;

        RefKind(int i, String str) {
            this.tag = i;
            this.name = str;
        }

        static RefKind getRefkind(int i) {
            switch (i) {
                case 1:
                    return REF_getField;
                case 2:
                    return REF_getStatic;
                case 3:
                    return REF_putField;
                case 4:
                    return REF_putStatic;
                case 5:
                    return REF_invokeVirtual;
                case 6:
                    return REF_invokeStatic;
                case 7:
                    return REF_invokeSpecial;
                case 8:
                    return REF_newInvokeSpecial;
                case 9:
                    return REF_invokeInterface;
                default:
                    return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstantPool(ClassReader classReader) throws IOException, InvalidEntry {
        int readUnsignedShort = classReader.readUnsignedShort();
        this.pool = new CPInfo[readUnsignedShort];
        int i = 1;
        while (i < readUnsignedShort) {
            int readUnsignedByte = classReader.readUnsignedByte();
            switch (readUnsignedByte) {
                case 1:
                    this.pool[i] = new CONSTANT_Utf8_info(classReader);
                    break;
                case 2:
                case 13:
                case 14:
                case 17:
                default:
                    throw new InvalidEntry(i, readUnsignedByte);
                case 3:
                    this.pool[i] = new CONSTANT_Integer_info(classReader);
                    break;
                case 4:
                    this.pool[i] = new CONSTANT_Float_info(classReader);
                    break;
                case 5:
                    this.pool[i] = new CONSTANT_Long_info(classReader);
                    i++;
                    break;
                case 6:
                    this.pool[i] = new CONSTANT_Double_info(classReader);
                    i++;
                    break;
                case 7:
                    this.pool[i] = new CONSTANT_Class_info(this, classReader);
                    break;
                case 8:
                    this.pool[i] = new CONSTANT_String_info(this, classReader);
                    break;
                case 9:
                    this.pool[i] = new CONSTANT_Fieldref_info(this, classReader);
                    break;
                case 10:
                    this.pool[i] = new CONSTANT_Methodref_info(this, classReader);
                    break;
                case 11:
                    this.pool[i] = new CONSTANT_InterfaceMethodref_info(this, classReader);
                    break;
                case 12:
                    this.pool[i] = new CONSTANT_NameAndType_info(this, classReader);
                    break;
                case 15:
                    this.pool[i] = new CONSTANT_MethodHandle_info(this, classReader);
                    break;
                case 16:
                    this.pool[i] = new CONSTANT_MethodType_info(this, classReader);
                    break;
                case 18:
                    this.pool[i] = new CONSTANT_InvokeDynamic_info(this, classReader);
                    break;
            }
            i++;
        }
    }

    public ConstantPool(CPInfo[] cPInfoArr) {
        this.pool = cPInfoArr;
    }

    public int size() {
        return this.pool.length;
    }

    public int byteLength() {
        int i = 2;
        int i2 = 1;
        while (true) {
            int i3 = i2;
            if (i3 < size()) {
                CPInfo cPInfo = this.pool[i3];
                i += cPInfo.byteLength();
                i2 = i3 + cPInfo.size();
            } else {
                return i;
            }
        }
    }

    public CPInfo get(int i) throws InvalidIndex {
        if (i <= 0 || i >= this.pool.length) {
            throw new InvalidIndex(i);
        }
        if (this.pool[i] == null) {
            throw new InvalidIndex(i);
        }
        return this.pool[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CPInfo get(int i, int i2) throws InvalidIndex, UnexpectedEntry {
        CPInfo cPInfo = get(i);
        if (cPInfo.getTag() != i2) {
            throw new UnexpectedEntry(i, i2, cPInfo.getTag());
        }
        return cPInfo;
    }

    public CONSTANT_Utf8_info getUTF8Info(int i) throws InvalidIndex, UnexpectedEntry {
        return (CONSTANT_Utf8_info) get(i, 1);
    }

    public CONSTANT_Class_info getClassInfo(int i) throws InvalidIndex, UnexpectedEntry {
        return (CONSTANT_Class_info) get(i, 7);
    }

    public CONSTANT_NameAndType_info getNameAndTypeInfo(int i) throws InvalidIndex, UnexpectedEntry {
        return (CONSTANT_NameAndType_info) get(i, 12);
    }

    public String getUTF8Value(int i) throws InvalidIndex, UnexpectedEntry {
        return getUTF8Info(i).value;
    }

    public int getUTF8Index(String str) throws EntryNotFound {
        for (int i = 1; i < this.pool.length; i++) {
            CPInfo cPInfo = this.pool[i];
            if ((cPInfo instanceof CONSTANT_Utf8_info) && ((CONSTANT_Utf8_info) cPInfo).value.equals(str)) {
                return i;
            }
        }
        throw new EntryNotFound(str);
    }

    public Iterable<CPInfo> entries() {
        return new Iterable<CPInfo>() { // from class: com.sun.tools.classfile.ConstantPool.1
            @Override // java.lang.Iterable
            public Iterator<CPInfo> iterator() {
                return new Iterator<CPInfo>() { // from class: com.sun.tools.classfile.ConstantPool.1.1
                    private CPInfo current;
                    private int next = 1;

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.next < ConstantPool.this.pool.length;
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public CPInfo next() {
                        this.current = ConstantPool.this.pool[this.next];
                        switch (this.current.getTag()) {
                            case 5:
                            case 6:
                                this.next += 2;
                                break;
                            default:
                                this.next++;
                                break;
                        }
                        return this.current;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CPInfo.class */
    public static abstract class CPInfo {
        protected final ConstantPool cp;

        public abstract int getTag();

        public abstract int byteLength();

        public abstract <R, D> R accept(Visitor<R, D> visitor, D d);

        CPInfo() {
            this.cp = null;
        }

        CPInfo(ConstantPool constantPool) {
            this.cp = constantPool;
        }

        public int size() {
            return 1;
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CPRefInfo.class */
    public static abstract class CPRefInfo extends CPInfo {
        public final int tag;
        public final int class_index;
        public final int name_and_type_index;

        protected CPRefInfo(ConstantPool constantPool, ClassReader classReader, int i) throws IOException {
            super(constantPool);
            this.tag = i;
            this.class_index = classReader.readUnsignedShort();
            this.name_and_type_index = classReader.readUnsignedShort();
        }

        protected CPRefInfo(ConstantPool constantPool, int i, int i2, int i3) {
            super(constantPool);
            this.tag = i;
            this.class_index = i2;
            this.name_and_type_index = i3;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return this.tag;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        public CONSTANT_Class_info getClassInfo() throws ConstantPoolException {
            return this.cp.getClassInfo(this.class_index);
        }

        public String getClassName() throws ConstantPoolException {
            return this.cp.getClassInfo(this.class_index).getName();
        }

        public CONSTANT_NameAndType_info getNameAndTypeInfo() throws ConstantPoolException {
            return this.cp.getNameAndTypeInfo(this.name_and_type_index);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_Class_info.class */
    public static class CONSTANT_Class_info extends CPInfo {
        public final int name_index;

        CONSTANT_Class_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.name_index = classReader.readUnsignedShort();
        }

        public CONSTANT_Class_info(ConstantPool constantPool, int i) {
            super(constantPool);
            this.name_index = i;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 7;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 3;
        }

        public String getName() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.name_index);
        }

        public String getBaseName() throws ConstantPoolException {
            String name = getName();
            if (name.startsWith(RuntimeConstants.SIG_ARRAY)) {
                int indexOf = name.indexOf("[L");
                if (indexOf == -1) {
                    return null;
                }
                return name.substring(indexOf + 2, name.length() - 1);
            }
            return name;
        }

        public int getDimensionCount() throws ConstantPoolException {
            int i = 0;
            while (getName().charAt(i) == '[') {
                i++;
            }
            return i;
        }

        public String toString() {
            return "CONSTANT_Class_info[name_index: " + this.name_index + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitClass(this, d);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_Double_info.class */
    public static class CONSTANT_Double_info extends CPInfo {
        public final double value;

        CONSTANT_Double_info(ClassReader classReader) throws IOException {
            this.value = classReader.readDouble();
        }

        public CONSTANT_Double_info(double d) {
            this.value = d;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 6;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 9;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int size() {
            return 2;
        }

        public String toString() {
            return "CONSTANT_Double_info[value: " + this.value + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitDouble(this, d);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_Fieldref_info.class */
    public static class CONSTANT_Fieldref_info extends CPRefInfo {
        CONSTANT_Fieldref_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool, classReader, 9);
        }

        public CONSTANT_Fieldref_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool, 9, i, i2);
        }

        public String toString() {
            return "CONSTANT_Fieldref_info[class_index: " + this.class_index + ", name_and_type_index: " + this.name_and_type_index + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitFieldref(this, d);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_Float_info.class */
    public static class CONSTANT_Float_info extends CPInfo {
        public final float value;

        CONSTANT_Float_info(ClassReader classReader) throws IOException {
            this.value = classReader.readFloat();
        }

        public CONSTANT_Float_info(float f) {
            this.value = f;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 4;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        public String toString() {
            return "CONSTANT_Float_info[value: " + this.value + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitFloat(this, d);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_Integer_info.class */
    public static class CONSTANT_Integer_info extends CPInfo {
        public final int value;

        CONSTANT_Integer_info(ClassReader classReader) throws IOException {
            this.value = classReader.readInt();
        }

        public CONSTANT_Integer_info(int i) {
            this.value = i;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 3;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        public String toString() {
            return "CONSTANT_Integer_info[value: " + this.value + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitInteger(this, d);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_InterfaceMethodref_info.class */
    public static class CONSTANT_InterfaceMethodref_info extends CPRefInfo {
        CONSTANT_InterfaceMethodref_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool, classReader, 11);
        }

        public CONSTANT_InterfaceMethodref_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool, 11, i, i2);
        }

        public String toString() {
            return "CONSTANT_InterfaceMethodref_info[class_index: " + this.class_index + ", name_and_type_index: " + this.name_and_type_index + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitInterfaceMethodref(this, d);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_InvokeDynamic_info.class */
    public static class CONSTANT_InvokeDynamic_info extends CPInfo {
        public final int bootstrap_method_attr_index;
        public final int name_and_type_index;

        CONSTANT_InvokeDynamic_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.bootstrap_method_attr_index = classReader.readUnsignedShort();
            this.name_and_type_index = classReader.readUnsignedShort();
        }

        public CONSTANT_InvokeDynamic_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool);
            this.bootstrap_method_attr_index = i;
            this.name_and_type_index = i2;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 18;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        public String toString() {
            return "CONSTANT_InvokeDynamic_info[bootstrap_method_index: " + this.bootstrap_method_attr_index + ", name_and_type_index: " + this.name_and_type_index + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitInvokeDynamic(this, d);
        }

        public CONSTANT_NameAndType_info getNameAndTypeInfo() throws ConstantPoolException {
            return this.cp.getNameAndTypeInfo(this.name_and_type_index);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_Long_info.class */
    public static class CONSTANT_Long_info extends CPInfo {
        public final long value;

        CONSTANT_Long_info(ClassReader classReader) throws IOException {
            this.value = classReader.readLong();
        }

        public CONSTANT_Long_info(long j) {
            this.value = j;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 5;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int size() {
            return 2;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 9;
        }

        public String toString() {
            return "CONSTANT_Long_info[value: " + this.value + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitLong(this, d);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_MethodHandle_info.class */
    public static class CONSTANT_MethodHandle_info extends CPInfo {
        public final RefKind reference_kind;
        public final int reference_index;

        CONSTANT_MethodHandle_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.reference_kind = RefKind.getRefkind(classReader.readUnsignedByte());
            this.reference_index = classReader.readUnsignedShort();
        }

        public CONSTANT_MethodHandle_info(ConstantPool constantPool, RefKind refKind, int i) {
            super(constantPool);
            this.reference_kind = refKind;
            this.reference_index = i;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 15;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 4;
        }

        public String toString() {
            return "CONSTANT_MethodHandle_info[ref_kind: " + this.reference_kind + ", member_index: " + this.reference_index + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitMethodHandle(this, d);
        }

        public CPRefInfo getCPRefInfo() throws ConstantPoolException {
            int i = 10;
            int tag = this.cp.get(this.reference_index).getTag();
            switch (tag) {
                case 9:
                case 11:
                    i = tag;
                    break;
            }
            return (CPRefInfo) this.cp.get(this.reference_index, i);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_MethodType_info.class */
    public static class CONSTANT_MethodType_info extends CPInfo {
        public final int descriptor_index;

        CONSTANT_MethodType_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.descriptor_index = classReader.readUnsignedShort();
        }

        public CONSTANT_MethodType_info(ConstantPool constantPool, int i) {
            super(constantPool);
            this.descriptor_index = i;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 16;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 3;
        }

        public String toString() {
            return "CONSTANT_MethodType_info[signature_index: " + this.descriptor_index + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitMethodType(this, d);
        }

        public String getType() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.descriptor_index);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_Methodref_info.class */
    public static class CONSTANT_Methodref_info extends CPRefInfo {
        CONSTANT_Methodref_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool, classReader, 10);
        }

        public CONSTANT_Methodref_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool, 10, i, i2);
        }

        public String toString() {
            return "CONSTANT_Methodref_info[class_index: " + this.class_index + ", name_and_type_index: " + this.name_and_type_index + "]";
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitMethodref(this, d);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_NameAndType_info.class */
    public static class CONSTANT_NameAndType_info extends CPInfo {
        public final int name_index;
        public final int type_index;

        CONSTANT_NameAndType_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.name_index = classReader.readUnsignedShort();
            this.type_index = classReader.readUnsignedShort();
        }

        public CONSTANT_NameAndType_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool);
            this.name_index = i;
            this.type_index = i2;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 12;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        public String getName() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.name_index);
        }

        public String getType() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.type_index);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitNameAndType(this, d);
        }

        public String toString() {
            return "CONSTANT_NameAndType_info[name_index: " + this.name_index + ", type_index: " + this.type_index + "]";
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_String_info.class */
    public static class CONSTANT_String_info extends CPInfo {
        public final int string_index;

        CONSTANT_String_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.string_index = classReader.readUnsignedShort();
        }

        public CONSTANT_String_info(ConstantPool constantPool, int i) {
            super(constantPool);
            this.string_index = i;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 8;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 3;
        }

        public String getString() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.string_index);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitString(this, d);
        }

        public String toString() {
            return "CONSTANT_String_info[class_index: " + this.string_index + "]";
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_Utf8_info.class */
    public static class CONSTANT_Utf8_info extends CPInfo {
        public final String value;

        CONSTANT_Utf8_info(ClassReader classReader) throws IOException {
            this.value = classReader.readUTF();
        }

        public CONSTANT_Utf8_info(String str) {
            this.value = str;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 1;
        }

        /* renamed from: com.sun.tools.classfile.ConstantPool$CONSTANT_Utf8_info$1SizeOutputStream, reason: invalid class name */
        /* loaded from: target.jar:com/sun/tools/classfile/ConstantPool$CONSTANT_Utf8_info$1SizeOutputStream.class */
        class C1SizeOutputStream extends OutputStream {
            int size;

            C1SizeOutputStream() {
            }

            @Override // java.io.OutputStream
            public void write(int i) {
                this.size++;
            }
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            C1SizeOutputStream c1SizeOutputStream = new C1SizeOutputStream();
            try {
                new DataOutputStream(c1SizeOutputStream).writeUTF(this.value);
            } catch (IOException e) {
            }
            return 1 + c1SizeOutputStream.size;
        }

        public String toString() {
            if (this.value.length() < 32 && isPrintableAscii(this.value)) {
                return "CONSTANT_Utf8_info[value: \"" + this.value + "\"]";
            }
            return "CONSTANT_Utf8_info[value: (" + this.value.length() + " chars)]";
        }

        static boolean isPrintableAscii(String str) {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt < ' ' || charAt >= 127) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitUtf8(this, d);
        }
    }
}
