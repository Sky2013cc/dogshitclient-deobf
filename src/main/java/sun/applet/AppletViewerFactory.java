package sun.applet;

import java.awt.MenuBar;
import java.net.URL;
import java.util.Hashtable;

/* loaded from: target.jar:sun/applet/AppletViewerFactory.class */
public interface AppletViewerFactory {
    AppletViewer createAppletViewer(int i, int i2, URL url, Hashtable hashtable);

    MenuBar getBaseMenuBar();

    boolean isStandalone();
}
