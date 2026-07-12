package com.sun.tools.script.shell;

import com.sun.tools.doclets.internal.toolkit.taglets.TagletManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/script/shell/Main.class */
public class Main {
    private static final int EXIT_SUCCESS = 0;
    private static final int EXIT_CMD_NO_CLASSPATH = 1;
    private static final int EXIT_CMD_NO_FILE = 2;
    private static final int EXIT_CMD_NO_SCRIPT = 3;
    private static final int EXIT_CMD_NO_LANG = 4;
    private static final int EXIT_CMD_NO_ENCODING = 5;
    private static final int EXIT_CMD_NO_PROPNAME = 6;
    private static final int EXIT_UNKNOWN_OPTION = 7;
    private static final int EXIT_ENGINE_NOT_FOUND = 8;
    private static final int EXIT_NO_ENCODING_FOUND = 9;
    private static final int EXIT_SCRIPT_ERROR = 10;
    private static final int EXIT_FILE_NOT_FOUND = 11;
    private static final int EXIT_MULTIPLE_STDIN = 12;
    private static final String DEFAULT_LANGUAGE = "js";
    private static ScriptEngineManager engineManager;
    private static String BUNDLE_NAME = "com.sun.tools.script.shell.messages";
    private static String PROGRAM_NAME = "jrunscript";
    private static List<Command> scripts = new ArrayList();
    private static Map<String, ScriptEngine> engines = new HashMap();
    private static ResourceBundle msgRes = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/script/shell/Main$Command.class */
    public interface Command {
        void run(String[] strArr);
    }

    public static void main(String[] strArr) {
        String[] processOptions = processOptions(strArr);
        Iterator<Command> it = scripts.iterator();
        while (it.hasNext()) {
            it.next().run(processOptions);
        }
        System.exit(0);
    }

    private static String[] processOptions(String[] strArr) {
        int length;
        int i;
        String str = DEFAULT_LANGUAGE;
        String str2 = null;
        checkClassPath(strArr);
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        while (i2 < strArr.length) {
            String str3 = strArr[i2];
            if (str3.equals("-classpath") || str3.equals("-cp")) {
                i2++;
            } else {
                if (!str3.startsWith(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                    if (z) {
                        length = strArr.length - i2;
                        i = i2;
                    } else {
                        length = (strArr.length - i2) - 1;
                        i = i2 + 1;
                        addFileSource(getScriptEngine(str), strArr[i2], str2);
                    }
                    String[] strArr2 = new String[length];
                    System.arraycopy(strArr, i, strArr2, 0, length);
                    return strArr2;
                }
                if (str3.startsWith("-D")) {
                    String substring = str3.substring(2);
                    int indexOf = substring.indexOf(61);
                    if (indexOf != -1) {
                        System.setProperty(substring.substring(0, indexOf), substring.substring(indexOf + 1));
                    } else if (!substring.equals("")) {
                        System.setProperty(substring, "");
                    } else {
                        usage(6);
                    }
                } else {
                    if (str3.equals("-?") || str3.equals("-help")) {
                        usage(0);
                    } else if (str3.equals("-e")) {
                        z = true;
                        i2++;
                        if (i2 == strArr.length) {
                            usage(3);
                        }
                        addStringSource(getScriptEngine(str), strArr[i2]);
                    } else if (str3.equals("-encoding")) {
                        i2++;
                        if (i2 == strArr.length) {
                            usage(5);
                        }
                        str2 = strArr[i2];
                    } else if (str3.equals("-f")) {
                        z = true;
                        i2++;
                        if (i2 == strArr.length) {
                            usage(2);
                        }
                        ScriptEngine scriptEngine = getScriptEngine(str);
                        if (strArr[i2].equals(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
                            if (z2) {
                                usage(12);
                            } else {
                                z2 = true;
                            }
                            addInteractiveMode(scriptEngine);
                        } else {
                            addFileSource(scriptEngine, strArr[i2], str2);
                        }
                    } else if (str3.equals("-l")) {
                        i2++;
                        if (i2 == strArr.length) {
                            usage(4);
                        }
                        str = strArr[i2];
                    } else if (str3.equals("-q")) {
                        listScriptEngines();
                    }
                    usage(7);
                }
            }
            i2++;
        }
        if (!z) {
            addInteractiveMode(getScriptEngine(str));
        }
        return new String[0];
    }

    private static void addInteractiveMode(final ScriptEngine scriptEngine) {
        scripts.add(new Command() { // from class: com.sun.tools.script.shell.Main.1
            @Override // com.sun.tools.script.shell.Main.Command
            public void run(String[] strArr) {
                Main.setScriptArguments(scriptEngine, strArr);
                Main.processSource(scriptEngine, TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR, null);
            }
        });
    }

    private static void addFileSource(final ScriptEngine scriptEngine, final String str, final String str2) {
        scripts.add(new Command() { // from class: com.sun.tools.script.shell.Main.2
            @Override // com.sun.tools.script.shell.Main.Command
            public void run(String[] strArr) {
                Main.setScriptArguments(scriptEngine, strArr);
                Main.processSource(scriptEngine, str, str2);
            }
        });
    }

    private static void addStringSource(final ScriptEngine scriptEngine, final String str) {
        scripts.add(new Command() { // from class: com.sun.tools.script.shell.Main.3
            @Override // com.sun.tools.script.shell.Main.Command
            public void run(String[] strArr) {
                Main.setScriptArguments(scriptEngine, strArr);
                String scriptFilename = Main.setScriptFilename(scriptEngine, "<string>");
                try {
                    Main.evaluateString(scriptEngine, str);
                } finally {
                    Main.setScriptFilename(scriptEngine, scriptFilename);
                }
            }
        });
    }

    private static void listScriptEngines() {
        for (ScriptEngineFactory scriptEngineFactory : engineManager.getEngineFactories()) {
            getError().println(getMessage("engine.info", new Object[]{scriptEngineFactory.getLanguageName(), scriptEngineFactory.getLanguageVersion(), scriptEngineFactory.getEngineName(), scriptEngineFactory.getEngineVersion()}));
        }
        System.exit(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void processSource(ScriptEngine scriptEngine, String str, String str2) {
        if (str.equals(TagletManager.ALT_SIMPLE_TAGLET_OPT_SEPARATOR)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getIn()));
            String prompt = getPrompt(scriptEngine);
            scriptEngine.put("javax.script.filename", "<STDIN>");
            while (0 == 0) {
                getError().print(prompt);
                String str3 = "";
                try {
                    str3 = bufferedReader.readLine();
                } catch (IOException e) {
                    getError().println(e.toString());
                }
                if (str3 != null) {
                    Object evaluateString = evaluateString(scriptEngine, str3, false);
                    if (evaluateString != null) {
                        String obj = evaluateString.toString();
                        if (obj == null) {
                            obj = "null";
                        }
                        getError().println((Object) obj);
                    }
                } else {
                    return;
                }
            }
            return;
        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(str);
        } catch (FileNotFoundException e2) {
            getError().println(getMessage("file.not.found", new Object[]{str}));
            System.exit(11);
        }
        evaluateStream(scriptEngine, fileInputStream, str, str2);
    }

    private static Object evaluateString(ScriptEngine scriptEngine, String str, boolean z) {
        try {
            return scriptEngine.eval(str);
        } catch (Exception e) {
            e.printStackTrace(getError());
            if (z) {
                System.exit(10);
                return null;
            }
            return null;
        } catch (ScriptException e2) {
            getError().println(getMessage("string.script.error", new Object[]{e2.getMessage()}));
            if (z) {
                System.exit(10);
                return null;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void evaluateString(ScriptEngine scriptEngine, String str) {
        evaluateString(scriptEngine, str, true);
    }

    private static Object evaluateReader(ScriptEngine scriptEngine, Reader reader, String str) {
        String scriptFilename = setScriptFilename(scriptEngine, str);
        try {
            try {
                try {
                    Object eval = scriptEngine.eval(reader);
                    setScriptFilename(scriptEngine, scriptFilename);
                    return eval;
                } catch (Exception e) {
                    e.printStackTrace(getError());
                    System.exit(10);
                    setScriptFilename(scriptEngine, scriptFilename);
                    return null;
                }
            } catch (ScriptException e2) {
                getError().println(getMessage("file.script.error", new Object[]{str, e2.getMessage()}));
                System.exit(10);
                setScriptFilename(scriptEngine, scriptFilename);
                return null;
            }
        } catch (Throwable th) {
            setScriptFilename(scriptEngine, scriptFilename);
            throw th;
        }
    }

    private static Object evaluateStream(ScriptEngine scriptEngine, InputStream inputStream, String str, String str2) {
        BufferedReader bufferedReader = null;
        if (str2 != null) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str2));
            } catch (UnsupportedEncodingException e) {
                getError().println(getMessage("encoding.unsupported", new Object[]{str2}));
                System.exit(9);
            }
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        return evaluateReader(scriptEngine, bufferedReader, str);
    }

    private static void usage(int i) {
        getError().println(getMessage("main.usage", new Object[]{PROGRAM_NAME}));
        System.exit(i);
    }

    private static String getPrompt(ScriptEngine scriptEngine) {
        return ((String) scriptEngine.getFactory().getNames().get(0)) + "> ";
    }

    private static String getMessage(String str, Object[] objArr) {
        return MessageFormat.format(msgRes.getString(str), objArr);
    }

    private static InputStream getIn() {
        return System.in;
    }

    private static PrintStream getError() {
        return System.err;
    }

    private static ScriptEngine getScriptEngine(String str) {
        ScriptEngine scriptEngine = engines.get(str);
        if (scriptEngine == null) {
            scriptEngine = engineManager.getEngineByName(str);
            if (scriptEngine == null) {
                getError().println(getMessage("engine.not.found", new Object[]{str}));
                System.exit(8);
            }
            initScriptEngine(scriptEngine);
            engines.put(str, scriptEngine);
        }
        return scriptEngine;
    }

    private static void initScriptEngine(ScriptEngine scriptEngine) {
        scriptEngine.put("engine", scriptEngine);
        List extensions = scriptEngine.getFactory().getExtensions();
        InputStream inputStream = null;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Iterator it = extensions.iterator();
        while (it.hasNext()) {
            inputStream = contextClassLoader.getResourceAsStream("com/sun/tools/script/shell/init." + ((String) it.next()));
            if (inputStream != null) {
                break;
            }
        }
        if (inputStream != null) {
            evaluateStream(scriptEngine, inputStream, "<system-init>", null);
        }
    }

    private static void checkClassPath(String[] strArr) {
        String str = null;
        int i = 0;
        while (i < strArr.length) {
            if (strArr[i].equals("-classpath") || strArr[i].equals("-cp")) {
                i++;
                if (i == strArr.length) {
                    usage(1);
                } else {
                    str = strArr[i];
                }
            }
            i++;
        }
        if (str != null) {
            Thread.currentThread().setContextClassLoader(new URLClassLoader(pathToURLs(str), Main.class.getClassLoader()));
        }
        engineManager = new ScriptEngineManager();
    }

    private static URL[] pathToURLs(String str) {
        String[] split = str.split(File.pathSeparator);
        URL[] urlArr = new URL[split.length];
        int i = 0;
        while (i < split.length) {
            URL fileToURL = fileToURL(new File(split[i]));
            if (fileToURL != null) {
                int i2 = i;
                i++;
                urlArr[i2] = fileToURL;
            }
        }
        if (urlArr.length != i) {
            URL[] urlArr2 = new URL[i];
            System.arraycopy(urlArr, 0, urlArr2, 0, i);
            urlArr = urlArr2;
        }
        return urlArr;
    }

    private static URL fileToURL(File file) {
        String absolutePath;
        try {
            absolutePath = file.getCanonicalPath();
        } catch (IOException e) {
            absolutePath = file.getAbsolutePath();
        }
        String replace = absolutePath.replace(File.separatorChar, '/');
        if (!replace.startsWith(RuntimeConstants.SIG_PACKAGE)) {
            replace = RuntimeConstants.SIG_PACKAGE + replace;
        }
        if (!file.isFile()) {
            replace = replace + RuntimeConstants.SIG_PACKAGE;
        }
        try {
            return new URL("file", "", replace);
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException("file");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setScriptArguments(ScriptEngine scriptEngine, String[] strArr) {
        scriptEngine.put("arguments", strArr);
        scriptEngine.put("javax.script.argv", strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String setScriptFilename(ScriptEngine scriptEngine, String str) {
        String str2 = (String) scriptEngine.get("javax.script.filename");
        scriptEngine.put("javax.script.filename", str);
        return str2;
    }
}
