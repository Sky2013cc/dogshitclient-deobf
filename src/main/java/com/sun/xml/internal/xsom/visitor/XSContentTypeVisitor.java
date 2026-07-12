package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSContentType;
import com.sun.xml.internal.xsom.XSParticle;
import com.sun.xml.internal.xsom.XSSimpleType;

/* loaded from: target.jar:com/sun/xml/internal/xsom/visitor/XSContentTypeVisitor.class */
public interface XSContentTypeVisitor {
    void simpleType(XSSimpleType xSSimpleType);

    void particle(XSParticle xSParticle);

    void empty(XSContentType xSContentType);
}
