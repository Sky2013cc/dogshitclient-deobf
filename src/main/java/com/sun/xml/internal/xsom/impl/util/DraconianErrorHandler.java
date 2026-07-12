package com.sun.xml.internal.xsom.impl.util;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/util/DraconianErrorHandler.class */
public class DraconianErrorHandler implements ErrorHandler {
    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException e) throws SAXException {
        throw e;
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException e) {
    }
}
