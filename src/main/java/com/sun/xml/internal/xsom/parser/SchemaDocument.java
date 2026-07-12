package com.sun.xml.internal.xsom.parser;

import com.sun.xml.internal.xsom.XSSchema;
import java.util.Set;

/* loaded from: target.jar:com/sun/xml/internal/xsom/parser/SchemaDocument.class */
public interface SchemaDocument {
    String getSystemId();

    String getTargetNamespace();

    XSSchema getSchema();

    Set<SchemaDocument> getReferencedDocuments();

    Set<SchemaDocument> getIncludedDocuments();

    Set<SchemaDocument> getImportedDocuments(String str);

    boolean includes(SchemaDocument schemaDocument);

    boolean imports(SchemaDocument schemaDocument);

    Set<SchemaDocument> getReferers();
}
