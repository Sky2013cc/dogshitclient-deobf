package com.sun.xml.internal.xsom;

import org.relaxng.datatype.ValidationContext;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/xml/internal/xsom/ForeignAttributes.class */
public interface ForeignAttributes extends Attributes {
    ValidationContext getContext();

    Locator getLocator();
}
