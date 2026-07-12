package com.sun.xml.internal.rngom.parse.host;

import com.sun.xml.internal.rngom.ast.om.Location;

/* loaded from: target.jar:com/sun/xml/internal/rngom/parse/host/LocationHost.class */
final class LocationHost implements Location {
    final Location lhs;
    final Location rhs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationHost(Location lhs, Location rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
