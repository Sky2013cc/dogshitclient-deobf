package com.sun.xml.internal.rngom.dt;

import org.relaxng.datatype.Datatype;
import org.relaxng.datatype.DatatypeBuilder;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.DatatypeLibrary;
import org.relaxng.datatype.DatatypeLibraryFactory;
import org.relaxng.datatype.DatatypeStreamingValidator;
import org.relaxng.datatype.ValidationContext;
import org.relaxng.datatype.helpers.StreamingValidatorImpl;

/* loaded from: target.jar:com/sun/xml/internal/rngom/dt/DoNothingDatatypeLibraryFactoryImpl.class */
public final class DoNothingDatatypeLibraryFactoryImpl implements DatatypeLibraryFactory {
    @Override // org.relaxng.datatype.DatatypeLibraryFactory
    public DatatypeLibrary createDatatypeLibrary(String s) {
        return new DatatypeLibrary() { // from class: com.sun.xml.internal.rngom.dt.DoNothingDatatypeLibraryFactoryImpl.1
            @Override // org.relaxng.datatype.DatatypeLibrary
            public Datatype createDatatype(String s2) throws DatatypeException {
                return createDatatypeBuilder(s2).createDatatype();
            }

            @Override // org.relaxng.datatype.DatatypeLibrary
            public DatatypeBuilder createDatatypeBuilder(String s2) throws DatatypeException {
                return new DatatypeBuilder() { // from class: com.sun.xml.internal.rngom.dt.DoNothingDatatypeLibraryFactoryImpl.1.1
                    @Override // org.relaxng.datatype.DatatypeBuilder
                    public void addParameter(String s3, String s1, ValidationContext validationContext) throws DatatypeException {
                    }

                    @Override // org.relaxng.datatype.DatatypeBuilder
                    public Datatype createDatatype() throws DatatypeException {
                        return new Datatype() { // from class: com.sun.xml.internal.rngom.dt.DoNothingDatatypeLibraryFactoryImpl.1.1.1
                            @Override // org.relaxng.datatype.Datatype
                            public boolean isValid(String s3, ValidationContext validationContext) {
                                return false;
                            }

                            @Override // org.relaxng.datatype.Datatype
                            public void checkValid(String s3, ValidationContext validationContext) throws DatatypeException {
                            }

                            @Override // org.relaxng.datatype.Datatype
                            public DatatypeStreamingValidator createStreamingValidator(ValidationContext validationContext) {
                                return new StreamingValidatorImpl(this, validationContext);
                            }

                            @Override // org.relaxng.datatype.Datatype
                            public Object createValue(String s3, ValidationContext validationContext) {
                                return null;
                            }

                            @Override // org.relaxng.datatype.Datatype
                            public boolean sameValue(Object o, Object o1) {
                                return false;
                            }

                            @Override // org.relaxng.datatype.Datatype
                            public int valueHashCode(Object o) {
                                return 0;
                            }

                            @Override // org.relaxng.datatype.Datatype
                            public int getIdType() {
                                return 0;
                            }

                            @Override // org.relaxng.datatype.Datatype
                            public boolean isContextDependent() {
                                return false;
                            }
                        };
                    }
                };
            }
        };
    }
}
