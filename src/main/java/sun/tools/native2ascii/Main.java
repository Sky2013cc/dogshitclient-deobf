package sun.tools.native2ascii;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: target.jar:sun/tools/native2ascii/Main.class */
public class Main {
    String inputFileName = null;
    String outputFileName = null;
    File tempFile = null;
    boolean reverse = false;
    static String encodingString = null;
    static String defaultEncoding = null;
    static CharsetEncoder encoder = null;
    private static ResourceBundle rsrc;

    static {
        try {
            rsrc = ResourceBundle.getBundle("sun.tools.native2ascii.resources.MsgNative2ascii");
        } catch (MissingResourceException e) {
            throw new Error("Missing message file.");
        }
    }

    /* JADX WARN: Failed to calculate best type for var: r12v1 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r12v1 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r12v3 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r12v3 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r13v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r13v0 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Failed to calculate best type for var: r13v3 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Failed to calculate best type for var: r13v3 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x01e1: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[TRY_LEAVE], block:B:183:0x01e1 */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x02d7: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[TRY_LEAVE], block:B:113:0x02d7 */
    /* JADX WARN: Not initialized variable reg: 13, insn: 0x01e6: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r13 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:185:0x01e6 */
    /* JADX WARN: Not initialized variable reg: 13, insn: 0x02dc: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r13 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:115:0x02dc */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0302 A[Catch: Exception -> 0x031a, TryCatch #9 {Exception -> 0x031a, blocks: (B:32:0x0098, B:34:0x00a5, B:35:0x00b3, B:37:0x00bd, B:40:0x00e1, B:42:0x00f4, B:44:0x00fb, B:45:0x010c, B:46:0x010d, B:126:0x0114, B:128:0x0121, B:130:0x012e, B:132:0x0139, B:135:0x0151, B:146:0x0163, B:144:0x0177, B:149:0x016d, B:156:0x01ba, B:154:0x01ce, B:159:0x01c4, B:85:0x0302, B:87:0x0309, B:88:0x030e, B:164:0x0187, B:173:0x0194, B:171:0x01a8, B:176:0x019e, B:178:0x01af, B:181:0x01de, B:189:0x01eb, B:187:0x01ff, B:192:0x01f5, B:194:0x0206, B:49:0x020a, B:51:0x0217, B:53:0x0224, B:55:0x022f, B:58:0x0247, B:69:0x0259, B:67:0x026d, B:72:0x0263, B:79:0x02b0, B:77:0x02c4, B:82:0x02ba, B:94:0x027d, B:103:0x028a, B:101:0x029e, B:106:0x0294, B:108:0x02a5, B:111:0x02d4, B:119:0x02e1, B:117:0x02f5, B:122:0x02eb, B:124:0x02fc), top: B:31:0x0098, inners: #4, #5, #8, #10, #15, #16 }] */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r12v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r13v3, types: [java.lang.Throwable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean convert(String[] strArr) {
        ArrayList arrayList = new ArrayList(2);
        File file = null;
        boolean z = false;
        int i = 0;
        while (i < strArr.length) {
            if (strArr[i].equals("-encoding")) {
                if (i + 1 >= strArr.length) {
                    error(getMsg("err.bad.arg"));
                    usage();
                    return false;
                }
                i++;
                encodingString = strArr[i];
            } else if (strArr[i].equals("-reverse")) {
                this.reverse = true;
            } else {
                if (arrayList.size() > 1) {
                    usage();
                    return false;
                }
                arrayList.add(strArr[i]);
            }
            i++;
        }
        if (encodingString == null) {
            defaultEncoding = Charset.defaultCharset().name();
        }
        char[] charArray = System.getProperty("line.separator").toCharArray();
        try {
            initializeConverter();
            if (arrayList.size() == 1) {
                this.inputFileName = (String) arrayList.get(0);
            }
            if (arrayList.size() == 2) {
                this.inputFileName = (String) arrayList.get(0);
                this.outputFileName = (String) arrayList.get(1);
                z = true;
            }
            if (z) {
                file = new File(this.outputFileName);
                if (file.exists() && !file.canWrite()) {
                    throw new Exception(formatMsg("err.cannot.write", this.outputFileName));
                }
            }
            if (!this.reverse) {
                try {
                    BufferedReader n2AInput = getN2AInput(this.inputFileName);
                    Throwable th = null;
                    BufferedWriter n2AOutput = getN2AOutput(this.outputFileName);
                    Throwable th2 = null;
                    while (true) {
                        try {
                            try {
                                String readLine = n2AInput.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                n2AOutput.write(readLine.toCharArray());
                                n2AOutput.write(charArray);
                                if (this.outputFileName == null) {
                                    n2AOutput.flush();
                                }
                            } finally {
                            }
                        } catch (Throwable th3) {
                            if (n2AOutput != null) {
                                if (th2 != null) {
                                    try {
                                        n2AOutput.close();
                                    } catch (Throwable th4) {
                                        th2.addSuppressed(th4);
                                    }
                                } else {
                                    n2AOutput.close();
                                }
                            }
                            throw th3;
                        }
                    }
                    if (n2AOutput != null) {
                        if (0 != 0) {
                            try {
                                n2AOutput.close();
                            } catch (Throwable th5) {
                                th2.addSuppressed(th5);
                            }
                        } else {
                            n2AOutput.close();
                        }
                    }
                    if (n2AInput != null) {
                        if (0 != 0) {
                            try {
                                n2AInput.close();
                            } catch (Throwable th6) {
                                th.addSuppressed(th6);
                            }
                        } else {
                            n2AInput.close();
                        }
                    }
                    if (z) {
                    }
                    return true;
                } finally {
                }
            }
            try {
                BufferedReader a2NInput = getA2NInput(this.inputFileName);
                Throwable th7 = null;
                Writer a2NOutput = getA2NOutput(this.outputFileName);
                Throwable th8 = null;
                while (true) {
                    try {
                        try {
                            String readLine2 = a2NInput.readLine();
                            if (readLine2 == null) {
                                break;
                            }
                            a2NOutput.write(readLine2.toCharArray());
                            a2NOutput.write(charArray);
                            if (this.outputFileName == null) {
                                a2NOutput.flush();
                            }
                        } finally {
                        }
                    } catch (Throwable th9) {
                        if (a2NOutput != null) {
                            if (th8 != null) {
                                try {
                                    a2NOutput.close();
                                } catch (Throwable th10) {
                                    th8.addSuppressed(th10);
                                }
                            } else {
                                a2NOutput.close();
                            }
                        }
                        throw th9;
                    }
                }
                if (a2NOutput != null) {
                    if (0 != 0) {
                        try {
                            a2NOutput.close();
                        } catch (Throwable th11) {
                            th8.addSuppressed(th11);
                        }
                    } else {
                        a2NOutput.close();
                    }
                }
                if (a2NInput != null) {
                    if (0 != 0) {
                        try {
                            a2NInput.close();
                        } catch (Throwable th12) {
                            th7.addSuppressed(th12);
                        }
                    } else {
                        a2NInput.close();
                    }
                }
                if (z) {
                    if (file.exists()) {
                        file.delete();
                    }
                    this.tempFile.renameTo(file);
                }
                return true;
            } finally {
            }
        } catch (Exception e) {
            error(e.toString());
            return false;
        }
    }

    private void error(String str) {
        System.out.println(str);
    }

    private void usage() {
        System.out.println(getMsg("usage"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.io.InputStream] */
    private BufferedReader getN2AInput(String str) throws Exception {
        FileInputStream fileInputStream;
        if (str == null) {
            fileInputStream = System.in;
        } else {
            File file = new File(str);
            if (!file.canRead()) {
                throw new Exception(formatMsg("err.cannot.read", file.getName()));
            }
            try {
                fileInputStream = new FileInputStream(str);
            } catch (IOException e) {
                throw new Exception(formatMsg("err.cannot.read", file.getName()));
            }
        }
        return encodingString != null ? new BufferedReader(new InputStreamReader(fileInputStream, encodingString)) : new BufferedReader(new InputStreamReader(fileInputStream));
    }

    private BufferedWriter getN2AOutput(String str) throws Exception {
        Writer fileWriter;
        if (str == null) {
            fileWriter = new OutputStreamWriter(System.out, "US-ASCII");
        } else {
            File parentFile = new File(str).getParentFile();
            if (parentFile == null) {
                parentFile = new File(System.getProperty("user.dir"));
            }
            this.tempFile = File.createTempFile("_N2A", ".TMP", parentFile);
            this.tempFile.deleteOnExit();
            try {
                fileWriter = new FileWriter(this.tempFile);
            } catch (IOException e) {
                throw new Exception(formatMsg("err.cannot.write", this.tempFile.getName()));
            }
        }
        return new BufferedWriter(new N2AFilter(fileWriter));
    }

    private BufferedReader getA2NInput(String str) throws Exception {
        Reader fileReader;
        if (str == null) {
            fileReader = new InputStreamReader(System.in, "US-ASCII");
        } else {
            File file = new File(str);
            if (!file.canRead()) {
                throw new Exception(formatMsg("err.cannot.read", file.getName()));
            }
            try {
                fileReader = new FileReader(str);
            } catch (Exception e) {
                throw new Exception(formatMsg("err.cannot.read", file.getName()));
            }
        }
        return new BufferedReader(new A2NFilter(fileReader));
    }

    private Writer getA2NOutput(String str) throws Exception {
        OutputStream fileOutputStream;
        if (str == null) {
            fileOutputStream = System.out;
        } else {
            File parentFile = new File(str).getParentFile();
            if (parentFile == null) {
                parentFile = new File(System.getProperty("user.dir"));
            }
            this.tempFile = File.createTempFile("_N2A", ".TMP", parentFile);
            this.tempFile.deleteOnExit();
            try {
                fileOutputStream = new FileOutputStream(this.tempFile);
            } catch (IOException e) {
                throw new Exception(formatMsg("err.cannot.write", this.tempFile.getName()));
            }
        }
        return encodingString != null ? new OutputStreamWriter(fileOutputStream, encodingString) : new OutputStreamWriter(fileOutputStream);
    }

    private static Charset lookupCharset(String str) {
        if (Charset.isSupported(str)) {
            try {
                return Charset.forName(str);
            } catch (UnsupportedCharsetException e) {
                throw new Error(e);
            }
        }
        return null;
    }

    public static boolean canConvert(char c) {
        return encoder != null && encoder.canEncode(c);
    }

    private static void initializeConverter() throws UnsupportedEncodingException {
        Charset lookupCharset;
        try {
            if (encodingString == null) {
                lookupCharset = lookupCharset(defaultEncoding);
            } else {
                lookupCharset = lookupCharset(encodingString);
            }
            Charset charset = lookupCharset;
            encoder = charset != null ? charset.newEncoder() : null;
        } catch (IllegalCharsetNameException e) {
            throw new Error(e);
        }
    }

    private String getMsg(String str) {
        try {
            return rsrc.getString(str);
        } catch (MissingResourceException e) {
            throw new Error("Error in  message file format.");
        }
    }

    private String formatMsg(String str, String str2) {
        return MessageFormat.format(getMsg(str), str2);
    }

    public static void main(String[] strArr) {
        System.exit(new Main().convert(strArr) ? 0 : 1);
    }
}
