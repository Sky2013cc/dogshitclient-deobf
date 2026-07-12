package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlAccessorTypeWriter.class */
public interface XmlAccessorTypeWriter extends JAnnotationWriter<XmlAccessorType> {
    XmlAccessorTypeWriter value(XmlAccessType xmlAccessType);
}
