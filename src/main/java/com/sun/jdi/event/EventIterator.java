package com.sun.jdi.event;

import java.util.Iterator;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/EventIterator.class */
public interface EventIterator extends Iterator<Event> {
    Event nextEvent();
}
