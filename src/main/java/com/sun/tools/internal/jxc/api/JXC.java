package com.sun.tools.internal.jxc.api;

import com.sun.tools.internal.jxc.api.impl.j2s.JavaCompilerImpl;
import com.sun.tools.internal.xjc.api.JavaCompiler;

/* loaded from: target.jar:com/sun/tools/internal/jxc/api/JXC.class */
public class JXC {
    public static JavaCompiler createJavaCompiler() {
        return new JavaCompilerImpl();
    }
}
