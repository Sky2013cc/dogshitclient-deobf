package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlSchemaWriter.class */
public interface XmlSchemaWriter extends JAnnotationWriter<XmlSchema> {
    XmlSchemaWriter location(String str);

    XmlSchemaWriter namespace(String str);

    XmlNsWriter xmlns();

    XmlSchemaWriter elementFormDefault(XmlNsForm xmlNsForm);

    XmlSchemaWriter attributeFormDefault(XmlNsForm xmlNsForm);
}
