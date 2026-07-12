package com.sun.tools.internal.ws.api.wsdl;

import com.sun.tools.internal.ws.wsdl.document.WSDLConstants;
import org.w3c.dom.Element;

/* loaded from: target.jar:com/sun/tools/internal/ws/api/wsdl/TWSDLExtensionHandler.class */
public abstract class TWSDLExtensionHandler {
    public String getNamespaceURI() {
        return null;
    }

    public boolean doHandleExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        if (parent.getWSDLElementName().equals(WSDLConstants.QNAME_DEFINITIONS)) {
            return handleDefinitionsExtension(context, parent, e);
        }
        if (parent.getWSDLElementName().equals(WSDLConstants.QNAME_TYPES)) {
            return handleTypesExtension(context, parent, e);
        }
        if (parent.getWSDLElementName().equals(WSDLConstants.QNAME_PORT_TYPE)) {
            return handlePortTypeExtension(context, parent, e);
        }
        if (parent.getWSDLElementName().equals(WSDLConstants.QNAME_BINDING)) {
            return handleBindingExtension(context, parent, e);
        }
        if (parent.getWSDLElementName().equals(WSDLConstants.QNAME_OPERATION)) {
            return handleOperationExtension(context, parent, e);
        }
        if (parent.getWSDLElementName().equals(WSDLConstants.QNAME_INPUT)) {
            return handleInputExtension(context, parent, e);
        }
        if (parent.getWSDLElementName().equals(WSDLConstants.QNAME_OUTPUT)) {
            return handleOutputExtension(context, parent, e);
        }
        if (parent.getWSDLElementName().equals(WSDLConstants.QNAME_FAULT)) {
            return handleFaultExtension(context, parent, e);
        }
        if (parent.getWSDLElementName().equals(WSDLConstants.QNAME_SERVICE)) {
            return handleServiceExtension(context, parent, e);
        }
        if (parent.getWSDLElementName().equals(WSDLConstants.QNAME_PORT)) {
            return handlePortExtension(context, parent, e);
        }
        return false;
    }

    public boolean handlePortTypeExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return false;
    }

    public boolean handleDefinitionsExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return false;
    }

    public boolean handleTypesExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return false;
    }

    public boolean handleBindingExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return false;
    }

    public boolean handleOperationExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return false;
    }

    public boolean handleInputExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return false;
    }

    public boolean handleOutputExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return false;
    }

    public boolean handleFaultExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return false;
    }

    public boolean handleServiceExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return false;
    }

    public boolean handlePortExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return false;
    }
}
