package com.sun.xml.internal.xsom.parser;

import org.xml.sax.ContentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;

/* loaded from: target.jar:com/sun/xml/internal/xsom/parser/AnnotationParser.class */
public abstract class AnnotationParser {
    public abstract ContentHandler getContentHandler(AnnotationContext annotationContext, String str, ErrorHandler errorHandler, EntityResolver entityResolver);

    public abstract Object getResult(Object obj);
}
