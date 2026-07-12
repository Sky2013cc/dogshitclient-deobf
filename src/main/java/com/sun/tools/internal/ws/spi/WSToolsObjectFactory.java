package com.sun.tools.internal.ws.spi;

import com.sun.tools.internal.ws.util.WSToolsObjectFactoryImpl;
import com.sun.xml.internal.ws.api.server.Container;
import java.io.OutputStream;

/* loaded from: target.jar:com/sun/tools/internal/ws/spi/WSToolsObjectFactory.class */
public abstract class WSToolsObjectFactory {
    private static final WSToolsObjectFactory factory = new WSToolsObjectFactoryImpl();

    public abstract boolean wsimport(OutputStream outputStream, Container container, String[] strArr);

    public abstract boolean wsgen(OutputStream outputStream, Container container, String[] strArr);

    public static WSToolsObjectFactory newInstance() {
        return factory;
    }

    public boolean wsimport(OutputStream logStream, String[] args) {
        return wsimport(logStream, Container.NONE, args);
    }

    public boolean wsgen(OutputStream logStream, String[] args) {
        return wsgen(logStream, Container.NONE, args);
    }
}
