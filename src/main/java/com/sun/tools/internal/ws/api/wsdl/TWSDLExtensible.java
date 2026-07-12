package com.sun.tools.internal.ws.api.wsdl;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/api/wsdl/TWSDLExtensible.class */
public interface TWSDLExtensible {
    String getNameValue();

    String getNamespaceURI();

    QName getWSDLElementName();

    void addExtension(TWSDLExtension tWSDLExtension);

    Iterable<? extends TWSDLExtension> extensions();

    TWSDLExtensible getParent();
}
