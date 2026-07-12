package org.relaxng.datatype;

/* loaded from: target.jar:org/relaxng/datatype/DatatypeBuilder.class */
public interface DatatypeBuilder {
    void addParameter(String str, String str2, ValidationContext validationContext) throws DatatypeException;

    Datatype createDatatype() throws DatatypeException;
}
