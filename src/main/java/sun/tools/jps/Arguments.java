package sun.tools.jps;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.PrintStream;
import java.net.URISyntaxException;
import sun.jvmstat.monitor.HostIdentifier;

/* loaded from: target.jar:sun/tools/jps/Arguments.class */
public class Arguments {
    private static final boolean debug = Boolean.getBoolean("jps.debug");
    private static final boolean printStackTrace = Boolean.getBoolean("jps.printStackTrace");
    private boolean help;
    private boolean quiet;
    private boolean longPaths;
    private boolean vmArgs;
    private boolean vmFlags;
    private boolean mainArgs;
    private String hostname;
    private HostIdentifier hostId;

    public static void printUsage(PrintStream printStream) {
        printStream.println("usage: jps [-help]");
        printStream.println("       jps [-q] [-mlvV] [<hostid>]");
        printStream.println();
        printStream.println("Definitions:");
        printStream.println("    <hostid>:      <hostname>[:<port>]");
    }

    public Arguments(String[] strArr) throws IllegalArgumentException {
        if (strArr.length == 1 && (strArr[0].compareTo("-?") == 0 || strArr[0].compareTo("-help") == 0)) {
            this.help = true;
            return;
        }
        int i = 0;
        while (i < strArr.length && strArr[i].startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
            String str = strArr[i];
            if (str.compareTo("-q") == 0) {
                this.quiet = true;
            } else if (str.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                for (int i2 = 1; i2 < str.length(); i2++) {
                    switch (str.charAt(i2)) {
                        case 'V':
                            this.vmFlags = true;
                            break;
                        case 'l':
                            this.longPaths = true;
                            break;
                        case 'm':
                            this.mainArgs = true;
                            break;
                        case 'v':
                            this.vmArgs = true;
                            break;
                        default:
                            throw new IllegalArgumentException("illegal argument: " + strArr[i]);
                    }
                }
            } else {
                throw new IllegalArgumentException("illegal argument: " + strArr[i]);
            }
            i++;
        }
        switch (strArr.length - i) {
            case 0:
                this.hostname = null;
                break;
            case 1:
                this.hostname = strArr[strArr.length - 1];
                break;
            default:
                throw new IllegalArgumentException("invalid argument count");
        }
        try {
            this.hostId = new HostIdentifier(this.hostname);
        } catch (URISyntaxException e) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Malformed Host Identifier: " + this.hostname);
            illegalArgumentException.initCause(e);
            throw illegalArgumentException;
        }
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean printStackTrace() {
        return printStackTrace;
    }

    public boolean isHelp() {
        return this.help;
    }

    public boolean isQuiet() {
        return this.quiet;
    }

    public boolean showLongPaths() {
        return this.longPaths;
    }

    public boolean showVmArgs() {
        return this.vmArgs;
    }

    public boolean showVmFlags() {
        return this.vmFlags;
    }

    public boolean showMainArgs() {
        return this.mainArgs;
    }

    public String hostname() {
        return this.hostname;
    }

    public HostIdentifier hostId() {
        return this.hostId;
    }
}
