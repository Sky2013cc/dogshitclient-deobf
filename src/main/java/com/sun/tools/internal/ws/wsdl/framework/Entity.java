package com.sun.tools.internal.ws.wsdl.framework;

import com.sun.tools.internal.ws.wscompile.ErrorReceiver;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/Entity.class */
public abstract class Entity implements Elemental {
    private final Locator locator;
    protected ErrorReceiver errorReceiver;
    private Map _properties;

    public abstract void validateThis();

    public Entity(Locator locator) {
        this.locator = locator;
    }

    public void setErrorReceiver(ErrorReceiver errorReceiver) {
        this.errorReceiver = errorReceiver;
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.Elemental
    public Locator getLocator() {
        return this.locator;
    }

    public Object getProperty(String key) {
        if (this._properties == null) {
            return null;
        }
        return this._properties.get(key);
    }

    public void setProperty(String key, Object value) {
        if (value == null) {
            removeProperty(key);
            return;
        }
        if (this._properties == null) {
            this._properties = new HashMap();
        }
        this._properties.put(key, value);
    }

    public void removeProperty(String key) {
        if (this._properties != null) {
            this._properties.remove(key);
        }
    }

    public void withAllSubEntitiesDo(EntityAction action) {
    }

    public void withAllQNamesDo(QNameAction action) {
        action.perform(getElementName());
    }

    public void withAllEntityReferencesDo(EntityReferenceAction action) {
    }

    protected void failValidation(String key) {
        throw new ValidationException(key, getElementName().getLocalPart());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void failValidation(String key, String arg) {
        throw new ValidationException(key, arg, getElementName().getLocalPart());
    }
}
