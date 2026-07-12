package com.sun.xml.internal.xsom.impl;

import com.sun.xml.internal.xsom.XSAnnotation;
import org.xml.sax.Locator;
import org.xml.sax.helpers.LocatorImpl;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/AnnotationImpl.class */
public class AnnotationImpl implements XSAnnotation {
    private Object annotation;
    private final Locator locator;
    private static final LocatorImplUnmodifiable NULL_LOCATION = new LocatorImplUnmodifiable();

    @Override // com.sun.xml.internal.xsom.XSAnnotation
    public Object getAnnotation() {
        return this.annotation;
    }

    @Override // com.sun.xml.internal.xsom.XSAnnotation
    public Object setAnnotation(Object o) {
        Object r = this.annotation;
        this.annotation = o;
        return r;
    }

    @Override // com.sun.xml.internal.xsom.XSAnnotation
    public Locator getLocator() {
        return this.locator;
    }

    public AnnotationImpl(Object o, Locator _loc) {
        this.annotation = o;
        this.locator = _loc;
    }

    public AnnotationImpl() {
        this.locator = NULL_LOCATION;
    }

    /* loaded from: target.jar:com/sun/xml/internal/xsom/impl/AnnotationImpl$LocatorImplUnmodifiable.class */
    private static class LocatorImplUnmodifiable extends LocatorImpl {
        private LocatorImplUnmodifiable() {
        }

        @Override // org.xml.sax.helpers.LocatorImpl
        public void setColumnNumber(int columnNumber) {
        }

        @Override // org.xml.sax.helpers.LocatorImpl
        public void setPublicId(String publicId) {
        }

        @Override // org.xml.sax.helpers.LocatorImpl
        public void setSystemId(String systemId) {
        }

        @Override // org.xml.sax.helpers.LocatorImpl
        public void setLineNumber(int lineNumber) {
        }
    }
}
