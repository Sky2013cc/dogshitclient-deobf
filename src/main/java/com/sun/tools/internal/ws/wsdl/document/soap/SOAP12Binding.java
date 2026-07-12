package com.sun.tools.internal.ws.wsdl.document.soap;

import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/soap/SOAP12Binding.class */
public class SOAP12Binding extends SOAPBinding {
    public SOAP12Binding(Locator locator) {
        super(locator);
    }

    @Override // com.sun.tools.internal.ws.wsdl.document.soap.SOAPBinding, com.sun.tools.internal.ws.wsdl.framework.Elemental
    public QName getElementName() {
        return SOAP12Constants.QNAME_BINDING;
    }
}
