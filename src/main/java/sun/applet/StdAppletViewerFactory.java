package sun.applet;

import java.awt.MenuBar;
import java.net.URL;
import java.util.Hashtable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AppletViewer.java */
/* loaded from: target.jar:sun/applet/StdAppletViewerFactory.class */
public final class StdAppletViewerFactory implements AppletViewerFactory {
    @Override // sun.applet.AppletViewerFactory
    public AppletViewer createAppletViewer(int i, int i2, URL url, Hashtable hashtable) {
        return new AppletViewer(i, i2, url, hashtable, System.out, this);
    }

    @Override // sun.applet.AppletViewerFactory
    public MenuBar getBaseMenuBar() {
        return new MenuBar();
    }

    @Override // sun.applet.AppletViewerFactory
    public boolean isStandalone() {
        return true;
    }
}
