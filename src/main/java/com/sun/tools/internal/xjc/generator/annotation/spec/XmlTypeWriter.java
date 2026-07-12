package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;
import javax.xml.bind.annotation.XmlType;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlTypeWriter.class */
public interface XmlTypeWriter extends JAnnotationWriter<XmlType> {
    XmlTypeWriter name(String str);

    XmlTypeWriter namespace(String str);

    XmlTypeWriter propOrder(String str);

    XmlTypeWriter factoryClass(Class cls);

    XmlTypeWriter factoryClass(JType jType);

    XmlTypeWriter factoryMethod(String str);
}
