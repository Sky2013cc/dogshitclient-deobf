package com.sun.jdi.event;

import com.sun.jdi.Location;
import com.sun.jdi.ObjectReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/ExceptionEvent.class */
public interface ExceptionEvent extends LocatableEvent {
    ObjectReference exception();

    Location catchLocation();
}
