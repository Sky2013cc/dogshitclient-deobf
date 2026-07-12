package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSWildcard;

/* loaded from: target.jar:com/sun/xml/internal/xsom/visitor/XSWildcardVisitor.class */
public interface XSWildcardVisitor {
    void any(XSWildcard.Any any);

    void other(XSWildcard.Other other);

    void union(XSWildcard.Union union);
}
