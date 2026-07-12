package com.sun.jdi;

import com.sun.jdi.event.EventQueue;
import com.sun.jdi.request.EventRequestManager;
import java.util.List;
import java.util.Map;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/jdi/VirtualMachine.class */
public interface VirtualMachine extends Mirror {
    public static final int TRACE_NONE = 0;
    public static final int TRACE_SENDS = 1;
    public static final int TRACE_RECEIVES = 2;
    public static final int TRACE_EVENTS = 4;
    public static final int TRACE_REFTYPES = 8;
    public static final int TRACE_OBJREFS = 16;
    public static final int TRACE_ALL = 16777215;

    List<ReferenceType> classesByName(String str);

    List<ReferenceType> allClasses();

    void redefineClasses(Map<? extends ReferenceType, byte[]> map);

    List<ThreadReference> allThreads();

    void suspend();

    void resume();

    List<ThreadGroupReference> topLevelThreadGroups();

    EventQueue eventQueue();

    EventRequestManager eventRequestManager();

    BooleanValue mirrorOf(boolean z);

    ByteValue mirrorOf(byte b);

    CharValue mirrorOf(char c);

    ShortValue mirrorOf(short s);

    IntegerValue mirrorOf(int i);

    LongValue mirrorOf(long j);

    FloatValue mirrorOf(float f);

    DoubleValue mirrorOf(double d);

    StringReference mirrorOf(String str);

    VoidValue mirrorOfVoid();

    Process process();

    void dispose();

    void exit(int i);

    boolean canWatchFieldModification();

    boolean canWatchFieldAccess();

    boolean canGetBytecodes();

    boolean canGetSyntheticAttribute();

    boolean canGetOwnedMonitorInfo();

    boolean canGetCurrentContendedMonitor();

    boolean canGetMonitorInfo();

    boolean canUseInstanceFilters();

    boolean canRedefineClasses();

    boolean canAddMethod();

    boolean canUnrestrictedlyRedefineClasses();

    boolean canPopFrames();

    boolean canGetSourceDebugExtension();

    boolean canRequestVMDeathEvent();

    boolean canGetMethodReturnValues();

    boolean canGetInstanceInfo();

    boolean canUseSourceNameFilters();

    boolean canForceEarlyReturn();

    boolean canBeModified();

    boolean canRequestMonitorEvents();

    boolean canGetMonitorFrameInfo();

    boolean canGetClassFileVersion();

    boolean canGetConstantPool();

    void setDefaultStratum(String str);

    String getDefaultStratum();

    long[] instanceCounts(List<? extends ReferenceType> list);

    String description();

    String version();

    String name();

    void setDebugTraceMode(int i);
}
