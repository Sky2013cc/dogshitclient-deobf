package com.sun.xml.internal.xsom.impl;

import com.sun.xml.internal.xsom.ForeignAttributes;
import org.relaxng.datatype.ValidationContext;
import org.xml.sax.Locator;
import org.xml.sax.helpers.AttributesImpl;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/ForeignAttributesImpl.class */
public final class ForeignAttributesImpl extends AttributesImpl implements ForeignAttributes {
    private final ValidationContext context;
    private final Locator locator;
    final ForeignAttributesImpl next;

    public ForeignAttributesImpl(ValidationContext context, Locator locator, ForeignAttributesImpl next) {
        this.context = context;
        this.locator = locator;
        this.next = next;
    }

    @Override // com.sun.xml.internal.xsom.ForeignAttributes
    public ValidationContext getContext() {
        return this.context;
    }

    @Override // com.sun.xml.internal.xsom.ForeignAttributes
    public Locator getLocator() {
        return this.locator;
    }
}
