package com.sun.tools.internal.ws.wscompile;

import com.sun.tools.internal.xjc.api.ErrorListener;
import org.xml.sax.SAXParseException;

/* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/ErrorReceiverFilter.class */
public class ErrorReceiverFilter extends ErrorReceiver {
    private ErrorListener core;
    private boolean hadError = false;

    public ErrorReceiverFilter() {
    }

    public ErrorReceiverFilter(ErrorListener h) {
        setErrorReceiver(h);
    }

    public void setErrorReceiver(ErrorListener handler) {
        this.core = handler;
    }

    public final boolean hadError() {
        return this.hadError;
    }

    public void reset() {
        this.hadError = false;
    }

    @Override // com.sun.tools.internal.ws.wscompile.ErrorReceiver, com.sun.tools.internal.xjc.api.ErrorListener
    public void info(SAXParseException exception) {
        if (this.core != null) {
            this.core.info(exception);
        }
    }

    @Override // com.sun.tools.internal.ws.wscompile.ErrorReceiver
    public void debug(SAXParseException exception) {
    }

    @Override // com.sun.tools.internal.ws.wscompile.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void warning(SAXParseException exception) {
        if (this.core != null) {
            this.core.warning(exception);
        }
    }

    @Override // com.sun.tools.internal.ws.wscompile.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void error(SAXParseException exception) {
        this.hadError = true;
        if (this.core != null) {
            this.core.error(exception);
        }
    }

    @Override // com.sun.tools.internal.ws.wscompile.ErrorReceiver, org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public void fatalError(SAXParseException exception) {
        this.hadError = true;
        if (this.core != null) {
            this.core.fatalError(exception);
        }
    }
}
