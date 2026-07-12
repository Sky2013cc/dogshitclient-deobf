package com.sun.jdi.event;

import com.sun.jdi.Method;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/MethodEntryEvent.class */
public interface MethodEntryEvent extends LocatableEvent {
    Method method();
}
