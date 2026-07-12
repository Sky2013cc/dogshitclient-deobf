package com.sun.tools.internal.xjc.model;

import com.sun.codemodel.internal.JExpression;
import com.sun.tools.internal.xjc.outline.Outline;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.xsom.XmlString;
import javax.activation.MimeType;

/* loaded from: target.jar:com/sun/tools/internal/xjc/model/TypeUse.class */
public interface TypeUse {
    boolean isCollection();

    CAdapter getAdapterUse();

    CNonElement getInfo();

    ID idUse();

    MimeType getExpectedMimeType();

    JExpression createConstant(Outline outline, XmlString xmlString);
}
