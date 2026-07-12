package org.relaxng.datatype;

/* loaded from: target.jar:org/relaxng/datatype/ValidationContext.class */
public interface ValidationContext {
    String resolveNamespacePrefix(String str);

    String getBaseUri();

    boolean isUnparsedEntity(String str);

    boolean isNotation(String str);
}
