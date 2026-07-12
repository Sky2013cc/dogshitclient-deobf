package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;
import javax.xml.bind.annotation.XmlElement;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlElementWriter.class */
public interface XmlElementWriter extends JAnnotationWriter<XmlElement> {
    XmlElementWriter name(String str);

    XmlElementWriter type(Class cls);

    XmlElementWriter type(JType jType);

    XmlElementWriter namespace(String str);

    XmlElementWriter defaultValue(String str);

    XmlElementWriter required(boolean z);

    XmlElementWriter nillable(boolean z);
}
