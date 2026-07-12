package com.sun.tools.jdeps;

import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Dependencies;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/* loaded from: target.jar:com/sun/tools/jdeps/ClassFileReader.class */
public class ClassFileReader {
    protected final Path path;
    protected final String baseFileName;
    protected final List<String> skippedEntries = new ArrayList();

    public static ClassFileReader newInstance(Path path) throws IOException {
        if (!Files.exists(path, new LinkOption[0])) {
            throw new FileNotFoundException(path.toString());
        }
        if (Files.isDirectory(path, new LinkOption[0])) {
            return new DirectoryReader(path);
        }
        if (path.getFileName().toString().endsWith(".jar")) {
            return new JarFileReader(path);
        }
        return new ClassFileReader(path);
    }

    public static ClassFileReader newInstance(Path path, JarFile jarFile) throws IOException {
        return new JarFileReader(path, jarFile);
    }

    protected ClassFileReader(Path path) {
        String path2;
        this.path = path;
        if (path.getFileName() != null) {
            path2 = path.getFileName().toString();
        } else {
            path2 = path.toString();
        }
        this.baseFileName = path2;
    }

    public String getFileName() {
        return this.baseFileName;
    }

    public List<String> skippedEntries() {
        return this.skippedEntries;
    }

    public ClassFile getClassFile(String str) throws IOException {
        if (str.indexOf(46) > 0) {
            int lastIndexOf = str.lastIndexOf(46);
            String str2 = str.replace('.', File.separatorChar) + ".class";
            if (this.baseFileName.equals(str2) || this.baseFileName.equals(str2.substring(0, lastIndexOf) + "$" + str2.substring(lastIndexOf + 1, str2.length()))) {
                return readClassFile(this.path);
            }
            return null;
        }
        if (this.baseFileName.equals(str.replace('/', File.separatorChar) + ".class")) {
            return readClassFile(this.path);
        }
        return null;
    }

    public Iterable<ClassFile> getClassFiles() throws IOException {
        return new Iterable<ClassFile>() { // from class: com.sun.tools.jdeps.ClassFileReader.1
            @Override // java.lang.Iterable
            public Iterator<ClassFile> iterator() {
                return new FileIterator();
            }
        };
    }

    protected ClassFile readClassFile(Path path) throws IOException {
        InputStream inputStream = null;
        try {
            try {
                inputStream = Files.newInputStream(path, new OpenOption[0]);
                ClassFile read = ClassFile.read(inputStream);
                if (inputStream != null) {
                    inputStream.close();
                }
                return read;
            } catch (ConstantPoolException e) {
                throw new Dependencies.ClassFileError(e);
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    /* loaded from: target.jar:com/sun/tools/jdeps/ClassFileReader$FileIterator.class */
    class FileIterator implements Iterator<ClassFile> {
        int count = 0;

        FileIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.count == 0 && ClassFileReader.this.baseFileName.endsWith(".class");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public ClassFile next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            try {
                ClassFile readClassFile = ClassFileReader.this.readClassFile(ClassFileReader.this.path);
                this.count++;
                return readClassFile;
            } catch (IOException e) {
                throw new Dependencies.ClassFileError(e);
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public boolean isMultiReleaseJar() throws IOException {
        return false;
    }

    public String toString() {
        return this.path.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: target.jar:com/sun/tools/jdeps/ClassFileReader$DirectoryReader.class */
    public static class DirectoryReader extends ClassFileReader {
        DirectoryReader(Path path) throws IOException {
            super(path);
        }

        @Override // com.sun.tools.jdeps.ClassFileReader
        public ClassFile getClassFile(String str) throws IOException {
            if (str.indexOf(46) > 0) {
                int lastIndexOf = str.lastIndexOf(46);
                String str2 = str.replace('.', File.separatorChar) + ".class";
                Path resolve = this.path.resolve(str2);
                if (!Files.exists(resolve, new LinkOption[0])) {
                    resolve = this.path.resolve(str2.substring(0, lastIndexOf) + "$" + str2.substring(lastIndexOf + 1, str2.length()));
                }
                if (Files.exists(resolve, new LinkOption[0])) {
                    return readClassFile(resolve);
                }
                return null;
            }
            Path resolve2 = this.path.resolve(str + ".class");
            if (Files.exists(resolve2, new LinkOption[0])) {
                return readClassFile(resolve2);
            }
            return null;
        }

        @Override // com.sun.tools.jdeps.ClassFileReader
        public Iterable<ClassFile> getClassFiles() throws IOException {
            final DirectoryIterator directoryIterator = new DirectoryIterator();
            return new Iterable<ClassFile>() { // from class: com.sun.tools.jdeps.ClassFileReader.DirectoryReader.1
                @Override // java.lang.Iterable
                public Iterator<ClassFile> iterator() {
                    return directoryIterator;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<Path> walkTree(Path path) throws IOException {
            final ArrayList arrayList = new ArrayList();
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() { // from class: com.sun.tools.jdeps.ClassFileReader.DirectoryReader.2
                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult visitFile(Path path2, BasicFileAttributes basicFileAttributes) throws IOException {
                    if (path2.getFileName().toString().endsWith(".class")) {
                        arrayList.add(path2);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            return arrayList;
        }

        /* loaded from: target.jar:com/sun/tools/jdeps/ClassFileReader$DirectoryReader$DirectoryIterator.class */
        class DirectoryIterator implements Iterator<ClassFile> {
            private List<Path> entries;
            private int index;

            DirectoryIterator() throws IOException {
                this.index = 0;
                this.entries = DirectoryReader.this.walkTree(DirectoryReader.this.path);
                this.index = 0;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index != this.entries.size();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public ClassFile next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                List<Path> list = this.entries;
                int i = this.index;
                this.index = i + 1;
                try {
                    return DirectoryReader.this.readClassFile(list.get(i));
                } catch (IOException e) {
                    throw new Dependencies.ClassFileError(e);
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/jdeps/ClassFileReader$JarFileReader.class */
    public static class JarFileReader extends ClassFileReader {
        private final JarFile jarfile;

        JarFileReader(Path path) throws IOException {
            this(path, new JarFile(path.toFile(), false));
        }

        JarFileReader(Path path, JarFile jarFile) throws IOException {
            super(path);
            this.jarfile = jarFile;
        }

        @Override // com.sun.tools.jdeps.ClassFileReader
        public ClassFile getClassFile(String str) throws IOException {
            if (str.indexOf(46) > 0) {
                int lastIndexOf = str.lastIndexOf(46);
                String str2 = str.replace('.', '/') + ".class";
                JarEntry jarEntry = this.jarfile.getJarEntry(str2);
                if (jarEntry == null) {
                    jarEntry = this.jarfile.getJarEntry(str2.substring(0, lastIndexOf) + "$" + str2.substring(lastIndexOf + 1, str2.length()));
                }
                if (jarEntry != null) {
                    return readClassFile(this.jarfile, jarEntry);
                }
                return null;
            }
            JarEntry jarEntry2 = this.jarfile.getJarEntry(str + ".class");
            if (jarEntry2 != null) {
                return readClassFile(this.jarfile, jarEntry2);
            }
            return null;
        }

        protected ClassFile readClassFile(JarFile jarFile, JarEntry jarEntry) throws IOException {
            InputStream inputStream = null;
            try {
                try {
                    inputStream = jarFile.getInputStream(jarEntry);
                    ClassFile read = ClassFile.read(inputStream);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return read;
                } catch (ConstantPoolException e) {
                    throw new Dependencies.ClassFileError(e);
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        }

        @Override // com.sun.tools.jdeps.ClassFileReader
        public Iterable<ClassFile> getClassFiles() throws IOException {
            final JarFileIterator jarFileIterator = new JarFileIterator(this, this.jarfile);
            return new Iterable<ClassFile>() { // from class: com.sun.tools.jdeps.ClassFileReader.JarFileReader.1
                @Override // java.lang.Iterable
                public Iterator<ClassFile> iterator() {
                    return jarFileIterator;
                }
            };
        }

        @Override // com.sun.tools.jdeps.ClassFileReader
        public boolean isMultiReleaseJar() throws IOException {
            Manifest manifest = this.jarfile.getManifest();
            if (manifest != null) {
                return Constants.TRUE.equalsIgnoreCase(manifest.getMainAttributes().getValue("Multi-Release"));
            }
            return false;
        }
    }

    /* loaded from: target.jar:com/sun/tools/jdeps/ClassFileReader$JarFileIterator.class */
    class JarFileIterator implements Iterator<ClassFile> {
        protected final JarFileReader reader;
        protected Enumeration<JarEntry> entries;
        protected JarFile jf;
        protected JarEntry nextEntry;
        protected ClassFile cf;

        JarFileIterator(ClassFileReader classFileReader, JarFileReader jarFileReader) {
            this(jarFileReader, null);
        }

        JarFileIterator(JarFileReader jarFileReader, JarFile jarFile) {
            this.reader = jarFileReader;
            setJarFile(jarFile);
        }

        void setJarFile(JarFile jarFile) {
            if (jarFile == null) {
                return;
            }
            this.jf = jarFile;
            this.entries = this.jf.entries();
            this.nextEntry = nextEntry();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextEntry != null && this.cf != null) {
                return true;
            }
            while (this.nextEntry != null) {
                try {
                    this.cf = this.reader.readClassFile(this.jf, this.nextEntry);
                    return true;
                } catch (Dependencies.ClassFileError | IOException e) {
                    ClassFileReader.this.skippedEntries.add(this.nextEntry.getName());
                    this.nextEntry = nextEntry();
                }
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public ClassFile next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            ClassFile classFile = this.cf;
            this.cf = null;
            this.nextEntry = nextEntry();
            return classFile;
        }

        protected JarEntry nextEntry() {
            while (this.entries.hasMoreElements()) {
                JarEntry nextElement = this.entries.nextElement();
                if (nextElement.getName().endsWith(".class")) {
                    return nextElement;
                }
            }
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
