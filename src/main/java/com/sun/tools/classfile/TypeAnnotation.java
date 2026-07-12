package com.sun.tools.classfile;

import com.sun.tools.classfile.Annotation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/classfile/TypeAnnotation.class */
public class TypeAnnotation {
    public final ConstantPool constant_pool;
    public final Position position;
    public final Annotation annotation;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TypeAnnotation(ClassReader classReader) throws IOException, Annotation.InvalidAnnotation {
        this.constant_pool = classReader.getConstantPool();
        this.position = read_position(classReader);
        this.annotation = new Annotation(classReader);
    }

    public TypeAnnotation(ConstantPool constantPool, Annotation annotation, Position position) {
        this.constant_pool = constantPool;
        this.position = position;
        this.annotation = annotation;
    }

    public int length() {
        return this.annotation.length() + position_length(this.position);
    }

    public String toString() {
        try {
            return "@" + this.constant_pool.getUTF8Value(this.annotation.type_index).toString().substring(1) + " pos: " + this.position.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    private static Position read_position(ClassReader classReader) throws IOException, Annotation.InvalidAnnotation {
        int readUnsignedByte = classReader.readUnsignedByte();
        if (!TargetType.isValidTargetTypeValue(readUnsignedByte)) {
            throw new Annotation.InvalidAnnotation("TypeAnnotation: Invalid type annotation target type value: " + String.format("0x%02X", Integer.valueOf(readUnsignedByte)));
        }
        TargetType fromTargetTypeValue = TargetType.fromTargetTypeValue(readUnsignedByte);
        Position position = new Position();
        position.type = fromTargetTypeValue;
        switch (fromTargetTypeValue) {
            case INSTANCEOF:
            case NEW:
            case CONSTRUCTOR_REFERENCE:
            case METHOD_REFERENCE:
                position.offset = classReader.readUnsignedShort();
                break;
            case LOCAL_VARIABLE:
            case RESOURCE_VARIABLE:
                int readUnsignedShort = classReader.readUnsignedShort();
                position.lvarOffset = new int[readUnsignedShort];
                position.lvarLength = new int[readUnsignedShort];
                position.lvarIndex = new int[readUnsignedShort];
                for (int i = 0; i < readUnsignedShort; i++) {
                    position.lvarOffset[i] = classReader.readUnsignedShort();
                    position.lvarLength[i] = classReader.readUnsignedShort();
                    position.lvarIndex[i] = classReader.readUnsignedShort();
                }
                break;
            case EXCEPTION_PARAMETER:
                position.exception_index = classReader.readUnsignedShort();
                break;
            case METHOD_RECEIVER:
            case METHOD_RETURN:
            case FIELD:
                break;
            case CLASS_TYPE_PARAMETER:
            case METHOD_TYPE_PARAMETER:
                position.parameter_index = classReader.readUnsignedByte();
                break;
            case CLASS_TYPE_PARAMETER_BOUND:
            case METHOD_TYPE_PARAMETER_BOUND:
                position.parameter_index = classReader.readUnsignedByte();
                position.bound_index = classReader.readUnsignedByte();
                break;
            case CLASS_EXTENDS:
                int readUnsignedShort2 = classReader.readUnsignedShort();
                if (readUnsignedShort2 == 65535) {
                    readUnsignedShort2 = -1;
                }
                position.type_index = readUnsignedShort2;
                break;
            case THROWS:
                position.type_index = classReader.readUnsignedShort();
                break;
            case METHOD_FORMAL_PARAMETER:
                position.parameter_index = classReader.readUnsignedByte();
                break;
            case CAST:
            case CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
            case METHOD_INVOCATION_TYPE_ARGUMENT:
            case CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
            case METHOD_REFERENCE_TYPE_ARGUMENT:
                position.offset = classReader.readUnsignedShort();
                position.type_index = classReader.readUnsignedByte();
                break;
            case UNKNOWN:
                throw new AssertionError("TypeAnnotation: UNKNOWN target type should never occur!");
            default:
                throw new AssertionError("TypeAnnotation: Unknown target type: " + fromTargetTypeValue);
        }
        int readUnsignedByte2 = classReader.readUnsignedByte();
        ArrayList arrayList = new ArrayList(readUnsignedByte2);
        for (int i2 = 0; i2 < readUnsignedByte2 * 2; i2++) {
            arrayList.add(Integer.valueOf(classReader.readUnsignedByte()));
        }
        position.location = Position.getTypePathFromBinary(arrayList);
        return position;
    }

    private static int position_length(Position position) {
        int i = 0 + 1;
        switch (position.type) {
            case INSTANCEOF:
            case NEW:
            case CONSTRUCTOR_REFERENCE:
            case METHOD_REFERENCE:
                i += 2;
                break;
            case LOCAL_VARIABLE:
            case RESOURCE_VARIABLE:
                int length = position.lvarOffset.length;
                i = i + 2 + (2 * length) + (2 * length) + (2 * length);
                break;
            case EXCEPTION_PARAMETER:
                i += 2;
                break;
            case METHOD_RECEIVER:
            case METHOD_RETURN:
            case FIELD:
                break;
            case CLASS_TYPE_PARAMETER:
            case METHOD_TYPE_PARAMETER:
                i++;
                break;
            case CLASS_TYPE_PARAMETER_BOUND:
            case METHOD_TYPE_PARAMETER_BOUND:
                i = i + 1 + 1;
                break;
            case CLASS_EXTENDS:
                i += 2;
                break;
            case THROWS:
                i += 2;
                break;
            case METHOD_FORMAL_PARAMETER:
                i++;
                break;
            case CAST:
            case CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
            case METHOD_INVOCATION_TYPE_ARGUMENT:
            case CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
            case METHOD_REFERENCE_TYPE_ARGUMENT:
                i = i + 2 + 1;
                break;
            case UNKNOWN:
                throw new AssertionError("TypeAnnotation: UNKNOWN target type should never occur!");
            default:
                throw new AssertionError("TypeAnnotation: Unknown target type: " + position.type);
        }
        return i + 1 + (2 * position.location.size());
    }

    /* loaded from: target.jar:com/sun/tools/classfile/TypeAnnotation$Position.class */
    public static class Position {
        public TargetType type = TargetType.UNKNOWN;
        public List<TypePathEntry> location = new ArrayList(0);
        public int pos = -1;
        public boolean isValidOffset = false;
        public int offset = -1;
        public int[] lvarOffset = null;
        public int[] lvarLength = null;
        public int[] lvarIndex = null;
        public int bound_index = Integer.MIN_VALUE;
        public int parameter_index = Integer.MIN_VALUE;
        public int type_index = Integer.MIN_VALUE;
        public int exception_index = Integer.MIN_VALUE;

        /* loaded from: target.jar:com/sun/tools/classfile/TypeAnnotation$Position$TypePathEntryKind.class */
        public enum TypePathEntryKind {
            ARRAY(0),
            INNER_TYPE(1),
            WILDCARD(2),
            TYPE_ARGUMENT(3);

            public final int tag;

            TypePathEntryKind(int i) {
                this.tag = i;
            }
        }

        /* loaded from: target.jar:com/sun/tools/classfile/TypeAnnotation$Position$TypePathEntry.class */
        public static class TypePathEntry {
            public static final int bytesPerEntry = 2;
            public final TypePathEntryKind tag;
            public final int arg;
            public static final TypePathEntry ARRAY = new TypePathEntry(TypePathEntryKind.ARRAY);
            public static final TypePathEntry INNER_TYPE = new TypePathEntry(TypePathEntryKind.INNER_TYPE);
            public static final TypePathEntry WILDCARD = new TypePathEntry(TypePathEntryKind.WILDCARD);

            private TypePathEntry(TypePathEntryKind typePathEntryKind) {
                if (typePathEntryKind != TypePathEntryKind.ARRAY && typePathEntryKind != TypePathEntryKind.INNER_TYPE && typePathEntryKind != TypePathEntryKind.WILDCARD) {
                    throw new AssertionError("Invalid TypePathEntryKind: " + typePathEntryKind);
                }
                this.tag = typePathEntryKind;
                this.arg = 0;
            }

            public TypePathEntry(TypePathEntryKind typePathEntryKind, int i) {
                if (typePathEntryKind != TypePathEntryKind.TYPE_ARGUMENT) {
                    throw new AssertionError("Invalid TypePathEntryKind: " + typePathEntryKind);
                }
                this.tag = typePathEntryKind;
                this.arg = i;
            }

            public static TypePathEntry fromBinary(int i, int i2) {
                if (i2 != 0 && i != TypePathEntryKind.TYPE_ARGUMENT.tag) {
                    throw new AssertionError("Invalid TypePathEntry tag/arg: " + i + RuntimeConstants.SIG_PACKAGE + i2);
                }
                switch (i) {
                    case 0:
                        return ARRAY;
                    case 1:
                        return INNER_TYPE;
                    case 2:
                        return WILDCARD;
                    case 3:
                        return new TypePathEntry(TypePathEntryKind.TYPE_ARGUMENT, i2);
                    default:
                        throw new AssertionError("Invalid TypePathEntryKind tag: " + i);
                }
            }

            public String toString() {
                return this.tag.toString() + (this.tag == TypePathEntryKind.TYPE_ARGUMENT ? RuntimeConstants.SIG_METHOD + this.arg + RuntimeConstants.SIG_ENDMETHOD : "");
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof TypePathEntry)) {
                    return false;
                }
                TypePathEntry typePathEntry = (TypePathEntry) obj;
                return this.tag == typePathEntry.tag && this.arg == typePathEntry.arg;
            }

            public int hashCode() {
                return (this.tag.hashCode() * 17) + this.arg;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            sb.append(this.type);
            switch (this.type) {
                case INSTANCEOF:
                case NEW:
                case CONSTRUCTOR_REFERENCE:
                case METHOD_REFERENCE:
                    sb.append(", offset = ");
                    sb.append(this.offset);
                    break;
                case LOCAL_VARIABLE:
                case RESOURCE_VARIABLE:
                    if (this.lvarOffset == null) {
                        sb.append(", lvarOffset is null!");
                        break;
                    } else {
                        sb.append(", {");
                        for (int i = 0; i < this.lvarOffset.length; i++) {
                            if (i != 0) {
                                sb.append("; ");
                            }
                            sb.append("start_pc = ");
                            sb.append(this.lvarOffset[i]);
                            sb.append(", length = ");
                            sb.append(this.lvarLength[i]);
                            sb.append(", index = ");
                            sb.append(this.lvarIndex[i]);
                        }
                        sb.append("}");
                        break;
                    }
                case EXCEPTION_PARAMETER:
                    sb.append(", exception_index = ");
                    sb.append(this.exception_index);
                    break;
                case METHOD_RECEIVER:
                case METHOD_RETURN:
                case FIELD:
                    break;
                case CLASS_TYPE_PARAMETER:
                case METHOD_TYPE_PARAMETER:
                    sb.append(", param_index = ");
                    sb.append(this.parameter_index);
                    break;
                case CLASS_TYPE_PARAMETER_BOUND:
                case METHOD_TYPE_PARAMETER_BOUND:
                    sb.append(", param_index = ");
                    sb.append(this.parameter_index);
                    sb.append(", bound_index = ");
                    sb.append(this.bound_index);
                    break;
                case CLASS_EXTENDS:
                    sb.append(", type_index = ");
                    sb.append(this.type_index);
                    break;
                case THROWS:
                    sb.append(", type_index = ");
                    sb.append(this.type_index);
                    break;
                case METHOD_FORMAL_PARAMETER:
                    sb.append(", param_index = ");
                    sb.append(this.parameter_index);
                    break;
                case CAST:
                case CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
                case METHOD_INVOCATION_TYPE_ARGUMENT:
                case CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
                case METHOD_REFERENCE_TYPE_ARGUMENT:
                    sb.append(", offset = ");
                    sb.append(this.offset);
                    sb.append(", type_index = ");
                    sb.append(this.type_index);
                    break;
                case UNKNOWN:
                    sb.append(", position UNKNOWN!");
                    break;
                default:
                    throw new AssertionError("Unknown target type: " + this.type);
            }
            if (!this.location.isEmpty()) {
                sb.append(", location = (");
                sb.append(this.location);
                sb.append(RuntimeConstants.SIG_ENDMETHOD);
            }
            sb.append(", pos = ");
            sb.append(this.pos);
            sb.append(']');
            return sb.toString();
        }

        public boolean emitToClassfile() {
            return !this.type.isLocal() || this.isValidOffset;
        }

        public static List<TypePathEntry> getTypePathFromBinary(List<Integer> list) {
            ArrayList arrayList = new ArrayList(list.size() / 2);
            for (int i = 0; i < list.size(); i += 2) {
                if (i + 1 == list.size()) {
                    throw new AssertionError("Could not decode type path: " + list);
                }
                arrayList.add(TypePathEntry.fromBinary(list.get(i).intValue(), list.get(i + 1).intValue()));
            }
            return arrayList;
        }

        public static List<Integer> getBinaryFromTypePath(List<TypePathEntry> list) {
            ArrayList arrayList = new ArrayList(list.size() * 2);
            for (TypePathEntry typePathEntry : list) {
                arrayList.add(Integer.valueOf(typePathEntry.tag.tag));
                arrayList.add(Integer.valueOf(typePathEntry.arg));
            }
            return arrayList;
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/TypeAnnotation$TargetType.class */
    public enum TargetType {
        CLASS_TYPE_PARAMETER(0),
        METHOD_TYPE_PARAMETER(1),
        CLASS_EXTENDS(16),
        CLASS_TYPE_PARAMETER_BOUND(17),
        METHOD_TYPE_PARAMETER_BOUND(18),
        FIELD(19),
        METHOD_RETURN(20),
        METHOD_RECEIVER(21),
        METHOD_FORMAL_PARAMETER(22),
        THROWS(23),
        LOCAL_VARIABLE(64, true),
        RESOURCE_VARIABLE(65, true),
        EXCEPTION_PARAMETER(66, true),
        INSTANCEOF(67, true),
        NEW(68, true),
        CONSTRUCTOR_REFERENCE(69, true),
        METHOD_REFERENCE(70, true),
        CAST(71, true),
        CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT(72, true),
        METHOD_INVOCATION_TYPE_ARGUMENT(73, true),
        CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT(74, true),
        METHOD_REFERENCE_TYPE_ARGUMENT(75, true),
        UNKNOWN(255);

        private static final int MAXIMUM_TARGET_TYPE_VALUE = 75;
        private final int targetTypeValue;
        private final boolean isLocal;
        private static final TargetType[] targets = new TargetType[76];

        static {
            for (TargetType targetType : values()) {
                if (targetType.targetTypeValue != UNKNOWN.targetTypeValue) {
                    targets[targetType.targetTypeValue] = targetType;
                }
            }
            for (int i = 0; i <= 75; i++) {
                if (targets[i] == null) {
                    targets[i] = UNKNOWN;
                }
            }
        }

        TargetType(int i) {
            this(i, false);
        }

        TargetType(int i, boolean z) {
            if (i < 0 || i > 255) {
                throw new AssertionError("Attribute type value needs to be an unsigned byte: " + String.format("0x%02X", Integer.valueOf(i)));
            }
            this.targetTypeValue = i;
            this.isLocal = z;
        }

        public boolean isLocal() {
            return this.isLocal;
        }

        public int targetTypeValue() {
            return this.targetTypeValue;
        }

        public static boolean isValidTargetTypeValue(int i) {
            if (i == UNKNOWN.targetTypeValue) {
                return true;
            }
            return i >= 0 && i < targets.length;
        }

        public static TargetType fromTargetTypeValue(int i) {
            if (i == UNKNOWN.targetTypeValue) {
                return UNKNOWN;
            }
            if (i < 0 || i >= targets.length) {
                throw new AssertionError("Unknown TargetType: " + i);
            }
            return targets[i];
        }
    }
}
