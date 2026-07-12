package com.sun.jdi.request;

import com.sun.jdi.Locatable;
import com.sun.jdi.Location;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ThreadReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/request/BreakpointRequest.class */
public interface BreakpointRequest extends EventRequest, Locatable {
    @Override // com.sun.jdi.Locatable
    Location location();

    void addThreadFilter(ThreadReference threadReference);

    void addInstanceFilter(ObjectReference objectReference);
}
