package com.sun.tools.jdi;

import com.sun.jdi.AbsentInformationException;

/* loaded from: target.jar:com/sun/tools/jdi/LineInfo.class */
interface LineInfo {
    String liStratum();

    int liLineNumber();

    String liSourceName() throws AbsentInformationException;

    String liSourcePath() throws AbsentInformationException;
}
