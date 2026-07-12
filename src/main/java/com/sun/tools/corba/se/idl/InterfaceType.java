package com.sun.tools.corba.se.idl;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/InterfaceType.class */
public interface InterfaceType {
    public static final int NORMAL = 0;
    public static final int ABSTRACT = 1;
    public static final int LOCAL = 2;
    public static final int LOCALSERVANT = 3;
    public static final int LOCAL_SIGNATURE_ONLY = 4;

    int getInterfaceType();

    void setInterfaceType(int i);
}
