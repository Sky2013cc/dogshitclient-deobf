package com.sun.xml.internal.xsom;

import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSAnnotation.class */
public interface XSAnnotation {
    Object getAnnotation();

    Object setAnnotation(Object obj);

    Locator getLocator();
}
