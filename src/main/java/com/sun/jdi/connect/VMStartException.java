package com.sun.jdi.connect;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/connect/VMStartException.class */
public class VMStartException extends Exception {
    private static final long serialVersionUID = 6408644824640801020L;
    Process process;

    public VMStartException(Process process) {
        this.process = process;
    }

    public VMStartException(String str, Process process) {
        super(str);
        this.process = process;
    }

    public Process process() {
        return this.process;
    }
}
