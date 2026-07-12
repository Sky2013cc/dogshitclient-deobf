package com.sun.tools.javac.code;

import com.formdev.flatlaf.FlatClientProperties;
import com.sun.tools.doclint.DocLint;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Filter;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.IntersectionType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.UnionType;
import okhttp3.internal.url._UrlKt;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javac/code/Type.class */
public abstract class Type extends AnnoConstruct implements TypeMirror {
    public static final JCNoType noType = new JCNoType() { // from class: com.sun.tools.javac.code.Type.1
        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            return FlatClientProperties.TABBED_PANE_TAB_ROTATION_NONE;
        }
    };
    public static final JCNoType recoveryType = new JCNoType() { // from class: com.sun.tools.javac.code.Type.2
        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            return "recovery";
        }
    };
    public static final JCNoType stuckType = new JCNoType() { // from class: com.sun.tools.javac.code.Type.3
        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            return "stuck";
        }
    };
    public static boolean moreInfo = false;
    public Symbol.TypeSymbol tsym;

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$Visitor.class */
    public interface Visitor<R, S> {
        R visitClassType(ClassType classType, S s);

        R visitWildcardType(WildcardType wildcardType, S s);

        R visitArrayType(ArrayType arrayType, S s);

        R visitMethodType(MethodType methodType, S s);

        R visitPackageType(PackageType packageType, S s);

        R visitTypeVar(TypeVar typeVar, S s);

        R visitCapturedType(CapturedType capturedType, S s);

        R visitForAll(ForAll forAll, S s);

        R visitUndetVar(UndetVar undetVar, S s);

        R visitErrorType(ErrorType errorType, S s);

        R visitAnnotatedType(AnnotatedType annotatedType, S s);

        R visitType(Type type, S s);
    }

    public abstract TypeTag getTag();

    public boolean hasTag(TypeTag typeTag) {
        return typeTag == getTag();
    }

    public boolean isNumeric() {
        return false;
    }

    public boolean isPrimitive() {
        return false;
    }

    public boolean isPrimitiveOrVoid() {
        return false;
    }

    public boolean isReference() {
        return false;
    }

    public boolean isNullOrReference() {
        return false;
    }

    public boolean isPartial() {
        return false;
    }

    public Object constValue() {
        return null;
    }

    public boolean isFalse() {
        return false;
    }

    public boolean isTrue() {
        return false;
    }

    public Type getModelType() {
        return this;
    }

    public static List<Type> getModelTypes(List<Type> list) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            listBuffer.append(it.next().getModelType());
        }
        return listBuffer.toList();
    }

    public Type getOriginalType() {
        return this;
    }

    public <R, S> R accept(Visitor<R, S> visitor, S s) {
        return visitor.visitType(this, s);
    }

    public Type(Symbol.TypeSymbol typeSymbol) {
        this.tsym = typeSymbol;
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$Mapping.class */
    public static abstract class Mapping {
        private String name;

        public abstract Type apply(Type type);

        public Mapping(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    public Type map(Mapping mapping) {
        return this;
    }

    public static List<Type> map(List<Type> list, Mapping mapping) {
        if (list.nonEmpty()) {
            List<Type> map = map(list.tail, mapping);
            Type apply = mapping.apply(list.head);
            if (map != list.tail || apply != list.head) {
                return map.prepend(apply);
            }
        }
        return list;
    }

    public Type constType(Object obj) {
        throw new AssertionError();
    }

    public Type baseType() {
        return this;
    }

    public Type annotatedType(List<Attribute.TypeCompound> list) {
        return new AnnotatedType(list, this);
    }

    public boolean isAnnotated() {
        return false;
    }

    public Type unannotatedType() {
        return this;
    }

    @Override // com.sun.tools.javac.code.AnnoConstruct
    /* renamed from: getAnnotationMirrors */
    public List<Attribute.TypeCompound> mo409getAnnotationMirrors() {
        return List.nil();
    }

    @Override // com.sun.tools.javac.code.AnnoConstruct
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return null;
    }

    @Override // com.sun.tools.javac.code.AnnoConstruct
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> cls) {
        return (A[]) ((Annotation[]) Array.newInstance((Class<?>) cls, 0));
    }

    public static List<Type> baseTypes(List<Type> list) {
        if (list.nonEmpty()) {
            Type baseType = list.head.baseType();
            List<Type> baseTypes = baseTypes(list.tail);
            if (baseType != list.head || baseTypes != list.tail) {
                return baseTypes.prepend(baseType);
            }
        }
        return list;
    }

    public String toString() {
        String name = (this.tsym == null || this.tsym.name == null) ? "<none>" : this.tsym.name.toString();
        if (moreInfo && hasTag(TypeTag.TYPEVAR)) {
            name = name + hashCode();
        }
        return name;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String toString(List<Type> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.head.toString());
        List list2 = list.tail;
        while (true) {
            List list3 = list2;
            if (list3.nonEmpty()) {
                sb.append(DocLint.TAGS_SEPARATOR).append(((Type) list3.head).toString());
                list2 = list3.tail;
            } else {
                return sb.toString();
            }
        }
    }

    public String stringValue() {
        return Assert.checkNonNull(constValue()).toString();
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String argtypes(boolean z) {
        List<Type> m456getParameterTypes = m456getParameterTypes();
        if (!z) {
            return m456getParameterTypes.toString();
        }
        StringBuilder sb = new StringBuilder();
        while (m456getParameterTypes.tail.nonEmpty()) {
            sb.append(m456getParameterTypes.head);
            m456getParameterTypes = m456getParameterTypes.tail;
            sb.append(',');
        }
        if (m456getParameterTypes.head.unannotatedType().hasTag(TypeTag.ARRAY)) {
            sb.append(((ArrayType) m456getParameterTypes.head.unannotatedType()).elemtype);
            if (m456getParameterTypes.head.mo409getAnnotationMirrors().nonEmpty()) {
                sb.append(m456getParameterTypes.head.mo409getAnnotationMirrors());
            }
            sb.append("...");
        } else {
            sb.append(m456getParameterTypes.head);
        }
        return sb.toString();
    }

    /* renamed from: getTypeArguments */
    public List<Type> mo451getTypeArguments() {
        return List.nil();
    }

    /* renamed from: getEnclosingType */
    public Type mo452getEnclosingType() {
        return null;
    }

    /* renamed from: getParameterTypes */
    public List<Type> m456getParameterTypes() {
        return List.nil();
    }

    /* renamed from: getReturnType */
    public Type m457getReturnType() {
        return null;
    }

    /* renamed from: getReceiverType */
    public Type m455getReceiverType() {
        return null;
    }

    /* renamed from: getThrownTypes */
    public List<Type> m454getThrownTypes() {
        return List.nil();
    }

    /* renamed from: getUpperBound */
    public Type m460getUpperBound() {
        return null;
    }

    /* renamed from: getLowerBound */
    public Type m459getLowerBound() {
        return null;
    }

    public List<Type> allparams() {
        return List.nil();
    }

    public boolean isErroneous() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isErroneous(List<Type> list) {
        List list2 = list;
        while (true) {
            List list3 = list2;
            if (list3.nonEmpty()) {
                if (((Type) list3.head).isErroneous()) {
                    return true;
                }
                list2 = list3.tail;
            } else {
                return false;
            }
        }
    }

    public boolean isParameterized() {
        return false;
    }

    public boolean isRaw() {
        return false;
    }

    public boolean isCompound() {
        return this.tsym.completer == null && (this.tsym.flags() & 16777216) != 0;
    }

    public boolean isIntersection() {
        return false;
    }

    public boolean isUnion() {
        return false;
    }

    public boolean isInterface() {
        return (this.tsym.flags() & 512) != 0;
    }

    public boolean isFinal() {
        return (this.tsym.flags() & 16) != 0;
    }

    public boolean contains(Type type) {
        return type == this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean contains(List<Type> list, Type type) {
        List list2 = list;
        while (true) {
            List list3 = list2;
            if (list3.tail != null) {
                if (((Type) list3.head).contains(type)) {
                    return true;
                }
                list2 = list3.tail;
            } else {
                return false;
            }
        }
    }

    public boolean containsAny(List<Type> list) {
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            if (contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAny(List<Type> list, List<Type> list2) {
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().containsAny(list2)) {
                return true;
            }
        }
        return false;
    }

    public static List<Type> filter(List<Type> list, Filter<Type> filter) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            Type next = it.next();
            if (filter.accepts(next)) {
                listBuffer.append(next);
            }
        }
        return listBuffer.toList();
    }

    public boolean isSuperBound() {
        return false;
    }

    public boolean isExtendsBound() {
        return false;
    }

    public boolean isUnbound() {
        return false;
    }

    public Type withTypeVar(Type type) {
        return this;
    }

    public MethodType asMethodType() {
        throw new AssertionError();
    }

    public void complete() {
    }

    /* renamed from: asElement */
    public Symbol.TypeSymbol m447asElement() {
        return this.tsym;
    }

    public TypeKind getKind() {
        return TypeKind.OTHER;
    }

    public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
        throw new AssertionError();
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$JCPrimitiveType.class */
    public static class JCPrimitiveType extends Type implements PrimitiveType {
        TypeTag tag;

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        public JCPrimitiveType(TypeTag typeTag, Symbol.TypeSymbol typeSymbol) {
            super(typeSymbol);
            this.tag = typeTag;
            Assert.check(typeTag.isPrimitive);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNumeric() {
            return this.tag != TypeTag.BOOLEAN;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPrimitive() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return this.tag;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPrimitiveOrVoid() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public Type constType(final Object obj) {
            return new JCPrimitiveType(this.tag, this.tsym) { // from class: com.sun.tools.javac.code.Type.JCPrimitiveType.1
                @Override // com.sun.tools.javac.code.Type.JCPrimitiveType, com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
                /* renamed from: getAnnotationMirrors */
                public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
                    return super.mo409getAnnotationMirrors();
                }

                @Override // com.sun.tools.javac.code.Type
                public Object constValue() {
                    return obj;
                }

                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return this.tsym.type;
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type
        public String stringValue() {
            Object checkNonNull = Assert.checkNonNull(constValue());
            if (this.tag == TypeTag.BOOLEAN) {
                return ((Integer) checkNonNull).intValue() == 0 ? Constants.FALSE : Constants.TRUE;
            }
            if (this.tag == TypeTag.CHAR) {
                return String.valueOf((char) ((Integer) checkNonNull).intValue());
            }
            return checkNonNull.toString();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isFalse() {
            return this.tag == TypeTag.BOOLEAN && constValue() != null && ((Integer) constValue()).intValue() == 0;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isTrue() {
            return (this.tag != TypeTag.BOOLEAN || constValue() == null || ((Integer) constValue()).intValue() == 0) ? false : true;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitPrimitive(this, p);
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            switch (this.tag) {
                case BYTE:
                    return TypeKind.BYTE;
                case CHAR:
                    return TypeKind.CHAR;
                case SHORT:
                    return TypeKind.SHORT;
                case INT:
                    return TypeKind.INT;
                case LONG:
                    return TypeKind.LONG;
                case FLOAT:
                    return TypeKind.FLOAT;
                case DOUBLE:
                    return TypeKind.DOUBLE;
                case BOOLEAN:
                    return TypeKind.BOOLEAN;
                default:
                    throw new AssertionError();
            }
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$WildcardType.class */
    public static class WildcardType extends Type implements javax.lang.model.type.WildcardType {
        public Type type;
        public BoundKind kind;
        public TypeVar bound;
        boolean isPrintingBound;

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitWildcardType(this, s);
        }

        public WildcardType(Type type, BoundKind boundKind, Symbol.TypeSymbol typeSymbol) {
            super(typeSymbol);
            this.isPrintingBound = false;
            this.type = (Type) Assert.checkNonNull(type);
            this.kind = boundKind;
        }

        public WildcardType(WildcardType wildcardType, TypeVar typeVar) {
            this(wildcardType.type, wildcardType.kind, wildcardType.tsym, typeVar);
        }

        public WildcardType(Type type, BoundKind boundKind, Symbol.TypeSymbol typeSymbol, TypeVar typeVar) {
            this(type, boundKind, typeSymbol);
            this.bound = typeVar;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.WILDCARD;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            return this.kind != BoundKind.UNBOUND && this.type.contains(type);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isSuperBound() {
            return this.kind == BoundKind.SUPER || this.kind == BoundKind.UNBOUND;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isExtendsBound() {
            return this.kind == BoundKind.EXTENDS || this.kind == BoundKind.UNBOUND;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isUnbound() {
            return this.kind == BoundKind.UNBOUND;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public Type withTypeVar(Type type) {
            if (this.bound == type) {
                return this;
            }
            this.bound = (TypeVar) type;
            return this;
        }

        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.kind.toString());
            if (this.kind != BoundKind.UNBOUND) {
                sb.append(this.type);
            }
            if (moreInfo && this.bound != null && !this.isPrintingBound) {
                try {
                    this.isPrintingBound = true;
                    sb.append("{:").append(this.bound.bound).append(":}");
                } finally {
                    this.isPrintingBound = false;
                }
            }
            return sb.toString();
        }

        @Override // com.sun.tools.javac.code.Type
        public Type map(Mapping mapping) {
            Type type = this.type;
            if (type != null) {
                type = mapping.apply(type);
            }
            if (type == this.type) {
                return this;
            }
            return new WildcardType(type, this.kind, this.tsym, this.bound);
        }

        /* renamed from: getExtendsBound, reason: merged with bridge method [inline-methods] */
        public Type m463getExtendsBound() {
            if (this.kind == BoundKind.EXTENDS) {
                return this.type;
            }
            return null;
        }

        /* renamed from: getSuperBound, reason: merged with bridge method [inline-methods] */
        public Type m462getSuperBound() {
            if (this.kind == BoundKind.SUPER) {
                return this.type;
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.WILDCARD;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitWildcard(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$ClassType.class */
    public static class ClassType extends Type implements DeclaredType {
        private Type outer_field;
        public List<Type> typarams_field;
        public List<Type> allparams_field;
        public Type supertype_field;
        public List<Type> interfaces_field;
        public List<Type> all_interfaces_field;
        int rank_field;

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        public /* bridge */ /* synthetic */ Element asElement() {
            return super.m447asElement();
        }

        public ClassType(Type type, List<Type> list, Symbol.TypeSymbol typeSymbol) {
            super(typeSymbol);
            this.rank_field = -1;
            this.outer_field = type;
            this.typarams_field = list;
            this.allparams_field = null;
            this.supertype_field = null;
            this.interfaces_field = null;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.CLASS;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitClassType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public Type constType(final Object obj) {
            return new ClassType(mo452getEnclosingType(), this.typarams_field, this.tsym) { // from class: com.sun.tools.javac.code.Type.ClassType.1
                @Override // com.sun.tools.javac.code.Type.ClassType
                /* renamed from: getTypeArguments */
                public /* bridge */ /* synthetic */ java.util.List mo451getTypeArguments() {
                    return super.mo451getTypeArguments();
                }

                @Override // com.sun.tools.javac.code.Type.ClassType
                /* renamed from: getEnclosingType */
                public /* bridge */ /* synthetic */ TypeMirror mo452getEnclosingType() {
                    return super.mo452getEnclosingType();
                }

                @Override // com.sun.tools.javac.code.Type.ClassType
                public /* bridge */ /* synthetic */ Element asElement() {
                    return super.asElement();
                }

                @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
                /* renamed from: getAnnotationMirrors */
                public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
                    return super.mo409getAnnotationMirrors();
                }

                @Override // com.sun.tools.javac.code.Type
                public Object constValue() {
                    return obj;
                }

                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return this.tsym.type;
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (mo452getEnclosingType().hasTag(TypeTag.CLASS) && this.tsym.owner.kind == 2) {
                sb.append(mo452getEnclosingType().toString());
                sb.append(sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR);
                sb.append(className(this.tsym, false));
            } else {
                sb.append(className(this.tsym, true));
            }
            if (mo451getTypeArguments().nonEmpty()) {
                sb.append('<');
                sb.append(mo451getTypeArguments().toString());
                sb.append(">");
            }
            return sb.toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private String className(Symbol symbol, boolean z) {
            String localizedString;
            if (symbol.name.isEmpty() && (symbol.flags() & 16777216) != 0) {
                StringBuilder sb = new StringBuilder(this.supertype_field.toString());
                List list = this.interfaces_field;
                while (true) {
                    List list2 = list;
                    if (list2.nonEmpty()) {
                        sb.append("&");
                        sb.append(((Type) list2.head).toString());
                        list = list2.tail;
                    } else {
                        return sb.toString();
                    }
                }
            } else {
                if (symbol.name.isEmpty()) {
                    ClassType classType = (ClassType) this.tsym.type.unannotatedType();
                    if (classType == null) {
                        localizedString = Log.getLocalizedString("anonymous.class", null);
                    } else if (classType.interfaces_field != null && classType.interfaces_field.nonEmpty()) {
                        localizedString = Log.getLocalizedString("anonymous.class", classType.interfaces_field.head);
                    } else {
                        localizedString = Log.getLocalizedString("anonymous.class", classType.supertype_field);
                    }
                    if (moreInfo) {
                        localizedString = localizedString + String.valueOf(symbol.hashCode());
                    }
                    return localizedString;
                }
                if (z) {
                    return symbol.m437getQualifiedName().toString();
                }
                return symbol.name.toString();
            }
        }

        @Override // 
        /* renamed from: getTypeArguments, reason: merged with bridge method [inline-methods] */
        public List<Type> mo451getTypeArguments() {
            if (this.typarams_field == null) {
                complete();
                if (this.typarams_field == null) {
                    this.typarams_field = List.nil();
                }
            }
            return this.typarams_field;
        }

        public boolean hasErasedSupertypes() {
            return isRaw();
        }

        @Override // 
        /* renamed from: getEnclosingType, reason: merged with bridge method [inline-methods] */
        public Type mo452getEnclosingType() {
            return this.outer_field;
        }

        public void setEnclosingType(Type type) {
            this.outer_field = type;
        }

        @Override // com.sun.tools.javac.code.Type
        public List<Type> allparams() {
            if (this.allparams_field == null) {
                this.allparams_field = mo451getTypeArguments().prependList(mo452getEnclosingType().allparams());
            }
            return this.allparams_field;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return mo452getEnclosingType().isErroneous() || isErroneous(mo451getTypeArguments()) || (this != this.tsym.type.unannotatedType() && this.tsym.type.isErroneous());
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isParameterized() {
            return allparams().tail != null;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isRaw() {
            return this != this.tsym.type && this.tsym.type.allparams().nonEmpty() && allparams().isEmpty();
        }

        @Override // com.sun.tools.javac.code.Type
        public Type map(Mapping mapping) {
            Type mo452getEnclosingType = mo452getEnclosingType();
            Type apply = mapping.apply(mo452getEnclosingType);
            List<Type> mo451getTypeArguments = mo451getTypeArguments();
            List<Type> map = map(mo451getTypeArguments, mapping);
            return (apply == mo452getEnclosingType && map == mo451getTypeArguments) ? this : new ClassType(apply, map, this.tsym);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            return type == this || (isParameterized() && (mo452getEnclosingType().contains(type) || contains(mo451getTypeArguments(), type))) || (isCompound() && (this.supertype_field.contains(type) || contains(this.interfaces_field, type)));
        }

        @Override // com.sun.tools.javac.code.Type
        public void complete() {
            if (this.tsym.completer != null) {
                this.tsym.complete();
            }
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.DECLARED;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitDeclared(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$ErasedClassType.class */
    public static class ErasedClassType extends ClassType {
        public ErasedClassType(Type type, Symbol.TypeSymbol typeSymbol) {
            super(type, List.nil(), typeSymbol);
        }

        @Override // com.sun.tools.javac.code.Type.ClassType
        public boolean hasErasedSupertypes() {
            return true;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$UnionClassType.class */
    public static class UnionClassType extends ClassType implements UnionType {
        final List<? extends Type> alternatives_field;

        public UnionClassType(ClassType classType, List<? extends Type> list) {
            super(classType.outer_field, classType.typarams_field, classType.tsym);
            this.allparams_field = classType.allparams_field;
            this.supertype_field = classType.supertype_field;
            this.interfaces_field = classType.interfaces_field;
            this.all_interfaces_field = classType.interfaces_field;
            this.alternatives_field = list;
        }

        public Type getLub() {
            return this.tsym.type;
        }

        public java.util.List<? extends TypeMirror> getAlternatives() {
            return Collections.unmodifiableList(this.alternatives_field);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isUnion() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.UNION;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitUnion(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$IntersectionClassType.class */
    public static class IntersectionClassType extends ClassType implements IntersectionType {
        public boolean allInterfaces;

        public IntersectionClassType(List<Type> list, Symbol.ClassSymbol classSymbol, boolean z) {
            super(Type.noType, List.nil(), classSymbol);
            this.allInterfaces = z;
            Assert.check((classSymbol.flags() & 16777216) != 0);
            this.supertype_field = list.head;
            this.interfaces_field = list.tail;
            Assert.check((this.supertype_field.tsym.completer == null && this.supertype_field.isInterface()) ? false : true, this.supertype_field);
        }

        public java.util.List<? extends TypeMirror> getBounds() {
            return Collections.unmodifiableList(getExplicitComponents());
        }

        public List<Type> getComponents() {
            return this.interfaces_field.prepend(this.supertype_field);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isIntersection() {
            return true;
        }

        public List<Type> getExplicitComponents() {
            return this.allInterfaces ? this.interfaces_field : getComponents();
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.INTERSECTION;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitIntersection(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$ArrayType.class */
    public static class ArrayType extends Type implements javax.lang.model.type.ArrayType {
        public Type elemtype;

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        public ArrayType(Type type, Symbol.TypeSymbol typeSymbol) {
            super(typeSymbol);
            this.elemtype = type;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.ARRAY;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitArrayType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            return this.elemtype + _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof ArrayType) && this.elemtype.equals(((ArrayType) obj).elemtype));
        }

        @Override // com.sun.tools.javac.code.Type
        public int hashCode() {
            return (TypeTag.ARRAY.ordinal() << 5) + this.elemtype.hashCode();
        }

        public boolean isVarargs() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type
        public List<Type> allparams() {
            return this.elemtype.allparams();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return this.elemtype.isErroneous();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isParameterized() {
            return this.elemtype.isParameterized();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isRaw() {
            return this.elemtype.isRaw();
        }

        public ArrayType makeVarargs() {
            return new ArrayType(this.elemtype, this.tsym) { // from class: com.sun.tools.javac.code.Type.ArrayType.1
                @Override // com.sun.tools.javac.code.Type.ArrayType
                /* renamed from: getComponentType */
                public /* bridge */ /* synthetic */ TypeMirror mo450getComponentType() {
                    return super.mo450getComponentType();
                }

                @Override // com.sun.tools.javac.code.Type.ArrayType, com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
                /* renamed from: getAnnotationMirrors */
                public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
                    return super.mo409getAnnotationMirrors();
                }

                @Override // com.sun.tools.javac.code.Type.ArrayType
                public boolean isVarargs() {
                    return true;
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type
        public Type map(Mapping mapping) {
            Type apply = mapping.apply(this.elemtype);
            return apply == this.elemtype ? this : new ArrayType(apply, this.tsym);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            return type == this || this.elemtype.contains(type);
        }

        @Override // com.sun.tools.javac.code.Type
        public void complete() {
            this.elemtype.complete();
        }

        @Override // 
        /* renamed from: getComponentType, reason: merged with bridge method [inline-methods] */
        public Type mo450getComponentType() {
            return this.elemtype;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.ARRAY;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitArray(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$MethodType.class */
    public static class MethodType extends Type implements ExecutableType {
        public List<Type> argtypes;
        public Type restype;
        public List<Type> thrown;
        public Type recvtype;

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        public MethodType(List<Type> list, Type type, List<Type> list2, Symbol.TypeSymbol typeSymbol) {
            super(typeSymbol);
            this.argtypes = list;
            this.restype = type;
            this.thrown = list2;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.METHOD;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitMethodType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            return RuntimeConstants.SIG_METHOD + this.argtypes + RuntimeConstants.SIG_ENDMETHOD + this.restype;
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getParameterTypes, reason: merged with bridge method [inline-methods] */
        public List<Type> m456getParameterTypes() {
            return this.argtypes;
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getReturnType, reason: merged with bridge method [inline-methods] */
        public Type m457getReturnType() {
            return this.restype;
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getReceiverType, reason: merged with bridge method [inline-methods] */
        public Type m455getReceiverType() {
            return this.recvtype;
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getThrownTypes, reason: merged with bridge method [inline-methods] */
        public List<Type> m454getThrownTypes() {
            return this.thrown;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return isErroneous(this.argtypes) || (this.restype != null && this.restype.isErroneous());
        }

        @Override // com.sun.tools.javac.code.Type
        public Type map(Mapping mapping) {
            List<Type> map = map(this.argtypes, mapping);
            Type apply = mapping.apply(this.restype);
            List<Type> map2 = map(this.thrown, mapping);
            if (map == this.argtypes && apply == this.restype && map2 == this.thrown) {
                return this;
            }
            return new MethodType(map, apply, map2, this.tsym);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            return type == this || contains(this.argtypes, type) || this.restype.contains(type) || contains(this.thrown, type);
        }

        @Override // com.sun.tools.javac.code.Type
        public MethodType asMethodType() {
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Type
        public void complete() {
            List list = this.argtypes;
            while (true) {
                List list2 = list;
                if (!list2.nonEmpty()) {
                    break;
                }
                ((Type) list2.head).complete();
                list = list2.tail;
            }
            this.restype.complete();
            this.recvtype.complete();
            List list3 = this.thrown;
            while (true) {
                List list4 = list3;
                if (list4.nonEmpty()) {
                    ((Type) list4.head).complete();
                    list3 = list4.tail;
                } else {
                    return;
                }
            }
        }

        /* renamed from: getTypeVariables, reason: merged with bridge method [inline-methods] */
        public List<TypeVar> m458getTypeVariables() {
            return List.nil();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: asElement */
        public Symbol.TypeSymbol m447asElement() {
            return null;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.EXECUTABLE;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitExecutable(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$PackageType.class */
    public static class PackageType extends Type implements NoType {
        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PackageType(Symbol.TypeSymbol typeSymbol) {
            super(typeSymbol);
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.PACKAGE;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitPackageType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            return this.tsym.m437getQualifiedName().toString();
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.PACKAGE;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitNoType(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$TypeVar.class */
    public static class TypeVar extends Type implements TypeVariable {
        public Type bound;
        public Type lower;
        int rank_field;

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        public /* bridge */ /* synthetic */ Element asElement() {
            return super.m447asElement();
        }

        public TypeVar(Name name, Symbol symbol, Type type) {
            super(null);
            this.bound = null;
            this.rank_field = -1;
            this.tsym = new Symbol.TypeVariableSymbol(0L, name, this, symbol);
            this.lower = type;
        }

        public TypeVar(Symbol.TypeSymbol typeSymbol, Type type, Type type2) {
            super(typeSymbol);
            this.bound = null;
            this.rank_field = -1;
            this.bound = type;
            this.lower = type2;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.TYPEVAR;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitTypeVar(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getUpperBound, reason: merged with bridge method [inline-methods] */
        public Type m460getUpperBound() {
            if ((this.bound == null || this.bound.hasTag(TypeTag.NONE)) && this != this.tsym.type) {
                this.bound = this.tsym.type.m460getUpperBound();
            }
            return this.bound;
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getLowerBound, reason: merged with bridge method [inline-methods] */
        public Type m459getLowerBound() {
            return this.lower;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.TYPEVAR;
        }

        public boolean isCaptured() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitTypeVariable(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$CapturedType.class */
    public static class CapturedType extends TypeVar {
        public WildcardType wildcard;

        public CapturedType(Name name, Symbol symbol, Type type, Type type2, WildcardType wildcardType) {
            super(name, symbol, type2);
            this.lower = (Type) Assert.checkNonNull(type2);
            this.bound = type;
            this.wildcard = wildcardType;
        }

        @Override // com.sun.tools.javac.code.Type.TypeVar, com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitCapturedType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type.TypeVar
        public boolean isCaptured() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            return "capture#" + ((hashCode() & 4294967295L) % 997) + " of " + this.wildcard;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$DelegatedType.class */
    public static abstract class DelegatedType extends Type {
        public Type qtype;
        public TypeTag tag;

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        public DelegatedType(TypeTag typeTag, Type type) {
            super(type.tsym);
            this.tag = typeTag;
            this.qtype = type;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return this.tag;
        }

        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            return this.qtype.toString();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getTypeArguments */
        public List<Type> mo451getTypeArguments() {
            return this.qtype.mo451getTypeArguments();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getEnclosingType */
        public Type mo452getEnclosingType() {
            return this.qtype.mo452getEnclosingType();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getParameterTypes */
        public List<Type> m456getParameterTypes() {
            return this.qtype.m456getParameterTypes();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getReturnType */
        public Type m457getReturnType() {
            return this.qtype.m457getReturnType();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getReceiverType */
        public Type m455getReceiverType() {
            return this.qtype.m455getReceiverType();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getThrownTypes */
        public List<Type> m454getThrownTypes() {
            return this.qtype.m454getThrownTypes();
        }

        @Override // com.sun.tools.javac.code.Type
        public List<Type> allparams() {
            return this.qtype.allparams();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getUpperBound */
        public Type m460getUpperBound() {
            return this.qtype.m460getUpperBound();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return this.qtype.isErroneous();
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$ForAll.class */
    public static class ForAll extends DelegatedType implements ExecutableType {
        public List<Type> tvars;

        public /* bridge */ /* synthetic */ java.util.List getThrownTypes() {
            return super.m454getThrownTypes();
        }

        public /* bridge */ /* synthetic */ TypeMirror getReceiverType() {
            return super.m455getReceiverType();
        }

        public /* bridge */ /* synthetic */ java.util.List getParameterTypes() {
            return super.m456getParameterTypes();
        }

        public /* bridge */ /* synthetic */ TypeMirror getReturnType() {
            return super.m457getReturnType();
        }

        public ForAll(List<Type> list, Type type) {
            super(TypeTag.FORALL, (MethodType) type);
            this.tvars = list;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitForAll(this, s);
        }

        @Override // com.sun.tools.javac.code.Type.DelegatedType, com.sun.tools.javac.code.Type
        public String toString() {
            return "<" + this.tvars + ">" + this.qtype;
        }

        @Override // com.sun.tools.javac.code.Type.DelegatedType, com.sun.tools.javac.code.Type
        /* renamed from: getTypeArguments */
        public List<Type> mo451getTypeArguments() {
            return this.tvars;
        }

        @Override // com.sun.tools.javac.code.Type.DelegatedType, com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return this.qtype.isErroneous();
        }

        @Override // com.sun.tools.javac.code.Type
        public Type map(Mapping mapping) {
            return mapping.apply(this.qtype);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            return this.qtype.contains(type);
        }

        @Override // com.sun.tools.javac.code.Type
        public MethodType asMethodType() {
            return (MethodType) this.qtype;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Type
        public void complete() {
            List list = this.tvars;
            while (true) {
                List list2 = list;
                if (list2.nonEmpty()) {
                    ((TypeVar) list2.head).bound.complete();
                    list = list2.tail;
                } else {
                    this.qtype.complete();
                    return;
                }
            }
        }

        /* renamed from: getTypeVariables, reason: merged with bridge method [inline-methods] */
        public List<TypeVar> m453getTypeVariables() {
            return List.convert(TypeVar.class, mo451getTypeArguments());
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.EXECUTABLE;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitExecutable(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$UndetVar.class */
    public static class UndetVar extends DelegatedType {
        protected Map<InferenceBound, List<Type>> bounds;
        public Type inst;
        public int declaredCount;
        public UndetVarListener listener;
        Mapping toTypeVarMap;

        /* loaded from: target.jar:com/sun/tools/javac/code/Type$UndetVar$InferenceBound.class */
        public enum InferenceBound {
            UPPER { // from class: com.sun.tools.javac.code.Type.UndetVar.InferenceBound.1
                @Override // com.sun.tools.javac.code.Type.UndetVar.InferenceBound
                public InferenceBound complement() {
                    return LOWER;
                }
            },
            LOWER { // from class: com.sun.tools.javac.code.Type.UndetVar.InferenceBound.2
                @Override // com.sun.tools.javac.code.Type.UndetVar.InferenceBound
                public InferenceBound complement() {
                    return UPPER;
                }
            },
            EQ { // from class: com.sun.tools.javac.code.Type.UndetVar.InferenceBound.3
                @Override // com.sun.tools.javac.code.Type.UndetVar.InferenceBound
                public InferenceBound complement() {
                    return EQ;
                }
            };

            public abstract InferenceBound complement();
        }

        /* loaded from: target.jar:com/sun/tools/javac/code/Type$UndetVar$UndetVarListener.class */
        public interface UndetVarListener {
            void varChanged(UndetVar undetVar, Set<InferenceBound> set);
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitUndetVar(this, s);
        }

        public UndetVar(TypeVar typeVar, Types types) {
            super(TypeTag.UNDETVAR, typeVar);
            this.inst = null;
            this.listener = null;
            this.toTypeVarMap = new Mapping("toTypeVarMap") { // from class: com.sun.tools.javac.code.Type.UndetVar.1
                @Override // com.sun.tools.javac.code.Type.Mapping
                public Type apply(Type type) {
                    if (type.hasTag(TypeTag.UNDETVAR)) {
                        UndetVar undetVar = (UndetVar) type;
                        return undetVar.inst != null ? undetVar.inst : undetVar.qtype;
                    }
                    return type.map(this);
                }
            };
            this.bounds = new EnumMap(InferenceBound.class);
            List<Type> bounds = types.getBounds(typeVar);
            this.declaredCount = bounds.length();
            this.bounds.put(InferenceBound.UPPER, bounds);
            this.bounds.put(InferenceBound.LOWER, List.nil());
            this.bounds.put(InferenceBound.EQ, List.nil());
        }

        @Override // com.sun.tools.javac.code.Type.DelegatedType, com.sun.tools.javac.code.Type
        public String toString() {
            return this.inst == null ? this.qtype + "?" : this.inst.toString();
        }

        public String debugString() {
            String str = "inference var = " + this.qtype + "\n";
            if (this.inst != null) {
                str = str + "inst = " + this.inst + '\n';
            }
            for (InferenceBound inferenceBound : InferenceBound.values()) {
                List<Type> list = this.bounds.get(inferenceBound);
                if (list.size() > 0) {
                    str = str + inferenceBound + " = " + list + '\n';
                }
            }
            return str;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPartial() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public Type baseType() {
            return this.inst == null ? this : this.inst.baseType();
        }

        public List<Type> getBounds(InferenceBound... inferenceBoundArr) {
            ListBuffer listBuffer = new ListBuffer();
            for (InferenceBound inferenceBound : inferenceBoundArr) {
                listBuffer.appendList(this.bounds.get(inferenceBound));
            }
            return listBuffer.toList();
        }

        public List<Type> getDeclaredBounds() {
            ListBuffer listBuffer = new ListBuffer();
            int i = 0;
            Iterator<Type> it = getBounds(InferenceBound.UPPER).iterator();
            while (it.hasNext()) {
                Type next = it.next();
                int i2 = i;
                i++;
                if (i2 == this.declaredCount) {
                    break;
                }
                listBuffer.append(next);
            }
            return listBuffer.toList();
        }

        public void setBounds(InferenceBound inferenceBound, List<Type> list) {
            this.bounds.put(inferenceBound, list);
        }

        public final void addBound(InferenceBound inferenceBound, Type type, Types types) {
            addBound(inferenceBound, type, types, false);
        }

        protected void addBound(InferenceBound inferenceBound, Type type, Types types, boolean z) {
            Type baseType = this.toTypeVarMap.apply(type).baseType();
            List<Type> list = this.bounds.get(inferenceBound);
            Iterator<Type> it = list.iterator();
            while (it.hasNext()) {
                if (types.isSameType(it.next(), baseType, true) || type == this.qtype) {
                    return;
                }
            }
            this.bounds.put(inferenceBound, list.prepend(baseType));
            notifyChange(EnumSet.of(inferenceBound));
        }

        public void substBounds(List<Type> list, List<Type> list2, Types types) {
            List<Type> diff = list.diff(list2);
            if (diff.isEmpty()) {
                return;
            }
            final EnumSet<InferenceBound> noneOf = EnumSet.noneOf(InferenceBound.class);
            UndetVarListener undetVarListener = this.listener;
            try {
                this.listener = new UndetVarListener() { // from class: com.sun.tools.javac.code.Type.UndetVar.2
                    @Override // com.sun.tools.javac.code.Type.UndetVar.UndetVarListener
                    public void varChanged(UndetVar undetVar, Set<InferenceBound> set) {
                        noneOf.addAll(set);
                    }
                };
                for (Map.Entry<InferenceBound, List<Type>> entry : this.bounds.entrySet()) {
                    InferenceBound key = entry.getKey();
                    List<Type> value = entry.getValue();
                    ListBuffer listBuffer = new ListBuffer();
                    ListBuffer listBuffer2 = new ListBuffer();
                    Iterator<Type> it = value.iterator();
                    while (it.hasNext()) {
                        Type next = it.next();
                        if (!next.containsAny(diff)) {
                            listBuffer.append(next);
                        } else {
                            listBuffer2.append(next);
                        }
                    }
                    this.bounds.put(key, listBuffer.toList());
                    Iterator it2 = listBuffer2.iterator();
                    while (it2.hasNext()) {
                        addBound(key, types.subst((Type) it2.next(), list, list2), types, true);
                    }
                }
            } finally {
                this.listener = undetVarListener;
                if (!noneOf.isEmpty()) {
                    notifyChange(noneOf);
                }
            }
        }

        private void notifyChange(EnumSet<InferenceBound> enumSet) {
            if (this.listener != null) {
                this.listener.varChanged(this, enumSet);
            }
        }

        public boolean isCaptured() {
            return false;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$CapturedUndetVar.class */
    public static class CapturedUndetVar extends UndetVar {
        public CapturedUndetVar(CapturedType capturedType, Types types) {
            super(capturedType, types);
            if (!capturedType.lower.hasTag(TypeTag.BOT)) {
                this.bounds.put(UndetVar.InferenceBound.LOWER, List.of(capturedType.lower));
            }
        }

        @Override // com.sun.tools.javac.code.Type.UndetVar
        public void addBound(UndetVar.InferenceBound inferenceBound, Type type, Types types, boolean z) {
            if (z) {
                super.addBound(inferenceBound, type, types, z);
            }
        }

        @Override // com.sun.tools.javac.code.Type.UndetVar
        public boolean isCaptured() {
            return true;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$JCNoType.class */
    public static class JCNoType extends Type implements NoType {
        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        public JCNoType() {
            super(null);
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.NONE;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.NONE;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitNoType(this, p);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return false;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$JCVoidType.class */
    public static class JCVoidType extends Type implements NoType {
        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        public JCVoidType() {
            super(null);
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.VOID;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.VOID;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitNoType(this, p);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPrimitiveOrVoid() {
            return true;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$BottomType.class */
    static class BottomType extends Type implements NullType {
        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        public BottomType() {
            super(null);
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.BOT;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.NULL;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitNull(this, p);
        }

        @Override // com.sun.tools.javac.code.Type
        public Type constType(Object obj) {
            return this;
        }

        @Override // com.sun.tools.javac.code.Type
        public String stringValue() {
            return "null";
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$ErrorType.class */
    public static class ErrorType extends ClassType implements javax.lang.model.type.ErrorType {
        private Type originalType;

        public ErrorType(Type type, Symbol.TypeSymbol typeSymbol) {
            super(noType, List.nil(), null);
            this.originalType = null;
            this.tsym = typeSymbol;
            this.originalType = type == null ? noType : type;
        }

        public ErrorType(Symbol.ClassSymbol classSymbol, Type type) {
            this(type, classSymbol);
            classSymbol.type = this;
            classSymbol.kind = 63;
            classSymbol.members_field = new Scope.ErrorScope(classSymbol);
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.ERROR;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPartial() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public boolean isReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        public ErrorType(Name name, Symbol.TypeSymbol typeSymbol, Type type) {
            this(new Symbol.ClassSymbol(1073741833L, name, null, typeSymbol), type);
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitErrorType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public Type constType(Object obj) {
            return this;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType
        /* renamed from: getEnclosingType */
        public Type mo452getEnclosingType() {
            return this;
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getReturnType */
        public Type m457getReturnType() {
            return this;
        }

        public Type asSub(Symbol symbol) {
            return this;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public Type map(Mapping mapping) {
            return this;
        }

        public boolean isGenType(Type type) {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isInterface() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public List<Type> allparams() {
            return List.nil();
        }

        @Override // com.sun.tools.javac.code.Type.ClassType
        /* renamed from: getTypeArguments */
        public List<Type> mo451getTypeArguments() {
            return List.nil();
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return TypeKind.ERROR;
        }

        @Override // com.sun.tools.javac.code.Type
        public Type getOriginalType() {
            return this.originalType;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitError(this, p);
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$AnnotatedType.class */
    public static class AnnotatedType extends Type implements javax.lang.model.type.ArrayType, DeclaredType, PrimitiveType, TypeVariable, javax.lang.model.type.WildcardType {
        private List<Attribute.TypeCompound> typeAnnotations;
        private Type underlyingType;

        protected AnnotatedType(List<Attribute.TypeCompound> list, Type type) {
            super(type.tsym);
            this.typeAnnotations = list;
            this.underlyingType = type;
            Assert.check(list != null && list.nonEmpty(), "Can't create AnnotatedType without annotations: " + type);
            Assert.check(!type.isAnnotated(), "Can't annotate already annotated type: " + type + "; adding: " + list);
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return this.underlyingType.getTag();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isAnnotated() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public List<Attribute.TypeCompound> mo409getAnnotationMirrors() {
            return this.typeAnnotations;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeKind getKind() {
            return this.underlyingType.getKind();
        }

        @Override // com.sun.tools.javac.code.Type
        public Type unannotatedType() {
            return this.underlyingType;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitAnnotatedType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) this.underlyingType.accept((TypeVisitor<R, TypeVisitor<R, P>>) typeVisitor, (TypeVisitor<R, P>) p);
        }

        @Override // com.sun.tools.javac.code.Type
        public Type map(Mapping mapping) {
            this.underlyingType.map(mapping);
            return this;
        }

        @Override // com.sun.tools.javac.code.Type
        public Type constType(Object obj) {
            return this.underlyingType.constType(obj);
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getEnclosingType, reason: merged with bridge method [inline-methods] */
        public Type m446getEnclosingType() {
            return this.underlyingType.mo452getEnclosingType();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getReturnType */
        public Type m457getReturnType() {
            return this.underlyingType.m457getReturnType();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getTypeArguments, reason: merged with bridge method [inline-methods] */
        public List<Type> m445getTypeArguments() {
            return this.underlyingType.mo451getTypeArguments();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getParameterTypes */
        public List<Type> m456getParameterTypes() {
            return this.underlyingType.m456getParameterTypes();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getReceiverType */
        public Type m455getReceiverType() {
            return this.underlyingType.m455getReceiverType();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getThrownTypes */
        public List<Type> m454getThrownTypes() {
            return this.underlyingType.m454getThrownTypes();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getUpperBound, reason: merged with bridge method [inline-methods] */
        public Type m449getUpperBound() {
            return this.underlyingType.m460getUpperBound();
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: getLowerBound, reason: merged with bridge method [inline-methods] */
        public Type m448getLowerBound() {
            return this.underlyingType.m459getLowerBound();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return this.underlyingType.isErroneous();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return this.underlyingType.isCompound();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isInterface() {
            return this.underlyingType.isInterface();
        }

        @Override // com.sun.tools.javac.code.Type
        public List<Type> allparams() {
            return this.underlyingType.allparams();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPrimitive() {
            return this.underlyingType.isPrimitive();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPrimitiveOrVoid() {
            return this.underlyingType.isPrimitiveOrVoid();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNumeric() {
            return this.underlyingType.isNumeric();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isReference() {
            return this.underlyingType.isReference();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return this.underlyingType.isNullOrReference();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPartial() {
            return this.underlyingType.isPartial();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isParameterized() {
            return this.underlyingType.isParameterized();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isRaw() {
            return this.underlyingType.isRaw();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isFinal() {
            return this.underlyingType.isFinal();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isSuperBound() {
            return this.underlyingType.isSuperBound();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isExtendsBound() {
            return this.underlyingType.isExtendsBound();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isUnbound() {
            return this.underlyingType.isUnbound();
        }

        @Override // com.sun.tools.javac.code.Type
        public String toString() {
            if (this.typeAnnotations != null && !this.typeAnnotations.isEmpty()) {
                return RuntimeConstants.SIG_METHOD + this.typeAnnotations.toString() + " :: " + this.underlyingType.toString() + RuntimeConstants.SIG_ENDMETHOD;
            }
            return "({} :: " + this.underlyingType.toString() + RuntimeConstants.SIG_ENDMETHOD;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            return this.underlyingType.contains(type);
        }

        @Override // com.sun.tools.javac.code.Type
        public Type withTypeVar(Type type) {
            this.underlyingType = this.underlyingType.withTypeVar(type);
            return this;
        }

        @Override // com.sun.tools.javac.code.Type
        /* renamed from: asElement, reason: merged with bridge method [inline-methods] */
        public Symbol.TypeSymbol m447asElement() {
            return this.underlyingType.m447asElement();
        }

        @Override // com.sun.tools.javac.code.Type
        public MethodType asMethodType() {
            return this.underlyingType.asMethodType();
        }

        @Override // com.sun.tools.javac.code.Type
        public void complete() {
            this.underlyingType.complete();
        }

        public TypeMirror getComponentType() {
            return ((ArrayType) this.underlyingType).mo450getComponentType();
        }

        public Type makeVarargs() {
            return ((ArrayType) this.underlyingType).makeVarargs().annotatedType(this.typeAnnotations);
        }

        public TypeMirror getExtendsBound() {
            return ((WildcardType) this.underlyingType).m463getExtendsBound();
        }

        public TypeMirror getSuperBound() {
            return ((WildcardType) this.underlyingType).m462getSuperBound();
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/code/Type$UnknownType.class */
    public static class UnknownType extends Type {
        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct
        /* renamed from: getAnnotationMirrors */
        public /* bridge */ /* synthetic */ java.util.List mo409getAnnotationMirrors() {
            return super.mo409getAnnotationMirrors();
        }

        public UnknownType() {
            super(null);
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.UNKNOWN;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return (R) typeVisitor.visitUnknown(this, p);
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPartial() {
            return true;
        }
    }
}
