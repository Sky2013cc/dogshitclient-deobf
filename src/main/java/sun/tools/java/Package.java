package sun.tools.java;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

/* loaded from: target.jar:sun/tools/java/Package.class */
public class Package {
    ClassPath sourcePath;
    ClassPath binaryPath;
    String pkg;

    public Package(ClassPath classPath, Identifier identifier) throws IOException {
        this(classPath, classPath, identifier);
    }

    public Package(ClassPath classPath, ClassPath classPath2, Identifier identifier) throws IOException {
        identifier = identifier.isInner() ? Identifier.lookup(identifier.getQualifier(), identifier.getFlatName()) : identifier;
        this.sourcePath = classPath;
        this.binaryPath = classPath2;
        this.pkg = identifier.toString().replace('.', File.separatorChar);
    }

    public boolean classExists(Identifier identifier) {
        return (getBinaryFile(identifier) == null && (identifier.isInner() || getSourceFile(identifier) == null)) ? false : true;
    }

    public boolean exists() {
        ClassFile directory;
        ClassFile directory2 = this.binaryPath.getDirectory(this.pkg);
        if (directory2 != null && directory2.isDirectory()) {
            return true;
        }
        if (this.sourcePath != this.binaryPath && (directory = this.sourcePath.getDirectory(this.pkg)) != null && directory.isDirectory()) {
            return true;
        }
        String str = this.pkg + File.separator;
        return this.binaryPath.getFiles(str, ".class").hasMoreElements() || this.sourcePath.getFiles(str, sun.rmi.rmic.iiop.Constants.SOURCE_FILE_EXTENSION).hasMoreElements();
    }

    private String makeName(String str) {
        return this.pkg.equals("") ? str : this.pkg + File.separator + str;
    }

    public ClassFile getBinaryFile(Identifier identifier) {
        return this.binaryPath.getFile(makeName(Type.mangleInnerType(identifier).toString() + ".class"));
    }

    public ClassFile getSourceFile(Identifier identifier) {
        return this.sourcePath.getFile(makeName(identifier.getTopName().toString() + sun.rmi.rmic.iiop.Constants.SOURCE_FILE_EXTENSION));
    }

    public ClassFile getSourceFile(String str) {
        if (str.endsWith(sun.rmi.rmic.iiop.Constants.SOURCE_FILE_EXTENSION)) {
            return this.sourcePath.getFile(makeName(str));
        }
        return null;
    }

    public Enumeration getSourceFiles() {
        return this.sourcePath.getFiles(this.pkg, sun.rmi.rmic.iiop.Constants.SOURCE_FILE_EXTENSION);
    }

    public Enumeration getBinaryFiles() {
        return this.binaryPath.getFiles(this.pkg, ".class");
    }

    public String toString() {
        if (this.pkg.equals("")) {
            return "unnamed package";
        }
        return "package " + this.pkg;
    }
}
