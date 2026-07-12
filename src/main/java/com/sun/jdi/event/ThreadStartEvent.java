package com.sun.jdi.event;

import com.sun.jdi.ThreadReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/ThreadStartEvent.class */
public interface ThreadStartEvent extends Event {
    ThreadReference thread();
}
