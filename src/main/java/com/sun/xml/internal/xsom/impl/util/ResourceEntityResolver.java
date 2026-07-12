package com.sun.xml.internal.xsom.impl.util;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/util/ResourceEntityResolver.class */
public class ResourceEntityResolver implements EntityResolver {
    private final Class base;

    public ResourceEntityResolver(Class _base) {
        this.base = _base;
    }

    @Override // org.xml.sax.EntityResolver
    public InputSource resolveEntity(String publicId, String systemId) {
        return new InputSource(this.base.getResourceAsStream(systemId));
    }
}
