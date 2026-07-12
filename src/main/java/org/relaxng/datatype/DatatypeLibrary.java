package org.relaxng.datatype;

/* loaded from: target.jar:org/relaxng/datatype/DatatypeLibrary.class */
public interface DatatypeLibrary {
    DatatypeBuilder createDatatypeBuilder(String str) throws DatatypeException;

    Datatype createDatatype(String str) throws DatatypeException;
}
