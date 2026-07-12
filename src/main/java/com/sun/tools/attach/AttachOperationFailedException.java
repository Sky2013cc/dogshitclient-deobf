package com.sun.tools.attach;

import java.io.IOException;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/tools/attach/AttachOperationFailedException.class */
public class AttachOperationFailedException extends IOException {
    private static final long serialVersionUID = 2140308168167478043L;

    public AttachOperationFailedException(String str) {
        super(str);
    }
}
