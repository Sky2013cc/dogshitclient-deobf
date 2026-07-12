package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import javax.xml.bind.annotation.XmlNs;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlNsWriter.class */
public interface XmlNsWriter extends JAnnotationWriter<XmlNs> {
    XmlNsWriter prefix(String str);

    XmlNsWriter namespaceURI(String str);
}
