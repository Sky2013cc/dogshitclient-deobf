package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSWildcard;

/* loaded from: target.jar:com/sun/xml/internal/xsom/visitor/XSWildcardFunction.class */
public interface XSWildcardFunction<T> {
    T any(XSWildcard.Any any);

    T other(XSWildcard.Other other);

    T union(XSWildcard.Union union);
}
