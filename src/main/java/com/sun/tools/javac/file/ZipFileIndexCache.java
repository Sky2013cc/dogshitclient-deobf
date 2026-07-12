package com.sun.tools.javac.file;

import com.sun.tools.javac.file.RelativePath;
import com.sun.tools.javac.util.Context;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: target.jar:com/sun/tools/javac/file/ZipFileIndexCache.class */
public class ZipFileIndexCache {
    private final Map<File, ZipFileIndex> map = new HashMap();
    private static ZipFileIndexCache sharedInstance;

    public static synchronized ZipFileIndexCache getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new ZipFileIndexCache();
        }
        return sharedInstance;
    }

    public static ZipFileIndexCache instance(Context context) {
        ZipFileIndexCache zipFileIndexCache = (ZipFileIndexCache) context.get(ZipFileIndexCache.class);
        if (zipFileIndexCache == null) {
            ZipFileIndexCache zipFileIndexCache2 = new ZipFileIndexCache();
            zipFileIndexCache = zipFileIndexCache2;
            context.put((Class<Class>) ZipFileIndexCache.class, (Class) zipFileIndexCache2);
        }
        return zipFileIndexCache;
    }

    public List<ZipFileIndex> getZipFileIndexes() {
        return getZipFileIndexes(false);
    }

    public synchronized List<ZipFileIndex> getZipFileIndexes(boolean z) {
        ArrayList<ZipFileIndex> arrayList = new ArrayList();
        arrayList.addAll(this.map.values());
        if (z) {
            for (ZipFileIndex zipFileIndex : arrayList) {
                if (!zipFileIndex.isOpen()) {
                    arrayList.remove(zipFileIndex);
                }
            }
        }
        return arrayList;
    }

    public synchronized ZipFileIndex getZipFileIndex(File file, RelativePath.RelativeDirectory relativeDirectory, boolean z, String str, boolean z2) throws IOException {
        ZipFileIndex existingZipIndex = getExistingZipIndex(file);
        if (existingZipIndex == null || (existingZipIndex != null && file.lastModified() != existingZipIndex.zipFileLastModified)) {
            existingZipIndex = new ZipFileIndex(file, relativeDirectory, z2, z, str);
            this.map.put(file, existingZipIndex);
        }
        return existingZipIndex;
    }

    public synchronized ZipFileIndex getExistingZipIndex(File file) {
        return this.map.get(file);
    }

    public synchronized void clearCache() {
        this.map.clear();
    }

    public synchronized void clearCache(long j) {
        for (File file : this.map.keySet()) {
            ZipFileIndex zipFileIndex = this.map.get(file);
            if (zipFileIndex != null) {
                long j2 = zipFileIndex.lastReferenceTimeStamp + j;
                if (j2 < zipFileIndex.lastReferenceTimeStamp || System.currentTimeMillis() > j2) {
                    this.map.remove(file);
                }
            }
        }
    }

    public synchronized void removeFromCache(File file) {
        this.map.remove(file);
    }

    public synchronized void setOpenedIndexes(List<ZipFileIndex> list) throws IllegalStateException {
        if (this.map.isEmpty()) {
            throw new IllegalStateException("Setting opened indexes should be called only when the ZipFileCache is empty. Call JavacFileManager.flush() before calling this method.");
        }
        for (ZipFileIndex zipFileIndex : list) {
            this.map.put(zipFileIndex.zipFile, zipFileIndex);
        }
    }
}
