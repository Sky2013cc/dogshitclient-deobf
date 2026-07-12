package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/Messages.class */
enum Messages {
    ERR_CANNOT_BE_BOUND_TO_SIMPLETYPE,
    ERR_UNDEFINED_SIMPLE_TYPE,
    ERR_ILLEGAL_FIXEDATTR;

    /* JADX INFO: Access modifiers changed from: package-private */
    public String format(Object... args) {
        String text = ResourceBundle.getBundle(Messages.class.getPackage().getName() + ".MessageBundle").getString(name());
        return MessageFormat.format(text, args);
    }
}
