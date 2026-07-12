package com.sun.tools.internal.xjc.reader.xmlschema.bindinfo;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/bindinfo/EnumMemberMode.class */
public enum EnumMemberMode {
    SKIP,
    ERROR,
    GENERATE;

    public EnumMemberMode getModeWithEnum() {
        return this == SKIP ? ERROR : this;
    }
}
