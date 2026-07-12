package sun.applet;

/* loaded from: target.jar:sun/applet/AppletEventMulticaster.class */
public class AppletEventMulticaster implements AppletListener {
    private final AppletListener a;
    private final AppletListener b;

    public AppletEventMulticaster(AppletListener appletListener, AppletListener appletListener2) {
        this.a = appletListener;
        this.b = appletListener2;
    }

    @Override // sun.applet.AppletListener
    public void appletStateChanged(AppletEvent appletEvent) {
        this.a.appletStateChanged(appletEvent);
        this.b.appletStateChanged(appletEvent);
    }

    public static AppletListener add(AppletListener appletListener, AppletListener appletListener2) {
        return addInternal(appletListener, appletListener2);
    }

    public static AppletListener remove(AppletListener appletListener, AppletListener appletListener2) {
        return removeInternal(appletListener, appletListener2);
    }

    private static AppletListener addInternal(AppletListener appletListener, AppletListener appletListener2) {
        return appletListener == null ? appletListener2 : appletListener2 == null ? appletListener : new AppletEventMulticaster(appletListener, appletListener2);
    }

    protected AppletListener remove(AppletListener appletListener) {
        if (appletListener == this.a) {
            return this.b;
        }
        if (appletListener == this.b) {
            return this.a;
        }
        AppletListener removeInternal = removeInternal(this.a, appletListener);
        AppletListener removeInternal2 = removeInternal(this.b, appletListener);
        if (removeInternal == this.a && removeInternal2 == this.b) {
            return this;
        }
        return addInternal(removeInternal, removeInternal2);
    }

    private static AppletListener removeInternal(AppletListener appletListener, AppletListener appletListener2) {
        if (appletListener == appletListener2 || appletListener == null) {
            return null;
        }
        if (appletListener instanceof AppletEventMulticaster) {
            return ((AppletEventMulticaster) appletListener).remove(appletListener2);
        }
        return appletListener;
    }
}
