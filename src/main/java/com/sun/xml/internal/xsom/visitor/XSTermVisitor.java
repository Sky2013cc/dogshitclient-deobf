package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSElementDecl;
import com.sun.xml.internal.xsom.XSModelGroup;
import com.sun.xml.internal.xsom.XSModelGroupDecl;
import com.sun.xml.internal.xsom.XSWildcard;

/* loaded from: target.jar:com/sun/xml/internal/xsom/visitor/XSTermVisitor.class */
public interface XSTermVisitor {
    void wildcard(XSWildcard xSWildcard);

    void modelGroupDecl(XSModelGroupDecl xSModelGroupDecl);

    void modelGroup(XSModelGroup xSModelGroup);

    void elementDecl(XSElementDecl xSElementDecl);
}
