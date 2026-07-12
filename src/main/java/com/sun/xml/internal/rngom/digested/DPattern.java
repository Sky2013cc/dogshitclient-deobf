package com.sun.xml.internal.rngom.digested;

import com.sun.xml.internal.rngom.ast.om.ParsedPattern;
import com.sun.xml.internal.rngom.parse.Parseable;
import org.xml.sax.Locator;

/* loaded from: target.jar:com/sun/xml/internal/rngom/digested/DPattern.class */
public abstract class DPattern implements ParsedPattern {
    Locator location;
    DAnnotation annotation;
    DPattern next;
    DPattern prev;

    public abstract boolean isNullable();

    public abstract <V> V accept(DPatternVisitor<V> dPatternVisitor);

    public Locator getLocation() {
        return this.location;
    }

    public DAnnotation getAnnotation() {
        if (this.annotation == null) {
            return DAnnotation.EMPTY;
        }
        return this.annotation;
    }

    public Parseable createParseable() {
        return new PatternParseable(this);
    }

    public final boolean isElement() {
        return this instanceof DElementPattern;
    }

    public final boolean isAttribute() {
        return this instanceof DAttributePattern;
    }
}
