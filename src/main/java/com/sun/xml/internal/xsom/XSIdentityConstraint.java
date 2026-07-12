package com.sun.xml.internal.xsom;

import java.util.List;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSIdentityConstraint.class */
public interface XSIdentityConstraint extends XSComponent {
    public static final short KEY = 0;
    public static final short KEYREF = 1;
    public static final short UNIQUE = 2;

    XSElementDecl getParent();

    String getName();

    String getTargetNamespace();

    short getCategory();

    XSXPath getSelector();

    List<XSXPath> getFields();

    XSIdentityConstraint getReferencedKey();
}
