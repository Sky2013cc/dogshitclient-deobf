package com.sun.jdi.request;

import com.sun.jdi.ReferenceType;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/request/ClassPrepareRequest.class */
public interface ClassPrepareRequest extends EventRequest {
    void addClassFilter(ReferenceType referenceType);

    void addClassFilter(String str);

    void addClassExclusionFilter(String str);

    void addSourceNameFilter(String str);
}
