package com.sun.tools.internal.ws.util;

import com.sun.xml.internal.ws.util.exception.JAXWSExceptionBase;

/* loaded from: target.jar:com/sun/tools/internal/ws/util/WSDLParseException.class */
public class WSDLParseException extends JAXWSExceptionBase {
    public WSDLParseException(String key, Object... args) {
        super(key, args);
    }

    public WSDLParseException(Throwable throwable) {
        super(throwable);
    }

    public String getDefaultResourceBundleName() {
        return "com.sun.tools.internal.ws.resources.util";
    }
}
