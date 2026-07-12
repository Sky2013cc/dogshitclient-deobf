package com.sun.jdi.event;

import com.sun.jdi.Mirror;
import java.util.Set;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/EventSet.class */
public interface EventSet extends Mirror, Set<Event> {
    int suspendPolicy();

    EventIterator eventIterator();

    void resume();
}
