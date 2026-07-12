package com.sun.tools.internal.xjc.generator.bean;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/bean/Messages.class */
enum Messages {
    METHOD_COLLISION,
    ERR_UNUSABLE_NAME,
    ERR_KEYNAME_COLLISION,
    ERR_NAME_COLLISION,
    ILLEGAL_CONSTRUCTOR_PARAM,
    OBJECT_FACTORY_CONFLICT,
    OBJECT_FACTORY_CONFLICT_RELATED;

    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getPackage().getName() + ".MessageBundle");

    @Override // java.lang.Enum
    public String toString() {
        return format(new Object[0]);
    }

    public String format(Object... args) {
        return MessageFormat.format(rb.getString(name()), args);
    }
}
