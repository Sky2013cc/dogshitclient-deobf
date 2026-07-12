package com.sun.tools.attach;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/tools/attach/AgentInitializationException.class */
public class AgentInitializationException extends Exception {
    static final long serialVersionUID = -1508756333332806353L;
    private int returnValue;

    public AgentInitializationException() {
        this.returnValue = 0;
    }

    public AgentInitializationException(String str) {
        super(str);
        this.returnValue = 0;
    }

    public AgentInitializationException(String str, int i) {
        super(str);
        this.returnValue = i;
    }

    public int returnValue() {
        return this.returnValue;
    }
}
