package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlJavaTypeAdapterWriter.class */
public interface XmlJavaTypeAdapterWriter extends JAnnotationWriter<XmlJavaTypeAdapter> {
    XmlJavaTypeAdapterWriter type(Class cls);

    XmlJavaTypeAdapterWriter type(JType jType);

    XmlJavaTypeAdapterWriter value(Class cls);

    XmlJavaTypeAdapterWriter value(JType jType);
}
