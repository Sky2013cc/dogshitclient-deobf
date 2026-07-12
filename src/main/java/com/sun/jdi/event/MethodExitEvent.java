package com.sun.jdi.event;

import com.sun.jdi.Method;
import com.sun.jdi.Value;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/MethodExitEvent.class */
public interface MethodExitEvent extends LocatableEvent {
    Method method();

    Value returnValue();
}
