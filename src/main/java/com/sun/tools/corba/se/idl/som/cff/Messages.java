package com.sun.tools.corba.se.idl.som.cff;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/som/cff/Messages.class */
public abstract class Messages {
    private static final String LTB = "%B";
    private static final char NL = '\n';
    private static final String lineSeparator = System.getProperty("line.separator");
    private static final Properties m = new Properties();
    private static boolean loadNeeded = true;

    private static final synchronized void loadDefaultProperties() {
        if (!loadNeeded) {
            return;
        }
        try {
            m.load(FileLocator.locateLocaleSpecificFileInClassPath("com/sun/tools/corba/se/idl/som/cff/cff.properties"));
        } catch (IOException e) {
        }
        fixMessages(m);
        loadNeeded = false;
    }

    private static final void fixMessages(Properties properties) {
        String str;
        Enumeration keys = properties.keys();
        Enumeration elements = properties.elements();
        while (keys.hasMoreElements()) {
            String str2 = (String) keys.nextElement();
            String str3 = (String) elements.nextElement();
            int indexOf = str3.indexOf(LTB);
            boolean z = false;
            while (indexOf != -1) {
                if (indexOf == 0) {
                    str = " " + str3.substring(2);
                } else {
                    str = str3.substring(0, indexOf) + " " + str3.substring(indexOf + 2);
                }
                str3 = str;
                z = true;
                indexOf = str3.indexOf(LTB);
            }
            int length = lineSeparator.length() - 1;
            int i = 0;
            while (i < str3.length()) {
                if (str3.charAt(i) == '\n') {
                    str3 = str3.substring(0, i) + lineSeparator + str3.substring(i + 1);
                    i += length;
                    z = true;
                }
                i++;
            }
            if (z) {
                properties.put(str2, str3);
            }
        }
    }

    public static final synchronized void msgLoad(String str) throws IOException {
        m.load(FileLocator.locateLocaleSpecificFileInClassPath(str));
        fixMessages(m);
        loadNeeded = false;
    }

    public static final String msg(String str) {
        if (loadNeeded) {
            loadDefaultProperties();
        }
        return m.getProperty(str, str);
    }

    public static final String msg(String str, String str2) {
        if (loadNeeded) {
            loadDefaultProperties();
        }
        String property = m.getProperty(str, str);
        int indexOf = property.indexOf("%1");
        if (indexOf >= 0) {
            String str3 = "";
            if (indexOf + 2 < property.length()) {
                str3 = property.substring(indexOf + 2);
            }
            return property.substring(0, indexOf) + str2 + str3;
        }
        return property + " " + str2;
    }

    public static final String msg(String str, int i) {
        return msg(str, String.valueOf(i));
    }

    public static final String msg(String str, String str2, String str3) {
        String str4;
        String str5;
        if (loadNeeded) {
            loadDefaultProperties();
        }
        String property = m.getProperty(str, str);
        String str6 = "";
        int indexOf = property.indexOf("%1");
        if (indexOf >= 0) {
            if (indexOf + 2 < property.length()) {
                str6 = property.substring(indexOf + 2);
            }
            str4 = property.substring(0, indexOf) + str2 + str6;
        } else {
            str4 = property + " " + str2;
        }
        int indexOf2 = str4.indexOf("%2");
        if (indexOf2 >= 0) {
            String str7 = "";
            if (indexOf2 + 2 < str4.length()) {
                str7 = str4.substring(indexOf2 + 2);
            }
            str5 = str4.substring(0, indexOf2) + str3 + str7;
        } else {
            str5 = str4 + " " + str3;
        }
        return str5;
    }

    public static final String msg(String str, int i, String str2) {
        return msg(str, String.valueOf(i), str2);
    }

    public static final String msg(String str, String str2, int i) {
        return msg(str, str2, String.valueOf(i));
    }

    public static final String msg(String str, int i, int i2) {
        return msg(str, String.valueOf(i), String.valueOf(i2));
    }

    public static final String msg(String str, String str2, String str3, String str4) {
        if (loadNeeded) {
            loadDefaultProperties();
        }
        return substituteString(substituteString(substituteString(m.getProperty(str, str), 1, str2), 2, str3), 3, str4);
    }

    private static String substituteString(String str, int i, String str2) {
        String str3;
        String str4 = "%" + i;
        int length = str4.length();
        int indexOf = str.indexOf(str4);
        String str5 = "";
        if (indexOf >= 0) {
            if (indexOf + length < str.length()) {
                str5 = str.substring(indexOf + length);
            }
            str3 = str.substring(0, indexOf) + str2 + str5;
        } else {
            str3 = str + " " + str2;
        }
        return str3;
    }
}
