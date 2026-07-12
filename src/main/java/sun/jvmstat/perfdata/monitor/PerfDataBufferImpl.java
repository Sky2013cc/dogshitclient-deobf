package sun.jvmstat.perfdata.monitor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.MonitorException;

/* loaded from: target.jar:sun/jvmstat/perfdata/monitor/PerfDataBufferImpl.class */
public abstract class PerfDataBufferImpl {
    protected ByteBuffer buffer;
    protected int lvmid;
    static final /* synthetic */ boolean $assertionsDisabled;
    protected Map<String, Monitor> monitors = new TreeMap();
    protected Map<String, ArrayList<String>> aliasMap = new HashMap();
    protected Map aliasCache = new HashMap();

    protected abstract MonitorStatus getMonitorStatus(Map<String, Monitor> map) throws MonitorException;

    protected abstract void buildMonitorMap(Map<String, Monitor> map) throws MonitorException;

    protected abstract void getNewMonitors(Map<String, Monitor> map) throws MonitorException;

    static {
        $assertionsDisabled = !PerfDataBufferImpl.class.desiredAssertionStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PerfDataBufferImpl(ByteBuffer byteBuffer, int i) {
        this.buffer = byteBuffer;
        this.lvmid = i;
    }

    public int getLocalVmId() {
        return this.lvmid;
    }

    public byte[] getBytes() {
        ByteBuffer duplicate;
        synchronized (this) {
            try {
                if (this.monitors.isEmpty()) {
                    buildMonitorMap(this.monitors);
                }
            } catch (MonitorException e) {
            }
            duplicate = this.buffer.duplicate();
        }
        duplicate.rewind();
        byte[] bArr = new byte[duplicate.limit()];
        duplicate.get(bArr);
        return bArr;
    }

    public int getCapacity() {
        return this.buffer.capacity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer getByteBuffer() {
        return this.buffer;
    }

    private void buildAliasMap() {
        URL resource;
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        String property = System.getProperty("sun.jvmstat.perfdata.aliasmap");
        if (property != null) {
            try {
                resource = new File(property).toURL();
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            resource = getClass().getResource("/sun/jvmstat/perfdata/resources/aliasmap");
        }
        if (!$assertionsDisabled && resource == null) {
            throw new AssertionError();
        }
        try {
            new AliasFileParser(resource).parse(this.aliasMap);
        } catch (IOException e2) {
            System.err.println("Error processing " + property + ": " + e2.getMessage());
        } catch (SyntaxException e3) {
            System.err.println("Syntax error parsing " + property + ": " + e3.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Monitor findByAlias(String str) {
        ArrayList<String> arrayList;
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        Monitor monitor = (Monitor) this.aliasCache.get(str);
        if (monitor == null && (arrayList = this.aliasMap.get(str)) != null) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext() && monitor == null) {
                monitor = this.monitors.get(it.next());
            }
        }
        return monitor;
    }

    public Monitor findByName(String str) throws MonitorException {
        Monitor monitor;
        synchronized (this) {
            if (this.monitors.isEmpty()) {
                buildMonitorMap(this.monitors);
                buildAliasMap();
            }
            monitor = this.monitors.get(str);
            if (monitor == null) {
                getNewMonitors(this.monitors);
                monitor = this.monitors.get(str);
            }
            if (monitor == null) {
                monitor = findByAlias(str);
            }
        }
        return monitor;
    }

    public List<Monitor> findByPattern(String str) throws MonitorException, PatternSyntaxException {
        synchronized (this) {
            if (this.monitors.isEmpty()) {
                buildMonitorMap(this.monitors);
            } else {
                getNewMonitors(this.monitors);
            }
        }
        Matcher matcher = Pattern.compile(str).matcher("");
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Monitor> entry : this.monitors.entrySet()) {
            String key = entry.getKey();
            entry.getValue();
            matcher.reset(key);
            if (matcher.lookingAt()) {
                arrayList.add(entry.getValue());
            }
        }
        return arrayList;
    }

    public MonitorStatus getMonitorStatus() throws MonitorException {
        MonitorStatus monitorStatus;
        synchronized (this) {
            if (this.monitors.isEmpty()) {
                buildMonitorMap(this.monitors);
            }
            monitorStatus = getMonitorStatus(this.monitors);
        }
        return monitorStatus;
    }
}
