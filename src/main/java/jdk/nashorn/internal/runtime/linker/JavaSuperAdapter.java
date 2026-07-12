package jdk.nashorn.internal.runtime.linker;

import java.util.Objects;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/linker/JavaSuperAdapter.class */
class JavaSuperAdapter {
    private final Object adapter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JavaSuperAdapter(Object adapter) {
        this.adapter = Objects.requireNonNull(adapter);
    }

    public Object getAdapter() {
        return this.adapter;
    }
}
