package com.sun.source.util;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/source/util/Plugin.class */
public interface Plugin {
    String getName();

    void init(JavacTask javacTask, String... strArr);
}
