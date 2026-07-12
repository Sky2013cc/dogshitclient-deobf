package com.sun.xml.internal.rngom.dt.builtin;

import com.sun.xml.internal.rngom.xml.util.WellKnownNamespaces;
import org.relaxng.datatype.Datatype;
import org.relaxng.datatype.DatatypeBuilder;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.DatatypeLibrary;
import org.relaxng.datatype.DatatypeLibraryFactory;

/* loaded from: target.jar:com/sun/xml/internal/rngom/dt/builtin/BuiltinDatatypeLibrary.class */
public class BuiltinDatatypeLibrary implements DatatypeLibrary {
    private final DatatypeLibraryFactory factory;
    private DatatypeLibrary xsdDatatypeLibrary = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BuiltinDatatypeLibrary(DatatypeLibraryFactory factory) {
        this.factory = factory;
    }

    @Override // org.relaxng.datatype.DatatypeLibrary
    public DatatypeBuilder createDatatypeBuilder(String type) throws DatatypeException {
        this.xsdDatatypeLibrary = this.factory.createDatatypeLibrary(WellKnownNamespaces.XML_SCHEMA_DATATYPES);
        if (this.xsdDatatypeLibrary == null) {
            throw new DatatypeException();
        }
        if (type.equals("string") || type.equals("token")) {
            return new BuiltinDatatypeBuilder(this.xsdDatatypeLibrary.createDatatype(type));
        }
        throw new DatatypeException();
    }

    @Override // org.relaxng.datatype.DatatypeLibrary
    public Datatype createDatatype(String type) throws DatatypeException {
        return createDatatypeBuilder(type).createDatatype();
    }
}
