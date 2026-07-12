package com.sun.tools.internal.jxc.ap;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/internal/jxc/ap/Messages.class */
enum Messages {
    NON_EXISTENT_FILE,
    UNRECOGNIZED_PARAMETER,
    OPERAND_MISSING;

    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getPackage().getName() + ".MessageBundle");

    @Override // java.lang.Enum
    public String toString() {
        return format(new Object[0]);
    }

    public String format(Object... args) {
        return MessageFormat.format(rb.getString(name()), args);
    }
}
