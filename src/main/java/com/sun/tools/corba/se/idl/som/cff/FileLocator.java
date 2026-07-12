package com.sun.tools.corba.se.idl.som.cff;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import sun.rmi.rmic.iiop.Constants;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/corba/se/idl/som/cff/FileLocator.class */
public abstract class FileLocator {
    static final Properties pp = System.getProperties();
    static final String classPath = pp.getProperty("java.class.path", Constants.NAME_SEPARATOR);
    static final String pathSeparator = pp.getProperty("path.separator", RuntimeConstants.SIG_ENDCLASS);

    public static DataInputStream locateClassFile(String str) throws FileNotFoundException, IOException {
        boolean z = true;
        String str2 = "";
        File file = null;
        StringTokenizer stringTokenizer = new StringTokenizer(classPath, pathSeparator, false);
        String str3 = str.replace('.', File.separatorChar) + ".class";
        while (stringTokenizer.hasMoreTokens() && z) {
            try {
                str2 = stringTokenizer.nextToken();
                int length = str2.length();
                String substring = length > 3 ? str2.substring(length - 4) : "";
                if (substring.equalsIgnoreCase(".zip") || substring.equalsIgnoreCase(".jar")) {
                    try {
                        NamedDataInputStream locateInZipFile = locateInZipFile(str2, str, true, true);
                        if (locateInZipFile != null) {
                            return locateInZipFile;
                        }
                    } catch (ZipException e) {
                    } catch (IOException e2) {
                    }
                } else {
                    try {
                        file = new File(str2 + File.separator + str3);
                        if (file != null && file.exists()) {
                            z = false;
                        }
                    } catch (NullPointerException e3) {
                    }
                }
            } catch (NoSuchElementException e4) {
            }
        }
        if (z) {
            int lastIndexOf = str.lastIndexOf(46);
            String substring2 = lastIndexOf >= 0 ? str.substring(lastIndexOf + 1) : str;
            return new NamedDataInputStream(new BufferedInputStream(new FileInputStream(substring2 + ".class")), substring2 + ".class", false);
        }
        return new NamedDataInputStream(new BufferedInputStream(new FileInputStream(file)), str2 + File.separator + str3, false);
    }

    public static DataInputStream locateLocaleSpecificFileInClassPath(String str) throws FileNotFoundException, IOException {
        String str2;
        String str3;
        String str4 = "_" + Locale.getDefault().toString();
        int lastIndexOf = str.lastIndexOf(47);
        int lastIndexOf2 = str.lastIndexOf(46);
        DataInputStream dataInputStream = null;
        boolean z = false;
        if (lastIndexOf2 > 0 && lastIndexOf2 > lastIndexOf) {
            str2 = str.substring(0, lastIndexOf2);
            str3 = str.substring(lastIndexOf2);
        } else {
            str2 = str;
            str3 = "";
        }
        while (true) {
            if (z) {
                dataInputStream = locateFileInClassPath(str);
            } else {
                try {
                    dataInputStream = locateFileInClassPath(str2 + str4 + str3);
                } catch (Exception e) {
                }
            }
            if (dataInputStream != null || z) {
                break;
            }
            int lastIndexOf3 = str4.lastIndexOf(95);
            if (lastIndexOf3 > 0) {
                str4 = str4.substring(0, lastIndexOf3);
            } else {
                z = true;
            }
        }
        return dataInputStream;
    }

    public static DataInputStream locateFileInClassPath(String str) throws FileNotFoundException, IOException {
        boolean z = true;
        String str2 = "";
        File file = null;
        String replace = File.separatorChar == '/' ? str : str.replace(File.separatorChar, '/');
        String replace2 = File.separatorChar == '/' ? str : str.replace('/', File.separatorChar);
        StringTokenizer stringTokenizer = new StringTokenizer(classPath, pathSeparator, false);
        while (stringTokenizer.hasMoreTokens() && z) {
            try {
                str2 = stringTokenizer.nextToken();
                int length = str2.length();
                String substring = length > 3 ? str2.substring(length - 4) : "";
                if (substring.equalsIgnoreCase(".zip") || substring.equalsIgnoreCase(".jar")) {
                    try {
                        NamedDataInputStream locateInZipFile = locateInZipFile(str2, replace, false, false);
                        if (locateInZipFile != null) {
                            return locateInZipFile;
                        }
                    } catch (ZipException e) {
                    } catch (IOException e2) {
                    }
                } else {
                    try {
                        file = new File(str2 + File.separator + replace2);
                        if (file != null && file.exists()) {
                            z = false;
                        }
                    } catch (NullPointerException e3) {
                    }
                }
            } catch (NoSuchElementException e4) {
            }
        }
        if (z) {
            int lastIndexOf = replace2.lastIndexOf(File.separator);
            String substring2 = lastIndexOf >= 0 ? replace2.substring(lastIndexOf + 1) : replace2;
            return new NamedDataInputStream(new BufferedInputStream(new FileInputStream(substring2)), substring2, false);
        }
        return new NamedDataInputStream(new BufferedInputStream(new FileInputStream(file)), str2 + File.separator + replace2, false);
    }

    public static String getFileNameFromStream(DataInputStream dataInputStream) {
        if (dataInputStream instanceof NamedDataInputStream) {
            return ((NamedDataInputStream) dataInputStream).fullyQualifiedFileName;
        }
        return "";
    }

    public static boolean isZipFileAssociatedWithStream(DataInputStream dataInputStream) {
        if (dataInputStream instanceof NamedDataInputStream) {
            return ((NamedDataInputStream) dataInputStream).inZipFile;
        }
        return false;
    }

    private static NamedDataInputStream locateInZipFile(String str, String str2, boolean z, boolean z2) throws ZipException, IOException {
        ZipFile zipFile = new ZipFile(str);
        if (zipFile == null) {
            return null;
        }
        String str3 = z ? str2.replace('.', '/') + ".class" : str2;
        ZipEntry entry = zipFile.getEntry(str3);
        if (entry == null) {
            zipFile.close();
            return null;
        }
        InputStream inputStream = zipFile.getInputStream(entry);
        if (z2) {
            inputStream = new BufferedInputStream(inputStream);
        }
        return new NamedDataInputStream(inputStream, str + '(' + str3 + ')', true);
    }
}
