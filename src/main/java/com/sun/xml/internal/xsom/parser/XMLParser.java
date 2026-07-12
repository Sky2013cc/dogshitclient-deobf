package com.sun.xml.internal.xsom.parser;

import java.io.IOException;
import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/xml/internal/xsom/parser/XMLParser.class */
public interface XMLParser {
    void parse(InputSource inputSource, ContentHandler contentHandler, ErrorHandler errorHandler, EntityResolver entityResolver) throws SAXException, IOException;
}
