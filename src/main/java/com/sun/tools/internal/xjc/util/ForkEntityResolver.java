package com.sun.tools.internal.xjc.util;

import java.io.IOException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/tools/internal/xjc/util/ForkEntityResolver.class */
public class ForkEntityResolver implements EntityResolver {
    private final EntityResolver lhs;
    private final EntityResolver rhs;

    public ForkEntityResolver(EntityResolver lhs, EntityResolver rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override // org.xml.sax.EntityResolver
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        InputSource is = this.lhs.resolveEntity(publicId, systemId);
        if (is != null) {
            return is;
        }
        return this.rhs.resolveEntity(publicId, systemId);
    }
}
