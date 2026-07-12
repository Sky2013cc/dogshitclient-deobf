package com.sun.tools.internal.ws.wsdl.document.jaxws;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/jaxws/CustomName.class */
public class CustomName {
    private String javaDoc;
    private String name;

    public CustomName() {
    }

    public CustomName(String name, String javaDoc) {
        this.name = name;
        this.javaDoc = javaDoc;
    }

    public String getJavaDoc() {
        return this.javaDoc;
    }

    public void setJavaDoc(String javaDoc) {
        this.javaDoc = javaDoc;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
