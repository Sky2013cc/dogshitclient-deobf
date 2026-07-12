package com.sun.tools.attach;

import java.security.BasicPermission;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/tools/attach/AttachPermission.class */
public final class AttachPermission extends BasicPermission {
    static final long serialVersionUID = -4619447669752976181L;

    public AttachPermission(String str) {
        super(str);
        if (!str.equals("attachVirtualMachine") && !str.equals("createAttachProvider")) {
            throw new IllegalArgumentException("name: " + str);
        }
    }

    public AttachPermission(String str, String str2) {
        super(str);
        if (!str.equals("attachVirtualMachine") && !str.equals("createAttachProvider")) {
            throw new IllegalArgumentException("name: " + str);
        }
        if (str2 != null && str2.length() > 0) {
            throw new IllegalArgumentException("actions: " + str2);
        }
    }
}
