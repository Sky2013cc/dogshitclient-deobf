package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import javax.xml.bind.annotation.XmlElementRefs;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlElementRefsWriter.class */
public interface XmlElementRefsWriter extends JAnnotationWriter<XmlElementRefs> {
    XmlElementRefWriter value();
}
