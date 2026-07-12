package com.sun.tools.jdi;

import com.sun.jdi.ThreadReference;
import java.util.EventObject;

/* loaded from: target.jar:com/sun/tools/jdi/ThreadAction.class */
class ThreadAction extends EventObject {
    private static final long serialVersionUID = 5690763191100515283L;
    static final int THREAD_RESUMABLE = 2;
    int id;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadAction(ThreadReference threadReference, int i) {
        super(threadReference);
        this.id = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadReference thread() {
        return (ThreadReference) getSource();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int id() {
        return this.id;
    }
}
