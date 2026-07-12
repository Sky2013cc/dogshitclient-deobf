package com.sun.jdi.request;

import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/request/DuplicateRequestException.class */
public class DuplicateRequestException extends RuntimeException {
    private static final long serialVersionUID = -3719784920313411060L;

    public DuplicateRequestException() {
    }

    public DuplicateRequestException(String str) {
        super(str);
    }
}
