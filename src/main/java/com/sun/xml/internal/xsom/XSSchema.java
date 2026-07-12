package com.sun.xml.internal.xsom;

import com.sun.xml.internal.xsom.parser.SchemaDocument;
import java.util.Iterator;
import java.util.Map;

/* loaded from: target.jar:com/sun/xml/internal/xsom/XSSchema.class */
public interface XSSchema extends XSComponent {
    String getTargetNamespace();

    Map<String, XSAttributeDecl> getAttributeDecls();

    Iterator<XSAttributeDecl> iterateAttributeDecls();

    XSAttributeDecl getAttributeDecl(String str);

    Map<String, XSElementDecl> getElementDecls();

    Iterator<XSElementDecl> iterateElementDecls();

    XSElementDecl getElementDecl(String str);

    Map<String, XSAttGroupDecl> getAttGroupDecls();

    Iterator<XSAttGroupDecl> iterateAttGroupDecls();

    XSAttGroupDecl getAttGroupDecl(String str);

    Map<String, XSModelGroupDecl> getModelGroupDecls();

    Iterator<XSModelGroupDecl> iterateModelGroupDecls();

    XSModelGroupDecl getModelGroupDecl(String str);

    Map<String, XSType> getTypes();

    Iterator<XSType> iterateTypes();

    XSType getType(String str);

    Map<String, XSSimpleType> getSimpleTypes();

    Iterator<XSSimpleType> iterateSimpleTypes();

    XSSimpleType getSimpleType(String str);

    Map<String, XSComplexType> getComplexTypes();

    Iterator<XSComplexType> iterateComplexTypes();

    XSComplexType getComplexType(String str);

    Map<String, XSNotation> getNotations();

    Iterator<XSNotation> iterateNotations();

    XSNotation getNotation(String str);

    Map<String, XSIdentityConstraint> getIdentityConstraints();

    XSIdentityConstraint getIdentityConstraint(String str);

    @Override // com.sun.xml.internal.xsom.XSComponent
    SchemaDocument getSourceDocument();

    @Override // com.sun.xml.internal.xsom.XSComponent
    XSSchemaSet getRoot();
}
