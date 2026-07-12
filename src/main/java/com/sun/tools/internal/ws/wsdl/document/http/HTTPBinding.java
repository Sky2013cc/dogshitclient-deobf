package com.sun.tools.internal.ws.wsdl.document.http;

import com.sun.tools.internal.ws.wsdl.framework.ExtensionImpl;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/http/HTTPBinding.class */
public class HTTPBinding extends ExtensionImpl {
    private String _verb;

    public HTTPBinding(Locator locator) {
        super(locator);
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Elemental
    public QName getElementName() {
        return HTTPConstants.QNAME_BINDING;
    }

    public String getVerb() {
        return this._verb;
    }

    public void setVerb(String s) {
        this._verb = s;
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Entity
    public void validateThis() {
        if (this._verb == null) {
            failValidation("validation.missingRequiredAttribute", Constants.ATTR_VERB);
        }
    }
}
