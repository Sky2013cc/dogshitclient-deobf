package com.sun.xml.internal.rngom.dt;

import org.relaxng.datatype.DatatypeLibrary;
import org.relaxng.datatype.DatatypeLibraryFactory;

/* loaded from: target.jar:com/sun/xml/internal/rngom/dt/CachedDatatypeLibraryFactory.class */
public class CachedDatatypeLibraryFactory implements DatatypeLibraryFactory {
    private String lastUri;
    private DatatypeLibrary lastLib;
    private final DatatypeLibraryFactory core;

    public CachedDatatypeLibraryFactory(DatatypeLibraryFactory core) {
        this.core = core;
    }

    @Override // org.relaxng.datatype.DatatypeLibraryFactory
    public DatatypeLibrary createDatatypeLibrary(String namespaceURI) {
        if (this.lastUri == namespaceURI) {
            return this.lastLib;
        }
        this.lastUri = namespaceURI;
        this.lastLib = this.core.createDatatypeLibrary(namespaceURI);
        return this.lastLib;
    }
}
