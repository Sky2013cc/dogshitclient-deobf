package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/MonitorInfo.class */
public interface MonitorInfo extends Mirror {
    ObjectReference monitor();

    int stackDepth();

    ThreadReference thread();
}
