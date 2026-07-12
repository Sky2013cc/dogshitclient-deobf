package sun.tools.jcmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* loaded from: target.jar:sun/tools/jcmd/Arguments.class */
class Arguments {
    private boolean listProcesses;
    private boolean listCounters;
    private boolean showUsage;
    private int pid;
    private String command;
    private String processSubstring;

    public boolean isListProcesses() {
        return this.listProcesses;
    }

    public boolean isListCounters() {
        return this.listCounters;
    }

    public boolean isShowUsage() {
        return this.showUsage;
    }

    public int getPid() {
        return this.pid;
    }

    public String getCommand() {
        return this.command;
    }

    public String getProcessSubstring() {
        return this.processSubstring;
    }

    public Arguments(String[] strArr) {
        this.listProcesses = false;
        this.listCounters = false;
        this.showUsage = false;
        this.pid = -1;
        this.command = null;
        if (strArr.length == 0 || strArr[0].equals("-l")) {
            this.listProcesses = true;
            return;
        }
        if (strArr[0].equals("-h") || strArr[0].equals("-help")) {
            this.showUsage = true;
            return;
        }
        try {
            this.pid = Integer.parseInt(strArr[0]);
        } catch (NumberFormatException e) {
            if (strArr[0].charAt(0) != '-') {
                this.processSubstring = strArr[0];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < strArr.length; i++) {
            if (strArr[i].equals("-f")) {
                if (strArr.length == i + 1) {
                    throw new IllegalArgumentException("No file specified for parameter -f");
                }
                if (strArr.length == i + 2) {
                    try {
                        readCommandFile(strArr[i + 1]);
                        return;
                    } catch (IOException e2) {
                        throw new IllegalArgumentException("Could not read from file specified with -f option: " + strArr[i + 1]);
                    }
                }
                throw new IllegalArgumentException("Options after -f are not allowed");
            }
            if (strArr[i].equals("PerfCounter.print")) {
                this.listCounters = true;
            } else {
                sb.append(strArr[i]).append(" ");
            }
        }
        if (!this.listCounters && sb.length() == 0) {
            throw new IllegalArgumentException("No command specified");
        }
        this.command = sb.toString().trim();
    }

    private void readCommandFile(String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        Throwable th = null;
        try {
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else {
                        sb.append(readLine).append("\n");
                    }
                }
                this.command = sb.toString();
                if (bufferedReader != null) {
                    if (0 != 0) {
                        try {
                            bufferedReader.close();
                            return;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            return;
                        }
                    }
                    bufferedReader.close();
                }
            } catch (Throwable th3) {
                th = th3;
                throw th3;
            }
        } catch (Throwable th4) {
            if (bufferedReader != null) {
                if (th != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th5) {
                        th.addSuppressed(th5);
                    }
                } else {
                    bufferedReader.close();
                }
            }
            throw th4;
        }
    }

    public static void usage() {
        System.out.println("Usage: jcmd <pid | main class> <command ...|PerfCounter.print|-f file>");
        System.out.println("   or: jcmd -l                                                    ");
        System.out.println("   or: jcmd -h                                                    ");
        System.out.println("                                                                  ");
        System.out.println("  command must be a valid jcmd command for the selected jvm.      ");
        System.out.println("  Use the command \"help\" to see which commands are available.   ");
        System.out.println("  If the pid is 0, commands will be sent to all Java processes.   ");
        System.out.println("  The main class argument will be used to match (either partially ");
        System.out.println("  or fully) the class used to start Java.                         ");
        System.out.println("  If no options are given, lists Java processes (same as -p).     ");
        System.out.println("                                                                  ");
        System.out.println("  PerfCounter.print display the counters exposed by this process  ");
        System.out.println("  -f  read and execute commands from the file                     ");
        System.out.println("  -l  list JVM processes on the local machine                     ");
        System.out.println("  -h  this help                                                   ");
    }
}
