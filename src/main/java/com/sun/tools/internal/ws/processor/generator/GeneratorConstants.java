package com.sun.tools.internal.ws.processor.generator;

import jdk.nashorn.internal.runtime.PropertyDescriptor;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/generator/GeneratorConstants.class */
public enum GeneratorConstants {
    DOTC(Constants.NAME_SEPARATOR),
    SIG_INNERCLASS("$"),
    JAVA_SRC_SUFFIX(Constants.SOURCE_FILE_EXTENSION),
    QNAME_SUFFIX("_QNAME"),
    GET(PropertyDescriptor.GET),
    IS("is"),
    RESPONSE("Response"),
    FAULT_CLASS_MEMBER_NAME("faultInfo");

    private String value;

    GeneratorConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
