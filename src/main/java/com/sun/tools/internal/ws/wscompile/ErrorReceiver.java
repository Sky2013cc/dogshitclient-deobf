package com.sun.tools.internal.ws.wscompile;

import com.sun.istack.internal.Nullable;
import com.sun.istack.internal.SAXParseException2;
import com.sun.tools.internal.ws.resources.ModelMessages;
import com.sun.tools.internal.xjc.api.ErrorListener;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

/* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/ErrorReceiver.class */
public abstract class ErrorReceiver implements ErrorHandler, ErrorListener {
    @Override // org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public abstract void error(SAXParseException sAXParseException) throws AbortException;

    @Override // org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public abstract void fatalError(SAXParseException sAXParseException) throws AbortException;

    @Override // org.xml.sax.ErrorHandler, com.sun.tools.internal.xjc.api.ErrorListener
    public abstract void warning(SAXParseException sAXParseException) throws AbortException;

    public abstract void info(SAXParseException sAXParseException);

    public abstract void debug(SAXParseException sAXParseException);

    public final void error(Locator loc, String msg) {
        error((SAXParseException) new SAXParseException2(msg, loc));
    }

    public final void error(Locator loc, String msg, Exception e) {
        error((SAXParseException) new SAXParseException2(msg, loc, e));
    }

    public final void error(String msg, Exception e) {
        error((SAXParseException) new SAXParseException2(msg, (Locator) null, e));
    }

    public void error(Exception e) {
        error(e.getMessage(), e);
    }

    public final void warning(@Nullable Locator loc, String msg) {
        warning(new SAXParseException(msg, loc));
    }

    public void pollAbort() throws AbortException {
    }

    public final void debug(String msg) {
        info(new SAXParseException(msg, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getLocationString(SAXParseException e) {
        if (e.getLineNumber() != -1 || e.getSystemId() != null) {
            int line = e.getLineNumber();
            return ModelMessages.CONSOLE_ERROR_REPORTER_LINE_X_OF_Y(line == -1 ? "?" : Integer.toString(line), getShortName(e.getSystemId()));
        }
        return "";
    }

    private String getShortName(String url) {
        if (url == null) {
            return ModelMessages.CONSOLE_ERROR_REPORTER_UNKNOWN_LOCATION();
        }
        return url;
    }
}
