package org.relaxng.datatype;

/* loaded from: target.jar:org/relaxng/datatype/DatatypeStreamingValidator.class */
public interface DatatypeStreamingValidator {
    void addCharacters(char[] cArr, int i, int i2);

    boolean isValid();

    void checkValid() throws DatatypeException;
}
