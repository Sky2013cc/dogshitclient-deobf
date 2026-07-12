package com.sun.tools.internal.xjc.api;

import java.util.List;
import javax.xml.namespace.QName;

/* loaded from: target.jar:com/sun/tools/internal/xjc/api/Mapping.class */
public interface Mapping {
    QName getElement();

    TypeAndAnnotation getType();

    List<? extends Property> getWrapperStyleDrilldown();
}
