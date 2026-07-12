package com.sun.xml.internal.rngom.dt.builtin;

import com.sun.xml.internal.rngom.xml.util.WellKnownNamespaces;
import org.relaxng.datatype.DatatypeLibrary;
import org.relaxng.datatype.DatatypeLibraryFactory;

/* loaded from: target.jar:com/sun/xml/internal/rngom/dt/builtin/BuiltinDatatypeLibraryFactory.class */
public class BuiltinDatatypeLibraryFactory implements DatatypeLibraryFactory {
    private final DatatypeLibrary builtinDatatypeLibrary;
    private final DatatypeLibrary compatibilityDatatypeLibrary;
    private final DatatypeLibraryFactory core;

    public BuiltinDatatypeLibraryFactory(DatatypeLibraryFactory coreFactory) {
        this.builtinDatatypeLibrary = new BuiltinDatatypeLibrary(coreFactory);
        this.compatibilityDatatypeLibrary = new CompatibilityDatatypeLibrary(coreFactory);
        this.core = coreFactory;
    }

    @Override // org.relaxng.datatype.DatatypeLibraryFactory
    public DatatypeLibrary createDatatypeLibrary(String uri) {
        if (uri.equals("")) {
            return this.builtinDatatypeLibrary;
        }
        if (uri.equals(WellKnownNamespaces.RELAX_NG_COMPATIBILITY_DATATYPES)) {
            return this.compatibilityDatatypeLibrary;
        }
        return this.core.createDatatypeLibrary(uri);
    }
}
