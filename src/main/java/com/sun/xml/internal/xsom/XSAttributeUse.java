package com.sun.xml.internal.xsom;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSAttributeUse.class */
public interface XSAttributeUse extends XSComponent {
    boolean isRequired();

    XSAttributeDecl getDecl();

    XmlString getDefaultValue();

    XmlString getFixedValue();
}
