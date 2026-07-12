package com.sun.jdi.event;

import com.sun.jdi.Locatable;
import com.sun.jdi.ThreadReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/LocatableEvent.class */
public interface LocatableEvent extends Event, Locatable {
    ThreadReference thread();
}
