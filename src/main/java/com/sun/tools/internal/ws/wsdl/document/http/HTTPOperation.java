package com.sun.tools.internal.ws.wsdl.document.http;

import com.sun.tools.internal.ws.wsdl.framework.ExtensionImpl;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/http/HTTPOperation.class */
public class HTTPOperation extends ExtensionImpl {
    private String _location;

    public HTTPOperation(Locator locator) {
        super(locator);
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Elemental
    public QName getElementName() {
        return HTTPConstants.QNAME_OPERATION;
    }

    public String getLocation() {
        return this._location;
    }

    public void setLocation(String s) {
        this._location = s;
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Entity
    public void validateThis() {
        if (this._location == null) {
            failValidation("validation.missingRequiredAttribute", Constants.ATTR_LOCATION);
        }
    }
}
