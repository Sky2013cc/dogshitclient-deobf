package com.sun.javadoc;

import java.io.File;

/* loaded from: target.jar:com/sun/javadoc/SourcePosition.class */
public interface SourcePosition {
    File file();

    int line();

    int column();

    String toString();
}
