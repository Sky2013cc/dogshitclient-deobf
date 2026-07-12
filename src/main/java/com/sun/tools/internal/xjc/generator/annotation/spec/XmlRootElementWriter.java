package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import javax.xml.bind.annotation.XmlRootElement;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlRootElementWriter.class */
public interface XmlRootElementWriter extends JAnnotationWriter<XmlRootElement> {
    XmlRootElementWriter name(String str);

    XmlRootElementWriter namespace(String str);
}
