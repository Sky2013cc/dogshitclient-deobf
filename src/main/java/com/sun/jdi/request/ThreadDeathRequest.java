package com.sun.jdi.request;

import com.sun.jdi.ThreadReference;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/request/ThreadDeathRequest.class */
public interface ThreadDeathRequest extends EventRequest {
    void addThreadFilter(ThreadReference threadReference);
}
