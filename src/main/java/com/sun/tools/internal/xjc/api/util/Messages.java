package com.sun.tools.internal.xjc.api.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/util/Messages.class */
enum Messages {
    TOOLS_JAR_NOT_FOUND;

    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getName());

    @Override // java.lang.Enum
    public String toString() {
        return format(new Object[0]);
    }

    public String format(Object... args) {
        return MessageFormat.format(rb.getString(name()), args);
    }
}
