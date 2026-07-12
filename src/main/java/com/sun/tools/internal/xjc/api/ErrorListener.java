package com.sun.tools.internal.xjc.api;

import org.xml.sax.SAXParseException;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/ErrorListener.class */
public interface ErrorListener extends com.sun.xml.internal.bind.api.ErrorListener {
    void error(SAXParseException sAXParseException);

    void fatalError(SAXParseException sAXParseException);

    void warning(SAXParseException sAXParseException);

    void info(SAXParseException sAXParseException);
}
