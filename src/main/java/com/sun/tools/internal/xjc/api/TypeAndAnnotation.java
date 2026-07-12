package com.sun.tools.internal.xjc.api;

import com.sun.codemodel.internal.JAnnotatable;
import com.sun.codemodel.internal.JType;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/TypeAndAnnotation.class */
public interface TypeAndAnnotation {
    JType getTypeClass();

    void annotate(JAnnotatable jAnnotatable);

    boolean equals(Object obj);
}
