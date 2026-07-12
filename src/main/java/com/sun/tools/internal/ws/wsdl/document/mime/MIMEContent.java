package com.sun.tools.internal.ws.wsdl.document.mime;

import com.sun.tools.internal.ws.wsdl.framework.ExtensionImpl;
import javax.xml.namespace.QName;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/mime/MIMEContent.class */
public class MIMEContent extends ExtensionImpl {
    private String _part;
    private String _type;

    public MIMEContent(Locator locator) {
        super(locator);
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Elemental
    public QName getElementName() {
        return MIMEConstants.QNAME_CONTENT;
    }

    public String getPart() {
        return this._part;
    }

    public void setPart(String s) {
        this._part = s;
    }

    public String getType() {
        return this._type;
    }

    public void setType(String s) {
        this._type = s;
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Entity
    public void validateThis() {
    }
}
