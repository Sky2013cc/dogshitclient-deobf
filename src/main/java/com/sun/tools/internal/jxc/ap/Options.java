package com.sun.tools.internal.jxc.ap;

import com.sun.tools.internal.xjc.BadCommandLineException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: target.jar:com/sun/tools/internal/jxc/ap/Options.class */
public class Options {
    public static final String DISABLE_XML_SECURITY = "-disableXmlSecurity";
    public String classpath = System.getenv("CLASSPATH");
    public File targetDir = null;
    public File episodeFile = null;
    private boolean disableXmlSecurity = false;
    public String encoding = null;
    public final List<String> arguments = new ArrayList();

    public void parseArguments(String[] args) throws BadCommandLineException {
        int i = 0;
        while (i < args.length) {
            if (args[i].charAt(0) == '-') {
                int j = parseArgument(args, i);
                if (j == 0) {
                    throw new BadCommandLineException(Messages.UNRECOGNIZED_PARAMETER.format(args[i]));
                }
                i += j;
            } else {
                this.arguments.add(args[i]);
            }
            i++;
        }
    }

    private int parseArgument(String[] args, int i) throws BadCommandLineException {
        if (args[i].equals("-d")) {
            if (i == args.length - 1) {
                throw new BadCommandLineException(Messages.OPERAND_MISSING.format(args[i]));
            }
            this.targetDir = new File(args[i + 1]);
            if (!this.targetDir.exists()) {
                throw new BadCommandLineException(Messages.NON_EXISTENT_FILE.format(this.targetDir));
            }
            return 1;
        }
        if (args[i].equals("-episode")) {
            if (i == args.length - 1) {
                throw new BadCommandLineException(Messages.OPERAND_MISSING.format(args[i]));
            }
            this.episodeFile = new File(args[i + 1]);
            return 1;
        }
        if (args[i].equals(DISABLE_XML_SECURITY)) {
            if (i == args.length - 1) {
                throw new BadCommandLineException(Messages.OPERAND_MISSING.format(args[i]));
            }
            this.disableXmlSecurity = true;
            return 1;
        }
        if (args[i].equals("-encoding")) {
            if (i == args.length - 1) {
                throw new BadCommandLineException(Messages.OPERAND_MISSING.format(args[i]));
            }
            this.encoding = args[i + 1];
            return 1;
        }
        if (args[i].equals("-cp") || args[i].equals("-classpath")) {
            if (i == args.length - 1) {
                throw new BadCommandLineException(Messages.OPERAND_MISSING.format(args[i]));
            }
            this.classpath = args[i + 1];
            return 1;
        }
        return 0;
    }

    public boolean isDisableXmlSecurity() {
        return this.disableXmlSecurity;
    }
}
