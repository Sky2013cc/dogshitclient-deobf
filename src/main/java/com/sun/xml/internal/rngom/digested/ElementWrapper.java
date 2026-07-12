package com.sun.xml.internal.rngom.digested;

import com.sun.xml.internal.rngom.ast.om.ParsedElementAnnotation;
import org.w3c.dom.Element;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/ElementWrapper.class */
final class ElementWrapper implements ParsedElementAnnotation {
    final Element element;

    public ElementWrapper(Element e) {
        this.element = e;
    }
}
