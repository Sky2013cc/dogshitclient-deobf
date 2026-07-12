package com.sun.tools.internal.xjc.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/internal/xjc/util/Messages.class */
class Messages {
    static final String ERR_CLASSNAME_COLLISION = "CodeModelClassFactory.ClassNameCollision";
    static final String ERR_CLASSNAME_COLLISION_SOURCE = "CodeModelClassFactory.ClassNameCollision.Source";
    static final String ERR_INVALID_CLASSNAME = "ERR_INVALID_CLASSNAME";
    static final String ERR_CASE_SENSITIVITY_COLLISION = "CodeModelClassFactory.CaseSensitivityCollision";
    static final String ERR_CHAMELEON_SCHEMA_GONE_WILD = "ERR_CHAMELEON_SCHEMA_GONE_WILD";

    Messages() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String format(String property, Object... args) {
        String text = ResourceBundle.getBundle(Messages.class.getPackage().getName() + ".MessageBundle").getString(property);
        return MessageFormat.format(text, args);
    }
}
