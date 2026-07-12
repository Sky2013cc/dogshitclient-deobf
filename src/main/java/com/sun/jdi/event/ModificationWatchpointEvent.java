package com.sun.jdi.event;

import com.sun.jdi.Value;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/ModificationWatchpointEvent.class */
public interface ModificationWatchpointEvent extends WatchpointEvent {
    Value valueToBe();
}
