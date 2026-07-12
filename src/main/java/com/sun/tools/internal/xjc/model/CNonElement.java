package com.sun.tools.internal.xjc.model;

import com.sun.tools.internal.xjc.model.nav.NClass;
import com.sun.tools.internal.xjc.model.nav.NType;
import com.sun.xml.internal.bind.v2.model.core.NonElement;

/* loaded from: target.jar:com/sun/tools/internal/xjc/model/CNonElement.class */
public interface CNonElement extends NonElement<NType, NClass>, TypeUse, CTypeInfo {
    @Deprecated
    CNonElement getInfo();

    @Deprecated
    boolean isCollection();

    @Deprecated
    CAdapter getAdapterUse();
}
