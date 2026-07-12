package sun.tools.attach;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.spi.AttachProvider;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Collectors;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:sun/tools/attach/HotSpotVirtualMachine.class */
public abstract class HotSpotVirtualMachine extends VirtualMachine {
    private static final int JNI_ENOMEM = -4;
    private static final int ATTACH_ERROR_BADJAR = 100;
    private static final int ATTACH_ERROR_NOTONCP = 101;
    private static final int ATTACH_ERROR_STARTFAIL = 102;
    private static final String MANAGMENT_PREFIX = "com.sun.management.";
    private static long defaultAttachTimeout = 5000;
    private volatile long attachTimeout;

    abstract InputStream execute(String str, Object... objArr) throws AgentLoadException, IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HotSpotVirtualMachine(AttachProvider attachProvider, String str) {
        super(attachProvider, str);
    }

    private void loadAgentLibrary(String str, boolean z, String str2) throws AgentLoadException, AgentInitializationException, IOException {
        Object[] objArr = new Object[3];
        objArr[0] = str;
        objArr[1] = z ? Constants.TRUE : Constants.FALSE;
        objArr[2] = str2;
        InputStream execute = execute("load", objArr);
        try {
            int readInt = readInt(execute);
            if (readInt != 0) {
                throw new AgentInitializationException("Agent_OnAttach failed", readInt);
            }
        } finally {
            execute.close();
        }
    }

    @Override // com.sun.tools.attach.VirtualMachine
    public void loadAgentLibrary(String str, String str2) throws AgentLoadException, AgentInitializationException, IOException {
        loadAgentLibrary(str, false, str2);
    }

    @Override // com.sun.tools.attach.VirtualMachine
    public void loadAgentPath(String str, String str2) throws AgentLoadException, AgentInitializationException, IOException {
        loadAgentLibrary(str, true, str2);
    }

    @Override // com.sun.tools.attach.VirtualMachine
    public void loadAgent(String str, String str2) throws AgentLoadException, AgentInitializationException, IOException {
        String str3 = str;
        if (str2 != null) {
            str3 = str3 + "=" + str2;
        }
        try {
            loadAgentLibrary("instrument", str3);
        } catch (AgentInitializationException e) {
            int returnValue = e.returnValue();
            switch (returnValue) {
                case JNI_ENOMEM /* -4 */:
                    throw new AgentLoadException("Insuffient memory");
                case 100:
                    throw new AgentLoadException("Agent JAR not found or no Agent-Class attribute");
                case 101:
                    throw new AgentLoadException("Unable to add JAR file to system class path");
                case 102:
                    throw new AgentInitializationException("Agent JAR loaded but agent failed to initialize");
                default:
                    throw new AgentLoadException("Failed to load agent - unknown reason: " + returnValue);
            }
        } catch (AgentLoadException e2) {
            throw new InternalError("instrument library is missing in target VM", e2);
        }
    }

    @Override // com.sun.tools.attach.VirtualMachine
    public Properties getSystemProperties() throws IOException {
        InputStream inputStream = null;
        Properties properties = new Properties();
        try {
            inputStream = executeCommand("properties", new Object[0]);
            properties.load(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return properties;
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    @Override // com.sun.tools.attach.VirtualMachine
    public Properties getAgentProperties() throws IOException {
        InputStream inputStream = null;
        Properties properties = new Properties();
        try {
            inputStream = executeCommand("agentProperties", new Object[0]);
            properties.load(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return properties;
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean checkedKeyName(Object obj) {
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException("Invalid option (not a String): " + obj);
        }
        if (!((String) obj).startsWith(MANAGMENT_PREFIX)) {
            throw new IllegalArgumentException("Invalid option: " + obj);
        }
        return true;
    }

    private static String stripKeyName(Object obj) {
        return ((String) obj).substring(MANAGMENT_PREFIX.length());
    }

    @Override // com.sun.tools.attach.VirtualMachine
    public void startManagementAgent(Properties properties) throws IOException {
        if (properties == null) {
            throw new NullPointerException("agentProperties cannot be null");
        }
        executeJCmd("ManagementAgent.start " + ((String) properties.entrySet().stream().filter(entry -> {
            return checkedKeyName(entry.getKey());
        }).map(entry2 -> {
            return stripKeyName(entry2.getKey()) + "=" + escape(entry2.getValue());
        }).collect(Collectors.joining(" "))));
    }

    private String escape(Object obj) {
        String obj2 = obj.toString();
        if (obj2.contains(" ")) {
            return OperatorName.SHOW_TEXT_LINE + obj2 + OperatorName.SHOW_TEXT_LINE;
        }
        return obj2;
    }

    @Override // com.sun.tools.attach.VirtualMachine
    public String startLocalManagementAgent() throws IOException {
        executeJCmd("ManagementAgent.start_local");
        return getAgentProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
    }

    public void localDataDump() throws IOException {
        executeCommand("datadump", new Object[0]).close();
    }

    public InputStream remoteDataDump(Object... objArr) throws IOException {
        return executeCommand("threaddump", objArr);
    }

    public InputStream dumpHeap(Object... objArr) throws IOException {
        return executeCommand("dumpheap", objArr);
    }

    public InputStream heapHisto(Object... objArr) throws IOException {
        return executeCommand("inspectheap", objArr);
    }

    public InputStream setFlag(String str, String str2) throws IOException {
        return executeCommand("setflag", str, str2);
    }

    public InputStream printFlag(String str) throws IOException {
        return executeCommand("printflag", str);
    }

    public InputStream executeJCmd(String str) throws IOException {
        return executeCommand("jcmd", str);
    }

    private InputStream executeCommand(String str, Object... objArr) throws IOException {
        try {
            return execute(str, objArr);
        } catch (AgentLoadException e) {
            throw new InternalError("Should not get here", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readInt(InputStream inputStream) throws IOException {
        int read;
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[1];
        do {
            read = inputStream.read(bArr, 0, 1);
            if (read > 0) {
                char c = (char) bArr[0];
                if (c == '\n') {
                    break;
                }
                sb.append(c);
            }
        } while (read > 0);
        if (sb.length() == 0) {
            throw new IOException("Premature EOF");
        }
        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            throw new IOException("Non-numeric value found - int expected");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String readErrorMessage(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1024];
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                stringBuffer.append(new String(bArr, 0, read, "UTF-8"));
            } else {
                return stringBuffer.toString();
            }
        }
    }

    long attachTimeout() {
        if (this.attachTimeout == 0) {
            synchronized (this) {
                if (this.attachTimeout == 0) {
                    try {
                        this.attachTimeout = Long.parseLong(System.getProperty("sun.tools.attach.attachTimeout"));
                    } catch (NumberFormatException e) {
                    } catch (SecurityException e2) {
                    }
                    if (this.attachTimeout <= 0) {
                        this.attachTimeout = defaultAttachTimeout;
                    }
                }
            }
        }
        return this.attachTimeout;
    }
}
