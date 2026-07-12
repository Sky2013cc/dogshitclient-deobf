package com.sun.xml.internal.xsom.parser;

import com.sun.tools.internal.ws.wsdl.parser.Constants;

/* loaded from: target.jar:com/sun/xml/internal/xsom/parser/AnnotationContext.class */
public final class AnnotationContext {
    private final String name;
    public static final AnnotationContext SCHEMA = new AnnotationContext("schema");
    public static final AnnotationContext NOTATION = new AnnotationContext("notation");
    public static final AnnotationContext ELEMENT_DECL = new AnnotationContext(Constants.ATTR_ELEMENT);
    public static final AnnotationContext IDENTITY_CONSTRAINT = new AnnotationContext("identityConstraint");
    public static final AnnotationContext XPATH = new AnnotationContext(Constants.ATTR_XPATH);
    public static final AnnotationContext MODELGROUP_DECL = new AnnotationContext("modelGroupDecl");
    public static final AnnotationContext SIMPLETYPE_DECL = new AnnotationContext("simpleTypeDecl");
    public static final AnnotationContext COMPLEXTYPE_DECL = new AnnotationContext("complexTypeDecl");
    public static final AnnotationContext PARTICLE = new AnnotationContext("particle");
    public static final AnnotationContext MODELGROUP = new AnnotationContext("modelGroup");
    public static final AnnotationContext ATTRIBUTE_USE = new AnnotationContext("attributeUse");
    public static final AnnotationContext WILDCARD = new AnnotationContext("wildcard");
    public static final AnnotationContext ATTRIBUTE_GROUP = new AnnotationContext("attributeGroup");
    public static final AnnotationContext ATTRIBUTE_DECL = new AnnotationContext("attributeDecl");

    private AnnotationContext(String _name) {
        this.name = _name;
    }

    public String toString() {
        return this.name;
    }
}
