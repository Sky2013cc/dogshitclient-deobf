package sun.applet;

import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:sun/applet/AppletViewerPanel.class */
class AppletViewerPanel extends AppletPanel {
    static boolean debug = false;
    URL documentURL;
    URL baseURL;
    Hashtable atts;
    private static final long serialVersionUID = 8890989370785545619L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppletViewerPanel(URL url, Hashtable hashtable) {
        String file;
        int lastIndexOf;
        this.documentURL = url;
        this.atts = hashtable;
        String parameter = getParameter("codebase");
        if (parameter != null) {
            try {
                this.baseURL = new URL(url, parameter.endsWith(RuntimeConstants.SIG_PACKAGE) ? parameter : parameter + RuntimeConstants.SIG_PACKAGE);
            } catch (MalformedURLException e) {
            }
        }
        if (this.baseURL == null && (lastIndexOf = (file = url.getFile()).lastIndexOf(47)) >= 0 && lastIndexOf < file.length() - 1) {
            try {
                this.baseURL = new URL(url, file.substring(0, lastIndexOf + 1));
            } catch (MalformedURLException e2) {
            }
        }
        if (this.baseURL == null) {
            this.baseURL = url;
        }
    }

    public String getParameter(String str) {
        return (String) this.atts.get(str.toLowerCase());
    }

    public URL getDocumentBase() {
        return this.documentURL;
    }

    public URL getCodeBase() {
        return this.baseURL;
    }

    @Override // sun.applet.AppletPanel
    public int getWidth() {
        String parameter = getParameter("width");
        if (parameter != null) {
            return Integer.valueOf(parameter).intValue();
        }
        return 0;
    }

    @Override // sun.applet.AppletPanel
    public int getHeight() {
        String parameter = getParameter("height");
        if (parameter != null) {
            return Integer.valueOf(parameter).intValue();
        }
        return 0;
    }

    @Override // sun.applet.AppletPanel
    public boolean hasInitialFocus() {
        if (isJDK11Applet() || isJDK12Applet()) {
            return false;
        }
        String parameter = getParameter("initial_focus");
        if (parameter != null && parameter.toLowerCase().equals(Constants.FALSE)) {
            return false;
        }
        return true;
    }

    @Override // sun.applet.AppletPanel
    public String getCode() {
        return getParameter("code");
    }

    @Override // sun.applet.AppletPanel
    public String getJarFiles() {
        return getParameter("archive");
    }

    @Override // sun.applet.AppletPanel
    public String getSerializedObject() {
        return getParameter("object");
    }

    public AppletContext getAppletContext() {
        return getParent();
    }

    static void debug(String str) {
        if (debug) {
            System.err.println("AppletViewerPanel:::" + str);
        }
    }

    static void debug(String str, Throwable th) {
        if (debug) {
            th.printStackTrace();
            debug(str);
        }
    }
}
