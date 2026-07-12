package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import javax.xml.bind.annotation.XmlElementWrapper;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlElementWrapperWriter.class */
public interface XmlElementWrapperWriter extends JAnnotationWriter<XmlElementWrapper> {
    XmlElementWrapperWriter name(String str);

    XmlElementWrapperWriter namespace(String str);

    XmlElementWrapperWriter required(boolean z);

    XmlElementWrapperWriter nillable(boolean z);
}
