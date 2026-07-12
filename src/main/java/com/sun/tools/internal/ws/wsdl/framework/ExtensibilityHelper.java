package com.sun.tools.internal.ws.wsdl.framework;

import com.sun.tools.internal.ws.api.wsdl.TWSDLExtension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/ExtensibilityHelper.class */
public class ExtensibilityHelper {
    private List<TWSDLExtension> _extensions;

    public void addExtension(TWSDLExtension e) {
        if (this._extensions == null) {
            this._extensions = new ArrayList();
        }
        this._extensions.add(e);
    }

    public Iterable<TWSDLExtension> extensions() {
        if (this._extensions == null) {
            return new ArrayList();
        }
        return this._extensions;
    }

    public void withAllSubEntitiesDo(EntityAction action) {
        if (this._extensions != null) {
            Iterator iter = this._extensions.iterator();
            while (iter.hasNext()) {
                action.perform((Entity) iter.next());
            }
        }
    }

    public void accept(ExtensionVisitor visitor) throws Exception {
        if (this._extensions != null) {
            Iterator iter = this._extensions.iterator();
            while (iter.hasNext()) {
                ((ExtensionImpl) iter.next()).accept(visitor);
            }
        }
    }
}
