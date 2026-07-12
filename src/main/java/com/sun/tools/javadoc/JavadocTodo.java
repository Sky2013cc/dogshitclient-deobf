package com.sun.tools.javadoc;

import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.comp.Todo;
import com.sun.tools.javac.util.Context;

/* loaded from: target.jar:com/sun/tools/javadoc/JavadocTodo.class */
public class JavadocTodo extends Todo {
    public static void preRegister(Context context) {
        context.put((Context.Key) todoKey, (Context.Factory) new Context.Factory<Todo>() { // from class: com.sun.tools.javadoc.JavadocTodo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sun.tools.javac.util.Context.Factory
            public Todo make(Context context2) {
                return new JavadocTodo(context2);
            }
        });
    }

    protected JavadocTodo(Context context) {
        super(context);
    }

    @Override // com.sun.tools.javac.comp.Todo
    public void append(Env<AttrContext> env) {
    }

    @Override // com.sun.tools.javac.comp.Todo, java.util.Queue
    public boolean offer(Env<AttrContext> env) {
        return false;
    }
}
