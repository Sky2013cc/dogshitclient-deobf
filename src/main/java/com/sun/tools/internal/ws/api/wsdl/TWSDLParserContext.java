package com.sun.tools.internal.ws.api.wsdl;

import org.w3c.dom.Element;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/api/wsdl/TWSDLParserContext.class */
public interface TWSDLParserContext {
    void push();

    void pop();

    String getNamespaceURI(String str);

    Iterable<String> getPrefixes();

    String getDefaultNamespaceURI();

    void registerNamespaces(Element element);

    Locator getLocation(Element element);
}
