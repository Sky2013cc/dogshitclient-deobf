package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;
import javax.xml.bind.annotation.XmlEnum;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlEnumWriter.class */
public interface XmlEnumWriter extends JAnnotationWriter<XmlEnum> {
    XmlEnumWriter value(Class cls);

    XmlEnumWriter value(JType jType);
}
