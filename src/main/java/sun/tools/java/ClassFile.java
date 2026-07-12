package sun.tools.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/* loaded from: target.jar:sun/tools/java/ClassFile.class */
public class ClassFile {
    private File file;
    private ZipFile zipFile;
    private ZipEntry zipEntry;

    public ClassFile(File file) {
        this.file = file;
    }

    public ClassFile(ZipFile zipFile, ZipEntry zipEntry) {
        this.zipFile = zipFile;
        this.zipEntry = zipEntry;
    }

    public boolean isZipped() {
        return this.zipFile != null;
    }

    public InputStream getInputStream() throws IOException {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        try {
            return this.zipFile.getInputStream(this.zipEntry);
        } catch (ZipException e) {
            throw new IOException(e.getMessage());
        }
    }

    public boolean exists() {
        if (this.file != null) {
            return this.file.exists();
        }
        return true;
    }

    public boolean isDirectory() {
        return this.file != null ? this.file.isDirectory() : this.zipEntry.getName().endsWith(RuntimeConstants.SIG_PACKAGE);
    }

    public long lastModified() {
        return this.file != null ? this.file.lastModified() : this.zipEntry.getTime();
    }

    public String getPath() {
        if (this.file != null) {
            return this.file.getPath();
        }
        return this.zipFile.getName() + RuntimeConstants.SIG_METHOD + this.zipEntry.getName() + RuntimeConstants.SIG_ENDMETHOD;
    }

    public String getName() {
        return this.file != null ? this.file.getName() : this.zipEntry.getName();
    }

    public String getAbsoluteName() {
        String absolutePath;
        if (this.file != null) {
            try {
                absolutePath = this.file.getCanonicalPath();
            } catch (IOException e) {
                absolutePath = this.file.getAbsolutePath();
            }
        } else {
            absolutePath = this.zipFile.getName() + RuntimeConstants.SIG_METHOD + this.zipEntry.getName() + RuntimeConstants.SIG_ENDMETHOD;
        }
        return absolutePath;
    }

    public long length() {
        return this.file != null ? this.file.length() : this.zipEntry.getSize();
    }

    public String toString() {
        return this.file != null ? this.file.toString() : this.zipEntry.toString();
    }
}
