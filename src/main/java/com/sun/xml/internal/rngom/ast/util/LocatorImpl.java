package com.sun.xml.internal.rngom.ast.util;

import com.sun.xml.internal.rngom.ast.om.Location;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/xml/internal/rngom/ast/util/LocatorImpl.class */
public class LocatorImpl implements Locator, Location {
    private final String systemId;
    private final int lineNumber;
    private final int columnNumber;

    public LocatorImpl(String systemId, int lineNumber, int columnNumber) {
        this.systemId = systemId;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    @Override // org.xml.sax.Locator
    public String getPublicId() {
        return null;
    }

    @Override // org.xml.sax.Locator
    public String getSystemId() {
        return this.systemId;
    }

    @Override // org.xml.sax.Locator
    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // org.xml.sax.Locator
    public int getColumnNumber() {
        return this.columnNumber;
    }
}
