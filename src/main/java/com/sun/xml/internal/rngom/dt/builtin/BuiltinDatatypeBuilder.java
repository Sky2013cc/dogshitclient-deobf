package com.sun.xml.internal.rngom.dt.builtin;

import com.sun.xml.internal.rngom.util.Localizer;
import org.relaxng.datatype.Datatype;
import org.relaxng.datatype.DatatypeBuilder;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;

/* loaded from: target.jar:com/sun/xml/internal/rngom/dt/builtin/BuiltinDatatypeBuilder.class */
class BuiltinDatatypeBuilder implements DatatypeBuilder {
    private final Datatype dt;
    private static final Localizer localizer = new Localizer(BuiltinDatatypeBuilder.class);

    /* JADX INFO: Access modifiers changed from: package-private */
    public BuiltinDatatypeBuilder(Datatype dt) {
        this.dt = dt;
    }

    @Override // org.relaxng.datatype.DatatypeBuilder
    public void addParameter(String name, String value, ValidationContext context) throws DatatypeException {
        throw new DatatypeException(localizer.message("builtin_param"));
    }

    @Override // org.relaxng.datatype.DatatypeBuilder
    public Datatype createDatatype() {
        return this.dt;
    }
}
