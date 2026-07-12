package com.sun.tools.internal.xjc.model;

import com.sun.xml.internal.xsom.XSComponent;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/tools/internal/xjc/model/CCustomizable.class */
public interface CCustomizable {
    CCustomizations getCustomizations();

    Locator getLocator();

    XSComponent getSchemaComponent();
}
