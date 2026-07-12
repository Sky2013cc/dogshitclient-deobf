package com.sun.xml.internal.xsom;

import com.sun.xml.internal.xsom.visitor.XSTermFunction;
import com.sun.xml.internal.xsom.visitor.XSTermFunctionWithParam;
import com.sun.xml.internal.xsom.visitor.XSTermVisitor;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSTerm.class */
public interface XSTerm extends XSComponent {
    void visit(XSTermVisitor xSTermVisitor);

    <T> T apply(XSTermFunction<T> xSTermFunction);

    <T, P> T apply(XSTermFunctionWithParam<T, P> xSTermFunctionWithParam, P p);

    boolean isWildcard();

    boolean isModelGroupDecl();

    boolean isModelGroup();

    boolean isElementDecl();

    XSWildcard asWildcard();

    XSModelGroupDecl asModelGroupDecl();

    XSModelGroup asModelGroup();

    XSElementDecl asElementDecl();
}
