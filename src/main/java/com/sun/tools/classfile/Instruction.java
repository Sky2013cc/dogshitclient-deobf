package com.sun.tools.classfile;

import java.util.Locale;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/classfile/Instruction.class */
public class Instruction {
    private byte[] bytes;
    private int pc;

    /* loaded from: target.jar:com/sun/tools/classfile/Instruction$KindVisitor.class */
    public interface KindVisitor<R, P> {
        R visitNoOperands(Instruction instruction, P p);

        R visitArrayType(Instruction instruction, TypeKind typeKind, P p);

        R visitBranch(Instruction instruction, int i, P p);

        R visitConstantPoolRef(Instruction instruction, int i, P p);

        R visitConstantPoolRefAndValue(Instruction instruction, int i, int i2, P p);

        R visitLocal(Instruction instruction, int i, P p);

        R visitLocalAndValue(Instruction instruction, int i, int i2, P p);

        R visitLookupSwitch(Instruction instruction, int i, int i2, int[] iArr, int[] iArr2, P p);

        R visitTableSwitch(Instruction instruction, int i, int i2, int i3, int[] iArr, P p);

        R visitValue(Instruction instruction, int i, P p);

        R visitUnknown(Instruction instruction, P p);
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Instruction$Kind.class */
    public enum Kind {
        NO_OPERANDS(1),
        ATYPE(2),
        BRANCH(3),
        BRANCH_W(5),
        BYTE(2),
        CPREF(2),
        CPREF_W(3),
        CPREF_W_UBYTE(4),
        CPREF_W_UBYTE_ZERO(5),
        DYNAMIC(-1),
        LOCAL(2),
        LOCAL_BYTE(3),
        SHORT(3),
        WIDE_NO_OPERANDS(2),
        WIDE_LOCAL(4),
        WIDE_CPREF_W(4),
        WIDE_CPREF_W_SHORT(6),
        WIDE_LOCAL_SHORT(6),
        UNKNOWN(1);

        public final int length;

        Kind(int i) {
            this.length = i;
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Instruction$TypeKind.class */
    public enum TypeKind {
        T_BOOLEAN(4, Constants.IDL_BOOLEAN),
        T_CHAR(5, "char"),
        T_FLOAT(6, Constants.IDL_FLOAT),
        T_DOUBLE(7, Constants.IDL_DOUBLE),
        T_BYTE(8, "byte"),
        T_SHORT(9, Constants.IDL_SHORT),
        T_INT(10, "int"),
        T_LONG(11, Constants.IDL_INT);

        public final int value;
        public final String name;

        TypeKind(int i, String str) {
            this.value = i;
            this.name = str;
        }

        public static TypeKind get(int i) {
            switch (i) {
                case 4:
                    return T_BOOLEAN;
                case 5:
                    return T_CHAR;
                case 6:
                    return T_FLOAT;
                case 7:
                    return T_DOUBLE;
                case 8:
                    return T_BYTE;
                case 9:
                    return T_SHORT;
                case 10:
                    return T_INT;
                case 11:
                    return T_LONG;
                default:
                    return null;
            }
        }
    }

    public Instruction(byte[] bArr, int i) {
        this.bytes = bArr;
        this.pc = i;
    }

    public int getPC() {
        return this.pc;
    }

    public int getByte(int i) {
        return this.bytes[this.pc + i];
    }

    public int getUnsignedByte(int i) {
        return getByte(i) & 255;
    }

    public int getShort(int i) {
        return (getByte(i) << 8) | getUnsignedByte(i + 1);
    }

    public int getUnsignedShort(int i) {
        return getShort(i) & 65535;
    }

    public int getInt(int i) {
        return (getShort(i) << 16) | getUnsignedShort(i + 2);
    }

    public Opcode getOpcode() {
        int unsignedByte = getUnsignedByte(0);
        switch (unsignedByte) {
            case 196:
            case 254:
            case 255:
                return Opcode.get(unsignedByte, getUnsignedByte(1));
            default:
                return Opcode.get(unsignedByte);
        }
    }

    public String getMnemonic() {
        Opcode opcode = getOpcode();
        if (opcode == null) {
            return "bytecode " + getUnsignedByte(0);
        }
        return opcode.toString().toLowerCase(Locale.US);
    }

    public int length() {
        Opcode opcode = getOpcode();
        if (opcode == null) {
            return 1;
        }
        switch (opcode) {
            case TABLESWITCH:
                int align = align(this.pc + 1) - this.pc;
                return align + 12 + (4 * ((getInt(align + 8) - getInt(align + 4)) + 1));
            case LOOKUPSWITCH:
                int align2 = align(this.pc + 1) - this.pc;
                return align2 + 8 + (8 * getInt(align2 + 4));
            default:
                return opcode.kind.length;
        }
    }

    public Kind getKind() {
        Opcode opcode = getOpcode();
        return opcode != null ? opcode.kind : Kind.UNKNOWN;
    }

    public <R, P> R accept(KindVisitor<R, P> kindVisitor, P p) {
        switch (getKind()) {
            case NO_OPERANDS:
                return kindVisitor.visitNoOperands(this, p);
            case ATYPE:
                return kindVisitor.visitArrayType(this, TypeKind.get(getUnsignedByte(1)), p);
            case BRANCH:
                return kindVisitor.visitBranch(this, getShort(1), p);
            case BRANCH_W:
                return kindVisitor.visitBranch(this, getInt(1), p);
            case BYTE:
                return kindVisitor.visitValue(this, getByte(1), p);
            case CPREF:
                return kindVisitor.visitConstantPoolRef(this, getUnsignedByte(1), p);
            case CPREF_W:
                return kindVisitor.visitConstantPoolRef(this, getUnsignedShort(1), p);
            case CPREF_W_UBYTE:
            case CPREF_W_UBYTE_ZERO:
                return kindVisitor.visitConstantPoolRefAndValue(this, getUnsignedShort(1), getUnsignedByte(3), p);
            case DYNAMIC:
                switch (getOpcode()) {
                    case TABLESWITCH:
                        int align = align(this.pc + 1) - this.pc;
                        int i = getInt(align);
                        int i2 = getInt(align + 4);
                        int i3 = getInt(align + 8);
                        int[] iArr = new int[(i3 - i2) + 1];
                        for (int i4 = 0; i4 < iArr.length; i4++) {
                            iArr[i4] = getInt(align + 12 + (4 * i4));
                        }
                        return kindVisitor.visitTableSwitch(this, i, i2, i3, iArr, p);
                    case LOOKUPSWITCH:
                        int align2 = align(this.pc + 1) - this.pc;
                        int i5 = getInt(align2);
                        int i6 = getInt(align2 + 4);
                        int[] iArr2 = new int[i6];
                        int[] iArr3 = new int[i6];
                        for (int i7 = 0; i7 < i6; i7++) {
                            iArr2[i7] = getInt(align2 + 8 + (i7 * 8));
                            iArr3[i7] = getInt(align2 + 12 + (i7 * 8));
                        }
                        return kindVisitor.visitLookupSwitch(this, i5, i6, iArr2, iArr3, p);
                    default:
                        throw new IllegalStateException();
                }
            case LOCAL:
                return kindVisitor.visitLocal(this, getUnsignedByte(1), p);
            case LOCAL_BYTE:
                return kindVisitor.visitLocalAndValue(this, getUnsignedByte(1), getByte(2), p);
            case SHORT:
                return kindVisitor.visitValue(this, getShort(1), p);
            case WIDE_NO_OPERANDS:
                return kindVisitor.visitNoOperands(this, p);
            case WIDE_LOCAL:
                return kindVisitor.visitLocal(this, getUnsignedShort(2), p);
            case WIDE_CPREF_W:
                return kindVisitor.visitConstantPoolRef(this, getUnsignedShort(2), p);
            case WIDE_CPREF_W_SHORT:
                return kindVisitor.visitConstantPoolRefAndValue(this, getUnsignedShort(2), getUnsignedByte(4), p);
            case WIDE_LOCAL_SHORT:
                return kindVisitor.visitLocalAndValue(this, getUnsignedShort(2), getShort(4), p);
            case UNKNOWN:
                return kindVisitor.visitUnknown(this, p);
            default:
                throw new IllegalStateException();
        }
    }

    private static int align(int i) {
        return (i + 3) & (-4);
    }
}
