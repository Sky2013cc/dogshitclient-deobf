package com.sun.jdi;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/Accessible.class */
public interface Accessible {
    int modifiers();

    boolean isPrivate();

    boolean isPackagePrivate();

    boolean isProtected();

    boolean isPublic();
}
