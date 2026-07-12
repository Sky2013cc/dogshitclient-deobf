package com.sun.jdi.event;

import com.sun.jdi.Mirror;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/EventQueue.class */
public interface EventQueue extends Mirror {
    EventSet remove() throws InterruptedException;

    EventSet remove(long j) throws InterruptedException;
}
