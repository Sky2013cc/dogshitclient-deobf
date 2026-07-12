package com.sun.xml.internal.xsom.visitor;

import com.sun.xml.internal.xsom.XSContentType;
import com.sun.xml.internal.xsom.XSParticle;
import com.sun.xml.internal.xsom.XSSimpleType;

/* loaded from: target.jar:com/sun/xml/internal/xsom/visitor/XSContentTypeFunction.class */
public interface XSContentTypeFunction<T> {
    T simpleType(XSSimpleType xSSimpleType);

    T particle(XSParticle xSParticle);

    T empty(XSContentType xSContentType);
}
