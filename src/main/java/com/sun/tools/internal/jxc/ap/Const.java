package com.sun.tools.internal.jxc.ap;

/* loaded from: target.jar:com/sun/tools/internal/jxc/ap/Const.class */
public enum Const {
    CONFIG_FILE_OPTION("jaxb.config"),
    DEBUG_OPTION("jaxb.debug");

    private String value;

    Const(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
