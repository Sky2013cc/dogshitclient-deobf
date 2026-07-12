package com.sun.codemodel.internal;

import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/codemodel/internal/JType.class */
public abstract class JType implements JGenerable, Comparable<JType> {
    public abstract JCodeModel owner();

    public abstract String fullName();

    public abstract String name();

    public abstract JClass array();

    public abstract JClass boxify();

    public abstract JType unboxify();

    public static JPrimitiveType parse(JCodeModel codeModel, String typeName) {
        if (typeName.equals(Constants.IDL_VOID)) {
            return codeModel.VOID;
        }
        if (typeName.equals(Constants.IDL_BOOLEAN)) {
            return codeModel.BOOLEAN;
        }
        if (typeName.equals("byte")) {
            return codeModel.BYTE;
        }
        if (typeName.equals(Constants.IDL_SHORT)) {
            return codeModel.SHORT;
        }
        if (typeName.equals("char")) {
            return codeModel.CHAR;
        }
        if (typeName.equals("int")) {
            return codeModel.INT;
        }
        if (typeName.equals(Constants.IDL_FLOAT)) {
            return codeModel.FLOAT;
        }
        if (typeName.equals(Constants.IDL_INT)) {
            return codeModel.LONG;
        }
        if (typeName.equals(Constants.IDL_DOUBLE)) {
            return codeModel.DOUBLE;
        }
        throw new IllegalArgumentException("Not a primitive type: " + typeName);
    }

    public String binaryName() {
        return fullName();
    }

    public boolean isArray() {
        return false;
    }

    public boolean isPrimitive() {
        return false;
    }

    public JType erasure() {
        return this;
    }

    public final boolean isReference() {
        return !isPrimitive();
    }

    public JType elementType() {
        throw new IllegalArgumentException("Not an array type");
    }

    public String toString() {
        return getClass().getName() + '(' + fullName() + ')';
    }

    @Override // java.lang.Comparable
    public int compareTo(JType o) {
        String rhs = o.fullName();
        boolean p = fullName().startsWith("java");
        boolean q = rhs.startsWith("java");
        if (p && !q) {
            return -1;
        }
        if (!p && q) {
            return 1;
        }
        return fullName().compareTo(rhs);
    }
}
