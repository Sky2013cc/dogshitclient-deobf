package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;
import javax.xml.bind.annotation.XmlAnyElement;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlAnyElementWriter.class */
public interface XmlAnyElementWriter extends JAnnotationWriter<XmlAnyElement> {
    XmlAnyElementWriter value(Class cls);

    XmlAnyElementWriter value(JType jType);

    XmlAnyElementWriter lax(boolean z);
}
