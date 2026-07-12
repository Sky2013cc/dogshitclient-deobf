package com.sun.tools.internal.ws.wsdl.document.soap;

import com.sun.tools.internal.ws.wsdl.framework.ExtensionImpl;
import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/soap/SOAPOperation.class */
public class SOAPOperation extends ExtensionImpl {
    private String _soapAction;
    private SOAPStyle _style;

    public SOAPOperation(Locator locator) {
        super(locator);
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Elemental
    public QName getElementName() {
        return SOAPConstants.QNAME_OPERATION;
    }

    public String getSOAPAction() {
        return this._soapAction;
    }

    public void setSOAPAction(String s) {
        this._soapAction = s;
    }

    public SOAPStyle getStyle() {
        return this._style;
    }

    public void setStyle(SOAPStyle s) {
        this._style = s;
    }

    public boolean isDocument() {
        return this._style == SOAPStyle.DOCUMENT;
    }

    public boolean isRPC() {
        return this._style == SOAPStyle.RPC;
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Entity
    public void validateThis() {
    }
}
