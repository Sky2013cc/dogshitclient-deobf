package com.sun.tools.corba.se.idl;

import com.sun.tools.corba.se.idl.som.cff.FileLocator;
import com.sun.tools.doclint.DocLint;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/Arguments.class */
public class Arguments {
    public String file = null;
    public boolean verbose = false;
    public boolean keepOldFiles = false;
    public boolean emitAll = false;
    public Vector includePaths = new Vector();
    public Hashtable definedSymbols = new Hashtable();
    public boolean cppModule = false;
    public boolean versionRequest = false;
    public float corbaLevel = 2.2f;
    public boolean noWarn = false;
    public boolean scannerDebugFlag = false;
    public boolean tokenDebugFlag = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public void parseOtherArgs(String[] strArr, Properties properties) throws InvalidArgument {
        if (strArr.length > 0) {
            throw new InvalidArgument(strArr[0]);
        }
    }

    protected void setDebugFlags(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, DocLint.TAGS_SEPARATOR);
        while (stringTokenizer.hasMoreTokens()) {
            try {
                Field field = getClass().getField(stringTokenizer.nextToken() + "DebugFlag");
                int modifiers = field.getModifiers();
                if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) && field.getType() == Boolean.TYPE) {
                    field.setBoolean(this, true);
                }
            } catch (Exception e) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void parseArgs(String[] strArr) throws InvalidArgument {
        String[] strArr2;
        Vector vector = new Vector();
        int i = 0;
        while (i < strArr.length - 1) {
            try {
                String lowerCase = strArr[i].toLowerCase();
                if (lowerCase.charAt(0) != '-' && lowerCase.charAt(0) != '/') {
                    throw new InvalidArgument(strArr[i]);
                }
                if (lowerCase.charAt(0) == '-') {
                    lowerCase = lowerCase.substring(1);
                }
                if (lowerCase.equals(OperatorName.SET_FLATNESS)) {
                    i++;
                    this.includePaths.addElement(strArr[i]);
                } else if (lowerCase.startsWith(OperatorName.SET_FLATNESS)) {
                    this.includePaths.addElement(strArr[i].substring(2));
                } else if (lowerCase.equals(OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT) || lowerCase.equals("verbose")) {
                    this.verbose = true;
                } else if (lowerCase.equals(OperatorName.SET_LINE_DASHPATTERN)) {
                    i++;
                    this.definedSymbols.put(strArr[i], "");
                } else if (lowerCase.equals("debug")) {
                    i++;
                    setDebugFlags(strArr[i]);
                } else if (lowerCase.startsWith(OperatorName.SET_LINE_DASHPATTERN)) {
                    this.definedSymbols.put(strArr[i].substring(2), "");
                } else if (lowerCase.equals("emitall")) {
                    this.emitAll = true;
                } else if (lowerCase.equals("keep")) {
                    this.keepOldFiles = true;
                } else if (lowerCase.equals("nowarn")) {
                    this.noWarn = true;
                } else if (lowerCase.equals("trace")) {
                    Runtime.getRuntime().traceMethodCalls(true);
                } else if (lowerCase.equals("cppmodule")) {
                    this.cppModule = true;
                } else if (lowerCase.equals("version")) {
                    this.versionRequest = true;
                } else if (lowerCase.equals("corba")) {
                    if (i + 1 >= strArr.length) {
                        throw new InvalidArgument(strArr[i]);
                    }
                    i++;
                    String str = strArr[i];
                    if (str.charAt(0) == '-') {
                        throw new InvalidArgument(strArr[i - 1]);
                    }
                    try {
                        this.corbaLevel = new Float(str).floatValue();
                    } catch (NumberFormatException e) {
                        throw new InvalidArgument(strArr[i]);
                    }
                } else {
                    vector.addElement(strArr[i]);
                    int i2 = i + 1;
                    while (i2 < strArr.length - 1 && strArr[i2].charAt(0) != '-' && strArr[i2].charAt(0) != '/') {
                        int i3 = i2;
                        i2++;
                        vector.addElement(strArr[i3]);
                    }
                    i = i2 - 1;
                }
                i++;
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new InvalidArgument(strArr[strArr.length - 1]);
            }
        }
        if (i == strArr.length - 1) {
            if (strArr[i].toLowerCase().equals("-version")) {
                this.versionRequest = true;
            } else {
                this.file = strArr[i];
            }
            Properties properties = new Properties();
            try {
                properties.load(FileLocator.locateFileInClassPath("idl.config"));
                addIncludePaths(properties);
            } catch (IOException e3) {
            }
            if (vector.size() > 0) {
                strArr2 = new String[vector.size()];
                vector.copyInto(strArr2);
            } else {
                strArr2 = new String[0];
            }
            parseOtherArgs(strArr2, properties);
            return;
        }
        throw new InvalidArgument();
    }

    private void addIncludePaths(Properties properties) {
        String property = properties.getProperty("includes");
        if (property != null) {
            String property2 = System.getProperty("path.separator");
            int i = -property2.length();
            do {
                property = property.substring(i + property2.length());
                i = property.indexOf(property2);
                if (i < 0) {
                    i = property.length();
                }
                this.includePaths.addElement(property.substring(0, i));
            } while (i != property.length());
        }
    }
}
