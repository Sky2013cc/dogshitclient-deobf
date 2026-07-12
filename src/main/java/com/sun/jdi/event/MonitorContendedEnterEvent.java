package com.sun.jdi.event;

import com.sun.jdi.ObjectReference;
import com.sun.jdi.ThreadReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/MonitorContendedEnterEvent.class */
public interface MonitorContendedEnterEvent extends LocatableEvent {
    @Override // com.sun.jdi.event.LocatableEvent
    ThreadReference thread();

    ObjectReference monitor();
}
