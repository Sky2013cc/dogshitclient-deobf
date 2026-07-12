package com.sun.jdi;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/ClassLoaderReference.class */
public interface ClassLoaderReference extends ObjectReference {
    List<ReferenceType> definedClasses();

    List<ReferenceType> visibleClasses();
}
