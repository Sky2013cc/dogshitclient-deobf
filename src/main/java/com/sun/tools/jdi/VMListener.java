package com.sun.tools.jdi;

import java.util.EventListener;

/* loaded from: target.jar:com/sun/tools/jdi/VMListener.class */
interface VMListener extends EventListener {
    boolean vmSuspended(VMAction vMAction);

    boolean vmNotSuspended(VMAction vMAction);
}
