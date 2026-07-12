package com.sun.tools.internal.ws.wsdl.parser;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible;
import com.sun.tools.internal.ws.api.wsdl.TWSDLParserContext;
import com.sun.tools.internal.ws.util.xml.XmlUtil;
import com.sun.tools.internal.ws.wscompile.ErrorReceiver;
import com.sun.xml.internal.ws.api.addressing.AddressingVersion;
import java.util.Map;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/parser/W3CAddressingExtensionHandler.class */
public class W3CAddressingExtensionHandler extends AbstractExtensionHandler {
    public W3CAddressingExtensionHandler(Map<String, AbstractExtensionHandler> extensionHandlerMap) {
        this(extensionHandlerMap, null);
    }

    public W3CAddressingExtensionHandler(Map<String, AbstractExtensionHandler> extensionHandlerMap, ErrorReceiver errReceiver) {
        super(extensionHandlerMap);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public String getNamespaceURI() {
        return AddressingVersion.W3C.wsdlNsUri;
    }

    protected QName getWSDLExtensionQName() {
        return AddressingVersion.W3C.wsdlExtensionTag;
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handleBindingExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        if (XmlUtil.matchesTagNS(e, getWSDLExtensionQName())) {
            return true;
        }
        return false;
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensionHandler
    public boolean handlePortExtension(TWSDLParserContext context, TWSDLExtensible parent, Element e) {
        return handleBindingExtension(context, parent, e);
    }
}
