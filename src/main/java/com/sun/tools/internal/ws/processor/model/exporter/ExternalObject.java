package com.sun.tools.internal.ws.processor.model.exporter;

import org.xml.sax.ContentHandler;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/exporter/ExternalObject.class */
public interface ExternalObject {
    String getType();

    void saveTo(ContentHandler contentHandler);
}
