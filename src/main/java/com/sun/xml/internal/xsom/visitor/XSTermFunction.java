package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSElementDecl;
import com.sun.xml.internal.xsom.XSModelGroup;
import com.sun.xml.internal.xsom.XSModelGroupDecl;
import com.sun.xml.internal.xsom.XSWildcard;

/* loaded from: target.jar:com/sun/xml/internal/xsom/visitor/XSTermFunction.class */
public interface XSTermFunction<T> {
    T wildcard(XSWildcard xSWildcard);

    T modelGroupDecl(XSModelGroupDecl xSModelGroupDecl);

    T modelGroup(XSModelGroup xSModelGroup);

    T elementDecl(XSElementDecl xSElementDecl);
}
