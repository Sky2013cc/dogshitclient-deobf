package com.sun.tools.javac.file;

import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.tools.JavaFileObject;
import sun.tools.java.RuntimeConstants;

/* loaded from: target.jar:com/sun/tools/javac/file/RelativePath.class */
public abstract class RelativePath implements Comparable<RelativePath> {
    protected final String path;

    public abstract RelativeDirectory dirname();

    public abstract String basename();

    protected RelativePath(String str) {
        this.path = str;
    }

    public File getFile(File file) {
        if (this.path.length() == 0) {
            return file;
        }
        return new File(file, this.path.replace('/', File.separatorChar));
    }

    @Override // java.lang.Comparable
    public int compareTo(RelativePath relativePath) {
        return this.path.compareTo(relativePath.path);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RelativePath)) {
            return false;
        }
        return this.path.equals(((RelativePath) obj).path);
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public String toString() {
        return "RelPath[" + this.path + "]";
    }

    public String getPath() {
        return this.path;
    }

    /* loaded from: target.jar:com/sun/tools/javac/file/RelativePath$RelativeDirectory.class */
    public static class RelativeDirectory extends RelativePath {
        @Override // com.sun.tools.javac.file.RelativePath, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(RelativePath relativePath) {
            return super.compareTo(relativePath);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static RelativeDirectory forPackage(CharSequence charSequence) {
            return new RelativeDirectory(charSequence.toString().replace('.', '/'));
        }

        public RelativeDirectory(String str) {
            super((str.length() == 0 || str.endsWith(RuntimeConstants.SIG_PACKAGE)) ? str : str + RuntimeConstants.SIG_PACKAGE);
        }

        public RelativeDirectory(RelativeDirectory relativeDirectory, String str) {
            this(relativeDirectory.path + str);
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public RelativeDirectory dirname() {
            int length = this.path.length();
            if (length == 0) {
                return this;
            }
            return new RelativeDirectory(this.path.substring(0, this.path.lastIndexOf(47, length - 2) + 1));
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public String basename() {
            int length = this.path.length();
            if (length == 0) {
                return this.path;
            }
            return this.path.substring(this.path.lastIndexOf(47, length - 2) + 1, length - 1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean contains(RelativePath relativePath) {
            return relativePath.path.length() > this.path.length() && relativePath.path.startsWith(this.path);
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public String toString() {
            return "RelativeDirectory[" + this.path + "]";
        }
    }

    /* loaded from: target.jar:com/sun/tools/javac/file/RelativePath$RelativeFile.class */
    public static class RelativeFile extends RelativePath {
        @Override // com.sun.tools.javac.file.RelativePath, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(RelativePath relativePath) {
            return super.compareTo(relativePath);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static RelativeFile forClass(CharSequence charSequence, JavaFileObject.Kind kind) {
            return new RelativeFile(charSequence.toString().replace('.', '/') + kind.extension);
        }

        public RelativeFile(String str) {
            super(str);
            if (str.endsWith(RuntimeConstants.SIG_PACKAGE)) {
                throw new IllegalArgumentException(str);
            }
        }

        public RelativeFile(RelativeDirectory relativeDirectory, String str) {
            this(relativeDirectory.path + str);
        }

        RelativeFile(RelativeDirectory relativeDirectory, RelativePath relativePath) {
            this(relativeDirectory, relativePath.path);
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public RelativeDirectory dirname() {
            return new RelativeDirectory(this.path.substring(0, this.path.lastIndexOf(47) + 1));
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public String basename() {
            return this.path.substring(this.path.lastIndexOf(47) + 1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ZipEntry getZipEntry(ZipFile zipFile) {
            return zipFile.getEntry(this.path);
        }

        @Override // com.sun.tools.javac.file.RelativePath
        public String toString() {
            return "RelativeFile[" + this.path + "]";
        }
    }
}
