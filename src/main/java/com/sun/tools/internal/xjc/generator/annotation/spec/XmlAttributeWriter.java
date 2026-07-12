package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import javax.xml.bind.annotation.XmlAttribute;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlAttributeWriter.class */
public interface XmlAttributeWriter extends JAnnotationWriter<XmlAttribute> {
    XmlAttributeWriter name(String str);

    XmlAttributeWriter namespace(String str);

    XmlAttributeWriter required(boolean z);
}
