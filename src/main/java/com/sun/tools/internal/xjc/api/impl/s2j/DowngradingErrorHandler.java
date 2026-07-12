package com.sun.tools.internal.xjc.api.impl.s2j;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/impl/s2j/DowngradingErrorHandler.class */
final class DowngradingErrorHandler implements ErrorHandler {
    private final ErrorHandler core;

    public DowngradingErrorHandler(ErrorHandler core) {
        this.core = core;
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException exception) throws SAXException {
        this.core.warning(exception);
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException exception) throws SAXException {
        this.core.warning(exception);
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException exception) throws SAXException {
        this.core.warning(exception);
    }
}
