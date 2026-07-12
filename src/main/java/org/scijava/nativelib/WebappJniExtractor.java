package org.scijava.nativelib;

import java.io.File;
import java.io.IOException;
import sun.rmi.rmic.iiop.Constants;

/* loaded from: target.jar:org/scijava/nativelib/WebappJniExtractor.class */
public class WebappJniExtractor extends BaseJniExtractor {
    private File nativeDir = new File(System.getProperty("java.library.tmpdir", "tmplib"));
    private File jniSubDir;

    public WebappJniExtractor(String classloaderName) throws IOException {
        this.nativeDir.mkdirs();
        if (!this.nativeDir.isDirectory()) {
            throw new IOException("Unable to create native library working directory " + this.nativeDir);
        }
        long now = System.currentTimeMillis();
        int attempt = 0;
        while (true) {
            File trialJniSubDir = new File(this.nativeDir, classloaderName + Constants.NAME_SEPARATOR + now + Constants.NAME_SEPARATOR + attempt);
            if (!trialJniSubDir.mkdir()) {
                if (trialJniSubDir.exists()) {
                    attempt++;
                } else {
                    throw new IOException("Unable to create native library working directory " + trialJniSubDir);
                }
            } else {
                this.jniSubDir = trialJniSubDir;
                this.jniSubDir.deleteOnExit();
                return;
            }
        }
    }

    protected void finalize() throws Throwable {
        File[] files = this.jniSubDir.listFiles();
        for (File file : files) {
            file.delete();
        }
        this.jniSubDir.delete();
    }

    @Override // org.scijava.nativelib.BaseJniExtractor
    public File getJniDir() {
        return this.jniSubDir;
    }

    @Override // org.scijava.nativelib.BaseJniExtractor
    public File getNativeDir() {
        return this.nativeDir;
    }
}
