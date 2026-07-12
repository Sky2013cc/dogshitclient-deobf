package com.sun.tools.corba.se.idl;

import com.sun.tools.corba.se.idl.som.cff.FileLocator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/Util.class */
public class Util {
    private static Properties messages = null;
    private static String defaultKey = "default";
    private static Vector msgFiles = new Vector();
    static RepositoryID emptyID;

    public static String getVersion() {
        return getVersion("com/sun/tools/corba/se/idl/idl.prp");
    }

    public static String getVersion(String str) {
        String message;
        if (messages == null) {
            Vector vector = msgFiles;
            if (str == null || str.equals("")) {
                str = "com/sun/tools/corba/se/idl/idl.prp";
            }
            registerMessageFile(str.replace('/', File.separatorChar));
            message = getMessage("Version.product", getMessage("Version.number"));
            msgFiles = vector;
            messages = null;
        } else {
            message = getMessage("Version.product", getMessage("Version.number"));
        }
        return message;
    }

    public static boolean isAttribute(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof AttributeEntry;
    }

    public static boolean isConst(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof ConstEntry;
    }

    public static boolean isEnum(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof EnumEntry;
    }

    public static boolean isException(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof ExceptionEntry;
    }

    public static boolean isInterface(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof InterfaceEntry;
    }

    public static boolean isMethod(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof MethodEntry;
    }

    public static boolean isModule(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof ModuleEntry;
    }

    public static boolean isParameter(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof ParameterEntry;
    }

    public static boolean isPrimitive(String str, Hashtable hashtable) {
        int indexOf;
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null && (indexOf = str.indexOf(40)) >= 0) {
            symtabEntry = (SymtabEntry) hashtable.get(str.substring(0, indexOf));
        }
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof PrimitiveEntry;
    }

    public static boolean isSequence(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof SequenceEntry;
    }

    public static boolean isStruct(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof StructEntry;
    }

    public static boolean isString(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof StringEntry;
    }

    public static boolean isTypedef(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof TypedefEntry;
    }

    public static boolean isUnion(String str, Hashtable hashtable) {
        SymtabEntry symtabEntry = (SymtabEntry) hashtable.get(str);
        if (symtabEntry == null) {
            return false;
        }
        return symtabEntry instanceof UnionEntry;
    }

    public static String getMessage(String str) {
        if (messages == null) {
            readMessages();
        }
        String property = messages.getProperty(str);
        if (property == null) {
            property = getDefaultMessage(str);
        }
        return property;
    }

    public static String getMessage(String str, String str2) {
        if (messages == null) {
            readMessages();
        }
        String property = messages.getProperty(str);
        if (property == null) {
            property = getDefaultMessage(str);
        } else {
            int indexOf = property.indexOf("%0");
            if (indexOf >= 0) {
                property = property.substring(0, indexOf) + str2 + property.substring(indexOf + 2);
            }
        }
        return property;
    }

    public static String getMessage(String str, String[] strArr) {
        if (messages == null) {
            readMessages();
        }
        String property = messages.getProperty(str);
        if (property == null) {
            property = getDefaultMessage(str);
        } else {
            for (int i = 0; i < strArr.length; i++) {
                int indexOf = property.indexOf("%" + i);
                if (indexOf >= 0) {
                    property = property.substring(0, indexOf) + strArr[i] + property.substring(indexOf + 2);
                }
            }
        }
        return property;
    }

    private static String getDefaultMessage(String str) {
        String property = messages.getProperty(defaultKey);
        int indexOf = property.indexOf("%0");
        if (indexOf > 0) {
            property = property.substring(0, indexOf) + str;
        }
        return property;
    }

    private static void readMessages() {
        messages = new Properties();
        Enumeration elements = msgFiles.elements();
        while (elements.hasMoreElements()) {
            try {
                messages.load(FileLocator.locateLocaleSpecificFileInClassPath((String) elements.nextElement()));
            } catch (IOException e) {
            }
        }
        if (messages.size() == 0) {
            messages.put(defaultKey, "Error reading Messages File.");
        }
    }

    public static void registerMessageFile(String str) {
        if (str != null) {
            if (messages == null) {
                msgFiles.addElement(str);
                return;
            }
            try {
                messages.load(FileLocator.locateLocaleSpecificFileInClassPath(str));
            } catch (IOException e) {
            }
        }
    }

    static {
        msgFiles.addElement("com/sun/tools/corba/se/idl/idl.prp");
        emptyID = new RepositoryID();
    }

    public static String capitalize(String str) {
        return new String(str.substring(0, 1)).toUpperCase() + str.substring(1);
    }

    public static String getAbsolutePath(String str, Vector vector) throws FileNotFoundException {
        String path;
        File file = new File(str);
        if (file.canRead()) {
            path = file.getAbsolutePath();
        } else {
            Enumeration elements = vector.elements();
            while (!file.canRead() && elements.hasMoreElements()) {
                file = new File(((String) elements.nextElement()) + File.separatorChar + str);
            }
            if (file.canRead()) {
                path = file.getPath();
            } else {
                throw new FileNotFoundException(str);
            }
        }
        return path;
    }

    public static float absDelta(float f, float f2) {
        double d = f - f2;
        return (float) (d < 0.0d ? d * (-1.0d) : d);
    }
}
