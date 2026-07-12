package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import javax.xml.bind.annotation.XmlElements;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlElementsWriter.class */
public interface XmlElementsWriter extends JAnnotationWriter<XmlElements> {
    XmlElementWriter value();
}
