package com.sun.tools.jdi;

import com.sun.jdi.ThreadReference;
import com.sun.jdi.VirtualMachine;
import java.util.EventObject;

/* loaded from: target.jar:com/sun/tools/jdi/VMAction.class */
class VMAction extends EventObject {
    private static final long serialVersionUID = -1701944679310296090L;
    static final int VM_SUSPENDED = 1;
    static final int VM_NOT_SUSPENDED = 2;
    int id;
    ThreadReference resumingThread;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VMAction(VirtualMachine virtualMachine, int i) {
        this(virtualMachine, null, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VMAction(VirtualMachine virtualMachine, ThreadReference threadReference, int i) {
        super(virtualMachine);
        this.id = i;
        this.resumingThread = threadReference;
    }

    VirtualMachine vm() {
        return (VirtualMachine) getSource();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int id() {
        return this.id;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadReference resumingThread() {
        return this.resumingThread;
    }
}
