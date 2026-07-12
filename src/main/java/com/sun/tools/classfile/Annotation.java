package com.sun.tools.classfile;

import java.io.IOException;

/* loaded from: target.jar:com/sun/tools/classfile/Annotation.class */
public class Annotation {
    public final int type_index;
    public final int num_element_value_pairs;
    public final element_value_pair[] element_value_pairs;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/classfile/Annotation$InvalidAnnotation.class */
    public static class InvalidAnnotation extends AttributeException {
        private static final long serialVersionUID = -4620480740735772708L;

        /* JADX INFO: Access modifiers changed from: package-private */
        public InvalidAnnotation(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Annotation(ClassReader classReader) throws IOException, InvalidAnnotation {
        this.type_index = classReader.readUnsignedShort();
        this.num_element_value_pairs = classReader.readUnsignedShort();
        this.element_value_pairs = new element_value_pair[this.num_element_value_pairs];
        for (int i = 0; i < this.element_value_pairs.length; i++) {
            this.element_value_pairs[i] = new element_value_pair(classReader);
        }
    }

    public Annotation(ConstantPool constantPool, int i, element_value_pair[] element_value_pairVarArr) {
        this.type_index = i;
        this.num_element_value_pairs = element_value_pairVarArr.length;
        this.element_value_pairs = element_value_pairVarArr;
    }

    public int length() {
        int i = 4;
        for (element_value_pair element_value_pairVar : this.element_value_pairs) {
            i += element_value_pairVar.length();
        }
        return i;
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Annotation$element_value.class */
    public static abstract class element_value {
        public final int tag;

        /* loaded from: target.jar:com/sun/tools/classfile/Annotation$element_value$Visitor.class */
        public interface Visitor<R, P> {
            R visitPrimitive(Primitive_element_value primitive_element_value, P p);

            R visitEnum(Enum_element_value enum_element_value, P p);

            R visitClass(Class_element_value class_element_value, P p);

            R visitAnnotation(Annotation_element_value annotation_element_value, P p);

            R visitArray(Array_element_value array_element_value, P p);
        }

        public abstract int length();

        public abstract <R, P> R accept(Visitor<R, P> visitor, P p);

        public static element_value read(ClassReader classReader) throws IOException, InvalidAnnotation {
            int readUnsignedByte = classReader.readUnsignedByte();
            switch (readUnsignedByte) {
                case 64:
                    return new Annotation_element_value(classReader, readUnsignedByte);
                case 65:
                case 69:
                case 71:
                case 72:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 100:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                default:
                    throw new InvalidAnnotation("unrecognized tag: " + readUnsignedByte);
                case 66:
                case 67:
                case 68:
                case 70:
                case 73:
                case 74:
                case 83:
                case 90:
                case 115:
                    return new Primitive_element_value(classReader, readUnsignedByte);
                case 91:
                    return new Array_element_value(classReader, readUnsignedByte);
                case 99:
                    return new Class_element_value(classReader, readUnsignedByte);
                case 101:
                    return new Enum_element_value(classReader, readUnsignedByte);
            }
        }

        protected element_value(int i) {
            this.tag = i;
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Annotation$Primitive_element_value.class */
    public static class Primitive_element_value extends element_value {
        public final int const_value_index;

        Primitive_element_value(ClassReader classReader, int i) throws IOException {
            super(i);
            this.const_value_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public int length() {
            return 2;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public <R, P> R accept(element_value.Visitor<R, P> visitor, P p) {
            return visitor.visitPrimitive(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Annotation$Enum_element_value.class */
    public static class Enum_element_value extends element_value {
        public final int type_name_index;
        public final int const_name_index;

        Enum_element_value(ClassReader classReader, int i) throws IOException {
            super(i);
            this.type_name_index = classReader.readUnsignedShort();
            this.const_name_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public int length() {
            return 4;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public <R, P> R accept(element_value.Visitor<R, P> visitor, P p) {
            return visitor.visitEnum(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Annotation$Class_element_value.class */
    public static class Class_element_value extends element_value {
        public final int class_info_index;

        Class_element_value(ClassReader classReader, int i) throws IOException {
            super(i);
            this.class_info_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public int length() {
            return 2;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public <R, P> R accept(element_value.Visitor<R, P> visitor, P p) {
            return visitor.visitClass(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Annotation$Annotation_element_value.class */
    public static class Annotation_element_value extends element_value {
        public final Annotation annotation_value;

        Annotation_element_value(ClassReader classReader, int i) throws IOException, InvalidAnnotation {
            super(i);
            this.annotation_value = new Annotation(classReader);
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public int length() {
            return this.annotation_value.length();
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public <R, P> R accept(element_value.Visitor<R, P> visitor, P p) {
            return visitor.visitAnnotation(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Annotation$Array_element_value.class */
    public static class Array_element_value extends element_value {
        public final int num_values;
        public final element_value[] values;

        Array_element_value(ClassReader classReader, int i) throws IOException, InvalidAnnotation {
            super(i);
            this.num_values = classReader.readUnsignedShort();
            this.values = new element_value[this.num_values];
            for (int i2 = 0; i2 < this.values.length; i2++) {
                this.values[i2] = element_value.read(classReader);
            }
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public int length() {
            int i = 2;
            for (int i2 = 0; i2 < this.values.length; i2++) {
                i += this.values[i2].length();
            }
            return i;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value
        public <R, P> R accept(element_value.Visitor<R, P> visitor, P p) {
            return visitor.visitArray(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Annotation$element_value_pair.class */
    public static class element_value_pair {
        public final int element_name_index;
        public final element_value value;

        element_value_pair(ClassReader classReader) throws IOException, InvalidAnnotation {
            this.element_name_index = classReader.readUnsignedShort();
            this.value = element_value.read(classReader);
        }

        public int length() {
            return 2 + this.value.length();
        }
    }
}
