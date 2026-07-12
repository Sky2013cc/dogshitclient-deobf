package com.sun.tools.javac.comp;

import com.sun.tools.javac.util.Context;
import java.util.AbstractQueue;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javax.tools.JavaFileObject;

/* loaded from: target.jar:com/sun/tools/javac/comp/Todo.class */
public class Todo extends AbstractQueue<Env<AttrContext>> {
    protected static final Context.Key<Todo> todoKey = new Context.Key<>();
    LinkedList<Env<AttrContext>> contents = new LinkedList<>();
    LinkedList<Queue<Env<AttrContext>>> contentsByFile;
    Map<JavaFileObject, FileQueue> fileMap;

    public static Todo instance(Context context) {
        Todo todo = (Todo) context.get(todoKey);
        if (todo == null) {
            todo = new Todo(context);
        }
        return todo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Todo(Context context) {
        context.put((Context.Key<Context.Key<Todo>>) todoKey, (Context.Key<Todo>) this);
    }

    public void append(Env<AttrContext> env) {
        add(env);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<Env<AttrContext>> iterator() {
        return this.contents.iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.contents.size();
    }

    @Override // java.util.Queue
    public boolean offer(Env<AttrContext> env) {
        if (this.contents.add(env)) {
            if (this.contentsByFile != null) {
                addByFile(env);
                return true;
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Queue
    public Env<AttrContext> poll() {
        if (size() == 0) {
            return null;
        }
        Env<AttrContext> remove = this.contents.remove(0);
        if (this.contentsByFile != null) {
            removeByFile(remove);
        }
        return remove;
    }

    @Override // java.util.Queue
    public Env<AttrContext> peek() {
        if (size() == 0) {
            return null;
        }
        return this.contents.get(0);
    }

    public Queue<Queue<Env<AttrContext>>> groupByFile() {
        if (this.contentsByFile == null) {
            this.contentsByFile = new LinkedList<>();
            Iterator<Env<AttrContext>> it = this.contents.iterator();
            while (it.hasNext()) {
                addByFile(it.next());
            }
        }
        return this.contentsByFile;
    }

    private void addByFile(Env<AttrContext> env) {
        JavaFileObject javaFileObject = env.toplevel.sourcefile;
        if (this.fileMap == null) {
            this.fileMap = new HashMap();
        }
        FileQueue fileQueue = this.fileMap.get(javaFileObject);
        if (fileQueue == null) {
            fileQueue = new FileQueue();
            this.fileMap.put(javaFileObject, fileQueue);
            this.contentsByFile.add(fileQueue);
        }
        fileQueue.fileContents.add(env);
    }

    private void removeByFile(Env<AttrContext> env) {
        JavaFileObject javaFileObject = env.toplevel.sourcefile;
        FileQueue fileQueue = this.fileMap.get(javaFileObject);
        if (fileQueue != null && fileQueue.fileContents.remove(env) && fileQueue.isEmpty()) {
            this.fileMap.remove(javaFileObject);
            this.contentsByFile.remove(fileQueue);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: target.jar:com/sun/tools/javac/comp/Todo$FileQueue.class */
    public class FileQueue extends AbstractQueue<Env<AttrContext>> {
        LinkedList<Env<AttrContext>> fileContents = new LinkedList<>();

        FileQueue() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Env<AttrContext>> iterator() {
            return this.fileContents.iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.fileContents.size();
        }

        @Override // java.util.Queue
        public boolean offer(Env<AttrContext> env) {
            if (this.fileContents.offer(env)) {
                Todo.this.contents.add(env);
                return true;
            }
            return false;
        }

        @Override // java.util.Queue
        public Env<AttrContext> poll() {
            if (this.fileContents.size() == 0) {
                return null;
            }
            Env<AttrContext> remove = this.fileContents.remove(0);
            Todo.this.contents.remove(remove);
            return remove;
        }

        @Override // java.util.Queue
        public Env<AttrContext> peek() {
            if (this.fileContents.size() == 0) {
                return null;
            }
            return this.fileContents.get(0);
        }
    }
}
