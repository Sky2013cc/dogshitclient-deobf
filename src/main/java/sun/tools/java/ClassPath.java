package sun.tools.java;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/* loaded from: target.jar:sun/tools/java/ClassPath.class */
public class ClassPath {
    static final char dirSeparator = File.pathSeparatorChar;
    String pathstr;
    private ClassPathEntry[] path;
    private final String fileSeparatorChar = "" + File.separatorChar;

    public ClassPath(String str) {
        init(str);
    }

    public ClassPath(String[] strArr) {
        init(strArr);
    }

    public ClassPath() {
        String property = System.getProperty("sun.boot.class.path");
        String property2 = System.getProperty("env.class.path");
        init(property + File.pathSeparator + (property2 == null ? sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR : property2));
    }

    private void init(String str) {
        this.pathstr = str;
        if (str.length() == 0) {
            this.path = new ClassPathEntry[0];
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            int indexOf = str.indexOf(dirSeparator, i2);
            if (indexOf == -1) {
                break;
            }
            i++;
            i2 = indexOf + 1;
        }
        ClassPathEntry[] classPathEntryArr = new ClassPathEntry[i + 1];
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = i3;
            if (i5 < length) {
                int indexOf2 = str.indexOf(dirSeparator, i5);
                int i6 = indexOf2;
                if (indexOf2 == -1) {
                    i6 = length;
                }
                if (i5 == i6) {
                    classPathEntryArr[i4] = new ClassPathEntry();
                    int i7 = i4;
                    i4++;
                    classPathEntryArr[i7].dir = new File(sun.rmi.rmic.iiop.Constants.NAME_SEPARATOR);
                } else {
                    File file = new File(str.substring(i5, i6));
                    if (file.isFile()) {
                        try {
                            ZipFile zipFile = new ZipFile(file);
                            classPathEntryArr[i4] = new ClassPathEntry();
                            int i8 = i4;
                            i4++;
                            classPathEntryArr[i8].zip = zipFile;
                        } catch (ZipException e) {
                        } catch (IOException e2) {
                        }
                    } else {
                        classPathEntryArr[i4] = new ClassPathEntry();
                        int i9 = i4;
                        i4++;
                        classPathEntryArr[i9].dir = file;
                    }
                }
                i3 = i6 + 1;
            } else {
                this.path = new ClassPathEntry[i4];
                System.arraycopy(classPathEntryArr, 0, this.path, 0, i4);
                return;
            }
        }
    }

    private void init(String[] strArr) {
        if (strArr.length == 0) {
            this.pathstr = "";
        } else {
            StringBuilder sb = new StringBuilder(strArr[0]);
            for (int i = 1; i < strArr.length; i++) {
                sb.append(File.pathSeparatorChar);
                sb.append(strArr[i]);
            }
            this.pathstr = sb.toString();
        }
        ClassPathEntry[] classPathEntryArr = new ClassPathEntry[strArr.length];
        int i2 = 0;
        for (String str : strArr) {
            File file = new File(str);
            if (file.isFile()) {
                try {
                    ZipFile zipFile = new ZipFile(file);
                    classPathEntryArr[i2] = new ClassPathEntry();
                    int i3 = i2;
                    i2++;
                    classPathEntryArr[i3].zip = zipFile;
                } catch (ZipException e) {
                } catch (IOException e2) {
                }
            } else {
                classPathEntryArr[i2] = new ClassPathEntry();
                int i4 = i2;
                i2++;
                classPathEntryArr[i4].dir = file;
            }
        }
        this.path = new ClassPathEntry[i2];
        System.arraycopy(classPathEntryArr, 0, this.path, 0, i2);
    }

    public ClassFile getDirectory(String str) {
        return getFile(str, true);
    }

    public ClassFile getFile(String str) {
        return getFile(str, false);
    }

    private ClassFile getFile(String str, boolean z) {
        String str2 = str;
        String str3 = "";
        if (!z) {
            int lastIndexOf = str.lastIndexOf(File.separatorChar);
            str2 = str.substring(0, lastIndexOf + 1);
            str3 = str.substring(lastIndexOf + 1);
        } else if (!str2.equals("") && !str2.endsWith(this.fileSeparatorChar)) {
            str2 = str2 + File.separatorChar;
            str = str2;
        }
        for (int i = 0; i < this.path.length; i++) {
            if (this.path[i].zip != null) {
                ZipEntry entry = this.path[i].zip.getEntry(str.replace(File.separatorChar, '/'));
                if (entry != null) {
                    return new ClassFile(this.path[i].zip, entry);
                }
            } else {
                File file = new File(this.path[i].dir.getPath(), str);
                String[] files = this.path[i].getFiles(str2);
                if (z) {
                    if (files.length > 0) {
                        return new ClassFile(file);
                    }
                } else {
                    for (String str4 : files) {
                        if (str3.equals(str4)) {
                            return new ClassFile(file);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Enumeration getFiles(String str, String str2) {
        Hashtable hashtable = new Hashtable();
        int length = this.path.length;
        while (true) {
            length--;
            if (length >= 0) {
                if (this.path[length].zip != null) {
                    Enumeration<? extends ZipEntry> entries = this.path[length].zip.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry nextElement = entries.nextElement();
                        String replace = nextElement.getName().replace('/', File.separatorChar);
                        if (replace.startsWith(str) && replace.endsWith(str2)) {
                            hashtable.put(replace, new ClassFile(this.path[length].zip, nextElement));
                        }
                    }
                } else {
                    for (String str3 : this.path[length].getFiles(str)) {
                        if (str3.endsWith(str2)) {
                            String str4 = str + File.separatorChar + str3;
                            hashtable.put(str4, new ClassFile(new File(this.path[length].dir.getPath(), str4)));
                        }
                    }
                }
            } else {
                return hashtable.elements();
            }
        }
    }

    public void close() throws IOException {
        int length = this.path.length;
        while (true) {
            length--;
            if (length >= 0) {
                if (this.path[length].zip != null) {
                    this.path[length].zip.close();
                }
            } else {
                return;
            }
        }
    }

    public String toString() {
        return this.pathstr;
    }
}
