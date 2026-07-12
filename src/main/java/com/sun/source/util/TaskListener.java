package com.sun.source.util;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/util/TaskListener.class */
public interface TaskListener {
    void started(TaskEvent taskEvent);

    void finished(TaskEvent taskEvent);
}
