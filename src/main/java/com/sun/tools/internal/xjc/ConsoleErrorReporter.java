package com.sun.tools.internal.xjc;

import java.io.OutputStream;
import java.io.PrintStream;
import org.xml.sax.SAXParseException;

/* loaded from: target.jar:com/sun/tools/internal/xjc/ConsoleErrorReporter.class */
public class ConsoleErrorReporter extends ErrorReceiver {
    private PrintStream output;
    private boolean hadError;

    public ConsoleErrorReporter(PrintStream out) {
        this.hadError = false;
        this.output = out;
    }

    public ConsoleErrorReporter(OutputStream out) {
        this(new PrintStream(out));
    }

    public ConsoleErrorReporter() {
        this(System.out);
    }

    @Override // com.sun.tools.internal.xjc.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void warning(SAXParseException e) {
        print("Driver.WarningMessage", e);
    }

    @Override // com.sun.tools.internal.xjc.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void error(SAXParseException e) {
        this.hadError = true;
        print("Driver.ErrorMessage", e);
    }

    @Override // com.sun.tools.internal.xjc.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void fatalError(SAXParseException e) {
        this.hadError = true;
        print("Driver.ErrorMessage", e);
    }

    @Override // com.sun.tools.internal.xjc.ErrorReceiver, com.sun.tools.internal.xjc.api.ErrorListener
    public void info(SAXParseException e) {
        print("Driver.InfoMessage", e);
    }

    public boolean hadError() {
        return this.hadError;
    }

    private void print(String resource, SAXParseException e) {
        this.output.println(Messages.format(resource, e.getMessage()));
        this.output.println(getLocationString(e));
        this.output.println();
    }
}
