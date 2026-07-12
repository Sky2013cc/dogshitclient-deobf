package sun.tools.jstat;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.File;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import sun.jvmstat.monitor.Monitor;
import sun.jvmstat.monitor.VmIdentifier;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:sun/tools/jstat/Arguments.class */
public class Arguments {
    private static final boolean debug;
    private static final boolean showUnsupported;
    private static final String JVMSTAT_USERDIR = ".jvmstat";
    private static final String OPTIONS_FILENAME = "jstat_options";
    private static final String UNSUPPORTED_OPTIONS_FILENAME = "jstat_unsupported_options";
    private static final String ALL_NAMES = "\\w*";
    private Comparator<Monitor> comparator;
    private int headerRate;
    private boolean help;
    private boolean list;
    private boolean options;
    private boolean constants;
    private boolean constantsOnly;
    private boolean strings;
    private boolean timestamp;
    private boolean snap;
    private boolean verbose;
    private String specialOption;
    private String names;
    private OptionFormat optionFormat;
    private int count;
    private int interval;
    private String vmIdString;
    private VmIdentifier vmId;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !Arguments.class.desiredAssertionStatus();
        debug = Boolean.getBoolean("jstat.debug");
        showUnsupported = Boolean.getBoolean("jstat.showUnsupported");
    }

    public static void printUsage(PrintStream printStream) {
        printStream.println("Usage: jstat -help|-options");
        printStream.println("       jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]");
        printStream.println();
        printStream.println("Definitions:");
        printStream.println("  <option>      An option reported by the -options option");
        printStream.println("  <vmid>        Virtual Machine Identifier. A vmid takes the following form:");
        printStream.println("                     <lvmid>[@<hostname>[:<port>]]");
        printStream.println("                Where <lvmid> is the local vm identifier for the target");
        printStream.println("                Java virtual machine, typically a process id; <hostname> is");
        printStream.println("                the name of the host running the target Java virtual machine;");
        printStream.println("                and <port> is the port number for the rmiregistry on the");
        printStream.println("                target host. See the jvmstat documentation for a more complete");
        printStream.println("                description of the Virtual Machine Identifier.");
        printStream.println("  <lines>       Number of samples between header lines.");
        printStream.println("  <interval>    Sampling interval. The following forms are allowed:");
        printStream.println("                    <n>[\"ms\"|\"s\"]");
        printStream.println("                Where <n> is an integer and the suffix specifies the units as ");
        printStream.println("                milliseconds(\"ms\") or seconds(\"s\"). The default units are \"ms\".");
        printStream.println("  <count>       Number of samples to take before terminating.");
        printStream.println("  -J<flag>      Pass <flag> directly to the runtime system.");
    }

    private static int toMillis(String str) throws IllegalArgumentException {
        String str2 = null;
        String str3 = str;
        for (String str4 : new String[]{"ms", OperatorName.CLOSE_AND_STROKE}) {
            int indexOf = str.indexOf(str4);
            if (indexOf > 0) {
                str2 = str.substring(indexOf);
                str3 = str.substring(0, indexOf);
                break;
            }
        }
        try {
            int parseInt = Integer.parseInt(str3);
            if (str2 == null || str2.compareTo("ms") == 0) {
                return parseInt;
            }
            if (str2.compareTo(OperatorName.CLOSE_AND_STROKE) == 0) {
                return parseInt * 1000;
            }
            throw new IllegalArgumentException("Unknow time unit: " + str2);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Could not convert interval: " + str);
        }
    }

    public Arguments(String[] strArr) throws IllegalArgumentException {
        String substring;
        String str;
        this.count = -1;
        this.interval = -1;
        int i = 0;
        if (strArr.length < 1) {
            throw new IllegalArgumentException("invalid argument count");
        }
        if (strArr[0].compareTo("-?") == 0 || strArr[0].compareTo("-help") == 0) {
            this.help = true;
            return;
        }
        if (strArr[0].compareTo("-options") == 0) {
            this.options = true;
            return;
        }
        if (strArr[0].compareTo("-list") == 0) {
            this.list = true;
            if (strArr.length > 2) {
                throw new IllegalArgumentException("invalid argument count");
            }
            i = 0 + 1;
        }
        while (i < strArr.length && strArr[i].startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
            String str2 = strArr[i];
            if (str2.compareTo("-a") == 0) {
                this.comparator = new AscendingMonitorComparator();
            } else if (str2.compareTo("-d") == 0) {
                this.comparator = new DescendingMonitorComparator();
            } else if (str2.compareTo("-t") == 0) {
                this.timestamp = true;
            } else if (str2.compareTo("-v") == 0) {
                this.verbose = true;
            } else if (str2.compareTo("-constants") == 0 || str2.compareTo("-c") == 0) {
                this.constants = true;
            } else if (str2.compareTo("-strings") == 0 || str2.compareTo("-s") == 0) {
                this.strings = true;
            } else if (str2.startsWith("-h")) {
                if (str2.compareTo("-h") != 0) {
                    str = str2.substring(2);
                } else {
                    i++;
                    if (i >= strArr.length) {
                        throw new IllegalArgumentException("-h requires an integer argument");
                    }
                    str = strArr[i];
                }
                try {
                    this.headerRate = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    this.headerRate = -1;
                }
                if (this.headerRate < 0) {
                    throw new IllegalArgumentException("illegal -h argument: " + str);
                }
            } else if (str2.startsWith("-name")) {
                if (str2.startsWith("-name=")) {
                    this.names = str2.substring(7);
                } else {
                    i++;
                    if (i >= strArr.length) {
                        throw new IllegalArgumentException("option argument expected");
                    }
                    this.names = strArr[i];
                }
            } else {
                int indexOf = strArr[i].indexOf(64);
                if (indexOf < 0) {
                    substring = strArr[i];
                } else {
                    substring = strArr[i].substring(0, indexOf);
                }
                try {
                    Integer.parseInt(substring);
                    break;
                } catch (NumberFormatException e2) {
                    if (i == 0 && strArr[i].compareTo("-snap") == 0) {
                        this.snap = true;
                    } else if (i == 0) {
                        this.specialOption = strArr[i].substring(1);
                    } else {
                        throw new IllegalArgumentException("illegal argument: " + strArr[i]);
                    }
                }
            }
            i++;
        }
        if (this.specialOption == null && !this.list && !this.snap && this.names == null) {
            throw new IllegalArgumentException("-<option> required");
        }
        switch (strArr.length - i) {
            case 0:
                if (!this.list) {
                    throw new IllegalArgumentException("invalid argument count");
                }
                break;
            case 1:
                this.vmIdString = strArr[strArr.length - 1];
                break;
            case 2:
                if (this.snap) {
                    throw new IllegalArgumentException("invalid argument count");
                }
                this.interval = toMillis(strArr[strArr.length - 1]);
                this.vmIdString = strArr[strArr.length - 2];
                break;
            case 3:
                if (this.snap) {
                    throw new IllegalArgumentException("invalid argument count");
                }
                try {
                    this.count = Integer.parseInt(strArr[strArr.length - 1]);
                    this.interval = toMillis(strArr[strArr.length - 2]);
                    this.vmIdString = strArr[strArr.length - 3];
                    break;
                } catch (NumberFormatException e3) {
                    throw new IllegalArgumentException("illegal count value: " + strArr[strArr.length - 1]);
                }
            default:
                throw new IllegalArgumentException("invalid argument count");
        }
        if (this.count == -1 && this.interval == -1) {
            this.count = 1;
            this.interval = 0;
        }
        if (this.comparator == null) {
            this.comparator = new AscendingMonitorComparator();
        }
        this.names = this.names == null ? ALL_NAMES : this.names.replace(',', '|');
        try {
            Pattern.compile(this.names);
            if (this.specialOption != null) {
                this.optionFormat = new OptionFinder(optionsSources()).getOptionFormat(this.specialOption, this.timestamp);
                if (this.optionFormat == null) {
                    throw new IllegalArgumentException("Unknown option: -" + this.specialOption);
                }
            }
            try {
                this.vmId = new VmIdentifier(this.vmIdString);
            } catch (URISyntaxException e4) {
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Malformed VM Identifier: " + this.vmIdString);
                illegalArgumentException.initCause(e4);
                throw illegalArgumentException;
            }
        } catch (PatternSyntaxException e5) {
            throw new IllegalArgumentException("Bad name pattern: " + e5.getMessage());
        }
    }

    public Comparator<Monitor> comparator() {
        return this.comparator;
    }

    public boolean isHelp() {
        return this.help;
    }

    public boolean isList() {
        return this.list;
    }

    public boolean isSnap() {
        return this.snap;
    }

    public boolean isOptions() {
        return this.options;
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public boolean printConstants() {
        return this.constants;
    }

    public boolean isConstantsOnly() {
        return this.constantsOnly;
    }

    public boolean printStrings() {
        return this.strings;
    }

    public boolean showUnsupported() {
        return showUnsupported;
    }

    public int headerRate() {
        return this.headerRate;
    }

    public String counterNames() {
        return this.names;
    }

    public VmIdentifier vmId() {
        return this.vmId;
    }

    public String vmIdString() {
        return this.vmIdString;
    }

    public int sampleInterval() {
        return this.interval;
    }

    public int sampleCount() {
        return this.count;
    }

    public boolean isTimestamp() {
        return this.timestamp;
    }

    public boolean isSpecialOption() {
        return this.specialOption != null;
    }

    public String specialOption() {
        return this.specialOption;
    }

    public OptionFormat optionFormat() {
        return this.optionFormat;
    }

    public List<URL> optionsSources() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(new File((System.getProperty("user.home") + RuntimeConstants.SIG_PACKAGE + JVMSTAT_USERDIR) + RuntimeConstants.SIG_PACKAGE + OPTIONS_FILENAME).toURI().toURL());
            URL resource = getClass().getResource("resources/" + OPTIONS_FILENAME);
            if (!$assertionsDisabled && resource == null) {
                throw new AssertionError();
            }
            arrayList.add(resource);
            if (showUnsupported) {
                URL resource2 = getClass().getResource("resources/jstat_unsupported_options");
                if (!$assertionsDisabled && resource2 == null) {
                    throw new AssertionError();
                }
                arrayList.add(resource2);
            }
            return arrayList;
        } catch (Exception e) {
            if (debug) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
            throw new IllegalArgumentException("Internal Error: Bad URL: " + e.getMessage());
        }
    }
}
