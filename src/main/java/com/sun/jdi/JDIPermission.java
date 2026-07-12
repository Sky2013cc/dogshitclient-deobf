package com.sun.jdi;

import java.security.BasicPermission;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/JDIPermission.class */
public final class JDIPermission extends BasicPermission {
    private static final long serialVersionUID = -6988461416938786271L;

    public JDIPermission(String str) {
        super(str);
        if (!str.equals("virtualMachineManager")) {
            throw new IllegalArgumentException("name: " + str);
        }
    }

    public JDIPermission(String str, String str2) throws IllegalArgumentException {
        super(str);
        if (!str.equals("virtualMachineManager")) {
            throw new IllegalArgumentException("name: " + str);
        }
        if (str2 != null && str2.length() > 0) {
            throw new IllegalArgumentException("actions: " + str2);
        }
    }
}
