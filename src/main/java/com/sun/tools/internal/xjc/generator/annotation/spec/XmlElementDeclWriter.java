package com.sun.tools.internal.xjc.generator.annotation.spec;

import com.sun.codemodel.internal.JAnnotationWriter;
import com.sun.codemodel.internal.JType;
import javax.xml.bind.annotation.XmlElementDecl;

/* loaded from: target.jar:com/sun/tools/internal/xjc/generator/annotation/spec/XmlElementDeclWriter.class */
public interface XmlElementDeclWriter extends JAnnotationWriter<XmlElementDecl> {
    XmlElementDeclWriter name(String str);

    XmlElementDeclWriter scope(Class cls);

    XmlElementDeclWriter scope(JType jType);

    XmlElementDeclWriter namespace(String str);

    XmlElementDeclWriter defaultValue(String str);

    XmlElementDeclWriter substitutionHeadNamespace(String str);

    XmlElementDeclWriter substitutionHeadName(String str);
}
