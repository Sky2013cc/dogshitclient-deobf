package com.sun.tools.internal.ws.wsdl.document;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/Documentation.class */
public class Documentation {
    private String content;

    public Documentation(String s) {
        this.content = s;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String s) {
        this.content = s;
    }

    public void accept(WSDLDocumentVisitor visitor) throws Exception {
        visitor.visit(this);
    }
}
