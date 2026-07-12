package com.sun.tools.jdi;

import java.util.EventListener;

/* loaded from: target.jar:com/sun/tools/jdi/ThreadListener.class */
interface ThreadListener extends EventListener {
    boolean threadResumable(ThreadAction threadAction);
}
