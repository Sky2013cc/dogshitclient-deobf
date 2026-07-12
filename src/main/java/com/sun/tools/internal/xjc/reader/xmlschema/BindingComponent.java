package com.sun.tools.internal.xjc.reader.xmlschema;

import com.sun.tools.internal.xjc.reader.Ring;

/* loaded from: target.jar:com/sun/tools/internal/xjc/reader/xmlschema/BindingComponent.class */
public abstract class BindingComponent {
    /* JADX INFO: Access modifiers changed from: protected */
    public BindingComponent() {
        Ring.add(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ErrorReporter getErrorReporter() {
        return (ErrorReporter) Ring.get(ErrorReporter.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ClassSelector getClassSelector() {
        return (ClassSelector) Ring.get(ClassSelector.class);
    }
}
