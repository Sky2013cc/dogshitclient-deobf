package com.sun.xml.internal.rngom.parse;

import java.util.Enumeration;
import org.relaxng.datatype.ValidationContext;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/Context.class */
public interface Context extends ValidationContext {
    Enumeration prefixes();

    Context copy();
}
