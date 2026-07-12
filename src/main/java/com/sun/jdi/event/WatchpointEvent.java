package com.sun.jdi.event;

import com.sun.jdi.Field;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.Value;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/WatchpointEvent.class */
public interface WatchpointEvent extends LocatableEvent {
    Field field();

    ObjectReference object();

    Value valueCurrent();
}
