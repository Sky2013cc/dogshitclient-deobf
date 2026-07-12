package com.sun.tools.internal.ws.processor.model.java;

import com.sun.tools.internal.ws.processor.model.jaxb.JAXBTypeAndAnnotation;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/java/JavaSimpleType.class */
public class JavaSimpleType extends JavaType {
    public JavaSimpleType() {
    }

    public JavaSimpleType(String name, String initString) {
        super(name, true, initString);
    }

    public JavaSimpleType(JAXBTypeAndAnnotation jtype) {
        super(jtype);
    }
}
