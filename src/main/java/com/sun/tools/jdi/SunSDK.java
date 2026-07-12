package com.sun.tools.jdi;

import java.io.File;

/* loaded from: target.jar:com/sun/tools/jdi/SunSDK.class */
class SunSDK {
    SunSDK() {
    }

    static String home() {
        File file = new File(new File(System.getProperty("java.home")).getParent());
        if (new File(file, "bin" + File.separator + System.mapLibraryName("jdwp")).exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }
}
