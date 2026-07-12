package com.sun.tools.internal.ws.wsdl.document;

import com.sun.tools.internal.ws.wsdl.framework.ExtensionVisitor;

/* loaded from: target.jar:com/sun/tools/internal/ws/wsdl/document/WSDLDocumentVisitor.class */
public interface WSDLDocumentVisitor extends ExtensionVisitor {
    void preVisit(Definitions definitions) throws Exception;

    void postVisit(Definitions definitions) throws Exception;

    void visit(Import r1) throws Exception;

    void preVisit(Types types) throws Exception;

    void postVisit(Types types) throws Exception;

    void preVisit(Message message) throws Exception;

    void postVisit(Message message) throws Exception;

    void visit(MessagePart messagePart) throws Exception;

    void preVisit(PortType portType) throws Exception;

    void postVisit(PortType portType) throws Exception;

    void preVisit(Operation operation) throws Exception;

    void postVisit(Operation operation) throws Exception;

    void preVisit(Input input) throws Exception;

    void postVisit(Input input) throws Exception;

    void preVisit(Output output) throws Exception;

    void postVisit(Output output) throws Exception;

    void preVisit(Fault fault) throws Exception;

    void postVisit(Fault fault) throws Exception;

    void preVisit(Binding binding) throws Exception;

    void postVisit(Binding binding) throws Exception;

    void preVisit(BindingOperation bindingOperation) throws Exception;

    void postVisit(BindingOperation bindingOperation) throws Exception;

    void preVisit(BindingInput bindingInput) throws Exception;

    void postVisit(BindingInput bindingInput) throws Exception;

    void preVisit(BindingOutput bindingOutput) throws Exception;

    void postVisit(BindingOutput bindingOutput) throws Exception;

    void preVisit(BindingFault bindingFault) throws Exception;

    void postVisit(BindingFault bindingFault) throws Exception;

    void preVisit(Service service) throws Exception;

    void postVisit(Service service) throws Exception;

    void preVisit(Port port) throws Exception;

    void postVisit(Port port) throws Exception;

    void visit(Documentation documentation) throws Exception;
}
