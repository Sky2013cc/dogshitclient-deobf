package com.sun.xml.internal.xsom;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSType.class */
public interface XSType extends XSDeclaration {
    public static final int EXTENSION = 1;
    public static final int RESTRICTION = 2;
    public static final int SUBSTITUTION = 4;

    XSType getBaseType();

    int getDerivationMethod();

    boolean isSimpleType();

    boolean isComplexType();

    XSType[] listSubstitutables();

    XSType getRedefinedBy();

    int getRedefinedCount();

    XSSimpleType asSimpleType();

    XSComplexType asComplexType();

    boolean isDerivedFrom(XSType xSType);
}
