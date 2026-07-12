package com.sun.tools.internal.ws.wsdl.framework;

import com.sun.tools.internal.ws.wscompile.ErrorReceiver;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/framework/GlobalEntity.class */
public abstract class GlobalEntity extends Entity implements GloballyKnown {
    private Defining _defining;
    private String _name;

    public abstract Kind getKind();

    public GlobalEntity(Defining defining, Locator locator, ErrorReceiver errorReceiver) {
        super(locator);
        this._defining = defining;
        this.errorReceiver = errorReceiver;
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.GloballyKnown
    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    @Override // com.sun.tools.internal.ws.wsdl.framework.GloballyKnown
    public Defining getDefining() {
        return this._defining;
    }
}
