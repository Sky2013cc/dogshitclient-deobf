package com.sun.xml.internal.xsom;

import com.sun.tools.internal.ws.wsdl.parser.Constants;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSVariety.class */
public final class XSVariety {
    public static final XSVariety ATOMIC = new XSVariety("atomic");
    public static final XSVariety UNION = new XSVariety(Constants.ATTRVALUE_UNION);
    public static final XSVariety LIST = new XSVariety(Constants.ATTRVALUE_LIST);
    private final String name;

    private XSVariety(String _name) {
        this.name = _name;
    }

    public String toString() {
        return this.name;
    }
}
