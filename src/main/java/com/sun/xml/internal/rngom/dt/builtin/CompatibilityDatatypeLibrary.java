package com.sun.xml.internal.rngom.dt.builtin;

import com.sun.xml.internal.dtdparser.DTDParser;
import com.sun.xml.internal.rngom.xml.util.WellKnownNamespaces;
import org.relaxng.datatype.Datatype;
import org.relaxng.datatype.DatatypeBuilder;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.DatatypeLibrary;
import org.relaxng.datatype.DatatypeLibraryFactory;

/* loaded from: target.jar:com/sun/xml/internal/rngom/dt/builtin/CompatibilityDatatypeLibrary.class */
class CompatibilityDatatypeLibrary implements DatatypeLibrary {
    private final DatatypeLibraryFactory factory;
    private DatatypeLibrary xsdDatatypeLibrary = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompatibilityDatatypeLibrary(DatatypeLibraryFactory factory) {
        this.factory = factory;
    }

    @Override // org.relaxng.datatype.DatatypeLibrary
    public DatatypeBuilder createDatatypeBuilder(String type) throws DatatypeException {
        if (type.equals("ID") || type.equals(DTDParser.TYPE_IDREF) || type.equals(DTDParser.TYPE_IDREFS)) {
            if (this.xsdDatatypeLibrary == null) {
                this.xsdDatatypeLibrary = this.factory.createDatatypeLibrary(WellKnownNamespaces.XML_SCHEMA_DATATYPES);
                if (this.xsdDatatypeLibrary == null) {
                    throw new DatatypeException();
                }
            }
            return this.xsdDatatypeLibrary.createDatatypeBuilder(type);
        }
        throw new DatatypeException();
    }

    @Override // org.relaxng.datatype.DatatypeLibrary
    public Datatype createDatatype(String type) throws DatatypeException {
        return createDatatypeBuilder(type).createDatatype();
    }
}
