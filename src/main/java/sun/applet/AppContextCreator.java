package sun.applet;

import sun.awt.AppContext;
import sun.awt.SunToolkit;

/* compiled from: AppletClassLoader.java */
/* loaded from: target.jar:sun/applet/AppContextCreator.class */
class AppContextCreator extends Thread {
    Object syncObject;
    AppContext appContext;
    volatile boolean created;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppContextCreator(ThreadGroup threadGroup) {
        super(threadGroup, "AppContextCreator");
        this.syncObject = new Object();
        this.appContext = null;
        this.created = false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.appContext = SunToolkit.createNewAppContext();
        this.created = true;
        synchronized (this.syncObject) {
            this.syncObject.notifyAll();
        }
    }
}
