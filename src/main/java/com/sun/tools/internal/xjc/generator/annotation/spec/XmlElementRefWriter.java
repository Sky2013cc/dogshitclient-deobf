package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;
import javax.xml.bind.annotation.XmlElementRef;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlElementRefWriter.class */
public interface XmlElementRefWriter extends JAnnotationWriter<XmlElementRef> {
    XmlElementRefWriter name(String str);

    XmlElementRefWriter type(Class cls);

    XmlElementRefWriter type(JType jType);

    XmlElementRefWriter namespace(String str);

    XmlElementRefWriter required(boolean z);
}
