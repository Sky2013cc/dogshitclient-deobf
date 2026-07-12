package com.sun.tools.internal.ws.wsdl.framework;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtensible;
import com.sun.tools.internal.ws.api.wsdl.TWSDLExtension;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/ExtensionImpl.class */
public abstract class ExtensionImpl extends Entity implements TWSDLExtension {
    private TWSDLExtensible _parent;

    public ExtensionImpl(Locator locator) {
        super(locator);
    }

    @Override // com.sun.tools.internal.ws.api.wsdl.TWSDLExtension
    public TWSDLExtensible getParent() {
        return this._parent;
    }

    public void setParent(TWSDLExtensible parent) {
        this._parent = parent;
    }

    public void accept(ExtensionVisitor visitor) throws Exception {
        visitor.preVisit(this);
        visitor.postVisit(this);
    }
}
