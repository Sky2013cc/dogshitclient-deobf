package com.sun.tools.internal.ws.processor.model.jaxb;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/jaxb/JAXBTypeVisitor.class */
public interface JAXBTypeVisitor {
    void visit(JAXBType jAXBType) throws Exception;

    void visit(RpcLitStructure rpcLitStructure) throws Exception;
}
