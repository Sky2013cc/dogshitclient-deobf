package sun.jvmstat.monitor;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import jdk.internal.dynalink.CallSiteDescriptor;
import sun.jvmstat.monitor.event.HostListener;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:sun/jvmstat/monitor/MonitoredHost.class */
public abstract class MonitoredHost {
    private static Map<HostIdentifier, MonitoredHost> monitoredHosts;
    private static final String IMPL_OVERRIDE_PROP_NAME = "sun.jvmstat.monitor.MonitoredHost";
    private static final String IMPL_PKG_PROP_NAME = "sun.jvmstat.monitor.package";
    private static final String IMPL_PACKAGE;
    private static final String LOCAL_PROTOCOL_PROP_NAME = "sun.jvmstat.monitor.local";
    private static final String LOCAL_PROTOCOL;
    private static final String REMOTE_PROTOCOL_PROP_NAME = "sun.jvmstat.monitor.remote";
    private static final String REMOTE_PROTOCOL;
    private static final String MONITORED_HOST_CLASS = "MonitoredHostProvider";
    protected HostIdentifier hostId;
    protected int interval;
    protected Exception lastException;
    static final /* synthetic */ boolean $assertionsDisabled;

    public abstract MonitoredVm getMonitoredVm(VmIdentifier vmIdentifier) throws MonitorException;

    public abstract MonitoredVm getMonitoredVm(VmIdentifier vmIdentifier, int i) throws MonitorException;

    public abstract void detach(MonitoredVm monitoredVm) throws MonitorException;

    public abstract void addHostListener(HostListener hostListener) throws MonitorException;

    public abstract void removeHostListener(HostListener hostListener) throws MonitorException;

    public abstract Set<Integer> activeVms() throws MonitorException;

    static {
        $assertionsDisabled = !MonitoredHost.class.desiredAssertionStatus();
        monitoredHosts = new HashMap();
        IMPL_PACKAGE = System.getProperty(IMPL_PKG_PROP_NAME, "sun.jvmstat.perfdata");
        LOCAL_PROTOCOL = System.getProperty(LOCAL_PROTOCOL_PROP_NAME, "local");
        REMOTE_PROTOCOL = System.getProperty(REMOTE_PROTOCOL_PROP_NAME, "rmi");
    }

    public static MonitoredHost getMonitoredHost(String str) throws MonitorException, URISyntaxException {
        return getMonitoredHost(new HostIdentifier(str));
    }

    public static MonitoredHost getMonitoredHost(VmIdentifier vmIdentifier) throws MonitorException {
        return getMonitoredHost(new HostIdentifier(vmIdentifier));
    }

    public static MonitoredHost getMonitoredHost(HostIdentifier hostIdentifier) throws MonitorException {
        String property = System.getProperty(IMPL_OVERRIDE_PROP_NAME);
        synchronized (monitoredHosts) {
            MonitoredHost monitoredHost = monitoredHosts.get(hostIdentifier);
            if (monitoredHost != null) {
                if (monitoredHost.isErrored()) {
                    monitoredHosts.remove(hostIdentifier);
                } else {
                    return monitoredHost;
                }
            }
            HostIdentifier resolveHostId = resolveHostId(hostIdentifier);
            if (property == null) {
                property = IMPL_PACKAGE + ".monitor.protocol." + resolveHostId.getScheme() + Constants.NAME_SEPARATOR + MONITORED_HOST_CLASS;
            }
            try {
                MonitoredHost monitoredHost2 = (MonitoredHost) Class.forName(property).getConstructor(resolveHostId.getClass()).newInstance(resolveHostId);
                synchronized (monitoredHosts) {
                    monitoredHosts.put(monitoredHost2.hostId, monitoredHost2);
                }
                return monitoredHost2;
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Could not find " + property + ": " + e.getMessage(), e);
            } catch (IllegalAccessException e2) {
                throw new IllegalArgumentException("Unexpected constructor access in " + property + ": " + e2.getMessage(), e2);
            } catch (InstantiationException e3) {
                throw new IllegalArgumentException(property + "is abstract: " + e3.getMessage(), e3);
            } catch (NoSuchMethodException e4) {
                throw new IllegalArgumentException("Expected constructor missing in " + property + ": " + e4.getMessage(), e4);
            } catch (InvocationTargetException e5) {
                Throwable cause = e5.getCause();
                if (cause instanceof MonitorException) {
                    throw ((MonitorException) cause);
                }
                throw new RuntimeException("Unexpected exception", e5);
            }
        }
    }

    protected static HostIdentifier resolveHostId(HostIdentifier hostIdentifier) throws MonitorException {
        String host = hostIdentifier.getHost();
        String scheme = hostIdentifier.getScheme();
        StringBuffer stringBuffer = new StringBuffer();
        if (!$assertionsDisabled && host == null) {
            throw new AssertionError();
        }
        if (scheme == null) {
            if (host.compareTo("localhost") == 0) {
                scheme = LOCAL_PROTOCOL;
            } else {
                scheme = REMOTE_PROTOCOL;
            }
        }
        stringBuffer.append(scheme).append(CallSiteDescriptor.TOKEN_DELIMITER).append(hostIdentifier.getSchemeSpecificPart());
        String fragment = hostIdentifier.getFragment();
        if (fragment != null) {
            stringBuffer.append("#").append(fragment);
        }
        try {
            return new HostIdentifier(stringBuffer.toString());
        } catch (URISyntaxException e) {
            if ($assertionsDisabled) {
                throw new IllegalArgumentException("Malformed URI created: " + stringBuffer.toString());
            }
            throw new AssertionError();
        }
    }

    public HostIdentifier getHostIdentifier() {
        return this.hostId;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setLastException(Exception exc) {
        this.lastException = exc;
    }

    public Exception getLastException() {
        return this.lastException;
    }

    public void clearLastException() {
        this.lastException = null;
    }

    public boolean isErrored() {
        return this.lastException != null;
    }
}
