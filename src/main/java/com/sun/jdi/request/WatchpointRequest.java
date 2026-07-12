package com.sun.jdi.request;

import com.sun.jdi.Field;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.ThreadReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/request/WatchpointRequest.class */
public interface WatchpointRequest extends EventRequest {
    Field field();

    void addThreadFilter(ThreadReference threadReference);

    void addClassFilter(ReferenceType referenceType);

    void addClassFilter(String str);

    void addClassExclusionFilter(String str);

    void addInstanceFilter(ObjectReference objectReference);
}
