package com.sun.tools.javac.file;

import com.sun.tools.javac.util.Context;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: target.jar:com/sun/tools/javac/file/CacheFSInfo.class */
public class CacheFSInfo extends FSInfo {
    private Map<File, Entry> cache = new ConcurrentHashMap();

    public static void preRegister(Context context) {
        context.put(FSInfo.class, (Context.Factory) new Context.Factory<FSInfo>() { // from class: com.sun.tools.javac.file.CacheFSInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sun.tools.javac.util.Context.Factory
            public FSInfo make(Context context2) {
                CacheFSInfo cacheFSInfo = new CacheFSInfo();
                context2.put((Class<Class>) FSInfo.class, (Class) cacheFSInfo);
                return cacheFSInfo;
            }
        });
    }

    public void clearCache() {
        this.cache.clear();
    }

    @Override // com.sun.tools.javac.file.FSInfo
    public File getCanonicalFile(File file) {
        return getEntry(file).canonicalFile;
    }

    @Override // com.sun.tools.javac.file.FSInfo
    public boolean exists(File file) {
        return getEntry(file).exists;
    }

    @Override // com.sun.tools.javac.file.FSInfo
    public boolean isDirectory(File file) {
        return getEntry(file).isDirectory;
    }

    @Override // com.sun.tools.javac.file.FSInfo
    public boolean isFile(File file) {
        return getEntry(file).isFile;
    }

    @Override // com.sun.tools.javac.file.FSInfo
    public List<File> getJarClassPath(File file) throws IOException {
        Entry entry = getEntry(file);
        if (entry.jarClassPath == null) {
            entry.jarClassPath = super.getJarClassPath(file);
        }
        return entry.jarClassPath;
    }

    private Entry getEntry(File file) {
        Entry entry = this.cache.get(file);
        if (entry == null) {
            entry = new Entry();
            entry.canonicalFile = super.getCanonicalFile(file);
            entry.exists = super.exists(file);
            entry.isDirectory = super.isDirectory(file);
            entry.isFile = super.isFile(file);
            this.cache.put(file, entry);
        }
        return entry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/javac/file/CacheFSInfo$Entry.class */
    public static class Entry {
        File canonicalFile;
        boolean exists;
        boolean isFile;
        boolean isDirectory;
        List<File> jarClassPath;

        private Entry() {
        }
    }
}
