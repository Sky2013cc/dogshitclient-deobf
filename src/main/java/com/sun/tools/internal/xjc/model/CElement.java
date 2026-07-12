package com.sun.tools.internal.xjc.model;

import com.sun.tools.internal.xjc.model.nav.NClass;
import com.sun.tools.internal.xjc.model.nav.NType;
import com.sun.xml.internal.bind.v2.model.core.Element;

/* loaded from: target.jar:com/sun/tools/internal/xjc/model/CElement.class */
public interface CElement extends CTypeInfo, Element<NType, NClass> {
    void setAbstract();

    boolean isAbstract();
}
