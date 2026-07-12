package com.sun.tools.classfile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/classfile/Type.class */
public abstract class Type {

    /* loaded from: target.jar:com/sun/tools/classfile/Type$Visitor.class */
    public interface Visitor<R, P> {
        R visitSimpleType(SimpleType simpleType, P p);

        R visitArrayType(ArrayType arrayType, P p);

        R visitMethodType(MethodType methodType, P p);

        R visitClassSigType(ClassSigType classSigType, P p);

        R visitClassType(ClassType classType, P p);

        R visitTypeParamType(TypeParamType typeParamType, P p);

        R visitWildcardType(WildcardType wildcardType, P p);
    }

    public abstract <R, D> R accept(Visitor<R, D> visitor, D d);

    protected Type() {
    }

    public boolean isObject() {
        return false;
    }

    protected static void append(StringBuilder sb, String str, List<? extends Type> list, String str2) {
        sb.append(str);
        String str3 = "";
        for (Type type : list) {
            sb.append(str3);
            sb.append(type);
            str3 = ", ";
        }
        sb.append(str2);
    }

    protected static void appendIfNotEmpty(StringBuilder sb, String str, List<? extends Type> list, String str2) {
        if (list != null && list.size() > 0) {
            append(sb, str, list, str2);
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Type$SimpleType.class */
    public static class SimpleType extends Type {
        private static final Set<String> primitiveTypes = new HashSet(Arrays.asList(Constants.IDL_BOOLEAN, "byte", "char", Constants.IDL_DOUBLE, Constants.IDL_FLOAT, "int", Constants.IDL_INT, Constants.IDL_SHORT, Constants.IDL_VOID));
        public final String name;

        public SimpleType(String str) {
            this.name = str;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitSimpleType(this, d);
        }

        public boolean isPrimitiveType() {
            return primitiveTypes.contains(this.name);
        }

        public String toString() {
            return this.name;
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Type$ArrayType.class */
    public static class ArrayType extends Type {
        public final Type elemType;

        public ArrayType(Type type) {
            this.elemType = type;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitArrayType(this, d);
        }

        public String toString() {
            return this.elemType + _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Type$MethodType.class */
    public static class MethodType extends Type {
        public final List<? extends TypeParamType> typeParamTypes;
        public final List<? extends Type> paramTypes;
        public final Type returnType;
        public final List<? extends Type> throwsTypes;

        public MethodType(List<? extends Type> list, Type type) {
            this(null, list, type, null);
        }

        public MethodType(List<? extends TypeParamType> list, List<? extends Type> list2, Type type, List<? extends Type> list3) {
            this.typeParamTypes = list;
            this.paramTypes = list2;
            this.returnType = type;
            this.throwsTypes = list3;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitMethodType(this, d);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendIfNotEmpty(sb, "<", this.typeParamTypes, "> ");
            sb.append(this.returnType);
            append(sb, " (", this.paramTypes, RuntimeConstants.SIG_ENDMETHOD);
            appendIfNotEmpty(sb, " throws ", this.throwsTypes, "");
            return sb.toString();
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Type$ClassSigType.class */
    public static class ClassSigType extends Type {
        public final List<TypeParamType> typeParamTypes;
        public final Type superclassType;
        public final List<Type> superinterfaceTypes;

        public ClassSigType(List<TypeParamType> list, Type type, List<Type> list2) {
            this.typeParamTypes = list;
            this.superclassType = type;
            this.superinterfaceTypes = list2;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitClassSigType(this, d);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendIfNotEmpty(sb, "<", this.typeParamTypes, ">");
            if (this.superclassType != null) {
                sb.append(" extends ");
                sb.append(this.superclassType);
            }
            appendIfNotEmpty(sb, " implements ", this.superinterfaceTypes, "");
            return sb.toString();
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Type$ClassType.class */
    public static class ClassType extends Type {
        public final ClassType outerType;
        public final String name;
        public final List<Type> typeArgs;

        public ClassType(ClassType classType, String str, List<Type> list) {
            this.outerType = classType;
            this.name = str;
            this.typeArgs = list;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitClassType(this, d);
        }

        public String getBinaryName() {
            if (this.outerType == null) {
                return this.name;
            }
            return this.outerType.getBinaryName() + "$" + this.name;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.outerType != null) {
                sb.append(this.outerType);
                sb.append(Constants.NAME_SEPARATOR);
            }
            sb.append(this.name);
            appendIfNotEmpty(sb, "<", this.typeArgs, ">");
            return sb.toString();
        }

        @Override // com.sun.tools.classfile.Type
        public boolean isObject() {
            return this.outerType == null && this.name.equals("java/lang/Object") && (this.typeArgs == null || this.typeArgs.isEmpty());
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Type$TypeParamType.class */
    public static class TypeParamType extends Type {
        public final String name;
        public final Type classBound;
        public final List<Type> interfaceBounds;

        public TypeParamType(String str, Type type, List<Type> list) {
            this.name = str;
            this.classBound = type;
            this.interfaceBounds = list;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitTypeParamType(this, d);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            String str = " extends ";
            if (this.classBound != null) {
                sb.append(str);
                sb.append(this.classBound);
                str = " & ";
            }
            if (this.interfaceBounds != null) {
                for (Type type : this.interfaceBounds) {
                    sb.append(str);
                    sb.append(type);
                    str = " & ";
                }
            }
            return sb.toString();
        }
    }

    /* loaded from: target.jar:com/sun/tools/classfile/Type$WildcardType.class */
    public static class WildcardType extends Type {
        public final Kind kind;
        public final Type boundType;

        /* loaded from: target.jar:com/sun/tools/classfile/Type$WildcardType$Kind.class */
        public enum Kind {
            UNBOUNDED,
            EXTENDS,
            SUPER
        }

        public WildcardType() {
            this(Kind.UNBOUNDED, null);
        }

        public WildcardType(Kind kind, Type type) {
            this.kind = kind;
            this.boundType = type;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitWildcardType(this, d);
        }

        public String toString() {
            switch (this.kind) {
                case UNBOUNDED:
                    return "?";
                case EXTENDS:
                    return "? extends " + this.boundType;
                case SUPER:
                    return "? super " + this.boundType;
                default:
                    throw new AssertionError();
            }
        }
    }
}
