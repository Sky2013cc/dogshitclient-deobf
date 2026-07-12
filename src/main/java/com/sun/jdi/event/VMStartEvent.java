package com.sun.jdi.event;

import com.sun.jdi.ThreadReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/VMStartEvent.class */
public interface VMStartEvent extends Event {
    ThreadReference thread();
}
