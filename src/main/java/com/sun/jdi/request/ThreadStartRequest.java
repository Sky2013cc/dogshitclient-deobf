package com.sun.jdi.request;

import com.sun.jdi.ThreadReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/request/ThreadStartRequest.class */
public interface ThreadStartRequest extends EventRequest {
    void addThreadFilter(ThreadReference threadReference);
}
