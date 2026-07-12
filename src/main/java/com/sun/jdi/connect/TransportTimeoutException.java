package com.sun.jdi.connect;

import java.io.IOException;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/connect/TransportTimeoutException.class */
public class TransportTimeoutException extends IOException {
    private static final long serialVersionUID = 4107035242623365074L;

    public TransportTimeoutException() {
    }

    public TransportTimeoutException(String str) {
        super(str);
    }
}
