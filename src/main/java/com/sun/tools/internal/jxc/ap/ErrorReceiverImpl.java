package com.sun.tools.internal.jxc.ap;

import com.sun.tools.internal.xjc.ErrorReceiver;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;
import org.xml.sax.SAXParseException;

/* loaded from: target.jar:com/sun/tools/internal/jxc/ap/ErrorReceiverImpl.class */
final class ErrorReceiverImpl extends ErrorReceiver {
    private final Messager messager;
    private final boolean debug;

    public ErrorReceiverImpl(Messager messager, boolean debug) {
        this.messager = messager;
        this.debug = debug;
    }

    public ErrorReceiverImpl(Messager messager) {
        this(messager, false);
    }

    public ErrorReceiverImpl(ProcessingEnvironment env) {
        this(env.getMessager());
    }

    @Override // com.sun.tools.internal.xjc.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void error(SAXParseException exception) {
        this.messager.printMessage(Diagnostic.Kind.ERROR, exception.getMessage());
        this.messager.printMessage(Diagnostic.Kind.ERROR, getLocation(exception));
        printDetail(exception);
    }

    @Override // com.sun.tools.internal.xjc.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void fatalError(SAXParseException exception) {
        this.messager.printMessage(Diagnostic.Kind.ERROR, exception.getMessage());
        this.messager.printMessage(Diagnostic.Kind.ERROR, getLocation(exception));
        printDetail(exception);
    }

    @Override // com.sun.tools.internal.xjc.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void warning(SAXParseException exception) {
        this.messager.printMessage(Diagnostic.Kind.WARNING, exception.getMessage());
        this.messager.printMessage(Diagnostic.Kind.WARNING, getLocation(exception));
        printDetail(exception);
    }

    @Override // com.sun.tools.internal.xjc.ErrorReceiver, com.sun.tools.internal.xjc.api.ErrorListener
    public void info(SAXParseException exception) {
        printDetail(exception);
    }

    private String getLocation(SAXParseException e) {
        return "";
    }

    private void printDetail(SAXParseException e) {
        if (this.debug) {
            e.printStackTrace(System.out);
        }
    }
}
