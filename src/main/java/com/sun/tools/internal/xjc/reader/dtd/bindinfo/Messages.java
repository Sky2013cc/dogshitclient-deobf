package com.sun.tools.internal.xjc.reader.dtd.bindinfo;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/dtd/bindinfo/Messages.class */
class Messages {
    static final String ERR_UNDEFINED_FIELD = "BIConstructor.UndefinedField";

    Messages() {
    }

    static String format(String property, Object... args) {
        String text = ResourceBundle.getBundle(Messages.class.getPackage().getName() + ".MessageBundle").getString(property);
        return MessageFormat.format(text, args);
    }
}
