package com.sun.tools.internal.xjc.generator.bean.field;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/bean/field/Messages.class */
public enum Messages {
    DEFAULT_GETTER_JAVADOC,
    DEFAULT_SETTER_JAVADOC;

    private static final ResourceBundle rb = ResourceBundle.getBundle(Messages.class.getName().substring(0, Messages.class.getName().lastIndexOf(46)) + ".MessageBundle");

    @Override // java.lang.Enum
    public String toString() {
        return format(new Object[0]);
    }

    public String format(Object... args) {
        return MessageFormat.format(rb.getString(name()), args);
    }
}
