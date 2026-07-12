package com.sun.tools.internal.ws.wscompile;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import com.sun.tools.internal.ws.Invoker;
import com.sun.tools.internal.ws.resources.WscompileMessages;
import com.sun.tools.internal.ws.wsdl.document.jaxws.JAXWSBindingsConstants;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.processing.Filer;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/Options.class */
public class Options {
    public boolean verbose;
    public boolean quiet;
    public boolean keep;
    public File sourceDir;
    public Filer filer;
    public String encoding;
    public List<String> javacOptions;
    public boolean nocompile;
    public boolean disableXmlSecurity;
    public static final int STRICT = 1;
    public static final int EXTENSION = 2;
    private ClassLoader classLoader;
    public File destDir = new File(Constants.NAME_SEPARATOR);
    public String classpath = System.getProperty("java.class.path");
    public Target target = Target.V2_2;
    public int compatibilityMode = 1;
    public boolean debug = false;
    public boolean debugMode = false;
    private final List<File> generatedFiles = new ArrayList();

    /* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/Options$WeAreDone.class */
    public static final class WeAreDone extends BadCommandLineException {
    }

    /* loaded from: target.jar:com/sun/tools/internal/ws/wscompile/Options$Target.class */
    public enum Target {
        V2_0,
        V2_1,
        V2_2;

        private static final Target LOADED_API_VERSION;

        static {
            if (Invoker.checkIfLoading22API()) {
                LOADED_API_VERSION = V2_2;
            } else if (Invoker.checkIfLoading21API()) {
                LOADED_API_VERSION = V2_1;
            } else {
                LOADED_API_VERSION = V2_0;
            }
        }

        public boolean isLaterThan(Target t) {
            return ordinal() >= t.ordinal();
        }

        public static Target parse(String token) {
            if (token.equals(JAXWSBindingsConstants.JAXB_BINDING_VERSION)) {
                return V2_0;
            }
            if (token.equals("2.1")) {
                return V2_1;
            }
            if (token.equals("2.2")) {
                return V2_2;
            }
            return null;
        }

        public String getVersion() {
            switch (this) {
                case V2_0:
                    return JAXWSBindingsConstants.JAXB_BINDING_VERSION;
                case V2_1:
                    return "2.1";
                case V2_2:
                    return "2.2";
                default:
                    return null;
            }
        }

        public static Target getDefault() {
            return V2_2;
        }

        public static Target getLoadedAPIVersion() {
            return LOADED_API_VERSION;
        }
    }

    public boolean isExtensionMode() {
        return this.compatibilityMode == 2;
    }

    public void addGeneratedFile(File file) {
        this.generatedFiles.add(file);
    }

    public void removeGeneratedFiles() {
        for (File file : this.generatedFiles) {
            if (file.getName().endsWith(Constants.SOURCE_FILE_EXTENSION)) {
                boolean deleted = file.delete();
                if (this.verbose && !deleted) {
                    System.out.println(MessageFormat.format("{0} could not be deleted.", file));
                }
            }
        }
        this.generatedFiles.clear();
    }

    public Iterable<File> getGeneratedFiles() {
        return this.generatedFiles;
    }

    public void deleteGeneratedFiles() {
        synchronized (this.generatedFiles) {
            for (File file : this.generatedFiles) {
                if (file.getName().endsWith(Constants.SOURCE_FILE_EXTENSION)) {
                    boolean deleted = file.delete();
                    if (this.verbose && !deleted) {
                        System.out.println(MessageFormat.format("{0} could not be deleted.", file));
                    }
                }
            }
            this.generatedFiles.clear();
        }
    }

    public void parseArguments(String[] args) throws BadCommandLineException {
        int i = 0;
        while (i < args.length) {
            if (args[i].length() == 0) {
                throw new BadCommandLineException();
            }
            if (args[i].charAt(0) == '-') {
                int j = parseArguments(args, i);
                if (j == 0) {
                    throw new BadCommandLineException(WscompileMessages.WSCOMPILE_INVALID_OPTION(args[i]));
                }
                i += j - 1;
            } else {
                addFile(args[i]);
            }
            i++;
        }
        if (this.destDir == null) {
            this.destDir = new File(Constants.NAME_SEPARATOR);
        }
        if (this.sourceDir == null) {
            this.sourceDir = this.destDir;
        }
    }

    protected void addFile(String arg) throws BadCommandLineException {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int parseArguments(String[] args, int i) throws BadCommandLineException {
        if (args[i].equals("-g")) {
            this.debug = true;
            return 1;
        }
        if (args[i].equals("-Xdebug")) {
            this.debugMode = true;
            return 1;
        }
        if (args[i].equals("-Xendorsed")) {
            return 1;
        }
        if (args[i].equals("-verbose")) {
            this.verbose = true;
            return 1;
        }
        if (args[i].equals("-quiet")) {
            this.quiet = true;
            return 1;
        }
        if (args[i].equals("-keep")) {
            this.keep = true;
            return 1;
        }
        if (args[i].equals("-target")) {
            String token = requireArgument("-target", args, i + 1);
            this.target = Target.parse(token);
            if (this.target == null) {
                throw new BadCommandLineException(WscompileMessages.WSIMPORT_ILLEGAL_TARGET_VERSION(token));
            }
            return 2;
        }
        if (args[i].equals("-classpath") || args[i].equals("-cp")) {
            this.classpath = requireArgument("-classpath", args, i + 1) + File.pathSeparator + System.getProperty("java.class.path");
            return 2;
        }
        if (args[i].equals("-d")) {
            this.destDir = new File(requireArgument("-d", args, i + 1));
            if (!this.destDir.exists()) {
                throw new BadCommandLineException(WscompileMessages.WSCOMPILE_NO_SUCH_DIRECTORY(this.destDir.getPath()));
            }
            return 2;
        }
        if (args[i].equals("-s")) {
            this.sourceDir = new File(requireArgument("-s", args, i + 1));
            this.keep = true;
            if (!this.sourceDir.exists()) {
                throw new BadCommandLineException(WscompileMessages.WSCOMPILE_NO_SUCH_DIRECTORY(this.sourceDir.getPath()));
            }
            return 2;
        }
        if (args[i].equals("-extension")) {
            this.compatibilityMode = 2;
            return 1;
        }
        if (args[i].startsWith("-help")) {
            WeAreDone done = new WeAreDone();
            done.initOptions(this);
            throw done;
        }
        if (args[i].equals("-Xnocompile")) {
            this.nocompile = true;
            this.keep = true;
            return 1;
        }
        if (args[i].equals("-encoding")) {
            this.encoding = requireArgument("-encoding", args, i + 1);
            try {
                if (!Charset.isSupported(this.encoding)) {
                    throw new BadCommandLineException(WscompileMessages.WSCOMPILE_UNSUPPORTED_ENCODING(this.encoding));
                }
                return 2;
            } catch (IllegalCharsetNameException e) {
                throw new BadCommandLineException(WscompileMessages.WSCOMPILE_UNSUPPORTED_ENCODING(this.encoding));
            }
        }
        if (args[i].equals(com.sun.tools.internal.jxc.ap.Options.DISABLE_XML_SECURITY)) {
            disableXmlSecurity();
            return 1;
        }
        if (args[i].startsWith("-J")) {
            if (this.javacOptions == null) {
                this.javacOptions = new ArrayList();
            }
            this.javacOptions.add(args[i].substring(2));
            return 1;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void disableXmlSecurity() {
        this.disableXmlSecurity = true;
    }

    public String requireArgument(String optionName, String[] args, int i) throws BadCommandLineException {
        if (args[i].startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
            throw new BadCommandLineException(WscompileMessages.WSCOMPILE_MISSING_OPTION_ARGUMENT(optionName));
        }
        return args[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<String> getJavacOptions(List<String> existingOptions, WsimportListener listener) {
        List<String> result = new ArrayList<>();
        for (String o : this.javacOptions) {
            if (o.contains("=") && !o.startsWith("A")) {
                int i = o.indexOf(61);
                String key = o.substring(0, i);
                if (existingOptions.contains(key)) {
                    listener.message(WscompileMessages.WSCOMPILE_EXISTING_OPTION(key));
                } else {
                    result.add(key);
                    result.add(o.substring(i + 1));
                }
            } else if (existingOptions.contains(o)) {
                listener.message(WscompileMessages.WSCOMPILE_EXISTING_OPTION(o));
            } else {
                result.add(o);
            }
        }
        return result;
    }

    public ClassLoader getClassLoader() {
        if (this.classLoader == null) {
            this.classLoader = new URLClassLoader(pathToURLs(this.classpath), getClass().getClassLoader());
        }
        return this.classLoader;
    }

    public static URL[] pathToURLs(String path) {
        StringTokenizer st = new StringTokenizer(path, File.pathSeparator);
        URL[] urls = new URL[st.countTokens()];
        int count = 0;
        while (st.hasMoreTokens()) {
            URL url = fileToURL(new File(st.nextToken()));
            if (url != null) {
                int i = count;
                count++;
                urls[i] = url;
            }
        }
        if (urls.length != count) {
            URL[] tmp = new URL[count];
            System.arraycopy(urls, 0, tmp, 0, count);
            urls = tmp;
        }
        return urls;
    }

    public static URL fileToURL(File file) {
        String name;
        try {
            name = file.getCanonicalPath();
        } catch (IOException e) {
            name = file.getAbsolutePath();
        }
        String name2 = name.replace(File.separatorChar, '/');
        if (!name2.startsWith(RuntimeConstants.SIG_PACKAGE)) {
            name2 = RuntimeConstants.SIG_PACKAGE + name2;
        }
        if (!file.isFile()) {
            name2 = name2 + RuntimeConstants.SIG_PACKAGE;
        }
        try {
            return new URL("file", "", name2);
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException("file");
        }
    }
}
