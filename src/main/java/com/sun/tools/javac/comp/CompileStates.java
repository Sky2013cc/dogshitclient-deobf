package com.sun.tools.javac.comp;

import com.sun.tools.javac.util.Context;
import java.util.HashMap;

/* loaded from: target.jar:com/sun/tools/javac/comp/CompileStates.class */
public class CompileStates extends HashMap<Env<AttrContext>, CompileState> {
    protected static final Context.Key<CompileStates> compileStatesKey = new Context.Key<>();
    private static final long serialVersionUID = 1812267524140424433L;
    protected Context context;

    public static CompileStates instance(Context context) {
        CompileStates compileStates = (CompileStates) context.get(compileStatesKey);
        if (compileStates == null) {
            compileStates = new CompileStates(context);
        }
        return compileStates;
    }

    /* loaded from: target.jar:com/sun/tools/javac/comp/CompileStates$CompileState.class */
    public enum CompileState {
        INIT(0),
        PARSE(1),
        ENTER(2),
        PROCESS(3),
        ATTR(4),
        FLOW(5),
        TRANSTYPES(6),
        UNLAMBDA(7),
        LOWER(8),
        GENERATE(9);

        private final int value;

        CompileState(int i) {
            this.value = i;
        }

        public boolean isAfter(CompileState compileState) {
            return this.value > compileState.value;
        }

        public static CompileState max(CompileState compileState, CompileState compileState2) {
            return compileState.value > compileState2.value ? compileState : compileState2;
        }
    }

    public CompileStates(Context context) {
        this.context = context;
        context.put((Context.Key<Context.Key<CompileStates>>) compileStatesKey, (Context.Key<CompileStates>) this);
    }

    public boolean isDone(Env<AttrContext> env, CompileState compileState) {
        CompileState compileState2 = get(env);
        return (compileState2 == null || compileState.isAfter(compileState2)) ? false : true;
    }
}
