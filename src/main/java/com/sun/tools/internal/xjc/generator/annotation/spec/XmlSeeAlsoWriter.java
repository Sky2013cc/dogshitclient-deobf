package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;
import javax.xml.bind.annotation.XmlSeeAlso;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlSeeAlsoWriter.class */
public interface XmlSeeAlsoWriter extends JAnnotationWriter<XmlSeeAlso> {
    XmlSeeAlsoWriter value(Class cls);

    XmlSeeAlsoWriter value(JType jType);
}
