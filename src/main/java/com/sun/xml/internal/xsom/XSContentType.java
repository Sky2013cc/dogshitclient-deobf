package com.sun.xml.internal.xsom;

import com.sun.xml.internal.xsom.visitor.XSContentTypeFunction;
import com.sun.xml.internal.xsom.visitor.XSContentTypeVisitor;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSContentType.class */
public interface XSContentType extends XSComponent {
    XSSimpleType asSimpleType();

    XSParticle asParticle();

    XSContentType asEmpty();

    <T> T apply(XSContentTypeFunction<T> xSContentTypeFunction);

    void visit(XSContentTypeVisitor xSContentTypeVisitor);
}
