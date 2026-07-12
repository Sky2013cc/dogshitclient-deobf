package com.sun.tools.internal.ws.wsdl.parser;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible;
import com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler;
import com.sun.tools.internal.ws.api.wsdl.TWSDLParserContext;
import com.sun.tools.internal.ws.util.xml.XmlUtil;
import com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.NamespaceVersion;
import com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken;
import org.w3c.dom.Element;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/parser/Policy12ExtensionHandler.class */
public class Policy12ExtensionHandler extends TWSDLExtensionHandler {
    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public String getNamespaceURI() {
        return NamespaceVersion.v1_2.toString();
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handlePortTypeExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handleDefinitionsExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handleBindingExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handleOperationExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handleInputExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handleOutputExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handleFaultExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handleServiceExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handlePortExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleExtension(context, parent, e);
    }

    private boolean handleExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return XmlUtil.matchesTagNS(e, NamespaceVersion.v1_2.asQName(XmlToken.Policy)) || XmlUtil.matchesTagNS(e, NamespaceVersion.v1_2.asQName(XmlToken.PolicyReference)) || XmlUtil.matchesTagNS(e, NamespaceVersion.v1_2.asQName(XmlToken.UsingPolicy));
    }
}
