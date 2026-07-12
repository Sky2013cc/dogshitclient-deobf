package com.sun.tools.jdeps;

import com.sun.tools.classfile.Dependency;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: target.jar:com/sun/tools/jdeps/Archive.class */
public class Archive {
    private final Path path;
    private final String filename;
    private final ClassFileReader reader;
    protected Map<Dependency.Location, Set<Dependency.Location>> deps;

    /* loaded from: target.jar:com/sun/tools/jdeps/Archive$Visitor.class */
    interface Visitor {
        void visit(Dependency.Location location, Dependency.Location location2);
    }

    public static Archive getInstance(Path path) throws IOException {
        return new Archive(path, ClassFileReader.newInstance(path));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Archive(String str) {
        this.deps = new ConcurrentHashMap();
        this.path = null;
        this.filename = str;
        this.reader = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Archive(Path path, ClassFileReader classFileReader) {
        this.deps = new ConcurrentHashMap();
        this.path = path;
        this.filename = this.path.getFileName().toString();
        this.reader = classFileReader;
    }

    public ClassFileReader reader() {
        return this.reader;
    }

    public String getName() {
        return this.filename;
    }

    public void addClass(Dependency.Location location) {
        if (this.deps.get(location) == null) {
            this.deps.put(location, new HashSet());
        }
    }

    public void addClass(Dependency.Location location, Dependency.Location location2) {
        Set<Dependency.Location> set = this.deps.get(location);
        if (set == null) {
            set = new HashSet();
            this.deps.put(location, set);
        }
        set.add(location2);
    }

    public Set<Dependency.Location> getClasses() {
        return this.deps.keySet();
    }

    public void visitDependences(Visitor visitor) {
        for (Map.Entry<Dependency.Location, Set<Dependency.Location>> entry : this.deps.entrySet()) {
            Iterator<Dependency.Location> it = entry.getValue().iterator();
            while (it.hasNext()) {
                visitor.visit(entry.getKey(), it.next());
            }
        }
    }

    public boolean isEmpty() {
        return getClasses().isEmpty();
    }

    public String getPathName() {
        return this.path != null ? this.path.toString() : this.filename;
    }

    public String toString() {
        return this.filename;
    }
}
