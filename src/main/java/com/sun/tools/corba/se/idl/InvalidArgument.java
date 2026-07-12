package com.sun.tools.corba.se.idl;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/InvalidArgument.class */
public class InvalidArgument extends Exception {
    private String message;

    public InvalidArgument(String str) {
        this.message = null;
        this.message = Util.getMessage("InvalidArgument.1", str) + "\n\n" + Util.getMessage("usage");
    }

    public InvalidArgument() {
        this.message = null;
        this.message = Util.getMessage("InvalidArgument.2") + "\n\n" + Util.getMessage("usage");
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
