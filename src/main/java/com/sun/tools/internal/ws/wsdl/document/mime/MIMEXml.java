package com.sun.tools.internal.ws.wsdl.document.mime;

import com.sun.tools.internal.ws.wsdl.framework.ExtensionImpl;
import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/mime/MIMEXml.class */
public class MIMEXml extends ExtensionImpl {
    private String _part;

    public MIMEXml(Locator locator) {
        super(locator);
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Elemental
    public QName getElementName() {
        return MIMEConstants.QNAME_MIME_XML;
    }

    public String getPart() {
        return this._part;
    }

    public void setPart(String s) {
        this._part = s;
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Entity
    public void validateThis() {
    }
}
