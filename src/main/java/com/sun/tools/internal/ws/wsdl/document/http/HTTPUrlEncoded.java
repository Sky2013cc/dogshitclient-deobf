package com.sun.tools.internal.ws.wsdl.document.http;

import com.sun.tools.internal.ws.wsdl.framework.ExtensionImpl;
import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/http/HTTPUrlEncoded.class */
public class HTTPUrlEncoded extends ExtensionImpl {
    public HTTPUrlEncoded(Locator locator) {
        super(locator);
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Elemental
    public QName getElementName() {
        return HTTPConstants.QNAME_URL_ENCODED;
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Entity
    public void validateThis() {
    }
}
