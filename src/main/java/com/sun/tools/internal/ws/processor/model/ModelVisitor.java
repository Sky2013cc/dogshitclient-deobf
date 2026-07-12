package com.sun.tools.internal.ws.processor.model;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/ModelVisitor.class */
public interface ModelVisitor {
    void visit(Model model) throws Exception;

    void visit(Service service) throws Exception;

    void visit(Port port) throws Exception;

    void visit(Operation operation) throws Exception;

    void visit(Request request) throws Exception;

    void visit(Response response) throws Exception;

    void visit(Fault fault) throws Exception;

    void visit(Block block) throws Exception;

    void visit(Parameter parameter) throws Exception;
}
