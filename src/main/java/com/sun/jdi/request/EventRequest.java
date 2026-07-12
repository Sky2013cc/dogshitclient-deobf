package com.sun.jdi.request;

import com.sun.jdi.Mirror;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/request/EventRequest.class */
public interface EventRequest extends Mirror {
    public static final int SUSPEND_NONE = 0;
    public static final int SUSPEND_EVENT_THREAD = 1;
    public static final int SUSPEND_ALL = 2;

    boolean isEnabled();

    void setEnabled(boolean z);

    void enable();

    void disable();

    void addCountFilter(int i);

    void setSuspendPolicy(int i);

    int suspendPolicy();

    void putProperty(Object obj, Object obj2);

    Object getProperty(Object obj);
}
