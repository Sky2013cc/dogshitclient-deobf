package com.sun.tools.internal.xjc.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/* loaded from: target.jar:com/sun/tools/internal/xjc/util/ReadOnlyAdapter.class */
public abstract class ReadOnlyAdapter<OnTheWire, InMemory> extends XmlAdapter<OnTheWire, InMemory> {
    public final OnTheWire marshal(InMemory onTheWire) {
        return null;
    }
}
