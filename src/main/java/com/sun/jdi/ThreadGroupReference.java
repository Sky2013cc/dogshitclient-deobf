package com.sun.jdi;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ThreadGroupReference.class */
public interface ThreadGroupReference extends ObjectReference {
    String name();

    ThreadGroupReference parent();

    void suspend();

    void resume();

    List<ThreadReference> threads();

    List<ThreadGroupReference> threadGroups();
}
