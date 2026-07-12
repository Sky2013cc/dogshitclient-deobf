package com.sun.tools.javac.nio;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

/* loaded from: target.jar:com/sun/tools/javac/nio/PathFileManager.class */
public interface PathFileManager extends JavaFileManager {
    FileSystem getDefaultFileSystem();

    void setDefaultFileSystem(FileSystem fileSystem);

    Iterable<? extends JavaFileObject> getJavaFileObjectsFromPaths(Iterable<? extends Path> iterable);

    Iterable<? extends JavaFileObject> getJavaFileObjects(Path... pathArr);

    Path getPath(FileObject fileObject);

    Iterable<? extends Path> getLocation(JavaFileManager.Location location);

    void setLocation(JavaFileManager.Location location, Iterable<? extends Path> iterable) throws IOException;
}
