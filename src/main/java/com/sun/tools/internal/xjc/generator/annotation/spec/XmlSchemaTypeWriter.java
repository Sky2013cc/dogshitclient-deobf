package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;
import javax.xml.bind.annotation.XmlSchemaType;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlSchemaTypeWriter.class */
public interface XmlSchemaTypeWriter extends JAnnotationWriter<XmlSchemaType> {
    XmlSchemaTypeWriter name(String str);

    XmlSchemaTypeWriter type(Class cls);

    XmlSchemaTypeWriter type(JType jType);

    XmlSchemaTypeWriter namespace(String str);
}
