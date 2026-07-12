package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.builder.Annotations;
import com.sun.xml.internal.rngom.ast.om.Location;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/host/Base.class */
public class Base {
    private static final AnnotationsHost nullAnnotations = new AnnotationsHost(null, null);
    private static final LocationHost nullLocation = new LocationHost(null, null);

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotationsHost cast(Annotations ann) {
        if (ann == null) {
            return nullAnnotations;
        }
        return (AnnotationsHost) ann;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LocationHost cast(Location loc) {
        if (loc == null) {
            return nullLocation;
        }
        return (LocationHost) loc;
    }
}
