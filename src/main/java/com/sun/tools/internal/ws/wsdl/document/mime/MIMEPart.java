package com.sun.tools.internal.ws.wsdl.document.mime;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible;
import com.sun.tools.internal.ws.api.wsdl.TWSDLExtension;
import com.sun.tools.internal.ws.wsdl.framework.EntityAction;
import com.sun.tools.internal.ws.wsdl.framework.ExtensibilityHelper;
import com.sun.tools.internal.ws.wsdl.framework.ExtensionImpl;
import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/mime/MIMEPart.class */
public class MIMEPart extends ExtensionImpl implements TWSDLExtensible {
    private String _name;
    private ExtensibilityHelper _helper;

    public MIMEPart(Locator locator) {
        super(locator);
        this._helper = new ExtensibilityHelper();
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Elemental
    public QName getElementName() {
        return MIMEConstants.QNAME_PART;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String s) {
        this._name = s;
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible
    public String getNameValue() {
        return getName();
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible
    public String getNamespaceURI() {
        return getParent().getNamespaceURI();
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible
    public QName getWSDLElementName() {
        return getElementName();
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible
    public void addExtension(TWSDLExtension e) {
        this._helper.addExtension(e);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible
    public Iterable<TWSDLExtension> extensions() {
        return this._helper.extensions();
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Entity
    public void withAllSubEntitiesDo(EntityAction action) {
        this._helper.withAllSubEntitiesDo(action);
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Entity
    public void validateThis() {
    }
}
