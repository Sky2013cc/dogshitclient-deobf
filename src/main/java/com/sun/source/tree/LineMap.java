package com.sun.source.tree;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/tree/LineMap.class */
public interface LineMap {
    long getStartPosition(long j);

    long getPosition(long j, long j2);

    long getLineNumber(long j);

    long getColumnNumber(long j);
}
