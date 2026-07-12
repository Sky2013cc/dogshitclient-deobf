package com.sun.xml.internal.xsom;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSAttributeDecl.class */
public interface XSAttributeDecl extends XSDeclaration {
    XSSimpleType getType();

    XmlString getDefaultValue();

    XmlString getFixedValue();
}
