package com.sun.tools.javac.file;

import com.sun.tools.javac.util.Context;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/* loaded from: target.jar:com/sun/tools/javac/file/FSInfo.class */
public class FSInfo {
    public static FSInfo instance(Context context) {
        FSInfo fSInfo = (FSInfo) context.get(FSInfo.class);
        if (fSInfo == null) {
            fSInfo = new FSInfo();
        }
        return fSInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FSInfo() {
    }

    protected FSInfo(Context context) {
        context.put((Class<Class>) FSInfo.class, (Class) this);
    }

    public File getCanonicalFile(File file) {
        try {
            return file.getCanonicalFile();
        } catch (IOException e) {
            return file.getAbsoluteFile();
        }
    }

    public boolean exists(File file) {
        return file.exists();
    }

    public boolean isDirectory(File file) {
        return file.isDirectory();
    }

    public boolean isFile(File file) {
        return file.isFile();
    }

    public List<File> getJarClassPath(File file) throws IOException {
        String parent = file.getParent();
        JarFile jarFile = new JarFile(file);
        try {
            Manifest manifest = jarFile.getManifest();
            if (manifest == null) {
                List<File> emptyList = Collections.emptyList();
                jarFile.close();
                return emptyList;
            }
            Attributes mainAttributes = manifest.getMainAttributes();
            if (mainAttributes == null) {
                List<File> emptyList2 = Collections.emptyList();
                jarFile.close();
                return emptyList2;
            }
            String value = mainAttributes.getValue(Attributes.Name.CLASS_PATH);
            if (value == null) {
                List<File> emptyList3 = Collections.emptyList();
                jarFile.close();
                return emptyList3;
            }
            ArrayList arrayList = new ArrayList();
            StringTokenizer stringTokenizer = new StringTokenizer(value);
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                arrayList.add(parent == null ? new File(nextToken) : new File(parent, nextToken));
            }
            return arrayList;
        } finally {
            jarFile.close();
        }
    }
}
