package com.sun.tools.attach;

import com.sun.tools.attach.spi.AttachProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import jdk.Exported;

@Exported
/* loaded from: target.jar:com/sun/tools/attach/VirtualMachine.class */
public abstract class VirtualMachine {
    private AttachProvider provider;
    private String id;
    private volatile int hash;

    public abstract void detach() throws IOException;

    public abstract void loadAgentLibrary(String str, String str2) throws AgentLoadException, AgentInitializationException, IOException;

    public abstract void loadAgentPath(String str, String str2) throws AgentLoadException, AgentInitializationException, IOException;

    public abstract void loadAgent(String str, String str2) throws AgentLoadException, AgentInitializationException, IOException;

    public abstract Properties getSystemProperties() throws IOException;

    public abstract Properties getAgentProperties() throws IOException;

    public abstract void startManagementAgent(Properties properties) throws IOException;

    public abstract String startLocalManagementAgent() throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public VirtualMachine(AttachProvider attachProvider, String str) {
        if (attachProvider == null) {
            throw new NullPointerException("provider cannot be null");
        }
        if (str == null) {
            throw new NullPointerException("id cannot be null");
        }
        this.provider = attachProvider;
        this.id = str;
    }

    public static List<VirtualMachineDescriptor> list() {
        ArrayList arrayList = new ArrayList();
        Iterator<AttachProvider> it = AttachProvider.providers().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().listVirtualMachines());
        }
        return arrayList;
    }

    public static VirtualMachine attach(String str) throws AttachNotSupportedException, IOException {
        if (str == null) {
            throw new NullPointerException("id cannot be null");
        }
        List<AttachProvider> providers = AttachProvider.providers();
        if (providers.size() == 0) {
            throw new AttachNotSupportedException("no providers installed");
        }
        AttachNotSupportedException attachNotSupportedException = null;
        Iterator<AttachProvider> it = providers.iterator();
        while (it.hasNext()) {
            try {
                return it.next().attachVirtualMachine(str);
            } catch (AttachNotSupportedException e) {
                attachNotSupportedException = e;
            }
        }
        throw attachNotSupportedException;
    }

    public static VirtualMachine attach(VirtualMachineDescriptor virtualMachineDescriptor) throws AttachNotSupportedException, IOException {
        return virtualMachineDescriptor.provider().attachVirtualMachine(virtualMachineDescriptor);
    }

    public final AttachProvider provider() {
        return this.provider;
    }

    public final String id() {
        return this.id;
    }

    public void loadAgentLibrary(String str) throws AgentLoadException, AgentInitializationException, IOException {
        loadAgentLibrary(str, null);
    }

    public void loadAgentPath(String str) throws AgentLoadException, AgentInitializationException, IOException {
        loadAgentPath(str, null);
    }

    public void loadAgent(String str) throws AgentLoadException, AgentInitializationException, IOException {
        loadAgent(str, null);
    }

    public int hashCode() {
        if (this.hash != 0) {
            return this.hash;
        }
        this.hash = (this.provider.hashCode() * 127) + this.id.hashCode();
        return this.hash;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VirtualMachine)) {
            return false;
        }
        VirtualMachine virtualMachine = (VirtualMachine) obj;
        if (virtualMachine.provider() != provider() || !virtualMachine.id().equals(id())) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.provider.toString() + ": " + this.id;
    }
}
