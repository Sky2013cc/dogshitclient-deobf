package com.sun.xml.internal.xsom;

import java.math.BigInteger;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSParticle.class */
public interface XSParticle extends XSContentType {
    public static final int UNBOUNDED = -1;

    BigInteger getMinOccurs();

    BigInteger getMaxOccurs();

    boolean isRepeated();

    XSTerm getTerm();
}
