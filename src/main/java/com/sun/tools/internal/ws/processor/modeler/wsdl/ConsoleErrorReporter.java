package com.sun.tools.internal.ws.processor.modeler.wsdl;

import com.sun.tools.internal.ws.resources.WscompileMessages;
import com.sun.tools.internal.ws.wscompile.ErrorReceiver;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.UnknownHostException;
import org.xml.sax.SAXParseException;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/modeler/wsdl/ConsoleErrorReporter.class */
public class ConsoleErrorReporter extends ErrorReceiver {
    private boolean hasError;
    private PrintStream output;
    private boolean debug;

    public ConsoleErrorReporter(PrintStream stream) {
        this.output = stream;
    }

    public ConsoleErrorReporter(OutputStream outputStream) {
        this.output = new PrintStream(outputStream);
    }

    public boolean hasError() {
        return this.hasError;
    }

    @Override // com.sun.tools.internal.ws.wscompile.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void error(SAXParseException e) {
        if (this.debug) {
            e.printStackTrace();
        }
        this.hasError = true;
        if (e.getSystemId() == null && e.getPublicId() == null && (e.getCause() instanceof UnknownHostException)) {
            print(WscompileMessages.WSIMPORT_ERROR_MESSAGE(e.toString()), e);
        } else {
            print(WscompileMessages.WSIMPORT_ERROR_MESSAGE(e.getMessage()), e);
        }
    }

    @Override // com.sun.tools.internal.ws.wscompile.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void fatalError(SAXParseException e) {
        if (this.debug) {
            e.printStackTrace();
        }
        this.hasError = true;
        print(WscompileMessages.WSIMPORT_ERROR_MESSAGE(e.getMessage()), e);
    }

    @Override // com.sun.tools.internal.ws.wscompile.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void warning(SAXParseException e) {
        print(WscompileMessages.WSIMPORT_WARNING_MESSAGE(e.getMessage()), e);
    }

    @Override // com.sun.tools.internal.ws.wscompile.ErrorReceiver, com.sun.tools.internal.xjc.api.ErrorListener
    public void info(SAXParseException e) {
        print(WscompileMessages.WSIMPORT_INFO_MESSAGE(e.getMessage()), e);
    }

    @Override // com.sun.tools.internal.ws.wscompile.ErrorReceiver
    public void debug(SAXParseException e) {
        print(WscompileMessages.WSIMPORT_DEBUG_MESSAGE(e.getMessage()), e);
    }

    private void print(String message, SAXParseException e) {
        this.output.println(message);
        this.output.println(getLocationString(e));
        this.output.println();
    }

    public void enableDebugging() {
        this.debug = true;
    }
}
