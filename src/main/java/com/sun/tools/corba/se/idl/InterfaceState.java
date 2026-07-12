package com.sun.tools.corba.se.idl;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/InterfaceState.class */
public class InterfaceState {
    public static final int Private = 0;
    public static final int Protected = 1;
    public static final int Public = 2;
    public int modifier;
    public TypedefEntry entry;

    public InterfaceState(int i, TypedefEntry typedefEntry) {
        this.modifier = 2;
        this.entry = null;
        this.modifier = i;
        this.entry = typedefEntry;
        if (this.modifier < 0 || this.modifier > 2) {
            this.modifier = 2;
        }
    }
}
