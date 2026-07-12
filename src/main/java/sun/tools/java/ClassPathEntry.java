package sun.tools.java;

import java.io.File;
import java.util.Hashtable;
import java.util.zip.ZipFile;

/* compiled from: ClassPath.java */
/* loaded from: target.jar:sun/tools/java/ClassPathEntry.class */
class ClassPathEntry {
    File dir;
    ZipFile zip;
    Hashtable subdirs = new Hashtable(29);

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getFiles(String str) {
        String[] strArr = (String[]) this.subdirs.get(str);
        if (strArr == null) {
            File file = new File(this.dir.getPath(), str);
            if (file.isDirectory()) {
                strArr = file.list();
                if (strArr == null) {
                    strArr = new String[0];
                }
                if (strArr.length == 0) {
                    strArr = new String[]{""};
                }
            } else {
                strArr = new String[0];
            }
            this.subdirs.put(str, strArr);
        }
        return strArr;
    }
}
