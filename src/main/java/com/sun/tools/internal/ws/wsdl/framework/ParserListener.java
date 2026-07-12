package com.sun.tools.internal.ws.wsdl.framework;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/ParserListener.class */
public interface ParserListener {
    void ignoringExtension(Entity entity, QName qName, QName qName2);

    void doneParsingEntity(QName qName, Entity entity);
}
