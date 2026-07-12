package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSElementDecl;
import com.sun.xml.internal.xsom.XSModelGroup;
import com.sun.xml.internal.xsom.XSModelGroupDecl;
import com.sun.xml.internal.xsom.XSWildcard;

/* loaded from: target.jar:com/sun/xml/internal/xsom/visitor/XSTermFunctionWithParam.class */
public interface XSTermFunctionWithParam<T, P> {
    T wildcard(XSWildcard xSWildcard, P p);

    T modelGroupDecl(XSModelGroupDecl xSModelGroupDecl, P p);

    T modelGroup(XSModelGroup xSModelGroup, P p);

    T elementDecl(XSElementDecl xSElementDecl, P p);
}
