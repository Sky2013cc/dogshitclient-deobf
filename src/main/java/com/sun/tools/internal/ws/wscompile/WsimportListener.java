package com.sun.tools.internal.ws.wscompile;

import com.sun.tools.internal.xjc.api.ErrorListener;
import org.xml.sax.SAXParseException;

/* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/WsimportListener.class */
public class WsimportListener implements ErrorListener {
    public void generatedFile(String fileName) {
    }

    public void message(String msg) {
    }

    @Override // com.sun.tools.internal.xjc.api.ErrorListener
    public void error(SAXParseException exception) {
    }

    @Override // com.sun.tools.internal.xjc.api.ErrorListener
    public void fatalError(SAXParseException exception) {
    }

    @Override // com.sun.tools.internal.xjc.api.ErrorListener
    public void warning(SAXParseException exception) {
    }

    @Override // com.sun.tools.internal.xjc.api.ErrorListener
    public void info(SAXParseException exception) {
    }

    public void debug(SAXParseException exception) {
    }

    public boolean isCanceled() {
        return false;
    }
}
