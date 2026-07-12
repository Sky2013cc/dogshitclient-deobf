package com.sun.jdi.event;

import com.sun.jdi.ThreadReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/ThreadDeathEvent.class */
public interface ThreadDeathEvent extends Event {
    ThreadReference thread();
}
