package com.sun.jdi.event;

import com.sun.jdi.ObjectReference;
import com.sun.jdi.ThreadReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/MonitorContendedEnteredEvent.class */
public interface MonitorContendedEnteredEvent extends LocatableEvent {
    @Override // com.sun.jdi.event.LocatableEvent
    ThreadReference thread();

    ObjectReference monitor();
}
