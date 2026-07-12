package com.sun.tools.jdi;

import com.sun.jdi.ClassNotPreparedException;
import com.sun.jdi.InconsistentDebugInfoException;
import com.sun.jdi.InternalException;
import com.sun.jdi.InvalidStackFrameException;
import com.sun.jdi.ObjectCollectedException;
import com.sun.jdi.VMDisconnectedException;
import com.sun.jdi.VMOutOfMemoryException;

/* loaded from: target.jar:com/sun/tools/jdi/JDWPException.class */
class JDWPException extends Exception {
    private static final long serialVersionUID = -6321344442751299874L;
    short errorCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JDWPException(short s) {
        this.errorCode = s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public short errorCode() {
        return this.errorCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RuntimeException toJDIException() {
        switch (this.errorCode) {
            case 10:
                return new IllegalThreadStateException();
            case 20:
                return new ObjectCollectedException();
            case 22:
                return new ClassNotPreparedException();
            case 30:
            case 33:
                return new InvalidStackFrameException();
            case 34:
                return new InconsistentDebugInfoException();
            case 99:
                return new UnsupportedOperationException();
            case 110:
                return new VMOutOfMemoryException();
            case 112:
                return new VMDisconnectedException();
            case 503:
            case 504:
                return new IndexOutOfBoundsException();
            default:
                return new InternalException("Unexpected JDWP Error: " + ((int) this.errorCode), this.errorCode);
        }
    }
}
