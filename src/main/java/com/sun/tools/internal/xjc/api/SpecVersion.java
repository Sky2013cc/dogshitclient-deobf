package com.sun.tools.internal.xjc.api;

import com.sun.tools.internal.ws.wsdl.document.jaxws.JAXWSBindingsConstants;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/SpecVersion.class */
public enum SpecVersion {
    V2_0,
    V2_1,
    V2_2;

    public static final SpecVersion LATEST = V2_2;

    public boolean isLaterThan(SpecVersion t) {
        return ordinal() >= t.ordinal();
    }

    public static SpecVersion parse(String token) {
        if (token.equals(JAXWSBindingsConstants.JAXB_BINDING_VERSION)) {
            return V2_0;
        }
        if (token.equals("2.1")) {
            return V2_1;
        }
        if (token.equals("2.2")) {
            return V2_2;
        }
        return null;
    }
}
