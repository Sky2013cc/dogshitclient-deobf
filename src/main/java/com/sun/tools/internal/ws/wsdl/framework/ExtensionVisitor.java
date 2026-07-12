package com.sun.tools.internal.ws.wsdl.framework;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtension;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/ExtensionVisitor.class */
public interface ExtensionVisitor {
    void preVisit(TWSDLExtension tWSDLExtension) throws Exception;

    void postVisit(TWSDLExtension tWSDLExtension) throws Exception;
}
