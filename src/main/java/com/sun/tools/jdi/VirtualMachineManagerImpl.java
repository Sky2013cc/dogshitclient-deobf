package com.sun.tools.jdi;

import com.sun.jdi.JDIPermission;
import com.sun.jdi.VMDisconnectedException;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.LaunchingConnector;
import com.sun.jdi.connect.ListeningConnector;
import com.sun.jdi.connect.spi.Connection;
import com.sun.jdi.connect.spi.TransportService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

/* loaded from: target.jar:com/sun/tools/jdi/VirtualMachineManagerImpl.class */
public class VirtualMachineManagerImpl implements VirtualMachineManagerService {
    private final ThreadGroup mainGroupForJDI;
    private static final int majorVersion = 1;
    private static final int minorVersion = 8;
    private static final Object lock = new Object();
    private static VirtualMachineManagerImpl vmm;
    private List<Connector> connectors = new ArrayList();
    private LaunchingConnector defaultConnector = null;
    private List<VirtualMachine> targets = new ArrayList();
    private ResourceBundle messages = null;
    private int vmSequenceNumber = 0;

    public static VirtualMachineManager virtualMachineManager() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JDIPermission("virtualMachineManager"));
        }
        synchronized (lock) {
            if (vmm == null) {
                vmm = new VirtualMachineManagerImpl();
            }
        }
        return vmm;
    }

    protected VirtualMachineManagerImpl() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (true) {
            ThreadGroup parent = threadGroup.getParent();
            if (parent == null) {
                break;
            } else {
                threadGroup = parent;
            }
        }
        this.mainGroupForJDI = new ThreadGroup(threadGroup, "JDI main");
        Iterator it = ServiceLoader.load(Connector.class, Connector.class.getClassLoader()).iterator();
        while (it.hasNext()) {
            try {
                addConnector((Connector) it.next());
            } catch (Error e) {
                System.err.println(e);
            } catch (Exception e2) {
                System.err.println(e2);
            } catch (ThreadDeath e3) {
                throw e3;
            }
        }
        Iterator it2 = ServiceLoader.load(TransportService.class, TransportService.class.getClassLoader()).iterator();
        while (it2.hasNext()) {
            try {
                TransportService transportService = (TransportService) it2.next();
                addConnector(GenericAttachingConnector.create(transportService));
                addConnector(GenericListeningConnector.create(transportService));
            } catch (ThreadDeath e4) {
                throw e4;
            } catch (Error e5) {
                System.err.println(e5);
            } catch (Exception e6) {
                System.err.println(e6);
            }
        }
        if (allConnectors().size() == 0) {
            throw new Error("no Connectors loaded");
        }
        boolean z = false;
        List<LaunchingConnector> launchingConnectors = launchingConnectors();
        Iterator<LaunchingConnector> it3 = launchingConnectors.iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            LaunchingConnector next = it3.next();
            if (next.name().equals("com.sun.jdi.CommandLineLaunch")) {
                setDefaultConnector(next);
                z = true;
                break;
            }
        }
        if (!z && launchingConnectors.size() > 0) {
            setDefaultConnector(launchingConnectors.get(0));
        }
    }

    @Override // com.sun.jdi.VirtualMachineManager
    public LaunchingConnector defaultConnector() {
        if (this.defaultConnector == null) {
            throw new Error("no default LaunchingConnector");
        }
        return this.defaultConnector;
    }

    @Override // com.sun.tools.jdi.VirtualMachineManagerService
    public void setDefaultConnector(LaunchingConnector launchingConnector) {
        this.defaultConnector = launchingConnector;
    }

    @Override // com.sun.jdi.VirtualMachineManager
    public List<LaunchingConnector> launchingConnectors() {
        ArrayList arrayList = new ArrayList(this.connectors.size());
        for (Connector connector : this.connectors) {
            if (connector instanceof LaunchingConnector) {
                arrayList.add((LaunchingConnector) connector);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.sun.jdi.VirtualMachineManager
    public List<AttachingConnector> attachingConnectors() {
        ArrayList arrayList = new ArrayList(this.connectors.size());
        for (Connector connector : this.connectors) {
            if (connector instanceof AttachingConnector) {
                arrayList.add((AttachingConnector) connector);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.sun.jdi.VirtualMachineManager
    public List<ListeningConnector> listeningConnectors() {
        ArrayList arrayList = new ArrayList(this.connectors.size());
        for (Connector connector : this.connectors) {
            if (connector instanceof ListeningConnector) {
                arrayList.add((ListeningConnector) connector);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.sun.jdi.VirtualMachineManager
    public List<Connector> allConnectors() {
        return Collections.unmodifiableList(this.connectors);
    }

    @Override // com.sun.jdi.VirtualMachineManager
    public List<VirtualMachine> connectedVirtualMachines() {
        return Collections.unmodifiableList(this.targets);
    }

    @Override // com.sun.tools.jdi.VirtualMachineManagerService
    public void addConnector(Connector connector) {
        this.connectors.add(connector);
    }

    @Override // com.sun.tools.jdi.VirtualMachineManagerService
    public void removeConnector(Connector connector) {
        this.connectors.remove(connector);
    }

    @Override // com.sun.jdi.VirtualMachineManager
    public synchronized VirtualMachine createVirtualMachine(Connection connection, Process process) throws IOException {
        if (!connection.isOpen()) {
            throw new IllegalStateException("connection is not open");
        }
        try {
            int i = this.vmSequenceNumber + 1;
            this.vmSequenceNumber = i;
            VirtualMachineImpl virtualMachineImpl = new VirtualMachineImpl(this, connection, process, i);
            this.targets.add(virtualMachineImpl);
            return virtualMachineImpl;
        } catch (VMDisconnectedException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override // com.sun.jdi.VirtualMachineManager
    public VirtualMachine createVirtualMachine(Connection connection) throws IOException {
        return createVirtualMachine(connection, null);
    }

    public void addVirtualMachine(VirtualMachine virtualMachine) {
        this.targets.add(virtualMachine);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void disposeVirtualMachine(VirtualMachine virtualMachine) {
        this.targets.remove(virtualMachine);
    }

    @Override // com.sun.jdi.VirtualMachineManager
    public int majorInterfaceVersion() {
        return 1;
    }

    @Override // com.sun.jdi.VirtualMachineManager
    public int minorInterfaceVersion() {
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadGroup mainGroupForJDI() {
        return this.mainGroupForJDI;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getString(String str) {
        if (this.messages == null) {
            this.messages = ResourceBundle.getBundle("com.sun.tools.jdi.resources.jdi");
        }
        return this.messages.getString(str);
    }
}
