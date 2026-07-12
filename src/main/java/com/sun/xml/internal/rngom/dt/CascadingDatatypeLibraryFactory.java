package com.sun.xml.internal.rngom.dt;

import org.relaxng.datatype.DatatypeLibrary;
import org.relaxng.datatype.DatatypeLibraryFactory;

/* loaded from: target.jar:com/sun/xml/internal/rngom/dt/CascadingDatatypeLibraryFactory.class */
public class CascadingDatatypeLibraryFactory implements DatatypeLibraryFactory {
    private final DatatypeLibraryFactory factory1;
    private final DatatypeLibraryFactory factory2;

    public CascadingDatatypeLibraryFactory(DatatypeLibraryFactory factory1, DatatypeLibraryFactory factory2) {
        this.factory1 = factory1;
        this.factory2 = factory2;
    }

    @Override // org.relaxng.datatype.DatatypeLibraryFactory
    public DatatypeLibrary createDatatypeLibrary(String namespaceURI) {
        DatatypeLibrary lib = this.factory1.createDatatypeLibrary(namespaceURI);
        if (lib == null) {
            lib = this.factory2.createDatatypeLibrary(namespaceURI);
        }
        return lib;
    }
}
