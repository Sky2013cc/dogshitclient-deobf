package org.relaxng.datatype.helpers;

import org.relaxng.datatype.Datatype;
import org.relaxng.datatype.DatatypeBuilder;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;

/* loaded from: target.jar:org/relaxng/datatype/helpers/ParameterlessDatatypeBuilder.class */
public final class ParameterlessDatatypeBuilder implements DatatypeBuilder {
    private final Datatype baseType;

    public ParameterlessDatatypeBuilder(Datatype baseType) {
        this.baseType = baseType;
    }

    @Override // org.relaxng.datatype.DatatypeBuilder
    public void addParameter(String name, String strValue, ValidationContext context) throws DatatypeException {
        throw new DatatypeException();
    }

    @Override // org.relaxng.datatype.DatatypeBuilder
    public Datatype createDatatype() throws DatatypeException {
        return this.baseType;
    }
}
