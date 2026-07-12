package com.sun.tools.internal.jxc;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/internal/jxc/Messages.class */
enum Messages {
    UNEXPECTED_NGCC_TOKEN,
    BASEDIR_DOESNT_EXIST,
    USAGE,
    FULLVERSION,
    VERSION;

    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getPackage().getName() + ".MessageBundle");

    @Override // java.lang.Enum
    public String toString() {
        return format(new Object[0]);
    }

    public String format(Object... args) {
        return MessageFormat.format(rb.getString(name()), args);
    }
}
