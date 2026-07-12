package com.sun.jdi.connect.spi;

import java.io.IOException;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/connect/spi/ClosedConnectionException.class */
public class ClosedConnectionException extends IOException {
    private static final long serialVersionUID = 3877032124297204774L;

    public ClosedConnectionException() {
    }

    public ClosedConnectionException(String str) {
        super(str);
    }
}
