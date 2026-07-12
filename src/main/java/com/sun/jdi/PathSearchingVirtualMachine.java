package com.sun.jdi;

import java.util.List;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/PathSearchingVirtualMachine.class */
public interface PathSearchingVirtualMachine extends VirtualMachine {
    List<String> classPath();

    List<String> bootClassPath();

    String baseDirectory();
}
