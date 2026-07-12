package com.sun.jdi.request;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/request/ClassUnloadRequest.class */
public interface ClassUnloadRequest extends EventRequest {
    void addClassFilter(String str);

    void addClassExclusionFilter(String str);
}
