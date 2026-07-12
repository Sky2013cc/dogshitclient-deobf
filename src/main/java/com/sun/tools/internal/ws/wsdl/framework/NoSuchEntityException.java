package com.sun.tools.internal.ws.wsdl.framework;

import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/NoSuchEntityException.class */
public class NoSuchEntityException extends ValidationException {
    public NoSuchEntityException(QName name) {
        super("entity.notFoundByQName", name.getLocalPart(), name.getNamespaceURI());
    }

    public NoSuchEntityException(String id) {
        super("entity.notFoundByID", id);
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.ValidationException
    public String getDefaultResourceBundleName() {
        return "com.sun.tools.internal.ws.resources.wsdl";
    }
}
