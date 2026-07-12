package com.sun.tools.corba.se.idl.toJavaPortable;

import com.sun.tools.corba.se.idl.InvalidArgument;
import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/toJavaPortable/Arguments.class */
public class Arguments extends com.sun.tools.corba.se.idl.Arguments {
    public static final int None = 0;
    public static final int Client = 1;
    public static final int Server = 2;
    public static final int All = 3;
    public Hashtable packages = new Hashtable();
    public String separator = null;
    public int emit = 0;
    public boolean TIEServer = false;
    public boolean POAServer = true;
    public boolean LocalOptimization = false;
    public NameModifier skeletonNameModifier = null;
    public NameModifier tieNameModifier = null;
    public Hashtable packageTranslation = new Hashtable();
    public String targetDir = "";

    public Arguments() {
        this.corbaLevel = 2.4f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.tools.corba.se.idl.Arguments
    public void parseOtherArgs(String[] strArr, Properties properties) throws InvalidArgument {
        String str = null;
        String str2 = null;
        this.packages.put("CORBA", "org.omg");
        packageFromProps(properties);
        try {
            Vector vector = new Vector();
            int i = 0;
            while (i < strArr.length) {
                String lowerCase = strArr[i].toLowerCase();
                if (lowerCase.charAt(0) != '-' && lowerCase.charAt(0) != '/') {
                    throw new InvalidArgument(strArr[i]);
                }
                if (lowerCase.charAt(0) == '-') {
                    lowerCase = lowerCase.substring(1);
                }
                if (lowerCase.startsWith("f")) {
                    if (lowerCase.equals("f")) {
                        i++;
                        lowerCase = 'f' + strArr[i].toLowerCase();
                    }
                    if (lowerCase.equals("fclient")) {
                        this.emit = (this.emit == 2 || this.emit == 3) ? 3 : 1;
                    } else if (lowerCase.equals("fserver")) {
                        this.emit = (this.emit == 1 || this.emit == 3) ? 3 : 2;
                        this.TIEServer = false;
                    } else if (lowerCase.equals("fall")) {
                        this.emit = 3;
                        this.TIEServer = false;
                    } else if (lowerCase.equals("fservertie")) {
                        this.emit = (this.emit == 1 || this.emit == 3) ? 3 : 2;
                        this.TIEServer = true;
                    } else if (lowerCase.equals("falltie")) {
                        this.emit = 3;
                        this.TIEServer = true;
                    } else {
                        i = collectUnknownArg(strArr, i, vector);
                    }
                } else if (lowerCase.equals("pkgtranslate")) {
                    if (i + 2 >= strArr.length) {
                        throw new InvalidArgument(strArr[i]);
                    }
                    int i2 = i + 1;
                    String str3 = strArr[i2];
                    i = i2 + 1;
                    String str4 = strArr[i];
                    checkPackageNameValid(str3);
                    checkPackageNameValid(str4);
                    if (str3.equals("org") || str3.startsWith("org.omg")) {
                        throw new InvalidArgument(strArr[i]);
                    }
                    this.packageTranslation.put(str3.replace('.', '/'), str4.replace('.', '/'));
                } else if (lowerCase.equals("pkgprefix")) {
                    if (i + 2 >= strArr.length) {
                        throw new InvalidArgument(strArr[i]);
                    }
                    int i3 = i + 1;
                    String str5 = strArr[i3];
                    i = i3 + 1;
                    String str6 = strArr[i];
                    checkPackageNameValid(str5);
                    checkPackageNameValid(str6);
                    this.packages.put(str5, str6);
                } else if (lowerCase.equals("td")) {
                    if (i + 1 >= strArr.length) {
                        throw new InvalidArgument(strArr[i]);
                    }
                    i++;
                    String str7 = strArr[i];
                    if (str7.charAt(0) == '-') {
                        throw new InvalidArgument(strArr[i - 1]);
                    }
                    this.targetDir = str7.replace('/', File.separatorChar);
                    if (this.targetDir.charAt(this.targetDir.length() - 1) != File.separatorChar) {
                        this.targetDir += File.separatorChar;
                    }
                } else if (lowerCase.equals("sep")) {
                    if (i + 1 >= strArr.length) {
                        throw new InvalidArgument(strArr[i]);
                    }
                    i++;
                    this.separator = strArr[i];
                } else if (lowerCase.equals("oldimplbase")) {
                    this.POAServer = false;
                } else if (lowerCase.equals("skeletonname")) {
                    if (i + 1 >= strArr.length) {
                        throw new InvalidArgument(strArr[i]);
                    }
                    i++;
                    str = strArr[i];
                } else if (lowerCase.equals("tiename")) {
                    if (i + 1 >= strArr.length) {
                        throw new InvalidArgument(strArr[i]);
                    }
                    i++;
                    str2 = strArr[i];
                } else if (lowerCase.equals("localoptimization")) {
                    this.LocalOptimization = true;
                } else {
                    i = collectUnknownArg(strArr, i, vector);
                }
                i++;
            }
            if (vector.size() > 0) {
                String[] strArr2 = new String[vector.size()];
                vector.copyInto(strArr2);
                super.parseOtherArgs(strArr2, properties);
            }
            setDefaultEmitter();
            setNameModifiers(str, str2);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgument(strArr[strArr.length - 1]);
        }
    }

    protected int collectUnknownArg(String[] strArr, int i, Vector vector) {
        vector.addElement(strArr[i]);
        int i2 = i + 1;
        while (i2 < strArr.length && strArr[i2].charAt(0) != '-' && strArr[i2].charAt(0) != '/') {
            int i3 = i2;
            i2++;
            vector.addElement(strArr[i3]);
        }
        return i2 - 1;
    }

    protected void packageFromProps(Properties properties) throws InvalidArgument {
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            if (str.startsWith("PkgPrefix.")) {
                String substring = str.substring(10);
                String property = properties.getProperty(str);
                checkPackageNameValid(property);
                checkPackageNameValid(substring);
                this.packages.put(substring, property);
            }
        }
    }

    protected void setDefaultEmitter() {
        if (this.emit == 0) {
            this.emit = 1;
        }
    }

    protected void setNameModifiers(String str, String str2) {
        String str3;
        String str4;
        if (this.emit > 1) {
            if (str != null) {
                str3 = str;
            } else if (this.POAServer) {
                str3 = "%POA";
            } else {
                str3 = "_%ImplBase";
            }
            if (str2 != null) {
                str4 = str2;
            } else if (this.POAServer) {
                str4 = "%POATie";
            } else {
                str4 = "%_Tie";
            }
            this.skeletonNameModifier = new NameModifierImpl(str3);
            this.tieNameModifier = new NameModifierImpl(str4);
        }
    }

    private void checkPackageNameValid(String str) throws InvalidArgument {
        if (str.charAt(0) == '.') {
            throw new InvalidArgument(str);
        }
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '.') {
                if (i != str.length() - 1) {
                    i++;
                    if (!Character.isJavaIdentifierStart(str.charAt(i))) {
                    }
                }
                throw new InvalidArgument(str);
            }
            if (!Character.isJavaIdentifierPart(str.charAt(i))) {
                throw new InvalidArgument(str);
            }
            i++;
        }
    }
}
