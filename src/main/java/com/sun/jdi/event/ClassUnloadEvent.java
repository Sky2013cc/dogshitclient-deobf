package com.sun.jdi.event;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/event/ClassUnloadEvent.class */
public interface ClassUnloadEvent extends Event {
    String className();

    String classSignature();
}
